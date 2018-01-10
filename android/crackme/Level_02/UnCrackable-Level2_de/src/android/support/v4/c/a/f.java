// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(19)
class f
{
    public static void a(final Drawable drawable, final boolean autoMirrored) {
        drawable.setAutoMirrored(autoMirrored);
    }
    
    public static boolean a(final Drawable drawable) {
        return drawable.isAutoMirrored();
    }
    
    public static Drawable b(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof m)) {
            drawable2 = new k(drawable);
        }
        return drawable2;
    }
    
    public static int c(final Drawable drawable) {
        return drawable.getAlpha();
    }
}
