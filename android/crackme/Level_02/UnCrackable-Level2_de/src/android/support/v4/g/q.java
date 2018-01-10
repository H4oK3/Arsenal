// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.util.Log;
import android.support.v4.d.a.b;
import android.view.MenuItem;
import android.os.Build$VERSION;

public final class q
{
    static final d a;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            a = (d)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            a = (d)new b();
            return;
        }
        a = (d)new a();
    }
    
    public static MenuItem a(final MenuItem menuItem, final android.support.v4.g.e e) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return (MenuItem)((android.support.v4.d.a.b)menuItem).a(e);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }
    
    public static MenuItem a(final MenuItem menuItem, final View actionView) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return ((android.support.v4.d.a.b)menuItem).setActionView(actionView);
        }
        return q.a.a(menuItem, actionView);
    }
    
    public static View a(final MenuItem menuItem) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return ((android.support.v4.d.a.b)menuItem).getActionView();
        }
        return q.a.a(menuItem);
    }
    
    public static void a(final MenuItem menuItem, final int showAsAction) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            ((android.support.v4.d.a.b)menuItem).setShowAsAction(showAsAction);
            return;
        }
        q.a.a(menuItem, showAsAction);
    }
    
    public static MenuItem b(final MenuItem menuItem, final int actionView) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return ((android.support.v4.d.a.b)menuItem).setActionView(actionView);
        }
        return q.a.b(menuItem, actionView);
    }
    
    public static boolean b(final MenuItem menuItem) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return ((android.support.v4.d.a.b)menuItem).expandActionView();
        }
        return q.a.b(menuItem);
    }
    
    public static boolean c(final MenuItem menuItem) {
        if (menuItem instanceof android.support.v4.d.a.b) {
            return ((android.support.v4.d.a.b)menuItem).isActionViewExpanded();
        }
        return q.a.c(menuItem);
    }
    
    static class a implements d
    {
        @Override
        public MenuItem a(final MenuItem menuItem, final View view) {
            return menuItem;
        }
        
        @Override
        public View a(final MenuItem menuItem) {
            return null;
        }
        
        @Override
        public void a(final MenuItem menuItem, final int n) {
        }
        
        @Override
        public MenuItem b(final MenuItem menuItem, final int n) {
            return menuItem;
        }
        
        @Override
        public boolean b(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean c(final MenuItem menuItem) {
            return false;
        }
    }
    
    static class b implements d
    {
        @Override
        public MenuItem a(final MenuItem menuItem, final View view) {
            return r.a(menuItem, view);
        }
        
        @Override
        public View a(final MenuItem menuItem) {
            return r.a(menuItem);
        }
        
        @Override
        public void a(final MenuItem menuItem, final int n) {
            r.a(menuItem, n);
        }
        
        @Override
        public MenuItem b(final MenuItem menuItem, final int n) {
            return r.b(menuItem, n);
        }
        
        @Override
        public boolean b(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean c(final MenuItem menuItem) {
            return false;
        }
    }
    
    static class c extends b
    {
        @Override
        public boolean b(final MenuItem menuItem) {
            return s.a(menuItem);
        }
        
        @Override
        public boolean c(final MenuItem menuItem) {
            return s.b(menuItem);
        }
    }
    
    interface d
    {
        MenuItem a(final MenuItem p0, final View p1);
        
        View a(final MenuItem p0);
        
        void a(final MenuItem p0, final int p1);
        
        MenuItem b(final MenuItem p0, final int p1);
        
        boolean b(final MenuItem p0);
        
        boolean c(final MenuItem p0);
    }
    
    public interface e
    {
        boolean a(final MenuItem p0);
        
        boolean b(final MenuItem p0);
    }
}
