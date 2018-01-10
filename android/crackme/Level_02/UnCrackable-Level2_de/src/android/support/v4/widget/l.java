// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Build$VERSION;
import android.widget.ListView;

public final class l
{
    public static void a(final ListView listView, final int n) {
        if (Build$VERSION.SDK_INT >= 19) {
            n.a(listView, n);
            return;
        }
        m.a(listView, n);
    }
}
