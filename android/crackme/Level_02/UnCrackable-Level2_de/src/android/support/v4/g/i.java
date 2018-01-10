// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.KeyEvent;
import android.annotation.TargetApi;

@TargetApi(11)
class i
{
    public static boolean a(final KeyEvent keyEvent) {
        return keyEvent.isCtrlPressed();
    }
}
