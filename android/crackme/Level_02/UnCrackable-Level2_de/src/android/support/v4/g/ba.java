// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.WindowInsets;
import android.annotation.TargetApi;

@TargetApi(20)
class ba
{
    public static int a(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetBottom();
    }
    
    public static Object a(final Object o, final int n, final int n2, final int n3, final int n4) {
        return ((WindowInsets)o).replaceSystemWindowInsets(n, n2, n3, n4);
    }
    
    public static int b(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetLeft();
    }
    
    public static int c(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetRight();
    }
    
    public static int d(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetTop();
    }
}
