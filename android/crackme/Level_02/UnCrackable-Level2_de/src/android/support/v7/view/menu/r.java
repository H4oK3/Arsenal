// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.KeyEvent;
import android.view.SubMenu;
import android.content.Intent;
import android.content.ComponentName;
import android.view.MenuItem;
import android.content.Context;
import android.annotation.TargetApi;
import android.view.Menu;
import android.support.v4.d.a.a;

@TargetApi(14)
class r extends c<a> implements Menu
{
    r(final Context context, final a a) {
        super(context, a);
    }
    
    public MenuItem add(final int n) {
        return this.a(((a)this.b).add(n));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final int n4) {
        return this.a(((a)this.b).add(n, n2, n3, n4));
    }
    
    public MenuItem add(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(((a)this.b).add(n, n2, n3, charSequence));
    }
    
    public MenuItem add(final CharSequence charSequence) {
        return this.a(((a)this.b).add(charSequence));
    }
    
    public int addIntentOptions(int i, int addIntentOptions, int length, final ComponentName componentName, final Intent[] array, final Intent intent, final int n, final MenuItem[] array2) {
        MenuItem[] array3 = null;
        if (array2 != null) {
            array3 = new MenuItem[array2.length];
        }
        addIntentOptions = ((a)this.b).addIntentOptions(i, addIntentOptions, length, componentName, array, intent, n, array3);
        if (array3 != null) {
            for (i = 0, length = array3.length; i < length; ++i) {
                array2[i] = this.a(array3[i]);
            }
        }
        return addIntentOptions;
    }
    
    public SubMenu addSubMenu(final int n) {
        return this.a(((a)this.b).addSubMenu(n));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final int n4) {
        return this.a(((a)this.b).addSubMenu(n, n2, n3, n4));
    }
    
    public SubMenu addSubMenu(final int n, final int n2, final int n3, final CharSequence charSequence) {
        return this.a(((a)this.b).addSubMenu(n, n2, n3, charSequence));
    }
    
    public SubMenu addSubMenu(final CharSequence charSequence) {
        return this.a(((a)this.b).addSubMenu(charSequence));
    }
    
    public void clear() {
        this.a();
        ((a)this.b).clear();
    }
    
    public void close() {
        ((a)this.b).close();
    }
    
    public MenuItem findItem(final int n) {
        return this.a(((a)this.b).findItem(n));
    }
    
    public MenuItem getItem(final int n) {
        return this.a(((a)this.b).getItem(n));
    }
    
    public boolean hasVisibleItems() {
        return ((a)this.b).hasVisibleItems();
    }
    
    public boolean isShortcutKey(final int n, final KeyEvent keyEvent) {
        return ((a)this.b).isShortcutKey(n, keyEvent);
    }
    
    public boolean performIdentifierAction(final int n, final int n2) {
        return ((a)this.b).performIdentifierAction(n, n2);
    }
    
    public boolean performShortcut(final int n, final KeyEvent keyEvent, final int n2) {
        return ((a)this.b).performShortcut(n, keyEvent, n2);
    }
    
    public void removeGroup(final int n) {
        this.a(n);
        ((a)this.b).removeGroup(n);
    }
    
    public void removeItem(final int n) {
        this.b(n);
        ((a)this.b).removeItem(n);
    }
    
    public void setGroupCheckable(final int n, final boolean b, final boolean b2) {
        ((a)this.b).setGroupCheckable(n, b, b2);
    }
    
    public void setGroupEnabled(final int n, final boolean b) {
        ((a)this.b).setGroupEnabled(n, b);
    }
    
    public void setGroupVisible(final int n, final boolean b) {
        ((a)this.b).setGroupVisible(n, b);
    }
    
    public void setQwertyMode(final boolean qwertyMode) {
        ((a)this.b).setQwertyMode(qwertyMode);
    }
    
    public int size() {
        return ((a)this.b).size();
    }
}
