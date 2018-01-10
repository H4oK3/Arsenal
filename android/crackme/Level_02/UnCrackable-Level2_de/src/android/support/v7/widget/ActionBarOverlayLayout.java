// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$MarginLayoutParams;
import android.view.Window$Callback;
import android.os.Build$VERSION;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.view.menu.o;
import android.view.Menu;
import android.content.res.TypedArray;
import android.support.v4.g.ae;
import android.view.View;
import android.support.v4.g.ax;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.a.a;
import android.support.v4.widget.t;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.g.aw;
import android.support.v4.g.as;
import android.support.v4.g.y;
import android.support.v4.g.x;
import android.view.ViewGroup;

public class ActionBarOverlayLayout extends ViewGroup implements x, ab
{
    static final int[] e;
    private final Runnable A;
    private final y B;
    ActionBarContainer a;
    boolean b;
    as c;
    final aw d;
    private int f;
    private int g;
    private ContentFrameLayout h;
    private ac i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private a w;
    private final int x;
    private t y;
    private final Runnable z;
    
    static {
        e = new int[] { android.support.v7.a.a.a.actionBarSize, 16842841 };
    }
    
    public ActionBarOverlayLayout(final Context context) {
        this(context, null);
    }
    
    public ActionBarOverlayLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.g = 0;
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        this.x = 600;
        this.d = new ax() {
            @Override
            public void b(final View view) {
                ActionBarOverlayLayout.this.c = null;
                ActionBarOverlayLayout.this.b = false;
            }
            
            @Override
            public void c(final View view) {
                ActionBarOverlayLayout.this.c = null;
                ActionBarOverlayLayout.this.b = false;
            }
        };
        this.z = new Runnable() {
            @Override
            public void run() {
                ActionBarOverlayLayout.this.d();
                ActionBarOverlayLayout.this.c = ae.h((View)ActionBarOverlayLayout.this.a).b(0.0f).a(ActionBarOverlayLayout.this.d);
            }
        };
        this.A = new Runnable() {
            @Override
            public void run() {
                ActionBarOverlayLayout.this.d();
                ActionBarOverlayLayout.this.c = ae.h((View)ActionBarOverlayLayout.this.a).b((float)(-ActionBarOverlayLayout.this.a.getHeight())).a(ActionBarOverlayLayout.this.d);
            }
        };
        this.a(context);
        this.B = new y(this);
    }
    
    private ac a(final View view) {
        if (view instanceof ac) {
            return (ac)view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar)view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }
    
    private void a(final Context context) {
        final boolean b = true;
        final TypedArray obtainStyledAttributes = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.e);
        this.f = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.j = obtainStyledAttributes.getDrawable(1);
        this.setWillNotDraw(this.j == null);
        obtainStyledAttributes.recycle();
        this.k = (context.getApplicationInfo().targetSdkVersion < 19 && b);
        this.y = android.support.v4.widget.t.a(context);
    }
    
    private boolean a(final float n, final float n2) {
        boolean b = false;
        this.y.a(0, 0, 0, (int)n2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.y.d() > this.a.getHeight()) {
            b = true;
        }
        return b;
    }
    
    private boolean a(final View view, final Rect rect, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final boolean b5 = false;
        final b b6 = (b)view.getLayoutParams();
        boolean b7 = b5;
        if (b) {
            b7 = b5;
            if (b6.leftMargin != rect.left) {
                b6.leftMargin = rect.left;
                b7 = true;
            }
        }
        boolean b8 = b7;
        if (b2) {
            b8 = b7;
            if (b6.topMargin != rect.top) {
                b6.topMargin = rect.top;
                b8 = true;
            }
        }
        boolean b9 = b8;
        if (b4) {
            b9 = b8;
            if (b6.rightMargin != rect.right) {
                b6.rightMargin = rect.right;
                b9 = true;
            }
        }
        if (b3 && b6.bottomMargin != rect.bottom) {
            b6.bottomMargin = rect.bottom;
            return true;
        }
        return b9;
    }
    
    private void l() {
        this.d();
        this.postDelayed(this.z, 600L);
    }
    
    private void m() {
        this.d();
        this.postDelayed(this.A, 600L);
    }
    
    private void n() {
        this.d();
        this.z.run();
    }
    
    private void o() {
        this.d();
        this.A.run();
    }
    
    public b a(final AttributeSet set) {
        return new b(this.getContext(), set);
    }
    
    public void a(final int n) {
        this.c();
        switch (n) {
            default: {}
            case 2: {
                this.i.f();
            }
            case 5: {
                this.i.g();
            }
            case 109: {
                this.setOverlayMode(true);
            }
        }
    }
    
    public void a(final Menu menu, final o.a a) {
        this.c();
        this.i.a(menu, a);
    }
    
    public boolean a() {
        return this.l;
    }
    
    protected b b() {
        return new b(-1, -1);
    }
    
    void c() {
        if (this.h == null) {
            this.h = (ContentFrameLayout)this.findViewById(android.support.v7.a.a.f.action_bar_activity_content);
            this.a = (ActionBarContainer)this.findViewById(android.support.v7.a.a.f.action_bar_container);
            this.i = this.a(this.findViewById(android.support.v7.a.a.f.action_bar));
        }
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof b;
    }
    
    void d() {
        this.removeCallbacks(this.z);
        this.removeCallbacks(this.A);
        if (this.c != null) {
            this.c.b();
        }
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.j != null && !this.k) {
            int n;
            if (this.a.getVisibility() == 0) {
                n = (int)(this.a.getBottom() + ae.f((View)this.a) + 0.5f);
            }
            else {
                n = 0;
            }
            this.j.setBounds(0, n, this.getWidth(), this.j.getIntrinsicHeight() + n);
            this.j.draw(canvas);
        }
    }
    
    public boolean e() {
        this.c();
        return this.i.h();
    }
    
    public boolean f() {
        this.c();
        return this.i.i();
    }
    
    protected boolean fitSystemWindows(final Rect rect) {
        this.c();
        if ((ae.j((View)this) & 0x100) != 0x0) {}
        boolean a = this.a((View)this.a, rect, true, true, false, true);
        this.t.set(rect);
        ay.a((View)this, this.t, this.q);
        if (!this.r.equals((Object)this.q)) {
            this.r.set(this.q);
            a = true;
        }
        if (a) {
            this.requestLayout();
        }
        return true;
    }
    
    public boolean g() {
        this.c();
        return this.i.j();
    }
    
    protected ViewGroup$LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return (ViewGroup$LayoutParams)new b(viewGroup$LayoutParams);
    }
    
    public int getActionBarHideOffset() {
        if (this.a != null) {
            return -(int)ae.f((View)this.a);
        }
        return 0;
    }
    
    public int getNestedScrollAxes() {
        return this.B.a();
    }
    
    public CharSequence getTitle() {
        this.c();
        return this.i.e();
    }
    
    public boolean h() {
        this.c();
        return this.i.k();
    }
    
    public boolean i() {
        this.c();
        return this.i.l();
    }
    
    public void j() {
        this.c();
        this.i.m();
    }
    
    public void k() {
        this.c();
        this.i.n();
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.a(this.getContext());
        ae.k((View)this);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d();
    }
    
    protected void onLayout(final boolean b, int i, int childCount, int paddingLeft, int paddingTop) {
        childCount = this.getChildCount();
        paddingLeft = this.getPaddingLeft();
        this.getPaddingRight();
        paddingTop = this.getPaddingTop();
        this.getPaddingBottom();
        View child;
        b b2;
        int measuredWidth;
        int measuredHeight;
        int n;
        int n2;
        for (i = 0; i < childCount; ++i) {
            child = this.getChildAt(i);
            if (child.getVisibility() != 8) {
                b2 = (b)child.getLayoutParams();
                measuredWidth = child.getMeasuredWidth();
                measuredHeight = child.getMeasuredHeight();
                n = b2.leftMargin + paddingLeft;
                n2 = b2.topMargin + paddingTop;
                child.layout(n, n2, measuredWidth + n, measuredHeight + n2);
            }
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.c();
        this.measureChildWithMargins((View)this.a, n, 0, n2, 0);
        final b b = (b)this.a.getLayoutParams();
        final int max = Math.max(0, this.a.getMeasuredWidth() + b.leftMargin + b.rightMargin);
        final int max2 = Math.max(0, b.bottomMargin + (this.a.getMeasuredHeight() + b.topMargin));
        final int a = ay.a(0, ae.e((View)this.a));
        boolean b2;
        if ((ae.j((View)this) & 0x100) != 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        int n4;
        if (b2) {
            final int n3 = n4 = this.f;
            if (this.m) {
                n4 = n3;
                if (this.a.getTabContainer() != null) {
                    n4 = n3 + this.f;
                }
            }
        }
        else if (this.a.getVisibility() != 8) {
            n4 = this.a.getMeasuredHeight();
        }
        else {
            n4 = 0;
        }
        this.s.set(this.q);
        this.u.set(this.t);
        if (!this.l && !b2) {
            final Rect s = this.s;
            s.top += n4;
            final Rect s2 = this.s;
            s2.bottom += 0;
        }
        else {
            final Rect u = this.u;
            u.top += n4;
            final Rect u2 = this.u;
            u2.bottom += 0;
        }
        this.a((View)this.h, this.s, true, true, true, true);
        if (!this.v.equals((Object)this.u)) {
            this.v.set(this.u);
            this.h.a(this.u);
        }
        this.measureChildWithMargins((View)this.h, n, 0, n2, 0);
        final b b3 = (b)this.h.getLayoutParams();
        final int max3 = Math.max(max, this.h.getMeasuredWidth() + b3.leftMargin + b3.rightMargin);
        final int max4 = Math.max(max2, b3.bottomMargin + (this.h.getMeasuredHeight() + b3.topMargin));
        final int a2 = ay.a(a, ae.e((View)this.h));
        this.setMeasuredDimension(ae.a(Math.max(max3 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), n, a2), ae.a(Math.max(max4 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), n2, a2 << 16));
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        if (!this.n || !b) {
            return false;
        }
        if (this.a(n, n2)) {
            this.o();
        }
        else {
            this.n();
        }
        return this.b = true;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        return false;
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
    }
    
    public void onNestedScroll(final View view, final int n, final int n2, final int n3, final int n4) {
        this.setActionBarHideOffset(this.o += n2);
    }
    
    public void onNestedScrollAccepted(final View view, final View view2, final int n) {
        this.B.a(view, view2, n);
        this.o = this.getActionBarHideOffset();
        this.d();
        if (this.w != null) {
            this.w.n();
        }
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        return (n & 0x2) != 0x0 && this.a.getVisibility() == 0 && this.n;
    }
    
    public void onStopNestedScroll(final View view) {
        if (this.n && !this.b) {
            if (this.o <= this.a.getHeight()) {
                this.l();
            }
            else {
                this.m();
            }
        }
        if (this.w != null) {
            this.w.o();
        }
    }
    
    public void onWindowSystemUiVisibilityChanged(final int p) {
        boolean b = true;
        if (Build$VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(p);
        }
        this.c();
        final int p2 = this.p;
        this.p = p;
        boolean b2;
        if ((p & 0x4) == 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if ((p & 0x100) != 0x0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (this.w != null) {
            final a w = this.w;
            if (b3) {
                b = false;
            }
            w.g(b);
            if (b2 || !b3) {
                this.w.l();
            }
            else {
                this.w.m();
            }
        }
        if (((p2 ^ p) & 0x100) != 0x0 && this.w != null) {
            ae.k((View)this);
        }
    }
    
    protected void onWindowVisibilityChanged(final int g) {
        super.onWindowVisibilityChanged(g);
        this.g = g;
        if (this.w != null) {
            this.w.a(g);
        }
    }
    
    public void setActionBarHideOffset(int max) {
        this.d();
        max = Math.max(0, Math.min(max, this.a.getHeight()));
        ae.a((View)this.a, (float)(-max));
    }
    
    public void setActionBarVisibilityCallback(final a w) {
        this.w = w;
        if (this.getWindowToken() != null) {
            this.w.a(this.g);
            if (this.p != 0) {
                this.onWindowSystemUiVisibilityChanged(this.p);
                ae.k((View)this);
            }
        }
    }
    
    public void setHasNonEmbeddedTabs(final boolean m) {
        this.m = m;
    }
    
    public void setHideOnContentScrollEnabled(final boolean n) {
        if (n != this.n && !(this.n = n)) {
            this.d();
            this.setActionBarHideOffset(0);
        }
    }
    
    public void setIcon(final int n) {
        this.c();
        this.i.a(n);
    }
    
    public void setIcon(final Drawable drawable) {
        this.c();
        this.i.a(drawable);
    }
    
    public void setLogo(final int n) {
        this.c();
        this.i.b(n);
    }
    
    public void setOverlayMode(final boolean l) {
        this.l = l;
        this.k = (l && this.getContext().getApplicationInfo().targetSdkVersion < 19);
    }
    
    public void setShowingForActionMode(final boolean b) {
    }
    
    public void setUiOptions(final int n) {
    }
    
    public void setWindowCallback(final Window$Callback window$Callback) {
        this.c();
        this.i.a(window$Callback);
    }
    
    public void setWindowTitle(final CharSequence charSequence) {
        this.c();
        this.i.a(charSequence);
    }
    
    public boolean shouldDelayChildPressedState() {
        return false;
    }
    
    public interface a
    {
        void a(final int p0);
        
        void g(final boolean p0);
        
        void l();
        
        void m();
        
        void n();
        
        void o();
    }
    
    public static class b extends ViewGroup$MarginLayoutParams
    {
        public b(final int n, final int n2) {
            super(n, n2);
        }
        
        public b(final Context context, final AttributeSet set) {
            super(context, set);
        }
        
        public b(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
        }
    }
}
