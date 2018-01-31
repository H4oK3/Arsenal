// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.os.Build$VERSION;

public class TextViewCompat
{
    static final TextViewCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 18) {
            IMPL = (TextViewCompatImpl)new JbMr2TextViewCompatImpl();
            return;
        }
        if (sdk_INT >= 17) {
            IMPL = (TextViewCompatImpl)new JbMr1TextViewCompatImpl();
            return;
        }
        if (sdk_INT >= 16) {
            IMPL = (TextViewCompatImpl)new JbTextViewCompatImpl();
            return;
        }
        IMPL = (TextViewCompatImpl)new BaseTextViewCompatImpl();
    }
    
    public static int getMaxLines(@NonNull final TextView textView) {
        return TextViewCompat.IMPL.getMaxLines(textView);
    }
    
    public static int getMinLines(@NonNull final TextView textView) {
        return TextViewCompat.IMPL.getMinLines(textView);
    }
    
    public static void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        TextViewCompat.IMPL.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, final int n, final int n2, final int n3, final int n4) {
        TextViewCompat.IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n, n2, n3, n4);
    }
    
    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
        TextViewCompat.IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
    }
    
    static class BaseTextViewCompatImpl implements TextViewCompatImpl
    {
        @Override
        public int getMaxLines(final TextView textView) {
            return TextViewCompatDonut.getMaxLines(textView);
        }
        
        @Override
        public int getMinLines(final TextView textView) {
            return TextViewCompatDonut.getMinLines(textView);
        }
        
        @Override
        public void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, final int n, final int n2, final int n3, final int n4) {
            textView.setCompoundDrawablesWithIntrinsicBounds(n, n2, n3, n4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }
    
    static class JbMr1TextViewCompatImpl extends JbTextViewCompatImpl
    {
        @Override
        public void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, final int n, final int n2, final int n3, final int n4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n, n2, n3, n4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
        }
    }
    
    static class JbMr2TextViewCompatImpl extends JbMr1TextViewCompatImpl
    {
        @Override
        public void setCompoundDrawablesRelative(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, final int n, final int n2, final int n3, final int n4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, n, n2, n3, n4);
        }
        
        @Override
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView textView, @Nullable final Drawable drawable, @Nullable final Drawable drawable2, @Nullable final Drawable drawable3, @Nullable final Drawable drawable4) {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
        }
    }
    
    static class JbTextViewCompatImpl extends BaseTextViewCompatImpl
    {
        @Override
        public int getMaxLines(final TextView textView) {
            return TextViewCompatJb.getMaxLines(textView);
        }
        
        @Override
        public int getMinLines(final TextView textView) {
            return TextViewCompatJb.getMinLines(textView);
        }
    }
    
    interface TextViewCompatImpl
    {
        int getMaxLines(final TextView p0);
        
        int getMinLines(final TextView p0);
        
        void setCompoundDrawablesRelative(@NonNull final TextView p0, @Nullable final Drawable p1, @Nullable final Drawable p2, @Nullable final Drawable p3, @Nullable final Drawable p4);
        
        void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView p0, final int p1, final int p2, final int p3, final int p4);
        
        void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull final TextView p0, @Nullable final Drawable p1, @Nullable final Drawable p2, @Nullable final Drawable p3, @Nullable final Drawable p4);
    }
}
