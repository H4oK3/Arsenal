// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal;

import android.os.Build$VERSION;

public class VersionUtils
{
    public static boolean isAtLeastL() {
        return Build$VERSION.SDK_INT >= 21;
    }
}
