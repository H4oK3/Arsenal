// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import android.graphics.PointF;
import android.os.Parcelable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import java.util.BitSet;

public class StaggeredGridLayoutManager extends LayoutManager
{
    private static final boolean DEBUG = false;
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final String TAG = "StaggeredGridLayoutManager";
    public static final int VERTICAL = 1;
    private final AnchorInfo mAnchorInfo;
    private final Runnable mCheckForGapsRunnable;
    private int mFullSizeSpec;
    private int mGapStrategy;
    private int mHeightSpec;
    private boolean mLaidOutInvalidFullSpan;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private LayoutState mLayoutState;
    LazySpanLookup mLazySpanLookup;
    private int mOrientation;
    private SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    private boolean mReverseLayout;
    OrientationHelper mSecondaryOrientation;
    boolean mShouldReverseLayout;
    private int mSizePerSpan;
    private boolean mSmoothScrollbarEnabled;
    private int mSpanCount;
    private Span[] mSpans;
    private final Rect mTmpRect;
    private int mWidthSpec;
    
    public StaggeredGridLayoutManager(final int spanCount, final int mOrientation) {
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo();
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = new Runnable() {
            @Override
            public void run() {
                StaggeredGridLayoutManager.this.checkForGaps();
            }
        };
        this.mOrientation = mOrientation;
        this.setSpanCount(spanCount);
    }
    
