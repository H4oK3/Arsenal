// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.net.Uri;
import android.app.Activity;
import android.support.annotation.RequiresApi;

@RequiresApi(22)
class ActivityCompatApi22
{
    public static Uri getReferrer(final Activity activity) {
        return activity.getReferrer();
    }
}
