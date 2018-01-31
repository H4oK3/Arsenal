// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.StateSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

final class StateListAnimator
{
    private Animation$AnimationListener mAnimationListener;
    private Tuple mLastMatch;
    private Animation mRunningAnimation;
    private final ArrayList<Tuple> mTuples;
    private WeakReference<View> mViewRef;
    
    StateListAnimator() {
        this.mTuples = new ArrayList<Tuple>();
        this.mLastMatch = null;
        this.mRunningAnimation = null;
        this.mAnimationListener = (Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                if (StateListAnimator.this.mRunningAnimation == animation) {
                    StateListAnimator.this.mRunningAnimation = null;
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        };
    }
    
    private void cancel() {
        if (this.mRunningAnimation != null) {
            final View target = this.getTarget();
            if (target != null && target.getAnimation() == this.mRunningAnimation) {
                target.clearAnimation();
            }
            this.mRunningAnimation = null;
        }
    }
    
    private void clearTarget() {
        final View target = this.getTarget();
        for (int size = this.mTuples.size(), i = 0; i < size; ++i) {
            if (target.getAnimation() == this.mTuples.get(i).mAnimation) {
                target.clearAnimation();
            }
        }
        this.mViewRef = null;
        this.mLastMatch = null;
        this.mRunningAnimation = null;
    }
    
    private void start(final Tuple tuple) {
        this.mRunningAnimation = tuple.mAnimation;
        final View target = this.getTarget();
        if (target != null) {
            target.startAnimation(this.mRunningAnimation);
        }
    }
    
    public void addState(final int[] array, final Animation animation) {
        final Tuple tuple = new Tuple(array, animation);
        animation.setAnimationListener(this.mAnimationListener);
        this.mTuples.add(tuple);
    }
    
    Animation getRunningAnimation() {
        return this.mRunningAnimation;
    }
    
    View getTarget() {
        if (this.mViewRef == null) {
            return null;
        }
        return this.mViewRef.get();
    }
    
    ArrayList<Tuple> getTuples() {
        return this.mTuples;
    }
    
    public void jumpToCurrentState() {
        if (this.mRunningAnimation != null) {
            final View target = this.getTarget();
            if (target != null && target.getAnimation() == this.mRunningAnimation) {
                target.clearAnimation();
            }
        }
    }
    
    void setState(final int[] array) {
        final Tuple tuple = null;
        final int size = this.mTuples.size();
        int n = 0;
        Tuple mLastMatch;
        while (true) {
            mLastMatch = tuple;
            if (n >= size) {
                break;
            }
            mLastMatch = this.mTuples.get(n);
            if (StateSet.stateSetMatches(mLastMatch.mSpecs, array)) {
                break;
            }
            ++n;
        }
        if (mLastMatch != this.mLastMatch) {
            if (this.mLastMatch != null) {
                this.cancel();
            }
            this.mLastMatch = mLastMatch;
            final View view = this.mViewRef.get();
            if (mLastMatch != null && view != null && view.getVisibility() == 0) {
                this.start(mLastMatch);
            }
        }
    }
    
    void setTarget(final View view) {
        final View target = this.getTarget();
        if (target != view) {
            if (target != null) {
                this.clearTarget();
            }
            if (view != null) {
                this.mViewRef = new WeakReference<View>(view);
            }
        }
    }
    
    static class Tuple
    {
        final Animation mAnimation;
        final int[] mSpecs;
        
        private Tuple(final int[] mSpecs, final Animation mAnimation) {
            this.mSpecs = mSpecs;
            this.mAnimation = mAnimation;
        }
        
        Animation getAnimation() {
            return this.mAnimation;
        }
        
        int[] getSpecs() {
            return this.mSpecs;
        }
    }
}
