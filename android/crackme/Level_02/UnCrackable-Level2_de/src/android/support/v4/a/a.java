// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.os.Build$VERSION;
import android.app.Activity;

public class a extends android.support.v4.b.a
{
    public static void a(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 16) {
            c.a(activity);
            return;
        }
        activity.finish();
    }
}
