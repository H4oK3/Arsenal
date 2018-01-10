// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewDebug$ExportedProperty;
import android.view.ContextThemeWrapper;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.accessibility.AccessibilityEvent;
import android.view.MenuItem;
import android.support.v7.view.menu.j;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View$MeasureSpec;
import android.view.View;
import android.util.AttributeSet;
import android.support.v7.view.menu.o;
import android.content.Context;
import android.support.v7.view.menu.p;
import android.support.v7.view.menu.h;

public class ActionMenuView extends ah implements h.b, p
{
    h.a a;
    e b;
    private h c;
    private Context d;
    private int e;
    private boolean f;
    private android.support.v7.widget.d g;
    private o.a h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    
    public ActionMenuView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuView(final Context d, final AttributeSet set) {
        super(d, set);
        this.setBaselineAligned(false);
        final float density = d.getResources().getDisplayMetrics().density;
        this.k = (int)(56.0f * density);
        this.l = (int)(density * 4.0f);
        this.d = d;
        this.e = 0;
    }
    
    static int a(final View view, final int n, int n2, int b, int n3) {
        final boolean b2 = false;
        final c c = (c)view.getLayoutParams();
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(b) - n3, View$MeasureSpec.getMode(b));
        ActionMenuItemView actionMenuItemView;
        if (view instanceof ActionMenuItemView) {
            actionMenuItemView = (ActionMenuItemView)view;
        }
        else {
            actionMenuItemView = null;
        }
        if (actionMenuItemView != null && actionMenuItemView.b()) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        if (n2 > 0 && (n3 == 0 || n2 >= 2)) {
            view.measure(View$MeasureSpec.makeMeasureSpec(n * n2, Integer.MIN_VALUE), measureSpec);
            final int measuredWidth = view.getMeasuredWidth();
            b = (n2 = measuredWidth / n);
            if (measuredWidth % n != 0) {
                n2 = b + 1;
            }
            b = n2;
            if (n3 != 0 && (b = n2) < 2) {
                b = 2;
            }
        }
        else {
            b = 0;
        }
        boolean d = b2;
        if (!c.a) {
            d = b2;
            if (n3 != 0) {
                d = true;
            }
        }
        c.d = d;
        c.b = b;
        view.measure(View$MeasureSpec.makeMeasureSpec(b * n, 1073741824), measureSpec);
        return b;
    }
    
    private void c(int i, int n) {
        final int mode = View$MeasureSpec.getMode(n);
        i = View$MeasureSpec.getSize(i);
        final int size = View$MeasureSpec.getSize(n);
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int n2 = this.getPaddingTop() + this.getPaddingBottom();
        final int childMeasureSpec = getChildMeasureSpec(n, n2, -2);
        final int n3 = i - (paddingLeft + paddingRight);
        i = n3 / this.k;
        n = this.k;
        if (i == 0) {
            this.setMeasuredDimension(n3, 0);
            return;
        }
        final int n4 = this.k + n3 % n / i;
        int max = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        long n9 = 0L;
        final int childCount = this.getChildCount();
        int n10;
        for (int j = 0; j < childCount; ++j, n7 = i, i = n10, n5 = n) {
            final View child = this.getChildAt(j);
            if (child.getVisibility() == 8) {
                n = n5;
                n10 = i;
                i = n7;
            }
            else {
                final boolean b = child instanceof ActionMenuItemView;
                final int n11 = n7 + 1;
                if (b) {
                    child.setPadding(this.l, 0, this.l, 0);
                }
                final c c = (c)child.getLayoutParams();
                c.f = false;
                c.c = 0;
                c.b = 0;
                c.d = false;
                c.leftMargin = 0;
                c.rightMargin = 0;
                c.e = (b && ((ActionMenuItemView)child).b());
                if (c.a) {
                    n = 1;
                }
                else {
                    n = i;
                }
                final int a = a(child, n4, n, childMeasureSpec, n2);
                final int max2 = Math.max(n5, a);
                if (c.d) {
                    n = n6 + 1;
                }
                else {
                    n = n6;
                }
                int n12;
                if (c.a) {
                    n12 = 1;
                }
                else {
                    n12 = n8;
                }
                final int n13 = i - a;
                max = Math.max(max, child.getMeasuredHeight());
                if (a == 1) {
                    final long n14 = 1 << j;
                    n6 = n;
                    n9 |= n14;
                    n = max2;
                    i = n11;
                    n8 = n12;
                    n10 = n13;
                }
                else {
                    i = n11;
                    final int n15 = max2;
                    final int n16 = n13;
                    n6 = n;
                    n = n15;
                    n8 = n12;
                    n10 = n16;
                }
            }
        }
        final boolean b2 = n8 != 0 && n7 == 2;
        int n17 = 0;
        while (true) {
            for (int n18 = i; n6 > 0 && n18 > 0; n18 = n) {
                i = Integer.MAX_VALUE;
                long n19 = 0L;
                n = 0;
                int n23;
                for (int k = 0; k < childCount; k = n23) {
                    final c c2 = (c)this.getChildAt(k).getLayoutParams();
                    if (!c2.d) {
                        final int n20 = n;
                        n = i;
                        i = n20;
                    }
                    else if (c2.b < i) {
                        n = c2.b;
                        n19 = 1 << k;
                        i = 1;
                    }
                    else if (c2.b == i) {
                        n19 |= 1 << k;
                        final int n21 = n + 1;
                        n = i;
                        i = n21;
                    }
                    else {
                        final int n22 = i;
                        i = n;
                        n = n22;
                    }
                    n23 = k + 1;
                    final int n24 = n;
                    n = i;
                    i = n24;
                }
                n9 |= n19;
                if (n > n18) {
                    if (n8 == 0 && n7 == 1) {
                        i = 1;
                    }
                    else {
                        i = 0;
                    }
                    int n31 = 0;
                    Label_1076: {
                        if (n18 > 0 && n9 != 0L && (n18 < n7 - 1 || i != 0 || n5 > 1)) {
                            float n26;
                            final float n25 = n26 = Long.bitCount(n9);
                            while (true) {
                                Label_1175: {
                                    if (i != 0) {
                                        break Label_1175;
                                    }
                                    float n27 = n25;
                                    if ((0x1L & n9) != 0x0L) {
                                        n27 = n25;
                                        if (!((c)this.getChildAt(0).getLayoutParams()).e) {
                                            n27 = n25 - 0.5f;
                                        }
                                    }
                                    n26 = n27;
                                    if ((1 << childCount - 1 & n9) == 0x0L) {
                                        break Label_1175;
                                    }
                                    n26 = n27;
                                    if (((c)this.getChildAt(childCount - 1).getLayoutParams()).e) {
                                        break Label_1175;
                                    }
                                    final float n28 = n27 - 0.5f;
                                    if (n28 > 0.0f) {
                                        n = (int)(n18 * n4 / n28);
                                    }
                                    else {
                                        n = 0;
                                    }
                                    final int n29 = 0;
                                    i = n17;
                                    int n30 = n29;
                                    while (true) {
                                        n31 = i;
                                        if (n30 >= childCount) {
                                            break Label_1076;
                                        }
                                        if ((1 << n30 & n9) != 0x0L) {
                                            final View child2 = this.getChildAt(n30);
                                            final c c3 = (c)child2.getLayoutParams();
                                            if (child2 instanceof ActionMenuItemView) {
                                                c3.c = n;
                                                c3.f = true;
                                                if (n30 == 0 && !c3.e) {
                                                    c3.leftMargin = -n / 2;
                                                }
                                                i = 1;
                                            }
                                            else if (c3.a) {
                                                c3.c = n;
                                                c3.f = true;
                                                c3.rightMargin = -n / 2;
                                                i = 1;
                                            }
                                            else {
                                                if (n30 != 0) {
                                                    c3.leftMargin = n / 2;
                                                }
                                                if (n30 != childCount - 1) {
                                                    c3.rightMargin = n / 2;
                                                }
                                            }
                                        }
                                        ++n30;
                                    }
                                }
                                final float n28 = n26;
                                continue;
                            }
                        }
                        n31 = n17;
                    }
                    if (n31 != 0) {
                        View child3;
                        c c4;
                        for (i = 0; i < childCount; ++i) {
                            child3 = this.getChildAt(i);
                            c4 = (c)child3.getLayoutParams();
                            if (c4.f) {
                                n = c4.b;
                                child3.measure(View$MeasureSpec.makeMeasureSpec(c4.c + n * n4, 1073741824), childMeasureSpec);
                            }
                        }
                    }
                    if (mode == 1073741824) {
                        max = size;
                    }
                    this.setMeasuredDimension(n3, max);
                    return;
                }
                int l = 0;
                n = n18;
                while (l < childCount) {
                    final View child4 = this.getChildAt(l);
                    final c c5 = (c)child4.getLayoutParams();
                    if ((1 << l & n19) == 0x0L) {
                        if (c5.b == i + 1) {
                            n9 |= 1 << l;
                        }
                    }
                    else {
                        if (b2 && c5.e && n == 1) {
                            child4.setPadding(this.l + n4, 0, this.l, 0);
                        }
                        ++c5.b;
                        c5.f = true;
                        --n;
                    }
                    ++l;
                }
                n17 = 1;
            }
            continue;
        }
    }
    
    public c a(final AttributeSet set) {
        return new c(this.getContext(), set);
    }
    
    protected c a(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams != null) {
            c c;
            if (viewGroup$LayoutParams instanceof c) {
                c = new c((c)viewGroup$LayoutParams);
            }
            else {
                c = new c(viewGroup$LayoutParams);
            }
            if (c.h <= 0) {
                c.h = 16;
            }
            return c;
        }
        return this.b();
    }
    
    @Override
    public void a(final h c) {
        this.c = c;
    }
    
    public void a(final o.a h, final h.a a) {
        this.h = h;
        this.a = a;
    }
    
    public boolean a() {
        return this.f;
    }
    
    protected boolean a(final int n) {
        final boolean b = false;
        if (n == 0) {
            return false;
        }
        final View child = this.getChildAt(n - 1);
        final View child2 = this.getChildAt(n);
        boolean b2 = b;
        if (n < this.getChildCount()) {
            b2 = b;
            if (child instanceof a) {
                b2 = (false | ((a)child).d());
            }
        }
        if (n > 0 && child2 instanceof a) {
            return ((a)child2).c() | b2;
        }
        return b2;
    }
    
    @Override
    public boolean a(final j j) {
        return this.c.a((MenuItem)j, 0);
    }
    
    protected c b() {
        final c c = new c(-2, -2);
        c.h = 16;
        return c;
    }
    
    public c c() {
        final c b = this.b();
        b.a = true;
        return b;
    }
    
    @Override
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams != null && viewGroup$LayoutParams instanceof c;
    }
    
    public h d() {
        return this.c;
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        return false;
    }
    
    public boolean e() {
        return this.g != null && this.g.d();
    }
    
    public boolean f() {
        return this.g != null && this.g.e();
    }
    
    public boolean g() {
        return this.g != null && this.g.h();
    }
    
    public Menu getMenu() {
        if (this.c == null) {
            final Context context = this.getContext();
            (this.c = new h(context)).a((h.a)new d());
            (this.g = new android.support.v7.widget.d(context)).c(true);
            final android.support.v7.widget.d g = this.g;
            o.a h;
            if (this.h != null) {
                h = this.h;
            }
            else {
                h = new b();
            }
            g.a(h);
            this.c.a(this.g, this.d);
            this.g.a(this);
        }
        return (Menu)this.c;
    }
    
    public Drawable getOverflowIcon() {
        this.getMenu();
        return this.g.c();
    }
    
    public int getPopupTheme() {
        return this.e;
    }
    
    public int getWindowAnimations() {
        return 0;
    }
    
    public boolean h() {
        return this.g != null && this.g.i();
    }
    
    public void i() {
        if (this.g != null) {
            this.g.f();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.g != null) {
            this.g.b(false);
            if (this.g.h()) {
                this.g.e();
                this.g.d();
            }
        }
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i();
    }
    
    @Override
    protected void onLayout(final boolean b, int paddingLeft, int i, int max, int n) {
        if (!this.i) {
            super.onLayout(b, paddingLeft, i, max, n);
        }
        else {
            final int childCount = this.getChildCount();
            final int n2 = (n - i) / 2;
            final int dividerWidth = this.getDividerWidth();
            n = 0;
            i = 0;
            int n3 = max - paddingLeft - this.getPaddingRight() - this.getPaddingLeft();
            int n4 = 0;
            final boolean a = ay.a((View)this);
            int n19;
            for (int j = 0; j < childCount; j = n19) {
                final View child = this.getChildAt(j);
                int n7;
                int n8;
                if (child.getVisibility() == 8) {
                    final int n5 = n4;
                    final int n6 = n3;
                    n7 = n;
                    n8 = i;
                    n = n6;
                    i = n5;
                }
                else {
                    final c c = (c)child.getLayoutParams();
                    if (c.a) {
                        int measuredWidth;
                        final int n9 = measuredWidth = child.getMeasuredWidth();
                        if (this.a(j)) {
                            measuredWidth = n9 + dividerWidth;
                        }
                        final int measuredHeight = child.getMeasuredHeight();
                        int n10;
                        int n11;
                        if (a) {
                            n10 = c.leftMargin + this.getPaddingLeft();
                            n11 = n10 + measuredWidth;
                        }
                        else {
                            n11 = this.getWidth() - this.getPaddingRight() - c.rightMargin;
                            n10 = n11 - measuredWidth;
                        }
                        final int n12 = n2 - measuredHeight / 2;
                        child.layout(n10, n12, n11, measuredHeight + n12);
                        final int n13 = n3 - measuredWidth;
                        final int n14 = 1;
                        n8 = i;
                        n7 = n;
                        i = n14;
                        n = n13;
                    }
                    else {
                        final int n15 = child.getMeasuredWidth() + c.leftMargin + c.rightMargin;
                        final int n16 = n += n15;
                        if (this.a(j)) {
                            n = n16 + dividerWidth;
                        }
                        final int n17 = n3 - n15;
                        final int n18 = i + 1;
                        n7 = n;
                        i = n4;
                        n = n17;
                        n8 = n18;
                    }
                }
                n19 = j + 1;
                final int n20 = n7;
                final int n21 = n8;
                n4 = i;
                n3 = n;
                i = n21;
                n = n20;
            }
            if (childCount == 1 && n4 == 0) {
                final View child2 = this.getChildAt(0);
                i = child2.getMeasuredWidth();
                n = child2.getMeasuredHeight();
                paddingLeft = (max - paddingLeft) / 2 - i / 2;
                max = n2 - n / 2;
                child2.layout(paddingLeft, max, i + paddingLeft, n + max);
                return;
            }
            if (n4 != 0) {
                paddingLeft = 0;
            }
            else {
                paddingLeft = 1;
            }
            paddingLeft = i - paddingLeft;
            if (paddingLeft > 0) {
                paddingLeft = n3 / paddingLeft;
            }
            else {
                paddingLeft = 0;
            }
            max = Math.max(0, paddingLeft);
            if (a) {
                paddingLeft = this.getWidth() - this.getPaddingRight();
                View child3;
                c c2;
                int measuredHeight2;
                int n22;
                for (i = 0; i < childCount; ++i) {
                    child3 = this.getChildAt(i);
                    c2 = (c)child3.getLayoutParams();
                    if (child3.getVisibility() != 8) {
                        if (!c2.a) {
                            paddingLeft -= c2.rightMargin;
                            n = child3.getMeasuredWidth();
                            measuredHeight2 = child3.getMeasuredHeight();
                            n22 = n2 - measuredHeight2 / 2;
                            child3.layout(paddingLeft - n, n22, paddingLeft, measuredHeight2 + n22);
                            paddingLeft -= c2.leftMargin + n + max;
                        }
                    }
                }
            }
            else {
                paddingLeft = this.getPaddingLeft();
                View child4;
                c c3;
                int measuredHeight3;
                int n23;
                for (i = 0; i < childCount; ++i) {
                    child4 = this.getChildAt(i);
                    c3 = (c)child4.getLayoutParams();
                    if (child4.getVisibility() != 8) {
                        if (!c3.a) {
                            paddingLeft += c3.leftMargin;
                            n = child4.getMeasuredWidth();
                            measuredHeight3 = child4.getMeasuredHeight();
                            n23 = n2 - measuredHeight3 / 2;
                            child4.layout(paddingLeft, n23, paddingLeft + n, measuredHeight3 + n23);
                            paddingLeft += c3.rightMargin + n + max;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    protected void onMeasure(final int n, final int n2) {
        final boolean i = this.i;
        this.i = (View$MeasureSpec.getMode(n) == 1073741824);
        if (i != this.i) {
            this.j = 0;
        }
        final int size = View$MeasureSpec.getSize(n);
        if (this.i && this.c != null && size != this.j) {
            this.j = size;
            this.c.b(true);
        }
        final int childCount = this.getChildCount();
        if (this.i && childCount > 0) {
            this.c(n, n2);
            return;
        }
        for (int j = 0; j < childCount; ++j) {
            final c c = (c)this.getChildAt(j).getLayoutParams();
            c.rightMargin = 0;
            c.leftMargin = 0;
        }
        super.onMeasure(n, n2);
    }
    
    public void setExpandedActionViewsExclusive(final boolean b) {
        this.g.d(b);
    }
    
    public void setOnMenuItemClickListener(final e b) {
        this.b = b;
    }
    
    public void setOverflowIcon(final Drawable drawable) {
        this.getMenu();
        this.g.a(drawable);
    }
    
    public void setOverflowReserved(final boolean f) {
        this.f = f;
    }
    
    public void setPopupTheme(final int e) {
        if (this.e != e) {
            if ((this.e = e) != 0) {
                this.d = (Context)new ContextThemeWrapper(this.getContext(), e);
                return;
            }
            this.d = this.getContext();
        }
    }
    
    public void setPresenter(final android.support.v7.widget.d g) {
        (this.g = g).a(this);
    }
    
    public interface a
    {
        boolean c();
        
        boolean d();
    }
    
    private class b implements o.a
    {
        @Override
        public void a(final h h, final boolean b) {
        }
        
        @Override
        public boolean a(final h h) {
            return false;
        }
    }
    
    public static class c extends ah.a
    {
        @ViewDebug$ExportedProperty
        public boolean a;
        @ViewDebug$ExportedProperty
        public int b;
        @ViewDebug$ExportedProperty
        public int c;
        @ViewDebug$ExportedProperty
        public boolean d;
        @ViewDebug$ExportedProperty
        public boolean e;
        boolean f;
        
        public c(final int n, final int n2) {
            super(n, n2);
            this.a = false;
        }
        
        public c(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public c(final c c) {
            super((ViewGroup$LayoutParams)c);
            this.a = c.a;
        }
        
        public c(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
    }
    
    private class d implements h.a
    {
        @Override
        public void a(final h h) {
            if (ActionMenuView.this.a != null) {
                ActionMenuView.this.a.a(h);
            }
        }
        
        @Override
        public boolean a(final h h, final MenuItem menuItem) {
            return ActionMenuView.this.b != null && ActionMenuView.this.b.a(menuItem);
        }
    }
    
    public interface e
    {
        boolean a(final MenuItem p0);
    }
}
