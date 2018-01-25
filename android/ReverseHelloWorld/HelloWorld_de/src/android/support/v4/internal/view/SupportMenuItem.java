// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.internal.view;

import android.view.MenuItem$OnActionExpandListener;
import android.support.v4.view.ActionProvider;
import android.view.View;
import android.support.annotation.RestrictTo;
import android.view.MenuItem;

@RestrictTo({ RestrictTo.Scope.LIBRARY_GROUP })
public interface SupportMenuItem extends MenuItem
{
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    
    boolean collapseActionView();
    
    boolean expandActionView();
    
    View getActionView();
    
    CharSequence getContentDescription();
    
    ActionProvider getSupportActionProvider();
    
    CharSequence getTooltipText();
    
    boolean isActionViewExpanded();
    
    MenuItem setActionView(final int p0);
    
    MenuItem setActionView(final View p0);
    
    SupportMenuItem setContentDescription(final CharSequence p0);
    
    void setShowAsAction(final int p0);
    
    MenuItem setShowAsActionFlags(final int p0);
    
    SupportMenuItem setSupportActionProvider(final ActionProvider p0);
    
    SupportMenuItem setSupportOnActionExpandListener(final MenuItem$OnActionExpandListener p0);
    
    SupportMenuItem setTooltipText(final CharSequence p0);
}
