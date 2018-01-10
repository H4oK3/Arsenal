// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;

public abstract class b
{
    private Object a;
    private boolean b;
    
    public abstract MenuInflater a();
    
    public abstract void a(final int p0);
    
    public abstract void a(final View p0);
    
    public abstract void a(final CharSequence p0);
    
    public void a(final Object a) {
        this.a = a;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
    
    public abstract Menu b();
    
    public abstract void b(final int p0);
    
    public abstract void b(final CharSequence p0);
    
    public abstract void c();
    
    public abstract void d();
    
    public abstract CharSequence f();
    
    public abstract CharSequence g();
    
    public boolean h() {
        return false;
    }
    
    public abstract View i();
    
    public Object j() {
        return this.a;
    }
    
    public boolean k() {
        return this.b;
    }
    
    public interface a
    {
        void a(final b p0);
        
        boolean a(final b p0, final Menu p1);
        
        boolean a(final b p0, final MenuItem p1);
        
        boolean b(final b p0, final Menu p1);
    }
}
