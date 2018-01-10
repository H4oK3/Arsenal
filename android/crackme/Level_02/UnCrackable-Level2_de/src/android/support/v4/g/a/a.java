// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g.a;

import android.view.accessibility.AccessibilityEvent;
import android.os.Build$VERSION;

public final class a
{
    private static final e a;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            a = (e)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            a = (e)new b();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            a = (e)new a();
            return;
        }
        a = (e)new d();
    }
    
    public static h a(final AccessibilityEvent accessibilityEvent) {
        return new h(accessibilityEvent);
    }
    
    static class a extends d
    {
    }
    
    static class b extends a
    {
    }
    
    static class c extends b
    {
    }
    
    static class d implements e
    {
    }
    
    interface e
    {
    }
}
