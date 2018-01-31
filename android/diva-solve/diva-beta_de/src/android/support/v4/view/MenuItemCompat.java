// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.util.Log;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.os.Build$VERSION;

public class MenuItemCompat
{
    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 14) {
            IMPL = (MenuVersionImpl)new IcsMenuVersionImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = (MenuVersionImpl)new HoneycombMenuVersionImpl();
            return;
        }
        IMPL = (MenuVersionImpl)new BaseMenuVersionImpl();
    }
    
    public static boolean collapseActionView(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).collapseActionView();
        }
        return MenuItemCompat.IMPL.collapseActionView(menuItem);
    }
    
    public static boolean expandActionView(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).expandActionView();
        }
        return MenuItemCompat.IMPL.expandActionView(menuItem);
    }
    
    public static ActionProvider getActionProvider(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getSupportActionProvider();
        }
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }
    
    public static View getActionView(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getActionView();
        }
        return MenuItemCompat.IMPL.getActionView(menuItem);
    }
    
    public static boolean isActionViewExpanded(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).isActionViewExpanded();
        }
        return MenuItemCompat.IMPL.isActionViewExpanded(menuItem);
    }
    
    public static MenuItem setActionProvider(final MenuItem menuItem, final ActionProvider supportActionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return (MenuItem)((SupportMenuItem)menuItem).setSupportActionProvider(supportActionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final int actionView) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        return MenuItemCompat.IMPL.setActionView(menuItem, actionView);
    }
    
    public static MenuItem setActionView(final MenuItem menuItem, final View actionView) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).setActionView(actionView);
        }
        return MenuItemCompat.IMPL.setActionView(menuItem, actionView);
    }
    
    public static MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener supportOnActionExpandListener) {
        if (menuItem instanceof SupportMenuItem) {
            return (MenuItem)((SupportMenuItem)menuItem).setSupportOnActionExpandListener(supportOnActionExpandListener);
        }
        return MenuItemCompat.IMPL.setOnActionExpandListener(menuItem, supportOnActionExpandListener);
    }
    
    public static void setShowAsAction(final MenuItem menuItem, final int showAsAction) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setShowAsAction(showAsAction);
            return;
        }
        MenuItemCompat.IMPL.setShowAsAction(menuItem, showAsAction);
    }
    
    static class BaseMenuVersionImpl implements MenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public View getActionView(final MenuItem menuItem) {
            return null;
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final int n) {
            return menuItem;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final View view) {
            return menuItem;
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }
        
        @Override
        public void setShowAsAction(final MenuItem menuItem, final int n) {
        }
    }
    
    static class HoneycombMenuVersionImpl implements MenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public View getActionView(final MenuItem menuItem) {
            return MenuItemCompatHoneycomb.getActionView(menuItem);
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return false;
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final int n) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, n);
        }
        
        @Override
        public MenuItem setActionView(final MenuItem menuItem, final View view) {
            return MenuItemCompatHoneycomb.setActionView(menuItem, view);
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            return menuItem;
        }
        
        @Override
        public void setShowAsAction(final MenuItem menuItem, final int n) {
            MenuItemCompatHoneycomb.setShowAsAction(menuItem, n);
        }
    }
    
    static class IcsMenuVersionImpl extends HoneycombMenuVersionImpl
    {
        @Override
        public boolean collapseActionView(final MenuItem menuItem) {
            return MenuItemCompatIcs.collapseActionView(menuItem);
        }
        
        @Override
        public boolean expandActionView(final MenuItem menuItem) {
            return MenuItemCompatIcs.expandActionView(menuItem);
        }
        
        @Override
        public boolean isActionViewExpanded(final MenuItem menuItem) {
            return MenuItemCompatIcs.isActionViewExpanded(menuItem);
        }
        
        @Override
        public MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
            if (onActionExpandListener == null) {
                return MenuItemCompatIcs.setOnActionExpandListener(menuItem, null);
            }
            return MenuItemCompatIcs.setOnActionExpandListener(menuItem, (MenuItemCompatIcs.SupportActionExpandProxy)new MenuItemCompatIcs.SupportActionExpandProxy() {
                @Override
                public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
                    return onActionExpandListener.onMenuItemActionCollapse(menuItem);
                }
                
                @Override
                public boolean onMenuItemActionExpand(final MenuItem menuItem) {
                    return onActionExpandListener.onMenuItemActionExpand(menuItem);
                }
            });
        }
    }
    
    interface MenuVersionImpl
    {
        boolean collapseActionView(final MenuItem p0);
        
        boolean expandActionView(final MenuItem p0);
        
        View getActionView(final MenuItem p0);
        
        boolean isActionViewExpanded(final MenuItem p0);
        
        MenuItem setActionView(final MenuItem p0, final int p1);
        
        MenuItem setActionView(final MenuItem p0, final View p1);
        
        MenuItem setOnActionExpandListener(final MenuItem p0, final OnActionExpandListener p1);
        
        void setShowAsAction(final MenuItem p0, final int p1);
    }
    
    public interface OnActionExpandListener
    {
        boolean onMenuItemActionCollapse(final MenuItem p0);
        
        boolean onMenuItemActionExpand(final MenuItem p0);
    }
}
