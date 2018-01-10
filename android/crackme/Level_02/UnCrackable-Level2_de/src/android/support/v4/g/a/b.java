// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class b
{
    static final f a;
    public int b;
    private final Object c;
    
    static {
        if (Build$VERSION.SDK_INT >= 24) {
            a = (f)new d();
            return;
        }
        if (Build$VERSION.SDK_INT >= 23) {
            a = (f)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 22) {
            a = (f)new b();
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            a = (f)new a();
            return;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            a = (f)new j();
            return;
        }
        if (Build$VERSION.SDK_INT >= 18) {
            a = (f)new i();
            return;
        }
        if (Build$VERSION.SDK_INT >= 17) {
            a = (f)new h();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            a = (f)new g();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            a = (f)new e();
            return;
        }
        a = (f)new k();
    }
    
    public b(final Object c) {
        this.b = -1;
        this.c = c;
    }
    
    private static String b(final int n) {
        switch (n) {
            default: {
                return "ACTION_UNKNOWN";
            }
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 32: {
                return "ACTION_LONG_CLICK";
            }
            case 64: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 128: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 256: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 512: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 1024: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 2048: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 4096: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 8192: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 65536: {
                return "ACTION_CUT";
            }
            case 16384: {
                return "ACTION_COPY";
            }
            case 32768: {
                return "ACTION_PASTE";
            }
            case 131072: {
                return "ACTION_SET_SELECTION";
            }
        }
    }
    
    public Object a() {
        return this.c;
    }
    
    public void a(final int n) {
        android.support.v4.g.a.b.a.a(this.c, n);
    }
    
    public void a(final Rect rect) {
        android.support.v4.g.a.b.a.a(this.c, rect);
    }
    
    public void a(final CharSequence charSequence) {
        android.support.v4.g.a.b.a.a(this.c, charSequence);
    }
    
    public void a(final boolean b) {
        b.a.a(this.c, b);
    }
    
    public int b() {
        return android.support.v4.g.a.b.a.a(this.c);
    }
    
    public void b(final Rect rect) {
        android.support.v4.g.a.b.a.b(this.c, rect);
    }
    
    public boolean c() {
        return android.support.v4.g.a.b.a.f(this.c);
    }
    
    public boolean d() {
        return android.support.v4.g.a.b.a.g(this.c);
    }
    
    public boolean e() {
        return android.support.v4.g.a.b.a.j(this.c);
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
            final b b = (b)o;
            if (this.c == null) {
                if (b.c != null) {
                    return false;
                }
            }
            else if (!this.c.equals(b.c)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean f() {
        return android.support.v4.g.a.b.a.k(this.c);
    }
    
    public boolean g() {
        return android.support.v4.g.a.b.a.o(this.c);
    }
    
    public boolean h() {
        return android.support.v4.g.a.b.a.h(this.c);
    }
    
    @Override
    public int hashCode() {
        if (this.c == null) {
            return 0;
        }
        return this.c.hashCode();
    }
    
    public boolean i() {
        return android.support.v4.g.a.b.a.l(this.c);
    }
    
    public boolean j() {
        return android.support.v4.g.a.b.a.i(this.c);
    }
    
    public boolean k() {
        return android.support.v4.g.a.b.a.m(this.c);
    }
    
    public boolean l() {
        return android.support.v4.g.a.b.a.n(this.c);
    }
    
    public CharSequence m() {
        return android.support.v4.g.a.b.a.d(this.c);
    }
    
    public CharSequence n() {
        return android.support.v4.g.a.b.a.b(this.c);
    }
    
    public CharSequence o() {
        return android.support.v4.g.a.b.a.e(this.c);
    }
    
    public CharSequence p() {
        return android.support.v4.g.a.b.a.c(this.c);
    }
    
    public String q() {
        return android.support.v4.g.a.b.a.p(this.c);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        final Rect rect = new Rect();
        this.a(rect);
        sb.append("; boundsInParent: " + rect);
        this.b(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ").append(this.m());
        sb.append("; className: ").append(this.n());
        sb.append("; text: ").append(this.o());
        sb.append("; contentDescription: ").append(this.p());
        sb.append("; viewId: ").append(this.q());
        sb.append("; checkable: ").append(this.c());
        sb.append("; checked: ").append(this.d());
        sb.append("; focusable: ").append(this.e());
        sb.append("; focused: ").append(this.f());
        sb.append("; selected: ").append(this.g());
        sb.append("; clickable: ").append(this.h());
        sb.append("; longClickable: ").append(this.i());
        sb.append("; enabled: ").append(this.j());
        sb.append("; password: ").append(this.k());
        sb.append("; scrollable: " + this.l());
        sb.append("; [");
        int n2;
        for (int i = this.b(); i != 0; i = n2) {
            final int n = 1 << Integer.numberOfTrailingZeros(i);
            n2 = (i & ~n);
            sb.append(b(n));
            if ((i = n2) != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    static class a extends j
    {
    }
    
    static class b extends a
    {
    }
    
    static class c extends b
    {
    }
    
    static class d extends c
    {
    }
    
    static class e extends k
    {
        @Override
        public int a(final Object o) {
            return android.support.v4.g.a.c.a(o);
        }
        
        @Override
        public void a(final Object o, final int n) {
            android.support.v4.g.a.c.a(o, n);
        }
        
        @Override
        public void a(final Object o, final Rect rect) {
            android.support.v4.g.a.c.a(o, rect);
        }
        
        @Override
        public void a(final Object o, final CharSequence charSequence) {
            android.support.v4.g.a.c.a(o, charSequence);
        }
        
        @Override
        public void a(final Object o, final boolean b) {
            android.support.v4.g.a.c.a(o, b);
        }
        
        @Override
        public CharSequence b(final Object o) {
            return android.support.v4.g.a.c.b(o);
        }
        
        @Override
        public void b(final Object o, final Rect rect) {
            android.support.v4.g.a.c.b(o, rect);
        }
        
        @Override
        public CharSequence c(final Object o) {
            return android.support.v4.g.a.c.c(o);
        }
        
        @Override
        public CharSequence d(final Object o) {
            return android.support.v4.g.a.c.d(o);
        }
        
        @Override
        public CharSequence e(final Object o) {
            return android.support.v4.g.a.c.e(o);
        }
        
        @Override
        public boolean f(final Object o) {
            return android.support.v4.g.a.c.f(o);
        }
        
        @Override
        public boolean g(final Object o) {
            return android.support.v4.g.a.c.g(o);
        }
        
        @Override
        public boolean h(final Object o) {
            return android.support.v4.g.a.c.h(o);
        }
        
        @Override
        public boolean i(final Object o) {
            return android.support.v4.g.a.c.i(o);
        }
        
        @Override
        public boolean j(final Object o) {
            return android.support.v4.g.a.c.j(o);
        }
        
        @Override
        public boolean k(final Object o) {
            return android.support.v4.g.a.c.k(o);
        }
        
        @Override
        public boolean l(final Object o) {
            return android.support.v4.g.a.c.l(o);
        }
        
        @Override
        public boolean m(final Object o) {
            return android.support.v4.g.a.c.m(o);
        }
        
        @Override
        public boolean n(final Object o) {
            return android.support.v4.g.a.c.n(o);
        }
        
        @Override
        public boolean o(final Object o) {
            return android.support.v4.g.a.c.o(o);
        }
    }
    
    interface f
    {
        int a(final Object p0);
        
        void a(final Object p0, final int p1);
        
        void a(final Object p0, final Rect p1);
        
        void a(final Object p0, final CharSequence p1);
        
        void a(final Object p0, final boolean p1);
        
        CharSequence b(final Object p0);
        
        void b(final Object p0, final Rect p1);
        
        CharSequence c(final Object p0);
        
        CharSequence d(final Object p0);
        
        CharSequence e(final Object p0);
        
        boolean f(final Object p0);
        
        boolean g(final Object p0);
        
        boolean h(final Object p0);
        
        boolean i(final Object p0);
        
        boolean j(final Object p0);
        
        boolean k(final Object p0);
        
        boolean l(final Object p0);
        
        boolean m(final Object p0);
        
        boolean n(final Object p0);
        
        boolean o(final Object p0);
        
        String p(final Object p0);
    }
    
    static class g extends e
    {
    }
    
    static class h extends g
    {
    }
    
    static class i extends h
    {
        @Override
        public String p(final Object o) {
            return android.support.v4.g.a.d.a(o);
        }
    }
    
    static class j extends i
    {
    }
    
    static class k implements f
    {
        @Override
        public int a(final Object o) {
            return 0;
        }
        
        @Override
        public void a(final Object o, final int n) {
        }
        
        @Override
        public void a(final Object o, final Rect rect) {
        }
        
        @Override
        public void a(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void a(final Object o, final boolean b) {
        }
        
        @Override
        public CharSequence b(final Object o) {
            return null;
        }
        
        @Override
        public void b(final Object o, final Rect rect) {
        }
        
        @Override
        public CharSequence c(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence d(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence e(final Object o) {
            return null;
        }
        
        @Override
        public boolean f(final Object o) {
            return false;
        }
        
        @Override
        public boolean g(final Object o) {
            return false;
        }
        
        @Override
        public boolean h(final Object o) {
            return false;
        }
        
        @Override
        public boolean i(final Object o) {
            return false;
        }
        
        @Override
        public boolean j(final Object o) {
            return false;
        }
        
        @Override
        public boolean k(final Object o) {
            return false;
        }
        
        @Override
        public boolean l(final Object o) {
            return false;
        }
        
        @Override
        public boolean m(final Object o) {
            return false;
        }
        
        @Override
        public boolean n(final Object o) {
            return false;
        }
        
        @Override
        public boolean o(final Object o) {
            return false;
        }
        
        @Override
        public String p(final Object o) {
            return null;
        }
    }
}
