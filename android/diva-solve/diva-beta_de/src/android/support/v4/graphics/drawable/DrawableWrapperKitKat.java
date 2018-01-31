// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableWrapperKitKat extends DrawableWrapperHoneycomb
{
    DrawableWrapperKitKat(final Drawable drawable) {
        super(drawable);
    }
    
    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }
    
    public void setAutoMirrored(final boolean autoMirrored) {
        this.mDrawable.setAutoMirrored(autoMirrored);
    }
}
