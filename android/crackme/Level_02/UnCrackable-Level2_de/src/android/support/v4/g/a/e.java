// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import java.util.ArrayList;
import android.os.Bundle;
import java.util.List;
import android.os.Build$VERSION;

public class e
{
    private static final a a;
    private final Object b;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            a = (a)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            a = (a)new b();
            return;
        }
        a = (a)new d();
    }
    
    public e() {
        this.b = e.a.a(this);
    }
    
    public e(final Object b) {
        this.b = b;
    }
    
    public android.support.v4.g.a.b a(final int n) {
        return null;
    }
    
    public Object a() {
        return this.b;
    }
    
    public List<android.support.v4.g.a.b> a(final String s, final int n) {
        return null;
    }
    
    public boolean a(final int n, final int n2, final Bundle bundle) {
        return false;
    }
    
    public android.support.v4.g.a.b b(final int n) {
        return null;
    }
    
    interface a
    {
        Object a(final e p0);
    }
    
    private static class b extends d
    {
        @Override
        public Object a(final e e) {
            return f.a((f.a)new f.a() {
                @Override
                public Object a(final int n) {
                    final android.support.v4.g.a.b a = e.a(n);
                    if (a == null) {
                        return null;
                    }
                    return a.a();
                }
                
                @Override
                public List<Object> a(final String s, int i) {
                    final List<android.support.v4.g.a.b> a = e.a(s, i);
                    if (a == null) {
                        return null;
                    }
                    final ArrayList<Object> list = new ArrayList<Object>();
                    int size;
                    for (size = a.size(), i = 0; i < size; ++i) {
                        list.add(a.get(i).a());
                    }
                    return list;
                }
                
                @Override
                public boolean a(final int n, final int n2, final Bundle bundle) {
                    return e.a(n, n2, bundle);
                }
            });
        }
    }
    
    private static class c extends d
    {
        @Override
        public Object a(final e e) {
            return g.a((g.a)new g.a() {
                @Override
                public Object a(final int n) {
                    final android.support.v4.g.a.b a = e.a(n);
                    if (a == null) {
                        return null;
                    }
                    return a.a();
                }
                
                @Override
                public List<Object> a(final String s, int i) {
                    final List<android.support.v4.g.a.b> a = e.a(s, i);
                    if (a == null) {
                        return null;
                    }
                    final ArrayList<Object> list = new ArrayList<Object>();
                    int size;
                    for (size = a.size(), i = 0; i < size; ++i) {
                        list.add(a.get(i).a());
                    }
                    return list;
                }
                
                @Override
                public boolean a(final int n, final int n2, final Bundle bundle) {
                    return e.a(n, n2, bundle);
                }
                
                @Override
                public Object b(final int n) {
                    final android.support.v4.g.a.b b = e.b(n);
                    if (b == null) {
                        return null;
                    }
                    return b.a();
                }
            });
        }
    }
    
    static class d implements a
    {
        @Override
        public Object a(final e e) {
            return null;
        }
    }
}
