// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.ColorFilter;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

class DrawableWrapperDonut extends Drawable implements Drawable$Callback, DrawableWrapper
{
    static final PorterDuff$Mode DEFAULT_MODE;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff$Mode mCurrentMode;
    Drawable mDrawable;
    private ColorStateList mTintList;
    private PorterDuff$Mode mTintMode;
    
    static {
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
    }
    
    DrawableWrapperDonut(final Drawable wrappedDrawable) {
        this.mTintMode = DrawableWrapperDonut.DEFAULT_MODE;
        this.setWrappedDrawable(wrappedDrawable);
    }
    
    private boolean updateTint(final int[] array) {
        if (this.mTintList != null && this.mTintMode != null) {
            final int colorForState = this.mTintList.getColorForState(array, this.mTintList.getDefaultColor());
            final PorterDuff$Mode mTintMode = this.mTintMode;
            if (!this.mColorFilterSet || colorForState != this.mCurrentColor || mTintMode != this.mCurrentMode) {
                this.setColorFilter(colorForState, mTintMode);
                this.mCurrentColor = colorForState;
                this.mCurrentMode = mTintMode;
                return this.mColorFilterSet = true;
            }
        }
        else {
            this.mColorFilterSet = false;
            this.clearColorFilter();
        }
        return false;
    }
    
    public void draw(final Canvas canvas) {
        this.mDrawable.draw(canvas);
    }
    
    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }
    
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }
    
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }
    
    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }
    
    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }
    
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }
    
    public boolean getPadding(final Rect rect) {
        return this.mDrawable.getPadding(rect);
    }
    
    public int[] getState() {
        return this.mDrawable.getState();
    }
    
    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }
    
    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidateSelf();
    }
    
    public boolean isStateful() {
        return (this.mTintList != null && this.mTintList.isStateful()) || this.mDrawable.isStateful();
    }
    
    public Drawable mutate() {
        final Drawable mDrawable = this.mDrawable;
        final Drawable mutate = mDrawable.mutate();
        if (mutate != mDrawable) {
            this.setWrappedDrawable(mutate);
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect bounds) {
        this.mDrawable.setBounds(bounds);
    }
    
    protected boolean onLevelChange(final int level) {
        return this.mDrawable.setLevel(level);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        this.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int alpha) {
        this.mDrawable.setAlpha(alpha);
    }
    
    public void setChangingConfigurations(final int changingConfigurations) {
        this.mDrawable.setChangingConfigurations(changingConfigurations);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }
    
    public void setDither(final boolean dither) {
        this.mDrawable.setDither(dither);
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.mDrawable.setFilterBitmap(filterBitmap);
    }
    
    public boolean setState(final int[] state) {
        final boolean setState = this.mDrawable.setState(state);
        return this.updateTint(state) || setState;
    }
    
    public void setTint(final int n) {
        this.setTintList(ColorStateList.valueOf(n));
    }
    
    public void setTintList(final ColorStateList mTintList) {
        this.mTintList = mTintList;
        this.updateTint(this.getState());
    }
    
    public void setTintMode(final PorterDuff$Mode mTintMode) {
        this.mTintMode = mTintMode;
        this.updateTint(this.getState());
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        return super.setVisible(b, b2) || this.mDrawable.setVisible(b, b2);
    }
    
    public void setWrappedDrawable(final Drawable mDrawable) {
        if (this.mDrawable != null) {
            this.mDrawable.setCallback((Drawable$Callback)null);
        }
        if ((this.mDrawable = mDrawable) != null) {
            mDrawable.setCallback((Drawable$Callback)this);
        }
        this.invalidateSelf();
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}
