// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(11)
class d
{
    public static void a(final Drawable drawable) {
        drawable.jumpToCurrentState();
    }
    
    public static Drawable b(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof m)) {
            drawable2 = new j(drawable);
        }
        return drawable2;
    }
}
