// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.DialogInterface$OnClickListener;
import android.widget.ListAdapter;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.content.DialogInterface$OnKeyListener;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.os.Bundle;
import android.support.v7.a.a;
import android.util.TypedValue;
import android.content.Context;
import android.content.DialogInterface;

public class b extends l implements DialogInterface
{
    final AlertController a;
    
    protected b(final Context context, final int n) {
        super(context, a(context, n));
        this.a = new AlertController(this.getContext(), this, this.getWindow());
    }
    
    static int a(final Context context, final int n) {
        if (n >= 16777216) {
            return n;
        }
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.support.v7.a.a.a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.a.a();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.a.a(n, keyEvent) || super.onKeyDown(n, keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return this.a.b(n, keyEvent) || super.onKeyUp(n, keyEvent);
    }
    
    @Override
    public void setTitle(final CharSequence title) {
        super.setTitle(title);
        this.a.a(title);
    }
    
    public static class a
    {
        private final AlertController.a a;
        private final int b;
        
        public a(final Context context) {
            this(context, b.a(context, 0));
        }
        
        public a(final Context context, final int b) {
            this.a = new AlertController.a((Context)new ContextThemeWrapper(context, b.a(context, b)));
            this.b = b;
        }
        
        public Context a() {
            return this.a.a;
        }
        
        public a a(final DialogInterface$OnKeyListener r) {
            this.a.r = r;
            return this;
        }
        
        public a a(final Drawable d) {
            this.a.d = d;
            return this;
        }
        
        public a a(final View g) {
            this.a.g = g;
            return this;
        }
        
        public a a(final ListAdapter t, final DialogInterface$OnClickListener u) {
            this.a.t = t;
            this.a.u = u;
            return this;
        }
        
        public a a(final CharSequence f) {
            this.a.f = f;
            return this;
        }
        
        public b b() {
            final b b = new b(this.a.a, this.b);
            this.a.a(b.a);
            b.setCancelable(this.a.o);
            if (this.a.o) {
                b.setCanceledOnTouchOutside(true);
            }
            b.setOnCancelListener(this.a.p);
            b.setOnDismissListener(this.a.q);
            if (this.a.r != null) {
                b.setOnKeyListener(this.a.r);
            }
            return b;
        }
    }
}
