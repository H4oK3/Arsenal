// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import java.util.Iterator;
import android.support.v4.f.a;
import android.view.SubMenu;
import android.view.MenuItem;
import android.support.v4.d.a.b;
import java.util.Map;
import android.content.Context;

abstract class c<T> extends d<T>
{
    final Context a;
    private Map<b, MenuItem> c;
    private Map<android.support.v4.d.a.c, SubMenu> d;
    
    c(final Context a, final T t) {
        super(t);
        this.a = a;
    }
    
    final MenuItem a(MenuItem a) {
        if (a instanceof b) {
            final b b = (b)a;
            if (this.c == null) {
                this.c = new a<b, MenuItem>();
            }
            if ((a = this.c.get(a)) == null) {
                a = q.a(this.a, b);
                this.c.put(b, a);
            }
            return a;
        }
        return a;
    }
    
    final SubMenu a(SubMenu a) {
        if (a instanceof android.support.v4.d.a.c) {
            final android.support.v4.d.a.c c = (android.support.v4.d.a.c)a;
            if (this.d == null) {
                this.d = new a<android.support.v4.d.a.c, SubMenu>();
            }
            if ((a = this.d.get(c)) == null) {
                a = q.a(this.a, c);
                this.d.put(c, a);
            }
            return a;
        }
        return a;
    }
    
    final void a() {
        if (this.c != null) {
            this.c.clear();
        }
        if (this.d != null) {
            this.d.clear();
        }
    }
    
    final void a(final int n) {
        if (this.c != null) {
            final Iterator<b> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == ((MenuItem)iterator.next()).getGroupId()) {
                    iterator.remove();
                }
            }
        }
    }
    
    final void b(final int n) {
        if (this.c != null) {
            final Iterator<b> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (n == ((MenuItem)iterator.next()).getItemId()) {
                    iterator.remove();
                }
            }
        }
    }
}
