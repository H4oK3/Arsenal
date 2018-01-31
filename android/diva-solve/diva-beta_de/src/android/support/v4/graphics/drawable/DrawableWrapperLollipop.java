// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.Outline;
import android.graphics.Rect;
import android.content.res.Resources$Theme;
import android.graphics.drawable.Drawable;

class DrawableWrapperLollipop extends DrawableWrapperKitKat
{
    DrawableWrapperLollipop(final Drawable drawable) {
        super(drawable);
    }
    
    public void applyTheme(final Resources$Theme resources$Theme) {
        this.mDrawable.applyTheme(resources$Theme);
    }
    
    public boolean canApplyTheme() {
        return this.mDrawable.canApplyTheme();
    }
    
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }
    
    public void getOutline(final Outline outline) {
        this.mDrawable.getOutline(outline);
    }
    
    public void setHotspot(final float n, final float n2) {
        this.mDrawable.setHotspot(n, n2);
    }
    
    public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
        this.mDrawable.setHotspotBounds(n, n2, n3, n4);
    }
}
