// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.TextView;

class TextViewCompatJbMr2
{
    public static void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, final int n, final int n2, final int n3, final int n4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(n, n2, n3, n4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }
}
