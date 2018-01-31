// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.ColorInt;
import android.view.View$MeasureSpec;
import android.util.Log;
import android.widget.AbsListView;
import android.os.Build$VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.util.DisplayMetrics;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.view.ViewConfiguration;
import android.view.animation.Transformation;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.NestedScrollingChildHelper;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.view.ViewGroup;

public class SwipeRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild
{
    private static final int ALPHA_ANIMATION_DURATION = 300;
    private static final int ANIMATE_TO_START_DURATION = 200;
    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    private static final int CIRCLE_BG_LIGHT = -328966;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    private static final int DEFAULT_CIRCLE_TARGET = 64;
    private static final float DRAG_RATE = 0.5f;
    private static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    private static final int[] LAYOUT_ATTRS;
    private static final String LOG_TAG;
    private static final int MAX_ALPHA = 255;
    private static final float MAX_PROGRESS_ANGLE = 0.8f;
    private static final int SCALE_DOWN_DURATION = 150;
    private static final int STARTING_PROGRESS_ALPHA = 76;
    private int mActivePointerId;
    private Animation mAlphaMaxAnimation;
    private Animation mAlphaStartAnimation;
    private final Animation mAnimateToCorrectPosition;
    private final Animation mAnimateToStartPosition;
    private int mCircleHeight;
    private CircleImageView mCircleView;
    private int mCircleViewIndex;
    private int mCircleWidth;
    private int mCurrentTargetOffsetTop;
    private final DecelerateInterpolator mDecelerateInterpolator;
    protected int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private OnRefreshListener mListener;
    private int mMediumAnimationDuration;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private boolean mNotify;
    private boolean mOriginalOffsetCalculated;
    protected int mOriginalOffsetTop;
    private final int[] mParentScrollConsumed;
    private MaterialProgressDrawable mProgress;
    private Animation$AnimationListener mRefreshListener;
    private boolean mRefreshing;
    private boolean mReturningToStart;
    private boolean mScale;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private Animation mScaleDownToStartAnimation;
    private float mSpinnerFinalOffset;
    private float mStartingScale;
    private View mTarget;
    private float mTotalDragDistance;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    private boolean mUsingCustomStart;
    
    static {
        LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
        LAYOUT_ATTRS = new int[] { 16842766 };
    }
    
    public SwipeRefreshLayout(final Context context) {
        this(context, null);
    }
    
