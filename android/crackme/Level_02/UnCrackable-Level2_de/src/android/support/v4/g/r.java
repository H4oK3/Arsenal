// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.view.MenuItem;
import android.annotation.TargetApi;

@TargetApi(11)
class r
{
    public static MenuItem a(final MenuItem menuItem, final View actionView) {
        return menuItem.setActionView(actionView);
    }
    
    public static View a(final MenuItem menuItem) {
        return menuItem.getActionView();
    }
    
    public static void a(final MenuItem menuItem, final int showAsAction) {
        menuItem.setShowAsAction(showAsAction);
    }
    
    public static MenuItem b(final MenuItem menuItem, final int actionView) {
        return menuItem.setActionView(actionView);
    }
}
