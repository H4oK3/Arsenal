// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.widget.FrameLayout;
import android.view.MenuItem$OnMenuItemClickListener;
import android.support.v4.g.q;
import android.view.MenuItem$OnActionExpandListener;
import android.view.CollapsibleActionView;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v4.g.e;
import android.util.Log;
import android.view.ActionProvider;
import android.content.Context;
import java.lang.reflect.Method;
import android.annotation.TargetApi;
import android.view.MenuItem;
import android.support.v4.d.a.b;

@TargetApi(14)
public class k extends android.support.v7.view.menu.c<android.support.v4.d.a.b> implements MenuItem
{
    private Method c;
    
    k(final Context context, final android.support.v4.d.a.b b) {
        super(context, b);
    }
    
    a a(final ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
    
    public void a(final boolean b) {
        try {
            if (this.c == null) {
                this.c = ((android.support.v4.d.a.b)this.b).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.c.invoke(this.b, b);
        }
        catch (Exception ex) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", (Throwable)ex);
        }
    }
    
    public boolean collapseActionView() {
        return ((android.support.v4.d.a.b)this.b).collapseActionView();
    }
    
    public boolean expandActionView() {
        return ((android.support.v4.d.a.b)this.b).expandActionView();
    }
    
    public ActionProvider getActionProvider() {
        final e a = ((android.support.v4.d.a.b)this.b).a();
        if (a instanceof a) {
            return ((a)a).a;
        }
        return null;
    }
    
    public View getActionView() {
        View view2;
        final View view = view2 = ((android.support.v4.d.a.b)this.b).getActionView();
        if (view instanceof b) {
            view2 = ((b)view).c();
        }
        return view2;
    }
    
    public char getAlphabeticShortcut() {
        return ((android.support.v4.d.a.b)this.b).getAlphabeticShortcut();
    }
    
    public int getGroupId() {
        return ((android.support.v4.d.a.b)this.b).getGroupId();
    }
    
    public Drawable getIcon() {
        return ((android.support.v4.d.a.b)this.b).getIcon();
    }
    
    public Intent getIntent() {
        return ((android.support.v4.d.a.b)this.b).getIntent();
    }
    
    public int getItemId() {
        return ((android.support.v4.d.a.b)this.b).getItemId();
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return ((android.support.v4.d.a.b)this.b).getMenuInfo();
    }
    
    public char getNumericShortcut() {
        return ((android.support.v4.d.a.b)this.b).getNumericShortcut();
    }
    
    public int getOrder() {
        return ((android.support.v4.d.a.b)this.b).getOrder();
    }
    
    public SubMenu getSubMenu() {
        return this.a(((android.support.v4.d.a.b)this.b).getSubMenu());
    }
    
    public CharSequence getTitle() {
        return ((android.support.v4.d.a.b)this.b).getTitle();
    }
    
    public CharSequence getTitleCondensed() {
        return ((android.support.v4.d.a.b)this.b).getTitleCondensed();
    }
    
    public boolean hasSubMenu() {
        return ((android.support.v4.d.a.b)this.b).hasSubMenu();
    }
    
    public boolean isActionViewExpanded() {
        return ((android.support.v4.d.a.b)this.b).isActionViewExpanded();
    }
    
    public boolean isCheckable() {
        return ((android.support.v4.d.a.b)this.b).isCheckable();
    }
    
    public boolean isChecked() {
        return ((android.support.v4.d.a.b)this.b).isChecked();
    }
    
    public boolean isEnabled() {
        return ((android.support.v4.d.a.b)this.b).isEnabled();
    }
    
    public boolean isVisible() {
        return ((android.support.v4.d.a.b)this.b).isVisible();
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        final android.support.v4.d.a.b b = (android.support.v4.d.a.b)this.b;
        a a;
        if (actionProvider != null) {
            a = this.a(actionProvider);
        }
        else {
            a = null;
        }
        b.a(a);
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final int actionView) {
        ((android.support.v4.d.a.b)this.b).setActionView(actionView);
        final View actionView2 = ((android.support.v4.d.a.b)this.b).getActionView();
        if (actionView2 instanceof CollapsibleActionView) {
            ((android.support.v4.d.a.b)this.b).setActionView((View)new b(actionView2));
        }
        return (MenuItem)this;
    }
    
    public MenuItem setActionView(final View view) {
        Object actionView = view;
        if (view instanceof CollapsibleActionView) {
            actionView = new b(view);
        }
        ((android.support.v4.d.a.b)this.b).setActionView((View)actionView);
        return (MenuItem)this;
    }
    
    public MenuItem setAlphabeticShortcut(final char alphabeticShortcut) {
        ((android.support.v4.d.a.b)this.b).setAlphabeticShortcut(alphabeticShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean checkable) {
        ((android.support.v4.d.a.b)this.b).setCheckable(checkable);
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean checked) {
        ((android.support.v4.d.a.b)this.b).setChecked(checked);
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean enabled) {
        ((android.support.v4.d.a.b)this.b).setEnabled(enabled);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int icon) {
        ((android.support.v4.d.a.b)this.b).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable icon) {
        ((android.support.v4.d.a.b)this.b).setIcon(icon);
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent intent) {
        ((android.support.v4.d.a.b)this.b).setIntent(intent);
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char numericShortcut) {
        ((android.support.v4.d.a.b)this.b).setNumericShortcut(numericShortcut);
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        final android.support.v4.d.a.b b = (android.support.v4.d.a.b)this.b;
        c c;
        if (menuItem$OnActionExpandListener != null) {
            c = new c(menuItem$OnActionExpandListener);
        }
        else {
            c = null;
        }
        b.a(c);
        return (MenuItem)this;
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
        final android.support.v4.d.a.b b = (android.support.v4.d.a.b)this.b;
        Object onMenuItemClickListener;
        if (menuItem$OnMenuItemClickListener != null) {
            onMenuItemClickListener = new d(menuItem$OnMenuItemClickListener);
        }
        else {
            onMenuItemClickListener = null;
        }
        b.setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)onMenuItemClickListener);
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char c, final char c2) {
        ((android.support.v4.d.a.b)this.b).setShortcut(c, c2);
        return (MenuItem)this;
    }
    
