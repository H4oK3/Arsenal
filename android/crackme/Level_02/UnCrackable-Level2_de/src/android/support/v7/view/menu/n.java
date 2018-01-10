// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.Display;
import android.support.v7.a.a;
import android.os.Build$VERSION;
import android.graphics.Point;
import android.view.WindowManager;
import android.graphics.Rect;
import android.support.v4.g.f;
import android.support.v4.g.ae;
import android.widget.PopupWindow$OnDismissListener;
import android.view.View;
import android.content.Context;

public class n
{
    private final Context a;
    private final h b;
    private final boolean c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private o.a i;
    private m j;
    private PopupWindow$OnDismissListener k;
    private final PopupWindow$OnDismissListener l;
    
    public n(final Context context, final h h, final View view, final boolean b, final int n) {
        this(context, h, view, b, n, 0);
    }
    
    public n(final Context a, final h b, final View f, final boolean c, final int d, final int e) {
        this.g = 8388611;
        this.l = (PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                n.this.e();
            }
        };
        this.a = a;
        this.b = b;
        this.f = f;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    private void a(int n, final int n2, final boolean b, final boolean b2) {
        final m b3 = this.b();
        b3.c(b2);
        if (b) {
            int n3 = n;
            if ((android.support.v4.g.f.a(this.g, ae.c(this.f)) & 0x7) == 0x5) {
                n3 = n - this.f.getWidth();
            }
            b3.b(n3);
            b3.c(n2);
            n = (int)(this.a.getResources().getDisplayMetrics().density * 48.0f / 2.0f);
            b3.a(new Rect(n3 - n, n2 - n, n3 + n, n + n2));
        }
        b3.a();
    }
    
    private m g() {
        final Display defaultDisplay = ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay();
        final Point point = new Point();
        if (Build$VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        }
        else if (Build$VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        }
        else {
            point.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        int n;
        if (Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(android.support.v7.a.a.d.abc_cascading_menus_min_smallest_width)) {
            n = 1;
        }
        else {
            n = 0;
        }
        o o;
        if (n != 0) {
            o = new e(this.a, this.f, this.d, this.e, this.c);
        }
        else {
            o = new t(this.a, this.b, this.f, this.d, this.e, this.c);
        }
        ((m)o).a(this.b);
        ((m)o).a(this.l);
        ((m)o).a(this.f);
        o.a(this.i);
        ((m)o).a(this.h);
        ((m)o).a(this.g);
        return (m)o;
    }
    
    public void a() {
        if (!this.c()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }
    
    public void a(final int g) {
        this.g = g;
    }
    
    public void a(final o.a i) {
        this.i = i;
        if (this.j != null) {
            this.j.a(i);
        }
    }
    
    public void a(final View f) {
        this.f = f;
    }
    
    public void a(final PopupWindow$OnDismissListener k) {
        this.k = k;
    }
    
    public void a(final boolean h) {
        this.h = h;
        if (this.j != null) {
            this.j.a(h);
        }
    }
    
    public boolean a(final int n, final int n2) {
        if (this.f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        this.a(n, n2, true, true);
        return true;
    }
    
    public m b() {
        if (this.j == null) {
            this.j = this.g();
        }
        return this.j;
    }
    
    public boolean c() {
        if (this.f()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        this.a(0, 0, false, false);
        return true;
    }
    
    public void d() {
        if (this.f()) {
            this.j.c();
        }
    }
    
    protected void e() {
        this.j = null;
        if (this.k != null) {
            this.k.onDismiss();
        }
    }
    
    public boolean f() {
        return this.j != null && this.j.d();
    }
}
