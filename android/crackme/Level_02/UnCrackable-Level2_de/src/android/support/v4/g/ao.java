// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.ViewConfiguration;
import android.os.Build$VERSION;

public final class ao
{
    static final d a;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            a = (d)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            a = (d)new b();
            return;
        }
        a = (d)new a();
    }
    
    public static boolean a(final ViewConfiguration viewConfiguration) {
        return ao.a.a(viewConfiguration);
    }
    
    static class a implements d
    {
        @Override
        public boolean a(final ViewConfiguration viewConfiguration) {
            return true;
        }
    }
    
    static class b extends a
    {
        @Override
        public boolean a(final ViewConfiguration viewConfiguration) {
            return false;
        }
    }
    
    static class c extends b
    {
        @Override
        public boolean a(final ViewConfiguration viewConfiguration) {
            return ap.a(viewConfiguration);
        }
    }
    
    interface d
    {
        boolean a(final ViewConfiguration p0);
    }
}
