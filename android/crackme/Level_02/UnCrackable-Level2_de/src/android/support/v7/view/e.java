// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import java.lang.ref.WeakReference;
import android.support.v7.widget.ActionBarContextView;
import android.content.Context;
import android.support.v7.view.menu.h;

public class e extends b implements h.a
{
    private Context a;
    private ActionBarContextView b;
    private a c;
    private WeakReference<View> d;
    private boolean e;
    private boolean f;
    private h g;
    
    public e(final Context a, final ActionBarContextView b, final a c, final boolean f) {
        this.a = a;
        this.b = b;
        this.c = c;
        (this.g = new h(b.getContext()).a(1)).a((h.a)this);
        this.f = f;
    }
    
    @Override
    public MenuInflater a() {
        return new g(this.b.getContext());
    }
    
    @Override
    public void a(final int n) {
        this.b(this.a.getString(n));
    }
    
    @Override
    public void a(final h h) {
        this.d();
        this.b.a();
    }
    
    @Override
    public void a(final View customView) {
        this.b.setCustomView(customView);
        WeakReference<View> d;
        if (customView != null) {
            d = new WeakReference<View>(customView);
        }
        else {
            d = null;
        }
        this.d = d;
    }
    
    @Override
    public void a(final CharSequence subtitle) {
        this.b.setSubtitle(subtitle);
    }
    
    @Override
    public void a(final boolean titleOptional) {
        super.a(titleOptional);
        this.b.setTitleOptional(titleOptional);
    }
    
    @Override
    public boolean a(final h h, final MenuItem menuItem) {
        return this.c.a(this, menuItem);
    }
    
    @Override
    public Menu b() {
        return (Menu)this.g;
    }
    
    @Override
    public void b(final int n) {
        this.a(this.a.getString(n));
    }
    
    @Override
    public void b(final CharSequence title) {
        this.b.setTitle(title);
    }
    
    @Override
    public void c() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.b.sendAccessibilityEvent(32);
        this.c.a(this);
    }
    
    @Override
    public void d() {
        this.c.b(this, (Menu)this.g);
    }
    
    @Override
    public CharSequence f() {
        return this.b.getTitle();
    }
    
    @Override
    public CharSequence g() {
        return this.b.getSubtitle();
    }
    
    @Override
    public boolean h() {
        return this.b.d();
    }
    
    @Override
    public View i() {
        if (this.d != null) {
            return this.d.get();
        }
        return null;
    }
}
