// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.support.v4.os.ParcelableCompat;
import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import android.view.ViewGroup$MarginLayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import android.view.View;
import android.support.design.R;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import android.support.v4.view.WindowInsetsCompat;
import android.widget.LinearLayout;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout extends LinearLayout
{
    private static final int INVALID_SCROLL_RANGE = -1;
    private static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    private static final int PENDING_ACTION_COLLAPSED = 2;
    private static final int PENDING_ACTION_EXPANDED = 1;
    private static final int PENDING_ACTION_NONE = 0;
    private int mDownPreScrollRange;
    private int mDownScrollRange;
    boolean mHaveChildWithInterpolator;
    private WindowInsetsCompat mLastInsets;
    private final List<OnOffsetChangedListener> mListeners;
    private int mPendingAction;
    private float mTargetElevation;
    private int mTotalScrollRange;
    
    public AppBarLayout(final Context context) {
        this(context, null);
    }
    
    public AppBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
        this.mPendingAction = 0;
        this.setOrientation(1);
        ThemeUtils.checkAppCompatTheme(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
        this.mTargetElevation = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0);
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.AppBarLayout_android_background));
        if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_expanded)) {
            this.setExpanded(obtainStyledAttributes.getBoolean(R.styleable.AppBarLayout_expanded, false));
        }
        obtainStyledAttributes.recycle();
        ViewUtils.setBoundsViewOutlineProvider((View)this);
        this.mListeners = new ArrayList<OnOffsetChangedListener>();
        ViewCompat.setElevation((View)this, this.mTargetElevation);
        ViewCompat.setOnApplyWindowInsetsListener((View)this, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
                AppBarLayout.this.setWindowInsets(windowInsetsCompat);
                return windowInsetsCompat.consumeSystemWindowInsets();
            }
        });
    }
    
    private int getDownNestedPreScrollRange() {
        if (this.mDownPreScrollRange != -1) {
            return this.mDownPreScrollRange;
        }
        int mDownPreScrollRange = 0;
        int n2;
        for (int i = this.getChildCount() - 1; i >= 0; --i, mDownPreScrollRange = n2) {
            final View child = this.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = layoutParams.mScrollFlags;
            if ((mScrollFlags & 0x5) == 0x5) {
                final int n = mDownPreScrollRange + (layoutParams.topMargin + layoutParams.bottomMargin);
                if ((mScrollFlags & 0x8) != 0x0) {
                    n2 = n + ViewCompat.getMinimumHeight(child);
                }
                else {
                    n2 = n + measuredHeight;
                }
            }
            else if ((n2 = mDownPreScrollRange) > 0) {
                break;
            }
        }
        return this.mDownPreScrollRange = mDownPreScrollRange;
    }
    
    private int getDownNestedScrollRange() {
        if (this.mDownScrollRange != -1) {
            return this.mDownScrollRange;
        }
        int n = 0;
        int n2 = 0;
        final int childCount = this.getChildCount();
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= childCount) {
                break;
            }
            final View child = this.getChildAt(n2);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int topMargin = layoutParams.topMargin;
            final int bottomMargin = layoutParams.bottomMargin;
            final int mScrollFlags = layoutParams.mScrollFlags;
            n3 = n;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += measuredHeight + (topMargin + bottomMargin);
            if ((mScrollFlags & 0x2) != 0x0) {
                n3 = n - (ViewCompat.getMinimumHeight(child) + this.getTopInset());
                break;
            }
            ++n2;
        }
        return this.mDownScrollRange = Math.max(0, n3);
    }
    
    private int getPendingAction() {
        return this.mPendingAction;
    }
    
    private int getTopInset() {
        if (this.mLastInsets != null) {
            return this.mLastInsets.getSystemWindowInsetTop();
        }
        return 0;
    }
    
    private int getUpNestedPreScrollRange() {
        return this.getTotalScrollRange();
    }
    
    private boolean hasChildWithInterpolator() {
        return this.mHaveChildWithInterpolator;
    }
    
    private boolean hasScrollableChildren() {
        return this.getTotalScrollRange() != 0;
    }
    
    private void invalidateScrollRanges() {
        this.mTotalScrollRange = -1;
        this.mDownPreScrollRange = -1;
        this.mDownScrollRange = -1;
    }
    
    private void resetPendingAction() {
        this.mPendingAction = 0;
    }
    
    private void setWindowInsets(WindowInsetsCompat dispatchApplyWindowInsets) {
        this.mTotalScrollRange = -1;
        this.mLastInsets = dispatchApplyWindowInsets;
        for (int i = 0; i < this.getChildCount(); ++i) {
            dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(this.getChildAt(i), dispatchApplyWindowInsets);
            if (dispatchApplyWindowInsets.isConsumed()) {
                break;
            }
        }
    }
    
    public void addOnOffsetChangedListener(final OnOffsetChangedListener onOffsetChangedListener) {
        if (onOffsetChangedListener != null && !this.mListeners.contains(onOffsetChangedListener)) {
            this.mListeners.add(onOffsetChangedListener);
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof LayoutParams;
    }
    
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }
    
    public LayoutParams generateLayoutParams(final AttributeSet set) {
        return new LayoutParams(this.getContext(), set);
    }
    
    protected LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof LinearLayout$LayoutParams) {
            return new LayoutParams((LinearLayout$LayoutParams)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    final int getMinimumHeightForVisibleOverlappingContent() {
        int n = 0;
        int systemWindowInsetTop;
        if (this.mLastInsets != null) {
            systemWindowInsetTop = this.mLastInsets.getSystemWindowInsetTop();
        }
        else {
            systemWindowInsetTop = 0;
        }
        final int minimumHeight = ViewCompat.getMinimumHeight((View)this);
        if (minimumHeight != 0) {
            n = minimumHeight * 2 + systemWindowInsetTop;
        }
        else {
            final int childCount = this.getChildCount();
            if (childCount >= 1) {
                return ViewCompat.getMinimumHeight(this.getChildAt(childCount - 1)) * 2 + systemWindowInsetTop;
            }
        }
        return n;
    }
    
    public float getTargetElevation() {
        return this.mTargetElevation;
    }
    
    public final int getTotalScrollRange() {
        if (this.mTotalScrollRange != -1) {
            return this.mTotalScrollRange;
        }
        int n = 0;
        int n2 = 0;
        final int childCount = this.getChildCount();
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= childCount) {
                break;
            }
            final View child = this.getChildAt(n2);
            final LayoutParams layoutParams = (LayoutParams)child.getLayoutParams();
            final int measuredHeight = child.getMeasuredHeight();
            final int mScrollFlags = layoutParams.mScrollFlags;
            n3 = n;
            if ((mScrollFlags & 0x1) == 0x0) {
                break;
            }
            n += layoutParams.topMargin + measuredHeight + layoutParams.bottomMargin;
            if ((mScrollFlags & 0x2) != 0x0) {
                n3 = n - ViewCompat.getMinimumHeight(child);
                break;
            }
            ++n2;
        }
        return this.mTotalScrollRange = Math.max(0, n3 - this.getTopInset());
    }
    
    protected void onLayout(final boolean b, int i, int childCount, final int n, final int n2) {
        super.onLayout(b, i, childCount, n, n2);
        this.invalidateScrollRanges();
        this.mHaveChildWithInterpolator = false;
        for (i = 0, childCount = this.getChildCount(); i < childCount; ++i) {
            if (((LayoutParams)this.getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.mHaveChildWithInterpolator = true;
                break;
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        this.invalidateScrollRanges();
    }
    
    public void removeOnOffsetChangedListener(final OnOffsetChangedListener onOffsetChangedListener) {
        if (onOffsetChangedListener != null) {
            this.mListeners.remove(onOffsetChangedListener);
        }
    }
    
    public void setExpanded(final boolean b) {
        this.setExpanded(b, ViewCompat.isLaidOut((View)this));
    }
    
    public void setExpanded(final boolean b, final boolean b2) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        int n2;
        if (b2) {
            n2 = 4;
        }
        else {
            n2 = 0;
        }
        this.mPendingAction = (n2 | n);
        this.requestLayout();
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }
    
    public void setTargetElevation(final float mTargetElevation) {
        this.mTargetElevation = mTargetElevation;
    }
    
    public static class Behavior extends HeaderBehavior<AppBarLayout>
    {
        private static final int INVALID_POSITION = -1;
        private ValueAnimatorCompat mAnimator;
        private WeakReference<View> mLastNestedScrollingChildRef;
        private int mOffsetDelta;
        private int mOffsetToChildIndexOnLayout;
        private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
        private float mOffsetToChildIndexOnLayoutPerc;
        private DragCallback mOnDragCallback;
        private boolean mSkipNestedPreScroll;
        private boolean mWasFlung;
        
        public Behavior() {
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        public Behavior(final Context context, final AttributeSet set) {
            super(context, set);
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        private void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final int n) {
            if (this.mAnimator == null) {
                (this.mAnimator = ViewUtils.createAnimator()).setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.mAnimator.setUpdateListener((ValueAnimatorCompat.AnimatorUpdateListener)new ValueAnimatorCompat.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                        Behavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, valueAnimatorCompat.getAnimatedIntValue());
                    }
                });
            }
            else {
                this.mAnimator.cancel();
            }
            this.mAnimator.setIntValues(this.getTopBottomOffsetForScrollingSibling(), n);
            this.mAnimator.start();
        }
        
        private void dispatchOffsetUpdates(final AppBarLayout appBarLayout) {
            final List access$800 = appBarLayout.mListeners;
            for (int i = 0; i < access$800.size(); ++i) {
                final OnOffsetChangedListener onOffsetChangedListener = access$800.get(i);
                if (onOffsetChangedListener != null) {
                    onOffsetChangedListener.onOffsetChanged(appBarLayout, this.getTopAndBottomOffset());
                }
            }
        }
        
        private View getChildOnOffset(final AppBarLayout appBarLayout, final int n) {
            for (int i = 0; i < appBarLayout.getChildCount(); ++i) {
                final View child = appBarLayout.getChildAt(i);
                if (child.getTop() <= -n && child.getBottom() >= -n) {
                    return child;
                }
            }
            return null;
        }
        
        private int interpolateOffset(final AppBarLayout appBarLayout, final int n) {
            final int abs = Math.abs(n);
            int n2 = 0;
            final int childCount = appBarLayout.getChildCount();
            int n3;
            while (true) {
                n3 = n;
                if (n2 >= childCount) {
                    break;
                }
                final View child = appBarLayout.getChildAt(n2);
                final AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams)child.getLayoutParams();
                final Interpolator scrollInterpolator = layoutParams.getScrollInterpolator();
                if (abs >= child.getTop() && abs <= child.getBottom()) {
                    n3 = n;
                    if (scrollInterpolator == null) {
                        break;
                    }
                    int n4 = 0;
                    final int scrollFlags = layoutParams.getScrollFlags();
                    if ((scrollFlags & 0x1) != 0x0) {
                        final int n5 = n4 = 0 + (child.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                        if ((scrollFlags & 0x2) != 0x0) {
                            n4 = n5 - ViewCompat.getMinimumHeight(child);
                        }
                    }
                    int n6 = n4;
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        n6 = n4 - appBarLayout.getTopInset();
                    }
                    n3 = n;
                    if (n6 > 0) {
                        n3 = Integer.signum(n) * (child.getTop() + Math.round(n6 * scrollInterpolator.getInterpolation((abs - child.getTop()) / n6)));
                        break;
                    }
                    break;
                }
                else {
                    ++n2;
                }
            }
            return n3;
        }
        
        private void snapToChildIfNeeded(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
            final View childOnOffset = this.getChildOnOffset(appBarLayout, topBottomOffsetForScrollingSibling);
            if (childOnOffset != null && (((AppBarLayout.LayoutParams)childOnOffset.getLayoutParams()).getScrollFlags() & 0x11) == 0x11) {
                int n = -childOnOffset.getTop();
                final int n2 = -childOnOffset.getBottom();
                if (topBottomOffsetForScrollingSibling < (n2 + n) / 2) {
                    n = n2;
                }
                this.animateOffsetTo(coordinatorLayout, appBarLayout, n);
            }
        }
        
        @Override
        boolean canDragView(final AppBarLayout appBarLayout) {
            final boolean b = true;
            boolean canDrag;
            if (this.mOnDragCallback != null) {
                canDrag = this.mOnDragCallback.canDrag(appBarLayout);
            }
            else {
                canDrag = b;
                if (this.mLastNestedScrollingChildRef != null) {
                    final View view = this.mLastNestedScrollingChildRef.get();
                    if (view != null && view.isShown()) {
                        canDrag = b;
                        if (!ViewCompat.canScrollVertically(view, -1)) {
                            return canDrag;
                        }
                    }
                    return false;
                }
            }
            return canDrag;
        }
        
        @Override
        int getMaxDragOffset(final AppBarLayout appBarLayout) {
            return -appBarLayout.getDownNestedScrollRange();
        }
        
        @Override
        int getScrollRangeForDragFling(final AppBarLayout appBarLayout) {
            return appBarLayout.getTotalScrollRange();
        }
        
        @Override
        int getTopBottomOffsetForScrollingSibling() {
            return this.getTopAndBottomOffset() + this.mOffsetDelta;
        }
        
        @Override
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int topAndBottomOffset) {
            final boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, appBarLayout, topAndBottomOffset);
            final int access$500 = appBarLayout.getPendingAction();
            if (access$500 != 0) {
                if ((access$500 & 0x4) != 0x0) {
                    topAndBottomOffset = 1;
                }
                else {
                    topAndBottomOffset = 0;
                }
                if ((access$500 & 0x2) != 0x0) {
                    final int n = -appBarLayout.getUpNestedPreScrollRange();
                    if (topAndBottomOffset != 0) {
                        this.animateOffsetTo(coordinatorLayout, appBarLayout, n);
                    }
                    else {
                        this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, n);
                    }
                }
                else if ((access$500 & 0x1) != 0x0) {
                    if (topAndBottomOffset != 0) {
                        this.animateOffsetTo(coordinatorLayout, appBarLayout, 0);
                    }
                    else {
                        this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            else if (this.mOffsetToChildIndexOnLayout >= 0) {
                final View child = appBarLayout.getChildAt(this.mOffsetToChildIndexOnLayout);
                topAndBottomOffset = -child.getBottom();
                if (this.mOffsetToChildIndexOnLayoutIsMinHeight) {
                    topAndBottomOffset += ViewCompat.getMinimumHeight(child);
                }
                else {
                    topAndBottomOffset += Math.round(child.getHeight() * this.mOffsetToChildIndexOnLayoutPerc);
                }
                this.setTopAndBottomOffset(topAndBottomOffset);
            }
            appBarLayout.resetPendingAction();
            this.mOffsetToChildIndexOnLayout = -1;
            this.dispatchOffsetUpdates(appBarLayout);
            return onLayoutChild;
        }
        
        public boolean onNestedFling(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final float n, final float n2, final boolean b) {
            final boolean b2 = false;
            boolean fling;
            if (!b) {
                fling = this.fling(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), 0, -n2);
            }
            else if (n2 < 0.0f) {
                final int n3 = -appBarLayout.getTotalScrollRange() + appBarLayout.getDownNestedPreScrollRange();
                fling = b2;
                if (this.getTopBottomOffsetForScrollingSibling() < n3) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n3);
                    fling = true;
                }
            }
            else {
                final int n4 = -appBarLayout.getUpNestedPreScrollRange();
                fling = b2;
                if (this.getTopBottomOffsetForScrollingSibling() > n4) {
                    this.animateOffsetTo(coordinatorLayout, appBarLayout, n4);
                    fling = true;
                }
            }
            return this.mWasFlung = fling;
        }
        
        public void onNestedPreScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, int n, final int n2, final int[] array) {
            if (n2 != 0 && !this.mSkipNestedPreScroll) {
                int n3;
                if (n2 < 0) {
                    n = -appBarLayout.getTotalScrollRange();
                    n3 = n + appBarLayout.getDownNestedPreScrollRange();
                }
                else {
                    n = -appBarLayout.getUpNestedPreScrollRange();
                    n3 = 0;
                }
                array[1] = this.scroll(coordinatorLayout, appBarLayout, n2, n, n3);
            }
        }
        
        public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final int n, final int n2, final int n3, final int n4) {
            if (n4 < 0) {
                this.scroll(coordinatorLayout, appBarLayout, n4, -appBarLayout.getDownNestedScrollRange(), 0);
                this.mSkipNestedPreScroll = true;
                return;
            }
            this.mSkipNestedPreScroll = false;
        }
        
        public void onRestoreInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                final SavedState savedState = (SavedState)parcelable;
                super.onRestoreInstanceState(coordinatorLayout, appBarLayout, savedState.getSuperState());
                this.mOffsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
                this.mOffsetToChildIndexOnLayoutPerc = savedState.firstVisibileChildPercentageShown;
                this.mOffsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibileChildAtMinimumHeight;
                return;
            }
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
            this.mOffsetToChildIndexOnLayout = -1;
        }
        
        public Parcelable onSaveInstanceState(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
            final Parcelable onSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, appBarLayout);
            final int topAndBottomOffset = this.getTopAndBottomOffset();
            for (int i = 0; i < appBarLayout.getChildCount(); ++i) {
                final View child = appBarLayout.getChildAt(i);
                final int n = child.getBottom() + topAndBottomOffset;
                if (child.getTop() + topAndBottomOffset <= 0 && n >= 0) {
                    final SavedState savedState = new SavedState(onSaveInstanceState);
                    savedState.firstVisibleChildIndex = i;
                    savedState.firstVisibileChildAtMinimumHeight = (n == ViewCompat.getMinimumHeight(child));
                    savedState.firstVisibileChildPercentageShown = n / child.getHeight();
                    return (Parcelable)savedState;
                }
            }
            return onSaveInstanceState;
        }
        
        public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view, final View view2, final int n) {
            final boolean b = (n & 0x2) != 0x0 && appBarLayout.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= appBarLayout.getHeight();
            if (b && this.mAnimator != null) {
                this.mAnimator.cancel();
            }
            this.mLastNestedScrollingChildRef = null;
            return b;
        }
        
        public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, final View view) {
            if (!this.mWasFlung) {
                this.snapToChildIfNeeded(coordinatorLayout, appBarLayout);
            }
            this.mSkipNestedPreScroll = false;
            this.mWasFlung = false;
            this.mLastNestedScrollingChildRef = new WeakReference<View>(view);
        }
        
        public void setDragCallback(@Nullable final DragCallback mOnDragCallback) {
            this.mOnDragCallback = mOnDragCallback;
        }
        
        @Override
        int setHeaderTopBottomOffset(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int interpolateOffset, int constrain, final int n) {
            final int topBottomOffsetForScrollingSibling = this.getTopBottomOffsetForScrollingSibling();
            int n3;
            final int n2 = n3 = 0;
            if (constrain != 0) {
                n3 = n2;
                if (topBottomOffsetForScrollingSibling >= constrain) {
                    n3 = n2;
                    if (topBottomOffsetForScrollingSibling <= n) {
                        constrain = MathUtils.constrain(interpolateOffset, constrain, n);
                        n3 = n2;
                        if (topBottomOffsetForScrollingSibling != constrain) {
                            if (appBarLayout.hasChildWithInterpolator()) {
                                interpolateOffset = this.interpolateOffset(appBarLayout, constrain);
                            }
                            else {
                                interpolateOffset = constrain;
                            }
                            final boolean setTopAndBottomOffset = this.setTopAndBottomOffset(interpolateOffset);
                            n3 = topBottomOffsetForScrollingSibling - constrain;
                            this.mOffsetDelta = constrain - interpolateOffset;
                            if (!setTopAndBottomOffset && appBarLayout.hasChildWithInterpolator()) {
                                coordinatorLayout.dispatchDependentViewsChanged((View)appBarLayout);
                            }
                            this.dispatchOffsetUpdates(appBarLayout);
                        }
                    }
                }
            }
            return n3;
        }
        
        public abstract static class DragCallback
        {
            public abstract boolean canDrag(@NonNull final AppBarLayout p0);
        }
        
        protected static class SavedState extends View$BaseSavedState
        {
            public static final Parcelable$Creator<SavedState> CREATOR;
            boolean firstVisibileChildAtMinimumHeight;
            float firstVisibileChildPercentageShown;
            int firstVisibleChildIndex;
            
            static {
                CREATOR = ParcelableCompat.newCreator((ParcelableCompatCreatorCallbacks<SavedState>)new ParcelableCompatCreatorCallbacks<SavedState>() {
                    @Override
                    public SavedState createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
                        return new SavedState(parcel, classLoader);
                    }
                    
                    @Override
                    public SavedState[] newArray(final int n) {
                        return new SavedState[n];
                    }
                });
            }
            
            public SavedState(final Parcel parcel, final ClassLoader classLoader) {
                super(parcel);
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibileChildPercentageShown = parcel.readFloat();
                this.firstVisibileChildAtMinimumHeight = (parcel.readByte() != 0);
            }
            
            public SavedState(final Parcelable parcelable) {
                super(parcelable);
            }
            
            public void writeToParcel(final Parcel parcel, int n) {
                super.writeToParcel(parcel, n);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibileChildPercentageShown);
                if (this.firstVisibileChildAtMinimumHeight) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel.writeByte((byte)n);
            }
        }
    }
    
    public static class LayoutParams extends LinearLayout$LayoutParams
    {
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        int mScrollFlags;
        Interpolator mScrollInterpolator;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final int n, final int n2, final float n3) {
            super(n, n2, n3);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mScrollFlags = 1;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.AppBarLayout_LayoutParams);
            this.mScrollFlags = obtainStyledAttributes.getInt(R.styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
            if (obtainStyledAttributes.hasValue(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
                this.mScrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
            }
            obtainStyledAttributes.recycle();
        }
        
        public LayoutParams(final LayoutParams layoutParams) {
            super((LinearLayout$LayoutParams)layoutParams);
            this.mScrollFlags = 1;
            this.mScrollFlags = layoutParams.mScrollFlags;
            this.mScrollInterpolator = layoutParams.mScrollInterpolator;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mScrollFlags = 1;
        }
        
        public LayoutParams(final LinearLayout$LayoutParams linearLayout$LayoutParams) {
            super(linearLayout$LayoutParams);
            this.mScrollFlags = 1;
        }
        
        public int getScrollFlags() {
            return this.mScrollFlags;
        }
        
        public Interpolator getScrollInterpolator() {
            return this.mScrollInterpolator;
        }
        
        public void setScrollFlags(final int mScrollFlags) {
            this.mScrollFlags = mScrollFlags;
        }
        
        public void setScrollInterpolator(final Interpolator mScrollInterpolator) {
            this.mScrollInterpolator = mScrollInterpolator;
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }
    }
    
    public interface OnOffsetChangedListener
    {
        void onOffsetChanged(final AppBarLayout p0, final int p1);
    }
    
    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior
    {
        private int mOverlayTop;
        
        public ScrollingViewBehavior() {
        }
        
        public ScrollingViewBehavior(final Context context, final AttributeSet set) {
            super(context, set);
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.ScrollingViewBehavior_Params);
            this.mOverlayTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Params_behavior_overlapTop, 0);
            obtainStyledAttributes.recycle();
        }
        
        private int getOverlapForOffset(final View view, final int n) {
            if (this.mOverlayTop != 0 && view instanceof AppBarLayout) {
                final AppBarLayout appBarLayout = (AppBarLayout)view;
                final int totalScrollRange = appBarLayout.getTotalScrollRange();
                final int access$200 = appBarLayout.getDownNestedPreScrollRange();
                if (access$200 != 0 && totalScrollRange + n <= access$200) {
                    return 0;
                }
                final int n2 = totalScrollRange - access$200;
                if (n2 != 0) {
                    return MathUtils.constrain(Math.round((1.0f + n / n2) * this.mOverlayTop), 0, this.mOverlayTop);
                }
            }
            return this.mOverlayTop;
        }
        
        private boolean updateOffset(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            final CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams)view2.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                final int topBottomOffsetForScrollingSibling = ((AppBarLayout.Behavior)behavior).getTopBottomOffsetForScrollingSibling();
                this.setTopAndBottomOffset(view2.getHeight() + topBottomOffsetForScrollingSibling - this.getOverlapForOffset(view2, topBottomOffsetForScrollingSibling));
                return true;
            }
            return false;
        }
        
        @Override
        View findFirstDependency(final List<View> list) {
            for (int i = 0; i < list.size(); ++i) {
                final View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return view;
                }
            }
            return null;
        }
        
        public int getOverlayTop() {
            return this.mOverlayTop;
        }
        
        @Override
        int getScrollRange(final View view) {
            if (view instanceof AppBarLayout) {
                return ((AppBarLayout)view).getTotalScrollRange();
            }
            return super.getScrollRange(view);
        }
        
        @Override
        public boolean layoutDependsOn(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            return view2 instanceof AppBarLayout;
        }
        
        @Override
        public boolean onDependentViewChanged(final CoordinatorLayout coordinatorLayout, final View view, final View view2) {
            this.updateOffset(coordinatorLayout, view, view2);
            return false;
        }
        
        @Override
        public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final View view, int n) {
            super.onLayoutChild(coordinatorLayout, view, n);
            List<View> dependencies;
            for (dependencies = coordinatorLayout.getDependencies(view), n = 0; n < dependencies.size() && !this.updateOffset(coordinatorLayout, view, dependencies.get(n)); ++n) {}
            return true;
        }
        
        public void setOverlayTop(final int mOverlayTop) {
            this.mOverlayTop = mOverlayTop;
        }
    }
}
