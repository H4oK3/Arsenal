// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.MotionEvent;
import android.annotation.TargetApi;

@TargetApi(12)
class u
{
    static float a(final MotionEvent motionEvent, final int n) {
        return motionEvent.getAxisValue(n);
    }
}
