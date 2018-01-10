// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.annotation.TargetApi;

@TargetApi(17)
class ak
{
    public static int a(final View view) {
        return view.getLayoutDirection();
    }
    
    public static int b(final View view) {
        return view.getWindowSystemUiVisibility();
    }
}
