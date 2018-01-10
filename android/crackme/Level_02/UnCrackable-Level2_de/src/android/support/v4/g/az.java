// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.os.Build$VERSION;

public class az
{
    private static final d a;
    private final Object b;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            a = (d)new b();
            return;
        }
        if (sdk_INT >= 20) {
            a = (d)new a();
            return;
        }
        a = (d)new c();
    }
    
    az(final Object b) {
        this.b = b;
    }
    
    static az a(final Object o) {
        if (o == null) {
            return null;
        }
        return new az(o);
    }
    
    static Object a(final az az) {
        if (az == null) {
            return null;
        }
        return az.b;
    }
    
    public int a() {
        return az.a.b(this.b);
    }
    
    public az a(final int n, final int n2, final int n3, final int n4) {
        return az.a.a(this.b, n, n2, n3, n4);
    }
    
    public int b() {
        return az.a.d(this.b);
    }
    
    public int c() {
        return az.a.c(this.b);
    }
    
    public int d() {
        return az.a.a(this.b);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final az az = (az)o;
            if (this.b != null) {
                return this.b.equals(az.b);
            }
            if (az.b != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.b == null) {
            return 0;
        }
        return this.b.hashCode();
    }
    
    private static class a extends c
    {
        @Override
        public int a(final Object o) {
            return ba.a(o);
        }
        
        @Override
        public az a(final Object o, final int n, final int n2, final int n3, final int n4) {
            return new az(ba.a(o, n, n2, n3, n4));
        }
        
        @Override
        public int b(final Object o) {
            return ba.b(o);
        }
        
        @Override
        public int c(final Object o) {
            return ba.c(o);
        }
        
        @Override
        public int d(final Object o) {
            return ba.d(o);
        }
    }
    
    private static class b extends a
    {
    }
    
    private static class c implements d
    {
        @Override
        public int a(final Object o) {
            return 0;
        }
        
        @Override
        public az a(final Object o, final int n, final int n2, final int n3, final int n4) {
            return null;
        }
        
        @Override
        public int b(final Object o) {
            return 0;
        }
        
        @Override
        public int c(final Object o) {
            return 0;
        }
        
        @Override
        public int d(final Object o) {
            return 0;
        }
    }
    
    private interface d
    {
        int a(final Object p0);
        
        az a(final Object p0, final int p1, final int p2, final int p3, final int p4);
        
        int b(final Object p0);
        
        int c(final Object p0);
        
        int d(final Object p0);
    }
}
