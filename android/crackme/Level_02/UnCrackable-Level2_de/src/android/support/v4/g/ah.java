// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(14)
class ah
{
    public static void a(final View view, final Object o) {
        view.setAccessibilityDelegate((View$AccessibilityDelegate)o);
    }
    
    public static boolean a(final View view, final int n) {
        return view.canScrollVertically(n);
    }
}
