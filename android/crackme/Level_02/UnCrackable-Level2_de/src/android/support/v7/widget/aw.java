// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.Log;
import android.content.Context;
import android.view.Menu;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.support.v7.b.a.b;
import android.support.v4.g.ax;
import android.support.v4.g.ae;
import android.support.v4.g.as;
import android.view.ViewGroup$LayoutParams;
import android.view.MenuItem;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window$Callback;

public class aw implements ac
{
    Toolbar a;
    CharSequence b;
    Window$Callback c;
    boolean d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private d n;
    private int o;
    private int p;
    private Drawable q;
    
    public aw(final Toolbar toolbar, final boolean b) {
        this(toolbar, b, android.support.v7.a.a.h.abc_action_bar_up_description, android.support.v7.a.a.e.abc_ic_ab_back_material);
    }
    
    public aw(final Toolbar a, final boolean b, final int n, int n2) {
        this.o = 0;
        this.p = 0;
        this.a = a;
        this.b = a.getTitle();
        this.l = a.getSubtitle();
        this.k = (this.b != null);
        this.j = a.getNavigationIcon();
        final av a2 = av.a(a.getContext(), null, android.support.v7.a.a.j.ActionBar, android.support.v7.a.a.a.actionBarStyle, 0);
        this.q = a2.a(android.support.v7.a.a.j.ActionBar_homeAsUpIndicator);
        if (b) {
            final CharSequence c = a2.c(android.support.v7.a.a.j.ActionBar_title);
            if (!TextUtils.isEmpty(c)) {
                this.b(c);
            }
            final CharSequence c2 = a2.c(android.support.v7.a.a.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(c2)) {
                this.c(c2);
            }
            final Drawable a3 = a2.a(android.support.v7.a.a.j.ActionBar_logo);
            if (a3 != null) {
                this.b(a3);
            }
            final Drawable a4 = a2.a(android.support.v7.a.a.j.ActionBar_icon);
            if (a4 != null) {
                this.a(a4);
            }
            if (this.j == null && this.q != null) {
                this.c(this.q);
            }
            this.c(a2.a(android.support.v7.a.a.j.ActionBar_displayOptions, 0));
            n2 = a2.g(android.support.v7.a.a.j.ActionBar_customNavigationLayout, 0);
            if (n2 != 0) {
                this.a(LayoutInflater.from(this.a.getContext()).inflate(n2, (ViewGroup)this.a, false));
                this.c(this.e | 0x10);
            }
            n2 = a2.f(android.support.v7.a.a.j.ActionBar_height, 0);
            if (n2 > 0) {
                final ViewGroup$LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = n2;
                this.a.setLayoutParams(layoutParams);
            }
            n2 = a2.d(android.support.v7.a.a.j.ActionBar_contentInsetStart, -1);
            final int d = a2.d(android.support.v7.a.a.j.ActionBar_contentInsetEnd, -1);
            if (n2 >= 0 || d >= 0) {
                this.a.a(Math.max(n2, 0), Math.max(d, 0));
            }
            n2 = a2.g(android.support.v7.a.a.j.ActionBar_titleTextStyle, 0);
            if (n2 != 0) {
                this.a.a(this.a.getContext(), n2);
            }
            n2 = a2.g(android.support.v7.a.a.j.ActionBar_subtitleTextStyle, 0);
            if (n2 != 0) {
                this.a.b(this.a.getContext(), n2);
            }
            n2 = a2.g(android.support.v7.a.a.j.ActionBar_popupTheme, 0);
            if (n2 != 0) {
                this.a.setPopupTheme(n2);
            }
        }
        else {
            this.e = this.s();
        }
        a2.a();
        this.e(n);
        this.m = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener((View$OnClickListener)new View$OnClickListener() {
            final android.support.v7.view.menu.a a = new android.support.v7.view.menu.a(aw.this.a.getContext(), 0, 16908332, 0, 0, aw.this.b);
            
            public void onClick(final View view) {
                if (aw.this.c != null && aw.this.d) {
                    aw.this.c.onMenuItemSelected(0, (MenuItem)this.a);
                }
            }
        });
    }
    
    private void e(final CharSequence charSequence) {
        this.b = charSequence;
        if ((this.e & 0x8) != 0x0) {
            this.a.setTitle(charSequence);
        }
    }
    
    private int s() {
        int n = 11;
        if (this.a.getNavigationIcon() != null) {
            n = 15;
            this.q = this.a.getNavigationIcon();
        }
        return n;
    }
    
    private void t() {
        Drawable logo = null;
        if ((this.e & 0x2) != 0x0) {
            if ((this.e & 0x1) != 0x0) {
                if (this.i != null) {
                    logo = this.i;
                }
                else {
                    logo = this.h;
                }
            }
            else {
                logo = this.h;
            }
        }
        this.a.setLogo(logo);
    }
    
    private void u() {
        if ((this.e & 0x4) != 0x0) {
            final Toolbar a = this.a;
            Drawable navigationIcon;
            if (this.j != null) {
                navigationIcon = this.j;
            }
            else {
                navigationIcon = this.q;
            }
            a.setNavigationIcon(navigationIcon);
            return;
        }
        this.a.setNavigationIcon(null);
    }
    
    private void v() {
        if ((this.e & 0x4) != 0x0) {
            if (!TextUtils.isEmpty(this.m)) {
                this.a.setNavigationContentDescription(this.m);
                return;
            }
            this.a.setNavigationContentDescription(this.p);
        }
    }
    
