// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.a;

import android.content.Context;

public class b
{
    public static boolean a(final Context context) {
        return (context.getApplicationContext().getApplicationInfo().flags & 0x2) != 0x0;
    }
}
