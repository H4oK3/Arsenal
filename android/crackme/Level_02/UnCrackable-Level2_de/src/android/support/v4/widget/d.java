// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import java.lang.reflect.Field;
import android.annotation.TargetApi;

@TargetApi(9)
class d
{
    private static Field a;
    private static boolean b;
    
    static Drawable a(final CompoundButton compoundButton) {
        while (true) {
            if (!d.b) {
                while (true) {
                    try {
                        (d.a = CompoundButton.class.getDeclaredField("mButtonDrawable")).setAccessible(true);
                        d.b = true;
                        if (d.a != null) {
                            final Field field = d.a;
                            final CompoundButton compoundButton2 = compoundButton;
                            final Object o = field.get(compoundButton2);
                            final Drawable drawable = (Drawable)o;
                            return drawable;
                        }
                        return null;
                    }
                    catch (NoSuchFieldException ex) {
                        Log.i("CompoundButtonCompatGingerbread", "Failed to retrieve mButtonDrawable field", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                try {
                    final Field field = d.a;
                    final CompoundButton compoundButton2 = compoundButton;
                    final Object o = field.get(compoundButton2);
                    final Drawable drawable2;
                    final Drawable drawable = drawable2 = (Drawable)o;
                    return drawable2;
                }
                catch (IllegalAccessException ex2) {
                    Log.i("CompoundButtonCompatGingerbread", "Failed to get button drawable via reflection", (Throwable)ex2);
                    d.a = null;
                }
                return null;
            }
            continue;
        }
    }
    
    static void a(final CompoundButton compoundButton, final ColorStateList supportButtonTintList) {
        if (compoundButton instanceof v) {
            ((v)compoundButton).setSupportButtonTintList(supportButtonTintList);
        }
    }
    
    static void a(final CompoundButton compoundButton, final PorterDuff$Mode supportButtonTintMode) {
        if (compoundButton instanceof v) {
            ((v)compoundButton).setSupportButtonTintMode(supportButtonTintMode);
        }
    }
}
