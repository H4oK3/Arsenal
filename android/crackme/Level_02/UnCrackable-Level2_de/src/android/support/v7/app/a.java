// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import android.view.KeyEvent;
import android.content.res.Configuration;
import android.support.v7.view.b;

public abstract class a
{
    public abstract int a();
    
    public android.support.v7.view.b a(final android.support.v7.view.b.a a) {
        return null;
    }
    
    public void a(final float n) {
        if (n != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }
    
    public void a(final Configuration configuration) {
    }
    
    public void a(final CharSequence charSequence) {
    }
    
    public void a(final boolean b) {
    }
    
    public boolean a(final int n, final KeyEvent keyEvent) {
        return false;
    }
    
    public void b(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }
    
    public abstract boolean b();
    
    public Context c() {
        return null;
    }
    
    public void c(final boolean b) {
    }
    
    public int d() {
        return 0;
    }
    
    public void d(final boolean b) {
    }
    
    public void e(final boolean b) {
    }
    
    public boolean e() {
        return false;
    }
    
    public boolean f() {
        return false;
    }
    
    boolean g() {
        return false;
    }
    
    void h() {
    }
    
    public static class a extends ViewGroup$MarginLayoutParams
    {
        public int a;
        
        public a(final int n, final int n2) {
            super(n, n2);
            this.a = 0;
            this.a = 8388627;
        }
        
        public a(final Context context, final AttributeSet set) {
            super(context, set);
            this.a = 0;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.ActionBarLayout);
            this.a = obtainStyledAttributes.getInt(android.support.v7.a.a.j.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }
        
        public a(final a a) {
            super((ViewGroup$MarginLayoutParams)a);
            this.a = 0;
            this.a = a.a;
        }
        
        public a(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.a = 0;
        }
    }
    
    public interface b
    {
        void a(final boolean p0);
    }
    
    @Deprecated
    public abstract static class c
    {
        public abstract Drawable a();
        
        public abstract CharSequence b();
        
        public abstract View c();
        
        public abstract void d();
        
        public abstract CharSequence e();
    }
}
