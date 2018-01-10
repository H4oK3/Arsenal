// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import android.content.Intent;
import android.content.ComponentName;
import android.os.Build$VERSION;

public final class f
{
    private static final a a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 15) {
            a = (a)new d();
            return;
        }
        if (sdk_INT >= 11) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static Intent a(final ComponentName componentName) {
        return f.a.a(componentName);
    }
    
    interface a
    {
        Intent a(final ComponentName p0);
    }
    
    static class b implements a
    {
        @Override
        public Intent a(final ComponentName component) {
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(component);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
    }
    
    static class c extends b
    {
        @Override
        public Intent a(final ComponentName componentName) {
            return g.a(componentName);
        }
    }
    
    static class d extends c
    {
    }
}
