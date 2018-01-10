// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.c.a;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public class a extends Drawable implements Drawable$Callback
{
    private Drawable a;
    
    public a(final Drawable drawable) {
        this.a(drawable);
    }
    
    public Drawable a() {
        return this.a;
    }
    
    public void a(final Drawable a) {
        if (this.a != null) {
            this.a.setCallback((Drawable$Callback)null);
        }
        if ((this.a = a) != null) {
            a.setCallback((Drawable$Callback)this);
        }
    }
    
    public void draw(final Canvas canvas) {
        this.a.draw(canvas);
    }
    
    public int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }
    
    public Drawable getCurrent() {
        return this.a.getCurrent();
    }
    
    public int getIntrinsicHeight() {
        return this.a.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        return this.a.getIntrinsicWidth();
    }
    
    public int getMinimumHeight() {
        return this.a.getMinimumHeight();
    }
    
    public int getMinimumWidth() {
        return this.a.getMinimumWidth();
    }
    
    public int getOpacity() {
        return this.a.getOpacity();
    }
    
    public boolean getPadding(final Rect rect) {
        return this.a.getPadding(rect);
    }
    
    public int[] getState() {
        return this.a.getState();
    }
    
    public Region getTransparentRegion() {
        return this.a.getTransparentRegion();
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidateSelf();
    }
    
    public boolean isAutoMirrored() {
        return android.support.v4.c.a.a.b(this.a);
    }
    
    public boolean isStateful() {
        return this.a.isStateful();
    }
    
    public void jumpToCurrentState() {
        android.support.v4.c.a.a.a(this.a);
    }
    
    protected void onBoundsChange(final Rect bounds) {
        this.a.setBounds(bounds);
    }
    
    protected boolean onLevelChange(final int level) {
        return this.a.setLevel(level);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        this.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int alpha) {
        this.a.setAlpha(alpha);
    }
    
    public void setAutoMirrored(final boolean b) {
        android.support.v4.c.a.a.a(this.a, b);
    }
    
    public void setChangingConfigurations(final int changingConfigurations) {
        this.a.setChangingConfigurations(changingConfigurations);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }
    
    public void setDither(final boolean dither) {
        this.a.setDither(dither);
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.a.setFilterBitmap(filterBitmap);
    }
    
    public void setHotspot(final float n, final float n2) {
        android.support.v4.c.a.a.a(this.a, n, n2);
    }
    
    public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
        android.support.v4.c.a.a.a(this.a, n, n2, n3, n4);
    }
    
    public boolean setState(final int[] state) {
        return this.a.setState(state);
    }
    
    public void setTint(final int n) {
        android.support.v4.c.a.a.a(this.a, n);
    }
    
    public void setTintList(final ColorStateList list) {
        android.support.v4.c.a.a.a(this.a, list);
    }
    
    public void setTintMode(final PorterDuff$Mode porterDuff$Mode) {
        android.support.v4.c.a.a.a(this.a, porterDuff$Mode);
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        return super.setVisible(b, b2) || this.a.setVisible(b, b2);
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
}
