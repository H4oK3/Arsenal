// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.view.accessibility.AccessibilityRecord;
import android.annotation.TargetApi;

@TargetApi(15)
class j
{
    public static void a(final Object o, final int maxScrollX) {
        ((AccessibilityRecord)o).setMaxScrollX(maxScrollX);
    }
    
    public static void b(final Object o, final int maxScrollY) {
        ((AccessibilityRecord)o).setMaxScrollY(maxScrollY);
    }
}
