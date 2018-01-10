// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.content.Context;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.annotation.TargetApi;

@TargetApi(17)
class z extends y
{
    private at b;
    private at c;
    
    z(final TextView textView) {
        super(textView);
    }
    
    @Override
    void a() {
        super.a();
        if (this.b != null || this.c != null) {
            final Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            this.a(compoundDrawablesRelative[0], this.b);
            this.a(compoundDrawablesRelative[2], this.c);
        }
    }
    
    @Override
    void a(final AttributeSet set, final int n) {
        super.a(set, n);
        final Context context = this.a.getContext();
        final l a = l.a();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.AppCompatTextHelper, n, 0);
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableStart)) {
            this.b = y.a(context, a, obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableEnd)) {
            this.c = y.a(context, a, obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        obtainStyledAttributes.recycle();
    }
}
