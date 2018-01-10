// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.ViewGroup$MarginLayoutParams;
import android.os.Build$VERSION;

public final class o
{
    static final a a;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static int a(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return o.a.a(viewGroup$MarginLayoutParams);
    }
    
    public static int b(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return o.a.b(viewGroup$MarginLayoutParams);
    }
    
    interface a
    {
        int a(final ViewGroup$MarginLayoutParams p0);
        
        int b(final ViewGroup$MarginLayoutParams p0);
    }
    
    static class b implements a
    {
        @Override
        public int a(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return viewGroup$MarginLayoutParams.leftMargin;
        }
        
        @Override
        public int b(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return viewGroup$MarginLayoutParams.rightMargin;
        }
    }
    
    static class c implements a
    {
        @Override
        public int a(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return p.a(viewGroup$MarginLayoutParams);
        }
        
        @Override
        public int b(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return p.b(viewGroup$MarginLayoutParams);
        }
    }
}
