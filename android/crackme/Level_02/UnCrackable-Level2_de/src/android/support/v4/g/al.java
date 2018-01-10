// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.annotation.TargetApi;

@TargetApi(19)
class al
{
    public static boolean a(final View view) {
        return view.isLaidOut();
    }
    
    public static boolean b(final View view) {
        return view.isAttachedToWindow();
    }
}
