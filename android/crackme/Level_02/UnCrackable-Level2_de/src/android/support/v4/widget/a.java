// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.AnimationUtils;
import android.support.v4.g.t;
import android.view.MotionEvent;
import android.os.SystemClock;
import android.support.v4.g.ae;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.view.animation.AccelerateInterpolator;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.View;
import android.view.View$OnTouchListener;

public abstract class a implements View$OnTouchListener
{
    private static final int r;
    final a a;
    final View b;
    boolean c;
    boolean d;
    boolean e;
    private final Interpolator f;
    private Runnable g;
    private float[] h;
    private float[] i;
    private int j;
    private int k;
    private float[] l;
    private float[] m;
    private float[] n;
    private boolean o;
    private boolean p;
    private boolean q;
    
    static {
        r = ViewConfiguration.getTapTimeout();
    }
    
    public a(final View b) {
        this.a = new a();
        this.f = (Interpolator)new AccelerateInterpolator();
        this.h = new float[] { 0.0f, 0.0f };
        this.i = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
        this.l = new float[] { 0.0f, 0.0f };
        this.m = new float[] { 0.0f, 0.0f };
        this.n = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
        this.b = b;
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        final int n = (int)(1575.0f * displayMetrics.density + 0.5f);
        final int n2 = (int)(displayMetrics.density * 315.0f + 0.5f);
        this.a(n, (float)n);
        this.b(n2, n2);
        this.a(1);
        this.e(Float.MAX_VALUE, Float.MAX_VALUE);
        this.d(0.2f, 0.2f);
        this.c(1.0f, 1.0f);
        this.b(android.support.v4.widget.a.r);
        this.c(500);
        this.d(500);
    }
    
    static float a(final float n, final float n2, final float n3) {
        if (n > n3) {
            return n3;
        }
        if (n < n2) {
            return n2;
        }
        return n;
    }
    
    private float a(float n, float n2, float f, final float n3) {
        final float n4 = 0.0f;
        n = a(n * n2, 0.0f, f);
        f = this.f(n3, n);
        n2 = this.f(n2 - n3, n) - f;
        if (n2 < 0.0f) {
            n = -this.f.getInterpolation(-n2);
        }
        else {
            n = n4;
            if (n2 <= 0.0f) {
                return n;
            }
            n = this.f.getInterpolation(n2);
        }
        n = a(n, -1.0f, 1.0f);
        return n;
    }
    
    private float a(final int n, float a, float n2, float n3) {
        a = this.a(this.h[n], n2, this.i[n], a);
        if (a == 0.0f) {
            return 0.0f;
        }
        final float n4 = this.l[n];
        n2 = this.m[n];
        final float n5 = this.n[n];
        n3 *= n4;
        if (a > 0.0f) {
            return a(a * n3, n2, n5);
        }
        return -a(-a * n3, n2, n5);
    }
    
    static int a(final int n, final int n2, final int n3) {
        if (n > n3) {
            return n3;
        }
        if (n < n2) {
            return n2;
        }
        return n;
    }
    
    private void c() {
        if (this.g == null) {
            this.g = new b();
        }
        this.e = true;
        this.c = true;
        if (!this.o && this.k > 0) {
            ae.a(this.b, this.g, this.k);
        }
        else {
            this.g.run();
        }
        this.o = true;
    }
    
    private void d() {
        if (this.c) {
            this.e = false;
            return;
        }
        this.a.b();
    }
    
    private float f(final float n, final float n2) {
        if (n2 != 0.0f) {
            switch (this.j) {
                default: {
                    return 0.0f;
                }
                case 0:
                case 1: {
                    if (n >= n2) {
                        break;
                    }
                    if (n >= 0.0f) {
                        return 1.0f - n / n2;
                    }
                    if (this.e && this.j == 1) {
                        return 1.0f;
                    }
                    break;
                }
                case 2: {
                    if (n < 0.0f) {
                        return n / -n2;
                    }
                    break;
                }
            }
        }
        return 0.0f;
    }
    
    public a a(final float n, final float n2) {
        this.n[0] = n / 1000.0f;
        this.n[1] = n2 / 1000.0f;
        return this;
    }
    
    public a a(final int j) {
        this.j = j;
        return this;
    }
    
    public a a(final boolean p) {
        if (this.p && !p) {
            this.d();
        }
        this.p = p;
        return this;
    }
    
    public abstract void a(final int p0, final int p1);
    
    boolean a() {
        final a a = this.a;
        final int f = a.f();
        final int e = a.e();
        return (f != 0 && this.f(f)) || (e != 0 && this.e(e));
    }
    
