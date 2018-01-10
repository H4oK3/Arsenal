// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.content.Context;
import android.os.Build$VERSION;

public final class d
{
    private static final b a;
    
    static {
        if (Build$VERSION.SDK_INT >= 23) {
            a = (b)new a();
            return;
        }
        a = new b();
    }
    
    public static int a(final Context context, final String s, final String s2) {
        return d.a.a(context, s, s2);
    }
    
    public static String a(final String s) {
        return d.a.a(s);
    }
    
    private static class a extends b
    {
        @Override
        public int a(final Context context, final String s, final String s2) {
            return e.a(context, s, s2);
        }
        
        @Override
        public String a(final String s) {
            return e.a(s);
        }
    }
    
    private static class b
    {
        public int a(final Context context, final String s, final String s2) {
            return 1;
        }
        
        public String a(final String s) {
            return null;
        }
    }
}
