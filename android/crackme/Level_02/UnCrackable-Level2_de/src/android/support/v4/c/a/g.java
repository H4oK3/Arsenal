// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.content.res.Resources$Theme;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(21)
class g
{
    public static Drawable a(final Drawable drawable) {
        Drawable drawable2 = drawable;
        if (!(drawable instanceof m)) {
            drawable2 = new l(drawable);
        }
        return drawable2;
    }
    
    public static void a(final Drawable drawable, final float n, final float n2) {
        drawable.setHotspot(n, n2);
    }
    
    public static void a(final Drawable drawable, final int tint) {
        drawable.setTint(tint);
    }
    
    public static void a(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        drawable.setHotspotBounds(n, n2, n3, n4);
    }
    
    public static void a(final Drawable drawable, final ColorStateList tintList) {
        drawable.setTintList(tintList);
    }
    
    public static void a(final Drawable drawable, final Resources$Theme resources$Theme) {
        drawable.applyTheme(resources$Theme);
    }
    
    public static void a(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        drawable.inflate(resources, xmlPullParser, set, resources$Theme);
    }
    
    public static void a(final Drawable drawable, final PorterDuff$Mode tintMode) {
        drawable.setTintMode(tintMode);
    }
    
    public static boolean b(final Drawable drawable) {
        return drawable.canApplyTheme();
    }
    
    public static ColorFilter c(final Drawable drawable) {
        return drawable.getColorFilter();
    }
}
