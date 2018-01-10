// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.support.v7.view.b;
import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.os.Bundle;
import android.view.Window$Callback;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.app.Activity;
import android.view.Window;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(14)
class i extends h
{
    private int t;
    private boolean u;
    private boolean v;
    private b w;
    
    i(final Context context, final Window window, final android.support.v7.app.d d) {
        super(context, window, d);
        this.t = -100;
        this.v = true;
    }
    
    private boolean h(int n) {
        final Resources resources = this.a.getResources();
        final Configuration configuration = resources.getConfiguration();
        final int uiMode = configuration.uiMode;
        if (n == 2) {
            n = 32;
        }
        else {
            n = 16;
        }
        if ((uiMode & 0x30) != n) {
            if (this.y()) {
                ((Activity)this.a).recreate();
            }
            else {
                final Configuration configuration2 = new Configuration(configuration);
                final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                configuration2.uiMode = (n | (configuration2.uiMode & 0xFFFFFFCF));
                resources.updateConfiguration(configuration2, displayMetrics);
                n.a(resources);
            }
            return true;
        }
        return false;
    }
    
    private int w() {
        if (this.t != -100) {
            return this.t;
        }
        return android.support.v7.app.e.j();
    }
    
    private void x() {
        if (this.w == null) {
            this.w = new b(android.support.v7.app.q.a(this.a));
        }
    }
    
    private boolean y() {
        if (this.u && this.a instanceof Activity) {
            final PackageManager packageManager = this.a.getPackageManager();
            try {
                return (packageManager.getActivityInfo(new ComponentName(this.a, (Class)this.a.getClass()), 0).configChanges & 0x200) == 0x0;
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", (Throwable)ex);
                return true;
            }
        }
        return false;
    }
    
    @Override
    Window$Callback a(final Window$Callback window$Callback) {
        return (Window$Callback)new a(window$Callback);
    }
    
    @Override
    public void a(final Bundle bundle) {
        super.a(bundle);
        if (bundle != null && this.t == -100) {
            this.t = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }
    
    @Override
    public void c() {
        super.c();
        this.i();
    }
    
    @Override
    public void c(final Bundle bundle) {
        super.c(bundle);
        if (this.t != -100) {
            bundle.putInt("appcompat:local_night_mode", this.t);
        }
    }
    
    int d(final int n) {
        switch (n) {
            default: {
                return n;
            }
            case 0: {
                this.x();
                return this.w.a();
            }
            case -100: {
                return -1;
            }
        }
    }
    
    @Override
    public void d() {
        super.d();
        if (this.w != null) {
            this.w.d();
        }
    }
    
    @Override
    public void g() {
        super.g();
        if (this.w != null) {
            this.w.d();
        }
    }
    
    @Override
    public boolean i() {
        boolean h = false;
        final int w = this.w();
        final int d = this.d(w);
        if (d != -1) {
            h = this.h(d);
        }
        if (w == 0) {
            this.x();
            this.w.c();
        }
        this.u = true;
        return h;
    }
    
    @Override
    public boolean o() {
        return this.v;
    }
    
    class a extends f.a
    {
        a(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        final ActionMode a(final ActionMode$Callback actionMode$Callback) {
            final android.support.v7.view.f.a a = new android.support.v7.view.f.a(i.this.a, actionMode$Callback);
            final android.support.v7.view.b b = i.this.b(a);
            if (b != null) {
                return a.b(b);
            }
            return null;
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
            if (i.this.o()) {
                return this.a(actionMode$Callback);
            }
            return super.onWindowStartingActionMode(actionMode$Callback);
        }
    }
    
    final class b
    {
        private q b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;
        
        b(final q b) {
            this.b = b;
            this.c = b.a();
        }
        
        final int a() {
            this.c = this.b.a();
            if (this.c) {
                return 2;
            }
            return 1;
        }
        
        final void b() {
            final boolean a = this.b.a();
            if (a != this.c) {
                this.c = a;
                i.this.i();
            }
        }
        
        final void c() {
            this.d();
            if (this.d == null) {
                this.d = new BroadcastReceiver() {
                    public void onReceive(final Context context, final Intent intent) {
                        i.b.this.b();
                    }
                };
            }
            if (this.e == null) {
                (this.e = new IntentFilter()).addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            i.this.a.registerReceiver(this.d, this.e);
        }
        
        final void d() {
            if (this.d != null) {
                i.this.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }
}
