// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.c.a.a;
import android.support.v4.c.a.h;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff$Mode;
import android.os.Build$VERSION;
import android.graphics.Rect;

public class ad
{
    public static final Rect a;
    private static Class<?> b;
    
    static {
        a = new Rect();
        if (Build$VERSION.SDK_INT < 18) {
            return;
        }
        try {
            ad.b = Class.forName("android.graphics.Insets");
        }
        catch (ClassNotFoundException ex) {}
    }
    
    static PorterDuff$Mode a(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
            case 16: {
                if (Build$VERSION.SDK_INT >= 11) {
                    return PorterDuff$Mode.valueOf("ADD");
                }
                break;
            }
        }
        return porterDuff$Mode;
    }
    
    static void a(final Drawable drawable) {
        if (Build$VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            c(drawable);
        }
    }
    
    public static boolean b(final Drawable drawable) {
        if (Build$VERSION.SDK_INT < 15 && drawable instanceof InsetDrawable) {
            return false;
        }
        if (Build$VERSION.SDK_INT < 15 && drawable instanceof GradientDrawable) {
            return false;
        }
        if (Build$VERSION.SDK_INT < 17 && drawable instanceof LayerDrawable) {
            return false;
        }
        if (drawable instanceof DrawableContainer) {
            final Drawable$ConstantState constantState = drawable.getConstantState();
            if (constantState instanceof DrawableContainer$DrawableContainerState) {
                final Drawable[] children = ((DrawableContainer$DrawableContainerState)constantState).getChildren();
                for (int length = children.length, i = 0; i < length; ++i) {
                    if (!b(children[i])) {
                        return false;
                    }
                }
            }
        }
        else {
            if (drawable instanceof h) {
                return b(((h)drawable).a());
            }
            if (drawable instanceof a) {
                return b(((a)drawable).a());
            }
            if (drawable instanceof ScaleDrawable) {
                return b(((ScaleDrawable)drawable).getDrawable());
            }
        }
        return true;
    }
    
    private static void c(final Drawable drawable) {
        final int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(aq.e);
        }
        else {
            drawable.setState(aq.h);
        }
        drawable.setState(state);
    }
}
