// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.support.v7.view.b;
import android.support.v7.a.a;
import android.util.TypedValue;
import android.os.Bundle;
import android.content.Context;
import android.app.Dialog;

public class l extends Dialog implements d
{
    private e a;
    
    public l(final Context context, final int n) {
        super(context, a(context, n));
        this.a().a((Bundle)null);
        this.a().i();
    }
    
    private static int a(final Context context, final int n) {
        int resourceId = n;
        if (n == 0) {
            final TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(a.a.dialogTheme, typedValue, true);
            resourceId = typedValue.resourceId;
        }
        return resourceId;
    }
    
    public e a() {
        if (this.a == null) {
            this.a = e.a(this, this);
        }
        return this.a;
    }
    
    public b a(final b.a a) {
        return null;
    }
    
    public void a(final b b) {
    }
    
    public boolean a(final int n) {
        return this.a().c(n);
    }
    
    public void addContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.a().b(view, viewGroup$LayoutParams);
    }
    
    public void b(final b b) {
    }
    
    public View findViewById(final int n) {
        return this.a().a(n);
    }
    
    public void invalidateOptionsMenu() {
        this.a().f();
    }
    
    protected void onCreate(final Bundle bundle) {
        this.a().h();
        super.onCreate(bundle);
        this.a().a(bundle);
    }
    
    protected void onStop() {
        super.onStop();
        this.a().d();
    }
    
    public void setContentView(final int n) {
        this.a().b(n);
    }
    
    public void setContentView(final View view) {
        this.a().a(view);
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.a().a(view, viewGroup$LayoutParams);
    }
    
    public void setTitle(final int title) {
        super.setTitle(title);
        this.a().a(this.getContext().getString(title));
    }
    
    public void setTitle(final CharSequence title) {
        super.setTitle(title);
        this.a().a(title);
    }
}
