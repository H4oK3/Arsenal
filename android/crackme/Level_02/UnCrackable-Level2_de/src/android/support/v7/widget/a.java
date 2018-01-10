// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.g.t;
import android.view.MotionEvent;
import android.content.res.TypedArray;
import android.content.res.Configuration;
import android.support.v4.g.aw;
import android.support.v4.g.ae;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ContextThemeWrapper;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.support.v4.g.as;
import android.content.Context;
import android.view.ViewGroup;

abstract class a extends ViewGroup
{
    protected final a a;
    protected final Context b;
    protected ActionMenuView c;
    protected d d;
    protected int e;
    protected as f;
    private boolean g;
    private boolean h;
    
    a(final Context context) {
        this(context, null);
    }
    
    a(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    a(final Context b, final AttributeSet set, final int n) {
        super(b, set, n);
        this.a = new a();
        final TypedValue typedValue = new TypedValue();
        if (b.getTheme().resolveAttribute(android.support.v7.a.a.a.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.b = (Context)new ContextThemeWrapper(b, typedValue.resourceId);
            return;
        }
        this.b = b;
    }
    
    protected static int a(final int n, final int n2, final boolean b) {
        if (b) {
            return n - n2;
        }
        return n + n2;
    }
    
    static /* synthetic */ void a(final a a, final int visibility) {
        a.setVisibility(visibility);
    }
    
    static /* synthetic */ void b(final a a, final int visibility) {
        a.setVisibility(visibility);
    }
    
    protected int a(final View view, final int n, final int n2, final int n3) {
        view.measure(View$MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE), n2);
        return Math.max(0, n - view.getMeasuredWidth() - n3);
    }
    
    protected int a(final View view, int n, int n2, final int n3, final boolean b) {
        final int measuredWidth = view.getMeasuredWidth();
        final int measuredHeight = view.getMeasuredHeight();
        n2 += (n3 - measuredHeight) / 2;
        if (b) {
            view.layout(n - measuredWidth, n2, n, measuredHeight + n2);
        }
        else {
            view.layout(n, n2, n + measuredWidth, measuredHeight + n2);
        }
        n = measuredWidth;
        if (b) {
            n = -measuredWidth;
        }
        return n;
    }
    
    public as a(final int n, final long n2) {
        if (this.f != null) {
            this.f.b();
        }
        if (n == 0) {
            if (this.getVisibility() != 0) {
                ae.b((View)this, 0.0f);
            }
            final as a = ae.h((View)this).a(1.0f);
            a.a(n2);
            a.a(this.a.a(a, n));
            return a;
        }
        final as a2 = ae.h((View)this).a(0.0f);
        a2.a(n2);
        a2.a(this.a.a(a2, n));
        return a2;
    }
    
    public boolean a() {
        return this.d != null && this.d.d();
    }
    
    public int getAnimatedVisibility() {
        if (this.f != null) {
            return this.a.a;
        }
        return this.getVisibility();
    }
    
    public int getContentHeight() {
        return this.e;
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes((AttributeSet)null, android.support.v7.a.a.j.ActionBar, android.support.v7.a.a.a.actionBarStyle, 0);
        this.setContentHeight(obtainStyledAttributes.getLayoutDimension(android.support.v7.a.a.j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.d != null) {
            this.d.a(configuration);
        }
    }
    
    public boolean onHoverEvent(final MotionEvent motionEvent) {
        final int a = t.a(motionEvent);
        if (a == 9) {
            this.h = false;
        }
        if (!this.h) {
            final boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (a == 9 && !onHoverEvent) {
                this.h = true;
            }
        }
        if (a == 10 || a == 3) {
            this.h = false;
        }
        return true;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int a = t.a(motionEvent);
        if (a == 0) {
            this.g = false;
        }
        if (!this.g) {
            final boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (a == 0 && !onTouchEvent) {
                this.g = true;
            }
        }
        if (a == 1 || a == 3) {
            this.g = false;
        }
        return true;
    }
    
    public void setContentHeight(final int e) {
        this.e = e;
        this.requestLayout();
    }
    
    public void setVisibility(final int visibility) {
        if (visibility != this.getVisibility()) {
            if (this.f != null) {
                this.f.b();
            }
            super.setVisibility(visibility);
        }
    }
    
    protected class a implements aw
    {
        int a;
        private boolean c;
        
        protected a() {
            this.c = false;
        }
        
        public a a(final as f, final int a) {
            a.this.f = f;
            this.a = a;
            return this;
        }
        
        @Override
        public void a(final View view) {
            android.support.v7.widget.a.a(android.support.v7.widget.a.this, 0);
            this.c = false;
        }
        
        @Override
        public void b(final View view) {
            if (this.c) {
                return;
            }
            android.support.v7.widget.a.this.f = null;
            android.support.v7.widget.a.b(android.support.v7.widget.a.this, this.a);
        }
        
        @Override
        public void c(final View view) {
            this.c = true;
        }
    }
}
