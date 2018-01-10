// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import android.content.Intent;
import android.content.ComponentName;
import android.annotation.TargetApi;

@TargetApi(11)
class g
{
    public static Intent a(final ComponentName componentName) {
        return Intent.makeMainActivity(componentName);
    }
}
