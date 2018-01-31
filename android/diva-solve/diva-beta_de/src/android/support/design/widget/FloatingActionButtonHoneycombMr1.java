// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.support.v4.view.ViewCompat;
import android.support.annotation.Nullable;
import android.view.View;

class FloatingActionButtonHoneycombMr1 extends FloatingActionButtonEclairMr1
{
    private boolean mIsHiding;
    
    FloatingActionButtonHoneycombMr1(final View view, final ShadowViewDelegate shadowViewDelegate) {
        super(view, shadowViewDelegate);
    }
    
    @Override
    void hide(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener) {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        }
        else {
            if (ViewCompat.isLaidOut(this.mView) && !this.mView.isInEditMode()) {
                this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                    public void onAnimationCancel(final Animator animator) {
                        FloatingActionButtonHoneycombMr1.this.mIsHiding = false;
                    }
                    
                    public void onAnimationEnd(final Animator animator) {
                        FloatingActionButtonHoneycombMr1.this.mIsHiding = false;
                        FloatingActionButtonHoneycombMr1.this.mView.setVisibility(8);
                        if (internalVisibilityChangedListener != null) {
                            internalVisibilityChangedListener.onHidden();
                        }
                    }
                    
                    public void onAnimationStart(final Animator animator) {
                        FloatingActionButtonHoneycombMr1.this.mIsHiding = true;
                        FloatingActionButtonHoneycombMr1.this.mView.setVisibility(0);
                    }
                });
                return;
            }
            this.mView.setVisibility(8);
            if (internalVisibilityChangedListener != null) {
                internalVisibilityChangedListener.onHidden();
            }
        }
    }
    
    @Override
    void show(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener) {
        if (this.mView.getVisibility() != 0) {
            if (ViewCompat.isLaidOut(this.mView) && !this.mView.isInEditMode()) {
                this.mView.setAlpha(0.0f);
                this.mView.setScaleY(0.0f);
                this.mView.setScaleX(0.0f);
                this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                    public void onAnimationEnd(final Animator animator) {
                        if (internalVisibilityChangedListener != null) {
                            internalVisibilityChangedListener.onShown();
                        }
                    }
                    
                    public void onAnimationStart(final Animator animator) {
                        FloatingActionButtonHoneycombMr1.this.mView.setVisibility(0);
                    }
                });
            }
            else {
                this.mView.setVisibility(0);
                this.mView.setAlpha(1.0f);
                this.mView.setScaleY(1.0f);
                this.mView.setScaleX(1.0f);
                if (internalVisibilityChangedListener != null) {
                    internalVisibilityChangedListener.onShown();
                }
            }
        }
    }
}
