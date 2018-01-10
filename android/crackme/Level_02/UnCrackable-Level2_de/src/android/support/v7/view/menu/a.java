// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ActionProvider;
import android.view.View;
import android.support.v4.g.q;
import android.support.v4.g.e;
import android.view.MenuItem$OnMenuItemClickListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.support.v4.d.a.b;

public class a implements b
{
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private char i;
    private Drawable j;
    private int k;
    private Context l;
    private MenuItem$OnMenuItemClickListener m;
    private int n;
    
    public a(final Context l, final int b, final int a, final int c, final int d, final CharSequence e) {
        this.k = 0;
        this.n = 16;
        this.l = l;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public b a(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public b a(final e e) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public b a(final q.e e) {
        return this;
    }
    
    public b a(final View view) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public e a() {
        return null;
    }
    
    public b b(final int showAsAction) {
        this.setShowAsAction(showAsAction);
        return this;
    }
    
    @Override
    public boolean collapseActionView() {
        return false;
    }
    
    @Override
    public boolean expandActionView() {
        return false;
    }
    
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public View getActionView() {
        return null;
    }
    
    public char getAlphabeticShortcut() {
        return this.i;
    }
    
    public int getGroupId() {
        return this.b;
    }
    
    public Drawable getIcon() {
        return this.j;
    }
    
    public Intent getIntent() {
        return this.g;
    }
    
    public int getItemId() {
        return this.a;
    }
    
    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return null;
    }
    
    public char getNumericShortcut() {
        return this.h;
    }
    
    public int getOrder() {
        return this.d;
    }
    
    public SubMenu getSubMenu() {
        return null;
    }
    
    public CharSequence getTitle() {
        return this.e;
    }
    
    public CharSequence getTitleCondensed() {
        if (this.f != null) {
            return this.f;
        }
        return this.e;
    }
    
    public boolean hasSubMenu() {
        return false;
    }
    
    @Override
    public boolean isActionViewExpanded() {
        return false;
    }
    
    public boolean isCheckable() {
        return (this.n & 0x1) != 0x0;
    }
    
    public boolean isChecked() {
        return (this.n & 0x2) != 0x0;
    }
    
    public boolean isEnabled() {
        return (this.n & 0x10) != 0x0;
    }
    
    public boolean isVisible() {
        return (this.n & 0x8) == 0x0;
    }
    
    public MenuItem setActionProvider(final ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setAlphabeticShortcut(final char i) {
        this.i = i;
        return (MenuItem)this;
    }
    
    public MenuItem setCheckable(final boolean b) {
        final int n = this.n;
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        this.n = ((b2 ? 1 : 0) | (n & 0xFFFFFFFE));
        return (MenuItem)this;
    }
    
    public MenuItem setChecked(final boolean b) {
        final int n = this.n;
        int n2;
        if (b) {
            n2 = 2;
        }
        else {
            n2 = 0;
        }
        this.n = (n2 | (n & 0xFFFFFFFD));
        return (MenuItem)this;
    }
    
    public MenuItem setEnabled(final boolean b) {
        final int n = this.n;
        int n2;
        if (b) {
            n2 = 16;
        }
        else {
            n2 = 0;
        }
        this.n = (n2 | (n & 0xFFFFFFEF));
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final int k) {
        this.k = k;
        this.j = android.support.v4.b.a.a(this.l, k);
        return (MenuItem)this;
    }
    
    public MenuItem setIcon(final Drawable j) {
        this.j = j;
        this.k = 0;
        return (MenuItem)this;
    }
    
    public MenuItem setIntent(final Intent g) {
        this.g = g;
        return (MenuItem)this;
    }
    
    public MenuItem setNumericShortcut(final char h) {
        this.h = h;
        return (MenuItem)this;
    }
    
    public MenuItem setOnActionExpandListener(final MenuItem$OnActionExpandListener menuItem$OnActionExpandListener) {
        throw new UnsupportedOperationException();
    }
    
    public MenuItem setOnMenuItemClickListener(final MenuItem$OnMenuItemClickListener m) {
        this.m = m;
        return (MenuItem)this;
    }
    
    public MenuItem setShortcut(final char h, final char i) {
        this.h = h;
        this.i = i;
        return (MenuItem)this;
    }
    
    @Override
    public void setShowAsAction(final int n) {
    }
    
    public MenuItem setTitle(final int n) {
        this.e = this.l.getResources().getString(n);
        return (MenuItem)this;
    }
    
    public MenuItem setTitle(final CharSequence e) {
        this.e = e;
        return (MenuItem)this;
    }
    
    public MenuItem setTitleCondensed(final CharSequence f) {
        this.f = f;
        return (MenuItem)this;
    }
    
    public MenuItem setVisible(final boolean b) {
        final int n = this.n;
        int n2;
        if (b) {
            n2 = 0;
        }
        else {
            n2 = 8;
        }
        this.n = (n2 | (n & 0x8));
        return (MenuItem)this;
    }
}
