// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Interpolator;

class ValueAnimatorCompat
{
    private final Impl mImpl;
    
    ValueAnimatorCompat(final Impl mImpl) {
        this.mImpl = mImpl;
    }
    
    public void cancel() {
        this.mImpl.cancel();
    }
    
    public void end() {
        this.mImpl.end();
    }
    
    public float getAnimatedFloatValue() {
        return this.mImpl.getAnimatedFloatValue();
    }
    
    public float getAnimatedFraction() {
        return this.mImpl.getAnimatedFraction();
    }
    
    public int getAnimatedIntValue() {
        return this.mImpl.getAnimatedIntValue();
    }
    
    public long getDuration() {
        return this.mImpl.getDuration();
    }
    
    public boolean isRunning() {
        return this.mImpl.isRunning();
    }
    
    public void setDuration(final int duration) {
        this.mImpl.setDuration(duration);
    }
    
    public void setFloatValues(final float n, final float n2) {
        this.mImpl.setFloatValues(n, n2);
    }
    
    public void setIntValues(final int n, final int n2) {
        this.mImpl.setIntValues(n, n2);
    }
    
    public void setInterpolator(final Interpolator interpolator) {
        this.mImpl.setInterpolator(interpolator);
    }
    
    public void setListener(final AnimatorListener animatorListener) {
        if (animatorListener != null) {
            this.mImpl.setListener((AnimatorListenerProxy)new AnimatorListenerProxy() {
                @Override
                public void onAnimationCancel() {
                    animatorListener.onAnimationCancel(ValueAnimatorCompat.this);
                }
                
                @Override
                public void onAnimationEnd() {
                    animatorListener.onAnimationEnd(ValueAnimatorCompat.this);
                }
                
                @Override
                public void onAnimationStart() {
                    animatorListener.onAnimationStart(ValueAnimatorCompat.this);
                }
            });
            return;
        }
        this.mImpl.setListener(null);
    }
    
    public void setUpdateListener(final AnimatorUpdateListener animatorUpdateListener) {
        if (animatorUpdateListener != null) {
            this.mImpl.setUpdateListener((AnimatorUpdateListenerProxy)new AnimatorUpdateListenerProxy() {
                @Override
                public void onAnimationUpdate() {
                    animatorUpdateListener.onAnimationUpdate(ValueAnimatorCompat.this);
                }
            });
            return;
        }
        this.mImpl.setUpdateListener(null);
    }
    
    public void start() {
        this.mImpl.start();
    }
    
    interface AnimatorListener
    {
        void onAnimationCancel(final ValueAnimatorCompat p0);
        
        void onAnimationEnd(final ValueAnimatorCompat p0);
        
        void onAnimationStart(final ValueAnimatorCompat p0);
    }
    
    static class AnimatorListenerAdapter implements AnimatorListener
    {
        @Override
        public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        @Override
        public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        @Override
        public void onAnimationStart(final ValueAnimatorCompat valueAnimatorCompat) {
        }
    }
    
    interface AnimatorUpdateListener
    {
        void onAnimationUpdate(final ValueAnimatorCompat p0);
    }
    
    interface Creator
    {
        ValueAnimatorCompat createAnimator();
    }
    
    abstract static class Impl
    {
        abstract void cancel();
        
        abstract void end();
        
        abstract float getAnimatedFloatValue();
        
        abstract float getAnimatedFraction();
        
        abstract int getAnimatedIntValue();
        
        abstract long getDuration();
        
        abstract boolean isRunning();
        
        abstract void setDuration(final int p0);
        
        abstract void setFloatValues(final float p0, final float p1);
        
        abstract void setIntValues(final int p0, final int p1);
        
        abstract void setInterpolator(final Interpolator p0);
        
        abstract void setListener(final AnimatorListenerProxy p0);
        
        abstract void setUpdateListener(final AnimatorUpdateListenerProxy p0);
        
        abstract void start();
        
        interface AnimatorListenerProxy
        {
            void onAnimationCancel();
            
            void onAnimationEnd();
            
            void onAnimationStart();
        }
        
        interface AnimatorUpdateListenerProxy
        {
            void onAnimationUpdate();
        }
    }
}
