// 
// Decompiled by Procyon v0.5.30
// 

package android.support.a.a;

import android.graphics.PorterDuff$Mode;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.ColorFilter;
import android.support.v4.c.a.a;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.support.v4.c.a.m;
import android.graphics.drawable.Drawable;

abstract class f extends Drawable implements m
{
    Drawable b;
    
    protected static TypedArray a(final Resources resources, final Resources$Theme resources$Theme, final AttributeSet set, final int[] array) {
        if (resources$Theme == null) {
            return resources.obtainAttributes(set, array);
        }
        return resources$Theme.obtainStyledAttributes(set, array, 0, 0);
    }
    
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.b != null) {
            a.a(this.b, resources$Theme);
        }
    }
    
    public void clearColorFilter() {
        if (this.b != null) {
            this.b.clearColorFilter();
            return;
        }
        super.clearColorFilter();
    }
    
    public ColorFilter getColorFilter() {
        if (this.b != null) {
            return a.e(this.b);
        }
        return null;
    }
    
    public Drawable getCurrent() {
        if (this.b != null) {
            return this.b.getCurrent();
        }
        return super.getCurrent();
    }
    
    public int getMinimumHeight() {
        if (this.b != null) {
            return this.b.getMinimumHeight();
        }
        return super.getMinimumHeight();
    }
    
    public int getMinimumWidth() {
        if (this.b != null) {
            return this.b.getMinimumWidth();
        }
        return super.getMinimumWidth();
    }
    
    public boolean getPadding(final Rect rect) {
        if (this.b != null) {
            return this.b.getPadding(rect);
        }
        return super.getPadding(rect);
    }
    
    public int[] getState() {
        if (this.b != null) {
            return this.b.getState();
        }
        return super.getState();
    }
    
    public Region getTransparentRegion() {
        if (this.b != null) {
            return this.b.getTransparentRegion();
        }
        return super.getTransparentRegion();
    }
    
    public void jumpToCurrentState() {
        if (this.b != null) {
            a.a(this.b);
        }
    }
    
    protected void onBoundsChange(final Rect bounds) {
        if (this.b != null) {
            this.b.setBounds(bounds);
            return;
        }
        super.onBoundsChange(bounds);
    }
    
    protected boolean onLevelChange(final int level) {
        if (this.b != null) {
            return this.b.setLevel(level);
        }
        return super.onLevelChange(level);
    }
    
    public void setChangingConfigurations(final int n) {
        if (this.b != null) {
            this.b.setChangingConfigurations(n);
            return;
        }
        super.setChangingConfigurations(n);
    }
    
    public void setColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        if (this.b != null) {
            this.b.setColorFilter(n, porterDuff$Mode);
            return;
        }
        super.setColorFilter(n, porterDuff$Mode);
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        if (this.b != null) {
            this.b.setFilterBitmap(filterBitmap);
        }
    }
    
    public void setHotspot(final float n, final float n2) {
        if (this.b != null) {
            a.a(this.b, n, n2);
        }
    }
    
    public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
        if (this.b != null) {
            a.a(this.b, n, n2, n3, n4);
        }
    }
    
    public boolean setState(final int[] array) {
        if (this.b != null) {
            return this.b.setState(array);
        }
        return super.setState(array);
    }
}
