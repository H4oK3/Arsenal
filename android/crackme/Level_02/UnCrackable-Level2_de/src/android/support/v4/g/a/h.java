// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.os.Build$VERSION;

public class h
{
    private static final c a;
    private final Object b;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            a = (c)new d();
            return;
        }
        if (Build$VERSION.SDK_INT >= 15) {
            a = (c)new b();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            a = (c)new a();
            return;
        }
        a = (c)new e();
    }
    
    public h(final Object b) {
        this.b = b;
    }
    
    public void a(final int n) {
        h.a.a(this.b, n);
    }
    
    public void a(final boolean b) {
        h.a.a(this.b, b);
    }
    
    public void b(final int n) {
        h.a.b(this.b, n);
    }
    
    public void c(final int n) {
        h.a.c(this.b, n);
    }
    
    public void d(final int n) {
        h.a.d(this.b, n);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final h h = (h)o;
            if (this.b == null) {
                if (h.b != null) {
                    return false;
                }
            }
            else if (!this.b.equals(h.b)) {
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
    
    static class a extends e
    {
        @Override
        public void a(final Object o, final int n) {
            i.a(o, n);
        }
        
        @Override
        public void a(final Object o, final boolean b) {
            i.a(o, b);
        }
        
        @Override
        public void b(final Object o, final int n) {
            i.b(o, n);
        }
    }
    
    static class b extends a
    {
        @Override
        public void c(final Object o, final int n) {
            j.a(o, n);
        }
        
        @Override
        public void d(final Object o, final int n) {
            j.b(o, n);
        }
    }
    
    interface c
    {
        void a(final Object p0, final int p1);
        
        void a(final Object p0, final boolean p1);
        
        void b(final Object p0, final int p1);
        
        void c(final Object p0, final int p1);
        
        void d(final Object p0, final int p1);
    }
    
    static class d extends b
    {
    }
    
    static class e implements c
    {
        @Override
        public void a(final Object o, final int n) {
        }
        
        @Override
        public void a(final Object o, final boolean b) {
        }
        
        @Override
        public void b(final Object o, final int n) {
        }
        
        @Override
        public void c(final Object o, final int n) {
        }
        
        @Override
        public void d(final Object o, final int n) {
        }
    }
}
