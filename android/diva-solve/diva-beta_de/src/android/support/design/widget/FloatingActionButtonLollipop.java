// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.support.v4.view.ViewCompat;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.animation.TimeInterpolator;
import android.animation.Animator;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.graphics.drawable.RippleDrawable;
import android.view.animation.Interpolator;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(21)
class FloatingActionButtonLollipop extends FloatingActionButtonHoneycombMr1
{
    private Drawable mBorderDrawable;
    private Interpolator mInterpolator;
    private RippleDrawable mRippleDrawable;
    private Drawable mShapeDrawable;
    
    FloatingActionButtonLollipop(final View view, final ShadowViewDelegate shadowViewDelegate) {
        super(view, shadowViewDelegate);
        if (!view.isInEditMode()) {
            this.mInterpolator = AnimationUtils.loadInterpolator(this.mView.getContext(), 17563661);
        }
    }
    
    private Animator setupAnimator(final Animator animator) {
        animator.setInterpolator((TimeInterpolator)this.mInterpolator);
        return animator;
    }
    
    @Override
    void jumpDrawableToCurrentState() {
    }
    
    @Override
    CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawableLollipop();
    }
    
    @Override
    void onDrawableStateChanged(final int[] array) {
    }
    
    @Override
    void setBackgroundDrawable(Drawable mShapeDrawable, final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int n, final int n2) {
        DrawableCompat.setTintList(this.mShapeDrawable = DrawableCompat.wrap(mShapeDrawable.mutate()), list);
        if (porterDuff$Mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
        if (n2 > 0) {
            this.mBorderDrawable = this.createBorderDrawable(n2, list);
            mShapeDrawable = (Drawable)new LayerDrawable(new Drawable[] { this.mBorderDrawable, this.mShapeDrawable });
        }
        else {
            this.mBorderDrawable = null;
            mShapeDrawable = this.mShapeDrawable;
        }
        this.mRippleDrawable = new RippleDrawable(ColorStateList.valueOf(n), mShapeDrawable, (Drawable)null);
        this.mShadowViewDelegate.setBackgroundDrawable((Drawable)this.mRippleDrawable);
        this.mShadowViewDelegate.setShadowPadding(0, 0, 0, 0);
    }
    
    @Override
    void setBackgroundTintList(final ColorStateList list) {
        DrawableCompat.setTintList(this.mShapeDrawable, list);
        if (this.mBorderDrawable != null) {
            DrawableCompat.setTintList(this.mBorderDrawable, list);
        }
    }
    
    @Override
    void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
    }
    
    public void setElevation(final float n) {
        ViewCompat.setElevation(this.mView, n);
    }
    
    @Override
    void setPressedTranslationZ(final float n) {
        final StateListAnimator stateListAnimator = new StateListAnimator();
        stateListAnimator.addState(FloatingActionButtonLollipop.PRESSED_ENABLED_STATE_SET, this.setupAnimator((Animator)ObjectAnimator.ofFloat((Object)this.mView, "translationZ", new float[] { n })));
        stateListAnimator.addState(FloatingActionButtonLollipop.FOCUSED_ENABLED_STATE_SET, this.setupAnimator((Animator)ObjectAnimator.ofFloat((Object)this.mView, "translationZ", new float[] { n })));
        stateListAnimator.addState(FloatingActionButtonLollipop.EMPTY_STATE_SET, this.setupAnimator((Animator)ObjectAnimator.ofFloat((Object)this.mView, "translationZ", new float[] { 0.0f })));
        this.mView.setStateListAnimator(stateListAnimator);
    }
    
    @Override
    void setRippleColor(final int n) {
        this.mRippleDrawable.setColor(ColorStateList.valueOf(n));
    }
}
