// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b.a;

import android.content.res.Resources;
import android.os.Build$VERSION;

public final class a
{
    private static final a a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 17) {
            a = (a)new d();
            return;
        }
        if (sdk_INT >= 13) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static int a(final Resources resources) {
        return android.support.v4.b.a.a.a.a(resources);
    }
    
    public static int b(final Resources resources) {
        return android.support.v4.b.a.a.a.b(resources);
    }
    
    public static int c(final Resources resources) {
        return android.support.v4.b.a.a.a.c(resources);
    }
    
    private interface a
    {
        int a(final Resources p0);
        
        int b(final Resources p0);
        
        int c(final Resources p0);
    }
    
    private static class b implements a
    {
        @Override
        public int a(final Resources resources) {
            return android.support.v4.b.a.b.a(resources);
        }
        
        @Override
        public int b(final Resources resources) {
            return android.support.v4.b.a.b.b(resources);
        }
        
        @Override
        public int c(final Resources resources) {
            return android.support.v4.b.a.b.c(resources);
        }
    }
    
    private static class c extends b
    {
        @Override
        public int a(final Resources resources) {
            return android.support.v4.b.a.c.a(resources);
        }
        
        @Override
        public int b(final Resources resources) {
            return android.support.v4.b.a.c.b(resources);
        }
        
        @Override
        public int c(final Resources resources) {
            return android.support.v4.b.a.c.c(resources);
        }
    }
    
    private static class d extends c
    {
    }
}
