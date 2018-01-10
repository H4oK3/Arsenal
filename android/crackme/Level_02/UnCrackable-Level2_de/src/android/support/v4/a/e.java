// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.app.AppOpsManager;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(23)
class e
{
    public static int a(final Context context, final String s, final String s2) {
        return ((AppOpsManager)context.getSystemService((Class)AppOpsManager.class)).noteProxyOp(s, s2);
    }
    
    public static String a(final String s) {
        return AppOpsManager.permissionToOp(s);
    }
}
