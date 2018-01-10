// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.support.v7.view.menu.h;
import android.support.v7.view.i;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.view.g;
import android.view.KeyEvent;
import android.view.Menu;
import android.support.v7.view.b;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.support.v7.widget.av;
import android.content.res.Resources$NotFoundException;
import android.os.Build$VERSION;
import android.view.MenuInflater;
import android.view.Window$Callback;
import android.view.Window;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(9)
abstract class f extends e
{
    private static boolean m;
    private static final boolean n;
    private static final int[] o;
    final Context a;
    final Window b;
    final Window$Callback c;
    final Window$Callback d;
    final d e;
    android.support.v7.app.a f;
    MenuInflater g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    private CharSequence p;
    private boolean q;
    private boolean r;
    
    static {
        n = (Build$VERSION.SDK_INT < 21);
        if (f.n && !f.m) {
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new Thread.UncaughtExceptionHandler() {
                final /* synthetic */ UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
                
                private boolean a(final Throwable t) {
                    boolean b2;
                    final boolean b = b2 = false;
                    if (t instanceof Resources$NotFoundException) {
                        final String message = t.getMessage();
                        b2 = b;
                        if (message != null) {
                            if (!message.contains("drawable")) {
                                b2 = b;
                                if (!message.contains("Drawable")) {
                                    return b2;
                                }
                            }
                            b2 = true;
                        }
                    }
                    return b2;
                }
                
                @Override
                public void uncaughtException(final Thread thread, final Throwable t) {
                    if (this.a(t)) {
                        final Resources$NotFoundException ex = new Resources$NotFoundException(t.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        ((Throwable)ex).initCause(t.getCause());
                        ((Throwable)ex).setStackTrace(t.getStackTrace());
                        this.a.uncaughtException(thread, (Throwable)ex);
                        return;
                    }
                    this.a.uncaughtException(thread, t);
                }
            });
            f.m = true;
        }
        o = new int[] { 16842836 };
    }
    
    f(final Context a, final Window b, final d e) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.c = this.b.getCallback();
        if (this.c instanceof a) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.d = this.a(this.c);
        this.b.setCallback(this.d);
        final av a2 = av.a(a, null, android.support.v7.app.f.o);
        final Drawable b2 = a2.b(0);
        if (b2 != null) {
            this.b.setBackgroundDrawable(b2);
        }
        a2.a();
    }
    
    @Override
    public android.support.v7.app.a a() {
        this.l();
        return this.f;
    }
    
    abstract b a(final b.a p0);
    
    Window$Callback a(final Window$Callback window$Callback) {
        return (Window$Callback)new a(window$Callback);
    }
    
    abstract void a(final int p0, final Menu p1);
    
    @Override
    public final void a(final CharSequence p) {
        this.b(this.p = p);
    }
    
    abstract boolean a(final int p0, final KeyEvent p1);
    
    abstract boolean a(final KeyEvent p0);
    
    @Override
    public MenuInflater b() {
        if (this.g == null) {
            this.l();
            Context context;
            if (this.f != null) {
                context = this.f.c();
            }
            else {
                context = this.a;
            }
            this.g = new g(context);
        }
        return this.g;
    }
    
    abstract void b(final CharSequence p0);
    
    abstract boolean b(final int p0, final Menu p1);
    
    @Override
    public void c() {
        this.q = true;
    }
    
    @Override
    public void c(final Bundle bundle) {
    }
    
    @Override
    public void d() {
        this.q = false;
    }
    
    @Override
    public void g() {
        this.r = true;
    }
    
    @Override
    public boolean i() {
        return false;
    }
    
    abstract void l();
    
    final android.support.v7.app.a m() {
        return this.f;
    }
    
    final Context n() {
        Context c = null;
        final android.support.v7.app.a a = this.a();
        if (a != null) {
            c = a.c();
        }
        Context a2;
        if ((a2 = c) == null) {
            a2 = this.a;
        }
        return a2;
    }
    
    public boolean o() {
        return false;
    }
    
    final boolean p() {
        return this.r;
    }
    
    final Window$Callback q() {
        return this.b.getCallback();
    }
    
    final CharSequence r() {
        if (this.c instanceof Activity) {
            return ((Activity)this.c).getTitle();
        }
        return this.p;
    }
    
    class a extends i
    {
        a(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
            return android.support.v7.app.f.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }
        
        @Override
        public boolean dispatchKeyShortcutEvent(final KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || android.support.v7.app.f.this.a(keyEvent.getKeyCode(), keyEvent);
        }
        
        @Override
        public void onContentChanged() {
        }
        
        @Override
        public boolean onCreatePanelMenu(final int n, final Menu menu) {
            return (n != 0 || menu instanceof h) && super.onCreatePanelMenu(n, menu);
        }
        
        @Override
        public boolean onMenuOpened(final int n, final Menu menu) {
            super.onMenuOpened(n, menu);
            android.support.v7.app.f.this.b(n, menu);
            return true;
        }
        
        @Override
        public void onPanelClosed(final int n, final Menu menu) {
            super.onPanelClosed(n, menu);
            android.support.v7.app.f.this.a(n, menu);
        }
        
        @Override
        public boolean onPreparePanel(final int n, final View view, final Menu menu) {
            h h;
            if (menu instanceof h) {
                h = (h)menu;
            }
            else {
                h = null;
            }
            boolean onPreparePanel;
            if (n == 0 && h == null) {
                onPreparePanel = false;
            }
            else {
                if (h != null) {
                    h.c(true);
                }
                onPreparePanel = super.onPreparePanel(n, view, menu);
                if (h != null) {
                    h.c(false);
                    return onPreparePanel;
                }
            }
            return onPreparePanel;
        }
    }
}
