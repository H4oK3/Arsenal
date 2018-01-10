// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.b.a.b;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.TextView;
import android.view.View;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.g.ab;
import android.widget.MultiAutoCompleteTextView;

public class q extends MultiAutoCompleteTextView implements ab
{
    private static final int[] a;
    private g b;
    private y c;
    
    static {
        a = new int[] { 16843126 };
    }
    
    public q(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.autoCompleteTextViewStyle);
    }
    
    public q(final Context context, final AttributeSet set, final int n) {
        super(as.a(context), set, n);
        final av a = av.a(this.getContext(), set, q.a, n, 0);
        if (a.g(0)) {
            this.setDropDownBackgroundDrawable(a.a(0));
        }
        a.a();
        (this.b = new g((View)this)).a(set, n);
        (this.c = y.a((TextView)this)).a(set, n);
        this.c.a();
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.c();
        }
        if (this.c != null) {
            this.c.a();
        }
    }
    
    public ColorStateList getSupportBackgroundTintList() {
        if (this.b != null) {
            return this.b.a();
        }
        return null;
    }
    
    public PorterDuff$Mode getSupportBackgroundTintMode() {
        if (this.b != null) {
            return this.b.b();
        }
        return null;
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        super.setBackgroundDrawable(backgroundDrawable);
        if (this.b != null) {
            this.b.a(backgroundDrawable);
        }
    }
    
    public void setBackgroundResource(final int backgroundResource) {
        super.setBackgroundResource(backgroundResource);
        if (this.b != null) {
            this.b.a(backgroundResource);
        }
    }
    
    public void setDropDownBackgroundResource(final int n) {
        this.setDropDownBackgroundDrawable(android.support.v7.b.a.b.b(this.getContext(), n));
    }
    
    public void setSupportBackgroundTintList(final ColorStateList list) {
        if (this.b != null) {
            this.b.a(list);
        }
    }
    
    public void setSupportBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.b != null) {
            this.b.a(porterDuff$Mode);
        }
    }
    
    public void setTextAppearance(final Context context, final int n) {
        super.setTextAppearance(context, n);
        if (this.c != null) {
            this.c.a(context, n);
        }
    }
}
