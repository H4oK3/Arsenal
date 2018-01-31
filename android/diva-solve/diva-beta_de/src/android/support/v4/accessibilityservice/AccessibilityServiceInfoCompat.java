// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.accessibilityservice;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.content.pm.ResolveInfo;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build$VERSION;

public class AccessibilityServiceInfoCompat
{
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 18) {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoJellyBeanMr2();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoIcsImpl();
            return;
        }
        IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoStubImpl();
    }
    
    public static String capabilityToString(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 1: {
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            }
            case 2: {
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            }
            case 4: {
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 8: {
                return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
            }
        }
    }
    
    public static String feedbackTypeToString(int i) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i > 0) {
            final int n = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~n;
            if (sb.length() > 1) {
                sb.append(", ");
            }
            switch (n) {
                default: {
                    continue;
                }
                case 1: {
                    sb.append("FEEDBACK_SPOKEN");
                    continue;
                }
                case 4: {
                    sb.append("FEEDBACK_AUDIBLE");
                    continue;
                }
                case 2: {
                    sb.append("FEEDBACK_HAPTIC");
                    continue;
                }
                case 16: {
                    sb.append("FEEDBACK_GENERIC");
                    continue;
                }
                case 8: {
                    sb.append("FEEDBACK_VISUAL");
                    continue;
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static String flagToString(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1: {
                return "DEFAULT";
            }
            case 2: {
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            }
            case 4: {
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            case 8: {
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 16: {
                return "FLAG_REPORT_VIEW_IDS";
            }
            case 32: {
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            }
        }
    }
    
    public static boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getCanRetrieveWindowContent(accessibilityServiceInfo);
    }
    
    public static int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getCapabilities(accessibilityServiceInfo);
    }
    
    public static String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getDescription(accessibilityServiceInfo);
    }
    
    public static String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getId(accessibilityServiceInfo);
    }
    
    public static ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getResolveInfo(accessibilityServiceInfo);
    }
    
    public static String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getSettingsActivityName(accessibilityServiceInfo);
    }
    
    static class AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoStubImpl
    {
        @Override
        public boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(accessibilityServiceInfo);
        }
        
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            if (this.getCanRetrieveWindowContent(accessibilityServiceInfo)) {
                return 1;
            }
            return 0;
        }
        
        @Override
        public String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getDescription(accessibilityServiceInfo);
        }
        
        @Override
        public String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getId(accessibilityServiceInfo);
        }
        
        @Override
        public ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getResolveInfo(accessibilityServiceInfo);
        }
        
        @Override
        public String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(accessibilityServiceInfo);
        }
    }
    
    static class AccessibilityServiceInfoJellyBeanMr2 extends AccessibilityServiceInfoIcsImpl
    {
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatJellyBeanMr2.getCapabilities(accessibilityServiceInfo);
        }
    }
    
    static class AccessibilityServiceInfoStubImpl implements AccessibilityServiceInfoVersionImpl
    {
        @Override
        public boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return false;
        }
        
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return 0;
        }
        
        @Override
        public String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
    }
    
    interface AccessibilityServiceInfoVersionImpl
    {
        boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo p0);
        
        int getCapabilities(final AccessibilityServiceInfo p0);
        
        String getDescription(final AccessibilityServiceInfo p0);
        
        String getId(final AccessibilityServiceInfo p0);
        
        ResolveInfo getResolveInfo(final AccessibilityServiceInfo p0);
        
        String getSettingsActivityName(final AccessibilityServiceInfo p0);
    }
}
