// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.support.v7.a.a;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public class g extends BaseAdapter
{
    static final int a;
    h b;
    private int c;
    private boolean d;
    private final boolean e;
    private final LayoutInflater f;
    
    static {
        a = android.support.v7.a.a.g.abc_popup_menu_item_layout;
    }
    
    public g(final h b, final LayoutInflater f, final boolean e) {
        this.c = -1;
        this.e = e;
        this.f = f;
        this.b = b;
        this.b();
    }
    
    public h a() {
        return this.b;
    }
    
    public j a(final int n) {
        ArrayList<j> list;
        if (this.e) {
            list = this.b.l();
        }
        else {
            list = this.b.i();
        }
        int n2 = n;
        if (this.c >= 0 && (n2 = n) >= this.c) {
            n2 = n + 1;
        }
        return list.get(n2);
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    void b() {
        final j r = this.b.r();
        if (r != null) {
            final ArrayList<j> l = this.b.l();
            for (int size = l.size(), i = 0; i < size; ++i) {
                if (l.get(i) == r) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }
    
    public int getCount() {
        ArrayList<j> list;
        if (this.e) {
            list = this.b.l();
        }
        else {
            list = this.b.i();
        }
        if (this.c < 0) {
            return list.size();
        }
        return list.size() - 1;
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        if (inflate == null) {
            inflate = this.f.inflate(g.a, viewGroup, false);
        }
        final p.a a = (p.a)inflate;
        if (this.d) {
            ((ListMenuItemView)inflate).setForceShowIcon(true);
        }
        a.a(this.a(n), 0);
        return inflate;
    }
    
    public void notifyDataSetChanged() {
        this.b();
        super.notifyDataSetChanged();
    }
}
