// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.SparseIntArray;
import android.graphics.Rect;

public class GridLayoutManager extends LinearLayoutManager
{
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    static final int MAIN_DIR_SPEC;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    
    static {
        MAIN_DIR_SPEC = View$MeasureSpec.makeMeasureSpec(0, 0);
    }
    
    public GridLayoutManager(final Context context, final int spanCount) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final int spanCount, final int n, final boolean b) {
        super(context, n, b);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(RecyclerView.LayoutManager.getProperties(context, set, n, n2).spanCount);
    }
    
    private void assignSpans(final Recycler recycler, final State state, int i, int n, final boolean b) {
        int n2;
        int n3;
        if (b) {
            n = 0;
            n2 = i;
            n3 = 1;
            i = n;
        }
        else {
            --i;
            n2 = -1;
            n3 = -1;
        }
        int n4;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n = this.mSpanCount - 1;
            n4 = -1;
        }
        else {
            n = 0;
            n4 = 1;
        }
        while (i != n2) {
            final View view = this.mSet[i];
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            layoutParams.mSpanSize = this.getSpanSize(recycler, state, ((RecyclerView.LayoutManager)this).getPosition(view));
            if (n4 == -1 && layoutParams.mSpanSize > 1) {
                layoutParams.mSpanIndex = n - (layoutParams.mSpanSize - 1);
            }
            else {
                layoutParams.mSpanIndex = n;
            }
            n += layoutParams.mSpanSize * n4;
            i += n3;
        }
    }
    
    private void cachePreLayoutSpanMapping() {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final LayoutParams layoutParams = (LayoutParams)((RecyclerView.LayoutManager)this).getChildAt(i).getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }
    
    private void calculateItemBorders(int n) {
        if (this.mCachedBorders == null || this.mCachedBorders.length != this.mSpanCount + 1 || this.mCachedBorders[this.mCachedBorders.length - 1] != n) {
            this.mCachedBorders = new int[this.mSpanCount + 1];
        }
        this.mCachedBorders[0] = 0;
        final int n2 = n / this.mSpanCount;
        final int n3 = n % this.mSpanCount;
        int n4 = 0;
        n = 0;
        for (int i = 1; i <= this.mSpanCount; ++i) {
            final int n5 = n2;
            final int n6 = n += n3;
            int n7 = n5;
            if (n6 > 0) {
                n = n6;
                n7 = n5;
                if (this.mSpanCount - n6 < n3) {
                    n7 = n5 + 1;
                    n = n6 - this.mSpanCount;
                }
            }
            n4 += n7;
            this.mCachedBorders[i] = n4;
        }
    }
    
    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }
    
    private void ensureAnchorIsInFirstSpan(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        for (int n = this.getSpanIndex(recycler, state, anchorInfo.mPosition); n > 0 && anchorInfo.mPosition > 0; n = this.getSpanIndex(recycler, state, anchorInfo.mPosition)) {
            --anchorInfo.mPosition;
        }
    }
    
    private int getMainDirSpec(final int n) {
        if (n < 0) {
            return GridLayoutManager.MAIN_DIR_SPEC;
        }
        return View$MeasureSpec.makeMeasureSpec(n, 1073741824);
    }
    
    private int getSpanGroupIndex(final Recycler recycler, final State state, final int n) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(n, this.mSpanCount);
        }
        final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + n);
            return 0;
        }
        return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
    }
    
    private int getSpanIndex(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getCachedSpanIndex(n, this.mSpanCount);
        }
        else if ((n2 = this.mPreLayoutSpanIndexCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 0;
            }
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        return n2;
    }
    
    private int getSpanSize(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanSize(n);
        }
        else if ((n2 = this.mPreLayoutSpanSizeCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 1;
            }
            return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        return n2;
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, int updateSpecWithExtra, final int n, final boolean b) {
        ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mDecorInsets);
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        int updateSpecWithExtra2 = 0;
        Label_0067: {
            if (!b) {
                updateSpecWithExtra2 = updateSpecWithExtra;
                if (this.mOrientation != 1) {
                    break Label_0067;
                }
            }
            updateSpecWithExtra2 = this.updateSpecWithExtra(updateSpecWithExtra, layoutParams.leftMargin + this.mDecorInsets.left, layoutParams.rightMargin + this.mDecorInsets.right);
        }
        Label_0113: {
            if (!b) {
                updateSpecWithExtra = n;
                if (this.mOrientation != 0) {
                    break Label_0113;
                }
            }
            updateSpecWithExtra = this.updateSpecWithExtra(n, layoutParams.topMargin + this.mDecorInsets.top, layoutParams.bottomMargin + this.mDecorInsets.bottom);
        }
        view.measure(updateSpecWithExtra2, updateSpecWithExtra);
    }
    
    private void updateMeasurements() {
        int n;
        if (this.getOrientation() == 1) {
            n = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight() - ((RecyclerView.LayoutManager)this).getPaddingLeft();
        }
        else {
            n = ((RecyclerView.LayoutManager)this).getHeight() - ((RecyclerView.LayoutManager)this).getPaddingBottom() - ((RecyclerView.LayoutManager)this).getPaddingTop();
        }
        this.calculateItemBorders(n);
    }
    
    private int updateSpecWithExtra(final int n, final int n2, final int n3) {
        if (n2 != 0 || n3 != 0) {
            final int mode = View$MeasureSpec.getMode(n);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                return View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(n) - n2 - n3, mode);
            }
        }
        return n;
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
    
    @Override
    View findReferenceChild(final Recycler recycler, final State state, int i, final int n, final int n2) {
        this.ensureLayoutState();
        View view = null;
        View view2 = null;
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n3;
        if (n > i) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        while (i != n) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int position = ((RecyclerView.LayoutManager)this).getPosition(child);
            View view3 = view;
            View view4 = view2;
            if (position >= 0) {
                view3 = view;
                view4 = view2;
                if (position < n2) {
                    if (this.getSpanIndex(recycler, state, position) != 0) {
                        view4 = view2;
                        view3 = view;
                    }
                    else if (((RecyclerView.LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                        view3 = view;
                        view4 = view2;
                        if (view == null) {
                            view3 = child;
                            view4 = view2;
                        }
                    }
                    else {
                        if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding) {
                            final View view5 = child;
                            if (this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                                return view5;
                            }
                        }
                        view3 = view;
                        if ((view4 = view2) == null) {
                            view3 = view;
                            view4 = child;
                        }
                    }
                }
            }
            i += n3;
            view = view3;
            view2 = view4;
        }
        if (view2 == null) {
            view2 = view;
        }
        return view2;
    }
    
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    @Override
    public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recycler, state, state.getItemCount() - 1);
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recycler, state, state.getItemCount() - 1);
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
    
    @Override
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final boolean b = layoutState.mItemDirection == 1;
        final int n = 0;
        final int n2 = 0;
        int mSpanCount = this.mSpanCount;
        int n3 = n;
        int n4 = n2;
        if (!b) {
            mSpanCount = this.getSpanIndex(recycler, state, layoutState.mCurrentPosition) + this.getSpanSize(recycler, state, layoutState.mCurrentPosition);
            n4 = n2;
            n3 = n;
        }
        while (n3 < this.mSpanCount && layoutState.hasMore(state) && mSpanCount > 0) {
            final int mCurrentPosition = layoutState.mCurrentPosition;
            final int spanSize = this.getSpanSize(recycler, state, mCurrentPosition);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + mCurrentPosition + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            mSpanCount -= spanSize;
            if (mSpanCount < 0) {
                break;
            }
            final View next = layoutState.next(recycler);
            if (next == null) {
                break;
            }
            n4 += spanSize;
            this.mSet[n3] = next;
            ++n3;
        }
        if (n3 == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int mConsumed = 0;
        this.assignSpans(recycler, state, n3, n4, b);
        int n5;
        for (int i = 0; i < n3; ++i, mConsumed = n5) {
            final View view = this.mSet[i];
            if (layoutState.mScrapList == null) {
                if (b) {
                    ((RecyclerView.LayoutManager)this).addView(view);
                }
                else {
                    ((RecyclerView.LayoutManager)this).addView(view, 0);
                }
            }
            else if (b) {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view);
            }
            else {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view, 0);
            }
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.mCachedBorders[layoutParams.mSpanIndex + layoutParams.mSpanSize] - this.mCachedBorders[layoutParams.mSpanIndex], 1073741824);
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, measureSpec, this.getMainDirSpec(layoutParams.height), false);
            }
            else {
                this.measureChildWithDecorationsAndMargin(view, this.getMainDirSpec(layoutParams.width), measureSpec, false);
            }
            final int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            n5 = mConsumed;
            if (decoratedMeasurement > mConsumed) {
                n5 = decoratedMeasurement;
            }
        }
        final int mainDirSpec = this.getMainDirSpec(mConsumed);
        for (int j = 0; j < n3; ++j) {
            final View view2 = this.mSet[j];
            if (this.mOrientationHelper.getDecoratedMeasurement(view2) != mConsumed) {
                final LayoutParams layoutParams2 = (LayoutParams)view2.getLayoutParams();
                final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(this.mCachedBorders[layoutParams2.mSpanIndex + layoutParams2.mSpanSize] - this.mCachedBorders[layoutParams2.mSpanIndex], 1073741824);
                if (this.mOrientation == 1) {
                    this.measureChildWithDecorationsAndMargin(view2, measureSpec2, mainDirSpec, true);
                }
                else {
                    this.measureChildWithDecorationsAndMargin(view2, mainDirSpec, measureSpec2, true);
                }
            }
        }
        layoutChunkResult.mConsumed = mConsumed;
        int mOffset = 0;
        int mOffset2 = 0;
        int mOffset3 = 0;
        final int n6 = 0;
        int n7;
        if (this.mOrientation == 1) {
            if (layoutState.mLayoutDirection == -1) {
                final int mOffset4 = layoutState.mOffset;
                mOffset3 = mOffset4 - mConsumed;
                n7 = mOffset4;
            }
            else {
                mOffset3 = layoutState.mOffset;
                n7 = mOffset3 + mConsumed;
            }
        }
        else if (layoutState.mLayoutDirection == -1) {
            mOffset2 = layoutState.mOffset;
            mOffset = mOffset2 - mConsumed;
            n7 = n6;
        }
        else {
            mOffset = layoutState.mOffset;
            mOffset2 = mOffset + mConsumed;
            n7 = n6;
        }
        final int n8 = 0;
        int n9 = mOffset3;
        int n10 = mOffset;
        for (int k = n8; k < n3; ++k) {
            final View view3 = this.mSet[k];
            final LayoutParams layoutParams3 = (LayoutParams)view3.getLayoutParams();
            if (this.mOrientation == 1) {
                n10 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[layoutParams3.mSpanIndex];
                mOffset2 = n10 + this.mOrientationHelper.getDecoratedMeasurementInOther(view3);
            }
            else {
                n9 = ((RecyclerView.LayoutManager)this).getPaddingTop() + this.mCachedBorders[layoutParams3.mSpanIndex];
                n7 = n9 + this.mOrientationHelper.getDecoratedMeasurementInOther(view3);
            }
            ((RecyclerView.LayoutManager)this).layoutDecorated(view3, n10 + layoutParams3.leftMargin, n9 + layoutParams3.topMargin, mOffset2 - layoutParams3.rightMargin, n7 - layoutParams3.bottomMargin);
            if (((RecyclerView.LayoutParams)layoutParams3).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams3).isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view3.isFocusable();
        }
        Arrays.fill(this.mSet, null);
    }
    
    @Override
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        super.onAnchorReady(recycler, state, anchorInfo);
        this.updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            this.ensureAnchorIsInFirstSpan(recycler, state, anchorInfo);
        }
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
        final int spanGroupIndex = this.getSpanGroupIndex(recycler, state, ((RecyclerView.LayoutParams)layoutParams2).getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
            return;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if (state.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        this.clearPreLayoutSpanMappingCache();
        if (!state.isPreLayout()) {
            this.mPendingSpanCountChange = false;
        }
    }
    
    public void setSpanCount(final int mSpanCount) {
        if (mSpanCount == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (mSpanCount < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
        }
        this.mSpanCount = mSpanCount;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    public void setSpanSizeLookup(final SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
    
    @Override
    public void setStackFromEnd(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
    
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup
    {
        @Override
        public int getSpanIndex(final int n, final int n2) {
            return n % n2;
        }
        
        @Override
        public int getSpanSize(final int n) {
            return 1;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        private int mSpanIndex;
        private int mSpanSize;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public int getSpanIndex() {
            return this.mSpanIndex;
        }
        
        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
    
    public abstract static class SpanSizeLookup
    {
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanIndexCache;
        
        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
        }
        
        int findReferenceIndexFromCache(int n) {
            int i = 0;
            int n2 = this.mSpanIndexCache.size() - 1;
            while (i <= n2) {
                final int n3 = i + n2 >>> 1;
                if (this.mSpanIndexCache.keyAt(n3) < n) {
                    i = n3 + 1;
                }
                else {
                    n2 = n3 - 1;
                }
            }
            n = i - 1;
            if (n >= 0 && n < this.mSpanIndexCache.size()) {
                return this.mSpanIndexCache.keyAt(n);
            }
            return -1;
        }
        
        int getCachedSpanIndex(final int n, int spanIndex) {
            int n2;
            if (!this.mCacheSpanIndices) {
                n2 = this.getSpanIndex(n, spanIndex);
            }
            else if ((n2 = this.mSpanIndexCache.get(n, -1)) == -1) {
                spanIndex = this.getSpanIndex(n, spanIndex);
                this.mSpanIndexCache.put(n, spanIndex);
                return spanIndex;
            }
            return n2;
        }
        
        public int getSpanGroupIndex(int n, final int n2) {
            int n3 = 0;
            int n4 = 0;
            final int spanSize = this.getSpanSize(n);
            int n6;
            for (int i = 0; i < n; ++i, n4 = n6) {
                final int spanSize2 = this.getSpanSize(i);
                final int n5 = n3 + spanSize2;
                if (n5 == n2) {
                    n3 = 0;
                    n6 = n4 + 1;
                }
                else {
                    n6 = n4;
                    if ((n3 = n5) > n2) {
                        n3 = spanSize2;
                        n6 = n4 + 1;
                    }
                }
            }
            n = n4;
            if (n3 + spanSize > n2) {
                n = n4 + 1;
            }
            return n;
        }
        
        public int getSpanIndex(int n, final int n2) {
            final int spanSize = this.getSpanSize(n);
            if (spanSize == n2) {
                n = 0;
            }
            else {
                final boolean b = false;
                final boolean b2 = false;
                int n3 = b ? 1 : 0;
                int i = b2 ? 1 : 0;
                if (this.mCacheSpanIndices) {
                    n3 = (b ? 1 : 0);
                    i = (b2 ? 1 : 0);
                    if (this.mSpanIndexCache.size() > 0) {
                        final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                        n3 = (b ? 1 : 0);
                        i = (b2 ? 1 : 0);
                        if (referenceIndexFromCache >= 0) {
                            n3 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                            i = referenceIndexFromCache + 1;
                        }
                    }
                }
                while (i < n) {
                    final int spanSize2 = this.getSpanSize(i);
                    final int n4 = n3 + spanSize2;
                    if (n4 == n2) {
                        n3 = 0;
                    }
                    else if ((n3 = n4) > n2) {
                        n3 = spanSize2;
                    }
                    ++i;
                }
                n = n3;
                if (n3 + spanSize > n2) {
                    return 0;
                }
            }
            return n;
        }
        
        public abstract int getSpanSize(final int p0);
        
        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
        
        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }
        
        public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
            this.mCacheSpanIndices = mCacheSpanIndices;
        }
    }
}
