// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.Collections;
import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Build$VERSION;

public class AccessibilityManagerCompat
{
    private static final AccessibilityManagerVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityManagerVersionImpl)new AccessibilityManagerIcsImpl();
            return;
        }
        IMPL = (AccessibilityManagerVersionImpl)new AccessibilityManagerStubImpl();
    }
    
    public static boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompat.IMPL.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat);
    }
    
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return AccessibilityManagerCompat.IMPL.getEnabledAccessibilityServiceList(accessibilityManager, n);
    }
    
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.getInstalledAccessibilityServiceList(accessibilityManager);
    }
    
    public static boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.isTouchExplorationEnabled(accessibilityManager);
    }
    
    public static boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return AccessibilityManagerCompat.IMPL.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat);
    }
    
    static class AccessibilityManagerIcsImpl extends AccessibilityManagerStubImpl
    {
        @Override
        public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
        }
        
        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
            return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilityManager, n);
        }
        
        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilityManager);
        }
        
        @Override
        public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilityManager);
        }
        
        @Override
        public Object newAccessiblityStateChangeListener(final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener((AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge)new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge() {
                @Override
                public void onAccessibilityStateChanged(final boolean b) {
                    accessibilityStateChangeListenerCompat.onAccessibilityStateChanged(b);
                }
            });
        }
        
        @Override
        public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
        }
    }
    
    static class AccessibilityManagerStubImpl implements AccessibilityManagerVersionImpl
    {
        @Override
        public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return false;
        }
        
        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
            return Collections.emptyList();
        }
        
        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
            return Collections.emptyList();
        }
        
        @Override
        public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
            return false;
        }
        
        @Override
        public Object newAccessiblityStateChangeListener(final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return null;
        }
        
        @Override
        public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return false;
        }
    }
    
    interface AccessibilityManagerVersionImpl
    {
        boolean addAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListenerCompat p1);
        
        List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager p0, final int p1);
        
        List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager p0);
        
        boolean isTouchExplorationEnabled(final AccessibilityManager p0);
        
        Object newAccessiblityStateChangeListener(final AccessibilityStateChangeListenerCompat p0);
        
        boolean removeAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListenerCompat p1);
    }
    
    public abstract static class AccessibilityStateChangeListenerCompat
    {
        final Object mListener;
        
        public AccessibilityStateChangeListenerCompat() {
            this.mListener = AccessibilityManagerCompat.IMPL.newAccessiblityStateChangeListener(this);
        }
        
        public abstract void onAccessibilityStateChanged(final boolean p0);
    }
}
