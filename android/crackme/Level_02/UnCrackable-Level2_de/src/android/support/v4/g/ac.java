// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.VelocityTracker;
import android.os.Build$VERSION;

public final class ac
{
    static final c a;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            a = (c)new b();
            return;
        }
        a = (c)new a();
    }
    
    public static float a(final VelocityTracker velocityTracker, final int n) {
        return ac.a.a(velocityTracker, n);
    }
    
    static class a implements c
    {
        @Override
        public float a(final VelocityTracker velocityTracker, final int n) {
            return velocityTracker.getYVelocity();
        }
    }
    
    static class b implements c
    {
        @Override
        public float a(final VelocityTracker velocityTracker, final int n) {
            return ad.a(velocityTracker, n);
        }
    }
    
    interface c
    {
        float a(final VelocityTracker p0, final int p1);
    }
}
