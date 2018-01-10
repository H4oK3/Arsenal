// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;

@TargetApi(14)
class c
{
    public static int a(final Object o) {
        return ((AccessibilityNodeInfo)o).getActions();
    }
    
    public static void a(final Object o, final int n) {
        ((AccessibilityNodeInfo)o).addAction(n);
    }
    
    public static void a(final Object o, final Rect rect) {
        ((AccessibilityNodeInfo)o).getBoundsInParent(rect);
    }
    
    public static void a(final Object o, final CharSequence className) {
        ((AccessibilityNodeInfo)o).setClassName(className);
    }
    
    public static void a(final Object o, final boolean scrollable) {
        ((AccessibilityNodeInfo)o).setScrollable(scrollable);
    }
    
    public static CharSequence b(final Object o) {
        return ((AccessibilityNodeInfo)o).getClassName();
    }
    
    public static void b(final Object o, final Rect rect) {
        ((AccessibilityNodeInfo)o).getBoundsInScreen(rect);
    }
    
    public static CharSequence c(final Object o) {
        return ((AccessibilityNodeInfo)o).getContentDescription();
    }
    
    public static CharSequence d(final Object o) {
        return ((AccessibilityNodeInfo)o).getPackageName();
    }
    
    public static CharSequence e(final Object o) {
        return ((AccessibilityNodeInfo)o).getText();
    }
    
    public static boolean f(final Object o) {
        return ((AccessibilityNodeInfo)o).isCheckable();
    }
    
    public static boolean g(final Object o) {
        return ((AccessibilityNodeInfo)o).isChecked();
    }
    
    public static boolean h(final Object o) {
        return ((AccessibilityNodeInfo)o).isClickable();
    }
    
    public static boolean i(final Object o) {
        return ((AccessibilityNodeInfo)o).isEnabled();
    }
    
    public static boolean j(final Object o) {
        return ((AccessibilityNodeInfo)o).isFocusable();
    }
    
    public static boolean k(final Object o) {
        return ((AccessibilityNodeInfo)o).isFocused();
    }
    
    public static boolean l(final Object o) {
        return ((AccessibilityNodeInfo)o).isLongClickable();
    }
    
    public static boolean m(final Object o) {
        return ((AccessibilityNodeInfo)o).isPassword();
    }
    
    public static boolean n(final Object o) {
        return ((AccessibilityNodeInfo)o).isScrollable();
    }
    
    public static boolean o(final Object o) {
        return ((AccessibilityNodeInfo)o).isSelected();
    }
}
