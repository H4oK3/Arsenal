// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewParent;
import android.os.SystemClock;
import android.support.v7.view.menu.s;
import android.support.v4.g.ae;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.support.v4.g.t;
import android.view.MotionEvent;
import android.annotation.TargetApi;
import android.view.View$OnAttachStateChangeListener;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.view.View;
import android.view.View$OnTouchListener;

public abstract class ag implements View$OnTouchListener
{
    private final float a;
    private final int b;
    final View c;
    private final int d;
    private Runnable e;
    private Runnable f;
    private boolean g;
    private int h;
    private final int[] i;
    
    public ag(final View c) {
        this.i = new int[2];
        (this.c = c).setLongClickable(true);
        if (Build$VERSION.SDK_INT >= 12) {
            this.a(c);
        }
        else {
            this.b(c);
        }
        this.a = ViewConfiguration.get(c.getContext()).getScaledTouchSlop();
        this.b = ViewConfiguration.getTapTimeout();
        this.d = (this.b + ViewConfiguration.getLongPressTimeout()) / 2;
    }
    
    @TargetApi(12)
    private void a(final View view) {
        view.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)new View$OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(final View view) {
            }
            
            public void onViewDetachedFromWindow(final View view) {
                ag.this.e();
            }
        });
    }
    
    private boolean a(final MotionEvent motionEvent) {
        final View c = this.c;
        if (c.isEnabled()) {
            switch (t.a(motionEvent)) {
                default: {
                    return false;
                }
                case 0: {
                    this.h = motionEvent.getPointerId(0);
                    if (this.e == null) {
                        this.e = new a();
                    }
                    c.postDelayed(this.e, (long)this.b);
                    if (this.f == null) {
                        this.f = new b();
                    }
                    c.postDelayed(this.f, (long)this.d);
                    return false;
                }
                case 2: {
                    final int pointerIndex = motionEvent.findPointerIndex(this.h);
                    if (pointerIndex >= 0 && !a(c, motionEvent.getX(pointerIndex), motionEvent.getY(pointerIndex), this.a)) {
                        this.f();
                        c.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                    break;
                }
                case 1:
                case 3: {
                    this.f();
                    return false;
                }
            }
        }
        return false;
    }
    
    private static boolean a(final View view, final float n, final float n2, final float n3) {
        return n >= -n3 && n2 >= -n3 && n < view.getRight() - view.getLeft() + n3 && n2 < view.getBottom() - view.getTop() + n3;
    }
    
    private boolean a(final View view, final MotionEvent motionEvent) {
        final int[] i = this.i;
        view.getLocationOnScreen(i);
        motionEvent.offsetLocation((float)(-i[0]), (float)(-i[1]));
        return true;
    }
    
    private void b(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            boolean a = ae.r(ag.this.c);
            
            public void onGlobalLayout() {
                final boolean a = this.a;
                this.a = ae.r(ag.this.c);
                if (a && !this.a) {
                    ag.this.e();
                }
            }
        });
    }
    
    private boolean b(final MotionEvent motionEvent) {
        final View c = this.c;
        final s a = this.a();
        if (a != null && a.d()) {
            final android.support.v7.widget.ae ae = (android.support.v7.widget.ae)a.e();
            if (ae != null && ae.isShown()) {
                final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                this.b(c, obtainNoHistory);
                this.a((View)ae, obtainNoHistory);
                final boolean a2 = ae.a(obtainNoHistory, this.h);
                obtainNoHistory.recycle();
                final int a3 = t.a(motionEvent);
                boolean b;
                if (a3 != 1 && a3 != 3) {
                    b = true;
                }
                else {
                    b = false;
                }
                return a2 && b;
            }
        }
        return false;
    }
    
    private boolean b(final View view, final MotionEvent motionEvent) {
        final int[] i = this.i;
        view.getLocationOnScreen(i);
        motionEvent.offsetLocation((float)i[0], (float)i[1]);
        return true;
    }
    
    private void e() {
        this.g = false;
        this.h = -1;
        if (this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }
    
    private void f() {
        if (this.f != null) {
            this.c.removeCallbacks(this.f);
        }
        if (this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }
    
    public abstract s a();
    
    protected boolean b() {
        final s a = this.a();
        if (a != null && !a.d()) {
            a.a();
        }
        return true;
    }
    
    protected boolean c() {
        final s a = this.a();
        if (a != null && a.d()) {
            a.c();
        }
        return true;
    }
    
    void d() {
        this.f();
        final View c = this.c;
        if (c.isEnabled() && !c.isLongClickable() && this.b()) {
            c.getParent().requestDisallowInterceptTouchEvent(true);
            final long uptimeMillis = SystemClock.uptimeMillis();
            final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            c.onTouchEvent(obtain);
            obtain.recycle();
            this.g = true;
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final boolean b = false;
        final boolean g = this.g;
        boolean g2;
        if (g) {
            g2 = (this.b(motionEvent) || !this.c());
        }
        else {
            g2 = (this.a(motionEvent) && this.b());
            if (g2) {
                final long uptimeMillis = SystemClock.uptimeMillis();
                final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.c.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!(this.g = g2)) {
            final boolean b2 = b;
            if (!g) {
                return b2;
            }
        }
        return true;
    }
    
    private class a implements Runnable
    {
        @Override
        public void run() {
            final ViewParent parent = ag.this.c.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }
    
    private class b implements Runnable
    {
        @Override
        public void run() {
            ag.this.d();
        }
    }
}
