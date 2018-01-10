// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.OverScroller;
import android.annotation.TargetApi;

@TargetApi(14)
class u
{
    public static float a(final Object o) {
        return ((OverScroller)o).getCurrVelocity();
    }
}
