// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import java.util.WeakHashMap;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.view.View;
import android.support.v4.e.c;
import android.os.Build$VERSION;

public class ae
{
    static final l a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (android.support.v4.e.c.a()) {
            a = (l)new a();
            return;
        }
        if (sdk_INT >= 23) {
            a = (l)new k();
            return;
        }
        if (sdk_INT >= 21) {
            a = (l)new j();
            return;
        }
        if (sdk_INT >= 19) {
            a = (l)new i();
            return;
        }
        if (sdk_INT >= 18) {
            a = (l)new h();
            return;
        }
        if (sdk_INT >= 17) {
            a = (l)new g();
            return;
        }
        if (sdk_INT >= 16) {
            a = (l)new f();
            return;
        }
        if (sdk_INT >= 15) {
            a = (l)new d();
            return;
        }
        if (sdk_INT >= 14) {
            a = (l)new e();
            return;
        }
        if (sdk_INT >= 11) {
            a = (l)new c();
            return;
        }
        a = (l)new b();
    }
    
    public static int a(final int n, final int n2) {
        return ae.a.a(n, n2);
    }
    
    public static int a(final int n, final int n2, final int n3) {
        return ae.a.a(n, n2, n3);
    }
    
    public static az a(final View view, final az az) {
        return ae.a.a(view, az);
    }
    
    public static void a(final View view) {
        ae.a.a(view);
    }
    
    public static void a(final View view, final float n) {
        ae.a.a(view, n);
    }
    
    public static void a(final View view, final int n, final int n2) {
        ae.a.a(view, n, n2);
    }
    
    public static void a(final View view, final int n, final Paint paint) {
        ae.a.a(view, n, paint);
    }
    
    public static void a(final View view, final ColorStateList list) {
        ae.a.a(view, list);
    }
    
    public static void a(final View view, final PorterDuff$Mode porterDuff$Mode) {
        ae.a.a(view, porterDuff$Mode);
    }
    
    public static void a(final View view, final Drawable drawable) {
        ae.a.a(view, drawable);
    }
    
    public static void a(final View view, final android.support.v4.g.b b) {
        ae.a.a(view, b);
    }
    
    public static void a(final View view, final z z) {
        ae.a.a(view, z);
    }
    
    public static void a(final View view, final Runnable runnable) {
        ae.a.a(view, runnable);
    }
    
    public static void a(final View view, final Runnable runnable, final long n) {
        ae.a.a(view, runnable, n);
    }
    
    public static void a(final View view, final boolean b) {
        ae.a.a(view, b);
    }
    
    public static boolean a(final View view, final int n) {
        return ae.a.a(view, n);
    }
    
    public static int b(final View view) {
        return ae.a.b(view);
    }
    
    public static void b(final View view, final float n) {
        ae.a.b(view, n);
    }
    
    public static void b(final View view, final boolean b) {
        ae.a.b(view, b);
    }
    
    public static int c(final View view) {
        return ae.a.c(view);
    }
    
    public static void c(final View view, final float n) {
        ae.a.c(view, n);
    }
    
    public static int d(final View view) {
        return ae.a.d(view);
    }
    
    public static int e(final View view) {
        return ae.a.e(view);
    }
    
    public static float f(final View view) {
        return ae.a.g(view);
    }
    
    public static int g(final View view) {
        return ae.a.h(view);
    }
    
    public static as h(final View view) {
        return ae.a.i(view);
    }
    
    public static String i(final View view) {
        return ae.a.j(view);
    }
    
    public static int j(final View view) {
        return ae.a.k(view);
    }
    
    public static void k(final View view) {
        ae.a.l(view);
    }
    
    public static void l(final View view) {
        ae.a.m(view);
    }
    
    public static boolean m(final View view) {
        return ae.a.f(view);
    }
    
    public static ColorStateList n(final View view) {
        return ae.a.n(view);
    }
    
    public static PorterDuff$Mode o(final View view) {
        return ae.a.o(view);
    }
    
    public static void p(final View view) {
        ae.a.p(view);
    }
    
    public static boolean q(final View view) {
        return ae.a.q(view);
    }
    
    public static boolean r(final View view) {
        return ae.a.r(view);
    }
    
    public static boolean s(final View view) {
        return ae.a.s(view);
    }
    
    static class a extends k
    {
    }
    
    static class b implements l
    {
        WeakHashMap<View, as> a;
        
        b() {
            this.a = null;
        }
        
        private boolean a(final aa aa, final int n) {
            boolean b = true;
            final int computeVerticalScrollOffset = aa.computeVerticalScrollOffset();
            final int n2 = aa.computeVerticalScrollRange() - aa.computeVerticalScrollExtent();
            if (n2 == 0) {
                b = false;
            }
            else if (n < 0) {
                if (computeVerticalScrollOffset <= 0) {
                    return false;
                }
            }
            else if (computeVerticalScrollOffset >= n2 - 1) {
                return false;
            }
            return b;
        }
        
        @Override
        public int a(final int n, final int n2) {
            return n | n2;
        }
        
        @Override
        public int a(final int n, final int n2, final int n3) {
            return View.resolveSize(n, n2);
        }
        
        long a() {
            return 10L;
        }
        
        @Override
        public az a(final View view, final az az) {
            return az;
        }
        
        @Override
        public void a(final View view) {
            view.invalidate();
        }
        
        @Override
        public void a(final View view, final float n) {
        }
        
        @Override
        public void a(final View view, final int n, final int n2) {
        }
        
        @Override
        public void a(final View view, final int n, final Paint paint) {
        }
        
        @Override
        public void a(final View view, final ColorStateList list) {
            af.a(view, list);
        }
        
        @Override
        public void a(final View view, final PorterDuff$Mode porterDuff$Mode) {
            af.a(view, porterDuff$Mode);
        }
        
        @Override
        public void a(final View view, final Drawable backgroundDrawable) {
            view.setBackgroundDrawable(backgroundDrawable);
        }
        
        @Override
        public void a(final View view, final android.support.v4.g.b b) {
        }
        
        @Override
        public void a(final View view, final z z) {
        }
        
        @Override
        public void a(final View view, final Runnable runnable) {
            view.postDelayed(runnable, this.a());
        }
        
        @Override
        public void a(final View view, final Runnable runnable, final long n) {
            view.postDelayed(runnable, this.a() + n);
        }
        
        @Override
        public void a(final View view, final boolean b) {
        }
        
        @Override
        public boolean a(final View view, final int n) {
            return view instanceof aa && this.a((aa)view, n);
        }
        
        @Override
        public int b(final View view) {
            return 0;
        }
        
        @Override
        public void b(final View view, final float n) {
        }
        
        @Override
        public void b(final View view, final boolean b) {
        }
        
        @Override
        public int c(final View view) {
            return 0;
        }
        
        @Override
        public void c(final View view, final float n) {
        }
        
        @Override
        public int d(final View view) {
            return view.getMeasuredWidth();
        }
        
        @Override
        public int e(final View view) {
            return 0;
        }
        
        @Override
        public boolean f(final View view) {
            return true;
        }
        
        @Override
        public float g(final View view) {
            return 0.0f;
        }
        
        @Override
        public int h(final View view) {
            return af.d(view);
        }
        
        @Override
        public as i(final View view) {
            return new as(view);
        }
        
        @Override
        public String j(final View view) {
            return null;
        }
        
        @Override
        public int k(final View view) {
            return 0;
        }
        
        @Override
        public void l(final View view) {
        }
        
        @Override
        public void m(final View view) {
        }
        
        @Override
        public ColorStateList n(final View view) {
            return af.a(view);
        }
        
        @Override
        public PorterDuff$Mode o(final View view) {
            return af.b(view);
        }
        
        @Override
        public void p(final View view) {
            if (view instanceof v) {
                ((v)view).stopNestedScroll();
            }
        }
        
        @Override
        public boolean q(final View view) {
            return af.c(view);
        }
        
        @Override
        public boolean r(final View view) {
            return af.e(view);
        }
        
        @Override
        public boolean s(final View view) {
            return false;
        }
    }
    
    static class c extends b
    {
        @Override
        public int a(final int n, final int n2) {
            return ag.a(n, n2);
        }
        
        @Override
        public int a(final int n, final int n2, final int n3) {
            return ag.a(n, n2, n3);
        }
        
        @Override
        long a() {
            return ag.a();
        }
        
        @Override
        public void a(final View view, final float n) {
            ag.a(view, n);
        }
        
        @Override
        public void a(final View view, final int n, final Paint paint) {
            ag.a(view, n, paint);
        }
        
        @Override
        public void a(final View view, final boolean b) {
            ag.a(view, b);
        }
        
        @Override
        public int b(final View view) {
            return ag.a(view);
        }
        
        @Override
        public void b(final View view, final float n) {
            ag.b(view, n);
        }
        
        @Override
        public void b(final View view, final boolean b) {
            ag.b(view, b);
        }
        
        @Override
        public int d(final View view) {
            return ag.b(view);
        }
        
        @Override
        public int e(final View view) {
            return ag.c(view);
        }
        
        @Override
        public float g(final View view) {
            return ag.d(view);
        }
        
        @Override
        public void m(final View view) {
            ag.e(view);
        }
    }
    
    static class d extends e
    {
        @Override
        public boolean s(final View view) {
            return ai.a(view);
        }
    }
    
    static class e extends c
    {
        static boolean b;
        
        static {
            e.b = false;
        }
        
        @Override
        public void a(final View view, final android.support.v4.g.b b) {
            Object a;
            if (b == null) {
                a = null;
            }
            else {
                a = b.a();
            }
            ah.a(view, a);
        }
        
        @Override
        public boolean a(final View view, final int n) {
            return ah.a(view, n);
        }
        
        @Override
        public as i(final View view) {
            if (this.a == null) {
                this.a = new WeakHashMap<View, as>();
            }
            as as;
            if ((as = this.a.get(view)) == null) {
                as = new as(view);
                this.a.put(view, as);
            }
            return as;
        }
    }
    
    static class f extends d
    {
        @Override
        public void a(final View view) {
            aj.a(view);
        }
        
        @Override
        public void a(final View view, final Drawable drawable) {
            aj.a(view, drawable);
        }
        
        @Override
        public void a(final View view, final Runnable runnable) {
            aj.a(view, runnable);
        }
        
        @Override
        public void a(final View view, final Runnable runnable, final long n) {
            aj.a(view, runnable, n);
        }
        
        @Override
        public boolean f(final View view) {
            return aj.d(view);
        }
        
        @Override
        public int h(final View view) {
            return aj.b(view);
        }
        
        @Override
        public void l(final View view) {
            aj.c(view);
        }
    }
    
    static class g extends f
    {
        @Override
        public int c(final View view) {
            return ak.a(view);
        }
        
        @Override
        public int k(final View view) {
            return ak.b(view);
        }
    }
    
    static class h extends g
    {
    }
    
    static class i extends h
    {
        @Override
        public boolean q(final View view) {
            return al.a(view);
        }
        
        @Override
        public boolean r(final View view) {
            return al.b(view);
        }
    }
    
    static class j extends i
    {
        @Override
        public az a(final View view, final az az) {
            return az.a(am.a(view, az.a(az)));
        }
        
        @Override
        public void a(final View view, final ColorStateList list) {
            am.a(view, list);
        }
        
        @Override
        public void a(final View view, final PorterDuff$Mode porterDuff$Mode) {
            am.a(view, porterDuff$Mode);
        }
        
        @Override
        public void a(final View view, final z z) {
            if (z == null) {
                am.a(view, (am.a)null);
                return;
            }
            am.a(view, (am.a)new am.a() {
                @Override
                public Object a(final View view, final Object o) {
                    return az.a(z.a(view, az.a(o)));
                }
            });
        }
        
        @Override
        public void c(final View view, final float n) {
            am.a(view, n);
        }
        
        @Override
        public String j(final View view) {
            return am.a(view);
        }
        
        @Override
        public void l(final View view) {
            am.b(view);
        }
        
        @Override
        public ColorStateList n(final View view) {
            return am.c(view);
        }
        
        @Override
        public PorterDuff$Mode o(final View view) {
            return am.d(view);
        }
        
        @Override
        public void p(final View view) {
            am.e(view);
        }
    }
    
    static class k extends j
    {
        @Override
        public void a(final View view, final int n, final int n2) {
            an.a(view, n, n2);
        }
    }
    
    interface l
    {
        int a(final int p0, final int p1);
        
        int a(final int p0, final int p1, final int p2);
        
        az a(final View p0, final az p1);
        
        void a(final View p0);
        
        void a(final View p0, final float p1);
        
        void a(final View p0, final int p1, final int p2);
        
        void a(final View p0, final int p1, final Paint p2);
        
        void a(final View p0, final ColorStateList p1);
        
        void a(final View p0, final PorterDuff$Mode p1);
        
        void a(final View p0, final Drawable p1);
        
        void a(final View p0, final android.support.v4.g.b p1);
        
        void a(final View p0, final z p1);
        
        void a(final View p0, final Runnable p1);
        
        void a(final View p0, final Runnable p1, final long p2);
        
        void a(final View p0, final boolean p1);
        
        boolean a(final View p0, final int p1);
        
        int b(final View p0);
        
        void b(final View p0, final float p1);
        
        void b(final View p0, final boolean p1);
        
        int c(final View p0);
        
        void c(final View p0, final float p1);
        
        int d(final View p0);
        
        int e(final View p0);
        
        boolean f(final View p0);
        
        float g(final View p0);
        
        int h(final View p0);
        
        as i(final View p0);
        
        String j(final View p0);
        
        int k(final View p0);
        
        void l(final View p0);
        
        void m(final View p0);
        
        ColorStateList n(final View p0);
        
        PorterDuff$Mode o(final View p0);
        
        void p(final View p0);
        
        boolean q(final View p0);
        
        boolean r(final View p0);
        
        boolean s(final View p0);
    }
}
