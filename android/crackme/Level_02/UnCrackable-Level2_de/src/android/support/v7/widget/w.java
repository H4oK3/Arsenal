// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.view.View;
import android.support.v4.g.ae;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.Canvas;
import android.support.v4.c.a.a;
import android.widget.ProgressBar;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;

class w extends s
{
    private final SeekBar a;
    private Drawable b;
    private ColorStateList c;
    private PorterDuff$Mode d;
    private boolean e;
    private boolean f;
    
    w(final SeekBar a) {
        super((ProgressBar)a);
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.a = a;
    }
    
    private void d() {
        if (this.b != null && (this.e || this.f)) {
            this.b = android.support.v4.c.a.a.f(this.b.mutate());
            if (this.e) {
                android.support.v4.c.a.a.a(this.b, this.c);
            }
            if (this.f) {
                android.support.v4.c.a.a.a(this.b, this.d);
            }
            if (this.b.isStateful()) {
                this.b.setState(this.a.getDrawableState());
            }
        }
    }
    
    void a(final Canvas canvas) {
        int n = 1;
        if (this.b != null) {
            final int max = this.a.getMax();
            if (max > 1) {
                final int intrinsicWidth = this.b.getIntrinsicWidth();
                final int intrinsicHeight = this.b.getIntrinsicHeight();
                int n2;
                if (intrinsicWidth >= 0) {
                    n2 = intrinsicWidth / 2;
                }
                else {
                    n2 = 1;
                }
                if (intrinsicHeight >= 0) {
                    n = intrinsicHeight / 2;
                }
                this.b.setBounds(-n2, -n, n2, n);
                final float n3 = (this.a.getWidth() - this.a.getPaddingLeft() - this.a.getPaddingRight()) / max;
                final int save = canvas.save();
                canvas.translate((float)this.a.getPaddingLeft(), (float)(this.a.getHeight() / 2));
                for (int i = 0; i <= max; ++i) {
                    this.b.draw(canvas);
                    canvas.translate(n3, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
    
    void a(final Drawable b) {
        if (this.b != null) {
            this.b.setCallback((Drawable$Callback)null);
        }
        if ((this.b = b) != null) {
            b.setCallback((Drawable$Callback)this.a);
            android.support.v4.c.a.a.b(b, ae.c((View)this.a));
            if (b.isStateful()) {
                b.setState(this.a.getDrawableState());
            }
            this.d();
        }
        this.a.invalidate();
    }
    
    @Override
    void a(final AttributeSet set, final int n) {
        super.a(set, n);
        final av a = av.a(this.a.getContext(), set, android.support.v7.a.a.j.AppCompatSeekBar, n, 0);
        final Drawable b = a.b(android.support.v7.a.a.j.AppCompatSeekBar_android_thumb);
        if (b != null) {
            this.a.setThumb(b);
        }
        this.a(a.a(android.support.v7.a.a.j.AppCompatSeekBar_tickMark));
        if (a.g(android.support.v7.a.a.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.d = ad.a(a.a(android.support.v7.a.a.j.AppCompatSeekBar_tickMarkTintMode, -1), this.d);
            this.f = true;
        }
        if (a.g(android.support.v7.a.a.j.AppCompatSeekBar_tickMarkTint)) {
            this.c = a.e(android.support.v7.a.a.j.AppCompatSeekBar_tickMarkTint);
            this.e = true;
        }
        a.a();
        this.d();
    }
    
    @TargetApi(11)
    void b() {
        if (this.b != null) {
            this.b.jumpToCurrentState();
        }
    }
    
    void c() {
        final Drawable b = this.b;
        if (b != null && b.isStateful() && b.setState(this.a.getDrawableState())) {
            this.a.invalidateDrawable(b);
        }
    }
}
