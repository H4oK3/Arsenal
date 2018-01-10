// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.support.v4.c.a;
import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.util.TypedValue;

class aq
{
    static final int[] a;
    static final int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e;
    static final int[] f;
    static final int[] g;
    static final int[] h;
    private static final ThreadLocal<TypedValue> i;
    private static final int[] j;
    
    static {
        i = new ThreadLocal<TypedValue>();
        a = new int[] { -16842910 };
        b = new int[] { 16842908 };
        c = new int[] { 16843518 };
        d = new int[] { 16842919 };
        e = new int[] { 16842912 };
        f = new int[] { 16842913 };
        g = new int[] { -16842919, -16842908 };
        h = new int[0];
        j = new int[1];
    }
    
    public static int a(Context a, int b) {
        aq.j[0] = b;
        a = (Context)av.a(a, null, aq.j);
        try {
            b = ((av)a).b(0, 0);
            return b;
        }
        finally {
            ((av)a).a();
        }
    }
    
    static int a(final Context context, int a, final float n) {
        a = a(context, a);
        return a.b(a, Math.round(Color.alpha(a) * n));
    }
    
    private static TypedValue a() {
        TypedValue typedValue;
        if ((typedValue = aq.i.get()) == null) {
            typedValue = new TypedValue();
            aq.i.set(typedValue);
        }
        return typedValue;
    }
    
    public static ColorStateList b(Context a, final int n) {
        aq.j[0] = n;
        a = (Context)av.a(a, null, aq.j);
        try {
            return ((av)a).e(0);
        }
        finally {
            ((av)a).a();
        }
    }
    
    public static int c(final Context context, final int n) {
        final ColorStateList b = b(context, n);
        if (b != null && b.isStateful()) {
            return b.getColorForState(aq.a, b.getDefaultColor());
        }
        final TypedValue a = a();
        context.getTheme().resolveAttribute(16842803, a, true);
        return a(context, n, a.getFloat());
    }
}
