// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;

public class SwipeDismissBehavior<V extends View> extends Behavior<V>
{
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private float mAlphaEndSwipeDistance;
    private float mAlphaStartSwipeDistance;
    private final ViewDragHelper.Callback mDragCallback;
    private float mDragDismissThreshold;
    private boolean mIgnoreEvents;
    private OnDismissListener mListener;
    private float mSensitivity;
    private boolean mSensitivitySet;
    private int mSwipeDirection;
    private ViewDragHelper mViewDragHelper;
    
    public SwipeDismissBehavior() {
        this.mSensitivity = 0.0f;
        this.mSwipeDirection = 2;
        this.mDragDismissThreshold = 0.5f;
        this.mAlphaStartSwipeDistance = 0.0f;
        this.mAlphaEndSwipeDistance = 0.5f;
        this.mDragCallback = new ViewDragHelper.Callback() {
            private int mOriginalCapturedViewLeft;
            
            private boolean shouldDismiss(final View view, final float n) {
                if (n != 0.0f) {
                    final boolean b = ViewCompat.getLayoutDirection(view) == 1;
                    if (SwipeDismissBehavior.this.mSwipeDirection != 2) {
                        if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                            if (b) {
                                if (n >= 0.0f) {
                                    return false;
                                }
                            }
                            else if (n <= 0.0f) {
                                return false;
                            }
                        }
                        else {
                            if (SwipeDismissBehavior.this.mSwipeDirection != 1) {
                                return false;
                            }
                            if (b) {
                                if (n <= 0.0f) {
                                    return false;
                                }
                            }
                            else if (n >= 0.0f) {
                                return false;
                            }
                        }
                    }
                }
                else if (Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) < Math.round(view.getWidth() * SwipeDismissBehavior.this.mDragDismissThreshold)) {
                    return false;
                }
                return true;
            }
            
            @Override
            public int clampViewPositionHorizontal(final View view, final int n, int n2) {
                if (ViewCompat.getLayoutDirection(view) == 1) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                int n3;
                if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                    if (n2 != 0) {
                        n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                        n2 = this.mOriginalCapturedViewLeft;
                    }
                    else {
                        n3 = this.mOriginalCapturedViewLeft;
                        n2 = this.mOriginalCapturedViewLeft + view.getWidth();
                    }
                }
                else if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
                    if (n2 != 0) {
                        n3 = this.mOriginalCapturedViewLeft;
                        n2 = this.mOriginalCapturedViewLeft + view.getWidth();
                    }
                    else {
                        n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                        n2 = this.mOriginalCapturedViewLeft;
                    }
                }
                else {
                    n3 = this.mOriginalCapturedViewLeft - view.getWidth();
                    n2 = this.mOriginalCapturedViewLeft + view.getWidth();
                }
                return clamp(n3, n, n2);
            }
            
            @Override
            public int clampViewPositionVertical(final View view, final int n, final int n2) {
                return view.getTop();
            }
            
            @Override
            public int getViewHorizontalDragRange(final View view) {
                return view.getWidth();
            }
            
            @Override
            public void onViewDragStateChanged(final int n) {
                if (SwipeDismissBehavior.this.mListener != null) {
                    SwipeDismissBehavior.this.mListener.onDragStateChanged(n);
                }
            }
            
            @Override
            public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
                final float n5 = this.mOriginalCapturedViewLeft + view.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance;
                final float n6 = this.mOriginalCapturedViewLeft + view.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance;
                if (n <= n5) {
                    ViewCompat.setAlpha(view, 1.0f);
                    return;
                }
                if (n >= n6) {
                    ViewCompat.setAlpha(view, 0.0f);
                    return;
                }
                ViewCompat.setAlpha(view, clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(n5, n6, n), 1.0f));
            }
            
            @Override
            public void onViewReleased(final View view, final float n, final float n2) {
                final int width = view.getWidth();
                boolean b = false;
                int mOriginalCapturedViewLeft;
                if (this.shouldDismiss(view, n)) {
                    if (view.getLeft() < this.mOriginalCapturedViewLeft) {
                        mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft - width;
                    }
                    else {
                        mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft + width;
                    }
                    b = true;
                }
                else {
                    mOriginalCapturedViewLeft = this.mOriginalCapturedViewLeft;
                }
                if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(mOriginalCapturedViewLeft, view.getTop())) {
                    ViewCompat.postOnAnimation(view, new SettleRunnable(view, b));
                }
                else if (b && SwipeDismissBehavior.this.mListener != null) {
                    SwipeDismissBehavior.this.mListener.onDismiss(view);
                }
            }
            
            @Override
            public boolean tryCaptureView(final View view, final int n) {
                this.mOriginalCapturedViewLeft = view.getLeft();
                return true;
            }
        };
    }
    
    private static float clamp(final float n, final float n2, final float n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    private static int clamp(final int n, final int n2, final int n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    private void ensureViewDragHelper(final ViewGroup viewGroup) {
        if (this.mViewDragHelper == null) {
            ViewDragHelper mViewDragHelper;
            if (this.mSensitivitySet) {
                mViewDragHelper = ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback);
            }
            else {
                mViewDragHelper = ViewDragHelper.create(viewGroup, this.mDragCallback);
            }
            this.mViewDragHelper = mViewDragHelper;
        }
    }
    
    static float fraction(final float n, final float n2, final float n3) {
        return (n3 - n) / (n2 - n);
    }
    
    public int getDragState() {
        if (this.mViewDragHelper != null) {
            return this.mViewDragHelper.getViewDragState();
        }
        return 0;
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            default: {
                this.mIgnoreEvents = !coordinatorLayout.isPointInChildBounds(v, (int)motionEvent.getX(), (int)motionEvent.getY());
                break;
            }
            case 1:
            case 3: {
                if (this.mIgnoreEvents) {
                    return this.mIgnoreEvents = false;
                }
                break;
            }
        }
        if (this.mIgnoreEvents) {
            return false;
        }
        this.ensureViewDragHelper(coordinatorLayout);
        return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        if (this.mViewDragHelper != null) {
            this.mViewDragHelper.processTouchEvent(motionEvent);
            return true;
        }
        return false;
    }
    
    public void setDragDismissDistance(final float n) {
        this.mDragDismissThreshold = clamp(0.0f, n, 1.0f);
    }
    
    public void setEndAlphaSwipeDistance(final float n) {
        this.mAlphaEndSwipeDistance = clamp(0.0f, n, 1.0f);
    }
    
    public void setListener(final OnDismissListener mListener) {
        this.mListener = mListener;
    }
    
    public void setSensitivity(final float mSensitivity) {
        this.mSensitivity = mSensitivity;
        this.mSensitivitySet = true;
    }
    
    public void setStartAlphaSwipeDistance(final float n) {
        this.mAlphaStartSwipeDistance = clamp(0.0f, n, 1.0f);
    }
    
    public void setSwipeDirection(final int mSwipeDirection) {
        this.mSwipeDirection = mSwipeDirection;
    }
    
    public interface OnDismissListener
    {
        void onDismiss(final View p0);
        
        void onDragStateChanged(final int p0);
    }
    
    private class SettleRunnable implements Runnable
    {
        private final boolean mDismiss;
        private final View mView;
        
        SettleRunnable(final View mView, final boolean mDismiss) {
            this.mView = mView;
            this.mDismiss = mDismiss;
        }
        
        @Override
        public void run() {
            if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            }
            else if (this.mDismiss && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
            }
        }
    }
}
