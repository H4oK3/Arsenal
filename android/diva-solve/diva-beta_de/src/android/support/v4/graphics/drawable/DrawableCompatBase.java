// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class DrawableCompatBase
{
    public static void setTint(final Drawable drawable, final int tint) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable).setTint(tint);
        }
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList tintList) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable).setTintList(tintList);
        }
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode tintMode) {
        if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper)drawable).setTintMode(tintMode);
        }
    }
    
    public static Drawable wrapForTinting(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof DrawableWrapperDonut)) {
            drawable2 = new DrawableWrapperDonut(drawable);
        }
        return drawable2;
    }
}
