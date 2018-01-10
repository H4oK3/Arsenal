// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.method.TransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.content.res.ColorStateList;
import android.content.Context;
import android.widget.TextView;
import android.annotation.TargetApi;

@TargetApi(9)
class y
{
    final TextView a;
    private at b;
    private at c;
    private at d;
    private at e;
    
    y(final TextView a) {
        this.a = a;
    }
    
    protected static at a(final Context context, final l l, final int n) {
        final ColorStateList b = l.b(context, n);
        if (b != null) {
            final at at = new at();
            at.d = true;
            at.a = b;
            return at;
        }
        return null;
    }
    
    static y a(final TextView textView) {
        if (Build$VERSION.SDK_INT >= 17) {
            return new z(textView);
        }
        return new y(textView);
    }
    
    void a() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            final Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            this.a(compoundDrawables[0], this.b);
            this.a(compoundDrawables[1], this.c);
            this.a(compoundDrawables[2], this.d);
            this.a(compoundDrawables[3], this.e);
        }
    }
    
    void a(final Context context, final int n) {
        final av a = av.a(context, n, android.support.v7.a.a.j.TextAppearance);
        if (a.g(android.support.v7.a.a.j.TextAppearance_textAllCaps)) {
            this.a(a.a(android.support.v7.a.a.j.TextAppearance_textAllCaps, false));
        }
        if (Build$VERSION.SDK_INT < 23 && a.g(android.support.v7.a.a.j.TextAppearance_android_textColor)) {
            final ColorStateList e = a.e(android.support.v7.a.a.j.TextAppearance_android_textColor);
            if (e != null) {
                this.a.setTextColor(e);
            }
        }
        a.a();
    }
    
    final void a(final Drawable drawable, final at at) {
        if (drawable != null && at != null) {
            l.a(drawable, at, this.a.getDrawableState());
        }
    }
    
    void a(final AttributeSet set, int n) {
        final ColorStateList list = null;
        ColorStateList e = null;
        final Context context = this.a.getContext();
        final l a = l.a();
        final av a2 = av.a(context, set, android.support.v7.a.a.j.AppCompatTextHelper, n, 0);
        final int g = a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_textAppearance, -1);
        if (a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableLeft)) {
            this.b = a(context, a, a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableTop)) {
            this.c = a(context, a, a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableRight)) {
            this.d = a(context, a, a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableBottom)) {
            this.e = a(context, a, a2.g(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        a2.a();
        final boolean b = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean a4;
        int n2;
        ColorStateList e3;
        if (g != -1) {
            final av a3 = av.a(context, g, android.support.v7.a.a.j.TextAppearance);
            if (!b && a3.g(android.support.v7.a.a.j.TextAppearance_textAllCaps)) {
                a4 = a3.a(android.support.v7.a.a.j.TextAppearance_textAllCaps, false);
                n2 = 1;
            }
            else {
                n2 = 0;
                a4 = false;
            }
            ColorStateList list2;
            if (Build$VERSION.SDK_INT < 23) {
                ColorStateList e2;
                if (a3.g(android.support.v7.a.a.j.TextAppearance_android_textColor)) {
                    e2 = a3.e(android.support.v7.a.a.j.TextAppearance_android_textColor);
                }
                else {
                    e2 = null;
                }
                list2 = e2;
                if (a3.g(android.support.v7.a.a.j.TextAppearance_android_textColorHint)) {
                    e = a3.e(android.support.v7.a.a.j.TextAppearance_android_textColorHint);
                    list2 = e2;
                }
            }
            else {
                list2 = null;
            }
            a3.a();
            e3 = list2;
        }
        else {
            e3 = null;
            n2 = 0;
            a4 = false;
            e = list;
        }
        final av a5 = av.a(context, set, android.support.v7.a.a.j.TextAppearance, n, 0);
        n = n2;
        boolean a6 = a4;
        if (!b) {
            n = n2;
            a6 = a4;
            if (a5.g(android.support.v7.a.a.j.TextAppearance_textAllCaps)) {
                a6 = a5.a(android.support.v7.a.a.j.TextAppearance_textAllCaps, false);
                n = 1;
            }
        }
        ColorStateList textColor = e3;
        ColorStateList e4 = e;
        if (Build$VERSION.SDK_INT < 23) {
            if (a5.g(android.support.v7.a.a.j.TextAppearance_android_textColor)) {
                e3 = a5.e(android.support.v7.a.a.j.TextAppearance_android_textColor);
            }
            textColor = e3;
            e4 = e;
            if (a5.g(android.support.v7.a.a.j.TextAppearance_android_textColorHint)) {
                e4 = a5.e(android.support.v7.a.a.j.TextAppearance_android_textColorHint);
                textColor = e3;
            }
        }
        a5.a();
        if (textColor != null) {
            this.a.setTextColor(textColor);
        }
        if (e4 != null) {
            this.a.setHintTextColor(e4);
        }
        if (!b && n != 0) {
            this.a(a6);
        }
    }
    
    void a(final boolean b) {
        final TextView a = this.a;
        Object transformationMethod;
        if (b) {
            transformationMethod = new android.support.v7.d.a(this.a.getContext());
        }
        else {
            transformationMethod = null;
        }
        a.setTransformationMethod((TransformationMethod)transformationMethod);
    }
}
