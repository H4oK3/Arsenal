// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

class c
{
    static final int[] a;
    static final long[] b;
    static final Object[] c;
    
    static {
        a = new int[0];
        b = new long[0];
        c = new Object[0];
    }
    
    public static int a(final int n) {
        return c(n * 4) / 4;
    }
    
    static int a(final int[] array, int i, final int n) {
        final int n2 = 0;
        final int n3 = i - 1;
        i = n2;
        int n4 = n3;
        while (i <= n4) {
            final int n5 = i + n4 >>> 1;
            final int n6 = array[n5];
            if (n6 < n) {
                i = n5 + 1;
            }
            else {
                if (n6 <= n) {
                    return n5;
                }
                n4 = n5 - 1;
            }
        }
        return ~i;
    }
    
    static int a(final long[] array, int i, final long n) {
        final int n2 = 0;
        final int n3 = i - 1;
        i = n2;
        int n4 = n3;
        while (i <= n4) {
            final int n5 = i + n4 >>> 1;
            final long n6 = array[n5];
            if (n6 < n) {
                i = n5 + 1;
            }
            else {
                if (n6 <= n) {
                    return n5;
                }
                n4 = n5 - 1;
            }
        }
        return ~i;
    }
    
    public static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int b(final int n) {
        return c(n * 8) / 8;
    }
    
    public static int c(final int n) {
        int n2 = 4;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= 32) {
                break;
            }
            if (n <= (1 << n2) - 12) {
                n3 = (1 << n2) - 12;
                break;
            }
            ++n2;
        }
        return n3;
    }
}
