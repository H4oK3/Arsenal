// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatHoneycomb
{
    public static void jumpToCurrentState(final Drawable drawable) {
        drawable.jumpToCurrentState();
    }
    
    public static Drawable wrapForTinting(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof DrawableWrapperHoneycomb)) {
            drawable2 = new DrawableWrapperHoneycomb(drawable);
        }
        return drawable2;
    }
}
