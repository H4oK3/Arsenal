// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.a.a;
import android.util.AttributeSet;
import android.os.Build$VERSION;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.support.v4.g.ae;
import android.graphics.drawable.Drawable;
import android.view.View;

class g
{
    private final View a;
    private final l b;
    private int c;
    private at d;
    private at e;
    private at f;
    
    g(final View a) {
        this.c = -1;
        this.a = a;
        this.b = l.a();
    }
    
    private boolean b(final Drawable drawable) {
        if (this.f == null) {
            this.f = new at();
        }
        final at f = this.f;
        f.a();
        final ColorStateList n = ae.n(this.a);
        if (n != null) {
            f.d = true;
            f.a = n;
        }
        final PorterDuff$Mode o = ae.o(this.a);
        if (o != null) {
            f.c = true;
            f.b = o;
        }
        if (f.d || f.c) {
            l.a(drawable, f, this.a.getDrawableState());
            return true;
        }
        return false;
    }
    
    private boolean d() {
        final boolean b = true;
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean b2;
        if (sdk_INT < 21) {
            b2 = false;
        }
        else {
            b2 = b;
            if (sdk_INT != 21) {
                b2 = b;
                if (this.d == null) {
                    return false;
                }
            }
        }
        return b2;
    }
    
    ColorStateList a() {
        if (this.e != null) {
            return this.e.a;
        }
        return null;
    }
    
    void a(final int c) {
        this.c = c;
        ColorStateList b;
        if (this.b != null) {
            b = this.b.b(this.a.getContext(), c);
        }
        else {
            b = null;
        }
        this.b(b);
        this.c();
    }
    
    void a(final ColorStateList a) {
        if (this.e == null) {
            this.e = new at();
        }
        this.e.a = a;
        this.e.d = true;
        this.c();
    }
    
    void a(final PorterDuff$Mode b) {
        if (this.e == null) {
            this.e = new at();
        }
        this.e.b = b;
        this.e.c = true;
        this.c();
    }
    
    void a(final Drawable drawable) {
        this.c = -1;
        this.b((ColorStateList)null);
        this.c();
    }
    
    void a(AttributeSet a, final int n) {
        a = (AttributeSet)av.a(this.a.getContext(), a, a.j.ViewBackgroundHelper, n, 0);
        try {
            if (((av)a).g(a.j.ViewBackgroundHelper_android_background)) {
                this.c = ((av)a).g(a.j.ViewBackgroundHelper_android_background, -1);
                final ColorStateList b = this.b.b(this.a.getContext(), this.c);
                if (b != null) {
                    this.b(b);
                }
            }
            if (((av)a).g(a.j.ViewBackgroundHelper_backgroundTint)) {
                ae.a(this.a, ((av)a).e(a.j.ViewBackgroundHelper_backgroundTint));
            }
            if (((av)a).g(a.j.ViewBackgroundHelper_backgroundTintMode)) {
                ae.a(this.a, ad.a(((av)a).a(a.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        }
        finally {
            ((av)a).a();
        }
    }
    
    PorterDuff$Mode b() {
        if (this.e != null) {
            return this.e.b;
        }
        return null;
    }
    
    void b(final ColorStateList a) {
        if (a != null) {
            if (this.d == null) {
                this.d = new at();
            }
            this.d.a = a;
            this.d.d = true;
        }
        else {
            this.d = null;
        }
        this.c();
    }
    
    void c() {
        final Drawable background = this.a.getBackground();
        if (background != null && (!this.d() || !this.b(background))) {
            if (this.e != null) {
                l.a(background, this.e, this.a.getDrawableState());
                return;
            }
            if (this.d != null) {
                l.a(background, this.d, this.a.getDrawableState());
            }
        }
    }
}
