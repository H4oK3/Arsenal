// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View$MeasureSpec;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.view.MotionEvent;
import android.view.ViewGroup$MarginLayoutParams;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.o;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.view.menu.h;
import android.view.View$OnClickListener;
import android.support.v7.view.b;
import android.support.v4.g.as;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.g.ae;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;

public class ActionBarContextView extends a
{
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private boolean p;
    private int q;
    
    public ActionBarContextView(final Context context) {
        this(context, null);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.actionModeStyle);
    }
    
    public ActionBarContextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final av a = av.a(context, set, android.support.v7.a.a.j.ActionMode, n, 0);
        ae.a((View)this, a.a(android.support.v7.a.a.j.ActionMode_background));
        this.n = a.g(android.support.v7.a.a.j.ActionMode_titleTextStyle, 0);
        this.o = a.g(android.support.v7.a.a.j.ActionMode_subtitleTextStyle, 0);
        this.e = a.f(android.support.v7.a.a.j.ActionMode_height, 0);
        this.q = a.g(android.support.v7.a.a.j.ActionMode_closeItemLayout, android.support.v7.a.a.g.abc_action_mode_close_item_material);
        a.a();
    }
    
    private void e() {
        final int n = 8;
        boolean b = true;
        if (this.k == null) {
            LayoutInflater.from(this.getContext()).inflate(android.support.v7.a.a.g.abc_action_bar_title_item, (ViewGroup)this);
            this.k = (LinearLayout)this.getChildAt(this.getChildCount() - 1);
            this.l = (TextView)this.k.findViewById(android.support.v7.a.a.f.action_bar_title);
            this.m = (TextView)this.k.findViewById(android.support.v7.a.a.f.action_bar_subtitle);
            if (this.n != 0) {
                this.l.setTextAppearance(this.getContext(), this.n);
            }
            if (this.o != 0) {
                this.m.setTextAppearance(this.getContext(), this.o);
            }
        }
        this.l.setText(this.g);
        this.m.setText(this.h);
        int n2;
        if (!TextUtils.isEmpty(this.g)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (TextUtils.isEmpty(this.h)) {
            b = false;
        }
        final TextView m = this.m;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        m.setVisibility(visibility);
        final LinearLayout k = this.k;
        int visibility2 = 0;
        Label_0204: {
            if (n2 == 0) {
                visibility2 = n;
                if (!b) {
                    break Label_0204;
                }
            }
            visibility2 = 0;
        }
        k.setVisibility(visibility2);
        if (this.k.getParent() == null) {
            this.addView((View)this.k);
        }
    }
    
    public void a(final b b) {
        if (this.i == null) {
            this.addView(this.i = LayoutInflater.from(this.getContext()).inflate(this.q, (ViewGroup)this, false));
        }
        else if (this.i.getParent() == null) {
            this.addView(this.i);
        }
        this.i.findViewById(android.support.v7.a.a.f.action_mode_close_button).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                b.c();
            }
        });
        final h h = (h)b.b();
        if (this.d != null) {
            this.d.f();
        }
        (this.d = new d(this.getContext())).c(true);
        final ViewGroup$LayoutParams viewGroup$LayoutParams = new ViewGroup$LayoutParams(-2, -1);
        h.a(this.d, this.b);
        ae.a((View)(this.c = (ActionMenuView)this.d.a(this)), (Drawable)null);
        this.addView((View)this.c, viewGroup$LayoutParams);
    }
    
    @Override
    public boolean a() {
        return this.d != null && this.d.d();
    }
    
    public void b() {
        if (this.i == null) {
            this.c();
        }
    }
    
    public void c() {
        this.removeAllViews();
        this.j = null;
        this.c = null;
    }
    
    public boolean d() {
        return this.p;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(-1, -2);
    }
    
    public ViewGroup$LayoutParams generateLayoutParams(final AttributeSet set) {
        return (ViewGroup$LayoutParams)new ViewGroup$MarginLayoutParams(this.getContext(), set);
    }
    
    public CharSequence getSubtitle() {
        return this.h;
    }
    
    public CharSequence getTitle() {
        return this.g;
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.e();
            this.d.g();
        }
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (Build$VERSION.SDK_INT >= 14) {
            if (accessibilityEvent.getEventType() != 32) {
                super.onInitializeAccessibilityEvent(accessibilityEvent);
                return;
            }
            accessibilityEvent.setSource((View)this);
            accessibilityEvent.setClassName((CharSequence)this.getClass().getName());
            accessibilityEvent.setPackageName((CharSequence)this.getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.g);
        }
    }
    
    protected void onLayout(final boolean b, int paddingLeft, int n, final int n2, int n3) {
        final boolean a = ay.a((View)this);
        int paddingLeft2;
        if (a) {
            paddingLeft2 = n2 - paddingLeft - this.getPaddingRight();
        }
        else {
            paddingLeft2 = this.getPaddingLeft();
        }
        final int paddingTop = this.getPaddingTop();
        final int n4 = n3 - n - this.getPaddingTop() - this.getPaddingBottom();
        if (this.i != null && this.i.getVisibility() != 8) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.i.getLayoutParams();
            if (a) {
                n = viewGroup$MarginLayoutParams.rightMargin;
            }
            else {
                n = viewGroup$MarginLayoutParams.leftMargin;
            }
            if (a) {
                n3 = viewGroup$MarginLayoutParams.leftMargin;
            }
            else {
                n3 = viewGroup$MarginLayoutParams.rightMargin;
            }
            n = android.support.v7.widget.a.a(paddingLeft2, n, a);
            n = android.support.v7.widget.a.a(this.a(this.i, n, paddingTop, n4, a) + n, n3, a);
        }
        else {
            n = paddingLeft2;
        }
        n3 = n;
        if (this.k != null) {
            n3 = n;
            if (this.j == null) {
                n3 = n;
                if (this.k.getVisibility() != 8) {
                    n3 = n + this.a((View)this.k, n, paddingTop, n4, a);
                }
            }
        }
        if (this.j != null) {
            this.a(this.j, n3, paddingTop, n4, a);
        }
        if (a) {
            paddingLeft = this.getPaddingLeft();
        }
        else {
            paddingLeft = n2 - paddingLeft - this.getPaddingRight();
        }
        if (this.c != null) {
            this.a((View)this.c, paddingLeft, paddingTop, n4, !a);
        }
    }
    
    protected void onMeasure(int visibility, int i) {
        final int n = 1073741824;
        final int n2 = 0;
        if (View$MeasureSpec.getMode(visibility) != 1073741824) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View$MeasureSpec.getMode(i) == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
        final int size = View$MeasureSpec.getSize(visibility);
        int n3;
        if (this.e > 0) {
            n3 = this.e;
        }
        else {
            n3 = View$MeasureSpec.getSize(i);
        }
        final int n4 = this.getPaddingTop() + this.getPaddingBottom();
        visibility = size - this.getPaddingLeft() - this.getPaddingRight();
        final int n5 = n3 - n4;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(n5, Integer.MIN_VALUE);
        i = visibility;
        if (this.i != null) {
            visibility = this.a(this.i, visibility, measureSpec, 0);
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.i.getLayoutParams();
            i = viewGroup$MarginLayoutParams.leftMargin;
            i = visibility - (viewGroup$MarginLayoutParams.rightMargin + i);
        }
        visibility = i;
        if (this.c != null) {
            visibility = i;
            if (this.c.getParent() == this) {
                visibility = this.a((View)this.c, i, measureSpec, 0);
            }
        }
        i = visibility;
        if (this.k != null) {
            i = visibility;
            if (this.j == null) {
                if (this.p) {
                    i = View$MeasureSpec.makeMeasureSpec(0, 0);
                    this.k.measure(i, measureSpec);
                    final int measuredWidth = this.k.getMeasuredWidth();
                    boolean b;
                    if (measuredWidth <= visibility) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    i = visibility;
                    if (b) {
                        i = visibility - measuredWidth;
                    }
                    final LinearLayout k = this.k;
                    if (b) {
                        visibility = 0;
                    }
                    else {
                        visibility = 8;
                    }
                    k.setVisibility(visibility);
                }
                else {
                    i = this.a((View)this.k, visibility, measureSpec, 0);
                }
            }
        }
        if (this.j != null) {
            final ViewGroup$LayoutParams layoutParams = this.j.getLayoutParams();
            if (layoutParams.width != -2) {
                visibility = 1073741824;
            }
            else {
                visibility = Integer.MIN_VALUE;
            }
            int min = i;
            if (layoutParams.width >= 0) {
                min = Math.min(layoutParams.width, i);
            }
            if (layoutParams.height != -2) {
                i = n;
            }
            else {
                i = Integer.MIN_VALUE;
            }
            int min2;
            if (layoutParams.height >= 0) {
                min2 = Math.min(layoutParams.height, n5);
            }
            else {
                min2 = n5;
            }
            this.j.measure(View$MeasureSpec.makeMeasureSpec(min, visibility), View$MeasureSpec.makeMeasureSpec(min2, i));
        }
        if (this.e <= 0) {
            final int childCount = this.getChildCount();
            visibility = 0;
            int n6;
            for (i = n2; i < childCount; ++i) {
                n6 = this.getChildAt(i).getMeasuredHeight() + n4;
                if (n6 > visibility) {
                    visibility = n6;
                }
            }
            this.setMeasuredDimension(size, visibility);
            return;
        }
        this.setMeasuredDimension(size, n3);
    }
    
    @Override
    public void setContentHeight(final int e) {
        this.e = e;
    }
    
    public void setCustomView(final View j) {
        if (this.j != null) {
            this.removeView(this.j);
        }
        this.j = j;
        if (j != null && this.k != null) {
            this.removeView((View)this.k);
            this.k = null;
        }
        if (j != null) {
            this.addView(j);
        }
        this.requestLayout();
    }
    
    public void setSubtitle(final CharSequence h) {
        this.h = h;
        this.e();
    }
    
    public void setTitle(final CharSequence g) {
        this.g = g;
        this.e();
    }
    
    public void setTitleOptional(final boolean p) {
        if (p != this.p) {
            this.requestLayout();
        }
        this.p = p;
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
