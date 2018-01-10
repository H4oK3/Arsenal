// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.b;
import android.os.Build$VERSION;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.CompoundButton;

class k
{
    private final CompoundButton a;
    private ColorStateList b;
    private PorterDuff$Mode c;
    private boolean d;
    private boolean e;
    private boolean f;
    
    k(final CompoundButton a) {
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.a = a;
    }
    
    int a(final int n) {
        int n2 = n;
        if (Build$VERSION.SDK_INT < 17) {
            final Drawable a = android.support.v4.widget.b.a(this.a);
            n2 = n;
            if (a != null) {
                n2 = n + a.getIntrinsicWidth();
            }
        }
        return n2;
    }
    
    ColorStateList a() {
        return this.b;
    }
    
    void a(final ColorStateList b) {
        this.b = b;
        this.d = true;
        this.d();
    }
    
    void a(final PorterDuff$Mode c) {
        this.c = c;
        this.e = true;
        this.d();
    }
    
    void a(AttributeSet obtainStyledAttributes, int resourceId) {
        obtainStyledAttributes = (AttributeSet)this.a.getContext().obtainStyledAttributes(obtainStyledAttributes, android.support.v7.a.a.j.CompoundButton, resourceId, 0);
        try {
            if (((TypedArray)obtainStyledAttributes).hasValue(android.support.v7.a.a.j.CompoundButton_android_button)) {
                resourceId = ((TypedArray)obtainStyledAttributes).getResourceId(android.support.v7.a.a.j.CompoundButton_android_button, 0);
                if (resourceId != 0) {
                    this.a.setButtonDrawable(android.support.v7.b.a.b.b(this.a.getContext(), resourceId));
                }
            }
            if (((TypedArray)obtainStyledAttributes).hasValue(android.support.v7.a.a.j.CompoundButton_buttonTint)) {
                android.support.v4.widget.b.a(this.a, ((TypedArray)obtainStyledAttributes).getColorStateList(android.support.v7.a.a.j.CompoundButton_buttonTint));
            }
            if (((TypedArray)obtainStyledAttributes).hasValue(android.support.v7.a.a.j.CompoundButton_buttonTintMode)) {
                android.support.v4.widget.b.a(this.a, ad.a(((TypedArray)obtainStyledAttributes).getInt(android.support.v7.a.a.j.CompoundButton_buttonTintMode, -1), null));
            }
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    PorterDuff$Mode b() {
        return this.c;
    }
    
    void c() {
        if (this.f) {
            this.f = false;
            return;
        }
        this.f = true;
        this.d();
    }
    
    void d() {
        final Drawable a = android.support.v4.widget.b.a(this.a);
        if (a != null && (this.d || this.e)) {
            final Drawable mutate = android.support.v4.c.a.a.f(a).mutate();
            if (this.d) {
                android.support.v4.c.a.a.a(mutate, this.b);
            }
            if (this.e) {
                android.support.v4.c.a.a.a(mutate, this.c);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.a.getDrawableState());
            }
            this.a.setButtonDrawable(mutate);
        }
    }
}
