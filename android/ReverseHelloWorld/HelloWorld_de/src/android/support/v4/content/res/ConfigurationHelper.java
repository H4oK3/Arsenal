// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.support.annotation.RequiresApi;
import android.support.annotation.NonNull;
import android.content.res.Resources;
import android.os.Build$VERSION;

public final class ConfigurationHelper
{
    private static final ConfigurationHelperBaseImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = (ConfigurationHelperBaseImpl)new ConfigurationHelperApi17Impl();
            return;
        }
        IMPL = new ConfigurationHelperBaseImpl();
    }
    
    public static int getDensityDpi(@NonNull final Resources resources) {
        return ConfigurationHelper.IMPL.getDensityDpi(resources);
    }
    
    @Deprecated
    public static int getScreenHeightDp(@NonNull final Resources resources) {
        return resources.getConfiguration().screenHeightDp;
    }
    
    @Deprecated
    public static int getScreenWidthDp(@NonNull final Resources resources) {
        return resources.getConfiguration().screenWidthDp;
    }
    
    @Deprecated
    public static int getSmallestScreenWidthDp(@NonNull final Resources resources) {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
    
    @RequiresApi(17)
    private static class ConfigurationHelperApi17Impl extends ConfigurationHelperBaseImpl
    {
        @Override
        public int getDensityDpi(@NonNull final Resources resources) {
            return ConfigurationHelperJellybeanMr1.getDensityDpi(resources);
        }
    }
    
    private static class ConfigurationHelperBaseImpl
    {
        public int getDensityDpi(@NonNull final Resources resources) {
            return resources.getDisplayMetrics().densityDpi;
        }
    }
}
