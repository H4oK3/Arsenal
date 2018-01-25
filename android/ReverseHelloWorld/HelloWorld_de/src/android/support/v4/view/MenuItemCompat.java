// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.annotation.TargetApi;
import android.view.MenuItem$OnActionExpandListener;
import android.view.View;
import android.util.Log;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.support.v4.os.BuildCompat;

public final class MenuItemCompat
{
    static final MenuVersionImpl IMPL;
    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";
    
    static {
        if (BuildCompat.isAtLeastO()) {
            IMPL = (MenuVersionImpl)new MenuItemCompatApi26Impl();
            return;
        }
        IMPL = (MenuVersionImpl)new MenuItemCompatBaseImpl();
    }
    
    @Deprecated
    public static boolean collapseActionView(final MenuItem menuItem) {
        return menuItem.collapseActionView();
    }
    
    @Deprecated
    public static boolean expandActionView(final MenuItem menuItem) {
        return menuItem.expandActionView();
    }
    
    public static ActionProvider getActionProvider(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getSupportActionProvider();
        }
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }
    
    @Deprecated
    public static View getActionView(final MenuItem menuItem) {
        return menuItem.getActionView();
    }
    
    public static CharSequence getContentDescription(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getContentDescription();
        }
        return MenuItemCompat.IMPL.getContentDescription(menuItem);
    }
    
    public static CharSequence getTooltipText(final MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem).getTooltipText();
        }
        return MenuItemCompat.IMPL.getTooltipText(menuItem);
    }
    
    @Deprecated
    public static boolean isActionViewExpanded(final MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }
    
    public static MenuItem setActionProvider(final MenuItem menuItem, final ActionProvider supportActionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return (MenuItem)((SupportMenuItem)menuItem).setSupportActionProvider(supportActionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }
    
    @Deprecated
    public static MenuItem setActionView(final MenuItem menuItem, final int actionView) {
        return menuItem.setActionView(actionView);
    }
    
    @Deprecated
    public static MenuItem setActionView(final MenuItem menuItem, final View actionView) {
        return menuItem.setActionView(actionView);
    }
    
    public static void setContentDescription(final MenuItem menuItem, final CharSequence contentDescription) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setContentDescription(contentDescription);
            return;
        }
        MenuItemCompat.IMPL.setContentDescription(menuItem, contentDescription);
    }
    
    @Deprecated
    public static MenuItem setOnActionExpandListener(final MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
        return menuItem.setOnActionExpandListener((MenuItem$OnActionExpandListener)new MenuItem$OnActionExpandListener() {
            public boolean onMenuItemActionCollapse(final MenuItem menuItem) {
                return onActionExpandListener.onMenuItemActionCollapse(menuItem);
            }
            
            public boolean onMenuItemActionExpand(final MenuItem menuItem) {
                return onActionExpandListener.onMenuItemActionExpand(menuItem);
            }
        });
    }
    
    @Deprecated
    public static void setShowAsAction(final MenuItem menuItem, final int showAsAction) {
        menuItem.setShowAsAction(showAsAction);
    }
    
    public static void setTooltipText(final MenuItem menuItem, final CharSequence tooltipText) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem).setTooltipText(tooltipText);
            return;
        }
        MenuItemCompat.IMPL.setTooltipText(menuItem, tooltipText);
    }
    
    @TargetApi(26)
    static class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl
    {
        @Override
        public CharSequence getContentDescription(final MenuItem menuItem) {
            return menuItem.getContentDescription();
        }
        
        @Override
        public CharSequence getTooltipText(final MenuItem menuItem) {
            return menuItem.getTooltipText();
        }
        
        @Override
        public void setContentDescription(final MenuItem menuItem, final CharSequence contentDescription) {
            menuItem.setContentDescription(contentDescription);
        }
        
        @Override
        public void setTooltipText(final MenuItem menuItem, final CharSequence tooltipText) {
            menuItem.setTooltipText(tooltipText);
        }
    }
    
    static class MenuItemCompatBaseImpl implements MenuVersionImpl
    {
        @Override
        public CharSequence getContentDescription(final MenuItem menuItem) {
            return null;
        }
        
        @Override
        public CharSequence getTooltipText(final MenuItem menuItem) {
            return null;
        }
        
        @Override
        public void setContentDescription(final MenuItem menuItem, final CharSequence charSequence) {
        }
        
        @Override
        public void setTooltipText(final MenuItem menuItem, final CharSequence charSequence) {
        }
    }
    
    interface MenuVersionImpl
    {
        CharSequence getContentDescription(final MenuItem p0);
        
        CharSequence getTooltipText(final MenuItem p0);
        
        void setContentDescription(final MenuItem p0, final CharSequence p1);
        
        void setTooltipText(final MenuItem p0, final CharSequence p1);
    }
    
    @Deprecated
    public interface OnActionExpandListener
    {
        boolean onMenuItemActionCollapse(final MenuItem p0);
        
        boolean onMenuItemActionExpand(final MenuItem p0);
    }
}
