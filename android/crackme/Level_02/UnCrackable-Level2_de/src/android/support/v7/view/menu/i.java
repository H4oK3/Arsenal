// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.KeyEvent$DispatcherState;
import android.view.Window;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import android.support.v7.a.a;
import android.os.IBinder;
import android.support.v7.app.b;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnClickListener;

class i implements DialogInterface$OnClickListener, DialogInterface$OnDismissListener, DialogInterface$OnKeyListener, a
{
    f a;
    private h b;
    private b c;
    private a d;
    
    public i(final h b) {
        this.b = b;
    }
    
    public void a() {
        if (this.c != null) {
            this.c.dismiss();
        }
    }
    
    public void a(final IBinder token) {
        final h b = this.b;
        final b.a a = new b.a(b.e());
        (this.a = new f(a.a(), android.support.v7.a.a.g.abc_list_menu_item_layout)).a(this);
        this.b.a(this.a);
        a.a(this.a.a(), (DialogInterface$OnClickListener)this);
        final View o = b.o();
        if (o != null) {
            a.a(o);
        }
        else {
            a.a(b.n()).a(b.m());
        }
        a.a((DialogInterface$OnKeyListener)this);
        (this.c = a.b()).setOnDismissListener((DialogInterface$OnDismissListener)this);
        final WindowManager$LayoutParams attributes = this.c.getWindow().getAttributes();
        attributes.type = 1003;
        if (token != null) {
            attributes.token = token;
        }
        attributes.flags |= 0x20000;
        this.c.show();
    }
    
    public void a(final h h, final boolean b) {
        if (b || h == this.b) {
            this.a();
        }
        if (this.d != null) {
            this.d.a(h, b);
        }
    }
    
    public boolean a(final h h) {
        return this.d != null && this.d.a(h);
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.b.a((MenuItem)this.a.a().getItem(n), 0);
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        this.a.a(this.b, true);
    }
    
    public boolean onKey(final DialogInterface dialogInterface, final int n, final KeyEvent keyEvent) {
        if (n == 82 || n == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                final Window window = this.c.getWindow();
                if (window != null) {
                    final View decorView = window.getDecorView();
                    if (decorView != null) {
                        final KeyEvent$DispatcherState keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null) {
                            keyDispatcherState.startTracking(keyEvent, (Object)this);
                            return true;
                        }
                    }
                }
            }
            else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                final Window window2 = this.c.getWindow();
                if (window2 != null) {
                    final View decorView2 = window2.getDecorView();
                    if (decorView2 != null) {
                        final KeyEvent$DispatcherState keyDispatcherState2 = decorView2.getKeyDispatcherState();
                        if (keyDispatcherState2 != null && keyDispatcherState2.isTracking(keyEvent)) {
                            this.b.a(true);
                            dialogInterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.b.performShortcut(n, keyEvent, 0);
    }
}
