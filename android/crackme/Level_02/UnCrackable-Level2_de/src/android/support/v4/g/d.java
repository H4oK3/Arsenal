// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.View$AccessibilityDelegate;
import android.annotation.TargetApi;

@TargetApi(16)
class d
{
    public static Object a(final a a) {
        return new View$AccessibilityDelegate() {
            public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                return a.a(view, accessibilityEvent);
            }
            
            public AccessibilityNodeProvider getAccessibilityNodeProvider(final View view) {
                return (AccessibilityNodeProvider)a.a(view);
            }
            
            public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                a.b(view, accessibilityEvent);
            }
            
            public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfo accessibilityNodeInfo) {
                a.a(view, accessibilityNodeInfo);
            }
            
            public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                a.c(view, accessibilityEvent);
            }
            
            public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                return a.a(viewGroup, view, accessibilityEvent);
            }
            
            public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
                return a.a(view, n, bundle);
            }
            
            public void sendAccessibilityEvent(final View view, final int n) {
                a.a(view, n);
            }
            
            public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
                a.d(view, accessibilityEvent);
            }
        };
    }
    
    public static Object a(final Object o, final View view) {
        return ((View$AccessibilityDelegate)o).getAccessibilityNodeProvider(view);
    }
    
    public static boolean a(final Object o, final View view, final int n, final Bundle bundle) {
        return ((View$AccessibilityDelegate)o).performAccessibilityAction(view, n, bundle);
    }
    
    public interface a
    {
        Object a(final View p0);
        
        void a(final View p0, final int p1);
        
        void a(final View p0, final Object p1);
        
        boolean a(final View p0, final int p1, final Bundle p2);
        
        boolean a(final View p0, final AccessibilityEvent p1);
        
        boolean a(final ViewGroup p0, final View p1, final AccessibilityEvent p2);
        
        void b(final View p0, final AccessibilityEvent p1);
        
        void c(final View p0, final AccessibilityEvent p1);
        
        void d(final View p0, final AccessibilityEvent p1);
    }
}
