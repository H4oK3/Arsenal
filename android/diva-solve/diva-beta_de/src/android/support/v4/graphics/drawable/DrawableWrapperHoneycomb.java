// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableWrapperHoneycomb extends DrawableWrapperDonut
{
    DrawableWrapperHoneycomb(final Drawable drawable) {
        super(drawable);
    }
    
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }
}
