// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.b.a;

import java.lang.reflect.Array;

final class c
{
    static final /* synthetic */ boolean a;
    
    static {
        a = !c.class.desiredAssertionStatus();
    }
    
    public static int a(final int n) {
        if (n <= 4) {
            return 8;
        }
        return n * 2;
    }
    
    public static int[] a(final int[] array, final int n, final int n2) {
        if (!c.a && n > array.length) {
            throw new AssertionError();
        }
        int[] array2 = array;
        if (n + 1 > array.length) {
            array2 = new int[a(n)];
            System.arraycopy(array, 0, array2, 0, n);
        }
        array2[n] = n2;
        return array2;
    }
    
    public static <T> T[] a(T[] array, final int n, final T t) {
        if (!c.a && n > array.length) {
            throw new AssertionError();
        }
        if (n + 1 > array.length) {
            final Object[] array2 = (Object[])Array.newInstance(array.getClass().getComponentType(), a(n));
            System.arraycopy(array, 0, array2, 0, n);
            array = (T[])array2;
        }
        array[n] = t;
        return array;
    }
}
