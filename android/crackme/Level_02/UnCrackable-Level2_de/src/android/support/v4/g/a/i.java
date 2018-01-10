// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.view.accessibility.AccessibilityRecord;
import android.annotation.TargetApi;

@TargetApi(14)
class i
{
    public static void a(final Object o, final int scrollX) {
        ((AccessibilityRecord)o).setScrollX(scrollX);
    }
    
    public static void a(final Object o, final boolean scrollable) {
        ((AccessibilityRecord)o).setScrollable(scrollable);
    }
    
    public static void b(final Object o, final int scrollY) {
        ((AccessibilityRecord)o).setScrollY(scrollY);
    }
}
