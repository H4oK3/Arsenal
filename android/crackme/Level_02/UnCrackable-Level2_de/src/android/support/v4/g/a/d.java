// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.view.accessibility.AccessibilityNodeInfo;
import android.annotation.TargetApi;

@TargetApi(18)
class d
{
    public static String a(final Object o) {
        return ((AccessibilityNodeInfo)o).getViewIdResourceName();
    }
}
