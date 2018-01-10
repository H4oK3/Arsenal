// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
class e
{
    public static void a(final Context context, final Intent[] array, final Bundle bundle) {
        context.startActivities(array, bundle);
    }
}
