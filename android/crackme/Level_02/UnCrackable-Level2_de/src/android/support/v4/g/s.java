// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.MenuItem;
import android.annotation.TargetApi;

@TargetApi(14)
class s
{
    public static boolean a(final MenuItem menuItem) {
        return menuItem.expandActionView();
    }
    
    public static boolean b(final MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }
}
