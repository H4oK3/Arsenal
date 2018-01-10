// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.util.TypedValue;

public class a
{
    private static final Object a;
    private static TypedValue b;
    
    static {
        a = new Object();
    }
    
    public static final Drawable a(final Context context, int resourceId) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            return android.support.v4.b.b.a(context, resourceId);
        }
        if (sdk_INT >= 16) {
            return context.getResources().getDrawable(resourceId);
        }
        synchronized (android.support.v4.b.a.a) {
            if (android.support.v4.b.a.b == null) {
                android.support.v4.b.a.b = new TypedValue();
            }
            context.getResources().getValue(resourceId, android.support.v4.b.a.b, true);
            resourceId = android.support.v4.b.a.b.resourceId;
            // monitorexit(a.a)
            return context.getResources().getDrawable(resourceId);
        }
    }
    
    public static boolean a(final Context context, final Intent[] array, final Bundle bundle) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            e.a(context, array, bundle);
            return true;
        }
        if (sdk_INT >= 11) {
            d.a(context, array);
            return true;
        }
        return false;
    }
    
    public static final ColorStateList b(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            return c.a(context, n);
        }
        return context.getResources().getColorStateList(n);
    }
}
