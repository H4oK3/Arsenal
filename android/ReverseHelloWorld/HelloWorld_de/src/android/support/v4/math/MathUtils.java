// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.math;

public class MathUtils
{
    public static double clamp(final double n, final double n2, final double n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static float clamp(final float n, final int n2, final int n3) {
        float n4;
        if (n < n2) {
            n4 = n2;
        }
        else {
            n4 = n;
            if (n > n3) {
                return n3;
            }
        }
        return n4;
    }
    
    public static int clamp(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
}
