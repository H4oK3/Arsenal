// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.os.IBinder;
import android.view.ContextThemeWrapper;
import android.widget.ListAdapter;
import android.support.v7.a.a;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.AdapterView$OnItemClickListener;

public class f implements o, AdapterView$OnItemClickListener
{
    Context a;
    LayoutInflater b;
    h c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    a h;
    private o.a i;
    
    public f(final int g, final int f) {
        this.g = g;
        this.f = f;
    }
    
    public f(final Context a, final int n) {
        this(n, 0);
        this.a = a;
        this.b = LayoutInflater.from(this.a);
    }
    
    public p a(final ViewGroup viewGroup) {
        if (this.d == null) {
            this.d = (ExpandedMenuView)this.b.inflate(android.support.v7.a.a.g.abc_expanded_menu_layout, viewGroup, false);
            if (this.h == null) {
                this.h = new a();
            }
            this.d.setAdapter((ListAdapter)this.h);
            this.d.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        }
        return this.d;
    }
    
    public ListAdapter a() {
        if (this.h == null) {
            this.h = new a();
        }
        return (ListAdapter)this.h;
    }
    
    @Override
    public void a(final Context a, final h c) {
        if (this.f != 0) {
            this.a = (Context)new ContextThemeWrapper(a, this.f);
            this.b = LayoutInflater.from(this.a);
        }
        else if (this.a != null) {
            this.a = a;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = c;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }
    
    @Override
    public void a(final h h, final boolean b) {
        if (this.i != null) {
            this.i.a(h, b);
        }
    }
    
    @Override
    public void a(final o.a i) {
        this.i = i;
    }
    
    @Override
    public boolean a(final h h, final j j) {
        return false;
    }
    
    @Override
    public boolean a(final u u) {
        if (!u.hasVisibleItems()) {
            return false;
        }
        new i(u).a((IBinder)null);
        if (this.i != null) {
            this.i.a(u);
        }
        return true;
    }
    
    @Override
    public void b(final boolean b) {
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }
    
    @Override
    public boolean b() {
        return false;
    }
    
    @Override
    public boolean b(final h h, final j j) {
        return false;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.c.a((MenuItem)this.h.a(n), this, 0);
    }
    
    private class a extends BaseAdapter
    {
        private int b;
        
        public a() {
            this.b = -1;
            this.a();
        }
        
        public j a(int n) {
            final ArrayList<j> l = android.support.v7.view.menu.f.this.c.l();
            final int n2 = n += android.support.v7.view.menu.f.this.e;
            if (this.b >= 0 && (n = n2) >= this.b) {
                n = n2 + 1;
            }
            return l.get(n);
        }
        
        void a() {
            final j r = android.support.v7.view.menu.f.this.c.r();
            if (r != null) {
                final ArrayList<j> l = android.support.v7.view.menu.f.this.c.l();
                for (int size = l.size(), i = 0; i < size; ++i) {
                    if (l.get(i) == r) {
                        this.b = i;
                        return;
                    }
                }
            }
            this.b = -1;
        }
        
        public int getCount() {
            final int n = android.support.v7.view.menu.f.this.c.l().size() - android.support.v7.view.menu.f.this.e;
            if (this.b < 0) {
                return n;
            }
            return n - 1;
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, View inflate, final ViewGroup viewGroup) {
            if (inflate == null) {
                inflate = android.support.v7.view.menu.f.this.b.inflate(android.support.v7.view.menu.f.this.g, viewGroup, false);
            }
            ((p.a)inflate).a(this.a(n), 0);
            return inflate;
        }
        
        public void notifyDataSetChanged() {
            this.a();
            super.notifyDataSetChanged();
        }
    }
}
