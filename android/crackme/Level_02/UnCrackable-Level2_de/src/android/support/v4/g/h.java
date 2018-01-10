// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.KeyEvent;
import android.os.Build$VERSION;

public final class h
{
    static final c a;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            a = (c)new b();
            return;
        }
        a = (c)new a();
    }
    
    public static boolean a(final KeyEvent keyEvent) {
        return h.a.a(keyEvent);
    }
    
    static class a implements c
    {
        @Override
        public boolean a(final KeyEvent keyEvent) {
            return false;
        }
    }
    
    static class b extends a
    {
        @Override
        public boolean a(final KeyEvent keyEvent) {
            return i.a(keyEvent);
        }
    }
    
    interface c
    {
        boolean a(final KeyEvent p0);
    }
}
