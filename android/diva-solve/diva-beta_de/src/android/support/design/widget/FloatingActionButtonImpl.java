// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.PorterDuff$Mode;
import android.support.annotation.Nullable;
import android.content.res.Resources;
import android.support.design.R;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.view.View;

abstract class FloatingActionButtonImpl
{
    static final int[] EMPTY_STATE_SET;
    static final int[] FOCUSED_ENABLED_STATE_SET;
    static final int[] PRESSED_ENABLED_STATE_SET;
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    final ShadowViewDelegate mShadowViewDelegate;
    final View mView;
    
    static {
        PRESSED_ENABLED_STATE_SET = new int[] { 16842919, 16842910 };
        FOCUSED_ENABLED_STATE_SET = new int[] { 16842908, 16842910 };
        EMPTY_STATE_SET = new int[0];
    }
    
    FloatingActionButtonImpl(final View mView, final ShadowViewDelegate mShadowViewDelegate) {
        this.mView = mView;
        this.mShadowViewDelegate = mShadowViewDelegate;
    }
    
    Drawable createBorderDrawable(final int n, final ColorStateList list) {
        final Resources resources = this.mView.getResources();
        final CircularBorderDrawable circularDrawable = this.newCircularDrawable();
        circularDrawable.setGradientColors(resources.getColor(R.color.design_fab_stroke_top_outer_color), resources.getColor(R.color.design_fab_stroke_top_inner_color), resources.getColor(R.color.design_fab_stroke_end_inner_color), resources.getColor(R.color.design_fab_stroke_end_outer_color));
        circularDrawable.setBorderWidth(n);
        circularDrawable.setTintColor(list.getDefaultColor());
        return circularDrawable;
    }
    
    abstract void hide(@Nullable final InternalVisibilityChangedListener p0);
    
    abstract void jumpDrawableToCurrentState();
    
    CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawable();
    }
    
    abstract void onDrawableStateChanged(final int[] p0);
    
    abstract void setBackgroundDrawable(final Drawable p0, final ColorStateList p1, final PorterDuff$Mode p2, final int p3, final int p4);
    
    abstract void setBackgroundTintList(final ColorStateList p0);
    
    abstract void setBackgroundTintMode(final PorterDuff$Mode p0);
    
    abstract void setElevation(final float p0);
    
    abstract void setPressedTranslationZ(final float p0);
    
    abstract void setRippleColor(final int p0);
    
    abstract void show(@Nullable final InternalVisibilityChangedListener p0);
    
    interface InternalVisibilityChangedListener
    {
        void onHidden();
        
        void onShown();
    }
}