    public a b(final float n, final float n2) {
        this.m[0] = n / 1000.0f;
        this.m[1] = n2 / 1000.0f;
        return this;
    }
    
    public a b(final int k) {
        this.k = k;
        return this;
    }
    
    void b() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        final MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.b.onTouchEvent(obtain);
        obtain.recycle();
    }
    
    public a c(final float n, final float n2) {
        this.l[0] = n / 1000.0f;
        this.l[1] = n2 / 1000.0f;
        return this;
    }
    
    public a c(final int n) {
        this.a.a(n);
        return this;
    }
    
    public a d(final float n, final float n2) {
        this.h[0] = n;
        this.h[1] = n2;
        return this;
    }
    
    public a d(final int n) {
        this.a.b(n);
        return this;
    }
    
    public a e(final float n, final float n2) {
        this.i[0] = n;
        this.i[1] = n2;
        return this;
    }
    
    public abstract boolean e(final int p0);
    
    public abstract boolean f(final int p0);
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        boolean b = true;
        if (!this.p) {
            return false;
        }
        switch (t.a(motionEvent)) {
            case 0: {
                this.d = true;
                this.o = false;
            }
            case 2: {
                this.a.a(this.a(0, motionEvent.getX(), view.getWidth(), this.b.getWidth()), this.a(1, motionEvent.getY(), view.getHeight(), this.b.getHeight()));
                if (!this.e && this.a()) {
                    this.c();
                    break;
                }
                break;
            }
            case 1:
            case 3: {
                this.d();
                break;
            }
        }
        if (!this.q || !this.e) {
            b = false;
        }
        return b;
    }
    
    private static class a
    {
        private int a;
        private int b;
        private float c;
        private float d;
        private long e;
        private long f;
        private int g;
        private int h;
        private long i;
        private float j;
        private int k;
        
        a() {
            this.e = Long.MIN_VALUE;
            this.i = -1L;
            this.f = 0L;
            this.g = 0;
            this.h = 0;
        }
        
        private float a(final float n) {
            return -4.0f * n * n + 4.0f * n;
        }
        
        private float a(final long n) {
            if (n < this.e) {
                return 0.0f;
            }
            if (this.i < 0L || n < this.i) {
                return android.support.v4.widget.a.a((n - this.e) / this.a, 0.0f, 1.0f) * 0.5f;
            }
            return android.support.v4.widget.a.a((n - this.i) / this.k, 0.0f, 1.0f) * this.j + (1.0f - this.j);
        }
        
        public void a() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1L;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }
        
        public void a(final float c, final float d) {
            this.c = c;
            this.d = d;
        }
        
        public void a(final int a) {
            this.a = a;
        }
        
        public void b() {
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = android.support.v4.widget.a.a((int)(currentAnimationTimeMillis - this.e), 0, this.b);
            this.j = this.a(currentAnimationTimeMillis);
            this.i = currentAnimationTimeMillis;
        }
        
        public void b(final int b) {
            this.b = b;
        }
        
        public boolean c() {
            return this.i > 0L && AnimationUtils.currentAnimationTimeMillis() > this.i + this.k;
        }
        
        public void d() {
            if (this.f == 0L) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            final long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            final float a = this.a(this.a(currentAnimationTimeMillis));
            final long n = currentAnimationTimeMillis - this.f;
            this.f = currentAnimationTimeMillis;
            this.g = (int)(n * a * this.c);
            this.h = (int)(n * a * this.d);
        }
        
        public int e() {
            return (int)(this.c / Math.abs(this.c));
        }
        
        public int f() {
            return (int)(this.d / Math.abs(this.d));
        }
        
        public int g() {
            return this.g;
        }
        
        public int h() {
            return this.h;
        }
    }
    
    private class b implements Runnable
    {
        @Override
        public void run() {
            if (!android.support.v4.widget.a.this.e) {
                return;
            }
            if (android.support.v4.widget.a.this.c) {
                android.support.v4.widget.a.this.c = false;
                android.support.v4.widget.a.this.a.a();
            }
            final a a = android.support.v4.widget.a.this.a;
            if (a.c() || !android.support.v4.widget.a.this.a()) {
                android.support.v4.widget.a.this.e = false;
                return;
            }
            if (android.support.v4.widget.a.this.d) {
                android.support.v4.widget.a.this.d = false;
                android.support.v4.widget.a.this.b();
            }
            a.d();
            android.support.v4.widget.a.this.a(a.g(), a.h());
            ae.a(android.support.v4.widget.a.this.b, this);
        }
    }
}
