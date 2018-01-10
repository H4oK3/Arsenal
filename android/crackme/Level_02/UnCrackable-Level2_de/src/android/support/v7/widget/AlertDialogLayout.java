// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.g.f;
import android.view.View$MeasureSpec;
import android.support.v7.a.a;
import android.view.ViewGroup;
import android.support.v4.g.ae;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;

public class AlertDialogLayout extends ah
{
    public AlertDialogLayout(final Context context) {
        super(context);
    }
    
    public AlertDialogLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    private void a(final View view, final int n, final int n2, final int n3, final int n4) {
        view.layout(n, n2, n + n3, n2 + n4);
    }
    
    private static int c(final View view) {
        final int g = ae.g(view);
        if (g > 0) {
            return g;
        }
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup)view;
            if (viewGroup.getChildCount() == 1) {
                return c(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }
    
    private boolean c(final int n, final int n2) {
        View view = null;
        View view2 = null;
        final int childCount = this.getChildCount();
        int i = 0;
        View view3 = null;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            View view5;
            View view6;
            if (child.getVisibility() == 8) {
                final View view4 = view2;
                view5 = view;
                view6 = view4;
            }
            else {
                final int id = child.getId();
                if (id == android.support.v7.a.a.f.topPanel) {
                    view6 = view2;
                    view5 = child;
                }
                else if (id == android.support.v7.a.a.f.buttonPanel) {
                    view5 = view;
                    view6 = child;
                }
                else {
                    if (id != android.support.v7.a.a.f.contentPanel && id != android.support.v7.a.a.f.customPanel) {
                        return false;
                    }
                    if (view3 != null) {
                        return false;
                    }
                    final View view7 = view;
                    view3 = child;
                    view6 = view2;
                    view5 = view7;
                }
            }
            ++i;
            final View view8 = view5;
            view2 = view6;
            view = view8;
        }
        final int mode = View$MeasureSpec.getMode(n2);
        final int size = View$MeasureSpec.getSize(n2);
        final int mode2 = View$MeasureSpec.getMode(n);
        int n3 = 0;
        int n5;
        final int n4 = n5 = this.getPaddingBottom() + this.getPaddingTop();
        if (view != null) {
            view.measure(n, 0);
            n5 = n4 + view.getMeasuredHeight();
            n3 = ae.a(0, ae.e(view));
        }
        int c = 0;
        int n6;
        if (view2 != null) {
            view2.measure(n, 0);
            c = c(view2);
            final int measuredHeight = view2.getMeasuredHeight();
            n5 += c;
            n3 = ae.a(n3, ae.e(view2));
            n6 = measuredHeight - c;
        }
        else {
            n6 = 0;
        }
        int measuredHeight2;
        if (view3 != null) {
            int measureSpec;
            if (mode == 0) {
                measureSpec = 0;
            }
            else {
                measureSpec = View$MeasureSpec.makeMeasureSpec(Math.max(0, size - n5), mode);
            }
            view3.measure(n, measureSpec);
            measuredHeight2 = view3.getMeasuredHeight();
            n5 += measuredHeight2;
            n3 = ae.a(n3, ae.e(view3));
        }
        else {
            measuredHeight2 = 0;
        }
        int n7 = size - n5;
        int n10;
        int a2;
        if (view2 != null) {
            final int min = Math.min(n7, n6);
            int n8 = n7;
            int n9 = c;
            if (min > 0) {
                n8 = n7 - min;
                n9 = c + min;
            }
            view2.measure(n, View$MeasureSpec.makeMeasureSpec(n9, 1073741824));
            final int measuredHeight3 = view2.getMeasuredHeight();
            final int a = ae.a(n3, ae.e(view2));
            n10 = measuredHeight3 + (n5 - c);
            n7 = n8;
            a2 = a;
        }
        else {
            n10 = n5;
            a2 = n3;
        }
        if (view3 != null && n7 > 0) {
            view3.measure(n, View$MeasureSpec.makeMeasureSpec(n7 + measuredHeight2, mode));
            final int measuredHeight4 = view3.getMeasuredHeight();
            a2 = ae.a(a2, ae.e(view3));
            n10 = n10 - measuredHeight2 + measuredHeight4;
        }
        int n11 = 0;
        int max;
        for (int j = 0; j < childCount; ++j, n11 = max) {
            final View child2 = this.getChildAt(j);
            max = n11;
            if (child2.getVisibility() != 8) {
                max = Math.max(n11, child2.getMeasuredWidth());
            }
        }
        this.setMeasuredDimension(ae.a(n11 + (this.getPaddingLeft() + this.getPaddingRight()), n, a2), ae.a(n10, n2, 0));
        if (mode2 != 1073741824) {
            this.d(childCount, n2);
        }
        return true;
    }
    
    private void d(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                final a a = (a)child.getLayoutParams();
                if (a.width == -1) {
                    final int height = a.height;
                    a.height = child.getMeasuredHeight();
                    this.measureChildWithMargins(child, measureSpec, 0, n2, 0);
                    a.height = height;
                }
            }
        }
    }
    
    @Override
    protected void onLayout(final boolean b, int n, int h, int n2, int i) {
        final int paddingLeft = this.getPaddingLeft();
        final int n3 = n2 - n;
        final int paddingRight = this.getPaddingRight();
        final int paddingRight2 = this.getPaddingRight();
        n = this.getMeasuredHeight();
        final int childCount = this.getChildCount();
        final int gravity = this.getGravity();
        switch (gravity & 0x70) {
            default: {
                n = this.getPaddingTop();
                break;
            }
            case 80: {
                n = this.getPaddingTop() + i - h - n;
                break;
            }
            case 16: {
                n2 = this.getPaddingTop();
                n = (i - h - n) / 2 + n2;
                break;
            }
        }
        final Drawable dividerDrawable = this.getDividerDrawable();
        if (dividerDrawable == null) {
            n2 = 0;
        }
        else {
            n2 = dividerDrawable.getIntrinsicHeight();
        }
        View child;
        int measuredWidth;
        int measuredHeight;
        a a;
        for (i = 0; i < childCount; ++i, n = h) {
            child = this.getChildAt(i);
            h = n;
            if (child != null) {
                h = n;
                if (child.getVisibility() != 8) {
                    measuredWidth = child.getMeasuredWidth();
                    measuredHeight = child.getMeasuredHeight();
                    a = (a)child.getLayoutParams();
                    if ((h = a.h) < 0) {
                        h = (gravity & 0x800007);
                    }
                    switch (android.support.v4.g.f.a(h, ae.c((View)this)) & 0x7) {
                        default: {
                            h = paddingLeft + a.leftMargin;
                            break;
                        }
                        case 1: {
                            h = (n3 - paddingLeft - paddingRight2 - measuredWidth) / 2 + paddingLeft + a.leftMargin - a.rightMargin;
                            break;
                        }
                        case 5: {
                            h = n3 - paddingRight - measuredWidth - a.rightMargin;
                            break;
                        }
                    }
                    if (this.c(i)) {
                        n += n2;
                    }
                    n += a.topMargin;
                    this.a(child, h, n, measuredWidth, measuredHeight);
                    h = n + (a.bottomMargin + measuredHeight);
                }
            }
        }
    }
    
    @Override
    protected void onMeasure(final int n, final int n2) {
        if (!this.c(n, n2)) {
            super.onMeasure(n, n2);
        }
    }
}
