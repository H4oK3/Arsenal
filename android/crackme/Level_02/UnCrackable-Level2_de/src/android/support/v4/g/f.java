// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.os.Build$VERSION;

public final class f
{
    static final a a;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static int a(final int n, final int n2) {
        return f.a.a(n, n2);
    }
    
    interface a
    {
        int a(final int p0, final int p1);
    }
    
    static class b implements a
    {
        @Override
        public int a(final int n, final int n2) {
            return 0xFF7FFFFF & n;
        }
    }
    
    static class c implements a
    {
        @Override
        public int a(final int n, final int n2) {
            return g.a(n, n2);
        }
    }
}
