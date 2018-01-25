// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.ActivityManager;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class ActivityManagerCompatKitKat
{
    public static boolean isLowRamDevice(final ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }
}
