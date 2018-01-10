// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.Drawable$Callback;
import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import android.widget.FrameLayout$LayoutParams;
import android.content.res.TypedArray;
import android.support.v7.a.a;
import android.support.v4.g.ae;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class ActionBarContainer extends FrameLayout
{
    Drawable a;
    Drawable b;
    Drawable c;
    boolean d;
    boolean e;
    private boolean f;
    private View g;
    private View h;
    private View i;
    private int j;
    
    public ActionBarContainer(final Context context) {
        this(context, null);
    }
    
    public ActionBarContainer(final Context context, final AttributeSet set) {
        super(context, set);
        b b;
        if (Build$VERSION.SDK_INT >= 21) {
            b = new c(this);
        }
        else {
            b = new b(this);
        }
        ae.a((View)this, b);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.ActionBar);
        this.a = obtainStyledAttributes.getDrawable(android.support.v7.a.a.j.ActionBar_background);
        this.b = obtainStyledAttributes.getDrawable(android.support.v7.a.a.j.ActionBar_backgroundStacked);
        this.j = obtainStyledAttributes.getDimensionPixelSize(android.support.v7.a.a.j.ActionBar_height, -1);
        if (this.getId() == android.support.v7.a.a.f.split_action_bar) {
            this.d = true;
            this.c = obtainStyledAttributes.getDrawable(android.support.v7.a.a.j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean willNotDraw;
        if (this.d) {
            willNotDraw = (this.c == null);
        }
        else {
            willNotDraw = (this.a == null && this.b == null);
        }
        this.setWillNotDraw(willNotDraw);
    }
    
    private boolean a(final View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }
    
    private int b(final View view) {
        final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)view.getLayoutParams();
        return frameLayout$LayoutParams.bottomMargin + (view.getMeasuredHeight() + frameLayout$LayoutParams.topMargin);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.a != null && this.a.isStateful()) {
            this.a.setState(this.getDrawableState());
        }
        if (this.b != null && this.b.isStateful()) {
            this.b.setState(this.getDrawableState());
        }
        if (this.c != null && this.c.isStateful()) {
            this.c.setState(this.getDrawableState());
        }
    }
    
    public View getTabContainer() {
        return this.g;
    }
    
    public void jumpDrawablesToCurrentState() {
        if (Build$VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            if (this.a != null) {
                this.a.jumpToCurrentState();
            }
            if (this.b != null) {
                this.b.jumpToCurrentState();
            }
            if (this.c != null) {
                this.c.jumpToCurrentState();
            }
        }
    }
    
    public void onFinishInflate() {
        super.onFinishInflate();
        this.h = this.findViewById(android.support.v7.a.a.f.action_bar);
        this.i = this.findViewById(android.support.v7.a.a.f.action_context_bar);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.f || super.onInterceptTouchEvent(motionEvent);
    }
    
    public void onLayout(final boolean b, int n, int measuredHeight, final int n2, final int n3) {
        final int n4 = 1;
        super.onLayout(b, n, measuredHeight, n2, n3);
        final View g = this.g;
        final boolean e = g != null && g.getVisibility() != 8;
        if (g != null && g.getVisibility() != 8) {
            measuredHeight = this.getMeasuredHeight();
            final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)g.getLayoutParams();
            g.layout(n, measuredHeight - g.getMeasuredHeight() - frameLayout$LayoutParams.bottomMargin, n2, measuredHeight - frameLayout$LayoutParams.bottomMargin);
        }
        if (this.d) {
            if (this.c != null) {
                this.c.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                n = n4;
            }
            else {
                n = 0;
            }
        }
        else {
            if (this.a != null) {
                if (this.h.getVisibility() == 0) {
                    this.a.setBounds(this.h.getLeft(), this.h.getTop(), this.h.getRight(), this.h.getBottom());
                }
                else if (this.i != null && this.i.getVisibility() == 0) {
                    this.a.setBounds(this.i.getLeft(), this.i.getTop(), this.i.getRight(), this.i.getBottom());
                }
                else {
                    this.a.setBounds(0, 0, 0, 0);
                }
                n = 1;
            }
            else {
                n = 0;
            }
            this.e = e;
            if (e && this.b != null) {
                this.b.setBounds(g.getLeft(), g.getTop(), g.getRight(), g.getBottom());
                n = n4;
            }
        }
        if (n != 0) {
            this.invalidate();
        }
    }
    
    public void onMeasure(int n, int n2) {
        int measureSpec = n2;
        if (this.h == null) {
            measureSpec = n2;
            if (View$MeasureSpec.getMode(n2) == Integer.MIN_VALUE) {
                measureSpec = n2;
                if (this.j >= 0) {
                    measureSpec = View$MeasureSpec.makeMeasureSpec(Math.min(this.j, View$MeasureSpec.getSize(n2)), Integer.MIN_VALUE);
                }
            }
        }
        super.onMeasure(n, measureSpec);
        if (this.h != null) {
            n2 = View$MeasureSpec.getMode(measureSpec);
            if (this.g != null && this.g.getVisibility() != 8 && n2 != 1073741824) {
                if (!this.a(this.h)) {
                    n = this.b(this.h);
                }
                else if (!this.a(this.i)) {
                    n = this.b(this.i);
                }
                else {
                    n = 0;
                }
                if (n2 == Integer.MIN_VALUE) {
                    n2 = View$MeasureSpec.getSize(measureSpec);
                }
                else {
                    n2 = Integer.MAX_VALUE;
                }
                this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(n + this.b(this.g), n2));
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }
    
    public void setPrimaryBackground(final Drawable a) {
        boolean willNotDraw = true;
        if (this.a != null) {
            this.a.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.a);
        }
        if ((this.a = a) != null) {
            a.setCallback((Drawable$Callback)this);
            if (this.h != null) {
                this.a.setBounds(this.h.getLeft(), this.h.getTop(), this.h.getRight(), this.h.getBottom());
            }
        }
        if (this.d) {
            if (this.c != null) {
                willNotDraw = false;
            }
        }
        else if (this.a != null || this.b != null) {
            willNotDraw = false;
        }
        this.setWillNotDraw(willNotDraw);
        this.invalidate();
    }
    
    public void setSplitBackground(final Drawable c) {
        boolean willNotDraw = true;
        if (this.c != null) {
            this.c.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.c);
        }
        if ((this.c = c) != null) {
            c.setCallback((Drawable$Callback)this);
            if (this.d && this.c != null) {
                this.c.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
        if (this.d) {
            if (this.c != null) {
                willNotDraw = false;
            }
        }
        else if (this.a != null || this.b != null) {
            willNotDraw = false;
        }
        this.setWillNotDraw(willNotDraw);
        this.invalidate();
    }
    
    public void setStackedBackground(final Drawable b) {
        boolean willNotDraw = true;
        if (this.b != null) {
            this.b.setCallback((Drawable$Callback)null);
            this.unscheduleDrawable(this.b);
        }
        if ((this.b = b) != null) {
            b.setCallback((Drawable$Callback)this);
            if (this.e && this.b != null) {
                this.b.setBounds(this.g.getLeft(), this.g.getTop(), this.g.getRight(), this.g.getBottom());
            }
        }
        if (this.d) {
            if (this.c != null) {
                willNotDraw = false;
            }
        }
        else if (this.a != null || this.b != null) {
            willNotDraw = false;
        }
        this.setWillNotDraw(willNotDraw);
        this.invalidate();
    }
    
    public void setTabContainer(final ao g) {
        if (this.g != null) {
            this.removeView(this.g);
        }
        if ((this.g = (View)g) != null) {
            this.addView((View)g);
            final ViewGroup$LayoutParams layoutParams = g.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            g.setAllowCollapse(false);
        }
    }
    
    public void setTransitioning(final boolean f) {
        this.f = f;
        int descendantFocusability;
        if (f) {
            descendantFocusability = 393216;
        }
        else {
            descendantFocusability = 262144;
        }
        this.setDescendantFocusability(descendantFocusability);
    }
    
    public void setVisibility(final int visibility) {
        super.setVisibility(visibility);
        final boolean b = visibility == 0;
        if (this.a != null) {
            this.a.setVisible(b, false);
        }
        if (this.b != null) {
            this.b.setVisible(b, false);
        }
        if (this.c != null) {
            this.c.setVisible(b, false);
        }
    }
    
    public ActionMode startActionModeForChild(final View view, final ActionMode$Callback actionMode$Callback) {
        return null;
    }
    
    public ActionMode startActionModeForChild(final View view, final ActionMode$Callback actionMode$Callback, final int n) {
        if (n != 0) {
            return super.startActionModeForChild(view, actionMode$Callback, n);
        }
        return null;
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return (drawable == this.a && !this.d) || (drawable == this.b && this.e) || (drawable == this.c && this.d) || super.verifyDrawable(drawable);
    }
}
