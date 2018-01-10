// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.content.Context;
import android.os.Build$VERSION;

public final class h
{
    private static final c b;
    private Object a;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            b = (c)new d();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            b = (c)new b();
            return;
        }
        b = (c)new a();
    }
    
    public h(final Context context) {
        this.a = h.b.a(context);
    }
    
    public void a(final int n, final int n2) {
        h.b.a(this.a, n, n2);
    }
    
    public boolean a() {
        return h.b.a(this.a);
    }
    
    public boolean a(final float n, final float n2) {
        return h.b.a(this.a, n, n2);
    }
    
    public boolean a(final int n) {
        return h.b.a(this.a, n);
    }
    
    public boolean a(final Canvas canvas) {
        return h.b.a(this.a, canvas);
    }
    
    public boolean b() {
        return h.b.b(this.a);
    }
    
    static class a implements c
    {
        @Override
        public Object a(final Context context) {
            return null;
        }
        
        @Override
        public void a(final Object o, final int n, final int n2) {
        }
        
        @Override
        public boolean a(final Object o) {
            return true;
        }
        
        @Override
        public boolean a(final Object o, final float n, final float n2) {
            return false;
        }
        
        @Override
        public boolean a(final Object o, final int n) {
            return false;
        }
        
        @Override
        public boolean a(final Object o, final Canvas canvas) {
            return false;
        }
        
        @Override
        public boolean b(final Object o) {
            return false;
        }
    }
    
    static class b implements c
    {
        @Override
        public Object a(final Context context) {
            return i.a(context);
        }
        
        @Override
        public void a(final Object o, final int n, final int n2) {
            i.a(o, n, n2);
        }
        
        @Override
        public boolean a(final Object o) {
            return i.a(o);
        }
        
        @Override
        public boolean a(final Object o, final float n, final float n2) {
            return i.a(o, n);
        }
        
        @Override
        public boolean a(final Object o, final int n) {
            return i.a(o, n);
        }
        
        @Override
        public boolean a(final Object o, final Canvas canvas) {
            return i.a(o, canvas);
        }
        
        @Override
        public boolean b(final Object o) {
            return i.b(o);
        }
    }
    
    interface c
    {
        Object a(final Context p0);
        
        void a(final Object p0, final int p1, final int p2);
        
        boolean a(final Object p0);
        
        boolean a(final Object p0, final float p1, final float p2);
        
        boolean a(final Object p0, final int p1);
        
        boolean a(final Object p0, final Canvas p1);
        
        boolean b(final Object p0);
    }
    
    static class d extends b
    {
        @Override
        public boolean a(final Object o, final float n, final float n2) {
            return j.a(o, n, n2);
        }
    }
}
