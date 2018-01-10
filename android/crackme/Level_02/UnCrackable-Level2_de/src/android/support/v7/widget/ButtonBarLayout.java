// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.LinearLayout$LayoutParams;
import android.support.v4.g.ae;
import android.os.Build$VERSION;
import android.view.View$MeasureSpec;
import android.view.View;
import android.content.res.TypedArray;
import android.support.v4.b.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{
    private boolean a;
    private int b;
    
    public ButtonBarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.b = -1;
        final boolean b = android.support.v4.b.a.a.a(this.getResources()) >= 320;
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.ButtonBarLayout);
        this.a = obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.ButtonBarLayout_allowStacking, b);
        obtainStyledAttributes.recycle();
    }
    
    private int a(int i) {
        while (i < this.getChildCount()) {
            if (this.getChildAt(i).getVisibility() == 0) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    private boolean a() {
        return this.getOrientation() == 1;
    }
    
    private void setStacked(final boolean b) {
        int orientation;
        if (b) {
            orientation = 1;
        }
        else {
            orientation = 0;
        }
        this.setOrientation(orientation);
        int gravity;
        if (b) {
            gravity = 5;
        }
        else {
            gravity = 80;
        }
        this.setGravity(gravity);
        final View viewById = this.findViewById(android.support.v7.a.a.f.spacer);
        if (viewById != null) {
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 4;
            }
            viewById.setVisibility(visibility);
        }
        for (int i = this.getChildCount() - 2; i >= 0; --i) {
            this.bringChildToFront(this.getChildAt(i));
        }
    }
    
    protected void onMeasure(int a, int paddingTop) {
        final int size = View$MeasureSpec.getSize(a);
        if (this.a) {
            if (size > this.b && this.a()) {
                this.setStacked(false);
            }
            this.b = size;
        }
        int measureSpec;
        boolean b;
        if (!this.a() && View$MeasureSpec.getMode(a) == 1073741824) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            b = true;
        }
        else {
            measureSpec = a;
            b = false;
        }
        super.onMeasure(measureSpec, paddingTop);
        int n = b ? 1 : 0;
        if (this.a) {
            n = (b ? 1 : 0);
            if (!this.a()) {
                int n2;
                if (Build$VERSION.SDK_INT >= 11) {
                    if ((ae.d((View)this) & 0xFF000000) == 0x1000000) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                else {
                    final int childCount = this.getChildCount();
                    int i = 0;
                    int n3 = 0;
                    while (i < childCount) {
                        n3 += this.getChildAt(i).getMeasuredWidth();
                        ++i;
                    }
                    if (this.getPaddingLeft() + n3 + this.getPaddingRight() > size) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                }
                n = (b ? 1 : 0);
                if (n2 != 0) {
                    this.setStacked(true);
                    n = 1;
                }
            }
        }
        if (n != 0) {
            super.onMeasure(a, paddingTop);
        }
        a = this.a(0);
        if (a >= 0) {
            final View child = this.getChildAt(a);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)child.getLayoutParams();
            paddingTop = this.getPaddingTop();
            paddingTop = linearLayout$LayoutParams.bottomMargin + (child.getMeasuredHeight() + paddingTop + linearLayout$LayoutParams.topMargin) + 0;
            if (this.a()) {
                final int a2 = this.a(a + 1);
                a = paddingTop;
                if (a2 >= 0) {
                    a = (int)(paddingTop + (this.getChildAt(a2).getPaddingTop() + 16.0f * this.getResources().getDisplayMetrics().density));
                }
            }
            else {
                a = paddingTop + this.getPaddingBottom();
            }
        }
        else {
            a = 0;
        }
        if (ae.g((View)this) != a) {
            this.setMinimumHeight(a);
        }
    }
    
    public void setAllowStacking(final boolean a) {
        if (this.a != a) {
            this.a = a;
            if (!this.a && this.getOrientation() == 1) {
                this.setStacked(false);
            }
            this.requestLayout();
        }
    }
}
