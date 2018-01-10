// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.LayoutInflater;
import android.os.Build$VERSION;

public final class j
{
    static final a a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            a = (a)new d();
            return;
        }
        if (sdk_INT >= 11) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static n a(final LayoutInflater layoutInflater) {
        return j.a.a(layoutInflater);
    }
    
    public static void a(final LayoutInflater layoutInflater, final n n) {
        j.a.a(layoutInflater, n);
    }
    
    interface a
    {
        n a(final LayoutInflater p0);
        
        void a(final LayoutInflater p0, final n p1);
    }
    
    static class b implements a
    {
        @Override
        public n a(final LayoutInflater layoutInflater) {
            return k.a(layoutInflater);
        }
        
        @Override
        public void a(final LayoutInflater layoutInflater, final n n) {
            k.a(layoutInflater, n);
        }
    }
    
    static class c extends b
    {
        @Override
        public void a(final LayoutInflater layoutInflater, final n n) {
            l.a(layoutInflater, n);
        }
    }
    
    static class d extends c
    {
        @Override
        public void a(final LayoutInflater layoutInflater, final n n) {
            m.a(layoutInflater, n);
        }
    }
}
