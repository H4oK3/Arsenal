// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View$OnApplyWindowInsetsListener;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.content.res.ColorStateList;
import android.view.WindowInsets;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(21)
class am
{
    public static Object a(final View view, Object o) {
        final WindowInsets windowInsets = (WindowInsets)o;
        final WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        if (onApplyWindowInsets != windowInsets) {
            o = new WindowInsets(onApplyWindowInsets);
        }
        return o;
    }
    
    public static String a(final View view) {
        return view.getTransitionName();
    }
    
    public static void a(final View view, final float elevation) {
        view.setElevation(elevation);
    }
    
    static void a(final View view, final ColorStateList backgroundTintList) {
        view.setBackgroundTintList(backgroundTintList);
        if (Build$VERSION.SDK_INT == 21) {
            final Drawable background = view.getBackground();
            boolean b;
            if (view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null) {
                b = true;
            }
            else {
                b = false;
            }
            if (background != null && b) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }
    
    static void a(final View view, final PorterDuff$Mode backgroundTintMode) {
        view.setBackgroundTintMode(backgroundTintMode);
        if (Build$VERSION.SDK_INT == 21) {
            final Drawable background = view.getBackground();
            boolean b;
            if (view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null) {
                b = true;
            }
            else {
                b = false;
            }
            if (background != null && b) {
                if (background.isStateful()) {
                    background.setState(view.getDrawableState());
                }
                view.setBackground(background);
            }
        }
    }
    
    public static void a(final View view, final a a) {
        if (a == null) {
            view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)null);
            return;
        }
        view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new View$OnApplyWindowInsetsListener() {
            public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
                return (WindowInsets)a.a(view, windowInsets);
            }
        });
    }
    
    public static void b(final View view) {
        view.requestApplyInsets();
    }
    
    static ColorStateList c(final View view) {
        return view.getBackgroundTintList();
    }
    
    static PorterDuff$Mode d(final View view) {
        return view.getBackgroundTintMode();
    }
    
    public static void e(final View view) {
        view.stopNestedScroll();
    }
    
    public interface a
    {
        Object a(final View p0, final Object p1);
    }
}
