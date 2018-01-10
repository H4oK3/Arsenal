// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.app.Activity;
import android.annotation.TargetApi;

@TargetApi(16)
class aa
{
    public static Intent a(final Activity activity) {
        return activity.getParentActivityIntent();
    }
    
    public static String a(final ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }
    
    public static boolean a(final Activity activity, final Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }
    
    public static void b(final Activity activity, final Intent intent) {
        activity.navigateUpTo(intent);
    }
}
