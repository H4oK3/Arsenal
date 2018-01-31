// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.ViewConfiguration;
import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.support.v4.widget.ScrollerCompat;
import android.view.View;

abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V>
{
    private static final int INVALID_POINTER = -1;
    private int mActivePointerId;
    private Runnable mFlingRunnable;
    private boolean mIsBeingDragged;
    private int mLastMotionY;
    private ScrollerCompat mScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    
    public HeaderBehavior() {
        this.mActivePointerId = -1;
        this.mTouchSlop = -1;
    }
    
    public HeaderBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mActivePointerId = -1;
        this.mTouchSlop = -1;
    }
    
    private void ensureVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }
    
    boolean canDragView(final V v) {
        return false;
    }
    
    final boolean fling(final CoordinatorLayout coordinatorLayout, final V v, final int n, final int n2, final float n3) {
        boolean b = false;
        if (this.mFlingRunnable != null) {
            v.removeCallbacks(this.mFlingRunnable);
            this.mFlingRunnable = null;
        }
        if (this.mScroller == null) {
            this.mScroller = ScrollerCompat.create(v.getContext());
        }
        this.mScroller.fling(0, this.getTopAndBottomOffset(), 0, Math.round(n3), 0, 0, n, n2);
        if (this.mScroller.computeScrollOffset()) {
            ViewCompat.postOnAnimation(v, this.mFlingRunnable = new FlingRunnable(coordinatorLayout, v));
            b = true;
        }
        return b;
    }
    
    int getMaxDragOffset(final V v) {
        return -v.getHeight();
    }
    
    int getScrollRangeForDragFling(final V v) {
        return v.getHeight();
    }
    
    int getTopBottomOffsetForScrollingSibling() {
        return this.getTopAndBottomOffset();
    }
    
    @Override
    public boolean onInterceptTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getAction() == 2 && this.mIsBeingDragged) {
            return true;
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0: {
                this.mIsBeingDragged = false;
                final int n = (int)motionEvent.getX();
                final int mLastMotionY = (int)motionEvent.getY();
                if (this.canDragView(v) && coordinatorLayout.isPointInChildBounds(v, n, mLastMotionY)) {
                    this.mLastMotionY = mLastMotionY;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.ensureVelocityTracker();
                    break;
                }
                break;
            }
            case 2: {
                final int mActivePointerId = this.mActivePointerId;
                if (mActivePointerId == -1) {
                    break;
                }
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, mActivePointerId);
                if (pointerIndex == -1) {
                    break;
                }
                final int mLastMotionY2 = (int)MotionEventCompat.getY(motionEvent, pointerIndex);
                if (Math.abs(mLastMotionY2 - this.mLastMotionY) > this.mTouchSlop) {
                    this.mIsBeingDragged = true;
                    this.mLastMotionY = mLastMotionY2;
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            }
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(motionEvent);
        }
        return this.mIsBeingDragged;
    }
    
    @Override
    public boolean onTouchEvent(final CoordinatorLayout coordinatorLayout, final V v, final MotionEvent motionEvent) {
        if (this.mTouchSlop < 0) {
            this.mTouchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0: {
                final int n = (int)motionEvent.getX();
                final int mLastMotionY = (int)motionEvent.getY();
                if (coordinatorLayout.isPointInChildBounds(v, n, mLastMotionY) && this.canDragView(v)) {
                    this.mLastMotionY = mLastMotionY;
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    this.ensureVelocityTracker();
                    break;
                }
                return false;
            }
            case 2: {
                final int pointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (pointerIndex == -1) {
                    return false;
                }
                final int mLastMotionY2 = (int)MotionEventCompat.getY(motionEvent, pointerIndex);
                int n3;
                final int n2 = n3 = this.mLastMotionY - mLastMotionY2;
                if (!this.mIsBeingDragged) {
                    n3 = n2;
                    if (Math.abs(n2) > this.mTouchSlop) {
                        this.mIsBeingDragged = true;
                        if (n2 > 0) {
                            n3 = n2 - this.mTouchSlop;
                        }
                        else {
                            n3 = n2 + this.mTouchSlop;
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    this.mLastMotionY = mLastMotionY2;
                    this.scroll(coordinatorLayout, v, n3, this.getMaxDragOffset(v), 0);
                    break;
                }
                break;
            }
            case 1: {
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    this.fling(coordinatorLayout, v, -this.getScrollRangeForDragFling(v), 0, VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId));
                }
            }
            case 3: {
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                if (this.mVelocityTracker != null) {
                    this.mVelocityTracker.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            }
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(motionEvent);
        }
        return true;
    }
    
    final int scroll(final CoordinatorLayout coordinatorLayout, final V v, final int n, final int n2, final int n3) {
        return this.setHeaderTopBottomOffset(coordinatorLayout, v, this.getTopBottomOffsetForScrollingSibling() - n, n2, n3);
    }
    
    int setHeaderTopBottomOffset(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
        return this.setHeaderTopBottomOffset(coordinatorLayout, v, n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    int setHeaderTopBottomOffset(final CoordinatorLayout coordinatorLayout, final V v, int constrain, final int n, final int n2) {
        final int topAndBottomOffset = this.getTopAndBottomOffset();
        int n4;
        final int n3 = n4 = 0;
        if (n != 0) {
            n4 = n3;
            if (topAndBottomOffset >= n) {
                n4 = n3;
                if (topAndBottomOffset <= n2) {
                    constrain = MathUtils.constrain(constrain, n, n2);
                    n4 = n3;
                    if (topAndBottomOffset != constrain) {
                        this.setTopAndBottomOffset(constrain);
                        n4 = topAndBottomOffset - constrain;
                    }
                }
            }
        }
        return n4;
    }
    
    private class FlingRunnable implements Runnable
    {
        private final V mLayout;
        private final CoordinatorLayout mParent;
        
        FlingRunnable(final CoordinatorLayout mParent, final V mLayout) {
            this.mParent = mParent;
            this.mLayout = mLayout;
        }
        
        @Override
        public void run() {
            if (this.mLayout != null && HeaderBehavior.this.mScroller != null && HeaderBehavior.this.mScroller.computeScrollOffset()) {
                HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.this.mScroller.getCurrY());
                ViewCompat.postOnAnimation(this.mLayout, this);
            }
        }
    }
}
