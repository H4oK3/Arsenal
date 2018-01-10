// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.SubMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;

public abstract class e
{
    private final Context a;
    private a b;
    private b c;
    
    public e(final Context a) {
        this.a = a;
    }
    
    public abstract View a();
    
    public View a(final MenuItem menuItem) {
        return this.a();
    }
    
    public void a(final a b) {
        this.b = b;
    }
    
    public void a(final b c) {
        if (this.c != null && c != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + this.getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.c = c;
    }
    
    public void a(final SubMenu subMenu) {
    }
    
    public void a(final boolean b) {
        if (this.b != null) {
            this.b.a(b);
        }
    }
    
    public boolean b() {
        return false;
    }
    
    public boolean c() {
        return true;
    }
    
    public boolean d() {
        return false;
    }
    
    public boolean e() {
        return false;
    }
    
    public void f() {
        this.c = null;
        this.b = null;
    }
    
    public interface a
    {
        void a(final boolean p0);
    }
    
    public interface b
    {
        void a(final boolean p0);
    }
}
