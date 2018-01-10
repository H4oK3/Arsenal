// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.EdgeEffect;
import android.annotation.TargetApi;

@TargetApi(21)
class j
{
    public static boolean a(final Object o, final float n, final float n2) {
        ((EdgeEffect)o).onPull(n, n2);
        return true;
    }
}
