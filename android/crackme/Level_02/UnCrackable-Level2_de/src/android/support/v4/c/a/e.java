// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.util.Log;
import android.graphics.drawable.Drawable;
import java.lang.reflect.Method;
import android.annotation.TargetApi;

@TargetApi(17)
class e
{
    private static Method a;
    private static boolean b;
    
    public static boolean a(final Drawable drawable, final int n) {
        while (true) {
            if (!e.b) {
                while (true) {
                    try {
                        (e.a = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE)).setAccessible(true);
                        e.b = true;
                        if (e.a != null) {
                            final Method method = e.a;
                            final Drawable drawable2 = drawable;
                            final int n2 = 1;
                            final Object[] array = new Object[n2];
                            final int n3 = 0;
                            final int n4 = n;
                            final Integer n5 = n4;
                            array[n3] = n5;
                            method.invoke(drawable2, array);
                            return true;
                        }
                        return false;
                    }
                    catch (NoSuchMethodException ex) {
                        Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve setLayoutDirection(int) method", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                try {
                    final Method method = e.a;
                    final Drawable drawable2 = drawable;
                    final int n2 = 1;
                    final Object[] array = new Object[n2];
                    final int n3 = 0;
                    final int n4 = n;
                    final Integer n5 = n4;
                    array[n3] = n5;
                    method.invoke(drawable2, array);
                    return true;
                }
                catch (Exception ex2) {
                    Log.i("DrawableCompatJellybeanMr1", "Failed to invoke setLayoutDirection(int) via reflection", (Throwable)ex2);
                    e.a = null;
                }
                return false;
            }
            continue;
        }
    }
}
