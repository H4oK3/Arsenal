// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.view.MenuItem;
import android.support.v4.f.j;
import java.util.ArrayList;
import android.view.ActionMode$Callback;
import android.view.MenuInflater;
import android.support.v7.view.menu.q;
import android.support.v4.d.a.a;
import android.view.Menu;
import android.view.View;
import android.content.Context;
import android.annotation.TargetApi;
import android.view.ActionMode;

@TargetApi(11)
public class f extends ActionMode
{
    final Context a;
    final b b;
    
    public f(final Context a, final b b) {
        this.a = a;
        this.b = b;
    }
    
    public void finish() {
        this.b.c();
    }
    
    public View getCustomView() {
        return this.b.i();
    }
    
    public Menu getMenu() {
        return q.a(this.a, (android.support.v4.d.a.a)this.b.b());
    }
    
    public MenuInflater getMenuInflater() {
        return this.b.a();
    }
    
    public CharSequence getSubtitle() {
        return this.b.g();
    }
    
    public Object getTag() {
        return this.b.j();
    }
    
    public CharSequence getTitle() {
        return this.b.f();
    }
    
    public boolean getTitleOptionalHint() {
        return this.b.k();
    }
    
    public void invalidate() {
        this.b.d();
    }
    
    public boolean isTitleOptional() {
        return this.b.h();
    }
    
    public void setCustomView(final View view) {
        this.b.a(view);
    }
    
    public void setSubtitle(final int n) {
        this.b.b(n);
    }
    
    public void setSubtitle(final CharSequence charSequence) {
        this.b.a(charSequence);
    }
    
    public void setTag(final Object o) {
        this.b.a(o);
    }
    
    public void setTitle(final int n) {
        this.b.a(n);
    }
    
    public void setTitle(final CharSequence charSequence) {
        this.b.b(charSequence);
    }
    
    public void setTitleOptionalHint(final boolean b) {
        this.b.a(b);
    }
    
    public static class a implements b.a
    {
        final ActionMode$Callback a;
        final Context b;
        final ArrayList<f> c;
        final j<Menu, Menu> d;
        
        public a(final Context b, final ActionMode$Callback a) {
            this.b = b;
            this.a = a;
            this.c = new ArrayList<f>();
            this.d = new j<Menu, Menu>();
        }
        
        private Menu a(final Menu menu) {
            Menu a;
            if ((a = this.d.get(menu)) == null) {
                a = q.a(this.b, (android.support.v4.d.a.a)menu);
                this.d.put(menu, a);
            }
            return a;
        }
        
        @Override
        public void a(final b b) {
            this.a.onDestroyActionMode(this.b(b));
        }
        
        @Override
        public boolean a(final b b, final Menu menu) {
            return this.a.onCreateActionMode(this.b(b), this.a(menu));
        }
        
        @Override
        public boolean a(final b b, final MenuItem menuItem) {
            return this.a.onActionItemClicked(this.b(b), q.a(this.b, (android.support.v4.d.a.b)menuItem));
        }
        
        public ActionMode b(final b b) {
            for (int size = this.c.size(), i = 0; i < size; ++i) {
                final f f = this.c.get(i);
                if (f != null && f.b == b) {
                    return f;
                }
            }
            final f f2 = new f(this.b, b);
            this.c.add(f2);
            return f2;
        }
        
        @Override
        public boolean b(final b b, final Menu menu) {
            return this.a.onPrepareActionMode(this.b(b), this.a(menu));
        }
    }
}