    public void setShowAsAction(final int showAsAction) {
        ((android.support.v4.d.a.b)this.b).setShowAsAction(showAsAction);
    }
    
    public MenuItem setShowAsActionFlags(final int showAsActionFlags) {
        ((android.support.v4.d.a.b)this.b).setShowAsActionFlags(showAsActionFlags);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final int title) {
        ((android.support.v4.d.a.b)this.b).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence title) {
        ((android.support.v4.d.a.b)this.b).setTitle(title);
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence titleCondensed) {
        ((android.support.v4.d.a.b)this.b).setTitleCondensed(titleCondensed);
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean visible) {
        return ((android.support.v4.d.a.b)this.b).setVisible(visible);
    }
    
    class a extends e
    {
        final ActionProvider a;
        
        public a(final Context context, final ActionProvider a) {
            super(context);
            this.a = a;
        }
        
        @Override
        public View a() {
            return this.a.onCreateActionView();
        }
        
        @Override
        public void a(final SubMenu subMenu) {
            this.a.onPrepareSubMenu(k.this.a(subMenu));
        }
        
        @Override
        public boolean d() {
            return this.a.onPerformDefaultAction();
        }
        
        @Override
        public boolean e() {
            return this.a.hasSubMenu();
        }
    }
    
    static class b extends FrameLayout implements android.support.v7.view.c
    {
        final CollapsibleActionView a;
        
        b(final View view) {
            super(view.getContext());
            this.a = (CollapsibleActionView)view;
            this.addView(view);
        }
        
        public void a() {
            this.a.onActionViewExpanded();
        }
        
        public void b() {
            this.a.onActionViewCollapsed();
        }
        
        View c() {
            return (View)this.a;
        }
    }
    
    private class c extends d<MenuItem$OnActionExpandListener> implements e
    {
        c(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
            super(menuItem$OnActionExpandListener);
        }
        
        @Override
        public boolean a(final MenuItem menuItem) {
            return ((MenuItem$OnActionExpandListener)this.b).onMenuItemActionExpand(k.this.a(menuItem));
        }
        
        @Override
        public boolean b(final MenuItem menuItem) {
            return ((MenuItem$OnActionExpandListener)this.b).onMenuItemActionCollapse(k.this.a(menuItem));
        }
    }
    
    private class d extends android.support.v7.view.menu.d<MenuItem$OnMenuItemClickListener> implements MenuItem$OnMenuItemClickListener
    {
        d(final MenuItem$OnMenuItemClickListener menuItem$OnMenuItemClickListener) {
            super(menuItem$OnMenuItemClickListener);
        }
        
        public boolean onMenuItemClick(final MenuItem menuItem) {
            return ((MenuItem$OnMenuItemClickListener)this.b).onMenuItemClick(k.this.a(menuItem));
        }
    }
}
