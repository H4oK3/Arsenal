// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MenuItem$OnActionExpandListener;
import android.os.Build$VERSION;
import android.view.SubMenu;
import android.view.ViewDebug$CapturedViewProperty;
import android.view.ActionProvider;
import android.content.ActivityNotFoundException;
import android.util.Log;
import android.view.MenuItem;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.ContextMenu$ContextMenuInfo;
import android.support.v4.g.q;
import android.support.v4.g.e;
import android.view.View;
import android.view.MenuItem$OnMenuItemClickListener;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.support.v4.d.a.b;

public final class j implements b
{
    private static String w;
    private static String x;
    private static String y;
    private static String z;
    h a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private Intent h;
    private char i;
    private char j;
    private Drawable k;
    private int l;
    private u m;
    private Runnable n;
    private MenuItem$OnMenuItemClickListener o;
    private int p;
    private int q;
    private View r;
    private e s;
    private q.e t;
    private boolean u;
    private ContextMenu$ContextMenuInfo v;
    
    j(final h a, final int c, final int b, final int d, final int e, final CharSequence f, final int q) {
        this.l = 0;
        this.p = 16;
        this.q = 0;
        this.u = false;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.q = q;
    }
    
    public b a(final int n) {
        final Context e = this.a.e();
        this.a(LayoutInflater.from(e).inflate(n, (ViewGroup)new LinearLayout(e), false));
        return this;
    }
    
    @Override
    public b a(final e s) {
        if (this.s != null) {
            this.s.f();
        }
        this.r = null;
        this.s = s;
        this.a.b(true);
        if (this.s != null) {
            this.s.a((e.b)new e.b() {
                @Override
                public void a(final boolean b) {
                    android.support.v7.view.menu.j.this.a.a(android.support.v7.view.menu.j.this);
                }
            });
        }
        return this;
    }
    
    @Override
    public b a(final q.e t) {
        this.t = t;
        return this;
    }
    
    public b a(final View r) {
        this.r = r;
        this.s = null;
        if (r != null && r.getId() == -1 && this.b > 0) {
            r.setId(this.b);
        }
        this.a.b(this);
        return this;
    }
    
    @Override
    public e a() {
        return this.s;
    }
    
    CharSequence a(final p.a a) {
        if (a != null && a.a()) {
            return this.getTitleCondensed();
        }
        return this.getTitle();
    }
    
    public void a(final u m) {
        (this.m = m).setHeaderTitle(this.getTitle());
    }
    
    void a(final ContextMenu$ContextMenuInfo v) {
        this.v = v;
    }
    
    public void a(final boolean b) {
        final int p = this.p;
        int n;
        if (b) {
            n = 4;
        }
        else {
            n = 0;
        }
        this.p = (n | (p & 0xFFFFFFFB));
    }
    
    public b b(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    void b(final boolean b) {
        final int p = this.p;
        final int p2 = this.p;
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.p = (n | (p2 & 0xFFFFFFFD));
        if (p != this.p) {
            this.a.b(false);
        }
    }
    
    public boolean b() {
        if ((this.o == null || !this.o.onMenuItemClick((MenuItem)this)) && !this.a.a(this.a.p(), (MenuItem)this)) {
            if (this.n != null) {
                this.n.run();
                return true;
            }
            if (this.h != null) {
                try {
                    this.a.e().startActivity(this.h);
                    return true;
                }
                catch (ActivityNotFoundException ex) {
                    Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", (Throwable)ex);
                }
            }
            if (this.s == null || !this.s.d()) {
                return false;
            }
        }
        return true;
    }
    
    public int c() {
        return this.e;
    }
    
    boolean c(final boolean b) {
        final boolean b2 = false;
        final int p = this.p;
        final int p2 = this.p;
        int n;
        if (b) {
            n = 0;
        }
        else {
            n = 8;
        }
        this.p = (n | (p2 & 0xFFFFFFF7));
        boolean b3 = b2;
        if (p != this.p) {
            b3 = true;
        }
        return b3;
    }
    
    @Override
    public boolean collapseActionView() {
        if ((this.q & 0x8) != 0x0) {
            if (this.r == null) {
                return true;
            }
            if (this.t == null || this.t.b((MenuItem)this)) {
                return this.a.d(this);
            }
        }
        return false;
    }
    
    char d() {
        if (this.a.b()) {
            return this.j;
        }
        return this.i;
    }
    
    public void d(final boolean b) {
        if (b) {
            this.p |= 0x20;
            return;
        }
        this.p &= 0xFFFFFFDF;
    }
    
    String e() {
        final char d = this.d();
        if (d == '\0') {
            return "";
        }
        final StringBuilder sb = new StringBuilder(android.support.v7.view.menu.j.w);
        switch (d) {
            default: {
                sb.append(d);
                break;
            }
            case 10: {
                sb.append(android.support.v7.view.menu.j.x);
                break;
            }
            case 8: {
                sb.append(android.support.v7.view.menu.j.y);
                break;
            }
            case 32: {
                sb.append(android.support.v7.view.menu.j.z);
                break;
            }
        }
        return sb.toString();
    }
    
    public void e(final boolean u) {
        this.u = u;
        this.a.b(false);
    }
    
    @Override
    public boolean expandActionView() {
        return this.n() && (this.t == null || this.t.a((MenuItem)this)) && this.a.c(this);
    }
    
    boolean f() {
        return this.a.c() && this.d() != '\0';
    }
    
    public boolean g() {
        return (this.p & 0x4) != 0x0;
    }
    
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }
    
