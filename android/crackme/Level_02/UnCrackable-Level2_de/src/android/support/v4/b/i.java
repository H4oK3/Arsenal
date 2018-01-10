// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import android.support.v4.a.d;
import android.os.Process;
import android.content.Context;

public final class i
{
    public static int a(final Context context, final String s) {
        return a(context, s, Process.myPid(), Process.myUid(), context.getPackageName());
    }
    
    public static int a(final Context context, String s, final int n, final int n2, final String s2) {
        if (context.checkPermission(s, n, n2) != -1) {
            final String a = d.a(s);
            if (a == null) {
                return 0;
            }
            if ((s = s2) == null) {
                final String[] packagesForUid = context.getPackageManager().getPackagesForUid(n2);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                s = packagesForUid[0];
            }
            if (d.a(context, a, s) != 0) {
                return -2;
            }
            return 0;
        }
        return -1;
    }
}
