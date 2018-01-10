// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v7.b.a.b;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.CompoundButton;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.widget.v;
import android.widget.CheckBox;

public class i extends CheckBox implements v
{
    private k a;
    
    public i(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.checkboxStyle);
    }
    
    public i(final Context context, final AttributeSet set, final int n) {
        super(as.a(context), set, n);
        (this.a = new k((CompoundButton)this)).a(set, n);
    }
    
    public int getCompoundPaddingLeft() {
        int n = super.getCompoundPaddingLeft();
        if (this.a != null) {
            n = this.a.a(n);
        }
        return n;
    }
    
    public ColorStateList getSupportButtonTintList() {
        if (this.a != null) {
            return this.a.a();
        }
        return null;
    }
    
    public PorterDuff$Mode getSupportButtonTintMode() {
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }
    
    public void setButtonDrawable(final int n) {
        this.setButtonDrawable(b.b(this.getContext(), n));
    }
    
    public void setButtonDrawable(final Drawable buttonDrawable) {
        super.setButtonDrawable(buttonDrawable);
        if (this.a != null) {
            this.a.c();
        }
    }
    
    public void setSupportButtonTintList(final ColorStateList list) {
        if (this.a != null) {
            this.a.a(list);
        }
    }
    
    public void setSupportButtonTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.a != null) {
            this.a.a(porterDuff$Mode);
        }
    }
}
