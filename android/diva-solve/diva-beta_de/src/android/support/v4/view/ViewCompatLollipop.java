// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View$OnApplyWindowInsetsListener;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.WindowInsets;
import android.view.View;

class ViewCompatLollipop
{
    public static WindowInsetsCompat dispatchApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            final WindowInsets unwrap = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            final WindowInsets dispatchApplyWindowInsets = view.dispatchApplyWindowInsets(unwrap);
            windowInsetsCompat2 = windowInsetsCompat;
            if (dispatchApplyWindowInsets != unwrap) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21(dispatchApplyWindowInsets);
            }
        }
        return windowInsetsCompat2;
    }
    
    public static boolean dispatchNestedFling(final View view, final float n, final float n2, final boolean b) {
        return view.dispatchNestedFling(n, n2, b);
    }
    
    public static boolean dispatchNestedPreFling(final View view, final float n, final float n2) {
        return view.dispatchNestedPreFling(n, n2);
    }
    
    public static boolean dispatchNestedPreScroll(final View view, final int n, final int n2, final int[] array, final int[] array2) {
        return view.dispatchNestedPreScroll(n, n2, array, array2);
    }
    
    public static boolean dispatchNestedScroll(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        return view.dispatchNestedScroll(n, n2, n3, n4, array);
    }
    
    static ColorStateList getBackgroundTintList(final View view) {
        return view.getBackgroundTintList();
    }
    
    static PorterDuff$Mode getBackgroundTintMode(final View view) {
        return view.getBackgroundTintMode();
    }
    
    public static float getElevation(final View view) {
        return view.getElevation();
    }
    
    public static String getTransitionName(final View view) {
        return view.getTransitionName();
    }
    
    public static float getTranslationZ(final View view) {
        return view.getTranslationZ();
    }
    
    public static float getZ(final View view) {
        return view.getZ();
    }
    
    public static boolean hasNestedScrollingParent(final View view) {
        return view.hasNestedScrollingParent();
    }
    
    public static boolean isImportantForAccessibility(final View view) {
        return view.isImportantForAccessibility();
    }
    
    public static boolean isNestedScrollingEnabled(final View view) {
        return view.isNestedScrollingEnabled();
    }
    
    public static WindowInsetsCompat onApplyWindowInsets(final View view, final WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        if (windowInsetsCompat instanceof WindowInsetsCompatApi21) {
            final WindowInsets unwrap = ((WindowInsetsCompatApi21)windowInsetsCompat).unwrap();
            final WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(unwrap);
            windowInsetsCompat2 = windowInsetsCompat;
            if (onApplyWindowInsets != unwrap) {
                windowInsetsCompat2 = new WindowInsetsCompatApi21(onApplyWindowInsets);
            }
        }
        return windowInsetsCompat2;
    }
    
    public static void requestApplyInsets(final View view) {
        view.requestApplyInsets();
    }
    
    static void setBackgroundTintList(final View view, final ColorStateList backgroundTintList) {
        view.setBackgroundTintList(backgroundTintList);
    }
    
    static void setBackgroundTintMode(final View view, final PorterDuff$Mode backgroundTintMode) {
        view.setBackgroundTintMode(backgroundTintMode);
    }
    
    public static void setElevation(final View view, final float elevation) {
        view.setElevation(elevation);
    }
    
    public static void setNestedScrollingEnabled(final View view, final boolean nestedScrollingEnabled) {
        view.setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    
    public static void setOnApplyWindowInsetsListener(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        view.setOnApplyWindowInsetsListener((View$OnApplyWindowInsetsListener)new View$OnApplyWindowInsetsListener() {
            public WindowInsets onApplyWindowInsets(final View view, final WindowInsets windowInsets) {
                return ((WindowInsetsCompatApi21)onApplyWindowInsetsListener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
            }
        });
    }
    
    public static void setTransitionName(final View view, final String transitionName) {
        view.setTransitionName(transitionName);
    }
    
    public static void setTranslationZ(final View view, final float translationZ) {
        view.setTranslationZ(translationZ);
    }
    
    public static boolean startNestedScroll(final View view, final int n) {
        return view.startNestedScroll(n);
    }
    
    public static void stopNestedScroll(final View view) {
        view.stopNestedScroll();
    }
}
