// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.support.v4.d.a.a;
import android.support.v4.d.a.c;
import android.content.Context;
import android.annotation.TargetApi;
import android.view.SubMenu;

@TargetApi(14)
class v extends r implements SubMenu
{
    v(final Context context, final android.support.v4.d.a.c c) {
        super(context, c);
    }
    
    public android.support.v4.d.a.c b() {
        return (android.support.v4.d.a.c)this.b;
    }
    
    public void clearHeader() {
        this.b().clearHeader();
    }
    
    public MenuItem getItem() {
        return this.a(this.b().getItem());
    }
    
    public SubMenu setHeaderIcon(final int headerIcon) {
        this.b().setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderIcon(final Drawable headerIcon) {
        this.b().setHeaderIcon(headerIcon);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final int headerTitle) {
        this.b().setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderTitle(final CharSequence headerTitle) {
        this.b().setHeaderTitle(headerTitle);
        return (SubMenu)this;
    }
    
    public SubMenu setHeaderView(final View headerView) {
        this.b().setHeaderView(headerView);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final int icon) {
        this.b().setIcon(icon);
        return (SubMenu)this;
    }
    
    public SubMenu setIcon(final Drawable icon) {
        this.b().setIcon(icon);
        return (SubMenu)this;
    }
}
