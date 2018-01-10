// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Parcel;
import android.support.v4.e.e;
import android.os.Parcelable$Creator;
import android.support.v7.view.menu.u;
import android.support.v7.view.c;
import android.text.TextUtils$TruncateAt;
import android.view.ContextThemeWrapper;
import android.support.v7.b.a.b;
import android.os.Build$VERSION;
import android.support.v4.g.q;
import android.os.Parcelable;
import android.support.v4.g.t;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.support.v7.view.menu.j;
import android.view.Menu;
import android.support.v7.view.g;
import android.view.MenuInflater;
import android.support.v4.g.f;
import android.support.v4.g.ae;
import android.view.ViewGroup$LayoutParams;
import android.view.View$MeasureSpec;
import java.util.List;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.o;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;

public class Toolbar extends ViewGroup
{
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    private final ActionMenuView.e H;
    private aw I;
    private android.support.v7.widget.d J;
    private a K;
    private o.a L;
    private h.a M;
    private boolean N;
    private final Runnable O;
    ImageButton a;
    View b;
    int c;
    c d;
    private ActionMenuView e;
    private TextView f;
    private TextView g;
    private ImageButton h;
    private ImageView i;
    private Drawable j;
    private CharSequence k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private an u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;
    
    public Toolbar(final Context context) {
        this(context, null);
    }
    
