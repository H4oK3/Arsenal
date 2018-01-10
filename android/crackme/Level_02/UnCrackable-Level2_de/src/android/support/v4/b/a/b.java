// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b.a;

import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.annotation.TargetApi;

@TargetApi(9)
class b
{
    static int a(final Resources resources) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int)(displayMetrics.heightPixels / displayMetrics.density);
    }
    
    static int b(final Resources resources) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int)(displayMetrics.widthPixels / displayMetrics.density);
    }
    
    static int c(final Resources resources) {
        return Math.min(b(resources), a(resources));
    }
}