    public SwipeRefreshLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mOriginalOffsetCalculated = false;
        this.mActivePointerId = -1;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = (Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                if (SwipeRefreshLayout.this.mRefreshing) {
                    SwipeRefreshLayout.this.mProgress.setAlpha(255);
                    SwipeRefreshLayout.this.mProgress.start();
                    if (SwipeRefreshLayout.this.mNotify && SwipeRefreshLayout.this.mListener != null) {
                        SwipeRefreshLayout.this.mListener.onRefresh();
                    }
                }
                else {
                    SwipeRefreshLayout.this.mProgress.stop();
                    SwipeRefreshLayout.this.mCircleView.setVisibility(8);
                    SwipeRefreshLayout.this.setColorViewAlpha(255);
                    if (SwipeRefreshLayout.this.mScale) {
                        SwipeRefreshLayout.this.setAnimationProgress(0.0f);
                    }
                    else {
                        SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mOriginalOffsetTop - SwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
                    }
                }
                SwipeRefreshLayout.this.mCurrentTargetOffsetTop = SwipeRefreshLayout.this.mCircleView.getTop();
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        };
        this.mAnimateToCorrectPosition = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                int n2;
                if (!SwipeRefreshLayout.this.mUsingCustomStart) {
                    n2 = (int)(SwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));
                }
                else {
                    n2 = (int)SwipeRefreshLayout.this.mSpinnerFinalOffset;
                }
                SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mFrom + (int)((n2 - SwipeRefreshLayout.this.mFrom) * n) - SwipeRefreshLayout.this.mCircleView.getTop(), false);
                SwipeRefreshLayout.this.mProgress.setArrowScale(1.0f - n);
            }
        };
        this.mAnimateToStartPosition = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                SwipeRefreshLayout.this.moveToStart(n);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = this.getResources().getInteger(17694721);
        this.setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, SwipeRefreshLayout.LAYOUT_ATTRS);
        this.setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        this.mCircleWidth = (int)(displayMetrics.density * 40.0f);
        this.mCircleHeight = (int)(displayMetrics.density * 40.0f);
        this.createProgressView();
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        this.mSpinnerFinalOffset = 64.0f * displayMetrics.density;
        this.mTotalDragDistance = this.mSpinnerFinalOffset;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper((View)this);
        this.setNestedScrollingEnabled(true);
    }
    
    private void animateOffsetToCorrectPosition(final int mFrom, final Animation$AnimationListener animationListener) {
        this.mFrom = mFrom;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator((Interpolator)this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }
    
    private void animateOffsetToStartPosition(final int mFrom, final Animation$AnimationListener animationListener) {
        if (this.mScale) {
            this.startScaleDownReturnToStartAnimation(mFrom, animationListener);
            return;
        }
        this.mFrom = mFrom;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator((Interpolator)this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }
    
    private void createProgressView() {
        this.mCircleView = new CircleImageView(this.getContext(), -328966, 20.0f);
        (this.mProgress = new MaterialProgressDrawable(this.getContext(), (View)this)).setBackgroundColor(-328966);
        this.mCircleView.setImageDrawable((Drawable)this.mProgress);
        this.mCircleView.setVisibility(8);
        this.addView((View)this.mCircleView);
    }
    
    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i = 0; i < this.getChildCount(); ++i) {
                final View child = this.getChildAt(i);
                if (!child.equals(this.mCircleView)) {
                    this.mTarget = child;
                    break;
                }
            }
        }
    }
    
    private void finishSpinner(final float n) {
        if (n > this.mTotalDragDistance) {
            this.setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.setStartEndTrim(0.0f, 0.0f);
        Object o = null;
        if (!this.mScale) {
            o = new Animation$AnimationListener() {
                public void onAnimationEnd(final Animation animation) {
                    if (!SwipeRefreshLayout.this.mScale) {
                        SwipeRefreshLayout.this.startScaleDownAnimation(null);
                    }
                }
                
                public void onAnimationRepeat(final Animation animation) {
                }
                
                public void onAnimationStart(final Animation animation) {
                }
            };
        }
        this.animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, (Animation$AnimationListener)o);
        this.mProgress.showArrow(false);
    }
    
    private float getMotionEventY(final MotionEvent motionEvent, int pointerIndex) {
        pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, pointerIndex);
        if (pointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, pointerIndex);
    }
    
    private boolean isAlphaUsedForScale() {
        return Build$VERSION.SDK_INT < 11;
    }
    
    private boolean isAnimationRunning(final Animation animation) {
        return animation != null && animation.hasStarted() && !animation.hasEnded();
    }
    
    private void moveSpinner(final float n) {
        this.mProgress.showArrow(true);
        final float min = Math.min(1.0f, Math.abs(n / this.mTotalDragDistance));
        final float n2 = (float)Math.max(min - 0.4, 0.0) * 5.0f / 3.0f;
        final float abs = Math.abs(n);
        final float mTotalDragDistance = this.mTotalDragDistance;
        float mSpinnerFinalOffset;
        if (this.mUsingCustomStart) {
            mSpinnerFinalOffset = this.mSpinnerFinalOffset - this.mOriginalOffsetTop;
        }
        else {
            mSpinnerFinalOffset = this.mSpinnerFinalOffset;
        }
        final float max = Math.max(0.0f, Math.min(abs - mTotalDragDistance, 2.0f * mSpinnerFinalOffset) / mSpinnerFinalOffset);
        final float n3 = (float)(max / 4.0f - Math.pow(max / 4.0f, 2.0)) * 2.0f;
        final int mOriginalOffsetTop = this.mOriginalOffsetTop;
        final int n4 = (int)(mSpinnerFinalOffset * min + mSpinnerFinalOffset * n3 * 2.0f);
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if (!this.mScale) {
            ViewCompat.setScaleX((View)this.mCircleView, 1.0f);
            ViewCompat.setScaleY((View)this.mCircleView, 1.0f);
        }
        if (n < this.mTotalDragDistance) {
            if (this.mScale) {
                this.setAnimationProgress(n / this.mTotalDragDistance);
            }
            if (this.mProgress.getAlpha() > 76 && !this.isAnimationRunning(this.mAlphaStartAnimation)) {
                this.startProgressAlphaStartAnimation();
            }
            this.mProgress.setStartEndTrim(0.0f, Math.min(0.8f, n2 * 0.8f));
            this.mProgress.setArrowScale(Math.min(1.0f, n2));
        }
        else if (this.mProgress.getAlpha() < 255 && !this.isAnimationRunning(this.mAlphaMaxAnimation)) {
            this.startProgressAlphaMaxAnimation();
        }
        this.mProgress.setProgressRotation((-0.25f + 0.4f * n2 + 2.0f * n3) * 0.5f);
        this.setTargetOffsetTopAndBottom(mOriginalOffsetTop + n4 - this.mCurrentTargetOffsetTop, true);
    }
    
    private void moveToStart(final float n) {
        this.setTargetOffsetTopAndBottom(this.mFrom + (int)((this.mOriginalOffsetTop - this.mFrom) * n) - this.mCircleView.getTop(), false);
    }
    
    private void onSecondaryPointerUp(final MotionEvent motionEvent) {
        final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            int n;
            if (actionIndex == 0) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, n);
        }
    }
    
    private void setAnimationProgress(final float n) {
        if (this.isAlphaUsedForScale()) {
            this.setColorViewAlpha((int)(255.0f * n));
            return;
        }
        ViewCompat.setScaleX((View)this.mCircleView, n);
        ViewCompat.setScaleY((View)this.mCircleView, n);
    }
    
    private void setColorViewAlpha(final int n) {
        this.mCircleView.getBackground().setAlpha(n);
        this.mProgress.setAlpha(n);
    }
    
    private void setRefreshing(final boolean mRefreshing, final boolean mNotify) {
        if (this.mRefreshing != mRefreshing) {
            this.mNotify = mNotify;
            this.ensureTarget();
            this.mRefreshing = mRefreshing;
            if (!this.mRefreshing) {
                this.startScaleDownAnimation(this.mRefreshListener);
                return;
            }
            this.animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
        }
    }
    
    private void setTargetOffsetTopAndBottom(final int n, final boolean b) {
        this.mCircleView.bringToFront();
        this.mCircleView.offsetTopAndBottom(n);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
        if (b && Build$VERSION.SDK_INT < 11) {
            this.invalidate();
        }
    }
    
    private Animation startAlphaAnimation(final int n, final int n2) {
        if (this.mScale && this.isAlphaUsedForScale()) {
            return null;
        }
        final Animation animation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                SwipeRefreshLayout.this.mProgress.setAlpha((int)(n + (n2 - n) * n));
            }
        };
        animation.setDuration(300L);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation((Animation)animation);
        return animation;
    }
    
    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 255);
    }
    
    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = this.startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }
    
    private void startScaleDownAnimation(final Animation$AnimationListener animationListener) {
        (this.mScaleDownAnimation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(1.0f - n);
            }
        }).setDuration(150L);
        this.mCircleView.setAnimationListener(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }
    
    private void startScaleDownReturnToStartAnimation(final int mFrom, final Animation$AnimationListener animationListener) {
        this.mFrom = mFrom;
        if (this.isAlphaUsedForScale()) {
            this.mStartingScale = this.mProgress.getAlpha();
        }
        else {
            this.mStartingScale = ViewCompat.getScaleX((View)this.mCircleView);
        }
        (this.mScaleDownToStartAnimation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(SwipeRefreshLayout.this.mStartingScale + -SwipeRefreshLayout.this.mStartingScale * n);
                SwipeRefreshLayout.this.moveToStart(n);
            }
        }).setDuration(150L);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }
    
    private void startScaleUpAnimation(final Animation$AnimationListener animationListener) {
        this.mCircleView.setVisibility(0);
        if (Build$VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(255);
        }
        (this.mScaleAnimation = new Animation() {
            public void applyTransformation(final float n, final Transformation transformation) {
                SwipeRefreshLayout.this.setAnimationProgress(n);
            }
        }).setDuration((long)this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }
    
    public boolean canChildScrollUp() {
        boolean b = false;
        if (Build$VERSION.SDK_INT >= 14) {
            return ViewCompat.canScrollVertically(this.mTarget, -1);
        }
        if (this.mTarget instanceof AbsListView) {
            final AbsListView absListView = (AbsListView)this.mTarget;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        }
        if (ViewCompat.canScrollVertically(this.mTarget, -1) || this.mTarget.getScrollY() > 0) {
            b = true;
        }
        return b;
    }
    
    public boolean dispatchNestedFling(final float n, final float n2, final boolean b) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(n, n2, b);
    }
    
    public boolean dispatchNestedPreFling(final float n, final float n2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(n, n2);
    }
    
    public boolean dispatchNestedPreScroll(final int n, final int n2, final int[] array, final int[] array2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    public boolean dispatchNestedScroll(final int n, final int n2, final int n3, final int n4, final int[] array) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    protected int getChildDrawingOrder(final int n, final int n2) {
        if (this.mCircleViewIndex >= 0) {
            if (n2 == n - 1) {
                return this.mCircleViewIndex;
            }
            if (n2 >= this.mCircleViewIndex) {
                return n2 + 1;
            }
        }
        return n2;
    }
    
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }
    
    public int getProgressCircleDiameter() {
        if (this.mCircleView != null) {
            return this.mCircleView.getMeasuredHeight();
        }
        return 0;
    }
    
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }
    
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }
    
    public boolean isRefreshing() {
        return this.mRefreshing;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.ensureTarget();
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing) {
            switch (actionMasked) {
                case 0: {
                    this.setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.mIsBeingDragged = false;
                    final float motionEventY = this.getMotionEventY(motionEvent, this.mActivePointerId);
                    if (motionEventY != -1.0f) {
                        this.mInitialDownY = motionEventY;
                        break;
                    }
                    return false;
                }
                case 2: {
                    if (this.mActivePointerId == -1) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                        return false;
                    }
                    final float motionEventY2 = this.getMotionEventY(motionEvent, this.mActivePointerId);
                    if (motionEventY2 == -1.0f) {
                        return false;
                    }
                    if (motionEventY2 - this.mInitialDownY > this.mTouchSlop && !this.mIsBeingDragged) {
                        this.mInitialMotionY = this.mInitialDownY + this.mTouchSlop;
                        this.mIsBeingDragged = true;
                        this.mProgress.setAlpha(76);
                        break;
                    }
                    break;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent);
                    break;
                }
                case 1:
                case 3: {
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    break;
                }
            }
            return this.mIsBeingDragged;
        }
        return false;
    }
    
    protected void onLayout(final boolean b, int measuredWidth, int n, int n2, int paddingTop) {
        measuredWidth = this.getMeasuredWidth();
        n = this.getMeasuredHeight();
        if (this.getChildCount() != 0) {
            if (this.mTarget == null) {
                this.ensureTarget();
            }
            if (this.mTarget != null) {
                final View mTarget = this.mTarget;
                n2 = this.getPaddingLeft();
                paddingTop = this.getPaddingTop();
                mTarget.layout(n2, paddingTop, n2 + (measuredWidth - this.getPaddingLeft() - this.getPaddingRight()), paddingTop + (n - this.getPaddingTop() - this.getPaddingBottom()));
                n = this.mCircleView.getMeasuredWidth();
                n2 = this.mCircleView.getMeasuredHeight();
                this.mCircleView.layout(measuredWidth / 2 - n / 2, this.mCurrentTargetOffsetTop, measuredWidth / 2 + n / 2, this.mCurrentTargetOffsetTop + n2);
            }
        }
    }
    
    public void onMeasure(int i, final int n) {
        super.onMeasure(i, n);
        if (this.mTarget == null) {
            this.ensureTarget();
        }
        if (this.mTarget != null) {
            this.mTarget.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
            this.mCircleView.measure(View$MeasureSpec.makeMeasureSpec(this.mCircleWidth, 1073741824), View$MeasureSpec.makeMeasureSpec(this.mCircleHeight, 1073741824));
            if (!this.mUsingCustomStart && !this.mOriginalOffsetCalculated) {
                this.mOriginalOffsetCalculated = true;
                i = -this.mCircleView.getMeasuredHeight();
                this.mOriginalOffsetTop = i;
                this.mCurrentTargetOffsetTop = i;
            }
            this.mCircleViewIndex = -1;
            for (i = 0; i < this.getChildCount(); ++i) {
                if (this.getChildAt(i) == this.mCircleView) {
                    this.mCircleViewIndex = i;
                    return;
                }
            }
        }
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        return this.dispatchNestedFling(n, n2, b);
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        return this.dispatchNestedPreFling(n, n2);
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
        if (n2 > 0 && this.mTotalUnconsumed > 0.0f) {
            if (n2 > this.mTotalUnconsumed) {
                array[1] = n2 - (int)this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0.0f;
            }
            else {
                this.mTotalUnconsumed -= n2;
                array[1] = n2;
            }
            this.moveSpinner(this.mTotalUnconsumed);
        }
        if (this.mUsingCustomStart && n2 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(n2 - array[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        final int[] mParentScrollConsumed = this.mParentScrollConsumed;
        if (this.dispatchNestedPreScroll(n - array[0], n2 - array[1], mParentScrollConsumed, null)) {
            array[0] += mParentScrollConsumed[0];
            array[1] += mParentScrollConsumed[1];
        }
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, int abs) {
        if (abs < 0) {
            abs = Math.abs(abs);
            this.moveSpinner(this.mTotalUnconsumed += abs);
        }
        this.dispatchNestedScroll(n, n2, n3, n, null);
    }
    
    public void onNestedScrollAccepted(final View view, final View view2, final int n) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, n);
        this.mTotalUnconsumed = 0.0f;
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        if (this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp() && !this.mRefreshing && (n & 0x2) != 0x0) {
            this.startNestedScroll(n & 0x2);
            return true;
        }
        return false;
    }
    
    public void onStopNestedScroll(final View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        if (this.mTotalUnconsumed > 0.0f) {
            this.finishSpinner(this.mTotalUnconsumed);
            this.mTotalUnconsumed = 0.0f;
        }
        this.stopNestedScroll();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (this.mReturningToStart && actionMasked == 0) {
            this.mReturningToStart = false;
        }
        if (this.isEnabled() && !this.mReturningToStart && !this.canChildScrollUp()) {
            switch (actionMasked) {
                case 3: {
                    return false;
                }
                case 0: {
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.mIsBeingDragged = false;
                    break;
                }
                case 2: {
                    final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                    if (pointerIndex < 0) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    final float n = (MotionEventCompat.getY(motionEvent, pointerIndex) - this.mInitialMotionY) * 0.5f;
                    if (!this.mIsBeingDragged) {
                        break;
                    }
                    if (n > 0.0f) {
                        this.moveSpinner(n);
                        break;
                    }
                    return false;
                }
                case 5: {
                    final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                    if (actionIndex < 0) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    break;
                }
                case 6: {
                    this.onSecondaryPointerUp(motionEvent);
                    break;
                }
                case 1: {
                    final int pointerIndex2 = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                    if (pointerIndex2 < 0) {
                        Log.e(SwipeRefreshLayout.LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                        return false;
                    }
                    final float y = MotionEventCompat.getY(motionEvent, pointerIndex2);
                    final float mInitialMotionY = this.mInitialMotionY;
                    this.mIsBeingDragged = false;
                    this.finishSpinner((y - mInitialMotionY) * 0.5f);
                    this.mActivePointerId = -1;
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        if ((Build$VERSION.SDK_INT < 21 && this.mTarget instanceof AbsListView) || (this.mTarget != null && !ViewCompat.isNestedScrollingEnabled(this.mTarget))) {
            return;
        }
        super.requestDisallowInterceptTouchEvent(b);
    }
    
    @Deprecated
    public void setColorScheme(@ColorInt final int... colorSchemeResources) {
        this.setColorSchemeResources(colorSchemeResources);
    }
    
    @ColorInt
    public void setColorSchemeColors(final int... colorSchemeColors) {
        this.ensureTarget();
        this.mProgress.setColorSchemeColors(colorSchemeColors);
    }
    
    public void setColorSchemeResources(@ColorRes final int... array) {
        final Resources resources = this.getResources();
        final int[] colorSchemeColors = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            colorSchemeColors[i] = resources.getColor(array[i]);
        }
        this.setColorSchemeColors(colorSchemeColors);
    }
    
    public void setDistanceToTriggerSync(final int n) {
        this.mTotalDragDistance = n;
    }
    
    public void setNestedScrollingEnabled(final boolean nestedScrollingEnabled) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    
    public void setOnRefreshListener(final OnRefreshListener mListener) {
        this.mListener = mListener;
    }
    
    @Deprecated
    public void setProgressBackgroundColor(final int progressBackgroundColorSchemeResource) {
        this.setProgressBackgroundColorSchemeResource(progressBackgroundColorSchemeResource);
    }
    
    public void setProgressBackgroundColorSchemeColor(@ColorInt final int n) {
        this.mCircleView.setBackgroundColor(n);
        this.mProgress.setBackgroundColor(n);
    }
    
    public void setProgressBackgroundColorSchemeResource(@ColorRes final int n) {
        this.setProgressBackgroundColorSchemeColor(this.getResources().getColor(n));
    }
    
    public void setProgressViewEndTarget(final boolean mScale, final int n) {
        this.mSpinnerFinalOffset = n;
        this.mScale = mScale;
        this.mCircleView.invalidate();
    }
    
    public void setProgressViewOffset(final boolean mScale, final int n, final int n2) {
        this.mScale = mScale;
        this.mCircleView.setVisibility(8);
        this.mCurrentTargetOffsetTop = n;
        this.mOriginalOffsetTop = n;
        this.mSpinnerFinalOffset = n2;
        this.mUsingCustomStart = true;
        this.mCircleView.invalidate();
    }
    
    public void setRefreshing(final boolean mRefreshing) {
        if (mRefreshing && this.mRefreshing != mRefreshing) {
            this.mRefreshing = mRefreshing;
            int n;
            if (!this.mUsingCustomStart) {
                n = (int)(this.mSpinnerFinalOffset + this.mOriginalOffsetTop);
            }
            else {
                n = (int)this.mSpinnerFinalOffset;
            }
            this.setTargetOffsetTopAndBottom(n - this.mCurrentTargetOffsetTop, true);
            this.mNotify = false;
            this.startScaleUpAnimation(this.mRefreshListener);
            return;
        }
        this.setRefreshing(mRefreshing, false);
    }
    
    public void setSize(final int n) {
        if (n != 0 && n != 1) {
            return;
        }
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        if (n == 0) {
            final int n2 = (int)(56.0f * displayMetrics.density);
            this.mCircleWidth = n2;
            this.mCircleHeight = n2;
        }
        else {
            final int n3 = (int)(40.0f * displayMetrics.density);
            this.mCircleWidth = n3;
            this.mCircleHeight = n3;
        }
        this.mCircleView.setImageDrawable((Drawable)null);
        this.mProgress.updateSizes(n);
        this.mCircleView.setImageDrawable((Drawable)this.mProgress);
    }
    
    public boolean startNestedScroll(final int n) {
        return this.mNestedScrollingChildHelper.startNestedScroll(n);
    }
    
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
    
    public interface OnRefreshListener
    {
        void onRefresh();
    }
}
