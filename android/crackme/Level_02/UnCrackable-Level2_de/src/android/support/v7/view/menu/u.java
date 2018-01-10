// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.view.SubMenu;

public class u extends h implements SubMenu
{
    private h d;
    private j e;
    
    public u(final Context context, final h d, final j e) {
        super(context);
        this.d = d;
        this.e = e;
    }
    
    public String a() {
        int itemId;
        if (this.e != null) {
            itemId = this.e.getItemId();
        }
        else {
            itemId = 0;
        }
        if (itemId == 0) {
            return null;
        }
        return super.a() + ":" + itemId;
    }
    
    @Override
    public void a(final a a) {
        this.d.a(a);
    }
    
    @Override
    boolean a(final h h, final MenuItem menuItem) {
        return super.a(h, menuItem) || this.d.a(h, menuItem);
    }
    
    public boolean b() {
        return this.d.b();
    }
    
    @Override
    public boolean c() {
        return this.d.c();
    }
    
    @Override
    public boolean c(final j j) {
        return this.d.c(j);
    }
    
    @Override
    public boolean d(final j j) {
        return this.d.d(j);
    }
    
    public MenuItem getItem() {
        return (MenuItem)this.e;
    }
    
    @Override
    public h p() {
        return this.d.p();
    }
    
    public Menu s() {
        return (Menu)this.d;
    }
    
    public SubMenu setHeaderIcon(final int n) {
        return (SubMenu)super.e(n);
    }
    
    public SubMenu setHeaderIcon(final Drawable drawable) {
        return (SubMenu)super.a(drawable);
    }
    
    public SubMenu setHeaderTitle(final int n) {
        return (SubMenu)super.d(n);
    }
    
    public SubMenu setHeaderTitle(final CharSequence charSequence) {
        return (SubMenu)super.a(charSequence);
    }
    
    public SubMenu setHeaderView(final View view) {
        return (SubMenu)super.a(view);
    }
    
    public SubMenu setIcon(final int icon) {
        this.e.setIcon(icon);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final Drawable icon) {
        this.e.setIcon(icon);
        return (SubMenu)this;
    }
    
    @Override
    public void setQwertyMode(final boolean qwertyMode) {
        this.d.setQwertyMode(qwertyMode);
    }
}
