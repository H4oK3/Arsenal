// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c;

import android.graphics.Color;

public final class a
{
    private static final ThreadLocal<double[]> a;
    
    static {
        a = new ThreadLocal<double[]>();
    }
    
    public static int a(final int n, final int n2) {
        final int alpha = Color.alpha(n2);
        final int alpha2 = Color.alpha(n);
        final int c = c(alpha2, alpha);
        return Color.argb(c, a(Color.red(n), alpha2, Color.red(n2), alpha, c), a(Color.green(n), alpha2, Color.green(n2), alpha, c), a(Color.blue(n), alpha2, Color.blue(n2), alpha, c));
    }
    
    private static int a(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n5 == 0) {
            return 0;
        }
        return (n * 255 * n2 + n3 * n4 * (255 - n2)) / (n5 * 255);
    }
    
    public static int b(final int n, final int n2) {
        if (n2 < 0 || n2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (0xFFFFFF & n) | n2 << 24;
    }
    
    private static int c(final int n, final int n2) {
        return 255 - (255 - n2) * (255 - n) / 255;
    }
}
