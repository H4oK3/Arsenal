// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Menu;
import android.view.MenuItem;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v7.widget.ax;
import android.view.MenuInflater;
import android.content.Context;
import android.support.v4.g.h;
import android.view.KeyEvent;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v7.view.b;
import android.app.Activity;
import android.support.v4.a.z;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.a.ag;
import android.support.v4.a.l;

public class c extends l implements ag.a, d
{
    private e m;
    private int n;
    private boolean o;
    private Resources p;
    
    public c() {
        this.n = 0;
    }
    
    @Override
    public Intent a() {
        return z.a(this);
    }
    
    @Override
    public b a(final b.a a) {
        return null;
    }
    
    public void a(final ag ag) {
        ag.a(this);
    }
    
    @Override
    public void a(final b b) {
    }
    
    public boolean a(final Intent intent) {
        return z.a(this, intent);
    }
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.h().b(view, viewGroup$LayoutParams);
    }
    
    public void b(final Intent intent) {
        z.b(this, intent);
    }
    
    public void b(final ag ag) {
    }
    
    @Override
    public void b(final b b) {
    }
    
    @Override
    public void c() {
        this.h().f();
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        if (android.support.v4.g.h.a(keyEvent) && keyEvent.getUnicodeChar(keyEvent.getMetaState() & 0xFFFF8FFF) == 60) {
            final int action = keyEvent.getAction();
            if (action == 0) {
                final a e = this.e();
                if (e != null && e.b() && e.g()) {
                    return this.o = true;
                }
            }
            else if (action == 1 && this.o) {
                this.o = false;
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }
    
    public a e() {
        return this.h().a();
    }
    
    public boolean f() {
        final Intent a = this.a();
        if (a != null) {
            Label_0050: {
                if (!this.a(a)) {
                    break Label_0050;
                }
                final ag a2 = ag.a((Context)this);
                this.a(a2);
                this.b(a2);
                a2.a();
                try {
                    android.support.v4.a.a.a(this);
                    return true;
                }
                catch (IllegalStateException ex) {
                    this.finish();
                    return true;
                }
            }
            this.b(a);
            return true;
        }
        return false;
    }
    
    public View findViewById(final int n) {
        return this.h().a(n);
    }
    
    @Deprecated
    public void g() {
    }
    
    public MenuInflater getMenuInflater() {
        return this.h().b();
    }
    
    public Resources getResources() {
        if (this.p == null && ax.a()) {
            this.p = new ax((Context)this, super.getResources());
        }
        if (this.p == null) {
            return super.getResources();
        }
        return this.p;
    }
    
    public e h() {
        if (this.m == null) {
            this.m = android.support.v7.app.e.a(this, this);
        }
        return this.m;
    }
    
    public void invalidateOptionsMenu() {
        this.h().f();
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.h().a(configuration);
        if (this.p != null) {
            this.p.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }
    
    public void onContentChanged() {
        this.g();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        final e h = this.h();
        h.h();
        h.a(bundle);
        if (h.i() && this.n != 0) {
            if (Build$VERSION.SDK_INT >= 23) {
                this.onApplyThemeResource(this.getTheme(), this.n, false);
            }
            else {
                this.setTheme(this.n);
            }
        }
        super.onCreate(bundle);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.h().g();
    }
    
    @Override
    public final boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        final a e = this.e();
        return menuItem.getItemId() == 16908332 && e != null && (e.a() & 0x4) != 0x0 && this.f();
    }
    
    public boolean onMenuOpened(final int n, final Menu menu) {
        return super.onMenuOpened(n, menu);
    }
    
    @Override
    public void onPanelClosed(final int n, final Menu menu) {
        super.onPanelClosed(n, menu);
    }
    
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        this.h().b(bundle);
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.h().e();
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.h().c(bundle);
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        this.h().c();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        this.h().d();
    }
    
    protected void onTitleChanged(final CharSequence charSequence, final int n) {
        super.onTitleChanged(charSequence, n);
        this.h().a(charSequence);
    }
    
    public void setContentView(final int n) {
        this.h().b(n);
    }
    
    public void setContentView(final View view) {
        this.h().a(view);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.h().a(view, viewGroup$LayoutParams);
    }
    
    public void setTheme(final int n) {
        super.setTheme(n);
        this.n = n;
    }
}
