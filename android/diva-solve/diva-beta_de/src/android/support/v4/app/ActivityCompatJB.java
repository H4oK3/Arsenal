// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;

class ActivityCompatJB
{
    public static void finishAffinity(final Activity activity) {
        activity.finishAffinity();
    }
    
    public static void startActivity(final Context context, final Intent intent, final Bundle bundle) {
        context.startActivity(intent, bundle);
    }
    
    public static void startActivityForResult(final Activity activity, final Intent intent, final int n, final Bundle bundle) {
        activity.startActivityForResult(intent, n, bundle);
    }
}
