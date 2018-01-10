// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b.a;

import android.content.res.Resources;
import android.annotation.TargetApi;

@TargetApi(13)
class c
{
    static int a(final Resources resources) {
        return resources.getConfiguration().screenHeightDp;
    }
    
    static int b(final Resources resources) {
        return resources.getConfiguration().screenWidthDp;
    }
    
    static int c(final Resources resources) {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
