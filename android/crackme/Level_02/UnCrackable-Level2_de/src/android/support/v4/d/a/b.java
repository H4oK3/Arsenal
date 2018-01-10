// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.d.a;

import android.view.View;
import android.support.v4.g.q;
import android.support.v4.g.e;
import android.view.MenuItem;

public interface b extends MenuItem
{
    b a(final e p0);
    
    b a(final q.e p0);
    
    e a();
    
    boolean collapseActionView();
    
    boolean expandActionView();
    
    View getActionView();
    
    boolean isActionViewExpanded();
    
    MenuItem setActionView(final int p0);
    
    MenuItem setActionView(final View p0);
    
    void setShowAsAction(final int p0);
    
    MenuItem setShowAsActionFlags(final int p0);
}
