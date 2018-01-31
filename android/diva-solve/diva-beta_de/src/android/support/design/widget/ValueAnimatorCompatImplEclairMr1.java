// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.os.SystemClock;
import android.os.Looper;
import android.view.animation.Interpolator;
import android.os.Handler;

class ValueAnimatorCompatImplEclairMr1 extends Impl
{
    private static final int DEFAULT_DURATION = 200;
    private static final int HANDLER_DELAY = 10;
    private static final Handler sHandler;
    private float mAnimatedFraction;
    private int mDuration;
    private final float[] mFloatValues;
    private final int[] mIntValues;
    private Interpolator mInterpolator;
    private boolean mIsRunning;
    private AnimatorListenerProxy mListener;
    private final Runnable mRunnable;
    private long mStartTime;
    private AnimatorUpdateListenerProxy mUpdateListener;
    
    static {
        sHandler = new Handler(Looper.getMainLooper());
    }
    
    ValueAnimatorCompatImplEclairMr1() {
        this.mIntValues = new int[2];
        this.mFloatValues = new float[2];
        this.mDuration = 200;
        this.mRunnable = new Runnable() {
            @Override
            public void run() {
                ValueAnimatorCompatImplEclairMr1.this.update();
            }
        };
    }
    
    private void update() {
        if (this.mIsRunning) {
            float interpolation = (SystemClock.uptimeMillis() - this.mStartTime) / this.mDuration;
            if (this.mInterpolator != null) {
                interpolation = this.mInterpolator.getInterpolation(interpolation);
            }
            this.mAnimatedFraction = interpolation;
            if (this.mUpdateListener != null) {
                this.mUpdateListener.onAnimationUpdate();
            }
            if (SystemClock.uptimeMillis() >= this.mStartTime + this.mDuration) {
                this.mIsRunning = false;
                if (this.mListener != null) {
                    this.mListener.onAnimationEnd();
                }
            }
        }
        if (this.mIsRunning) {
            ValueAnimatorCompatImplEclairMr1.sHandler.postDelayed(this.mRunnable, 10L);
        }
    }
    
    public void cancel() {
        this.mIsRunning = false;
        ValueAnimatorCompatImplEclairMr1.sHandler.removeCallbacks(this.mRunnable);
        if (this.mListener != null) {
            this.mListener.onAnimationCancel();
        }
    }
    
    public void end() {
        if (this.mIsRunning) {
            this.mIsRunning = false;
            ValueAnimatorCompatImplEclairMr1.sHandler.removeCallbacks(this.mRunnable);
            this.mAnimatedFraction = 1.0f;
            if (this.mUpdateListener != null) {
                this.mUpdateListener.onAnimationUpdate();
            }
            if (this.mListener != null) {
                this.mListener.onAnimationEnd();
            }
        }
    }
    
    public float getAnimatedFloatValue() {
        return AnimationUtils.lerp(this.mFloatValues[0], this.mFloatValues[1], this.getAnimatedFraction());
    }
    
    public float getAnimatedFraction() {
        return this.mAnimatedFraction;
    }
    
    public int getAnimatedIntValue() {
        return AnimationUtils.lerp(this.mIntValues[0], this.mIntValues[1], this.getAnimatedFraction());
    }
    
    public long getDuration() {
        return this.mDuration;
    }
    
    public boolean isRunning() {
        return this.mIsRunning;
    }
    
    public void setDuration(final int mDuration) {
        this.mDuration = mDuration;
    }
    
    public void setFloatValues(final float n, final float n2) {
        this.mFloatValues[0] = n;
        this.mFloatValues[1] = n2;
    }
    
    public void setIntValues(final int n, final int n2) {
        this.mIntValues[0] = n;
        this.mIntValues[1] = n2;
    }
    
    public void setInterpolator(final Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }
    
    public void setListener(final AnimatorListenerProxy mListener) {
        this.mListener = mListener;
    }
    
    public void setUpdateListener(final AnimatorUpdateListenerProxy mUpdateListener) {
        this.mUpdateListener = mUpdateListener;
    }
    
    public void start() {
        if (this.mIsRunning) {
            return;
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = (Interpolator)new AccelerateDecelerateInterpolator();
        }
        this.mStartTime = SystemClock.uptimeMillis();
        this.mIsRunning = true;
        if (this.mListener != null) {
            this.mListener.onAnimationStart();
        }
        ValueAnimatorCompatImplEclairMr1.sHandler.postDelayed(this.mRunnable, 10L);
    }
}
