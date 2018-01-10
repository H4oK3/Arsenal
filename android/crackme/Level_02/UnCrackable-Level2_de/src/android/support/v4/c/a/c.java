// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.PorterDuff$Mode;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(9)
class c
{
    public static Drawable a(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof m)) {
            drawable2 = new i(drawable);
        }
        return drawable2;
    }
    
    public static void a(final Drawable drawable, final int tint) {
        if (drawable instanceof m) {
            ((m)drawable).setTint(tint);
        }
    }
    
    public static void a(final Drawable drawable, final ColorStateList tintList) {
        if (drawable instanceof m) {
            ((m)drawable).setTintList(tintList);
        }
    }
    
    public static void a(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        drawable.inflate(resources, xmlPullParser, set);
    }
    
    public static void a(final Drawable drawable, final PorterDuff$Mode tintMode) {
        if (drawable instanceof m) {
            ((m)drawable).setTintMode(tintMode);
        }
    }
}
