// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.KeyEvent;
import android.support.v4.g.f;
import android.support.v4.g.ae;
import android.widget.AdapterView$OnItemClickListener;
import android.util.AttributeSet;
import android.graphics.Rect;
import android.support.v7.widget.al;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.HeaderViewListAdapter;
import android.content.res.Resources;
import android.support.v7.a.a;
import android.os.SystemClock;
import android.view.MenuItem;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import android.view.ViewTreeObserver;
import android.support.v7.widget.ak;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.view.View;
import java.util.List;
import android.os.Handler;
import android.widget.PopupWindow$OnDismissListener;
import android.view.View$OnKeyListener;

final class e extends m implements o, View$OnKeyListener, PopupWindow$OnDismissListener
{
    final Handler a;
    final List<a> b;
    View c;
    boolean d;
    private final Context e;
    private final int f;
    private final int g;
    private final int h;
    private final boolean i;
    private final List<h> j;
    private final ViewTreeObserver$OnGlobalLayoutListener k;
    private final ak l;
    private int m;
    private int n;
    private View o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private o.a w;
    private ViewTreeObserver x;
    private PopupWindow$OnDismissListener y;
    
    public e(final Context e, final View o, final int g, final int h, final boolean i) {
        this.j = new LinkedList<h>();
        this.b = new ArrayList<a>();
        this.k = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (android.support.v7.view.menu.e.this.d() && android.support.v7.view.menu.e.this.b.size() > 0 && !((a)android.support.v7.view.menu.e.this.b.get(0)).a.g()) {
                    final View c = android.support.v7.view.menu.e.this.c;
                    if (c == null || !c.isShown()) {
                        android.support.v7.view.menu.e.this.c();
                    }
                    else {
                        final Iterator<a> iterator = android.support.v7.view.menu.e.this.b.iterator();
                        while (iterator.hasNext()) {
                            ((a)iterator.next()).a.a();
                        }
                    }
                }
            }
        };
        this.l = new ak() {
            @Override
            public void a(final h h, final MenuItem menuItem) {
                android.support.v7.view.menu.e.this.a.removeCallbacksAndMessages((Object)h);
            }
            
            @Override
            public void b(final h h, final MenuItem menuItem) {
                android.support.v7.view.menu.e.this.a.removeCallbacksAndMessages((Object)null);
                int i = 0;
                while (true) {
                    while (i < android.support.v7.view.menu.e.this.b.size()) {
                        if (h == ((a)android.support.v7.view.menu.e.this.b.get(i)).b) {
                            if (i == -1) {
                                return;
                            }
                            final int n = i + 1;
                            a a;
                            if (n < android.support.v7.view.menu.e.this.b.size()) {
                                a = (a)android.support.v7.view.menu.e.this.b.get(n);
                            }
                            else {
                                a = null;
                            }
                            android.support.v7.view.menu.e.this.a.postAtTime((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (a != null) {
                                        android.support.v7.view.menu.e.this.d = true;
                                        a.b.a(false);
                                        android.support.v7.view.menu.e.this.d = false;
                                    }
                                    if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                                        h.a(menuItem, 4);
                                    }
                                }
                            }, (Object)h, SystemClock.uptimeMillis() + 200L);
                            return;
                        }
                        else {
                            ++i;
                        }
                    }
                    i = -1;
                    continue;
                }
            }
        };
        this.m = 0;
        this.n = 0;
        this.e = e;
        this.o = o;
        this.g = g;
        this.h = h;
        this.i = i;
        this.u = false;
        this.p = this.i();
        final Resources resources = e.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(android.support.v7.a.a.d.abc_config_prefDialogWidth));
        this.a = new Handler();
    }
    
    private MenuItem a(final h h, final h h2) {
        for (int size = h.size(), i = 0; i < size; ++i) {
            final MenuItem item = h.getItem(i);
            if (item.hasSubMenu() && h2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }
    
    private View a(final a a, final h h) {
        int i = 0;
        final MenuItem a2 = this.a(a.b, h);
        if (a2 == null) {
            return null;
        }
        final ListView a3 = a.a();
        final ListAdapter adapter = a3.getAdapter();
        int headersCount;
        g g;
        if (adapter instanceof HeaderViewListAdapter) {
            final HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter)adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            g = (g)headerViewListAdapter.getWrappedAdapter();
        }
        else {
            g = (g)adapter;
            headersCount = 0;
        }
        while (true) {
            while (i < g.getCount()) {
                if (a2 == g.a(i)) {
                    if (i == -1) {
                        return null;
                    }
                    final int n = i + headersCount - a3.getFirstVisiblePosition();
                    if (n < 0 || n >= a3.getChildCount()) {
                        return null;
                    }
                    return a3.getChildAt(n);
                }
                else {
                    ++i;
                }
            }
            i = -1;
            continue;
        }
    }
    
    private void c(final h h) {
        final LayoutInflater from = LayoutInflater.from(this.e);
        final g g = new g(h, from, this.i);
        if (!this.d() && this.u) {
            g.a(true);
        }
        else if (this.d()) {
            g.a(android.support.v7.view.menu.m.b(h));
        }
        final int a = android.support.v7.view.menu.m.a((ListAdapter)g, null, this.e, this.f);
        final al h2 = this.h();
        h2.a((ListAdapter)g);
        h2.g(a);
        h2.e(this.n);
        a a2;
        View a3;
        if (this.b.size() > 0) {
            a2 = this.b.get(this.b.size() - 1);
            a3 = this.a(a2, h);
        }
        else {
            a3 = null;
            a2 = null;
        }
        if (a3 != null) {
            h2.b(false);
            h2.a((Object)null);
            final int d = this.d(a);
            boolean b;
            if (d == 1) {
                b = true;
            }
            else {
                b = false;
            }
            this.p = d;
            final int[] array = new int[2];
            a3.getLocationInWindow(array);
            final int n = a2.a.j() + array[0];
            final int k = a2.a.k();
            final int n2 = array[1];
            int n3;
            if ((this.n & 0x5) == 0x5) {
                if (b) {
                    n3 = n + a;
                }
                else {
                    n3 = n - a3.getWidth();
                }
            }
            else if (b) {
                n3 = a3.getWidth() + n;
            }
            else {
                n3 = n - a;
            }
            h2.c(n3);
            h2.d(n2 + k);
        }
        else {
            if (this.q) {
                h2.c(this.s);
            }
            if (this.r) {
                h2.d(this.t);
            }
            h2.a(this.g());
        }
        this.b.add(new a(h2, h, this.p));
        h2.a();
        if (a2 == null && this.v && h.m() != null) {
            final ListView e = h2.e();
            final FrameLayout frameLayout = (FrameLayout)from.inflate(android.support.v7.a.a.g.abc_popup_menu_header_item_layout, (ViewGroup)e, false);
            final TextView textView = (TextView)frameLayout.findViewById(16908310);
            frameLayout.setEnabled(false);
            textView.setText(h.m());
            e.addHeaderView((View)frameLayout, (Object)null, false);
            h2.a();
        }
    }
    
    private int d(final int n) {
        final ListView a = this.b.get(this.b.size() - 1).a();
        final int[] array = new int[2];
        a.getLocationOnScreen(array);
        final Rect rect = new Rect();
        this.c.getWindowVisibleDisplayFrame(rect);
        if (this.p == 1) {
            if (a.getWidth() + array[0] + n > rect.right) {
                return 0;
            }
            return 1;
        }
        else {
            if (array[0] - n < 0) {
                return 1;
            }
            return 0;
        }
    }
    
    private int d(final h h) {
        for (int i = 0; i < this.b.size(); ++i) {
            if (h == this.b.get(i).b) {
                return i;
            }
        }
        return -1;
    }
    
    private al h() {
        final al al = new al(this.e, null, this.g, this.h);
        al.a(this.l);
        al.a((AdapterView$OnItemClickListener)this);
        al.a((PopupWindow$OnDismissListener)this);
        al.b(this.o);
        al.e(this.n);
        al.a(true);
        return al;
    }
    
    private int i() {
        int n = 1;
        if (ae.c(this.o) == 1) {
            n = 0;
        }
        return n;
    }
    
    public void a() {
        if (!this.d()) {
            final Iterator<h> iterator = this.j.iterator();
            while (iterator.hasNext()) {
                this.c(iterator.next());
            }
            this.j.clear();
            this.c = this.o;
            if (this.c != null) {
                int n;
                if (this.x == null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                this.x = this.c.getViewTreeObserver();
                if (n != 0) {
                    this.x.addOnGlobalLayoutListener(this.k);
                }
            }
        }
    }
    
    @Override
    public void a(final int m) {
        if (this.m != m) {
            this.m = m;
            this.n = android.support.v4.g.f.a(m, ae.c(this.o));
        }
    }
    
    @Override
    public void a(final h h) {
        h.a(this, this.e);
        if (this.d()) {
            this.c(h);
            return;
        }
        this.j.add(h);
    }
    
    @Override
    public void a(final h h, final boolean b) {
        final int d = this.d(h);
        if (d >= 0) {
            final int n = d + 1;
            if (n < this.b.size()) {
                this.b.get(n).b.a(false);
            }
            final a a = this.b.remove(d);
            a.b.b(this);
            if (this.d) {
                a.a.b((Object)null);
                a.a.b(0);
            }
            a.a.c();
            final int size = this.b.size();
            if (size > 0) {
                this.p = this.b.get(size - 1).c;
            }
            else {
                this.p = this.i();
            }
            if (size == 0) {
                this.c();
                if (this.w != null) {
                    this.w.a(h, true);
                }
                if (this.x != null) {
                    if (this.x.isAlive()) {
                        this.x.removeGlobalOnLayoutListener(this.k);
                    }
                    this.x = null;
                }
                this.y.onDismiss();
                return;
            }
            if (b) {
                this.b.get(0).b.a(false);
            }
        }
    }
    
    @Override
    public void a(final o.a w) {
        this.w = w;
    }
    
    @Override
    public void a(final View o) {
        if (this.o != o) {
            this.o = o;
            this.n = android.support.v4.g.f.a(this.m, ae.c(this.o));
        }
    }
    
    @Override
    public void a(final PopupWindow$OnDismissListener y) {
        this.y = y;
    }
    
    @Override
    public void a(final boolean u) {
        this.u = u;
    }
    
    @Override
    public boolean a(final u u) {
        for (final a a : this.b) {
            if (u == a.b) {
                a.a().requestFocus();
                return true;
            }
        }
        if (u.hasVisibleItems()) {
            this.a((h)u);
            if (this.w != null) {
                this.w.a(u);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void b(final int s) {
        this.q = true;
        this.s = s;
    }
    
    @Override
    public void b(final boolean b) {
        final Iterator<a> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            android.support.v7.view.menu.m.a(iterator.next().a().getAdapter()).notifyDataSetChanged();
        }
    }
    
    @Override
    public boolean b() {
        return false;
    }
    
    public void c() {
        final int size = this.b.size();
        if (size > 0) {
            final a[] array = this.b.toArray(new a[size]);
            for (int i = size - 1; i >= 0; --i) {
                final a a = array[i];
                if (a.a.d()) {
                    a.a.c();
                }
            }
        }
    }
    
    @Override
    public void c(final int t) {
        this.r = true;
        this.t = t;
    }
    
    @Override
    public void c(final boolean v) {
        this.v = v;
    }
    
    public boolean d() {
        return this.b.size() > 0 && this.b.get(0).a.d();
    }
    
    public ListView e() {
        if (this.b.isEmpty()) {
            return null;
        }
        return this.b.get(this.b.size() - 1).a();
    }
    
    @Override
    protected boolean f() {
        return false;
    }
    
    public void onDismiss() {
        while (true) {
            for (int size = this.b.size(), i = 0; i < size; ++i) {
                final a a = this.b.get(i);
                if (!a.a.d()) {
                    if (a != null) {
                        a.b.a(false);
                    }
                    return;
                }
            }
            final a a = null;
            continue;
        }
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && n == 82) {
            this.c();
            return true;
        }
        return false;
    }
    
    private static class a
    {
        public final al a;
        public final h b;
        public final int c;
        
        public a(final al a, final h b, final int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public ListView a() {
            return this.a.e();
        }
    }
}
