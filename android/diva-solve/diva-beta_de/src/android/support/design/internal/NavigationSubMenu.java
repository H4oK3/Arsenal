// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.internal;

import android.view.MenuItem;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.content.Context;
import android.support.v7.internal.view.menu.SubMenuBuilder;

public class NavigationSubMenu extends SubMenuBuilder
{
    public NavigationSubMenu(final Context context, final NavigationMenu navigationMenu, final MenuItemImpl menuItemImpl) {
        super(context, navigationMenu, menuItemImpl);
    }
    
    private void notifyParent() {
        ((MenuBuilder)this.getParentMenu()).onItemsChanged(true);
    }
    
    @Override
    public MenuItem add(final int n) {
        final MenuItem add = super.add(n);
        this.notifyParent();
        return add;
    }
    
    @Override
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        final MenuItem add = super.add(n, n2, n3, n4);
        this.notifyParent();
        return add;
    }
    
    @Override
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        final MenuItem add = super.add(n, n2, n3, charSequence);
        this.notifyParent();
        return add;
    }
    
    @Override
    public MenuItem add(final CharSequence charSequence) {
        final MenuItem add = super.add(charSequence);
        this.notifyParent();
        return add;
    }
}
