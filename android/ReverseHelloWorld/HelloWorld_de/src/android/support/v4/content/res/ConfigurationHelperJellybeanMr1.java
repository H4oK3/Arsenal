// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.support.annotation.NonNull;
import android.content.res.Resources;
import android.support.annotation.RequiresApi;

@RequiresApi(17)
class ConfigurationHelperJellybeanMr1
{
    static int getDensityDpi(@NonNull final Resources resources) {
        return resources.getConfiguration().densityDpi;
    }
}
