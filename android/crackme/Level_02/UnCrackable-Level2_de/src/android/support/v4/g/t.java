// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.MotionEvent;
import android.os.Build$VERSION;

public final class t
{
    static final d a;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            a = (d)new c();
            return;
        }
        if (Build$VERSION.SDK_INT >= 12) {
            a = (d)new b();
            return;
        }
        a = (d)new a();
    }
    
    public static float a(final MotionEvent motionEvent, final int n) {
        return t.a.a(motionEvent, n);
    }
    
    public static int a(final MotionEvent motionEvent) {
        return motionEvent.getAction() & 0xFF;
    }
    
    public static int b(final MotionEvent motionEvent) {
        return (motionEvent.getAction() & 0xFF00) >> 8;
    }
    
    static class a implements d
    {
        @Override
        public float a(final MotionEvent motionEvent, final int n) {
            return 0.0f;
        }
    }
    
    static class b extends a
    {
        @Override
        public float a(final MotionEvent motionEvent, final int n) {
            return u.a(motionEvent, n);
        }
    }
    
    private static class c extends b
    {
    }
    
    interface d
    {
        float a(final MotionEvent p0, final int p1);
    }
}