    @Override
    public View getActionView() {
        if (this.r != null) {
            return this.r;
        }
        if (this.s != null) {
            return this.r = this.s.a((MenuItem)this);
        }
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.j;
    }
    
    public int getGroupId() {
        return this.c;
    }
    
    public Drawable getIcon() {
        if (this.k != null) {
            return this.k;
        }
        if (this.l != 0) {
            final Drawable b = android.support.v7.b.a.b.b(this.a.e(), this.l);
            this.l = 0;
            return this.k = b;
        }
        return null;
    }
    
    public Intent getIntent() {
        return this.h;
    }
    
    @ViewDebug$CapturedViewProperty
    public int getItemId() {
        return this.b;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.v;
    }
    
    public char getNumericShortcut() {
        return this.i;
    }
    
    public int getOrder() {
        return this.d;
    }
    
    public SubMenu getSubMenu() {
        return (SubMenu)this.m;
    }
    
    @ViewDebug$CapturedViewProperty
    public CharSequence getTitle() {
        return this.f;
    }
    
    public CharSequence getTitleCondensed() {
        CharSequence charSequence;
        if (this.g != null) {
            charSequence = this.g;
        }
        else {
            charSequence = this.f;
        }
        CharSequence string = charSequence;
        if (Build$VERSION.SDK_INT < 18 && (string = charSequence) != null) {
            string = charSequence;
            if (!(charSequence instanceof String)) {
                string = charSequence.toString();
            }
        }
        return string;
    }
    
    public void h() {
        this.a.b(this);
    }
    
    public boolean hasSubMenu() {
        return this.m != null;
    }
    
    public boolean i() {
        return this.a.q();
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return this.u;
    }
    
    public boolean isCheckable() {
        return (this.p & 0x1) == 0x1;
    }
    
    public boolean isChecked() {
        return (this.p & 0x2) == 0x2;
    }
    
    public boolean isEnabled() {
        return (this.p & 0x10) != 0x0;
    }
    
    public boolean isVisible() {
        if (this.s != null && this.s.b()) {
            if ((this.p & 0x8) != 0x0 || !this.s.c()) {
                return false;
            }
        }
        else if ((this.p & 0x8) != 0x0) {
            return false;
        }
        return true;
    }
    
    public boolean j() {
        return (this.p & 0x20) == 0x20;
    }
    
    public boolean k() {
        return (this.q & 0x1) == 0x1;
    }
    
    public boolean l() {
        return (this.q & 0x2) == 0x2;
    }
    
    public boolean m() {
        return (this.q & 0x4) == 0x4;
    }
    
    public boolean n() {
        boolean b = false;
        if ((this.q & 0x8) != 0x0) {
            if (this.r == null && this.s != null) {
                this.r = this.s.a((MenuItem)this);
            }
            b = b;
            if (this.r != null) {
                b = true;
            }
        }
        return b;
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }
    
    public MenuItem setAlphabeticShortcut(final char c) {
        if (this.j == c) {
            return (MenuItem)this;
        }
        this.j = Character.toLowerCase(c);
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int p = this.p;
        final int p2 = this.p;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.p = ((b2 ? 1 : 0) | (p2 & 0xFFFFFFFE));
        if (p != this.p) {
            this.a.b(false);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        if ((this.p & 0x4) != 0x0) {
            this.a.a((MenuItem)this);
            return (MenuItem)this;
        }
        this.b(b);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        if (b) {
            this.p |= 0x10;
        }
        else {
            this.p &= 0xFFFFFFEF;
        }
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int l) {
        this.k = null;
        this.l = l;
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable k) {
        this.l = 0;
        this.k = k;
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent h) {
        this.h = h;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char i) {
        if (this.i == i) {
            return (MenuItem)this;
        }
        this.i = i;
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener o) {
        this.o = o;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char i, final char c) {
        this.i = i;
        this.j = Character.toLowerCase(c);
        this.a.b(false);
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int q) {
        switch (q & 0x3) {
            default: {
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
            }
            case 0:
            case 1:
            case 2: {
                this.q = q;
                this.a.b(this);
            }
        }
    }
    
    public MenuItem setTitle(final int n) {
        return this.setTitle(this.a.e().getString(n));
    }
    
    public MenuItem setTitle(final CharSequence charSequence) {
        this.f = charSequence;
        this.a.b(false);
        if (this.m != null) {
            this.m.setHeaderTitle(charSequence);
        }
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(CharSequence f) {
        this.g = f;
        if (f == null) {
            f = this.f;
        }
        this.a.b(false);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        if (this.c(b)) {
            this.a.a(this);
        }
        return (MenuItem)this;
    }
    
    @Override
    public String toString() {
        if (this.f != null) {
            return this.f.toString();
        }
        return null;
    }
}
