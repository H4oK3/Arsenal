// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.MenuInflater;
import android.view.ViewGroup$LayoutParams;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.View;
import android.support.v4.e.c;
import android.os.Build$VERSION;
import android.view.Window;
import android.app.Dialog;
import android.content.Context;
import android.app.Activity;

public abstract class e
{
    private static int a;
    private static boolean b;
    
    static {
        e.a = -1;
        e.b = false;
    }
    
    public static e a(final Activity activity, final d d) {
        return a((Context)activity, activity.getWindow(), d);
    }
    
    public static e a(final Dialog dialog, final d d) {
        return a(dialog.getContext(), dialog.getWindow(), d);
    }
    
    private static e a(final Context context, final Window window, final d d) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (c.a()) {
            return new g(context, window, d);
        }
        if (sdk_INT >= 23) {
            return new j(context, window, d);
        }
        if (sdk_INT >= 14) {
            return new i(context, window, d);
        }
        if (sdk_INT >= 11) {
            return new h(context, window, d);
        }
        return new k(context, window, d);
    }
    
    public static int j() {
        return e.a;
    }
    
    public static boolean k() {
        return e.b;
    }
    
    public abstract a a();
    
    public abstract View a(final int p0);
    
    public abstract void a(final Configuration p0);
    
    public abstract void a(final Bundle p0);
    
    public abstract void a(final View p0);
    
    public abstract void a(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract void a(final CharSequence p0);
    
    public abstract MenuInflater b();
    
    public abstract void b(final int p0);
    
    public abstract void b(final Bundle p0);
    
    public abstract void b(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract void c();
    
    public abstract void c(final Bundle p0);
    
    public abstract boolean c(final int p0);
    
    public abstract void d();
    
    public abstract void e();
    
    public abstract void f();
    
    public abstract void g();
    
    public abstract void h();
    
    public abstract boolean i();
}
