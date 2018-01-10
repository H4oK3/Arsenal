// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.os.Bundle;
import java.util.List;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.annotation.TargetApi;

@TargetApi(16)
class f
{
    public static Object a(final a a) {
        return new AccessibilityNodeProvider() {
            public AccessibilityNodeInfo createAccessibilityNodeInfo(final int n) {
                return (AccessibilityNodeInfo)a.a(n);
            }
            
            public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(final String s, final int n) {
                return (List<AccessibilityNodeInfo>)a.a(s, n);
            }
            
            public boolean performAction(final int n, final int n2, final Bundle bundle) {
                return a.a(n, n2, bundle);
            }
        };
    }
    
    interface a
    {
        Object a(final int p0);
        
        List<Object> a(final String p0, final int p1);
        
        boolean a(final int p0, final int p1, final Bundle p2);
    }
}