    public Toolbar(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.toolbarStyle);
    }
    
    public Toolbar(final Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.x = 8388627;
        this.E = new ArrayList<View>();
        this.F = new ArrayList<View>();
        this.G = new int[2];
        this.H = new ActionMenuView.e() {
            @Override
            public boolean a(final MenuItem menuItem) {
                return Toolbar.this.d != null && Toolbar.this.d.a(menuItem);
            }
        };
        this.O = new Runnable() {
            @Override
            public void run() {
                Toolbar.this.d();
            }
        };
        final av a = av.a(this.getContext(), set, android.support.v7.a.a.j.Toolbar, n, 0);
        this.n = a.g(android.support.v7.a.a.j.Toolbar_titleTextAppearance, 0);
        this.o = a.g(android.support.v7.a.a.j.Toolbar_subtitleTextAppearance, 0);
        this.x = a.c(android.support.v7.a.a.j.Toolbar_android_gravity, this.x);
        this.c = a.c(android.support.v7.a.a.j.Toolbar_buttonGravity, 48);
        final int n2 = n = a.d(android.support.v7.a.a.j.Toolbar_titleMargin, 0);
        if (a.g(android.support.v7.a.a.j.Toolbar_titleMargins)) {
            n = a.d(android.support.v7.a.a.j.Toolbar_titleMargins, n2);
        }
        this.t = n;
        this.s = n;
        this.r = n;
        this.q = n;
        n = a.d(android.support.v7.a.a.j.Toolbar_titleMarginStart, -1);
        if (n >= 0) {
            this.q = n;
        }
        n = a.d(android.support.v7.a.a.j.Toolbar_titleMarginEnd, -1);
        if (n >= 0) {
            this.r = n;
        }
        n = a.d(android.support.v7.a.a.j.Toolbar_titleMarginTop, -1);
        if (n >= 0) {
            this.s = n;
        }
        n = a.d(android.support.v7.a.a.j.Toolbar_titleMarginBottom, -1);
        if (n >= 0) {
            this.t = n;
        }
        this.p = a.e(android.support.v7.a.a.j.Toolbar_maxButtonHeight, -1);
        n = a.d(android.support.v7.a.a.j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        final int d = a.d(android.support.v7.a.a.j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        final int e = a.e(android.support.v7.a.a.j.Toolbar_contentInsetLeft, 0);
        final int e2 = a.e(android.support.v7.a.a.j.Toolbar_contentInsetRight, 0);
        this.s();
        this.u.b(e, e2);
        if (n != Integer.MIN_VALUE || d != Integer.MIN_VALUE) {
            this.u.a(n, d);
        }
        this.v = a.d(android.support.v7.a.a.j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.w = a.d(android.support.v7.a.a.j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.j = a.a(android.support.v7.a.a.j.Toolbar_collapseIcon);
        this.k = a.c(android.support.v7.a.a.j.Toolbar_collapseContentDescription);
        final CharSequence c = a.c(android.support.v7.a.a.j.Toolbar_title);
        if (!TextUtils.isEmpty(c)) {
            this.setTitle(c);
        }
        final CharSequence c2 = a.c(android.support.v7.a.a.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c2)) {
            this.setSubtitle(c2);
        }
        this.l = this.getContext();
        this.setPopupTheme(a.g(android.support.v7.a.a.j.Toolbar_popupTheme, 0));
        final Drawable a2 = a.a(android.support.v7.a.a.j.Toolbar_navigationIcon);
        if (a2 != null) {
            this.setNavigationIcon(a2);
        }
        final CharSequence c3 = a.c(android.support.v7.a.a.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c3)) {
            this.setNavigationContentDescription(c3);
        }
        final Drawable a3 = a.a(android.support.v7.a.a.j.Toolbar_logo);
        if (a3 != null) {
            this.setLogo(a3);
        }
        final CharSequence c4 = a.c(android.support.v7.a.a.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c4)) {
            this.setLogoDescription(c4);
        }
        if (a.g(android.support.v7.a.a.j.Toolbar_titleTextColor)) {
            this.setTitleTextColor(a.b(android.support.v7.a.a.j.Toolbar_titleTextColor, -1));
        }
        if (a.g(android.support.v7.a.a.j.Toolbar_subtitleTextColor)) {
            this.setSubtitleTextColor(a.b(android.support.v7.a.a.j.Toolbar_subtitleTextColor, -1));
        }
        a.a();
    }
    
    private int a(int n) {
        switch (n &= 0x70) {
            default: {
                n = (this.x & 0x70);
                return n;
            }
            case 16:
            case 48:
            case 80: {
                return n;
            }
        }
    }
    
    private int a(final View view, int n) {
        final b b = (b)view.getLayoutParams();
        final int measuredHeight = view.getMeasuredHeight();
        if (n > 0) {
            n = (measuredHeight - n) / 2;
        }
        else {
            n = 0;
        }
        switch (this.a(b.a)) {
            default: {
                final int paddingTop = this.getPaddingTop();
                final int paddingBottom = this.getPaddingBottom();
                final int height = this.getHeight();
                n = (height - paddingTop - paddingBottom - measuredHeight) / 2;
                if (n < b.topMargin) {
                    n = b.topMargin;
                }
                else {
                    final int n2 = height - paddingBottom - measuredHeight - n - paddingTop;
                    if (n2 < b.bottomMargin) {
                        n = Math.max(0, n - (b.bottomMargin - n2));
                    }
                }
                return n + paddingTop;
            }
            case 48: {
                return this.getPaddingTop() - n;
            }
            case 80: {
                return this.getHeight() - this.getPaddingBottom() - measuredHeight - b.bottomMargin - n;
            }
        }
    }
    
    private int a(final View view, final int n, final int n2, final int n3, final int n4, final int[] array) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        final int n5 = viewGroup$MarginLayoutParams.leftMargin - array[0];
        final int n6 = viewGroup$MarginLayoutParams.rightMargin - array[1];
        final int n7 = Math.max(0, n5) + Math.max(0, n6);
        array[0] = Math.max(0, -n5);
        array[1] = Math.max(0, -n6);
        view.measure(getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight() + n7 + n2, viewGroup$MarginLayoutParams.width), getChildMeasureSpec(n3, this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin + n4, viewGroup$MarginLayoutParams.height));
        return view.getMeasuredWidth() + n7;
    }
    
    private int a(final View view, int n, final int[] array, int a) {
        final b b = (b)view.getLayoutParams();
        final int n2 = b.leftMargin - array[0];
        n += Math.max(0, n2);
        array[0] = Math.max(0, -n2);
        a = this.a(view, a);
        final int measuredWidth = view.getMeasuredWidth();
        view.layout(n, a, n + measuredWidth, view.getMeasuredHeight() + a);
        return b.rightMargin + measuredWidth + n;
    }
    
    private int a(final List<View> list, final int[] array) {
        int max = array[0];
        int max2 = array[1];
        int size;
        int i;
        int n;
        int max3;
        int max4;
        int measuredWidth;
        for (size = list.size(), i = 0, n = 0; i < size; ++i, n += measuredWidth + max3 + max4) {
            final View view = list.get(i);
            final b b = (b)view.getLayoutParams();
            final int n2 = b.leftMargin - max;
            final int n3 = b.rightMargin - max2;
            max3 = Math.max(0, n2);
            max4 = Math.max(0, n3);
            max = Math.max(0, -n2);
            max2 = Math.max(0, -n3);
            measuredWidth = view.getMeasuredWidth();
        }
        return n;
    }
    
    private void a(final View view, int n, int childMeasureSpec, int mode, final int n2, final int n3) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        final int childMeasureSpec2 = getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight() + viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin + childMeasureSpec, viewGroup$MarginLayoutParams.width);
        childMeasureSpec = getChildMeasureSpec(mode, this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams.topMargin + viewGroup$MarginLayoutParams.bottomMargin + n2, viewGroup$MarginLayoutParams.height);
        mode = View$MeasureSpec.getMode(childMeasureSpec);
        n = childMeasureSpec;
        if (mode != 1073741824) {
            n = childMeasureSpec;
            if (n3 >= 0) {
                n = n3;
                if (mode != 0) {
                    n = Math.min(View$MeasureSpec.getSize(childMeasureSpec), n3);
                }
                n = View$MeasureSpec.makeMeasureSpec(n, 1073741824);
            }
        }
        view.measure(childMeasureSpec2, n);
    }
    
    private void a(final View view, final boolean b) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        b layoutParams2;
        if (layoutParams == null) {
            layoutParams2 = this.j();
        }
        else if (!this.checkLayoutParams(layoutParams)) {
            layoutParams2 = this.a(layoutParams);
        }
        else {
            layoutParams2 = (b)layoutParams;
        }
        layoutParams2.b = 1;
        if (b && this.b != null) {
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.F.add(view);
            return;
        }
        this.addView(view, (ViewGroup$LayoutParams)layoutParams2);
    }
    
    private void a(final List<View> list, int i) {
        int n = 1;
        final int n2 = 0;
        if (ae.c((View)this) != 1) {
            n = 0;
        }
        final int childCount = this.getChildCount();
        final int a = android.support.v4.g.f.a(i, ae.c((View)this));
        list.clear();
        i = n2;
        if (n != 0) {
            View child;
            b b;
            for (i = childCount - 1; i >= 0; --i) {
                child = this.getChildAt(i);
                b = (b)child.getLayoutParams();
                if (b.b == 0 && this.a(child) && this.b(b.a) == a) {
                    list.add(child);
                }
            }
        }
        else {
            while (i < childCount) {
                final View child2 = this.getChildAt(i);
                final b b2 = (b)child2.getLayoutParams();
                if (b2.b == 0 && this.a(child2) && this.b(b2.a) == a) {
                    list.add(child2);
                }
                ++i;
            }
        }
    }
    
    private boolean a(final View view) {
        return view != null && view.getParent() == this && view.getVisibility() != 8;
    }
    
    private int b(int n) {
        final int c = ae.c((View)this);
        switch (n = (android.support.v4.g.f.a(n, c) & 0x7)) {
            default: {
                if (c == 1) {
                    n = 5;
                    return n;
                }
                return 3;
            }
            case 1:
            case 3:
            case 5: {
                return n;
            }
        }
    }
    
    private int b(final View view) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        return android.support.v4.g.o.b(viewGroup$MarginLayoutParams) + android.support.v4.g.o.a(viewGroup$MarginLayoutParams);
    }
    
    private int b(final View view, int n, final int[] array, int a) {
        final b b = (b)view.getLayoutParams();
        final int n2 = b.rightMargin - array[1];
        n -= Math.max(0, n2);
        array[1] = Math.max(0, -n2);
        a = this.a(view, a);
        final int measuredWidth = view.getMeasuredWidth();
        view.layout(n - measuredWidth, a, n, view.getMeasuredHeight() + a);
        return n - (b.leftMargin + measuredWidth);
    }
    
    private int c(final View view) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        return viewGroup$MarginLayoutParams.bottomMargin + viewGroup$MarginLayoutParams.topMargin;
    }
    
    private boolean d(final View view) {
        return view.getParent() == this || this.F.contains(view);
    }
    
    private MenuInflater getMenuInflater() {
        return new g(this.getContext());
    }
    
    private void m() {
        if (this.i == null) {
            this.i = new p(this.getContext());
        }
    }
    
    private void n() {
        this.o();
        if (this.e.d() == null) {
            final h h = (h)this.e.getMenu();
            if (this.K == null) {
                this.K = new a();
            }
            this.e.setExpandedActionViewsExclusive(true);
            h.a(this.K, this.l);
        }
    }
    
    private void o() {
        if (this.e == null) {
            (this.e = new ActionMenuView(this.getContext())).setPopupTheme(this.m);
            this.e.setOnMenuItemClickListener(this.H);
            this.e.a(this.L, this.M);
            final b j = this.j();
            j.a = (0x800005 | (this.c & 0x70));
            this.e.setLayoutParams((ViewGroup$LayoutParams)j);
            this.a((View)this.e, false);
        }
    }
    
    private void p() {
        if (this.h == null) {
            this.h = new n(this.getContext(), null, android.support.v7.a.a.a.toolbarNavigationButtonStyle);
            final b j = this.j();
            j.a = (0x800003 | (this.c & 0x70));
            this.h.setLayoutParams((ViewGroup$LayoutParams)j);
        }
    }
    
    private void q() {
        this.removeCallbacks(this.O);
        this.post(this.O);
    }
    
    private boolean r() {
        if (this.N) {
            for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = this.getChildAt(i);
                if (this.a(child) && child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private void s() {
        if (this.u == null) {
            this.u = new an();
        }
    }
    
    public b a(final AttributeSet set) {
        return new b(this.getContext(), set);
    }
    
    protected b a(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof b) {
            return new b((b)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof android.support.v7.app.a.a) {
            return new b((android.support.v7.app.a.a)viewGroup$LayoutParams);
        }
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new b((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new b(viewGroup$LayoutParams);
    }
    
    public void a(final int n, final int n2) {
        this.s();
        this.u.a(n, n2);
    }
    
    public void a(final Context context, final int n) {
        this.n = n;
        if (this.f != null) {
            this.f.setTextAppearance(context, n);
        }
    }
    
    public void a(final h h, final android.support.v7.widget.d d) {
        if (h != null || this.e != null) {
            this.o();
            final h d2 = this.e.d();
            if (d2 != h) {
                if (d2 != null) {
                    d2.b(this.J);
                    d2.b(this.K);
                }
                if (this.K == null) {
                    this.K = new a();
                }
                d.d(true);
                if (h != null) {
                    h.a(d, this.l);
                    h.a(this.K, this.l);
                }
                else {
                    d.a(this.l, null);
                    this.K.a(this.l, null);
                    d.b(true);
                    this.K.b(true);
                }
                this.e.setPopupTheme(this.m);
                this.e.setPresenter(d);
                this.J = d;
            }
        }
    }
    
    public void a(final o.a l, final h.a m) {
        this.L = l;
        this.M = m;
        if (this.e != null) {
            this.e.a(l, m);
        }
    }
    
    public boolean a() {
        return this.getVisibility() == 0 && this.e != null && this.e.a();
    }
    
    public void b(final Context context, final int o) {
        this.o = o;
        if (this.g != null) {
            this.g.setTextAppearance(context, o);
        }
    }
    
    public boolean b() {
        return this.e != null && this.e.g();
    }
    
    public boolean c() {
        return this.e != null && this.e.h();
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return super.checkLayoutParams(viewGroup$LayoutParams) && viewGroup$LayoutParams instanceof b;
    }
    
    public boolean d() {
        return this.e != null && this.e.e();
    }
    
    public boolean e() {
        return this.e != null && this.e.f();
    }
    
    public void f() {
        if (this.e != null) {
            this.e.i();
        }
    }
    
    public boolean g() {
        return this.K != null && this.K.b != null;
    }
    
    public int getContentInsetEnd() {
        if (this.u != null) {
            return this.u.d();
        }
        return 0;
    }
    
    public int getContentInsetEndWithActions() {
        if (this.w != Integer.MIN_VALUE) {
            return this.w;
        }
        return this.getContentInsetEnd();
    }
    
    public int getContentInsetLeft() {
        if (this.u != null) {
            return this.u.a();
        }
        return 0;
    }
    
    public int getContentInsetRight() {
        if (this.u != null) {
            return this.u.b();
        }
        return 0;
    }
    
    public int getContentInsetStart() {
        if (this.u != null) {
            return this.u.c();
        }
        return 0;
    }
    
    public int getContentInsetStartWithNavigation() {
        if (this.v != Integer.MIN_VALUE) {
            return this.v;
        }
        return this.getContentInsetStart();
    }
    
    public int getCurrentContentInsetEnd() {
        int n;
        if (this.e != null) {
            final h d = this.e.d();
            if (d != null && d.hasVisibleItems()) {
                n = 1;
            }
            else {
                n = 0;
            }
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return Math.max(this.getContentInsetEnd(), Math.max(this.w, 0));
        }
        return this.getContentInsetEnd();
    }
    
    public int getCurrentContentInsetLeft() {
        if (ae.c((View)this) == 1) {
            return this.getCurrentContentInsetEnd();
        }
        return this.getCurrentContentInsetStart();
    }
    
    public int getCurrentContentInsetRight() {
        if (ae.c((View)this) == 1) {
            return this.getCurrentContentInsetStart();
        }
        return this.getCurrentContentInsetEnd();
    }
    
    public int getCurrentContentInsetStart() {
        if (this.getNavigationIcon() != null) {
            return Math.max(this.getContentInsetStart(), Math.max(this.v, 0));
        }
        return this.getContentInsetStart();
    }
    
    public Drawable getLogo() {
        if (this.i != null) {
            return this.i.getDrawable();
        }
        return null;
    }
    
    public CharSequence getLogoDescription() {
        if (this.i != null) {
            return this.i.getContentDescription();
        }
        return null;
    }
    
    public Menu getMenu() {
        this.n();
        return this.e.getMenu();
    }
    
    public CharSequence getNavigationContentDescription() {
        if (this.h != null) {
            return this.h.getContentDescription();
        }
        return null;
    }
    
    public Drawable getNavigationIcon() {
        if (this.h != null) {
            return this.h.getDrawable();
        }
        return null;
    }
    
    public Drawable getOverflowIcon() {
        this.n();
        return this.e.getOverflowIcon();
    }
    
    public int getPopupTheme() {
        return this.m;
    }
    
    public CharSequence getSubtitle() {
        return this.z;
    }
    
    public CharSequence getTitle() {
        return this.y;
    }
    
    public int getTitleMarginBottom() {
        return this.t;
    }
    
    public int getTitleMarginEnd() {
        return this.r;
    }
    
    public int getTitleMarginStart() {
        return this.q;
    }
    
    public int getTitleMarginTop() {
        return this.s;
    }
    
    public ac getWrapper() {
        if (this.I == null) {
            this.I = new aw(this, true);
        }
        return this.I;
    }
    
    public void h() {
        j b;
        if (this.K == null) {
            b = null;
        }
        else {
            b = this.K.b;
        }
        if (b != null) {
            b.collapseActionView();
        }
    }
    
    void i() {
        if (this.a == null) {
            (this.a = new n(this.getContext(), null, android.support.v7.a.a.a.toolbarNavigationButtonStyle)).setImageDrawable(this.j);
            this.a.setContentDescription(this.k);
            final b j = this.j();
            j.a = (0x800003 | (this.c & 0x70));
            j.b = 2;
            this.a.setLayoutParams((ViewGroup$LayoutParams)j);
            this.a.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    Toolbar.this.h();
                }
            });
        }
    }
    
    protected b j() {
        return new b(-2, -2);
    }
    
    void k() {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            final View child = this.getChildAt(i);
            if (((b)child.getLayoutParams()).b != 2 && child != this.e) {
                this.removeViewAt(i);
                this.F.add(child);
            }
        }
    }
    
    void l() {
        for (int i = this.F.size() - 1; i >= 0; --i) {
            this.addView((View)this.F.get(i));
        }
        this.F.clear();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.O);
    }
    
    public boolean onHoverEvent(final MotionEvent motionEvent) {
        final int a = android.support.v4.g.t.a(motionEvent);
        if (a == 9) {
            this.D = false;
        }
        if (!this.D) {
            final boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (a == 9 && !onHoverEvent) {
                this.D = true;
            }
        }
        if (a == 10 || a == 3) {
            this.D = false;
        }
        return true;
    }
    
    protected void onLayout(final boolean b, int i, int j, int n, int n2) {
        boolean b2;
        if (ae.c((View)this) == 1) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        i = width - paddingRight;
        final int[] g = this.G;
        g[g[1] = 0] = 0;
        n = ae.g((View)this);
        int min;
        if (n >= 0) {
            min = Math.min(n, n2 - j);
        }
        else {
            min = 0;
        }
        if (this.a((View)this.h)) {
            if (b2) {
                i = this.b((View)this.h, i, g, min);
                j = paddingLeft;
            }
            else {
                j = this.a((View)this.h, paddingLeft, g, min);
            }
        }
        else {
            j = paddingLeft;
        }
        n = i;
        n2 = j;
        if (this.a((View)this.a)) {
            if (b2) {
                n = this.b((View)this.a, i, g, min);
                n2 = j;
            }
            else {
                n2 = this.a((View)this.a, j, g, min);
                n = i;
            }
        }
        i = n;
        j = n2;
        if (this.a((View)this.e)) {
            if (b2) {
                j = this.a((View)this.e, n2, g, min);
                i = n;
            }
            else {
                i = this.b((View)this.e, n, g, min);
                j = n2;
            }
        }
        n = this.getCurrentContentInsetLeft();
        n2 = this.getCurrentContentInsetRight();
        g[0] = Math.max(0, n - j);
        g[1] = Math.max(0, n2 - (width - paddingRight - i));
        n = Math.max(j, n);
        n2 = (j = Math.min(i, width - paddingRight - n2));
        i = n;
        if (this.a(this.b)) {
            if (b2) {
                j = this.b(this.b, n2, g, min);
                i = n;
            }
            else {
                i = this.a(this.b, n, g, min);
                j = n2;
            }
        }
        if (this.a((View)this.i)) {
            if (b2) {
                j = this.b((View)this.i, j, g, min);
                n = i;
            }
            else {
                n = this.a((View)this.i, i, g, min);
            }
        }
        else {
            n = i;
        }
        final boolean a = this.a((View)this.f);
        final boolean a2 = this.a((View)this.g);
        i = 0;
        if (a) {
            final b b3 = (b)this.f.getLayoutParams();
            i = b3.topMargin;
            n2 = this.f.getMeasuredHeight();
            i = 0 + (b3.bottomMargin + (i + n2));
        }
        int n3;
        if (a2) {
            final b b4 = (b)this.g.getLayoutParams();
            n2 = b4.topMargin;
            n3 = b4.bottomMargin + (n2 + this.g.getMeasuredHeight()) + i;
        }
        else {
            n3 = i;
        }
        Label_0832: {
            if (!a) {
                n2 = j;
                i = n;
                if (!a2) {
                    break Label_0832;
                }
            }
            TextView textView;
            if (a) {
                textView = this.f;
            }
            else {
                textView = this.g;
            }
            TextView textView2;
            if (a2) {
                textView2 = this.g;
            }
            else {
                textView2 = this.f;
            }
            final b b5 = (b)((View)textView).getLayoutParams();
            final b b6 = (b)((View)textView2).getLayoutParams();
            boolean b7;
            if ((a && this.f.getMeasuredWidth() > 0) || (a2 && this.g.getMeasuredWidth() > 0)) {
                b7 = true;
            }
            else {
                b7 = false;
            }
            switch (this.x & 0x70) {
                default: {
                    i = (height - paddingTop - paddingBottom - n3) / 2;
                    if (i < b5.topMargin + this.s) {
                        i = b5.topMargin + this.s;
                    }
                    else {
                        n2 = height - paddingBottom - n3 - i - paddingTop;
                        if (n2 < b5.bottomMargin + this.t) {
                            i = Math.max(0, i - (b6.bottomMargin + this.t - n2));
                        }
                    }
                    i += paddingTop;
                    break;
                }
                case 48: {
                    i = this.getPaddingTop();
                    i = b5.topMargin + i + this.s;
                    break;
                }
                case 80: {
                    i = height - paddingBottom - b6.bottomMargin - this.t - n3;
                    break;
                }
            }
            if (b2) {
                if (b7) {
                    n2 = this.q;
                }
                else {
                    n2 = 0;
                }
                n2 -= g[1];
                j -= Math.max(0, n2);
                g[1] = Math.max(0, -n2);
                if (a) {
                    final b b8 = (b)this.f.getLayoutParams();
                    n2 = j - this.f.getMeasuredWidth();
                    final int n4 = this.f.getMeasuredHeight() + i;
                    this.f.layout(n2, i, j, n4);
                    final int r = this.r;
                    i = n4 + b8.bottomMargin;
                    n2 -= r;
                }
                else {
                    n2 = j;
                }
                if (a2) {
                    final b b9 = (b)this.g.getLayoutParams();
                    i += b9.topMargin;
                    this.g.layout(j - this.g.getMeasuredWidth(), i, j, this.g.getMeasuredHeight() + i);
                    i = this.r;
                    final int bottomMargin = b9.bottomMargin;
                    i = j - i;
                }
                else {
                    i = j;
                }
                if (b7) {
                    i = Math.min(n2, i);
                }
                else {
                    i = j;
                }
                n2 = i;
                i = n;
            }
            else {
                if (b7) {
                    n2 = this.q;
                }
                else {
                    n2 = 0;
                }
                n2 -= g[0];
                n += Math.max(0, n2);
                g[0] = Math.max(0, -n2);
                int n6;
                if (a) {
                    final b b10 = (b)this.f.getLayoutParams();
                    final int n5 = this.f.getMeasuredWidth() + n;
                    n2 = this.f.getMeasuredHeight() + i;
                    this.f.layout(n, i, n5, n2);
                    final int r2 = this.r;
                    i = b10.bottomMargin;
                    n6 = n5 + r2;
                    i += n2;
                }
                else {
                    n6 = n;
                }
                int n7;
                if (a2) {
                    final b b11 = (b)this.g.getLayoutParams();
                    n2 = i + b11.topMargin;
                    i = this.g.getMeasuredWidth() + n;
                    this.g.layout(n, n2, i, this.g.getMeasuredHeight() + n2);
                    n2 = this.r;
                    final int bottomMargin2 = b11.bottomMargin;
                    n7 = n2 + i;
                }
                else {
                    n7 = n;
                }
                n2 = j;
                i = n;
                if (b7) {
                    i = Math.max(n6, n7);
                    n2 = j;
                }
            }
        }
        this.a(this.E, 3);
        for (n = this.E.size(), j = 0; j < n; ++j) {
            i = this.a(this.E.get(j), i, g, min);
        }
        this.a(this.E, 5);
        for (n = this.E.size(), j = 0; j < n; ++j) {
            n2 = this.b(this.E.get(j), n2, g, min);
        }
        this.a(this.E, 1);
        n = this.a(this.E, g);
        j = (width - paddingLeft - paddingRight) / 2 + paddingLeft - n / 2;
        n += j;
        if (j >= i) {
            i = j;
            if (n > n2) {
                i = j - (n - n2);
            }
        }
        n2 = this.E.size();
        n = 0;
        j = i;
        for (i = n; i < n2; ++i) {
            j = this.a(this.E.get(i), j, g, min);
        }
        this.E.clear();
    }
    
    protected void onMeasure(int a, final int n) {
        int max = 0;
        int a2 = 0;
        final int[] g = this.G;
        int n2;
        int n3;
        if (ay.a((View)this)) {
            n2 = 0;
            n3 = 1;
        }
        else {
            n2 = 1;
            n3 = 0;
        }
        int n4 = 0;
        if (this.a((View)this.h)) {
            this.a((View)this.h, a, 0, n, 0, this.p);
            n4 = this.h.getMeasuredWidth() + this.b((View)this.h);
            max = Math.max(0, this.h.getMeasuredHeight() + this.c((View)this.h));
            a2 = ay.a(0, ae.e((View)this.h));
        }
        int n5 = n4;
        int a3 = a2;
        int max2 = max;
        if (this.a((View)this.a)) {
            this.a((View)this.a, a, 0, n, 0, this.p);
            n5 = this.a.getMeasuredWidth() + this.b((View)this.a);
            max2 = Math.max(max, this.a.getMeasuredHeight() + this.c((View)this.a));
            a3 = ay.a(a2, ae.e((View)this.a));
        }
        final int currentContentInsetStart = this.getCurrentContentInsetStart();
        final int n6 = 0 + Math.max(currentContentInsetStart, n5);
        g[n3] = Math.max(0, currentContentInsetStart - n5);
        int n7 = 0;
        int a4 = a3;
        int max3 = max2;
        if (this.a((View)this.e)) {
            this.a((View)this.e, a, n6, n, 0, this.p);
            n7 = this.e.getMeasuredWidth() + this.b((View)this.e);
            max3 = Math.max(max2, this.e.getMeasuredHeight() + this.c((View)this.e));
            a4 = ay.a(a3, ae.e((View)this.e));
        }
        final int currentContentInsetEnd = this.getCurrentContentInsetEnd();
        final int n8 = n6 + Math.max(currentContentInsetEnd, n7);
        g[n2] = Math.max(0, currentContentInsetEnd - n7);
        int n9 = n8;
        int a5 = a4;
        int max4 = max3;
        if (this.a(this.b)) {
            n9 = n8 + this.a(this.b, a, n8, n, 0, g);
            max4 = Math.max(max3, this.b.getMeasuredHeight() + this.c(this.b));
            a5 = ay.a(a4, ae.e(this.b));
        }
        int n10 = n9;
        int a6 = a5;
        int max5 = max4;
        if (this.a((View)this.i)) {
            n10 = n9 + this.a((View)this.i, a, n9, n, 0, g);
            max5 = Math.max(max4, this.i.getMeasuredHeight() + this.c((View)this.i));
            a6 = ay.a(a5, ae.e((View)this.i));
        }
        final int childCount = this.getChildCount();
        final int n11 = 0;
        int max6 = max5;
        int a7 = a6;
        int i = n11;
        int n12 = n10;
        while (i < childCount) {
            final View child = this.getChildAt(i);
            if (((b)child.getLayoutParams()).b == 0) {
                if (this.a(child)) {
                    n12 += this.a(child, a, n12, n, 0, g);
                    max6 = Math.max(max6, child.getMeasuredHeight() + this.c(child));
                    a7 = ay.a(a7, ae.e(child));
                }
            }
            ++i;
        }
        int n13 = 0;
        int n14 = 0;
        final int n15 = this.s + this.t;
        final int n16 = this.q + this.r;
        int a8 = a7;
        if (this.a((View)this.f)) {
            this.a((View)this.f, a, n12 + n16, n, n15, g);
            n13 = this.b((View)this.f) + this.f.getMeasuredWidth();
            n14 = this.f.getMeasuredHeight() + this.c((View)this.f);
            a8 = ay.a(a7, ae.e((View)this.f));
        }
        int n17 = n14;
        int max7 = n13;
        int a9 = a8;
        if (this.a((View)this.g)) {
            max7 = Math.max(n13, this.a((View)this.g, a, n12 + n16, n, n15 + n14, g));
            n17 = n14 + (this.g.getMeasuredHeight() + this.c((View)this.g));
            a9 = ay.a(a8, ae.e((View)this.g));
        }
        final int max8 = Math.max(max6, n17);
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int paddingTop = this.getPaddingTop();
        final int paddingBottom = this.getPaddingBottom();
        final int a10 = ae.a(Math.max(max7 + n12 + (paddingLeft + paddingRight), this.getSuggestedMinimumWidth()), a, 0xFF000000 & a9);
        a = ae.a(Math.max(max8 + (paddingTop + paddingBottom), this.getSuggestedMinimumHeight()), n, a9 << 16);
        if (this.r()) {
            a = 0;
        }
        this.setMeasuredDimension(a10, a);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof d)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final d d = (d)parcelable;
            super.onRestoreInstanceState(d.a());
            Object d2;
            if (this.e != null) {
                d2 = this.e.d();
            }
            else {
                d2 = null;
            }
            if (d.b != 0 && this.K != null && d2 != null) {
                final MenuItem item = ((Menu)d2).findItem(d.b);
                if (item != null) {
                    android.support.v4.g.q.b(item);
                }
            }
            if (d.c) {
                this.q();
            }
        }
    }
    
    public void onRtlPropertiesChanged(final int n) {
        boolean b = true;
        if (Build$VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(n);
        }
        this.s();
        final an u = this.u;
        if (n != 1) {
            b = false;
        }
        u.a(b);
    }
    
    protected Parcelable onSaveInstanceState() {
        final d d = new d(super.onSaveInstanceState());
        if (this.K != null && this.K.b != null) {
            d.b = this.K.b.getItemId();
        }
        d.c = this.b();
        return (Parcelable)d;
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int a = android.support.v4.g.t.a(motionEvent);
        if (a == 0) {
            this.C = false;
        }
        if (!this.C) {
            final boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (a == 0 && !onTouchEvent) {
                this.C = true;
            }
        }
        if (a == 1 || a == 3) {
            this.C = false;
        }
        return true;
    }
    
    public void setCollapsible(final boolean n) {
        this.N = n;
        this.requestLayout();
    }
    
    public void setContentInsetEndWithActions(final int n) {
        int w = n;
        if (n < 0) {
            w = Integer.MIN_VALUE;
        }
        if (w != this.w) {
            this.w = w;
            if (this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }
    
    public void setContentInsetStartWithNavigation(final int n) {
        int v = n;
        if (n < 0) {
            v = Integer.MIN_VALUE;
        }
        if (v != this.v) {
            this.v = v;
            if (this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }
    
    public void setLogo(final int n) {
        this.setLogo(android.support.v7.b.a.b.b(this.getContext(), n));
    }
    
    public void setLogo(final Drawable imageDrawable) {
        if (imageDrawable != null) {
            this.m();
            if (!this.d((View)this.i)) {
                this.a((View)this.i, true);
            }
        }
        else if (this.i != null && this.d((View)this.i)) {
            this.removeView((View)this.i);
            this.F.remove(this.i);
        }
        if (this.i != null) {
            this.i.setImageDrawable(imageDrawable);
        }
    }
    
    public void setLogoDescription(final int n) {
        this.setLogoDescription(this.getContext().getText(n));
    }
    
    public void setLogoDescription(final CharSequence contentDescription) {
        if (!TextUtils.isEmpty(contentDescription)) {
            this.m();
        }
        if (this.i != null) {
            this.i.setContentDescription(contentDescription);
        }
    }
    
    public void setNavigationContentDescription(final int n) {
        CharSequence text;
        if (n != 0) {
            text = this.getContext().getText(n);
        }
        else {
            text = null;
        }
        this.setNavigationContentDescription(text);
    }
    
    public void setNavigationContentDescription(final CharSequence contentDescription) {
        if (!TextUtils.isEmpty(contentDescription)) {
            this.p();
        }
        if (this.h != null) {
            this.h.setContentDescription(contentDescription);
        }
    }
    
    public void setNavigationIcon(final int n) {
        this.setNavigationIcon(android.support.v7.b.a.b.b(this.getContext(), n));
    }
    
    public void setNavigationIcon(final Drawable imageDrawable) {
        if (imageDrawable != null) {
            this.p();
            if (!this.d((View)this.h)) {
                this.a((View)this.h, true);
            }
        }
        else if (this.h != null && this.d((View)this.h)) {
            this.removeView((View)this.h);
            this.F.remove(this.h);
        }
        if (this.h != null) {
            this.h.setImageDrawable(imageDrawable);
        }
    }
    
    public void setNavigationOnClickListener(final View$OnClickListener onClickListener) {
        this.p();
        this.h.setOnClickListener(onClickListener);
    }
    
    public void setOnMenuItemClickListener(final c d) {
        this.d = d;
    }
    
    public void setOverflowIcon(final Drawable overflowIcon) {
        this.n();
        this.e.setOverflowIcon(overflowIcon);
    }
    
    public void setPopupTheme(final int m) {
        if (this.m != m) {
            if ((this.m = m) != 0) {
                this.l = (Context)new ContextThemeWrapper(this.getContext(), m);
                return;
            }
            this.l = this.getContext();
        }
    }
    
    public void setSubtitle(final int n) {
        this.setSubtitle(this.getContext().getText(n));
    }
    
    public void setSubtitle(final CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.g == null) {
                final Context context = this.getContext();
                (this.g = new aa(context)).setSingleLine();
                this.g.setEllipsize(TextUtils$TruncateAt.END);
                if (this.o != 0) {
                    this.g.setTextAppearance(context, this.o);
                }
                if (this.B != 0) {
                    this.g.setTextColor(this.B);
                }
            }
            if (!this.d((View)this.g)) {
                this.a((View)this.g, true);
            }
        }
        else if (this.g != null && this.d((View)this.g)) {
            this.removeView((View)this.g);
            this.F.remove(this.g);
        }
        if (this.g != null) {
            this.g.setText(charSequence);
        }
        this.z = charSequence;
    }
    
    public void setSubtitleTextColor(final int n) {
        this.B = n;
        if (this.g != null) {
            this.g.setTextColor(n);
        }
    }
    
    public void setTitle(final int n) {
        this.setTitle(this.getContext().getText(n));
    }
    
    public void setTitle(final CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f == null) {
                final Context context = this.getContext();
                (this.f = new aa(context)).setSingleLine();
                this.f.setEllipsize(TextUtils$TruncateAt.END);
                if (this.n != 0) {
                    this.f.setTextAppearance(context, this.n);
                }
                if (this.A != 0) {
                    this.f.setTextColor(this.A);
                }
            }
            if (!this.d((View)this.f)) {
                this.a((View)this.f, true);
            }
        }
        else if (this.f != null && this.d((View)this.f)) {
            this.removeView((View)this.f);
            this.F.remove(this.f);
        }
        if (this.f != null) {
            this.f.setText(charSequence);
        }
        this.y = charSequence;
    }
    
    public void setTitleMarginBottom(final int t) {
        this.t = t;
        this.requestLayout();
    }
    
    public void setTitleMarginEnd(final int r) {
        this.r = r;
        this.requestLayout();
    }
    
    public void setTitleMarginStart(final int q) {
        this.q = q;
        this.requestLayout();
    }
    
    public void setTitleMarginTop(final int s) {
        this.s = s;
        this.requestLayout();
    }
    
    public void setTitleTextColor(final int n) {
        this.A = n;
        if (this.f != null) {
            this.f.setTextColor(n);
        }
    }
    
    private class a implements o
    {
        h a;
        j b;
        
        @Override
        public void a(final Context context, final h a) {
            if (this.a != null && this.b != null) {
                this.a.d(this.b);
            }
            this.a = a;
        }
        
        @Override
        public void a(final h h, final boolean b) {
        }
        
        @Override
        public void a(final o.a a) {
        }
        
        @Override
        public boolean a(final h h, final j b) {
            Toolbar.this.i();
            if (Toolbar.this.a.getParent() != Toolbar.this) {
                Toolbar.this.addView((View)Toolbar.this.a);
            }
            Toolbar.this.b = b.getActionView();
            this.b = b;
            if (Toolbar.this.b.getParent() != Toolbar.this) {
                final b j = Toolbar.this.j();
                j.a = (0x800003 | (Toolbar.this.c & 0x70));
                j.b = 2;
                Toolbar.this.b.setLayoutParams((ViewGroup$LayoutParams)j);
                Toolbar.this.addView(Toolbar.this.b);
            }
            Toolbar.this.k();
            Toolbar.this.requestLayout();
            b.e(true);
            if (Toolbar.this.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c)Toolbar.this.b).a();
            }
            return true;
        }
        
        @Override
        public boolean a(final u u) {
            return false;
        }
        
        @Override
        public void b(final boolean b) {
            final boolean b2 = false;
            if (this.b != null) {
                boolean b3 = b2;
                if (this.a != null) {
                    final int size = this.a.size();
                    int n = 0;
                    while (true) {
                        b3 = b2;
                        if (n >= size) {
                            break;
                        }
                        if (this.a.getItem(n) == this.b) {
                            b3 = true;
                            break;
                        }
                        ++n;
                    }
                }
                if (!b3) {
                    this.b(this.a, this.b);
                }
            }
        }
        
        @Override
        public boolean b() {
            return false;
        }
        
        @Override
        public boolean b(final h h, final j j) {
            if (Toolbar.this.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c)Toolbar.this.b).b();
            }
            Toolbar.this.removeView(Toolbar.this.b);
            Toolbar.this.removeView((View)Toolbar.this.a);
            Toolbar.this.b = null;
            Toolbar.this.l();
            this.b = null;
            Toolbar.this.requestLayout();
            j.e(false);
            return true;
        }
    }
    
    public static class b extends a.a
    {
        int b;
        
        public b(final int n, final int n2) {
            super(n, n2);
            this.b = 0;
            this.a = 8388627;
        }
        
        public b(final Context context, final AttributeSet set) {
            super(context, set);
            this.b = 0;
        }
        
        public b(final a.a a) {
            super(a);
            this.b = 0;
        }
        
        public b(final b b) {
            super((a.a)b);
            this.b = 0;
            this.b = b.b;
        }
        
        public b(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.b = 0;
        }
        
        public b(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super((ViewGroup$LayoutParams)viewGroup$MarginLayoutParams);
            this.b = 0;
            this.a(viewGroup$MarginLayoutParams);
        }
        
        void a(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            this.leftMargin = viewGroup$MarginLayoutParams.leftMargin;
            this.topMargin = viewGroup$MarginLayoutParams.topMargin;
            this.rightMargin = viewGroup$MarginLayoutParams.rightMargin;
            this.bottomMargin = viewGroup$MarginLayoutParams.bottomMargin;
        }
    }
    
    public interface c
    {
        boolean a(final MenuItem p0);
    }
    
    public static class d extends android.support.v4.g.a
    {
        public static final Parcelable$Creator<d> CREATOR;
        int b;
        boolean c;
        
        static {
            CREATOR = android.support.v4.e.d.a((e<d>)new e<d>() {
                public d b(final Parcel parcel, final ClassLoader classLoader) {
                    return new d(parcel, classLoader);
                }
                
                public d[] b(final int n) {
                    return new d[n];
                }
            });
        }
        
        public d(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel, classLoader);
            this.b = parcel.readInt();
            this.c = (parcel.readInt() != 0);
        }
        
        public d(final Parcelable parcelable) {
            super(parcelable);
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.b);
            if (this.c) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
        }
    }
}