    @Override
    public as a(final int n, final long n2) {
        final as h = ae.h((View)this.a);
        float n3;
        if (n == 0) {
            n3 = 1.0f;
        }
        else {
            n3 = 0.0f;
        }
        return h.a(n3).a(n2).a(new ax() {
            private boolean c = false;
            
            @Override
            public void a(final View view) {
                aw.this.a.setVisibility(0);
            }
            
            @Override
            public void b(final View view) {
                if (!this.c) {
                    aw.this.a.setVisibility(n);
                }
            }
            
            @Override
            public void c(final View view) {
                this.c = true;
            }
        });
    }
    
    @Override
    public ViewGroup a() {
        return this.a;
    }
    
    @Override
    public void a(final int n) {
        Drawable b;
        if (n != 0) {
            b = android.support.v7.b.a.b.b(this.b(), n);
        }
        else {
            b = null;
        }
        this.a(b);
    }
    
    @Override
    public void a(final Drawable h) {
        this.h = h;
        this.t();
    }
    
    @Override
    public void a(final o.a a, final h.a a2) {
        this.a.a(a, a2);
    }
    
    @Override
    public void a(final ao f) {
        if (this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }
        if ((this.f = (View)f) != null && this.o == 2) {
            this.a.addView(this.f, 0);
            final Toolbar.b b = (Toolbar.b)this.f.getLayoutParams();
            b.width = -2;
            b.height = -2;
            b.a = 8388691;
            f.setAllowCollapse(true);
        }
    }
    
    @Override
    public void a(final Menu menu, final o.a a) {
        if (this.n == null) {
            (this.n = new d(this.a.getContext())).a(android.support.v7.a.a.f.action_menu_presenter);
        }
        this.n.a(a);
        this.a.a((h)menu, this.n);
    }
    
    public void a(final View g) {
        if (this.g != null && (this.e & 0x10) != 0x0) {
            this.a.removeView(this.g);
        }
        if ((this.g = g) != null && (this.e & 0x10) != 0x0) {
            this.a.addView(this.g);
        }
    }
    
    @Override
    public void a(final Window$Callback c) {
        this.c = c;
    }
    
    @Override
    public void a(final CharSequence charSequence) {
        if (!this.k) {
            this.e(charSequence);
        }
    }
    
    @Override
    public void a(final boolean collapsible) {
        this.a.setCollapsible(collapsible);
    }
    
    @Override
    public Context b() {
        return this.a.getContext();
    }
    
    @Override
    public void b(final int n) {
        Drawable b;
        if (n != 0) {
            b = android.support.v7.b.a.b.b(this.b(), n);
        }
        else {
            b = null;
        }
        this.b(b);
    }
    
    public void b(final Drawable i) {
        this.i = i;
        this.t();
    }
    
    public void b(final CharSequence charSequence) {
        this.k = true;
        this.e(charSequence);
    }
    
    @Override
    public void b(final boolean b) {
    }
    
    @Override
    public void c(final int e) {
        final int n = this.e ^ e;
        this.e = e;
        if (n != 0) {
            if ((n & 0x4) != 0x0) {
                if ((e & 0x4) != 0x0) {
                    this.v();
                }
                this.u();
            }
            if ((n & 0x3) != 0x0) {
                this.t();
            }
            if ((n & 0x8) != 0x0) {
                if ((e & 0x8) != 0x0) {
                    this.a.setTitle(this.b);
                    this.a.setSubtitle(this.l);
                }
                else {
                    this.a.setTitle(null);
                    this.a.setSubtitle(null);
                }
            }
            if ((n & 0x10) != 0x0 && this.g != null) {
                if ((e & 0x10) == 0x0) {
                    this.a.removeView(this.g);
                    return;
                }
                this.a.addView(this.g);
            }
        }
    }
    
    public void c(final Drawable j) {
        this.j = j;
        this.u();
    }
    
    public void c(final CharSequence charSequence) {
        this.l = charSequence;
        if ((this.e & 0x8) != 0x0) {
            this.a.setSubtitle(charSequence);
        }
    }
    
    @Override
    public boolean c() {
        return this.a.g();
    }
    
    @Override
    public void d() {
        this.a.h();
    }
    
    @Override
    public void d(final int visibility) {
        this.a.setVisibility(visibility);
    }
    
    public void d(final CharSequence m) {
        this.m = m;
        this.v();
    }
    
    @Override
    public CharSequence e() {
        return this.a.getTitle();
    }
    
    public void e(final int p) {
        if (p != this.p) {
            this.p = p;
            if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
                this.f(this.p);
            }
        }
    }
    
    @Override
    public void f() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }
    
    public void f(final int n) {
        CharSequence string;
        if (n == 0) {
            string = null;
        }
        else {
            string = this.b().getString(n);
        }
        this.d(string);
    }
    
    @Override
    public void g() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }
    
    @Override
    public boolean h() {
        return this.a.a();
    }
    
    @Override
    public boolean i() {
        return this.a.b();
    }
    
    @Override
    public boolean j() {
        return this.a.c();
    }
    
    @Override
    public boolean k() {
        return this.a.d();
    }
    
    @Override
    public boolean l() {
        return this.a.e();
    }
    
    @Override
    public void m() {
        this.d = true;
    }
    
    @Override
    public void n() {
        this.a.f();
    }
    
    @Override
    public int o() {
        return this.e;
    }
    
    @Override
    public int p() {
        return this.o;
    }
    
    @Override
    public int q() {
        return this.a.getVisibility();
    }
    
    @Override
    public Menu r() {
        return this.a.getMenu();
    }
}
