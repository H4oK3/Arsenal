// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.view.ViewParent;
import android.os.Build$VERSION;

public final class aq
{
    static final b a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            a = (b)new d();
            return;
        }
        if (sdk_INT >= 19) {
            a = (b)new c();
            return;
        }
        if (sdk_INT >= 14) {
            a = (b)new a();
            return;
        }
        a = (b)new e();
    }
    
    public static void a(final ViewParent viewParent, final View view) {
        aq.a.a(viewParent, view);
    }
    
    public static void a(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
        aq.a.a(viewParent, view, n, n2, n3, n4);
    }
    
    public static void a(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
        aq.a.a(viewParent, view, n, n2, array);
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final float n, final float n2) {
        return aq.a.a(viewParent, view, n, n2);
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
        return aq.a.a(viewParent, view, n, n2, b);
    }
    
    public static boolean a(final ViewParent viewParent, final View view, final View view2, final int n) {
        return aq.a.a(viewParent, view, view2, n);
    }
    
    public static void b(final ViewParent viewParent, final View view, final View view2, final int n) {
        aq.a.b(viewParent, view, view2, n);
    }
    
    static class a extends e
    {
    }
    
    interface b
    {
        void a(final ViewParent p0, final View p1);
        
        void a(final ViewParent p0, final View p1, final int p2, final int p3, final int p4, final int p5);
        
        void a(final ViewParent p0, final View p1, final int p2, final int p3, final int[] p4);
        
        boolean a(final ViewParent p0, final View p1, final float p2, final float p3);
        
        boolean a(final ViewParent p0, final View p1, final float p2, final float p3, final boolean p4);
        
        boolean a(final ViewParent p0, final View p1, final View p2, final int p3);
        
        void b(final ViewParent p0, final View p1, final View p2, final int p3);
    }
    
    static class c extends a
    {
    }
    
    static class d extends c
    {
        @Override
        public void a(final ViewParent viewParent, final View view) {
            ar.a(viewParent, view);
        }
        
        @Override
        public void a(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
            ar.a(viewParent, view, n, n2, n3, n4);
        }
        
        @Override
        public void a(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
            ar.a(viewParent, view, n, n2, array);
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final float n, final float n2) {
            return ar.a(viewParent, view, n, n2);
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
            return ar.a(viewParent, view, n, n2, b);
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final View view2, final int n) {
            return ar.a(viewParent, view, view2, n);
        }
        
        @Override
        public void b(final ViewParent viewParent, final View view, final View view2, final int n) {
            ar.b(viewParent, view, view2, n);
        }
    }
    
    static class e implements b
    {
        @Override
        public void a(final ViewParent viewParent, final View view) {
            if (viewParent instanceof x) {
                ((x)viewParent).onStopNestedScroll(view);
            }
        }
        
        @Override
        public void a(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
            if (viewParent instanceof x) {
                ((x)viewParent).onNestedScroll(view, n, n2, n3, n4);
            }
        }
        
        @Override
        public void a(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
            if (viewParent instanceof x) {
                ((x)viewParent).onNestedPreScroll(view, n, n2, array);
            }
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final float n, final float n2) {
            return viewParent instanceof x && ((x)viewParent).onNestedPreFling(view, n, n2);
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
            return viewParent instanceof x && ((x)viewParent).onNestedFling(view, n, n2, b);
        }
        
        @Override
        public boolean a(final ViewParent viewParent, final View view, final View view2, final int n) {
            return viewParent instanceof x && ((x)viewParent).onStartNestedScroll(view, view2, n);
        }
        
        @Override
        public void b(final ViewParent viewParent, final View view, final View view2, final int n) {
            if (viewParent instanceof x) {
                ((x)viewParent).onNestedScrollAccepted(view, view2, n);
            }
        }
    }
}
