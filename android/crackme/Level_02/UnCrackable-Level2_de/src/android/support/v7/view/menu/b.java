// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import java.util.ArrayList;
import android.support.v4.g.ae;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;

public abstract class b implements o
{
    protected Context a;
    protected Context b;
    protected h c;
    protected LayoutInflater d;
    protected LayoutInflater e;
    protected p f;
    private a g;
    private int h;
    private int i;
    private int j;
    
    public b(final Context a, final int h, final int i) {
        this.a = a;
        this.d = LayoutInflater.from(a);
        this.h = h;
        this.i = i;
    }
    
    public a a() {
        return this.g;
    }
    
    public p a(final ViewGroup viewGroup) {
        if (this.f == null) {
            (this.f = (p)this.d.inflate(this.h, viewGroup, false)).a(this.c);
            this.b(true);
        }
        return this.f;
    }
    
    public View a(final j j, final View view, final ViewGroup viewGroup) {
        p.a b;
        if (view instanceof p.a) {
            b = (p.a)view;
        }
        else {
            b = this.b(viewGroup);
        }
        this.a(j, b);
        return (View)b;
    }
    
    public void a(final int j) {
        this.j = j;
    }
    
    @Override
    public void a(final Context b, final h c) {
        this.b = b;
        this.e = LayoutInflater.from(this.b);
        this.c = c;
    }
    
    @Override
    public void a(final h h, final boolean b) {
        if (this.g != null) {
            this.g.a(h, b);
        }
    }
    
    public abstract void a(final j p0, final p.a p1);
    
    @Override
    public void a(final a g) {
        this.g = g;
    }
    
    protected void a(final View view, final int n) {
        final ViewGroup viewGroup = (ViewGroup)view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup)this.f).addView(view, n);
    }
    
    public boolean a(final int n, final j j) {
        return true;
    }
    
    @Override
    public boolean a(final h h, final j j) {
        return false;
    }
    
    @Override
    public boolean a(final u u) {
        return this.g != null && this.g.a(u);
    }
    
    protected boolean a(final ViewGroup viewGroup, final int n) {
        viewGroup.removeViewAt(n);
        return true;
    }
    
    public p.a b(final ViewGroup viewGroup) {
        return (p.a)this.d.inflate(this.i, viewGroup, false);
    }
    
    @Override
    public void b(final boolean b) {
        final ViewGroup viewGroup = (ViewGroup)this.f;
        if (viewGroup != null) {
            int j;
            if (this.c != null) {
                this.c.j();
                final ArrayList<j> i = this.c.i();
                final int size = i.size();
                int n = 0;
                int n2 = 0;
                while (true) {
                    j = n2;
                    if (n >= size) {
                        break;
                    }
                    final j k = i.get(n);
                    if (this.a(n2, k)) {
                        final View child = viewGroup.getChildAt(n2);
                        j itemData;
                        if (child instanceof p.a) {
                            itemData = ((p.a)child).getItemData();
                        }
                        else {
                            itemData = null;
                        }
                        final View a = this.a(k, child, viewGroup);
                        if (k != itemData) {
                            a.setPressed(false);
                            ae.l(a);
                        }
                        if (a != child) {
                            this.a(a, n2);
                        }
                        ++n2;
                    }
                    ++n;
                }
            }
            else {
                j = 0;
            }
            while (j < viewGroup.getChildCount()) {
                if (!this.a(viewGroup, j)) {
                    ++j;
                }
            }
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
}
