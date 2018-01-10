// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.VelocityTracker;
import android.annotation.TargetApi;

@TargetApi(11)
class ad
{
    public static float a(final VelocityTracker velocityTracker, final int n) {
        return velocityTracker.getYVelocity(n);
    }
}
