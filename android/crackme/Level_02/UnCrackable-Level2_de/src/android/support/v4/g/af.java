// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.View;
import java.lang.reflect.Field;
import android.annotation.TargetApi;

@TargetApi(9)
class af
{
    private static Field a;
    private static boolean b;
    
    static ColorStateList a(final View view) {
        if (view instanceof ab) {
            return ((ab)view).getSupportBackgroundTintList();
        }
        return null;
    }
    
    static void a(final View view, final ColorStateList supportBackgroundTintList) {
        if (view instanceof ab) {
            ((ab)view).setSupportBackgroundTintList(supportBackgroundTintList);
        }
    }
    
    static void a(final View view, final PorterDuff$Mode supportBackgroundTintMode) {
        if (view instanceof ab) {
            ((ab)view).setSupportBackgroundTintMode(supportBackgroundTintMode);
        }
    }
    
    static PorterDuff$Mode b(final View view) {
        if (view instanceof ab) {
            return ((ab)view).getSupportBackgroundTintMode();
        }
        return null;
    }
    
    static boolean c(final View view) {
        return view.getWidth() > 0 && view.getHeight() > 0;
    }
    
    static int d(final View view) {
        Label_0027: {
            if (af.b) {
                break Label_0027;
            }
            while (true) {
                try {
                    (af.a = View.class.getDeclaredField("mMinHeight")).setAccessible(true);
                    af.b = true;
                    if (af.a != null) {
                        try {
                            return (int)af.a.get(view);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    static boolean e(final View view) {
        return view.getWindowToken() != null;
    }
}
