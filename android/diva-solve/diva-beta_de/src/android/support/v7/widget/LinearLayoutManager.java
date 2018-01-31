// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.PointF;
import android.util.Log;
import java.util.List;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper;

public class LinearLayoutManager extends LayoutManager implements ViewDropHandler
{
    private static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33f;
    private static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    final AnchorInfo mAnchorInfo;
    private boolean mLastStackFromEnd;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;
    
    public LinearLayoutManager(final Context context) {
        this(context, 1, false);
    }
    
    public LinearLayoutManager(final Context context, final int orientation, final boolean reverseLayout) {
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.setOrientation(orientation);
        this.setReverseLayout(reverseLayout);
    }
    
    public LinearLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        final Properties properties = RecyclerView.LayoutManager.getProperties(context, set, n, n2);
        this.setOrientation(properties.orientation);
        this.setReverseLayout(properties.reverseLayout);
        this.setStackFromEnd(properties.stackFromEnd);
    }
    
    private int computeScrollExtent(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        final OrientationHelper mOrientationHelper = this.mOrientationHelper;
        final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollExtent(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private int computeScrollOffset(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        final OrientationHelper mOrientationHelper = this.mOrientationHelper;
        final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollOffset(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
    
    private int computeScrollRange(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureLayoutState();
        final OrientationHelper mOrientationHelper = this.mOrientationHelper;
        final View firstVisibleChildClosestToStart = this.findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollRange(state, mOrientationHelper, firstVisibleChildClosestToStart, this.findFirstVisibleChildClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private int convertFocusDirectionToLayoutDirection(int n) {
        final int n2 = -1;
        final int n3 = 1;
        final int n4 = Integer.MIN_VALUE;
        int n5 = n2;
        switch (n) {
            default: {
                n5 = Integer.MIN_VALUE;
                return n5;
            }
            case 1: {
                return n5;
            }
            case 2: {
                return 1;
            }
            case 33: {
                n5 = n2;
                if (this.mOrientation != 1) {
                    return Integer.MIN_VALUE;
                }
                return n5;
            }
            case 130: {
                n = n4;
                if (this.mOrientation == 1) {
                    n = 1;
                }
                return n;
            }
            case 17: {
                n5 = n2;
                if (this.mOrientation != 0) {
                    return Integer.MIN_VALUE;
                }
                return n5;
            }
            case 66: {
                if (this.mOrientation == 0) {
                    n = n3;
                }
                else {
                    n = Integer.MIN_VALUE;
                }
                return n;
            }
        }
    }
    
    private View findFirstReferenceChild(final Recycler recycler, final State state) {
        return this.findReferenceChild(recycler, state, 0, ((RecyclerView.LayoutManager)this).getChildCount(), state.getItemCount());
    }
    
    private View findFirstVisibleChildClosestToEnd(final boolean b, final boolean b2) {
        if (this.mShouldReverseLayout) {
            return this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), b, b2);
        }
        return this.findOneVisibleChild(((RecyclerView.LayoutManager)this).getChildCount() - 1, -1, b, b2);
    }
    
    private View findFirstVisibleChildClosestToStart(final boolean b, final boolean b2) {
        if (this.mShouldReverseLayout) {
            return this.findOneVisibleChild(((RecyclerView.LayoutManager)this).getChildCount() - 1, -1, b, b2);
        }
        return this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), b, b2);
    }
    
    private View findLastReferenceChild(final Recycler recycler, final State state) {
        return this.findReferenceChild(recycler, state, ((RecyclerView.LayoutManager)this).getChildCount() - 1, -1, state.getItemCount());
    }
    
    private View findReferenceChildClosestToEnd(final Recycler recycler, final State state) {
        if (this.mShouldReverseLayout) {
            return this.findFirstReferenceChild(recycler, state);
        }
        return this.findLastReferenceChild(recycler, state);
    }
    
    private View findReferenceChildClosestToStart(final Recycler recycler, final State state) {
        if (this.mShouldReverseLayout) {
            return this.findLastReferenceChild(recycler, state);
        }
        return this.findFirstReferenceChild(recycler, state);
    }
    
    private int fixLayoutEndGap(int n, final Recycler recycler, final State state, final boolean b) {
        final int n2 = this.mOrientationHelper.getEndAfterPadding() - n;
        if (n2 > 0) {
            final int n3 = -this.scrollBy(-n2, recycler, state);
            if (b) {
                n = this.mOrientationHelper.getEndAfterPadding() - (n + n3);
                if (n > 0) {
                    this.mOrientationHelper.offsetChildren(n);
                    return n + n3;
                }
            }
            return n3;
        }
        return 0;
    }
    
    private int fixLayoutStartGap(int n, final Recycler recycler, final State state, final boolean b) {
        final int n2 = n - this.mOrientationHelper.getStartAfterPadding();
        if (n2 > 0) {
            final int n3 = -this.scrollBy(n2, recycler, state);
            if (b) {
                n = n + n3 - this.mOrientationHelper.getStartAfterPadding();
                if (n > 0) {
                    this.mOrientationHelper.offsetChildren(-n);
                    return n3 - n;
                }
            }
            return n3;
        }
        return 0;
    }
    
    private View getChildClosestToEnd() {
        int n;
        if (this.mShouldReverseLayout) {
            n = 0;
        }
        else {
            n = ((RecyclerView.LayoutManager)this).getChildCount() - 1;
        }
        return ((RecyclerView.LayoutManager)this).getChildAt(n);
    }
    
    private View getChildClosestToStart() {
        int n;
        if (this.mShouldReverseLayout) {
            n = ((RecyclerView.LayoutManager)this).getChildCount() - 1;
        }
        else {
            n = 0;
        }
        return ((RecyclerView.LayoutManager)this).getChildAt(n);
    }
    
    private void layoutForPredictiveAnimations(final Recycler recycler, final State state, final int n, final int n2) {
        if (!state.willRunPredictiveAnimations() || ((RecyclerView.LayoutManager)this).getChildCount() == 0 || state.isPreLayout() || !this.supportsPredictiveItemAnimations()) {
            return;
        }
        int mExtra = 0;
        int mExtra2 = 0;
        final List<ViewHolder> scrapList = recycler.getScrapList();
        final int size = scrapList.size();
        final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
        for (int i = 0; i < size; ++i) {
            final ViewHolder viewHolder = scrapList.get(i);
            if (!viewHolder.isRemoved()) {
                int n3;
                if (viewHolder.getLayoutPosition() < position != this.mShouldReverseLayout) {
                    n3 = -1;
                }
                else {
                    n3 = 1;
                }
                if (n3 == -1) {
                    mExtra += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                }
                else {
                    mExtra2 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                }
            }
        }
        this.mLayoutState.mScrapList = scrapList;
        if (mExtra > 0) {
            this.updateLayoutStateToFillStart(((RecyclerView.LayoutManager)this).getPosition(this.getChildClosestToStart()), n);
            this.mLayoutState.mExtra = mExtra;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(recycler, this.mLayoutState, state, false);
        }
        if (mExtra2 > 0) {
            this.updateLayoutStateToFillEnd(((RecyclerView.LayoutManager)this).getPosition(this.getChildClosestToEnd()), n2);
            this.mLayoutState.mExtra = mExtra2;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(recycler, this.mLayoutState, state, false);
        }
        this.mLayoutState.mScrapList = null;
    }
    
    private void logChildren() {
        Log.d("LinearLayoutManager", "internal representation of views on the screen");
        for (int i = 0; i < ((RecyclerView.LayoutManager)this).getChildCount(); ++i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            Log.d("LinearLayoutManager", "item " + ((RecyclerView.LayoutManager)this).getPosition(child) + ", coord:" + this.mOrientationHelper.getDecoratedStart(child));
        }
        Log.d("LinearLayoutManager", "==============");
    }
    
    private void recycleByLayoutState(final Recycler recycler, final LayoutState layoutState) {
        if (!layoutState.mRecycle) {
            return;
        }
        if (layoutState.mLayoutDirection == -1) {
            this.recycleViewsFromEnd(recycler, layoutState.mScrollingOffset);
            return;
        }
        this.recycleViewsFromStart(recycler, layoutState.mScrollingOffset);
    }
    
    private void recycleChildren(final Recycler recycler, int i, int j) {
        if (i != j) {
            if (j > i) {
                for (--j; j >= i; --j) {
                    ((RecyclerView.LayoutManager)this).removeAndRecycleViewAt(j, recycler);
                }
            }
            else {
                while (i > j) {
                    ((RecyclerView.LayoutManager)this).removeAndRecycleViewAt(i, recycler);
                    --i;
                }
            }
        }
    }
    
    private void recycleViewsFromEnd(final Recycler recycler, int i) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        if (i >= 0) {
            final int n = this.mOrientationHelper.getEnd() - i;
            if (this.mShouldReverseLayout) {
                for (i = 0; i < childCount; ++i) {
                    if (this.mOrientationHelper.getDecoratedStart(((RecyclerView.LayoutManager)this).getChildAt(i)) < n) {
                        this.recycleChildren(recycler, 0, i);
                        return;
                    }
                }
            }
            else {
                for (i = childCount - 1; i >= 0; --i) {
                    if (this.mOrientationHelper.getDecoratedStart(((RecyclerView.LayoutManager)this).getChildAt(i)) < n) {
                        this.recycleChildren(recycler, childCount - 1, i);
                        return;
                    }
                }
            }
        }
    }
    
    private void recycleViewsFromStart(final Recycler recycler, final int n) {
        if (n >= 0) {
            final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
            if (this.mShouldReverseLayout) {
                for (int i = childCount - 1; i >= 0; --i) {
                    if (this.mOrientationHelper.getDecoratedEnd(((RecyclerView.LayoutManager)this).getChildAt(i)) > n) {
                        this.recycleChildren(recycler, childCount - 1, i);
                        return;
                    }
                }
            }
            else {
                for (int j = 0; j < childCount; ++j) {
                    if (this.mOrientationHelper.getDecoratedEnd(((RecyclerView.LayoutManager)this).getChildAt(j)) > n) {
                        this.recycleChildren(recycler, 0, j);
                        return;
                    }
                }
            }
        }
    }
    
    private void resolveShouldLayoutReverse() {
        boolean mShouldReverseLayout = true;
        if (this.mOrientation == 1 || !this.isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
            return;
        }
        if (this.mReverseLayout) {
            mShouldReverseLayout = false;
        }
        this.mShouldReverseLayout = mShouldReverseLayout;
    }
    
    private boolean updateAnchorFromChildren(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        if (((RecyclerView.LayoutManager)this).getChildCount() != 0) {
            final View focusedChild = ((RecyclerView.LayoutManager)this).getFocusedChild();
            if (focusedChild != null && anchorInfo.isViewValidAsAnchor(focusedChild, state)) {
                anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild);
                return true;
            }
            if (this.mLastStackFromEnd == this.mStackFromEnd) {
                View view;
                if (anchorInfo.mLayoutFromEnd) {
                    view = this.findReferenceChildClosestToEnd(recycler, state);
                }
                else {
                    view = this.findReferenceChildClosestToStart(recycler, state);
                }
                if (view != null) {
                    anchorInfo.assignFromView(view);
                    if (!state.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                        int n;
                        if (this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(view) < this.mOrientationHelper.getStartAfterPadding()) {
                            n = 1;
                        }
                        else {
                            n = 0;
                        }
                        if (n != 0) {
                            int mCoordinate;
                            if (anchorInfo.mLayoutFromEnd) {
                                mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                            }
                            else {
                                mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            }
                            anchorInfo.mCoordinate = mCoordinate;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean updateAnchorFromPendingData(final State state, final AnchorInfo anchorInfo) {
        boolean mLayoutFromEnd = false;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        anchorInfo.mPosition = this.mPendingScrollPosition;
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
            if (anchorInfo.mLayoutFromEnd) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                return true;
            }
            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
            return true;
        }
        else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
            final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
            if (viewByPosition == null) {
                if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
                    if (this.mPendingScrollPosition < ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0)) == this.mShouldReverseLayout) {
                        mLayoutFromEnd = true;
                    }
                    anchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                }
                anchorInfo.assignCoordinateFromPadding();
                return true;
            }
            if (this.mOrientationHelper.getDecoratedMeasurement(viewByPosition) > this.mOrientationHelper.getTotalSpace()) {
                anchorInfo.assignCoordinateFromPadding();
                return true;
            }
            if (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                anchorInfo.mLayoutFromEnd = false;
                return true;
            }
            if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) < 0) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                return anchorInfo.mLayoutFromEnd = true;
            }
            int decoratedStart;
            if (anchorInfo.mLayoutFromEnd) {
                decoratedStart = this.mOrientationHelper.getDecoratedEnd(viewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
            }
            else {
                decoratedStart = this.mOrientationHelper.getDecoratedStart(viewByPosition);
            }
            anchorInfo.mCoordinate = decoratedStart;
            return true;
        }
        else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
            if (this.mShouldReverseLayout) {
                anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                return true;
            }
            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            return true;
        }
    }
    
    private void updateAnchorInfoForLayout(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
        if (!this.updateAnchorFromPendingData(state, anchorInfo) && !this.updateAnchorFromChildren(recycler, state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            int mPosition;
            if (this.mStackFromEnd) {
                mPosition = state.getItemCount() - 1;
            }
            else {
                mPosition = 0;
            }
            anchorInfo.mPosition = mPosition;
        }
    }
    
    private void updateLayoutState(int n, final int mAvailable, final boolean b, final State state) {
        final int n2 = -1;
        final int n3 = 1;
        this.mLayoutState.mExtra = this.getExtraLayoutSpace(state);
        this.mLayoutState.mLayoutDirection = n;
        if (n == 1) {
            final LayoutState mLayoutState = this.mLayoutState;
            mLayoutState.mExtra += this.mOrientationHelper.getEndPadding();
            final View childClosestToEnd = this.getChildClosestToEnd();
            final LayoutState mLayoutState2 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                n = n2;
            }
            else {
                n = 1;
            }
            mLayoutState2.mItemDirection = n;
            this.mLayoutState.mCurrentPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToEnd) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            n = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        }
        else {
            final View childClosestToStart = this.getChildClosestToStart();
            final LayoutState mLayoutState3 = this.mLayoutState;
            mLayoutState3.mExtra += this.mOrientationHelper.getStartAfterPadding();
            final LayoutState mLayoutState4 = this.mLayoutState;
            if (this.mShouldReverseLayout) {
                n = n3;
            }
            else {
                n = -1;
            }
            mLayoutState4.mItemDirection = n;
            this.mLayoutState.mCurrentPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToStart) + this.mLayoutState.mItemDirection;
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            n = -this.mOrientationHelper.getDecoratedStart(childClosestToStart) + this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mAvailable = mAvailable;
        if (b) {
            final LayoutState mLayoutState5 = this.mLayoutState;
            mLayoutState5.mAvailable -= n;
        }
        this.mLayoutState.mScrollingOffset = n;
    }
    
    private void updateLayoutStateToFillEnd(final int mCurrentPosition, final int mOffset) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - mOffset;
        final LayoutState mLayoutState = this.mLayoutState;
        int mItemDirection;
        if (this.mShouldReverseLayout) {
            mItemDirection = -1;
        }
        else {
            mItemDirection = 1;
        }
        mLayoutState.mItemDirection = mItemDirection;
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillEnd(final AnchorInfo anchorInfo) {
        this.updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }
    
    private void updateLayoutStateToFillStart(int n, final int mOffset) {
        this.mLayoutState.mAvailable = mOffset - this.mOrientationHelper.getStartAfterPadding();
        this.mLayoutState.mCurrentPosition = n;
        final LayoutState mLayoutState = this.mLayoutState;
        if (this.mShouldReverseLayout) {
            n = 1;
        }
        else {
            n = -1;
        }
        mLayoutState.mItemDirection = n;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = mOffset;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    }
    
    private void updateLayoutStateToFillStart(final AnchorInfo anchorInfo) {
        this.updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }
    
    @Override
    public void assertNotInLayoutOrScroll(final String s) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(s);
        }
    }
    
    @Override
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }
    
    @Override
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }
    
    @Override
    public int computeHorizontalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeHorizontalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeHorizontalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    public PointF computeScrollVectorForPosition(int n) {
        boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return null;
        }
        if (n < ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0))) {
            b = true;
        }
        if (b != this.mShouldReverseLayout) {
            n = -1;
        }
        else {
            n = 1;
        }
        if (this.mOrientation == 0) {
            return new PointF((float)n, 0.0f);
        }
        return new PointF(0.0f, (float)n);
    }
    
    @Override
    public int computeVerticalScrollExtent(final State state) {
        return this.computeScrollExtent(state);
    }
    
    @Override
    public int computeVerticalScrollOffset(final State state) {
        return this.computeScrollOffset(state);
    }
    
    @Override
    public int computeVerticalScrollRange(final State state) {
        return this.computeScrollRange(state);
    }
    
    LayoutState createLayoutState() {
        return new LayoutState();
    }
    
    void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = this.createLayoutState();
        }
        if (this.mOrientationHelper == null) {
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        }
    }
    
    int fill(final Recycler recycler, final LayoutState layoutState, final State state, final boolean b) {
        final int mAvailable = layoutState.mAvailable;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset += layoutState.mAvailable;
            }
            this.recycleByLayoutState(recycler, layoutState);
        }
        int n = layoutState.mAvailable + layoutState.mExtra;
        final LayoutChunkResult layoutChunkResult = new LayoutChunkResult();
        while (n > 0 && layoutState.hasMore(state)) {
            layoutChunkResult.resetInternal();
            this.layoutChunk(recycler, state, layoutState, layoutChunkResult);
            if (layoutChunkResult.mFinished) {
                break;
            }
            layoutState.mOffset += layoutChunkResult.mConsumed * layoutState.mLayoutDirection;
            int n2 = 0;
            Label_0176: {
                if (layoutChunkResult.mIgnoreConsumed && this.mLayoutState.mScrapList == null) {
                    n2 = n;
                    if (state.isPreLayout()) {
                        break Label_0176;
                    }
                }
                layoutState.mAvailable -= layoutChunkResult.mConsumed;
                n2 = n - layoutChunkResult.mConsumed;
            }
            if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
                layoutState.mScrollingOffset += layoutChunkResult.mConsumed;
                if (layoutState.mAvailable < 0) {
                    layoutState.mScrollingOffset += layoutState.mAvailable;
                }
                this.recycleByLayoutState(recycler, layoutState);
            }
            n = n2;
            if (!b) {
                continue;
            }
            n = n2;
            if (layoutChunkResult.mFocusable) {
                break;
            }
        }
        return mAvailable - layoutState.mAvailable;
    }
    
    public int findFirstCompletelyVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), true, false);
        if (oneVisibleChild == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
    }
    
    public int findFirstVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(0, ((RecyclerView.LayoutManager)this).getChildCount(), false, true);
        if (oneVisibleChild == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
    }
    
    public int findLastCompletelyVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(((RecyclerView.LayoutManager)this).getChildCount() - 1, -1, true, false);
        if (oneVisibleChild == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
    }
    
    public int findLastVisibleItemPosition() {
        final View oneVisibleChild = this.findOneVisibleChild(((RecyclerView.LayoutManager)this).getChildCount() - 1, -1, false, true);
        if (oneVisibleChild == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(oneVisibleChild);
    }
    
    View findOneVisibleChild(int i, final int n, final boolean b, final boolean b2) {
        this.ensureLayoutState();
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n2;
        if (n > i) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        View view = null;
        while (i != n) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(child);
            final int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(child);
            View view2 = view;
            if (decoratedStart < endAfterPadding) {
                view2 = view;
                if (decoratedEnd > startAfterPadding) {
                    if (!b || (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding)) {
                        return child;
                    }
                    view2 = view;
                    if (b2 && (view2 = view) == null) {
                        view2 = child;
                    }
                }
            }
            i += n2;
            view = view2;
        }
        return view;
    }
    
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
                    if (((LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                        view3 = view;
                        view4 = view2;
                        if (view == null) {
                            view4 = view2;
                            view3 = child;
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
    public View findViewByPosition(final int n) {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        View child;
        if (childCount == 0) {
            child = null;
        }
        else {
            final int n2 = n - ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            if (n2 < 0 || n2 >= childCount || ((RecyclerView.LayoutManager)this).getPosition(child = ((RecyclerView.LayoutManager)this).getChildAt(n2)) != n) {
                return super.findViewByPosition(n);
            }
        }
        return child;
    }
    
    @Override
    public LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }
    
    protected int getExtraLayoutSpace(final State state) {
        if (state.hasTargetScrollPosition()) {
            return this.mOrientationHelper.getTotalSpace();
        }
        return 0;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }
    
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }
    
    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }
    
    protected boolean isLayoutRTL() {
        return ((RecyclerView.LayoutManager)this).getLayoutDirection() == 1;
    }
    
    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }
    
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        final LayoutParams layoutParams = (LayoutParams)next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                ((RecyclerView.LayoutManager)this).addView(next);
            }
            else {
                ((RecyclerView.LayoutManager)this).addView(next, 0);
            }
        }
        else if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
            ((RecyclerView.LayoutManager)this).addDisappearingView(next);
        }
        else {
            ((RecyclerView.LayoutManager)this).addDisappearingView(next, 0);
        }
        ((RecyclerView.LayoutManager)this).measureChildWithMargins(next, 0, 0);
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        int mOffset;
        int n;
        int mOffset2;
        int n2;
        if (this.mOrientation == 1) {
            if (this.isLayoutRTL()) {
                mOffset = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight();
                n = mOffset - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            }
            else {
                n = ((RecyclerView.LayoutManager)this).getPaddingLeft();
                mOffset = n + this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            }
            if (layoutState.mLayoutDirection == -1) {
                mOffset2 = layoutState.mOffset;
                n2 = layoutState.mOffset - layoutChunkResult.mConsumed;
            }
            else {
                n2 = layoutState.mOffset;
                mOffset2 = layoutState.mOffset + layoutChunkResult.mConsumed;
            }
        }
        else {
            n2 = ((RecyclerView.LayoutManager)this).getPaddingTop();
            mOffset2 = n2 + this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            if (layoutState.mLayoutDirection == -1) {
                mOffset = layoutState.mOffset;
                n = layoutState.mOffset - layoutChunkResult.mConsumed;
            }
            else {
                n = layoutState.mOffset;
                mOffset = layoutState.mOffset + layoutChunkResult.mConsumed;
            }
        }
        ((RecyclerView.LayoutManager)this).layoutDecorated(next, n + layoutParams.leftMargin, n2 + layoutParams.topMargin, mOffset - layoutParams.rightMargin, mOffset2 - layoutParams.bottomMargin);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.isFocusable();
    }
    
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo) {
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }
    
    @Override
    public View onFocusSearchFailed(View view, int convertFocusDirectionToLayoutDirection, final Recycler recycler, final State state) {
        this.resolveShouldLayoutReverse();
        View view2;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            view2 = null;
        }
        else {
            convertFocusDirectionToLayoutDirection = this.convertFocusDirectionToLayoutDirection(convertFocusDirectionToLayoutDirection);
            if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
                return null;
            }
            this.ensureLayoutState();
            View view3;
            if (convertFocusDirectionToLayoutDirection == -1) {
                view3 = this.findReferenceChildClosestToStart(recycler, state);
            }
            else {
                view3 = this.findReferenceChildClosestToEnd(recycler, state);
            }
            if (view3 == null) {
                return null;
            }
            this.ensureLayoutState();
            this.updateLayoutState(convertFocusDirectionToLayoutDirection, (int)(0.33f * this.mOrientationHelper.getTotalSpace()), false, state);
            this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
            this.mLayoutState.mRecycle = false;
            this.fill(recycler, this.mLayoutState, state, true);
            if (convertFocusDirectionToLayoutDirection == -1) {
                view = this.getChildClosestToStart();
            }
            else {
                view = this.getChildClosestToEnd();
            }
            if (view != view3) {
                view2 = view;
                if (view.isFocusable()) {
                    return view2;
                }
            }
            return null;
        }
        return view2;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            record.setFromIndex(this.findFirstVisibleItemPosition());
            record.setToIndex(this.findLastVisibleItemPosition());
        }
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
            return;
        }
        if (this.mPendingSavedState != null && this.mPendingSavedState.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        this.ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        this.resolveShouldLayoutReverse();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mLayoutFromEnd = (this.mShouldReverseLayout ^ this.mStackFromEnd);
        this.updateAnchorInfoForLayout(recycler, state, this.mAnchorInfo);
        int extraLayoutSpace = this.getExtraLayoutSpace(state);
        int n;
        if (this.mLayoutState.mLastScrollDelta >= 0) {
            n = 0;
        }
        else {
            n = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        final int n2 = n + this.mOrientationHelper.getStartAfterPadding();
        int mExtra;
        final int n3 = mExtra = extraLayoutSpace + this.mOrientationHelper.getEndPadding();
        int mExtra2 = n2;
        if (state.isPreLayout()) {
            mExtra = n3;
            mExtra2 = n2;
            if (this.mPendingScrollPosition != -1) {
                mExtra = n3;
                mExtra2 = n2;
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    final View viewByPosition = this.findViewByPosition(this.mPendingScrollPosition);
                    mExtra = n3;
                    mExtra2 = n2;
                    if (viewByPosition != null) {
                        int n4;
                        if (this.mShouldReverseLayout) {
                            n4 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewByPosition) - this.mPendingScrollPositionOffset;
                        }
                        else {
                            n4 = this.mPendingScrollPositionOffset - (this.mOrientationHelper.getDecoratedStart(viewByPosition) - this.mOrientationHelper.getStartAfterPadding());
                        }
                        if (n4 > 0) {
                            mExtra2 = n2 + n4;
                            mExtra = n3;
                        }
                        else {
                            mExtra = n3 - n4;
                            mExtra2 = n2;
                        }
                    }
                }
            }
        }
        this.onAnchorReady(recycler, state, this.mAnchorInfo);
        ((RecyclerView.LayoutManager)this).detachAndScrapAttachedViews(recycler);
        this.mLayoutState.mIsPreLayout = state.isPreLayout();
        int n6;
        int mOffset2;
        if (this.mAnchorInfo.mLayoutFromEnd) {
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra2;
            this.fill(recycler, this.mLayoutState, state, false);
            final int mOffset = this.mLayoutState.mOffset;
            final int mCurrentPosition = this.mLayoutState.mCurrentPosition;
            int mExtra3 = mExtra;
            if (this.mLayoutState.mAvailable > 0) {
                mExtra3 = mExtra + this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra3;
            final LayoutState mLayoutState = this.mLayoutState;
            mLayoutState.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state, false);
            final int n5 = n6 = this.mLayoutState.mOffset;
            mOffset2 = mOffset;
            if (this.mLayoutState.mAvailable > 0) {
                final int mAvailable = this.mLayoutState.mAvailable;
                this.updateLayoutStateToFillStart(mCurrentPosition, mOffset);
                this.mLayoutState.mExtra = mAvailable;
                this.fill(recycler, this.mLayoutState, state, false);
                mOffset2 = this.mLayoutState.mOffset;
                n6 = n5;
            }
        }
        else {
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra;
            this.fill(recycler, this.mLayoutState, state, false);
            final int mOffset3 = this.mLayoutState.mOffset;
            final int mCurrentPosition2 = this.mLayoutState.mCurrentPosition;
            int mExtra4 = mExtra2;
            if (this.mLayoutState.mAvailable > 0) {
                mExtra4 = mExtra2 + this.mLayoutState.mAvailable;
            }
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtra = mExtra4;
            final LayoutState mLayoutState2 = this.mLayoutState;
            mLayoutState2.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state, false);
            final int mOffset4 = this.mLayoutState.mOffset;
            n6 = mOffset3;
            mOffset2 = mOffset4;
            if (this.mLayoutState.mAvailable > 0) {
                final int mAvailable2 = this.mLayoutState.mAvailable;
                this.updateLayoutStateToFillEnd(mCurrentPosition2, mOffset3);
                this.mLayoutState.mExtra = mAvailable2;
                this.fill(recycler, this.mLayoutState, state, false);
                n6 = this.mLayoutState.mOffset;
                mOffset2 = mOffset4;
            }
        }
        int n7 = n6;
        int n8 = mOffset2;
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                final int fixLayoutEndGap = this.fixLayoutEndGap(n6, recycler, state, true);
                final int n9 = mOffset2 + fixLayoutEndGap;
                final int fixLayoutStartGap = this.fixLayoutStartGap(n9, recycler, state, false);
                n8 = n9 + fixLayoutStartGap;
                n7 = n6 + fixLayoutEndGap + fixLayoutStartGap;
            }
            else {
                final int fixLayoutStartGap2 = this.fixLayoutStartGap(mOffset2, recycler, state, true);
                final int n10 = n6 + fixLayoutStartGap2;
                final int fixLayoutEndGap2 = this.fixLayoutEndGap(n10, recycler, state, false);
                n8 = mOffset2 + fixLayoutStartGap2 + fixLayoutEndGap2;
                n7 = n10 + fixLayoutEndGap2;
            }
        }
        this.layoutForPredictiveAnimations(recycler, state, n8, n7);
        if (!state.isPreLayout()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            this.mOrientationHelper.onLayoutComplete();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
        this.mPendingSavedState = null;
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState)parcelable;
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return (Parcelable)new SavedState(this.mPendingSavedState);
        }
        final SavedState savedState = new SavedState();
        if (((RecyclerView.LayoutManager)this).getChildCount() <= 0) {
            savedState.invalidateAnchor();
            return (Parcelable)savedState;
        }
        this.ensureLayoutState();
        final boolean mAnchorLayoutFromEnd = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
        savedState.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
        if (mAnchorLayoutFromEnd) {
            final View childClosestToEnd = this.getChildClosestToEnd();
            savedState.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            savedState.mAnchorPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToEnd);
            return (Parcelable)savedState;
        }
        final View childClosestToStart = this.getChildClosestToStart();
        savedState.mAnchorPosition = ((RecyclerView.LayoutManager)this).getPosition(childClosestToStart);
        savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        return (Parcelable)savedState;
    }
    
    @Override
    public void prepareForDrop(final View view, final View view2, int position, int position2) {
        this.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        this.ensureLayoutState();
        this.resolveShouldLayoutReverse();
        position = ((RecyclerView.LayoutManager)this).getPosition(view);
        position2 = ((RecyclerView.LayoutManager)this).getPosition(view2);
        if (position < position2) {
            position = 1;
        }
        else {
            position = -1;
        }
        if (this.mShouldReverseLayout) {
            if (position == 1) {
                this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(view2) + this.mOrientationHelper.getDecoratedMeasurement(view)));
                return;
            }
            this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(view2));
        }
        else {
            if (position == -1) {
                this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedStart(view2));
                return;
            }
            this.scrollToPositionWithOffset(position2, this.mOrientationHelper.getDecoratedEnd(view2) - this.mOrientationHelper.getDecoratedMeasurement(view));
        }
    }
    
    int scrollBy(int mLastScrollDelta, final Recycler recycler, final State state) {
        if (((RecyclerView.LayoutManager)this).getChildCount() != 0 && mLastScrollDelta != 0) {
            this.mLayoutState.mRecycle = true;
            this.ensureLayoutState();
            int n;
            if (mLastScrollDelta > 0) {
                n = 1;
            }
            else {
                n = -1;
            }
            final int abs = Math.abs(mLastScrollDelta);
            this.updateLayoutState(n, abs, true, state);
            final int n2 = this.mLayoutState.mScrollingOffset + this.fill(recycler, this.mLayoutState, state, false);
            if (n2 >= 0) {
                if (abs > n2) {
                    mLastScrollDelta = n * n2;
                }
                this.mOrientationHelper.offsetChildren(-mLastScrollDelta);
                return this.mLayoutState.mLastScrollDelta = mLastScrollDelta;
            }
        }
        return 0;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return this.scrollBy(n, recycler, state);
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void scrollToPositionWithOffset(final int mPendingScrollPosition, final int mPendingScrollPositionOffset) {
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = mPendingScrollPositionOffset;
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchor();
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return this.scrollBy(n, recycler, state);
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation:" + mOrientation);
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation == this.mOrientation) {
            return;
        }
        this.mOrientation = mOrientation;
        this.mOrientationHelper = null;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setRecycleChildrenOnDetach(final boolean mRecycleChildrenOnDetach) {
        this.mRecycleChildrenOnDetach = mRecycleChildrenOnDetach;
    }
    
    public void setReverseLayout(final boolean mReverseLayout) {
        this.assertNotInLayoutOrScroll(null);
        if (mReverseLayout == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = mReverseLayout;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setSmoothScrollbarEnabled(final boolean mSmoothScrollbarEnabled) {
        this.mSmoothScrollbarEnabled = mSmoothScrollbarEnabled;
    }
    
    public void setStackFromEnd(final boolean mStackFromEnd) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == mStackFromEnd) {
            return;
        }
        this.mStackFromEnd = mStackFromEnd;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int targetPosition) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(final int n) {
                return LinearLayoutManager.this.computeScrollVectorForPosition(n);
            }
        };
        ((RecyclerView.SmoothScroller)linearSmoothScroller).setTargetPosition(targetPosition);
        ((RecyclerView.LayoutManager)this).startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }
    
    void validateChildOrder() {
        final boolean b = true;
        boolean b2 = true;
        Log.d("LinearLayoutManager", "validating child count " + ((RecyclerView.LayoutManager)this).getChildCount());
        if (((RecyclerView.LayoutManager)this).getChildCount() >= 1) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
            final int decoratedStart = this.mOrientationHelper.getDecoratedStart(((RecyclerView.LayoutManager)this).getChildAt(0));
            if (this.mShouldReverseLayout) {
                for (int i = 1; i < ((RecyclerView.LayoutManager)this).getChildCount(); ++i) {
                    final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                    final int position2 = ((RecyclerView.LayoutManager)this).getPosition(child);
                    final int decoratedStart2 = this.mOrientationHelper.getDecoratedStart(child);
                    if (position2 < position) {
                        this.logChildren();
                        final StringBuilder append = new StringBuilder().append("detected invalid position. loc invalid? ");
                        if (decoratedStart2 >= decoratedStart) {
                            b2 = false;
                        }
                        throw new RuntimeException(append.append(b2).toString());
                    }
                    if (decoratedStart2 > decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
            else {
                for (int j = 1; j < ((RecyclerView.LayoutManager)this).getChildCount(); ++j) {
                    final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(j);
                    final int position3 = ((RecyclerView.LayoutManager)this).getPosition(child2);
                    final int decoratedStart3 = this.mOrientationHelper.getDecoratedStart(child2);
                    if (position3 < position) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid position. loc invalid? " + (decoratedStart3 < decoratedStart && b));
                    }
                    if (decoratedStart3 < decoratedStart) {
                        this.logChildren();
                        throw new RuntimeException("detected invalid location");
                    }
                }
            }
        }
    }
    
    class AnchorInfo
    {
        int mCoordinate;
        boolean mLayoutFromEnd;
        int mPosition;
        
        private boolean isViewValidAsAnchor(final View view, final State state) {
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }
        
        void assignCoordinateFromPadding() {
            int mCoordinate;
            if (this.mLayoutFromEnd) {
                mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding();
            }
            else {
                mCoordinate = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = mCoordinate;
        }
        
        public void assignFromView(final View view) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            }
            else {
                this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
        }
        
        public void assignFromViewAndKeepVisibleRect(final View view) {
            final int totalSpaceChange = LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                this.assignFromView(view);
            }
            else {
                this.mPosition = ((RecyclerView.LayoutManager)LinearLayoutManager.this).getPosition(view);
                if (this.mLayoutFromEnd) {
                    final int n = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
                    this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - n;
                    if (n > 0) {
                        final int decoratedMeasurement = LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view);
                        final int mCoordinate = this.mCoordinate;
                        final int startAfterPadding = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                        final int n2 = mCoordinate - decoratedMeasurement - (startAfterPadding + Math.min(LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0));
                        if (n2 < 0) {
                            this.mCoordinate += Math.min(n, -n2);
                        }
                    }
                }
                else {
                    final int decoratedStart = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
                    final int n3 = decoratedStart - LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
                    this.mCoordinate = decoratedStart;
                    if (n3 > 0) {
                        final int n4 = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - Math.min(0, LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(view)) - (decoratedStart + LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(view));
                        if (n4 < 0) {
                            this.mCoordinate -= Math.min(n3, -n4);
                        }
                    }
                }
            }
        }
        
        void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
        }
        
        @Override
        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + '}';
        }
    }
    
    protected static class LayoutChunkResult
    {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;
        
        void resetInternal() {
            this.mConsumed = 0;
            this.mFinished = false;
            this.mIgnoreConsumed = false;
            this.mFocusable = false;
        }
    }
    
    static class LayoutState
    {
        static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        static final int ITEM_DIRECTION_HEAD = -1;
        static final int ITEM_DIRECTION_TAIL = 1;
        static final int LAYOUT_END = 1;
        static final int LAYOUT_START = -1;
        static final int SCOLLING_OFFSET_NaN = Integer.MIN_VALUE;
        static final String TAG = "LinearLayoutManager#LayoutState";
        int mAvailable;
        int mCurrentPosition;
        int mExtra;
        boolean mIsPreLayout;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        boolean mRecycle;
        List<ViewHolder> mScrapList;
        int mScrollingOffset;
        
        LayoutState() {
            this.mRecycle = true;
            this.mExtra = 0;
            this.mIsPreLayout = false;
            this.mScrapList = null;
        }
        
        private View nextViewFromScrapList() {
            for (int size = this.mScrapList.size(), i = 0; i < size; ++i) {
                final View itemView = this.mScrapList.get(i).itemView;
                final LayoutParams layoutParams = (LayoutParams)itemView.getLayoutParams();
                if (!layoutParams.isItemRemoved() && this.mCurrentPosition == layoutParams.getViewLayoutPosition()) {
                    this.assignPositionFromScrapList(itemView);
                    return itemView;
                }
            }
            return null;
        }
        
        public void assignPositionFromScrapList() {
            this.assignPositionFromScrapList(null);
        }
        
        public void assignPositionFromScrapList(View nextViewInLimitedList) {
            nextViewInLimitedList = this.nextViewInLimitedList(nextViewInLimitedList);
            if (nextViewInLimitedList == null) {
                this.mCurrentPosition = -1;
                return;
            }
            this.mCurrentPosition = ((LayoutParams)nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
        }
        
        boolean hasMore(final State state) {
            return this.mCurrentPosition >= 0 && this.mCurrentPosition < state.getItemCount();
        }
        
        void log() {
            Log.d("LinearLayoutManager#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
        }
        
        View next(final Recycler recycler) {
            if (this.mScrapList != null) {
                return this.nextViewFromScrapList();
            }
            final View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return viewForPosition;
        }
        
        public View nextViewInLimitedList(final View view) {
            final int size = this.mScrapList.size();
            View view2 = null;
            int n = Integer.MAX_VALUE;
            int n2 = 0;
            View view3;
            while (true) {
                view3 = view2;
                if (n2 >= size) {
                    break;
                }
                final View itemView = this.mScrapList.get(n2).itemView;
                final LayoutParams layoutParams = (LayoutParams)itemView.getLayoutParams();
                View view4 = view2;
                int n3 = n;
                if (itemView != view) {
                    if (layoutParams.isItemRemoved()) {
                        n3 = n;
                        view4 = view2;
                    }
                    else {
                        final int n4 = (layoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
                        view4 = view2;
                        n3 = n;
                        if (n4 >= 0) {
                            view4 = view2;
                            if (n4 < (n3 = n)) {
                                final View view5 = itemView;
                                n3 = n4;
                                view4 = view5;
                                if (n4 == 0) {
                                    view3 = view5;
                                    break;
                                }
                            }
                        }
                    }
                }
                ++n2;
                view2 = view4;
                n = n3;
            }
            return view3;
        }
    }
    
    public static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        public SavedState() {
        }
        
        SavedState(final Parcel parcel) {
            boolean mAnchorLayoutFromEnd = true;
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            if (parcel.readInt() != 1) {
                mAnchorLayoutFromEnd = false;
            }
            this.mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
        }
        
        public SavedState(final SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }
        
        public int describeContents() {
            return 0;
        }
        
        boolean hasValidAnchor() {
            return this.mAnchorPosition >= 0;
        }
        
        void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            if (this.mAnchorLayoutFromEnd) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
        }
    }
}
