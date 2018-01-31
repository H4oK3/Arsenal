// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class DrawableCompatLollipop
{
    public static void setHotspot(final Drawable drawable, final float n, final float n2) {
        drawable.setHotspot(n, n2);
    }
    
    public static void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        drawable.setHotspotBounds(n, n2, n3, n4);
    }
    
    public static void setTint(final Drawable drawable, final int tint) {
        if (drawable instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTint(drawable, tint);
            return;
        }
        drawable.setTint(tint);
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList tintList) {
        if (drawable instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTintList(drawable, tintList);
            return;
        }
        drawable.setTintList(tintList);
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode tintMode) {
        if (drawable instanceof DrawableWrapperLollipop) {
            DrawableCompatBase.setTintMode(drawable, tintMode);
            return;
        }
        drawable.setTintMode(tintMode);
    }
    
    public static Drawable wrapForTinting(final Drawable drawable) {
        if (!(drawable instanceof GradientDrawable)) {
            final Drawable drawable2 = drawable;
            if (!(drawable instanceof DrawableContainer)) {
                return drawable2;
            }
        }
        return new DrawableWrapperLollipop(drawable);
    }
}
