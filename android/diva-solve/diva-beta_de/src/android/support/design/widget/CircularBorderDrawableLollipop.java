// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Outline;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.content.res.ColorStateList;

class CircularBorderDrawableLollipop extends CircularBorderDrawable
{
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff$Mode mTintMode;
    
    CircularBorderDrawableLollipop() {
        this.mTintMode = PorterDuff$Mode.SRC_IN;
    }
    
    private PorterDuffColorFilter updateTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(list.getColorForState(this.getState(), 0), porterDuff$Mode);
    }
    
    @Override
    public void draw(final Canvas canvas) {
        int n;
        if (this.mTintFilter != null && this.mPaint.getColorFilter() == null) {
            this.mPaint.setColorFilter((ColorFilter)this.mTintFilter);
            n = 1;
        }
        else {
            n = 0;
        }
        super.draw(canvas);
        if (n != 0) {
            this.mPaint.setColorFilter((ColorFilter)null);
        }
    }
    
    public void getOutline(final Outline outline) {
        this.copyBounds(this.mRect);
        outline.setOval(this.mRect);
    }
    
    public void setTintList(final ColorStateList mTint) {
        this.mTint = mTint;
        this.mTintFilter = this.updateTintFilter(mTint, this.mTintMode);
        this.invalidateSelf();
    }
    
    public void setTintMode(final PorterDuff$Mode mTintMode) {
        this.mTintMode = mTintMode;
        this.mTintFilter = this.updateTintFilter(this.mTint, mTintMode);
        this.invalidateSelf();
    }
}
