// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.view.View;
import android.support.v7.widget.al;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnKeyListener;

final class t extends m implements o, View$OnKeyListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener
{
    final al a;
    View b;
    private final Context c;
    private final h d;
    private final g e;
    private final boolean f;
    private final int g;
    private final int h;
    private final int i;
    private final ViewTreeObserver$OnGlobalLayoutListener j;
    private PopupWindow$OnDismissListener k;
    private View l;
    private a m;
    private ViewTreeObserver n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private boolean s;
    
    public t(final Context c, final h d, final View l, final int h, final int i, final boolean f) {
        this.j = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (t.this.d() && !t.this.a.g()) {
                    final View b = t.this.b;
                    if (b != null && b.isShown()) {
                        t.this.a.a();
                        return;
                    }
                    t.this.c();
                }
            }
        };
        this.r = 0;
        this.c = c;
        this.d = d;
        this.f = f;
        this.e = new g(d, LayoutInflater.from(c), this.f);
        this.h = h;
        this.i = i;
        final Resources resources = c.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(android.support.v7.a.a.d.abc_config_prefDialogWidth));
        this.l = l;
        this.a = new al(this.c, null, this.h, this.i);
        d.a(this, c);
    }
    
    private boolean h() {
        if (this.d()) {
            return true;
        }
        if (this.o || this.l == null) {
            return false;
        }
        this.b = this.l;
        this.a.a((PopupWindow$OnDismissListener)this);
        this.a.a((AdapterView$OnItemClickListener)this);
        this.a.a(true);
        final View b = this.b;
        int n;
        if (this.n == null) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.n = b.getViewTreeObserver();
        if (n != 0) {
            this.n.addOnGlobalLayoutListener(this.j);
        }
        this.a.b(b);
        this.a.e(this.r);
        if (!this.p) {
            this.q = android.support.v7.view.menu.m.a((ListAdapter)this.e, null, this.c, this.g);
            this.p = true;
        }
        this.a.g(this.q);
        this.a.h(2);
        this.a.a(this.g());
        this.a.a();
        final ListView e = this.a.e();
        e.setOnKeyListener((View$OnKeyListener)this);
        if (this.s && this.d.m() != null) {
            final FrameLayout frameLayout = (FrameLayout)LayoutInflater.from(this.c).inflate(android.support.v7.a.a.g.abc_popup_menu_header_item_layout, (ViewGroup)e, false);
            final TextView textView = (TextView)frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.d.m());
            }
            frameLayout.setEnabled(false);
            e.addHeaderView((View)frameLayout, (Object)null, false);
        }
        this.a.a((ListAdapter)this.e);
        this.a.a();
        return true;
    }
    
    public void a() {
        if (!this.h()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }
    
    @Override
    public void a(final int r) {
        this.r = r;
    }
    
    @Override
    public void a(final h h) {
    }
    
    @Override
    public void a(final h h, final boolean b) {
        if (h == this.d) {
            this.c();
            if (this.m != null) {
                this.m.a(h, b);
            }
        }
    }
    
    @Override
    public void a(final a m) {
        this.m = m;
    }
    
    @Override
    public void a(final View l) {
        this.l = l;
    }
    
    @Override
    public void a(final PopupWindow$OnDismissListener k) {
        this.k = k;
    }
    
    @Override
    public void a(final boolean b) {
        this.e.a(b);
    }
    
    @Override
    public boolean a(final u u) {
        if (u.hasVisibleItems()) {
            final n n = new n(this.c, u, this.b, this.f, this.h, this.i);
            n.a(this.m);
            n.a(android.support.v7.view.menu.m.b(u));
            n.a(this.k);
            this.k = null;
            this.d.a(false);
            if (n.a(this.a.j(), this.a.k())) {
                if (this.m != null) {
                    this.m.a(u);
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void b(final int n) {
        this.a.c(n);
    }
    
    @Override
    public void b(final boolean b) {
        this.p = false;
        if (this.e != null) {
            this.e.notifyDataSetChanged();
        }
    }
    
    @Override
    public boolean b() {
        return false;
    }
    
    public void c() {
        if (this.d()) {
            this.a.c();
        }
    }
    
    @Override
    public void c(final int n) {
        this.a.d(n);
    }
    
    @Override
    public void c(final boolean s) {
        this.s = s;
    }
    
    public boolean d() {
        return !this.o && this.a.d();
    }
    
    public ListView e() {
        return this.a.e();
    }
    
    public void onDismiss() {
        this.o = true;
        this.d.close();
        if (this.n != null) {
            if (!this.n.isAlive()) {
                this.n = this.b.getViewTreeObserver();
            }
            this.n.removeGlobalOnLayoutListener(this.j);
            this.n = null;
        }
        if (this.k != null) {
            this.k.onDismiss();
        }
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 82) {
            this.c();
            return true;
        }
        return false;
    }
}
