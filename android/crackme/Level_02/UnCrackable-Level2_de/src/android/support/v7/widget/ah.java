// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.accessibility.AccessibilityNodeInfo;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Canvas;
import android.support.v4.g.f;
import android.support.v4.g.ae;
import android.view.View$MeasureSpec;
import android.view.View;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public class ah extends ViewGroup
{
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private boolean h;
    private int[] i;
    private int[] j;
    private Drawable k;
    private int l;
    private int m;
    private int n;
    private int o;
    
    public ah(final Context context) {
        this(context, null);
    }
    
    public ah(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ah(final Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.a = true;
        this.b = -1;
        this.c = 0;
        this.e = 8388659;
        final av a = av.a(context, set, android.support.v7.a.a.j.LinearLayoutCompat, n, 0);
        n = a.a(android.support.v7.a.a.j.LinearLayoutCompat_android_orientation, -1);
        if (n >= 0) {
            this.setOrientation(n);
        }
        n = a.a(android.support.v7.a.a.j.LinearLayoutCompat_android_gravity, -1);
        if (n >= 0) {
            this.setGravity(n);
        }
        final boolean a2 = a.a(android.support.v7.a.a.j.LinearLayoutCompat_android_baselineAligned, true);
        if (!a2) {
            this.setBaselineAligned(a2);
        }
        this.g = a.a(android.support.v7.a.a.j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.b = a.a(android.support.v7.a.a.j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.h = a.a(android.support.v7.a.a.j.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(a.a(android.support.v7.a.a.j.LinearLayoutCompat_divider));
        this.n = a.a(android.support.v7.a.a.j.LinearLayoutCompat_showDividers, 0);
        this.o = a.e(android.support.v7.a.a.j.LinearLayoutCompat_dividerPadding, 0);
        a.a();
    }
    
    private void a(final View view, final int n, final int n2, final int n3, final int n4) {
        view.layout(n, n2, n + n3, n2 + n4);
    }
    
    private void c(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View b = this.b(i);
            if (b.getVisibility() != 8) {
                final a a = (a)b.getLayoutParams();
                if (a.width == -1) {
                    final int height = a.height;
                    a.height = b.getMeasuredHeight();
                    this.measureChildWithMargins(b, measureSpec, 0, n2, 0);
                    a.height = height;
                }
            }
        }
    }
    
    private void d(final int n, final int n2) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);
        for (int i = 0; i < n; ++i) {
            final View b = this.b(i);
            if (b.getVisibility() != 8) {
                final a a = (a)b.getLayoutParams();
                if (a.height == -1) {
                    final int width = a.width;
                    a.width = b.getMeasuredWidth();
                    this.measureChildWithMargins(b, n2, 0, measureSpec, 0);
                    a.width = width;
                }
            }
        }
    }
    
    int a(final View view) {
        return 0;
    }
    
    int a(final View view, final int n) {
        return 0;
    }
    
    void a(final int n, final int n2) {
        this.f = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 1;
        float g = 0.0f;
        final int virtualChildCount = this.getVirtualChildCount();
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int n8 = 0;
        int n9 = 0;
        final int b = this.b;
        final boolean h = this.h;
        int n10 = Integer.MIN_VALUE;
        int n13;
        int n14;
        int n15;
        int n16;
        int n26;
        int n27;
        for (int i = 0; i < virtualChildCount; ++i, n26 = n14, n27 = n13, n10 = n16, n9 = n15, n4 = n26, n3 = n27) {
            final View b2 = this.b(i);
            if (b2 == null) {
                this.f += this.d(i);
                final int n11 = n10;
                final int n12 = n9;
                n13 = n3;
                n14 = n4;
                n15 = n12;
                n16 = n11;
            }
            else if (b2.getVisibility() == 8) {
                final int n17 = i + this.a(b2, i);
                final int n18 = n10;
                final int n19 = n9;
                n14 = n4;
                n13 = n3;
                n16 = n18;
                n15 = n19;
                i = n17;
            }
            else {
                if (this.c(i)) {
                    this.f += this.m;
                }
                final a a = (a)b2.getLayoutParams();
                g += a.g;
                int n20;
                int max;
                if (mode2 == 1073741824 && a.height == 0 && a.g > 0.0f) {
                    final int f = this.f;
                    this.f = Math.max(f, a.topMargin + f + a.bottomMargin);
                    n20 = 1;
                    max = n10;
                }
                else {
                    int height;
                    final int n21 = height = Integer.MIN_VALUE;
                    if (a.height == 0) {
                        height = n21;
                        if (a.g > 0.0f) {
                            height = 0;
                            a.height = -2;
                        }
                    }
                    int f2;
                    if (g == 0.0f) {
                        f2 = this.f;
                    }
                    else {
                        f2 = 0;
                    }
                    this.a(b2, i, n, 0, n2, f2);
                    if (height != Integer.MIN_VALUE) {
                        a.height = height;
                    }
                    final int measuredHeight = b2.getMeasuredHeight();
                    final int f3 = this.f;
                    this.f = Math.max(f3, f3 + measuredHeight + a.topMargin + a.bottomMargin + this.b(b2));
                    max = n10;
                    n20 = n9;
                    if (h) {
                        max = Math.max(measuredHeight, n10);
                        n20 = n9;
                    }
                }
                if (b >= 0 && b == i + 1) {
                    this.c = this.f;
                }
                if (i < b && a.g > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                }
                boolean b3 = false;
                int n22;
                if (mode != 1073741824 && a.width == -1) {
                    n22 = 1;
                    b3 = true;
                }
                else {
                    n22 = n8;
                }
                int n23 = a.rightMargin + a.leftMargin;
                final int n24 = b2.getMeasuredWidth() + n23;
                final int max2 = Math.max(n3, n24);
                final int a2 = ay.a(n4, ae.e(b2));
                if (n7 != 0 && a.width == -1) {
                    n7 = 1;
                }
                else {
                    n7 = 0;
                }
                int max3;
                int max4;
                if (a.g > 0.0f) {
                    if (!b3) {
                        n23 = n24;
                    }
                    max3 = Math.max(n6, n23);
                    max4 = n5;
                }
                else {
                    if (!b3) {
                        n23 = n24;
                    }
                    max4 = Math.max(n5, n23);
                    max3 = n6;
                }
                i += this.a(b2, i);
                n6 = max3;
                n5 = max4;
                n13 = max2;
                n16 = max;
                final int n25 = a2;
                n8 = n22;
                n15 = n20;
                n14 = n25;
            }
        }
        if (this.f > 0 && this.c(virtualChildCount)) {
            this.f += this.m;
        }
        if (h && (mode2 == Integer.MIN_VALUE || mode2 == 0)) {
            this.f = 0;
            for (int j = 0; j < virtualChildCount; ++j) {
                final View b4 = this.b(j);
                if (b4 == null) {
                    this.f += this.d(j);
                }
                else if (b4.getVisibility() == 8) {
                    j += this.a(b4, j);
                }
                else {
                    final a a3 = (a)b4.getLayoutParams();
                    final int f4 = this.f;
                    this.f = Math.max(f4, a3.bottomMargin + (f4 + n10 + a3.topMargin) + this.b(b4));
                }
            }
        }
        this.f += this.getPaddingTop() + this.getPaddingBottom();
        final int a4 = ae.a(Math.max(this.f, this.getSuggestedMinimumHeight()), n2, 0);
        final int n28 = (0xFFFFFF & a4) - this.f;
        int n33;
        int n52;
        if (n9 != 0 || (n28 != 0 && g > 0.0f)) {
            if (this.g > 0.0f) {
                g = this.g;
            }
            this.f = 0;
            final int n29 = 0;
            final int n30 = n7;
            int n31 = n5;
            final int n32 = n28;
            int k = n29;
            n33 = n3;
            int n34 = n30;
            int n35 = n32;
            while (k < virtualChildCount) {
                final View b5 = this.b(k);
                int n37;
                int max5;
                if (b5.getVisibility() == 8) {
                    final int n36 = n31;
                    n37 = n33;
                    max5 = n36;
                }
                else {
                    final a a5 = (a)b5.getLayoutParams();
                    final float g2 = a5.g;
                    int n42;
                    int n43;
                    if (g2 > 0.0f) {
                        final int n38 = (int)(n35 * g2 / g);
                        final int childMeasureSpec = getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight() + a5.leftMargin + a5.rightMargin, a5.width);
                        if (a5.height != 0 || mode2 != 1073741824) {
                            int n39;
                            if ((n39 = n38 + b5.getMeasuredHeight()) < 0) {
                                n39 = 0;
                            }
                            b5.measure(childMeasureSpec, View$MeasureSpec.makeMeasureSpec(n39, 1073741824));
                        }
                        else {
                            int n40;
                            if (n38 > 0) {
                                n40 = n38;
                            }
                            else {
                                n40 = 0;
                            }
                            b5.measure(childMeasureSpec, View$MeasureSpec.makeMeasureSpec(n40, 1073741824));
                        }
                        final int a6 = ay.a(n4, ae.e(b5) & 0xFFFFFF00);
                        final int n41 = n35 - n38;
                        n42 = a6;
                        g -= g2;
                        n43 = n41;
                    }
                    else {
                        final int n44 = n4;
                        n43 = n35;
                        n42 = n44;
                    }
                    final int n45 = a5.leftMargin + a5.rightMargin;
                    final int n46 = b5.getMeasuredWidth() + n45;
                    final int max6 = Math.max(n33, n46);
                    int n47;
                    if (mode != 1073741824 && a5.width == -1) {
                        n47 = 1;
                    }
                    else {
                        n47 = 0;
                    }
                    int n48;
                    if (n47 != 0) {
                        n48 = n45;
                    }
                    else {
                        n48 = n46;
                    }
                    max5 = Math.max(n31, n48);
                    if (n34 != 0 && a5.width == -1) {
                        n34 = 1;
                    }
                    else {
                        n34 = 0;
                    }
                    final int f5 = this.f;
                    this.f = Math.max(f5, a5.bottomMargin + (b5.getMeasuredHeight() + f5 + a5.topMargin) + this.b(b5));
                    n37 = max6;
                    final int n49 = n43;
                    n4 = n42;
                    n35 = n49;
                }
                ++k;
                final int n50 = n37;
                n31 = max5;
                n33 = n50;
            }
            this.f += this.getPaddingTop() + this.getPaddingBottom();
            final int n51 = n34;
            n52 = n31;
            n7 = n51;
        }
        else {
            final int max7 = Math.max(n5, n6);
            if (h && mode2 != 1073741824) {
                for (int l = 0; l < virtualChildCount; ++l) {
                    final View b6 = this.b(l);
                    if (b6 != null && b6.getVisibility() != 8 && ((a)b6.getLayoutParams()).g > 0.0f) {
                        b6.measure(View$MeasureSpec.makeMeasureSpec(b6.getMeasuredWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(n10, 1073741824));
                    }
                }
            }
            final int n53 = max7;
            final int n54 = n3;
            n52 = n53;
            n33 = n54;
        }
        if (n7 != 0 || mode == 1073741824) {
            n52 = n33;
        }
        this.setMeasuredDimension(ae.a(Math.max(n52 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), n, n4), a4);
        if (n8 != 0) {
            this.c(virtualChildCount, n2);
        }
    }
    
    void a(int i, int n, int n2, int h) {
        final int paddingLeft = this.getPaddingLeft();
        final int n3 = n2 - i;
        final int paddingRight = this.getPaddingRight();
        final int paddingRight2 = this.getPaddingRight();
        final int virtualChildCount = this.getVirtualChildCount();
        i = this.e;
        final int e = this.e;
        switch (i & 0x70) {
            default: {
                i = this.getPaddingTop();
                break;
            }
            case 80: {
                i = this.getPaddingTop() + h - n - this.f;
                break;
            }
            case 16: {
                i = this.getPaddingTop() + (h - n - this.f) / 2;
                break;
            }
        }
        n2 = 0;
        n = i;
        View b;
        int measuredWidth;
        int measuredHeight;
        a a;
        for (i = n2; i < virtualChildCount; ++i) {
            b = this.b(i);
            if (b == null) {
                n += this.d(i);
            }
            else if (b.getVisibility() != 8) {
                measuredWidth = b.getMeasuredWidth();
                measuredHeight = b.getMeasuredHeight();
                a = (a)b.getLayoutParams();
                h = a.h;
                if ((n2 = h) < 0) {
                    n2 = (e & 0x800007);
                }
                switch (android.support.v4.g.f.a(n2, ae.c((View)this)) & 0x7) {
                    default: {
                        n2 = paddingLeft + a.leftMargin;
                        break;
                    }
                    case 1: {
                        n2 = (n3 - paddingLeft - paddingRight2 - measuredWidth) / 2 + paddingLeft + a.leftMargin - a.rightMargin;
                        break;
                    }
                    case 5: {
                        n2 = n3 - paddingRight - measuredWidth - a.rightMargin;
                        break;
                    }
                }
                if (this.c(i)) {
                    n += this.m;
                }
                n += a.topMargin;
                this.a(b, n2, n + this.a(b), measuredWidth, measuredHeight);
                n += a.bottomMargin + measuredHeight + this.b(b);
                i += this.a(b, i);
            }
        }
    }
    
    void a(final Canvas canvas) {
        final int virtualChildCount = this.getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; ++i) {
            final View b = this.b(i);
            if (b != null && b.getVisibility() != 8 && this.c(i)) {
                this.a(canvas, b.getTop() - ((a)b.getLayoutParams()).topMargin - this.m);
            }
        }
        if (this.c(virtualChildCount)) {
            final View b2 = this.b(virtualChildCount - 1);
            int n;
            if (b2 == null) {
                n = this.getHeight() - this.getPaddingBottom() - this.m;
            }
            else {
                n = ((a)b2.getLayoutParams()).bottomMargin + b2.getBottom();
            }
            this.a(canvas, n);
        }
    }
    
    void a(final Canvas canvas, final int n) {
        this.k.setBounds(this.getPaddingLeft() + this.o, n, this.getWidth() - this.getPaddingRight() - this.o, this.m + n);
        this.k.draw(canvas);
    }
    
    void a(final View view, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.measureChildWithMargins(view, n2, n3, n4, n5);
    }
    
    int b(final View view) {
        return 0;
    }
    
    public a b(final AttributeSet set) {
        return new a(this.getContext(), set);
    }
    
    protected a b(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return new a(viewGroup$LayoutParams);
    }
    
    View b(final int n) {
        return this.getChildAt(n);
    }
    
    void b(final int n, final int n2) {
        this.f = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 1;
        float g = 0.0f;
        final int virtualChildCount = this.getVirtualChildCount();
        final int mode = View$MeasureSpec.getMode(n);
        final int mode2 = View$MeasureSpec.getMode(n2);
        int n8 = 0;
        int n9 = 0;
        if (this.i == null || this.j == null) {
            this.i = new int[4];
            this.j = new int[4];
        }
        final int[] i = this.i;
        final int[] j = this.j;
        i[2] = (i[3] = -1);
        i[0] = (i[1] = -1);
        j[2] = (j[3] = -1);
        j[0] = (j[1] = -1);
        final boolean a = this.a;
        final boolean h = this.h;
        final boolean b = mode == 1073741824;
        int n10 = Integer.MIN_VALUE;
        int n13;
        int n14;
        int n15;
        int n16;
        int n17;
        int n32;
        int n33;
        for (int k = 0; k < virtualChildCount; ++k, n32 = n14, n33 = n13, n10 = n17, n9 = n16, n7 = n15, n4 = n32, n3 = n33) {
            final View b2 = this.b(k);
            if (b2 == null) {
                this.f += this.d(k);
                final int n11 = n10;
                final int n12 = n9;
                n13 = n3;
                n14 = n4;
                n15 = n7;
                n16 = n12;
                n17 = n11;
            }
            else if (b2.getVisibility() == 8) {
                final int n18 = k + this.a(b2, k);
                final int n19 = n10;
                final int n20 = n9;
                final int n21 = n7;
                final int n22 = n4;
                final int n23 = n3;
                n17 = n19;
                n16 = n20;
                k = n18;
                n15 = n21;
                n14 = n22;
                n13 = n23;
            }
            else {
                if (this.c(k)) {
                    this.f += this.l;
                }
                final a a2 = (a)b2.getLayoutParams();
                g += a2.g;
                int n24;
                int max;
                if (mode == 1073741824 && a2.width == 0 && a2.g > 0.0f) {
                    if (b) {
                        this.f += a2.leftMargin + a2.rightMargin;
                    }
                    else {
                        final int f = this.f;
                        this.f = Math.max(f, a2.leftMargin + f + a2.rightMargin);
                    }
                    if (a) {
                        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
                        b2.measure(measureSpec, measureSpec);
                        n24 = n9;
                        max = n10;
                    }
                    else {
                        n24 = 1;
                        max = n10;
                    }
                }
                else {
                    int width;
                    final int n25 = width = Integer.MIN_VALUE;
                    if (a2.width == 0) {
                        width = n25;
                        if (a2.g > 0.0f) {
                            width = 0;
                            a2.width = -2;
                        }
                    }
                    int f2;
                    if (g == 0.0f) {
                        f2 = this.f;
                    }
                    else {
                        f2 = 0;
                    }
                    this.a(b2, k, n, f2, n2, 0);
                    if (width != Integer.MIN_VALUE) {
                        a2.width = width;
                    }
                    final int measuredWidth = b2.getMeasuredWidth();
                    if (b) {
                        this.f += a2.leftMargin + measuredWidth + a2.rightMargin + this.b(b2);
                    }
                    else {
                        final int f3 = this.f;
                        this.f = Math.max(f3, f3 + measuredWidth + a2.leftMargin + a2.rightMargin + this.b(b2));
                    }
                    max = n10;
                    n24 = n9;
                    if (h) {
                        max = Math.max(measuredWidth, n10);
                        n24 = n9;
                    }
                }
                boolean b3 = false;
                int n26;
                if (mode2 != 1073741824 && a2.height == -1) {
                    n26 = 1;
                    b3 = true;
                }
                else {
                    n26 = n8;
                }
                int n27 = a2.bottomMargin + a2.topMargin;
                final int n28 = b2.getMeasuredHeight() + n27;
                final int a3 = ay.a(n4, ae.e(b2));
                if (a) {
                    final int baseline = b2.getBaseline();
                    if (baseline != -1) {
                        int n29;
                        if (a2.h < 0) {
                            n29 = this.e;
                        }
                        else {
                            n29 = a2.h;
                        }
                        final int n30 = ((n29 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
                        i[n30] = Math.max(i[n30], baseline);
                        j[n30] = Math.max(j[n30], n28 - baseline);
                    }
                }
                final int max2 = Math.max(n3, n28);
                if (n7 != 0 && a2.height == -1) {
                    n15 = 1;
                }
                else {
                    n15 = 0;
                }
                int max3;
                int max4;
                if (a2.g > 0.0f) {
                    if (!b3) {
                        n27 = n28;
                    }
                    max3 = Math.max(n6, n27);
                    max4 = n5;
                }
                else {
                    if (!b3) {
                        n27 = n28;
                    }
                    max4 = Math.max(n5, n27);
                    max3 = n6;
                }
                k += this.a(b2, k);
                n6 = max3;
                n5 = max4;
                n13 = max2;
                n17 = max;
                final int n31 = a3;
                n8 = n26;
                n16 = n24;
                n14 = n31;
            }
        }
        if (this.f > 0 && this.c(virtualChildCount)) {
            this.f += this.l;
        }
        int max5;
        if (i[1] != -1 || i[0] != -1 || i[2] != -1 || i[3] != -1) {
            max5 = Math.max(n3, Math.max(i[3], Math.max(i[0], Math.max(i[1], i[2]))) + Math.max(j[3], Math.max(j[0], Math.max(j[1], j[2]))));
        }
        else {
            max5 = n3;
        }
        if (h && (mode == Integer.MIN_VALUE || mode == 0)) {
            this.f = 0;
            for (int l = 0; l < virtualChildCount; ++l) {
                final View b4 = this.b(l);
                if (b4 == null) {
                    this.f += this.d(l);
                }
                else if (b4.getVisibility() == 8) {
                    l += this.a(b4, l);
                }
                else {
                    final a a4 = (a)b4.getLayoutParams();
                    if (b) {
                        this.f += a4.rightMargin + (a4.leftMargin + n10) + this.b(b4);
                    }
                    else {
                        final int f4 = this.f;
                        this.f = Math.max(f4, a4.rightMargin + (f4 + n10 + a4.leftMargin) + this.b(b4));
                    }
                }
            }
        }
        this.f += this.getPaddingLeft() + this.getPaddingRight();
        final int a5 = ae.a(Math.max(this.f, this.getSuggestedMinimumWidth()), n, 0);
        final int n34 = (0xFFFFFF & a5) - this.f;
        int max8;
        int n60;
        int n61;
        int n62;
        if (n9 != 0 || (n34 != 0 && g > 0.0f)) {
            if (this.g > 0.0f) {
                g = this.g;
            }
            i[2] = (i[3] = -1);
            i[0] = (i[1] = -1);
            j[2] = (j[3] = -1);
            j[0] = (j[1] = -1);
            this.f = 0;
            final int n35 = 0;
            final int n36 = n5;
            int a6 = n4;
            int n37 = n34;
            int n38 = -1;
            int n39 = n35;
            int n40 = n36;
            while (n39 < virtualChildCount) {
                final View b5 = this.b(n39);
                int n42;
                int max6;
                int n43;
                int n44;
                if (b5 != null) {
                    if (b5.getVisibility() == 8) {
                        final int n41 = n37;
                        n42 = n38;
                        max6 = n40;
                        n43 = n7;
                        n44 = n41;
                    }
                    else {
                        final a a7 = (a)b5.getLayoutParams();
                        final float g2 = a7.g;
                        if (g2 > 0.0f) {
                            final int n45 = (int)(n37 * g2 / g);
                            final int n46 = n37 - n45;
                            final int childMeasureSpec = getChildMeasureSpec(n2, this.getPaddingTop() + this.getPaddingBottom() + a7.topMargin + a7.bottomMargin, a7.height);
                            if (a7.width != 0 || mode != 1073741824) {
                                int n47;
                                if ((n47 = n45 + b5.getMeasuredWidth()) < 0) {
                                    n47 = 0;
                                }
                                b5.measure(View$MeasureSpec.makeMeasureSpec(n47, 1073741824), childMeasureSpec);
                            }
                            else {
                                int n48;
                                if (n45 > 0) {
                                    n48 = n45;
                                }
                                else {
                                    n48 = 0;
                                }
                                b5.measure(View$MeasureSpec.makeMeasureSpec(n48, 1073741824), childMeasureSpec);
                            }
                            a6 = ay.a(a6, ae.e(b5) & 0xFF000000);
                            g -= g2;
                            n37 = n46;
                        }
                        if (b) {
                            this.f += b5.getMeasuredWidth() + a7.leftMargin + a7.rightMargin + this.b(b5);
                        }
                        else {
                            final int f5 = this.f;
                            this.f = Math.max(f5, b5.getMeasuredWidth() + f5 + a7.leftMargin + a7.rightMargin + this.b(b5));
                        }
                        int n49;
                        if (mode2 != 1073741824 && a7.height == -1) {
                            n49 = 1;
                        }
                        else {
                            n49 = 0;
                        }
                        final int n50 = a7.topMargin + a7.bottomMargin;
                        final int n51 = b5.getMeasuredHeight() + n50;
                        final int max7 = Math.max(n38, n51);
                        int n52;
                        if (n49 != 0) {
                            n52 = n50;
                        }
                        else {
                            n52 = n51;
                        }
                        max6 = Math.max(n40, n52);
                        int n53;
                        if (n7 != 0 && a7.height == -1) {
                            n53 = 1;
                        }
                        else {
                            n53 = 0;
                        }
                        if (a) {
                            final int baseline2 = b5.getBaseline();
                            if (baseline2 != -1) {
                                int n54;
                                if (a7.h < 0) {
                                    n54 = this.e;
                                }
                                else {
                                    n54 = a7.h;
                                }
                                final int n55 = ((n54 & 0x70) >> 4 & 0xFFFFFFFE) >> 1;
                                i[n55] = Math.max(i[n55], baseline2);
                                j[n55] = Math.max(j[n55], n51 - baseline2);
                            }
                        }
                        n43 = n53;
                        n44 = n37;
                        n42 = max7;
                    }
                }
                else {
                    final int n56 = n37;
                    n42 = n38;
                    max6 = n40;
                    n43 = n7;
                    n44 = n56;
                }
                final int n57 = n39 + 1;
                final int n58 = n42;
                n37 = n44;
                n7 = n43;
                n40 = max6;
                n38 = n58;
                n39 = n57;
            }
            this.f += this.getPaddingLeft() + this.getPaddingRight();
            Label_2120: {
                if (i[1] == -1 && i[0] == -1 && i[2] == -1) {
                    max8 = n38;
                    if (i[3] == -1) {
                        break Label_2120;
                    }
                }
                max8 = Math.max(n38, Math.max(i[3], Math.max(i[0], Math.max(i[1], i[2]))) + Math.max(j[3], Math.max(j[0], Math.max(j[1], j[2]))));
            }
            final int n59 = n7;
            n60 = a6;
            n61 = n59;
            n62 = n40;
        }
        else {
            final int max9 = Math.max(n5, n6);
            if (h && mode != 1073741824) {
                for (int n63 = 0; n63 < virtualChildCount; ++n63) {
                    final View b6 = this.b(n63);
                    if (b6 != null && b6.getVisibility() != 8 && ((a)b6.getLayoutParams()).g > 0.0f) {
                        b6.measure(View$MeasureSpec.makeMeasureSpec(n10, 1073741824), View$MeasureSpec.makeMeasureSpec(b6.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            n62 = max9;
            max8 = max5;
            n61 = n7;
            n60 = n4;
        }
        if (n61 != 0 || mode2 == 1073741824) {
            n62 = max8;
        }
        this.setMeasuredDimension((0xFF000000 & n60) | a5, ae.a(Math.max(n62 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), n2, n60 << 16));
        if (n8 != 0) {
            this.d(virtualChildCount, n);
        }
    }
    
    void b(int n, int i, int n2, int n3) {
        final boolean a = ay.a((View)this);
        final int paddingTop = this.getPaddingTop();
        final int n4 = n3 - i;
        final int paddingBottom = this.getPaddingBottom();
        final int paddingBottom2 = this.getPaddingBottom();
        final int virtualChildCount = this.getVirtualChildCount();
        i = this.e;
        final int e = this.e;
        final boolean a2 = this.a;
        final int[] j = this.i;
        final int[] k = this.j;
        switch (android.support.v4.g.f.a(i & 0x800007, ae.c((View)this))) {
            default: {
                n = this.getPaddingLeft();
                break;
            }
            case 5: {
                n = this.getPaddingLeft() + n2 - n - this.f;
                break;
            }
            case 1: {
                n = this.getPaddingLeft() + (n2 - n - this.f) / 2;
                break;
            }
        }
        int n5;
        if (a) {
            n3 = -1;
            n5 = virtualChildCount - 1;
        }
        else {
            n3 = 1;
            n5 = 0;
        }
        i = 0;
        n2 = n;
        while (i < virtualChildCount) {
            final int n6 = n5 + n3 * i;
            final View b = this.b(n6);
            if (b == null) {
                n2 += this.d(n6);
                n = i;
            }
            else if (b.getVisibility() != 8) {
                final int measuredWidth = b.getMeasuredWidth();
                final int measuredHeight = b.getMeasuredHeight();
                final a a3 = (a)b.getLayoutParams();
                int baseline;
                if (a2 && a3.height != -1) {
                    baseline = b.getBaseline();
                }
                else {
                    baseline = -1;
                }
                if ((n = a3.h) < 0) {
                    n = (e & 0x70);
                }
                switch (n & 0x70) {
                    default: {
                        n = paddingTop;
                        break;
                    }
                    case 48: {
                        final int n7 = n = paddingTop + a3.topMargin;
                        if (baseline != -1) {
                            n = n7 + (j[1] - baseline);
                            break;
                        }
                        break;
                    }
                    case 16: {
                        n = (n4 - paddingTop - paddingBottom2 - measuredHeight) / 2 + paddingTop + a3.topMargin - a3.bottomMargin;
                        break;
                    }
                    case 80: {
                        final int n8 = n = n4 - paddingBottom - measuredHeight - a3.bottomMargin;
                        if (baseline != -1) {
                            n = b.getMeasuredHeight();
                            n = n8 - (k[2] - (n - baseline));
                            break;
                        }
                        break;
                    }
                }
                if (this.c(n6)) {
                    n2 += this.l;
                }
                n2 += a3.leftMargin;
                this.a(b, n2 + this.a(b), n, measuredWidth, measuredHeight);
                n2 += a3.rightMargin + measuredWidth + this.b(b);
                n = this.a(b, n6) + i;
            }
            else {
                n = i;
            }
            i = n + 1;
        }
    }
    
    void b(final Canvas canvas) {
        final int virtualChildCount = this.getVirtualChildCount();
        final boolean a = ay.a((View)this);
        for (int i = 0; i < virtualChildCount; ++i) {
            final View b = this.b(i);
            if (b != null && b.getVisibility() != 8 && this.c(i)) {
                final a a2 = (a)b.getLayoutParams();
                int n;
                if (a) {
                    n = a2.rightMargin + b.getRight();
                }
                else {
                    n = b.getLeft() - a2.leftMargin - this.l;
                }
                this.b(canvas, n);
            }
        }
        if (this.c(virtualChildCount)) {
            final View b2 = this.b(virtualChildCount - 1);
            int paddingLeft;
            if (b2 == null) {
                if (a) {
                    paddingLeft = this.getPaddingLeft();
                }
                else {
                    paddingLeft = this.getWidth() - this.getPaddingRight() - this.l;
                }
            }
            else {
                final a a3 = (a)b2.getLayoutParams();
                if (a) {
                    paddingLeft = b2.getLeft() - a3.leftMargin - this.l;
                }
                else {
                    paddingLeft = a3.rightMargin + b2.getRight();
                }
            }
            this.b(canvas, paddingLeft);
        }
    }
    
    void b(final Canvas canvas, final int n) {
        this.k.setBounds(n, this.getPaddingTop() + this.o, this.l + n, this.getHeight() - this.getPaddingBottom() - this.o);
        this.k.draw(canvas);
    }
    
    protected boolean c(int i) {
        if (i == 0) {
            if ((this.n & 0x1) == 0x0) {
                return false;
            }
        }
        else if (i == this.getChildCount()) {
            if ((this.n & 0x4) == 0x0) {
                return false;
            }
        }
        else {
            if ((this.n & 0x2) != 0x0) {
                for (--i; i >= 0; --i) {
                    if (this.getChildAt(i).getVisibility() != 8) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof a;
    }
    
    int d(final int n) {
        return 0;
    }
    
    public int getBaseline() {
        int baseline = -1;
        if (this.b < 0) {
            baseline = super.getBaseline();
        }
        else {
            if (this.getChildCount() <= this.b) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
            }
            final View child = this.getChildAt(this.b);
            final int baseline2 = child.getBaseline();
            if (baseline2 != -1) {
                int c = this.c;
                if (this.d == 1) {
                    final int n = this.e & 0x70;
                    if (n != 48) {
                        switch (n) {
                            case 80: {
                                c = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.f;
                                break;
                            }
                            case 16: {
                                c += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.f) / 2;
                                break;
                            }
                        }
                    }
                }
                return ((a)child.getLayoutParams()).topMargin + c + baseline2;
            }
            if (this.b != 0) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        return baseline;
    }
    
    public int getBaselineAlignedChildIndex() {
        return this.b;
    }
    
    public Drawable getDividerDrawable() {
        return this.k;
    }
    
    public int getDividerPadding() {
        return this.o;
    }
    
    public int getDividerWidth() {
        return this.l;
    }
    
    public int getGravity() {
        return this.e;
    }
    
    public int getOrientation() {
        return this.d;
    }
    
    public int getShowDividers() {
        return this.n;
    }
    
    int getVirtualChildCount() {
        return this.getChildCount();
    }
    
    public float getWeightSum() {
        return this.g;
    }
    
    protected a j() {
        if (this.d == 0) {
            return new a(-2, -2);
        }
        if (this.d == 1) {
            return new a(-1, -2);
        }
        return null;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.k == null) {
            return;
        }
        if (this.d == 1) {
            this.a(canvas);
            return;
        }
        this.b(canvas);
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        if (Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)ah.class.getName());
        }
    }
    
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName((CharSequence)ah.class.getName());
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (this.d == 1) {
            this.a(n, n2, n3, n4);
            return;
        }
        this.b(n, n2, n3, n4);
    }
    
    protected void onMeasure(final int n, final int n2) {
        if (this.d == 1) {
            this.a(n, n2);
            return;
        }
        this.b(n, n2);
    }
    
    public void setBaselineAligned(final boolean a) {
        this.a = a;
    }
    
    public void setBaselineAlignedChildIndex(final int b) {
        if (b < 0 || b >= this.getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
        }
        this.b = b;
    }
    
    public void setDividerDrawable(final Drawable k) {
        boolean willNotDraw = false;
        if (k == this.k) {
            return;
        }
        if ((this.k = k) != null) {
            this.l = k.getIntrinsicWidth();
            this.m = k.getIntrinsicHeight();
        }
        else {
            this.l = 0;
            this.m = 0;
        }
        if (k == null) {
            willNotDraw = true;
        }
        this.setWillNotDraw(willNotDraw);
        this.requestLayout();
    }
    
    public void setDividerPadding(final int o) {
        this.o = o;
    }
    
    public void setGravity(int n) {
        if (this.e != n) {
            if ((0x800007 & n) == 0x0) {
                n |= 0x800003;
            }
            int e = n;
            if ((n & 0x70) == 0x0) {
                e = (n | 0x30);
            }
            this.e = e;
            this.requestLayout();
        }
    }
    
    public void setHorizontalGravity(int n) {
        n &= 0x800007;
        if ((this.e & 0x800007) != n) {
            this.e = (n | (this.e & 0xFF7FFFF8));
            this.requestLayout();
        }
    }
    
    public void setMeasureWithLargestChildEnabled(final boolean h) {
        this.h = h;
    }
    
    public void setOrientation(final int d) {
        if (this.d != d) {
            this.d = d;
            this.requestLayout();
        }
    }
    
    public void setShowDividers(final int n) {
        if (n != this.n) {
            this.requestLayout();
        }
        this.n = n;
    }
    
    public void setVerticalGravity(int n) {
        n &= 0x70;
        if ((this.e & 0x70) != n) {
            this.e = (n | (this.e & 0xFFFFFF8F));
            this.requestLayout();
        }
    }
    
    public void setWeightSum(final float n) {
        this.g = Math.max(0.0f, n);
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    public static class a extends ViewGroup$MarginLayoutParams
    {
        public float g;
        public int h;
        
        public a(final int n, final int n2) {
            super(n, n2);
            this.h = -1;
            this.g = 0.0f;
        }
        
        public a(final Context context, final AttributeSet set) {
            super(context, set);
            this.h = -1;
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.LinearLayoutCompat_Layout);
            this.g = obtainStyledAttributes.getFloat(android.support.v7.a.a.j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.h = obtainStyledAttributes.getInt(android.support.v7.a.a.j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }
        
        public a(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.h = -1;
        }
    }
}
