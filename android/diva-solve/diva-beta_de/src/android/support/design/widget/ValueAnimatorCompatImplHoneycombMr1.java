// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.animation.ValueAnimator;

class ValueAnimatorCompatImplHoneycombMr1 extends Impl
{
    final ValueAnimator mValueAnimator;
    
    ValueAnimatorCompatImplHoneycombMr1() {
        this.mValueAnimator = new ValueAnimator();
    }
    
    public void cancel() {
        this.mValueAnimator.cancel();
    }
    
    public void end() {
        this.mValueAnimator.end();
    }
    
    public float getAnimatedFloatValue() {
        return (float)this.mValueAnimator.getAnimatedValue();
    }
    
    public float getAnimatedFraction() {
        return this.mValueAnimator.getAnimatedFraction();
    }
    
    public int getAnimatedIntValue() {
        return (int)this.mValueAnimator.getAnimatedValue();
    }
    
    public long getDuration() {
        return this.mValueAnimator.getDuration();
    }
    
    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }
    
    public void setDuration(final int n) {
        this.mValueAnimator.setDuration((long)n);
    }
    
    public void setFloatValues(final float n, final float n2) {
        this.mValueAnimator.setFloatValues(new float[] { n, n2 });
    }
    
    public void setIntValues(final int n, final int n2) {
        this.mValueAnimator.setIntValues(new int[] { n, n2 });
    }
    
    public void setInterpolator(final Interpolator interpolator) {
        this.mValueAnimator.setInterpolator((TimeInterpolator)interpolator);
    }
    
    public void setListener(final AnimatorListenerProxy animatorListenerProxy) {
        this.mValueAnimator.addListener((Animator$AnimatorListener)new android.animation.AnimatorListenerAdapter() {
            public void onAnimationCancel(final Animator animator) {
                animatorListenerProxy.onAnimationCancel();
            }
            
            public void onAnimationEnd(final Animator animator) {
                animatorListenerProxy.onAnimationEnd();
            }
            
            public void onAnimationStart(final Animator animator) {
                animatorListenerProxy.onAnimationStart();
            }
        });
    }
    
    public void setUpdateListener(final AnimatorUpdateListenerProxy animatorUpdateListenerProxy) {
        this.mValueAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                animatorUpdateListenerProxy.onAnimationUpdate();
            }
        });
    }
    
    public void start() {
        this.mValueAnimator.start();
    }
}
