// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.ViewGroup;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.support.v4.g.a.e;
import android.view.View;
import android.os.Build$VERSION;

public class b
{
    private static final b b;
    private static final Object c;
    final Object a;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            b = (b)new c();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            b = (b)new a();
        }
        else {
            b = (b)new d();
        }
        c = android.support.v4.g.b.b.a();
    }
    
    public b() {
        this.a = android.support.v4.g.b.b.a(this);
    }
    
    public e a(final View view) {
        return android.support.v4.g.b.b.a(android.support.v4.g.b.c, view);
    }
    
    Object a() {
        return this.a;
    }
    
    public void a(final View view, final int n) {
        android.support.v4.g.b.b.a(android.support.v4.g.b.c, view, n);
    }
    
    public void a(final View view, final b b) {
        b.b.a(b.c, view, b);
    }
    
    public void a(final View view, final AccessibilityEvent accessibilityEvent) {
        android.support.v4.g.b.b.d(android.support.v4.g.b.c, view, accessibilityEvent);
    }
    
    public boolean a(final View view, final int n, final Bundle bundle) {
        return android.support.v4.g.b.b.a(android.support.v4.g.b.c, view, n, bundle);
    }
    
    public boolean a(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return android.support.v4.g.b.b.a(android.support.v4.g.b.c, viewGroup, view, accessibilityEvent);
    }
    
    public boolean b(final View view, final AccessibilityEvent accessibilityEvent) {
        return android.support.v4.g.b.b.a(android.support.v4.g.b.c, view, accessibilityEvent);
    }
    
    public void c(final View view, final AccessibilityEvent accessibilityEvent) {
        android.support.v4.g.b.b.c(android.support.v4.g.b.c, view, accessibilityEvent);
    }
    
    public void d(final View view, final AccessibilityEvent accessibilityEvent) {
        android.support.v4.g.b.b.b(android.support.v4.g.b.c, view, accessibilityEvent);
    }
    
    static class a extends d
    {
        @Override
        public Object a() {
            return android.support.v4.g.c.a();
        }
        
        @Override
        public Object a(final b b) {
            return android.support.v4.g.c.a((android.support.v4.g.c.a)new android.support.v4.g.c.a() {
                @Override
                public void a(final View view, final int n) {
                    b.a(view, n);
                }
                
                @Override
                public void a(final View view, final Object o) {
                    b.a(view, new b(o));
                }
                
                @Override
                public boolean a(final View view, final AccessibilityEvent accessibilityEvent) {
                    return b.b(view, accessibilityEvent);
                }
                
                @Override
                public boolean a(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                    return b.a(viewGroup, view, accessibilityEvent);
                }
                
                @Override
                public void b(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.d(view, accessibilityEvent);
                }
                
                @Override
                public void c(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.c(view, accessibilityEvent);
                }
                
                @Override
                public void d(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.a(view, accessibilityEvent);
                }
            });
        }
        
        @Override
        public void a(final Object o, final View view, final int n) {
            android.support.v4.g.c.a(o, view, n);
        }
        
        @Override
        public void a(final Object o, final View view, final b b) {
            android.support.v4.g.c.a(o, view, b.a());
        }
        
        @Override
        public boolean a(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            return android.support.v4.g.c.a(o, view, accessibilityEvent);
        }
        
        @Override
        public boolean a(final Object o, final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return android.support.v4.g.c.a(o, viewGroup, view, accessibilityEvent);
        }
        
        @Override
        public void b(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            android.support.v4.g.c.b(o, view, accessibilityEvent);
        }
        
        @Override
        public void c(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            android.support.v4.g.c.c(o, view, accessibilityEvent);
        }
        
        @Override
        public void d(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            android.support.v4.g.c.d(o, view, accessibilityEvent);
        }
    }
    
    interface b
    {
        e a(final Object p0, final View p1);
        
        Object a();
        
        Object a(final android.support.v4.g.b p0);
        
        void a(final Object p0, final View p1, final int p2);
        
        void a(final Object p0, final View p1, final android.support.v4.g.a.b p2);
        
        boolean a(final Object p0, final View p1, final int p2, final Bundle p3);
        
        boolean a(final Object p0, final View p1, final AccessibilityEvent p2);
        
        boolean a(final Object p0, final ViewGroup p1, final View p2, final AccessibilityEvent p3);
        
        void b(final Object p0, final View p1, final AccessibilityEvent p2);
        
        void c(final Object p0, final View p1, final AccessibilityEvent p2);
        
        void d(final Object p0, final View p1, final AccessibilityEvent p2);
    }
    
    static class c extends a
    {
        @Override
        public e a(Object a, final View view) {
            a = android.support.v4.g.d.a(a, view);
            if (a != null) {
                return new e(a);
            }
            return null;
        }
        
        @Override
        public Object a(final b b) {
            return android.support.v4.g.d.a((android.support.v4.g.d.a)new android.support.v4.g.d.a() {
                @Override
                public Object a(final View view) {
                    final e a = b.a(view);
                    if (a != null) {
                        return a.a();
                    }
                    return null;
                }
                
                @Override
                public void a(final View view, final int n) {
                    b.a(view, n);
                }
                
                @Override
                public void a(final View view, final Object o) {
                    b.a(view, new b(o));
                }
                
                @Override
                public boolean a(final View view, final int n, final Bundle bundle) {
                    return b.a(view, n, bundle);
                }
                
                @Override
                public boolean a(final View view, final AccessibilityEvent accessibilityEvent) {
                    return b.b(view, accessibilityEvent);
                }
                
                @Override
                public boolean a(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                    return b.a(viewGroup, view, accessibilityEvent);
                }
                
                @Override
                public void b(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.d(view, accessibilityEvent);
                }
                
                @Override
                public void c(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.c(view, accessibilityEvent);
                }
                
                @Override
                public void d(final View view, final AccessibilityEvent accessibilityEvent) {
                    b.a(view, accessibilityEvent);
                }
            });
        }
        
        @Override
        public boolean a(final Object o, final View view, final int n, final Bundle bundle) {
            return android.support.v4.g.d.a(o, view, n, bundle);
        }
    }
    
    static class d implements b
    {
        @Override
        public e a(final Object o, final View view) {
            return null;
        }
        
        @Override
        public Object a() {
            return null;
        }
        
        @Override
        public Object a(final b b) {
            return null;
        }
        
        @Override
        public void a(final Object o, final View view, final int n) {
        }
        
        @Override
        public void a(final Object o, final View view, final b b) {
        }
        
        @Override
        public boolean a(final Object o, final View view, final int n, final Bundle bundle) {
            return false;
        }
        
        @Override
        public boolean a(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            return false;
        }
        
        @Override
        public boolean a(final Object o, final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return true;
        }
        
        @Override
        public void b(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
        
        @Override
        public void c(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
        
        @Override
        public void d(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
    }
}