    public StaggeredGridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLazySpanLookup = new LazySpanLookup();
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo();
        this.mLaidOutInvalidFullSpan = false;
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = new Runnable() {
            @Override
            public void run() {
                StaggeredGridLayoutManager.this.checkForGaps();
            }
        };
        final Properties properties = RecyclerView.LayoutManager.getProperties(context, set, n, n2);
        this.setOrientation(properties.orientation);
        this.setSpanCount(properties.spanCount);
        this.setReverseLayout(properties.reverseLayout);
    }
    
    private void appendViewToAllSpans(final View view) {
        for (int i = this.mSpanCount - 1; i >= 0; --i) {
            this.mSpans[i].appendToSpan(view);
        }
    }
    
    private void applyPendingSavedState(final AnchorInfo anchorInfo) {
        if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
            if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    this.mSpans[i].clear();
                    final int n = this.mPendingSavedState.mSpanOffsets[i];
                    int line;
                    if ((line = n) != Integer.MIN_VALUE) {
                        if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                            line = n + this.mPrimaryOrientation.getEndAfterPadding();
                        }
                        else {
                            line = n + this.mPrimaryOrientation.getStartAfterPadding();
                        }
                    }
                    this.mSpans[i].setLine(line);
                }
            }
            else {
                this.mPendingSavedState.invalidateSpanInfo();
                this.mPendingSavedState.mAnchorPosition = this.mPendingSavedState.mVisibleAnchorPosition;
            }
        }
        this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
        this.setReverseLayout(this.mPendingSavedState.mReverseLayout);
        this.resolveShouldLayoutReverse();
        if (this.mPendingSavedState.mAnchorPosition != -1) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            anchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
        }
        else {
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        if (this.mPendingSavedState.mSpanLookupSize > 1) {
            this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
            this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
        }
    }
    
    private void attachViewToSpans(final View view, final LayoutParams layoutParams, final LayoutState layoutState) {
        if (layoutState.mLayoutDirection == 1) {
            if (layoutParams.mFullSpan) {
                this.appendViewToAllSpans(view);
                return;
            }
            layoutParams.mSpan.appendToSpan(view);
        }
        else {
            if (layoutParams.mFullSpan) {
                this.prependViewToAllSpans(view);
                return;
            }
            layoutParams.mSpan.prependToSpan(view);
        }
    }
    
    private int calculateScrollDirectionForPosition(int n) {
        final int n2 = -1;
        if (((RecyclerView.LayoutManager)this).getChildCount() != 0) {
            if (n < this.getFirstChildPosition() != this.mShouldReverseLayout) {
                n = n2;
            }
            else {
                n = 1;
            }
            return n;
        }
        if (this.mShouldReverseLayout) {
            return 1;
        }
        return -1;
    }
    
    private boolean checkForGaps() {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0 || this.mGapStrategy == 0 || !((RecyclerView.LayoutManager)this).isAttachedToWindow()) {
            return false;
        }
        int n;
        int n2;
        if (this.mShouldReverseLayout) {
            n = this.getLastChildPosition();
            n2 = this.getFirstChildPosition();
        }
        else {
            n = this.getFirstChildPosition();
            n2 = this.getLastChildPosition();
        }
        if (n == 0 && this.hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            ((RecyclerView.LayoutManager)this).requestSimpleAnimationsInNextLayout();
            ((RecyclerView.LayoutManager)this).requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int n3;
        if (this.mShouldReverseLayout) {
            n3 = -1;
        }
        else {
            n3 = 1;
        }
        final FullSpanItem firstFullSpanItemInRange = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, n2 + 1, n3, true);
        if (firstFullSpanItemInRange == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.forceInvalidateAfter(n2 + 1);
            return false;
        }
        final FullSpanItem firstFullSpanItemInRange2 = this.mLazySpanLookup.getFirstFullSpanItemInRange(n, firstFullSpanItemInRange.mPosition, n3 * -1, true);
        if (firstFullSpanItemInRange2 == null) {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange.mPosition);
        }
        else {
            this.mLazySpanLookup.forceInvalidateAfter(firstFullSpanItemInRange2.mPosition + 1);
        }
        ((RecyclerView.LayoutManager)this).requestSimpleAnimationsInNextLayout();
        ((RecyclerView.LayoutManager)this).requestLayout();
        return true;
    }
    
    private boolean checkSpanForGap(final Span span) {
        if (this.mShouldReverseLayout) {
            if (span.getEndLine() >= this.mPrimaryOrientation.getEndAfterPadding()) {
                return false;
            }
        }
        else if (span.getStartLine() <= this.mPrimaryOrientation.getStartAfterPadding()) {
            return false;
        }
        return true;
    }
    
    private int computeScrollExtent(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollExtent(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private int computeScrollOffset(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollOffset(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
    
    private int computeScrollRange(final State state) {
        final boolean b = false;
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        this.ensureOrientationHelper();
        final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
        final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled, true);
        boolean b2 = b;
        if (!this.mSmoothScrollbarEnabled) {
            b2 = true;
        }
        return ScrollbarHelper.computeScrollRange(state, mPrimaryOrientation, firstVisibleItemClosestToStart, this.findFirstVisibleItemClosestToEnd(b2, true), this, this.mSmoothScrollbarEnabled);
    }
    
    private FullSpanItem createFullSpanItemFromEnd(final int n) {
        final FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            fullSpanItem.mGapPerSpan[i] = n - this.mSpans[i].getEndLine(n);
        }
        return fullSpanItem;
    }
    
    private FullSpanItem createFullSpanItemFromStart(final int n) {
        final FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.mGapPerSpan = new int[this.mSpanCount];
        for (int i = 0; i < this.mSpanCount; ++i) {
            fullSpanItem.mGapPerSpan[i] = this.mSpans[i].getStartLine(n) - n;
        }
        return fullSpanItem;
    }
    
    private void ensureOrientationHelper() {
        if (this.mPrimaryOrientation == null) {
            this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
            this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
            this.mLayoutState = new LayoutState();
        }
    }
    
    private int fill(final Recycler recycler, final LayoutState layoutState, final State state) {
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        int n;
        if (layoutState.mLayoutDirection == 1) {
            n = layoutState.mEndLine + layoutState.mAvailable;
        }
        else {
            n = layoutState.mStartLine - layoutState.mAvailable;
        }
        this.updateAllRemainingSpans(layoutState.mLayoutDirection, n);
        int n2;
        if (this.mShouldReverseLayout) {
            n2 = this.mPrimaryOrientation.getEndAfterPadding();
        }
        else {
            n2 = this.mPrimaryOrientation.getStartAfterPadding();
        }
        boolean b = false;
        while (layoutState.hasMore(state) && !this.mRemainingSpans.isEmpty()) {
            final View next = layoutState.next(recycler);
            final LayoutParams layoutParams = (LayoutParams)next.getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            final int span = this.mLazySpanLookup.getSpan(viewLayoutPosition);
            boolean b2;
            if (span == -1) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            Span nextSpan;
            if (b2) {
                if (layoutParams.mFullSpan) {
                    nextSpan = this.mSpans[0];
                }
                else {
                    nextSpan = this.getNextSpan(layoutState);
                }
                this.mLazySpanLookup.setSpan(viewLayoutPosition, nextSpan);
            }
            else {
                nextSpan = this.mSpans[span];
            }
            layoutParams.mSpan = nextSpan;
            if (layoutState.mLayoutDirection == 1) {
                ((RecyclerView.LayoutManager)this).addView(next);
            }
            else {
                ((RecyclerView.LayoutManager)this).addView(next, 0);
            }
            this.measureChildWithDecorationsAndMargin(next, layoutParams);
            int n5;
            int n6;
            if (layoutState.mLayoutDirection == 1) {
                int n3;
                if (layoutParams.mFullSpan) {
                    n3 = this.getMaxEnd(n2);
                }
                else {
                    n3 = nextSpan.getEndLine(n2);
                }
                final int n4 = n3 + this.mPrimaryOrientation.getDecoratedMeasurement(next);
                n5 = n3;
                n6 = n4;
                if (b2) {
                    n5 = n3;
                    n6 = n4;
                    if (layoutParams.mFullSpan) {
                        final FullSpanItem fullSpanItemFromEnd = this.createFullSpanItemFromEnd(n3);
                        fullSpanItemFromEnd.mGapDir = -1;
                        fullSpanItemFromEnd.mPosition = viewLayoutPosition;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromEnd);
                        n6 = n4;
                        n5 = n3;
                    }
                }
            }
            else {
                int n7;
                if (layoutParams.mFullSpan) {
                    n7 = this.getMinStart(n2);
                }
                else {
                    n7 = nextSpan.getStartLine(n2);
                }
                final int n8 = n5 = n7 - this.mPrimaryOrientation.getDecoratedMeasurement(next);
                n6 = n7;
                if (b2) {
                    n5 = n8;
                    n6 = n7;
                    if (layoutParams.mFullSpan) {
                        final FullSpanItem fullSpanItemFromStart = this.createFullSpanItemFromStart(n7);
                        fullSpanItemFromStart.mGapDir = 1;
                        fullSpanItemFromStart.mPosition = viewLayoutPosition;
                        this.mLazySpanLookup.addFullSpanItem(fullSpanItemFromStart);
                        n5 = n8;
                        n6 = n7;
                    }
                }
            }
            if (layoutParams.mFullSpan && layoutState.mItemDirection == -1) {
                if (b2) {
                    this.mLaidOutInvalidFullSpan = true;
                }
                else {
                    int n9;
                    if (layoutState.mLayoutDirection == 1) {
                        if (!this.areAllEndsEqual()) {
                            n9 = 1;
                        }
                        else {
                            n9 = 0;
                        }
                    }
                    else if (!this.areAllStartsEqual()) {
                        n9 = 1;
                    }
                    else {
                        n9 = 0;
                    }
                    if (n9 != 0) {
                        final FullSpanItem fullSpanItem = this.mLazySpanLookup.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.mHasUnwantedGapAfter = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            this.attachViewToSpans(next, layoutParams, layoutState);
            int startAfterPadding;
            if (layoutParams.mFullSpan) {
                startAfterPadding = this.mSecondaryOrientation.getStartAfterPadding();
            }
            else {
                startAfterPadding = nextSpan.mIndex * this.mSizePerSpan + this.mSecondaryOrientation.getStartAfterPadding();
            }
            final int n10 = startAfterPadding + this.mSecondaryOrientation.getDecoratedMeasurement(next);
            if (this.mOrientation == 1) {
                this.layoutDecoratedWithMargins(next, startAfterPadding, n5, n10, n6);
            }
            else {
                this.layoutDecoratedWithMargins(next, n5, startAfterPadding, n6, n10);
            }
            if (layoutParams.mFullSpan) {
                this.updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, n);
            }
            else {
                this.updateRemainingSpans(nextSpan, this.mLayoutState.mLayoutDirection, n);
            }
            this.recycle(recycler, this.mLayoutState);
            b = true;
        }
        if (!b) {
            this.recycle(recycler, this.mLayoutState);
        }
        int n11;
        if (this.mLayoutState.mLayoutDirection == -1) {
            n11 = this.mPrimaryOrientation.getStartAfterPadding() - this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
        }
        else {
            n11 = this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        }
        if (n11 > 0) {
            return Math.min(layoutState.mAvailable, n11);
        }
        return 0;
    }
    
    private int findFirstReferenceChildPosition(final int n) {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private int findLastReferenceChildPosition(final int n) {
        for (int i = ((RecyclerView.LayoutManager)this).getChildCount() - 1; i >= 0; --i) {
            final int position = ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(i));
            if (position >= 0 && position < n) {
                return position;
            }
        }
        return 0;
    }
    
    private void fixEndGap(final Recycler recycler, final State state, final boolean b) {
        final int n = this.mPrimaryOrientation.getEndAfterPadding() - this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding());
        if (n > 0) {
            final int n2 = n - -this.scrollBy(-n, recycler, state);
            if (b && n2 > 0) {
                this.mPrimaryOrientation.offsetChildren(n2);
            }
        }
    }
    
    private void fixStartGap(final Recycler recycler, final State state, final boolean b) {
        final int n = this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding()) - this.mPrimaryOrientation.getStartAfterPadding();
        if (n > 0) {
            final int n2 = n - this.scrollBy(n, recycler, state);
            if (b && n2 > 0) {
                this.mPrimaryOrientation.offsetChildren(-n2);
            }
        }
    }
    
    private int getFirstChildPosition() {
        if (((RecyclerView.LayoutManager)this).getChildCount() == 0) {
            return 0;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(0));
    }
    
    private int getLastChildPosition() {
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(((RecyclerView.LayoutManager)this).getChildAt(childCount - 1));
    }
    
    private int getMaxEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, endLine = n2) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 > (n2 = endLine)) {
                n2 = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMaxStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, startLine = n2) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 > (n2 = startLine)) {
                n2 = startLine2;
            }
        }
        return startLine;
    }
    
    private int getMinEnd(final int n) {
        int endLine = this.mSpans[0].getEndLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, endLine = n2) {
            final int endLine2 = this.mSpans[i].getEndLine(n);
            if (endLine2 < (n2 = endLine)) {
                n2 = endLine2;
            }
        }
        return endLine;
    }
    
    private int getMinStart(final int n) {
        int startLine = this.mSpans[0].getStartLine(n);
        int n2;
        for (int i = 1; i < this.mSpanCount; ++i, startLine = n2) {
            final int startLine2 = this.mSpans[i].getStartLine(n);
            if (startLine2 < (n2 = startLine)) {
                n2 = startLine2;
            }
        }
        return startLine;
    }
    
    private Span getNextSpan(final LayoutState layoutState) {
        int i;
        int mSpanCount;
        int n;
        if (this.preferLastSpan(layoutState.mLayoutDirection)) {
            i = this.mSpanCount - 1;
            mSpanCount = -1;
            n = -1;
        }
        else {
            i = 0;
            mSpanCount = this.mSpanCount;
            n = 1;
        }
        Span span2;
        if (layoutState.mLayoutDirection == 1) {
            Span span = null;
            int n2 = Integer.MAX_VALUE;
            final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            while (true) {
                span2 = span;
                if (i == mSpanCount) {
                    break;
                }
                final Span span3 = this.mSpans[i];
                final int endLine = span3.getEndLine(startAfterPadding);
                int n3;
                if (endLine < (n3 = n2)) {
                    span = span3;
                    n3 = endLine;
                }
                i += n;
                n2 = n3;
            }
        }
        else {
            Span span4 = null;
            int n4 = Integer.MIN_VALUE;
            final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
            while (i != mSpanCount) {
                final Span span5 = this.mSpans[i];
                final int startLine = span5.getStartLine(endAfterPadding);
                int n5;
                if (startLine > (n5 = n4)) {
                    span4 = span5;
                    n5 = startLine;
                }
                i += n;
                n4 = n5;
            }
            span2 = span4;
        }
        return span2;
    }
    
    private int getSpecForDimension(final int n, final int n2) {
        if (n < 0) {
            return n2;
        }
        return View$MeasureSpec.makeMeasureSpec(n, 1073741824);
    }
    
    private void handleUpdate(int n, final int n2, final int n3) {
        int n4;
        if (this.mShouldReverseLayout) {
            n4 = this.getLastChildPosition();
        }
        else {
            n4 = this.getFirstChildPosition();
        }
        int n5;
        int n6;
        if (n3 == 8) {
            if (n < n2) {
                n5 = n2 + 1;
                n6 = n;
            }
            else {
                n5 = n + 1;
                n6 = n2;
            }
        }
        else {
            n6 = n;
            n5 = n + n2;
        }
        this.mLazySpanLookup.invalidateAfter(n6);
        switch (n3) {
            case 1: {
                this.mLazySpanLookup.offsetForAddition(n, n2);
                break;
            }
            case 2: {
                this.mLazySpanLookup.offsetForRemoval(n, n2);
                break;
            }
            case 8: {
                this.mLazySpanLookup.offsetForRemoval(n, 1);
                this.mLazySpanLookup.offsetForAddition(n2, 1);
                break;
            }
        }
        if (n5 > n4) {
            if (this.mShouldReverseLayout) {
                n = this.getFirstChildPosition();
            }
            else {
                n = this.getLastChildPosition();
            }
            if (n6 <= n) {
                ((RecyclerView.LayoutManager)this).requestLayout();
            }
        }
    }
    
    private void layoutDecoratedWithMargins(final View view, final int n, final int n2, final int n3, final int n4) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        ((RecyclerView.LayoutManager)this).layoutDecorated(view, n + layoutParams.leftMargin, n2 + layoutParams.topMargin, n3 - layoutParams.rightMargin, n4 - layoutParams.bottomMargin);
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final int n, final int n2) {
        ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mTmpRect);
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        view.measure(this.updateSpecWithExtra(n, layoutParams.leftMargin + this.mTmpRect.left, layoutParams.rightMargin + this.mTmpRect.right), this.updateSpecWithExtra(n2, layoutParams.topMargin + this.mTmpRect.top, layoutParams.bottomMargin + this.mTmpRect.bottom));
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final LayoutParams layoutParams) {
        if (layoutParams.mFullSpan) {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, this.getSpecForDimension(layoutParams.height, this.mHeightSpec));
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, this.getSpecForDimension(layoutParams.width, this.mWidthSpec), this.mFullSizeSpec);
        }
        else {
            if (this.mOrientation == 1) {
                this.measureChildWithDecorationsAndMargin(view, this.mWidthSpec, this.getSpecForDimension(layoutParams.height, this.mHeightSpec));
                return;
            }
            this.measureChildWithDecorationsAndMargin(view, this.getSpecForDimension(layoutParams.width, this.mWidthSpec), this.mHeightSpec);
        }
    }
    
    private boolean preferLastSpan(final int n) {
        if (this.mOrientation == 0) {
            if (n == -1 == this.mShouldReverseLayout) {
                return false;
            }
        }
        else if (n == -1 == this.mShouldReverseLayout != this.isLayoutRTL()) {
            return false;
        }
        return true;
    }
    
    private void prependViewToAllSpans(final View view) {
        for (int i = this.mSpanCount - 1; i >= 0; --i) {
            this.mSpans[i].prependToSpan(view);
        }
    }
    
    private void recycle(final Recycler recycler, final LayoutState layoutState) {
        if (layoutState.mAvailable == 0) {
            if (layoutState.mLayoutDirection == -1) {
                this.recycleFromEnd(recycler, layoutState.mEndLine);
                return;
            }
            this.recycleFromStart(recycler, layoutState.mStartLine);
        }
        else {
            if (layoutState.mLayoutDirection == -1) {
                final int n = layoutState.mStartLine - this.getMaxStart(layoutState.mStartLine);
                int mEndLine;
                if (n < 0) {
                    mEndLine = layoutState.mEndLine;
                }
                else {
                    mEndLine = layoutState.mEndLine - Math.min(n, layoutState.mAvailable);
                }
                this.recycleFromEnd(recycler, mEndLine);
                return;
            }
            final int n2 = this.getMinEnd(layoutState.mEndLine) - layoutState.mEndLine;
            int mStartLine;
            if (n2 < 0) {
                mStartLine = layoutState.mStartLine;
            }
            else {
                mStartLine = layoutState.mStartLine + Math.min(n2, layoutState.mAvailable);
            }
            this.recycleFromStart(recycler, mStartLine);
        }
    }
    
    private void recycleFromEnd(final Recycler recycler, final int n) {
    Label_0078:
        for (int i = ((RecyclerView.LayoutManager)this).getChildCount() - 1; i >= 0; --i) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            if (this.mPrimaryOrientation.getDecoratedStart(child) < n) {
                break;
            }
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int j = 0; j < this.mSpanCount; ++j) {
                    if (this.mSpans[j].mViews.size() == 1) {
                        break Label_0078;
                    }
                }
                for (int k = 0; k < this.mSpanCount; ++k) {
                    this.mSpans[k].popEnd();
                }
            }
            else {
                if (layoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                layoutParams.mSpan.popEnd();
            }
            ((RecyclerView.LayoutManager)this).removeAndRecycleView(child, recycler);
        }
    }
    
    private void recycleFromStart(final Recycler recycler, final int n) {
    Label_0071:
        while (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > n) {
                break;
            }
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (layoutParams.mFullSpan) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    if (this.mSpans[i].mViews.size() == 1) {
                        break Label_0071;
                    }
                }
                for (int j = 0; j < this.mSpanCount; ++j) {
                    this.mSpans[j].popStart();
                }
            }
            else {
                if (layoutParams.mSpan.mViews.size() == 1) {
                    break;
                }
                layoutParams.mSpan.popStart();
            }
            ((RecyclerView.LayoutManager)this).removeAndRecycleView(child, recycler);
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
    
    private void setLayoutStateDirection(int n) {
        final int n2 = 1;
        this.mLayoutState.mLayoutDirection = n;
        final LayoutState mLayoutState = this.mLayoutState;
        if (this.mShouldReverseLayout == (n == -1)) {
            n = n2;
        }
        else {
            n = -1;
        }
        mLayoutState.mItemDirection = n;
    }
    
    private void updateAllRemainingSpans(final int n, final int n2) {
        for (int i = 0; i < this.mSpanCount; ++i) {
            if (!this.mSpans[i].mViews.isEmpty()) {
                this.updateRemainingSpans(this.mSpans[i], n, n2);
            }
        }
    }
    
    private boolean updateAnchorFromChildren(final State state, final AnchorInfo anchorInfo) {
        int mPosition;
        if (this.mLastLayoutFromEnd) {
            mPosition = this.findLastReferenceChildPosition(state.getItemCount());
        }
        else {
            mPosition = this.findFirstReferenceChildPosition(state.getItemCount());
        }
        anchorInfo.mPosition = mPosition;
        anchorInfo.mOffset = Integer.MIN_VALUE;
        return true;
    }
    
    private void updateLayoutState(final int mCurrentPosition, final State state) {
        int n = false ? 1 : 0;
        this.mLayoutState.mAvailable = 0;
        this.mLayoutState.mCurrentPosition = mCurrentPosition;
        final int n2 = 0;
        int totalSpace;
        final int n3 = totalSpace = 0;
        int totalSpace2 = n2;
        if (((RecyclerView.LayoutManager)this).isSmoothScrolling()) {
            final int targetScrollPosition = state.getTargetScrollPosition();
            totalSpace = n3;
            totalSpace2 = n2;
            if (targetScrollPosition != -1) {
                final boolean mShouldReverseLayout = this.mShouldReverseLayout;
                if (targetScrollPosition < mCurrentPosition) {
                    n = (true ? 1 : 0);
                }
                if ((mShouldReverseLayout ? 1 : 0) == n) {
                    totalSpace = this.mPrimaryOrientation.getTotalSpace();
                    totalSpace2 = n2;
                }
                else {
                    totalSpace2 = this.mPrimaryOrientation.getTotalSpace();
                    totalSpace = n3;
                }
            }
        }
        if (((RecyclerView.LayoutManager)this).getClipToPadding()) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - totalSpace2;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + totalSpace;
            return;
        }
        this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + totalSpace;
        this.mLayoutState.mStartLine = -totalSpace2;
    }
    
    private void updateRemainingSpans(final Span span, final int n, final int n2) {
        final int deletedSize = span.getDeletedSize();
        if (n == -1) {
            if (span.getStartLine() + deletedSize <= n2) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        }
        else if (span.getEndLine() - deletedSize >= n2) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }
    
    private int updateSpecWithExtra(final int n, final int n2, final int n3) {
        if (n2 != 0 || n3 != 0) {
            final int mode = View$MeasureSpec.getMode(n);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                return View$MeasureSpec.makeMeasureSpec(Math.max(0, View$MeasureSpec.getSize(n) - n2 - n3), mode);
            }
        }
        return n;
    }
    
    boolean areAllEndsEqual() {
        final int endLine = this.mSpans[0].getEndLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; ++i) {
            if (this.mSpans[i].getEndLine(Integer.MIN_VALUE) != endLine) {
                return false;
            }
        }
        return true;
    }
    
    boolean areAllStartsEqual() {
        final int startLine = this.mSpans[0].getStartLine(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; ++i) {
            if (this.mSpans[i].getStartLine(Integer.MIN_VALUE) != startLine) {
                return false;
            }
        }
        return true;
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
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
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
    
    public int[] findFirstCompletelyVisibleItemPositions(final int[] array) {
        int[] array2;
        if (array == null) {
            array2 = new int[this.mSpanCount];
        }
        else {
            array2 = array;
            if (array.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
            }
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array2[i] = this.mSpans[i].findFirstCompletelyVisibleItemPosition();
        }
        return array2;
    }
    
    View findFirstVisibleItemClosestToEnd(final boolean b, final boolean b2) {
        this.ensureOrientationHelper();
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        View view2;
        for (int i = ((RecyclerView.LayoutManager)this).getChildCount() - 1; i >= 0; --i, view = view2) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
            view2 = view;
            if (decoratedEnd > startAfterPadding) {
                if (decoratedStart >= endAfterPadding) {
                    view2 = view;
                }
                else {
                    if (decoratedEnd <= endAfterPadding || !b) {
                        return child;
                    }
                    view2 = view;
                    if (b2 && (view2 = view) == null) {
                        view2 = child;
                    }
                }
            }
        }
        return view;
    }
    
    View findFirstVisibleItemClosestToStart(final boolean b, final boolean b2) {
        this.ensureOrientationHelper();
        final int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        final int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        final int childCount = ((RecyclerView.LayoutManager)this).getChildCount();
        View view = null;
        View view2;
        for (int i = 0; i < childCount; ++i, view = view2) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
            view2 = view;
            if (this.mPrimaryOrientation.getDecoratedEnd(child) > startAfterPadding) {
                if (decoratedStart >= endAfterPadding) {
                    view2 = view;
                }
                else {
                    if (decoratedStart >= startAfterPadding || !b) {
                        return child;
                    }
                    view2 = view;
                    if (b2 && (view2 = view) == null) {
                        view2 = child;
                    }
                }
            }
        }
        return view;
    }
    
    int findFirstVisibleItemPositionInt() {
        View view;
        if (this.mShouldReverseLayout) {
            view = this.findFirstVisibleItemClosestToEnd(true, true);
        }
        else {
            view = this.findFirstVisibleItemClosestToStart(true, true);
        }
        if (view == null) {
            return -1;
        }
        return ((RecyclerView.LayoutManager)this).getPosition(view);
    }
    
    public int[] findFirstVisibleItemPositions(final int[] array) {
        int[] array2;
        if (array == null) {
            array2 = new int[this.mSpanCount];
        }
        else {
            array2 = array;
            if (array.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
            }
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array2[i] = this.mSpans[i].findFirstVisibleItemPosition();
        }
        return array2;
    }
    
    public int[] findLastCompletelyVisibleItemPositions(final int[] array) {
        int[] array2;
        if (array == null) {
            array2 = new int[this.mSpanCount];
        }
        else {
            array2 = array;
            if (array.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
            }
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array2[i] = this.mSpans[i].findLastCompletelyVisibleItemPosition();
        }
        return array2;
    }
    
    public int[] findLastVisibleItemPositions(final int[] array) {
        int[] array2;
        if (array == null) {
            array2 = new int[this.mSpanCount];
        }
        else {
            array2 = array;
            if (array.length < this.mSpanCount) {
                throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + array.length);
            }
        }
        for (int i = 0; i < this.mSpanCount; ++i) {
            array2[i] = this.mSpans[i].findLastVisibleItemPosition();
        }
        return array2;
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
        return super.getColumnCountForAccessibility(recycler, state);
    }
    
    public int getGapStrategy() {
        return this.mGapStrategy;
    }
    
    public int getOrientation() {
        return this.mOrientation;
    }
    
    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        return super.getRowCountForAccessibility(recycler, state);
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    View hasGapsToFix() {
        int n = ((RecyclerView.LayoutManager)this).getChildCount() - 1;
        final BitSet set = new BitSet(this.mSpanCount);
        set.set(0, this.mSpanCount, true);
        int n2;
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            n2 = 1;
        }
        else {
            n2 = -1;
        }
        int n3;
        if (this.mShouldReverseLayout) {
            n3 = 0 - 1;
        }
        else {
            final int n4 = 0;
            n3 = n + 1;
            n = n4;
        }
        int n5;
        if (n < n3) {
            n5 = 1;
        }
        else {
            n5 = -1;
        }
        for (int i = n; i != n3; i += n5) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            if (set.get(layoutParams.mSpan.mIndex)) {
                if (this.checkSpanForGap(layoutParams.mSpan)) {
                    return child;
                }
                set.clear(layoutParams.mSpan.mIndex);
            }
            if (!layoutParams.mFullSpan && i + n5 != n3) {
                final View child2 = ((RecyclerView.LayoutManager)this).getChildAt(i + n5);
                int n6 = 0;
                if (this.mShouldReverseLayout) {
                    final int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(child);
                    final int decoratedEnd2 = this.mPrimaryOrientation.getDecoratedEnd(child2);
                    if (decoratedEnd < decoratedEnd2) {
                        return child;
                    }
                    if (decoratedEnd == decoratedEnd2) {
                        n6 = 1;
                    }
                }
                else {
                    final int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(child);
                    final int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(child2);
                    if (decoratedStart > decoratedStart2) {
                        return child;
                    }
                    if (decoratedStart == decoratedStart2) {
                        n6 = 1;
                    }
                }
                if (n6 != 0) {
                    int n7;
                    if (layoutParams.mSpan.mIndex - ((LayoutParams)child2.getLayoutParams()).mSpan.mIndex < 0) {
                        n7 = 1;
                    }
                    else {
                        n7 = 0;
                    }
                    int n8;
                    if (n2 < 0) {
                        n8 = 1;
                    }
                    else {
                        n8 = 0;
                    }
                    if (n7 != n8) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
    
    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.clear();
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    boolean isLayoutRTL() {
        return ((RecyclerView.LayoutManager)this).getLayoutDirection() == 1;
    }
    
    @Override
    public void offsetChildrenHorizontal(final int n) {
        super.offsetChildrenHorizontal(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void offsetChildrenVertical(final int n) {
        super.offsetChildrenVertical(n);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].onOffset(n);
        }
    }
    
    @Override
    public void onDetachedFromWindow(final RecyclerView recyclerView, final Recycler recycler) {
        ((RecyclerView.LayoutManager)this).removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; ++i) {
            this.mSpans[i].clear();
        }
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
            final View firstVisibleItemClosestToStart = this.findFirstVisibleItemClosestToStart(false, true);
            final View firstVisibleItemClosestToEnd = this.findFirstVisibleItemClosestToEnd(false, true);
            if (firstVisibleItemClosestToStart != null && firstVisibleItemClosestToEnd != null) {
                final int position = ((RecyclerView.LayoutManager)this).getPosition(firstVisibleItemClosestToStart);
                final int position2 = ((RecyclerView.LayoutManager)this).getPosition(firstVisibleItemClosestToEnd);
                if (position < position2) {
                    record.setFromIndex(position);
                    record.setToIndex(position2);
                    return;
                }
                record.setFromIndex(position2);
                record.setToIndex(position);
            }
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
        if (this.mOrientation == 0) {
            final int spanIndex = layoutParams2.getSpanIndex();
            int mSpanCount;
            if (layoutParams2.mFullSpan) {
                mSpanCount = this.mSpanCount;
            }
            else {
                mSpanCount = 1;
            }
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(spanIndex, mSpanCount, -1, -1, layoutParams2.mFullSpan, false));
            return;
        }
        final int spanIndex2 = layoutParams2.getSpanIndex();
        int mSpanCount2;
        if (layoutParams2.mFullSpan) {
            mSpanCount2 = this.mSpanCount;
        }
        else {
            mSpanCount2 = 1;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, spanIndex2, mSpanCount2, layoutParams2.mFullSpan, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 1);
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mLazySpanLookup.clear();
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.handleUpdate(n, n2, 8);
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.handleUpdate(n, n2, 2);
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.handleUpdate(n, n2, 4);
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        final boolean b = true;
        this.ensureOrientationHelper();
        final AnchorInfo mAnchorInfo = this.mAnchorInfo;
        mAnchorInfo.reset();
        if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && state.getItemCount() == 0) {
            ((RecyclerView.LayoutManager)this).removeAndRecycleAllViews(recycler);
            return;
        }
        if (this.mPendingSavedState != null) {
            this.applyPendingSavedState(mAnchorInfo);
        }
        else {
            this.resolveShouldLayoutReverse();
            mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
        }
        this.updateAnchorInfoForLayout(state, mAnchorInfo);
        if (this.mPendingSavedState == null && (mAnchorInfo.mLayoutFromEnd != this.mLastLayoutFromEnd || this.isLayoutRTL() != this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            mAnchorInfo.mInvalidateOffsets = true;
        }
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0 && (this.mPendingSavedState == null || this.mPendingSavedState.mSpanOffsetsSize < 1)) {
            if (mAnchorInfo.mInvalidateOffsets) {
                for (int i = 0; i < this.mSpanCount; ++i) {
                    this.mSpans[i].clear();
                    if (mAnchorInfo.mOffset != Integer.MIN_VALUE) {
                        this.mSpans[i].setLine(mAnchorInfo.mOffset);
                    }
                }
            }
            else {
                for (int j = 0; j < this.mSpanCount; ++j) {
                    this.mSpans[j].cacheReferenceLineAndClear(this.mShouldReverseLayout, mAnchorInfo.mOffset);
                }
            }
        }
        ((RecyclerView.LayoutManager)this).detachAndScrapAttachedViews(recycler);
        this.mLaidOutInvalidFullSpan = false;
        this.updateMeasureSpecs();
        this.updateLayoutState(mAnchorInfo.mPosition, state);
        if (mAnchorInfo.mLayoutFromEnd) {
            this.setLayoutStateDirection(-1);
            this.fill(recycler, this.mLayoutState, state);
            this.setLayoutStateDirection(1);
            this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state);
        }
        else {
            this.setLayoutStateDirection(1);
            this.fill(recycler, this.mLayoutState, state);
            this.setLayoutStateDirection(-1);
            this.mLayoutState.mCurrentPosition = mAnchorInfo.mPosition + this.mLayoutState.mItemDirection;
            this.fill(recycler, this.mLayoutState, state);
        }
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            if (this.mShouldReverseLayout) {
                this.fixEndGap(recycler, state, true);
                this.fixStartGap(recycler, state, false);
            }
            else {
                this.fixStartGap(recycler, state, true);
                this.fixEndGap(recycler, state, false);
            }
        }
        Label_0428: {
            if (!state.isPreLayout()) {
                while (true) {
                    Label_0523: {
                        if (this.mGapStrategy == 0 || ((RecyclerView.LayoutManager)this).getChildCount() <= 0) {
                            break Label_0523;
                        }
                        int n = b ? 1 : 0;
                        if (!this.mLaidOutInvalidFullSpan) {
                            if (this.hasGapsToFix() == null) {
                                break Label_0523;
                            }
                            n = (b ? 1 : 0);
                        }
                        if (n != 0) {
                            ((RecyclerView.LayoutManager)this).removeCallbacks(this.mCheckForGapsRunnable);
                            ((RecyclerView.LayoutManager)this).postOnAnimation(this.mCheckForGapsRunnable);
                        }
                        this.mPendingScrollPosition = -1;
                        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                        break Label_0428;
                    }
                    int n = 0;
                    continue;
                }
            }
        }
        this.mLastLayoutFromEnd = mAnchorInfo.mLayoutFromEnd;
        this.mLastLayoutRTL = this.isLayoutRTL();
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
        SavedState savedState;
        if (this.mPendingSavedState != null) {
            savedState = new SavedState(this.mPendingSavedState);
        }
        else {
            final SavedState savedState2 = new SavedState();
            savedState2.mReverseLayout = this.mReverseLayout;
            savedState2.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
            savedState2.mLastLayoutRTL = this.mLastLayoutRTL;
            if (this.mLazySpanLookup != null && this.mLazySpanLookup.mData != null) {
                savedState2.mSpanLookup = this.mLazySpanLookup.mData;
                savedState2.mSpanLookupSize = savedState2.mSpanLookup.length;
                savedState2.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
            }
            else {
                savedState2.mSpanLookupSize = 0;
            }
            if (((RecyclerView.LayoutManager)this).getChildCount() <= 0) {
                savedState2.mAnchorPosition = -1;
                savedState2.mVisibleAnchorPosition = -1;
                savedState2.mSpanOffsetsSize = 0;
                return (Parcelable)savedState2;
            }
            this.ensureOrientationHelper();
            int mAnchorPosition;
            if (this.mLastLayoutFromEnd) {
                mAnchorPosition = this.getLastChildPosition();
            }
            else {
                mAnchorPosition = this.getFirstChildPosition();
            }
            savedState2.mAnchorPosition = mAnchorPosition;
            savedState2.mVisibleAnchorPosition = this.findFirstVisibleItemPositionInt();
            savedState2.mSpanOffsetsSize = this.mSpanCount;
            savedState2.mSpanOffsets = new int[this.mSpanCount];
            int n = 0;
            while (true) {
                savedState = savedState2;
                if (n >= this.mSpanCount) {
                    break;
                }
                int n2;
                if (this.mLastLayoutFromEnd) {
                    final int endLine = this.mSpans[n].getEndLine(Integer.MIN_VALUE);
                    if ((n2 = endLine) != Integer.MIN_VALUE) {
                        n2 = endLine - this.mPrimaryOrientation.getEndAfterPadding();
                    }
                }
                else {
                    final int startLine = this.mSpans[n].getStartLine(Integer.MIN_VALUE);
                    if ((n2 = startLine) != Integer.MIN_VALUE) {
                        n2 = startLine - this.mPrimaryOrientation.getStartAfterPadding();
                    }
                }
                savedState2.mSpanOffsets[n] = n2;
                ++n;
            }
        }
        return (Parcelable)savedState;
    }
    
    @Override
    public void onScrollStateChanged(final int n) {
        if (n == 0) {
            this.checkForGaps();
        }
    }
    
    int scrollBy(int n, final Recycler recycler, final State state) {
        this.ensureOrientationHelper();
        int layoutStateDirection;
        int n2;
        if (n > 0) {
            layoutStateDirection = 1;
            n2 = this.getLastChildPosition();
        }
        else {
            layoutStateDirection = -1;
            n2 = this.getFirstChildPosition();
        }
        this.updateLayoutState(n2, state);
        this.setLayoutStateDirection(layoutStateDirection);
        this.mLayoutState.mCurrentPosition = this.mLayoutState.mItemDirection + n2;
        final int abs = Math.abs(n);
        this.mLayoutState.mAvailable = abs;
        final int fill = this.fill(recycler, this.mLayoutState, state);
        if (abs >= fill) {
            if (n < 0) {
                n = -fill;
            }
            else {
                n = fill;
            }
        }
        this.mPrimaryOrientation.offsetChildren(-n);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        return n;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        return this.scrollBy(n, recycler, state);
    }
    
    @Override
    public void scrollToPosition(final int mPendingScrollPosition) {
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != mPendingScrollPosition) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void scrollToPositionWithOffset(final int mPendingScrollPosition, final int mPendingScrollPositionOffset) {
        if (this.mPendingSavedState != null) {
            this.mPendingSavedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = mPendingScrollPosition;
        this.mPendingScrollPositionOffset = mPendingScrollPositionOffset;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        return this.scrollBy(n, recycler, state);
    }
    
    public void setGapStrategy(final int mGapStrategy) {
        this.assertNotInLayoutOrScroll(null);
        if (mGapStrategy == this.mGapStrategy) {
            return;
        }
        if (mGapStrategy != 0 && mGapStrategy != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = mGapStrategy;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        this.assertNotInLayoutOrScroll(null);
        if (mOrientation == this.mOrientation) {
            return;
        }
        this.mOrientation = mOrientation;
        if (this.mPrimaryOrientation != null && this.mSecondaryOrientation != null) {
            final OrientationHelper mPrimaryOrientation = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = mPrimaryOrientation;
        }
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setReverseLayout(final boolean b) {
        this.assertNotInLayoutOrScroll(null);
        if (this.mPendingSavedState != null && this.mPendingSavedState.mReverseLayout != b) {
            this.mPendingSavedState.mReverseLayout = b;
        }
        this.mReverseLayout = b;
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setSpanCount(int i) {
        this.assertNotInLayoutOrScroll(null);
        if (i != this.mSpanCount) {
            this.invalidateSpanAssignments();
            this.mSpanCount = i;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (i = 0; i < this.mSpanCount; ++i) {
                this.mSpans[i] = new Span(i);
            }
            ((RecyclerView.LayoutManager)this).requestLayout();
        }
    }
    
    @Override
    public void smoothScrollToPosition(final RecyclerView recyclerView, final State state, final int targetPosition) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(int access$400) {
                access$400 = StaggeredGridLayoutManager.this.calculateScrollDirectionForPosition(access$400);
                if (access$400 == 0) {
                    return null;
                }
                if (StaggeredGridLayoutManager.this.mOrientation == 0) {
                    return new PointF((float)access$400, 0.0f);
                }
                return new PointF(0.0f, (float)access$400);
            }
        };
        ((RecyclerView.SmoothScroller)linearSmoothScroller).setTargetPosition(targetPosition);
        ((RecyclerView.LayoutManager)this).startSmoothScroll(linearSmoothScroller);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }
    
    boolean updateAnchorFromPendingData(final State state, final AnchorInfo anchorInfo) {
        boolean mLayoutFromEnd = false;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        if (this.mPendingSavedState != null && this.mPendingSavedState.mAnchorPosition != -1 && this.mPendingSavedState.mSpanOffsetsSize >= 1) {
            anchorInfo.mOffset = Integer.MIN_VALUE;
            anchorInfo.mPosition = this.mPendingScrollPosition;
            return true;
        }
        final View viewByPosition = ((RecyclerView.LayoutManager)this).findViewByPosition(this.mPendingScrollPosition);
        if (viewByPosition == null) {
            anchorInfo.mPosition = this.mPendingScrollPosition;
            if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                if (this.calculateScrollDirectionForPosition(anchorInfo.mPosition) == 1) {
                    mLayoutFromEnd = true;
                }
                anchorInfo.mLayoutFromEnd = mLayoutFromEnd;
                anchorInfo.assignCoordinateFromPadding();
            }
            else {
                anchorInfo.assignCoordinateFromPadding(this.mPendingScrollPositionOffset);
            }
            return anchorInfo.mInvalidateOffsets = true;
        }
        int mPosition;
        if (this.mShouldReverseLayout) {
            mPosition = this.getLastChildPosition();
        }
        else {
            mPosition = this.getFirstChildPosition();
        }
        anchorInfo.mPosition = mPosition;
        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
            if (anchorInfo.mLayoutFromEnd) {
                anchorInfo.mOffset = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
                return true;
            }
            anchorInfo.mOffset = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedStart(viewByPosition);
            return true;
        }
        else {
            if (this.mPrimaryOrientation.getDecoratedMeasurement(viewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                int mOffset;
                if (anchorInfo.mLayoutFromEnd) {
                    mOffset = this.mPrimaryOrientation.getEndAfterPadding();
                }
                else {
                    mOffset = this.mPrimaryOrientation.getStartAfterPadding();
                }
                anchorInfo.mOffset = mOffset;
                return true;
            }
            final int n = this.mPrimaryOrientation.getDecoratedStart(viewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
            if (n < 0) {
                anchorInfo.mOffset = -n;
                return true;
            }
            final int mOffset2 = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(viewByPosition);
            if (mOffset2 < 0) {
                anchorInfo.mOffset = mOffset2;
                return true;
            }
            anchorInfo.mOffset = Integer.MIN_VALUE;
            return true;
        }
    }
    
    void updateAnchorInfoForLayout(final State state, final AnchorInfo anchorInfo) {
        if (!this.updateAnchorFromPendingData(state, anchorInfo) && !this.updateAnchorFromChildren(state, anchorInfo)) {
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = 0;
        }
    }
    
    void updateMeasureSpecs() {
        this.mSizePerSpan = this.mSecondaryOrientation.getTotalSpace() / this.mSpanCount;
        this.mFullSizeSpec = View$MeasureSpec.makeMeasureSpec(this.mSecondaryOrientation.getTotalSpace(), 1073741824);
        if (this.mOrientation == 1) {
            this.mWidthSpec = View$MeasureSpec.makeMeasureSpec(this.mSizePerSpan, 1073741824);
            this.mHeightSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
            return;
        }
        this.mHeightSpec = View$MeasureSpec.makeMeasureSpec(this.mSizePerSpan, 1073741824);
        this.mWidthSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
    }
    
    private class AnchorInfo
    {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        
        void assignCoordinateFromPadding() {
            int mOffset;
            if (this.mLayoutFromEnd) {
                mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            }
            else {
                mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            }
            this.mOffset = mOffset;
        }
        
        void assignCoordinateFromPadding(final int n) {
            if (this.mLayoutFromEnd) {
                this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - n;
                return;
            }
            this.mOffset = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + n;
        }
        
        void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        boolean mFullSpan;
        Span mSpan;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
        }
        
        public final int getSpanIndex() {
            if (this.mSpan == null) {
                return -1;
            }
            return this.mSpan.mIndex;
        }
        
        public boolean isFullSpan() {
            return this.mFullSpan;
        }
        
        public void setFullSpan(final boolean mFullSpan) {
            this.mFullSpan = mFullSpan;
        }
    }
    
    static class LazySpanLookup
    {
        private static final int MIN_SIZE = 10;
        int[] mData;
        List<FullSpanItem> mFullSpanItems;
        
        private int invalidateFullSpansAfter(final int n) {
            if (this.mFullSpanItems != null) {
                final FullSpanItem fullSpanItem = this.getFullSpanItem(n);
                if (fullSpanItem != null) {
                    this.mFullSpanItems.remove(fullSpanItem);
                }
                final int n2 = -1;
                final int size = this.mFullSpanItems.size();
                int n3 = 0;
                int n4;
                while (true) {
                    n4 = n2;
                    if (n3 >= size) {
                        break;
                    }
                    if (this.mFullSpanItems.get(n3).mPosition >= n) {
                        n4 = n3;
                        break;
                    }
                    ++n3;
                }
                if (n4 != -1) {
                    final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(n4);
                    this.mFullSpanItems.remove(n4);
                    return fullSpanItem2.mPosition;
                }
            }
            return -1;
        }
        
        private void offsetFullSpansForAddition(final int n, final int n2) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n) {
                        fullSpanItem.mPosition += n2;
                    }
                }
            }
        }
        
        private void offsetFullSpansForRemoval(final int n, final int n2) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n) {
                        if (fullSpanItem.mPosition < n + n2) {
                            this.mFullSpanItems.remove(i);
                        }
                        else {
                            fullSpanItem.mPosition -= n2;
                        }
                    }
                }
            }
        }
        
        public void addFullSpanItem(final FullSpanItem fullSpanItem) {
            if (this.mFullSpanItems == null) {
                this.mFullSpanItems = new ArrayList<FullSpanItem>();
            }
            for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                final FullSpanItem fullSpanItem2 = this.mFullSpanItems.get(i);
                if (fullSpanItem2.mPosition == fullSpanItem.mPosition) {
                    this.mFullSpanItems.remove(i);
                }
                if (fullSpanItem2.mPosition >= fullSpanItem.mPosition) {
                    this.mFullSpanItems.add(i, fullSpanItem);
                    return;
                }
            }
            this.mFullSpanItems.add(fullSpanItem);
        }
        
        void clear() {
            if (this.mData != null) {
                Arrays.fill(this.mData, -1);
            }
            this.mFullSpanItems = null;
        }
        
        void ensureSize(final int n) {
            if (this.mData == null) {
                Arrays.fill(this.mData = new int[Math.max(n, 10) + 1], -1);
            }
            else if (n >= this.mData.length) {
                final int[] mData = this.mData;
                System.arraycopy(mData, 0, this.mData = new int[this.sizeForPosition(n)], 0, mData.length);
                Arrays.fill(this.mData, mData.length, this.mData.length, -1);
            }
        }
        
        int forceInvalidateAfter(final int n) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                    if (this.mFullSpanItems.get(i).mPosition >= n) {
                        this.mFullSpanItems.remove(i);
                    }
                }
            }
            return this.invalidateAfter(n);
        }
        
        public FullSpanItem getFirstFullSpanItemInRange(final int n, final int n2, final int n3, final boolean b) {
            if (this.mFullSpanItems != null) {
                for (int size = this.mFullSpanItems.size(), i = 0; i < size; ++i) {
                    final FullSpanItem fullSpanItem = this.mFullSpanItems.get(i);
                    if (fullSpanItem.mPosition >= n2) {
                        return null;
                    }
                    if (fullSpanItem.mPosition >= n) {
                        FullSpanItem fullSpanItem2 = fullSpanItem;
                        if (n3 == 0) {
                            return fullSpanItem2;
                        }
                        fullSpanItem2 = fullSpanItem;
                        if (fullSpanItem.mGapDir == n3) {
                            return fullSpanItem2;
                        }
                        if (b) {
                            fullSpanItem2 = fullSpanItem;
                            if (fullSpanItem.mHasUnwantedGapAfter) {
                                return fullSpanItem2;
                            }
                        }
                    }
                }
                return null;
            }
            return null;
        }
        
        public FullSpanItem getFullSpanItem(final int n) {
            if (this.mFullSpanItems != null) {
                for (int i = this.mFullSpanItems.size() - 1; i >= 0; --i) {
                    final FullSpanItem fullSpanItem;
                    if ((fullSpanItem = this.mFullSpanItems.get(i)).mPosition == n) {
                        return fullSpanItem;
                    }
                }
                return null;
            }
            return null;
        }
        
        int getSpan(final int n) {
            if (this.mData == null || n >= this.mData.length) {
                return -1;
            }
            return this.mData[n];
        }
        
        int invalidateAfter(final int n) {
            if (this.mData == null || n >= this.mData.length) {
                return -1;
            }
            final int invalidateFullSpansAfter = this.invalidateFullSpansAfter(n);
            if (invalidateFullSpansAfter == -1) {
                Arrays.fill(this.mData, n, this.mData.length, -1);
                return this.mData.length;
            }
            Arrays.fill(this.mData, n, invalidateFullSpansAfter + 1, -1);
            return invalidateFullSpansAfter + 1;
        }
        
        void offsetForAddition(final int n, final int n2) {
            if (this.mData == null || n >= this.mData.length) {
                return;
            }
            this.ensureSize(n + n2);
            System.arraycopy(this.mData, n, this.mData, n + n2, this.mData.length - n - n2);
            Arrays.fill(this.mData, n, n + n2, -1);
            this.offsetFullSpansForAddition(n, n2);
        }
        
        void offsetForRemoval(final int n, final int n2) {
            if (this.mData == null || n >= this.mData.length) {
                return;
            }
            this.ensureSize(n + n2);
            System.arraycopy(this.mData, n + n2, this.mData, n, this.mData.length - n - n2);
            Arrays.fill(this.mData, this.mData.length - n2, this.mData.length, -1);
            this.offsetFullSpansForRemoval(n, n2);
        }
        
        void setSpan(final int n, final Span span) {
            this.ensureSize(n);
            this.mData[n] = span.mIndex;
        }
        
        int sizeForPosition(final int n) {
            int i;
            for (i = this.mData.length; i <= n; i *= 2) {}
            return i;
        }
        
        static class FullSpanItem implements Parcelable
        {
            public static final Parcelable$Creator<FullSpanItem> CREATOR;
            int mGapDir;
            int[] mGapPerSpan;
            boolean mHasUnwantedGapAfter;
            int mPosition;
            
            static {
                CREATOR = (Parcelable$Creator)new Parcelable$Creator<FullSpanItem>() {
                    public FullSpanItem createFromParcel(final Parcel parcel) {
                        return new FullSpanItem(parcel);
                    }
                    
                    public FullSpanItem[] newArray(final int n) {
                        return new FullSpanItem[n];
                    }
                };
            }
            
            public FullSpanItem() {
            }
            
            public FullSpanItem(final Parcel parcel) {
                boolean mHasUnwantedGapAfter = true;
                this.mPosition = parcel.readInt();
                this.mGapDir = parcel.readInt();
                if (parcel.readInt() != 1) {
                    mHasUnwantedGapAfter = false;
                }
                this.mHasUnwantedGapAfter = mHasUnwantedGapAfter;
                final int int1 = parcel.readInt();
                if (int1 > 0) {
                    parcel.readIntArray(this.mGapPerSpan = new int[int1]);
                }
            }
            
            public int describeContents() {
                return 0;
            }
            
            int getGapForSpan(final int n) {
                if (this.mGapPerSpan == null) {
                    return 0;
                }
                return this.mGapPerSpan[n];
            }
            
            public void invalidateSpanGaps() {
                this.mGapPerSpan = null;
            }
            
            @Override
            public String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }
            
            public void writeToParcel(final Parcel parcel, int n) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                if (this.mHasUnwantedGapAfter) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel.writeInt(n);
                if (this.mGapPerSpan != null && this.mGapPerSpan.length > 0) {
                    parcel.writeInt(this.mGapPerSpan.length);
                    parcel.writeIntArray(this.mGapPerSpan);
                    return;
                }
                parcel.writeInt(0);
            }
        }
    }
    
    public static class SavedState implements Parcelable
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List<FullSpanItem> mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;
        
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
            final boolean b = true;
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            this.mSpanOffsetsSize = parcel.readInt();
            if (this.mSpanOffsetsSize > 0) {
                parcel.readIntArray(this.mSpanOffsets = new int[this.mSpanOffsetsSize]);
            }
            this.mSpanLookupSize = parcel.readInt();
            if (this.mSpanLookupSize > 0) {
                parcel.readIntArray(this.mSpanLookup = new int[this.mSpanLookupSize]);
            }
            this.mReverseLayout = (parcel.readInt() == 1);
            this.mAnchorLayoutFromEnd = (parcel.readInt() == 1);
            this.mLastLayoutRTL = (parcel.readInt() == 1 && b);
            this.mFullSpanItems = (List<FullSpanItem>)parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }
        
        public SavedState(final SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }
        
        public int describeContents() {
            return 0;
        }
        
        void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }
        
        void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            final int n2 = 1;
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            if (this.mReverseLayout) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
            if (this.mAnchorLayoutFromEnd) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
            if (this.mLastLayoutRTL) {
                n = n2;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
            parcel.writeList((List)this.mFullSpanItems);
        }
    }
    
    class Span
    {
        static final int INVALID_LINE = Integer.MIN_VALUE;
        int mCachedEnd;
        int mCachedStart;
        int mDeletedSize;
        final int mIndex;
        private ArrayList<View> mViews;
        
        private Span(final int mIndex) {
            this.mViews = new ArrayList<View>();
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mDeletedSize = 0;
            this.mIndex = mIndex;
        }
        
        void appendToSpan(final View view) {
            final LayoutParams layoutParams = this.getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(view);
            this.mCachedEnd = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }
        
        void cacheReferenceLineAndClear(final boolean b, final int n) {
            int n2;
            if (b) {
                n2 = this.getEndLine(Integer.MIN_VALUE);
            }
            else {
                n2 = this.getStartLine(Integer.MIN_VALUE);
            }
            this.clear();
            if (n2 != Integer.MIN_VALUE && (!b || n2 >= StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) && (b || n2 <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding())) {
                int n3 = n2;
                if (n != Integer.MIN_VALUE) {
                    n3 = n2 + n;
                }
                this.mCachedEnd = n3;
                this.mCachedStart = n3;
            }
        }
        
        void calculateCachedEnd() {
            final View view = this.mViews.get(this.mViews.size() - 1);
            final LayoutParams layoutParams = this.getLayoutParams(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (layoutParams.mFullSpan) {
                final FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
                if (fullSpanItem != null && fullSpanItem.mGapDir == 1) {
                    this.mCachedEnd += fullSpanItem.getGapForSpan(this.mIndex);
                }
            }
        }
        
        void calculateCachedStart() {
            final View view = this.mViews.get(0);
            final LayoutParams layoutParams = this.getLayoutParams(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (layoutParams.mFullSpan) {
                final FullSpanItem fullSpanItem = StaggeredGridLayoutManager.this.mLazySpanLookup.getFullSpanItem(((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition());
                if (fullSpanItem != null && fullSpanItem.mGapDir == -1) {
                    this.mCachedStart -= fullSpanItem.getGapForSpan(this.mIndex);
                }
            }
        }
        
        void clear() {
            this.mViews.clear();
            this.invalidateCache();
            this.mDeletedSize = 0;
        }
        
        public int findFirstCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(this.mViews.size() - 1, -1, true);
            }
            return this.findOneVisibleChild(0, this.mViews.size(), true);
        }
        
        public int findFirstVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(this.mViews.size() - 1, -1, false);
            }
            return this.findOneVisibleChild(0, this.mViews.size(), false);
        }
        
        public int findLastCompletelyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(0, this.mViews.size(), true);
            }
            return this.findOneVisibleChild(this.mViews.size() - 1, -1, true);
        }
        
        public int findLastVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return this.findOneVisibleChild(0, this.mViews.size(), false);
            }
            return this.findOneVisibleChild(this.mViews.size() - 1, -1, false);
        }
        
        int findOneVisibleChild(int n, final int n2, final boolean b) {
            final int n3 = -1;
            final int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            final int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int n4;
            if (n2 > n) {
                n4 = 1;
            }
            else {
                n4 = -1;
            }
            int position;
            while (true) {
                position = n3;
                if (n == n2) {
                    break;
                }
                final View view = this.mViews.get(n);
                final int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                final int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                if (decoratedStart < endAfterPadding && decoratedEnd > startAfterPadding) {
                    if (!b) {
                        return ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view);
                    }
                    if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        position = ((RecyclerView.LayoutManager)StaggeredGridLayoutManager.this).getPosition(view);
                        break;
                    }
                }
                n += n4;
            }
            return position;
        }
        
        public int getDeletedSize() {
            return this.mDeletedSize;
        }
        
        int getEndLine() {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                return this.mCachedEnd;
            }
            this.calculateCachedEnd();
            return this.mCachedEnd;
        }
        
        int getEndLine(int mCachedEnd) {
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                mCachedEnd = this.mCachedEnd;
            }
            else if (this.mViews.size() != 0) {
                this.calculateCachedEnd();
                return this.mCachedEnd;
            }
            return mCachedEnd;
        }
        
        LayoutParams getLayoutParams(final View view) {
            return (LayoutParams)view.getLayoutParams();
        }
        
        int getNormalizedOffset(int n, int n2, int n3) {
            if (this.mViews.size() == 0) {
                n2 = 0;
            }
            else if (n < 0) {
                n3 = this.getEndLine() - n3;
                if (n3 <= 0) {
                    return 0;
                }
                n2 = n;
                if (-n > n3) {
                    return -n3;
                }
            }
            else {
                n2 -= this.getStartLine();
                if (n2 <= 0) {
                    return 0;
                }
                if (n2 < n) {
                    n = n2;
                }
                return n;
            }
            return n2;
        }
        
        int getStartLine() {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                return this.mCachedStart;
            }
            this.calculateCachedStart();
            return this.mCachedStart;
        }
        
        int getStartLine(int mCachedStart) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                mCachedStart = this.mCachedStart;
            }
            else if (this.mViews.size() != 0) {
                this.calculateCachedStart();
                return this.mCachedStart;
            }
            return mCachedStart;
        }
        
        void invalidateCache() {
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        
        boolean isEmpty(final int n, final int n2) {
            for (int size = this.mViews.size(), i = 0; i < size; ++i) {
                final View view = this.mViews.get(i);
                if (StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view) < n2 && StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view) > n) {
                    return false;
                }
            }
            return true;
        }
        
        void onOffset(final int n) {
            if (this.mCachedStart != Integer.MIN_VALUE) {
                this.mCachedStart += n;
            }
            if (this.mCachedEnd != Integer.MIN_VALUE) {
                this.mCachedEnd += n;
            }
        }
        
        void popEnd() {
            final int size = this.mViews.size();
            final View view = this.mViews.remove(size - 1);
            final LayoutParams layoutParams = this.getLayoutParams(view);
            layoutParams.mSpan = null;
            if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            if (size == 1) {
                this.mCachedStart = Integer.MIN_VALUE;
            }
            this.mCachedEnd = Integer.MIN_VALUE;
        }
        
        void popStart() {
            final View view = this.mViews.remove(0);
            final LayoutParams layoutParams = this.getLayoutParams(view);
            layoutParams.mSpan = null;
            if (this.mViews.size() == 0) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
                this.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            this.mCachedStart = Integer.MIN_VALUE;
        }
        
        void prependToSpan(final View view) {
            final LayoutParams layoutParams = this.getLayoutParams(view);
            layoutParams.mSpan = this;
            this.mViews.add(0, view);
            this.mCachedStart = Integer.MIN_VALUE;
            if (this.mViews.size() == 1) {
                this.mCachedEnd = Integer.MIN_VALUE;
            }
            if (((RecyclerView.LayoutParams)layoutParams).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams).isItemChanged()) {
                this.mDeletedSize += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }
        
        void setLine(final int n) {
            this.mCachedStart = n;
            this.mCachedEnd = n;
        }
    }
}
