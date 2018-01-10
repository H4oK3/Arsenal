// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.lang.reflect.InvocationTargetException;
import android.support.v4.g.ae;
import android.util.Log;
import android.graphics.Rect;
import android.view.View;
import android.os.Build$VERSION;
import java.lang.reflect.Method;

public class ay
{
    private static Method a;
    
    static {
        if (Build$VERSION.SDK_INT < 18) {
            return;
        }
        try {
            ay.a = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            if (!ay.a.isAccessible()) {
                ay.a.setAccessible(true);
            }
        }
        catch (NoSuchMethodException ex) {
            Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
        }
    }
    
    public static int a(final int n, final int n2) {
        return n | n2;
    }
    
    public static void a(final View view, final Rect rect, final Rect rect2) {
        if (ay.a == null) {
            return;
        }
        try {
            ay.a.invoke(view, rect, rect2);
        }
        catch (Exception ex) {
            Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", (Throwable)ex);
        }
    }
    
    public static boolean a(final View view) {
        return ae.c(view) == 1;
    }
    
    public static void b(final View view) {
        if (Build$VERSION.SDK_INT < 16) {
            return;
        }
        try {
            final Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", (Class<?>[])new Class[0]);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(view, new Object[0]);
        }
        catch (NoSuchMethodException ex3) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        }
        catch (InvocationTargetException ex) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", (Throwable)ex);
        }
        catch (IllegalAccessException ex2) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", (Throwable)ex2);
        }
    }
}
