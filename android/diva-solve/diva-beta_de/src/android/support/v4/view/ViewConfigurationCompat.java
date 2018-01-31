// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;
import android.os.Build$VERSION;

public class ViewConfigurationCompat
{
    static final ViewConfigurationVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (ViewConfigurationVersionImpl)new IcsViewConfigurationVersionImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (ViewConfigurationVersionImpl)new HoneycombViewConfigurationVersionImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 8) {
            IMPL = (ViewConfigurationVersionImpl)new FroyoViewConfigurationVersionImpl();
            return;
        }
        IMPL = (ViewConfigurationVersionImpl)new BaseViewConfigurationVersionImpl();
    }
    
    public static int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompat.IMPL.getScaledPagingTouchSlop(viewConfiguration);
    }
    
    public static boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompat.IMPL.hasPermanentMenuKey(viewConfiguration);
    }
    
    static class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl
    {
        @Override
        public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
            return viewConfiguration.getScaledTouchSlop();
        }
        
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return true;
        }
    }
    
    static class FroyoViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl
    {
        @Override
        public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewConfiguration);
        }
    }
    
    static class HoneycombViewConfigurationVersionImpl extends FroyoViewConfigurationVersionImpl
    {
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return false;
        }
    }
    
    static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl
    {
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewConfiguration);
        }
    }
    
    interface ViewConfigurationVersionImpl
    {
        int getScaledPagingTouchSlop(final ViewConfiguration p0);
        
        boolean hasPermanentMenuKey(final ViewConfiguration p0);
    }
}
