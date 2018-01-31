// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.routing;

import android.view.Display;
import android.media.MediaRouter$RouteInfo;
import android.media.MediaRouter;
import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.Context;
import java.lang.reflect.Method;
import android.os.Handler;
import android.hardware.display.DisplayManager;

class MediaRouterJellybeanMr1 extends MediaRouterJellybean
{
    private static final String TAG = "MediaRouterJellybeanMr1";
    
    public static Object createCallback(final Callback callback) {
        return new CallbackProxy(callback);
    }
    
    public static final class ActiveScanWorkaround implements Runnable
    {
        private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
        private boolean mActivelyScanningWifiDisplays;
        private final DisplayManager mDisplayManager;
        private final Handler mHandler;
        private Method mScanWifiDisplaysMethod;
        
        public ActiveScanWorkaround(final Context context, final Handler mHandler) {
            if (Build$VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            this.mDisplayManager = (DisplayManager)context.getSystemService("display");
            this.mHandler = mHandler;
            try {
                this.mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays", (Class<?>[])new Class[0]);
            }
            catch (NoSuchMethodException ex) {}
        }
        
        @Override
        public void run() {
            if (!this.mActivelyScanningWifiDisplays) {
                return;
            }
            while (true) {
                try {
                    this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager, new Object[0]);
                    this.mHandler.postDelayed((Runnable)this, 15000L);
                }
                catch (IllegalAccessException ex) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", (Throwable)ex);
                    continue;
                }
                catch (InvocationTargetException ex2) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", (Throwable)ex2);
                    continue;
                }
                break;
            }
        }
        
        public void setActiveScanRouteTypes(final int n) {
            if ((n & 0x2) != 0x0) {
                if (!this.mActivelyScanningWifiDisplays) {
                    if (this.mScanWifiDisplaysMethod == null) {
                        Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
                        return;
                    }
                    this.mActivelyScanningWifiDisplays = true;
                    this.mHandler.post((Runnable)this);
                }
            }
            else if (this.mActivelyScanningWifiDisplays) {
                this.mActivelyScanningWifiDisplays = false;
                this.mHandler.removeCallbacks((Runnable)this);
            }
        }
    }
    
    public interface Callback extends MediaRouterJellybean.Callback
    {
        void onRoutePresentationDisplayChanged(final Object p0);
    }
    
    static class CallbackProxy<T extends MediaRouterJellybeanMr1.Callback> extends MediaRouterJellybean.CallbackProxy<T>
    {
        public CallbackProxy(final T t) {
            super(t);
        }
        
        public void onRoutePresentationDisplayChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
            ((MediaRouterJellybeanMr1.Callback)this.mCallback).onRoutePresentationDisplayChanged(mediaRouter$RouteInfo);
        }
    }
    
    public static final class IsConnectingWorkaround
    {
        private Method mGetStatusCodeMethod;
        private int mStatusConnecting;
        
        public IsConnectingWorkaround() {
            if (Build$VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            try {
                this.mStatusConnecting = MediaRouter$RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
                this.mGetStatusCodeMethod = MediaRouter$RouteInfo.class.getMethod("getStatusCode", (Class<?>[])new Class[0]);
            }
            catch (IllegalAccessException ex) {}
            catch (NoSuchMethodException ex2) {}
            catch (NoSuchFieldException ex3) {}
        }
        
        public boolean isConnecting(final Object o) {
            final MediaRouter$RouteInfo mediaRouter$RouteInfo = (MediaRouter$RouteInfo)o;
            if (this.mGetStatusCodeMethod == null) {
                goto Label_0046;
            }
            try {
                return (int)this.mGetStatusCodeMethod.invoke(mediaRouter$RouteInfo, new Object[0]) == this.mStatusConnecting;
            }
            catch (InvocationTargetException ex) {}
            catch (IllegalAccessException ex2) {
                goto Label_0046;
            }
        }
    }
    
    public static final class RouteInfo
    {
        public static Display getPresentationDisplay(final Object o) {
            return ((MediaRouter$RouteInfo)o).getPresentationDisplay();
        }
        
        public static boolean isEnabled(final Object o) {
            return ((MediaRouter$RouteInfo)o).isEnabled();
        }
    }
}
