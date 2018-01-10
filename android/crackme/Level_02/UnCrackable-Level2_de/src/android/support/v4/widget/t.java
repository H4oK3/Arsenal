// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import android.content.Context;
import android.widget.OverScroller;

public final class t
{
    OverScroller a;
    private final boolean b;
    
    t(final boolean b, final Context context, final Interpolator interpolator) {
        this.b = b;
        OverScroller a;
        if (interpolator != null) {
            a = new OverScroller(context, interpolator);
        }
        else {
            a = new OverScroller(context);
        }
        this.a = a;
    }
    
    public static t a(final Context context) {
        return a(context, null);
    }
    
    public static t a(final Context context, final Interpolator interpolator) {
        return new t(Build$VERSION.SDK_INT >= 14, context, interpolator);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.a.startScroll(n, n2, n3, n4);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.a.fling(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.a.fling(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public boolean a() {
        return this.a.isFinished();
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.a.springBack(n, n2, n3, n4, n5, n6);
    }
    
    public int b() {
        return this.a.getCurrX();
    }
    
    public int c() {
        return this.a.getCurrY();
    }
    
    public int d() {
        return this.a.getFinalY();
    }
    
    public float e() {
        if (this.b) {
            return u.a(this.a);
        }
        return 0.0f;
    }
    
    public boolean f() {
        return this.a.computeScrollOffset();
    }
    
    public void g() {
        this.a.abortAnimation();
    }
}
