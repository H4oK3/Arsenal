// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View$OnTouchListener;
import android.util.AttributeSet;
import android.support.v7.view.menu.s;
import android.support.v7.view.menu.o;
import android.support.v7.view.menu.n;
import java.util.ArrayList;
import android.support.v7.view.menu.u;
import android.support.v7.view.menu.ActionMenuItemView;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.p;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.support.v7.view.menu.h;
import android.support.v7.a.a;
import android.content.Context;
import android.view.View;
import android.util.SparseBooleanArray;
import android.graphics.drawable.Drawable;
import android.support.v4.g.e;
import android.support.v7.view.menu.b;

class d extends android.support.v7.view.menu.b implements android.support.v4.g.e.a
{
    private b A;
    d g;
    e h;
    a i;
    c j;
    final f k;
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y;
    private View z;
    
    public d(final Context context) {
        super(context, android.support.v7.a.a.g.abc_action_menu_layout, android.support.v7.a.a.g.abc_action_menu_item_layout);
        this.y = new SparseBooleanArray();
        this.k = new f();
    }
    
    private View a(final MenuItem menuItem) {
        final ViewGroup viewGroup = (ViewGroup)this.f;
        if (viewGroup != null) {
            for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = viewGroup.getChildAt(i);
                if (child instanceof p.a) {
                    final View view = child;
                    if (((p.a)child).getItemData() == menuItem) {
                        return view;
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    @Override
    public p a(final ViewGroup viewGroup) {
        final p f = this.f;
        final p a = super.a(viewGroup);
        if (f != a) {
            ((ActionMenuView)a).setPresenter(this);
        }
        return a;
    }
    
    @Override
    public View a(final j j, final View view, final ViewGroup viewGroup) {
        View view2 = j.getActionView();
        if (view2 == null || j.n()) {
            view2 = super.a(j, view, viewGroup);
        }
        int visibility;
        if (j.isActionViewExpanded()) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        view2.setVisibility(visibility);
        final ActionMenuView actionMenuView = (ActionMenuView)viewGroup;
        final ViewGroup$LayoutParams layoutParams = view2.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            view2.setLayoutParams((ViewGroup$LayoutParams)actionMenuView.a(layoutParams));
        }
        return view2;
    }
    
    @Override
    public void a(final Context context, final h h) {
        super.a(context, h);
        final Resources resources = context.getResources();
        final android.support.v7.view.a a = android.support.v7.view.a.a(context);
        if (!this.p) {
            this.o = a.b();
        }
        if (!this.v) {
            this.q = a.c();
        }
        if (!this.t) {
            this.s = a.a();
        }
        int q = this.q;
        if (this.o) {
            if (this.g == null) {
                this.g = new d(this.a);
                if (this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(measureSpec, measureSpec);
            }
            q -= this.g.getMeasuredWidth();
        }
        else {
            this.g = null;
        }
        this.r = q;
        this.x = (int)(56.0f * resources.getDisplayMetrics().density);
        this.z = null;
    }
    
    public void a(final Configuration configuration) {
        if (!this.t) {
            this.s = android.support.v7.view.a.a(this.b).a();
        }
        if (this.c != null) {
            this.c.b(true);
        }
    }
    
    public void a(final Drawable drawable) {
        if (this.g != null) {
            this.g.setImageDrawable(drawable);
            return;
        }
        this.n = true;
        this.m = drawable;
    }
    
    @Override
    public void a(final h h, final boolean b) {
        this.f();
        super.a(h, b);
    }
    
    @Override
    public void a(final j j, final p.a a) {
        a.a(j, 0);
        final ActionMenuView itemInvoker = (ActionMenuView)this.f;
        final ActionMenuItemView actionMenuItemView = (ActionMenuItemView)a;
        actionMenuItemView.setItemInvoker(itemInvoker);
        if (this.A == null) {
            this.A = new b();
        }
        actionMenuItemView.setPopupCallback((ActionMenuItemView.b)this.A);
    }
    
    public void a(final ActionMenuView f) {
        ((ActionMenuView)(this.f = f)).a(this.c);
    }
    
    @Override
    public void a(final boolean b) {
        if (b) {
            super.a((u)null);
        }
        else if (this.c != null) {
            this.c.a(false);
        }
    }
    
    @Override
    public boolean a(final int n, final j j) {
        return j.j();
    }
    
    @Override
    public boolean a(final u u) {
        if (u.hasVisibleItems()) {
            u u2;
            for (u2 = u; u2.s() != this.c; u2 = (u)u2.s()) {}
            final View a = this.a(u2.getItem());
            if (a != null) {
                this.l = u.getItem().getItemId();
                while (true) {
                    for (int size = u.size(), i = 0; i < size; ++i) {
                        final MenuItem item = u.getItem(i);
                        if (item.isVisible() && item.getIcon() != null) {
                            final boolean b = true;
                            (this.i = new a(this.b, u, a)).a(b);
                            this.i.a();
                            super.a(u);
                            return true;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
        }
        return false;
    }
    
    public boolean a(final ViewGroup viewGroup, final int n) {
        return viewGroup.getChildAt(n) != this.g && super.a(viewGroup, n);
    }
    
    @Override
    public void b(final boolean b) {
        final boolean b2 = true;
        final boolean b3 = false;
        final ViewGroup viewGroup = (ViewGroup)((View)this.f).getParent();
        if (viewGroup != null) {
            android.support.v7.e.a.a(viewGroup);
        }
        super.b(b);
        ((View)this.f).requestLayout();
        if (this.c != null) {
            final ArrayList<j> k = this.c.k();
            for (int size = k.size(), i = 0; i < size; ++i) {
                final android.support.v4.g.e a = k.get(i).a();
                if (a != null) {
                    a.a((android.support.v4.g.e.a)this);
                }
            }
        }
        ArrayList<j> l;
        if (this.c != null) {
            l = this.c.l();
        }
        else {
            l = null;
        }
        int n = b3 ? 1 : 0;
        if (this.o) {
            n = (b3 ? 1 : 0);
            if (l != null) {
                final int size2 = l.size();
                if (size2 == 1) {
                    if (!l.get(0).isActionViewExpanded()) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                }
                else {
                    n = ((size2 > 0 && b2) ? 1 : 0);
                }
            }
        }
        if (n != 0) {
            if (this.g == null) {
                this.g = new d(this.a);
            }
            final ViewGroup viewGroup2 = (ViewGroup)this.g.getParent();
            if (viewGroup2 != this.f) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView((View)this.g);
                }
                final ActionMenuView actionMenuView = (ActionMenuView)this.f;
                actionMenuView.addView((View)this.g, (ViewGroup$LayoutParams)actionMenuView.c());
            }
        }
        else if (this.g != null && this.g.getParent() == this.f) {
            ((ViewGroup)this.f).removeView((View)this.g);
        }
        ((ActionMenuView)this.f).setOverflowReserved(this.o);
    }
    
    @Override
    public boolean b() {
        ArrayList<j> i;
        int size;
        if (this.c != null) {
            i = this.c.i();
            size = i.size();
        }
        else {
            size = 0;
            i = null;
        }
        int s = this.s;
        final int r = this.r;
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final ViewGroup viewGroup = (ViewGroup)this.f;
        int n = 0;
        int n2 = 0;
        boolean b = false;
        for (int j = 0; j < size; ++j) {
            final j k = i.get(j);
            if (k.l()) {
                ++n;
            }
            else if (k.k()) {
                ++n2;
            }
            else {
                b = true;
            }
            if (this.w && k.isActionViewExpanded()) {
                s = 0;
            }
        }
        int n3 = s;
        if (this.o && (b || n + n2 > (n3 = s))) {
            n3 = s - 1;
        }
        final int n4 = n3 - n;
        final SparseBooleanArray y = this.y;
        y.clear();
        int n5 = 0;
        int n6;
        if (this.u) {
            n5 = r / this.x;
            n6 = r % this.x / n5 + this.x;
        }
        else {
            n6 = 0;
        }
        int l = 0;
        final boolean b2 = false;
        int n7 = n5;
        int n8 = n4;
        int n9 = r;
        int n10 = b2 ? 1 : 0;
        while (l < size) {
            final j m = i.get(l);
            int n11;
            int n13;
            int n14;
            if (m.l()) {
                final View a = this.a(m, this.z, viewGroup);
                if (this.z == null) {
                    this.z = a;
                }
                if (this.u) {
                    n11 = n7 - ActionMenuView.a(a, n6, n7, measureSpec, 0);
                }
                else {
                    a.measure(measureSpec, measureSpec);
                    n11 = n7;
                }
                final int measuredWidth = a.getMeasuredWidth();
                if (n10 == 0) {
                    n10 = measuredWidth;
                }
                final int groupId = m.getGroupId();
                if (groupId != 0) {
                    y.put(groupId, true);
                }
                m.d(true);
                final int n12 = n9 - measuredWidth;
                n13 = n8;
                n14 = n12;
            }
            else if (m.k()) {
                final int groupId2 = m.getGroupId();
                final boolean value = y.get(groupId2);
                boolean b3 = (n8 > 0 || value) && n9 > 0 && (!this.u || n7 > 0);
                int n19;
                int n20;
                if (b3) {
                    final View a2 = this.a(m, this.z, viewGroup);
                    if (this.z == null) {
                        this.z = a2;
                    }
                    if (this.u) {
                        final int a3 = ActionMenuView.a(a2, n6, n7, measureSpec, 0);
                        if (a3 == 0) {
                            b3 = false;
                        }
                        n7 -= a3;
                    }
                    else {
                        a2.measure(measureSpec, measureSpec);
                    }
                    final int measuredWidth2 = a2.getMeasuredWidth();
                    final int n15 = n9 - measuredWidth2;
                    int n16 = n10;
                    if (n10 == 0) {
                        n16 = measuredWidth2;
                    }
                    if (this.u) {
                        b3 &= (n15 >= 0);
                        final int n17 = n16;
                        final int n18 = n7;
                        n9 = n15;
                        n19 = n17;
                        n20 = n18;
                    }
                    else {
                        b3 &= (n15 + n16 > 0);
                        n20 = n7;
                        n19 = n16;
                        n9 = n15;
                    }
                }
                else {
                    final int n21 = n10;
                    n20 = n7;
                    n19 = n21;
                }
                if (b3 && groupId2 != 0) {
                    y.put(groupId2, true);
                }
                else if (value) {
                    y.put(groupId2, false);
                    int n23;
                    for (int n22 = 0; n22 < l; ++n22, n8 = n23) {
                        final j j2 = i.get(n22);
                        n23 = n8;
                        if (j2.getGroupId() == groupId2) {
                            n23 = n8;
                            if (j2.j()) {
                                n23 = n8 + 1;
                            }
                            j2.d(false);
                        }
                    }
                }
                int n24 = n8;
                if (b3) {
                    n24 = n8 - 1;
                }
                m.d(b3);
                n14 = n9;
                final int n25 = n24;
                n11 = n20;
                n10 = n19;
                n13 = n25;
            }
            else {
                m.d(false);
                final int n26 = n9;
                final int n27 = n8;
                n14 = n26;
                n11 = n7;
                n13 = n27;
            }
            final int n28 = l + 1;
            final int n29 = n13;
            n7 = n11;
            n9 = n14;
            n8 = n29;
            l = n28;
        }
        return true;
    }
    
    public Drawable c() {
        if (this.g != null) {
            return this.g.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }
    
    public void c(final boolean o) {
        this.o = o;
        this.p = true;
    }
    
    public void d(final boolean w) {
        this.w = w;
    }
    
    public boolean d() {
        if (this.o && !this.h() && this.c != null && this.f != null && this.j == null && !this.c.l().isEmpty()) {
            this.j = new c(new e(this.b, this.c, (View)this.g, true));
            ((View)this.f).post((Runnable)this.j);
            super.a((u)null);
            return true;
        }
        return false;
    }
    
    public boolean e() {
        if (this.j != null && this.f != null) {
            ((View)this.f).removeCallbacks((Runnable)this.j);
            this.j = null;
            return true;
        }
        final e h = this.h;
        if (h != null) {
            h.d();
            return true;
        }
        return false;
    }
    
    public boolean f() {
        return this.e() | this.g();
    }
    
    public boolean g() {
        if (this.i != null) {
            this.i.d();
            return true;
        }
        return false;
    }
    
    public boolean h() {
        return this.h != null && this.h.f();
    }
    
    public boolean i() {
        return this.j != null || this.h();
    }
    
    private class a extends n
    {
        public a(final Context context, final u u, final View view) {
            super(context, u, view, false, android.support.v7.a.a.a.actionOverflowMenuStyle);
            if (!((j)u.getItem()).j()) {
                Object g;
                if (d.this.g == null) {
                    g = d.this.f;
                }
                else {
                    g = d.this.g;
                }
                this.a((View)g);
            }
            this.a(d.this.k);
        }
        
        @Override
        protected void e() {
            d.this.i = null;
            d.this.l = 0;
            super.e();
        }
    }
    
    private class b extends ActionMenuItemView.b
    {
        @Override
        public s a() {
            if (d.this.i != null) {
                return d.this.i.b();
            }
            return null;
        }
    }
    
    private class c implements Runnable
    {
        private e b;
        
        public c(final e b) {
            this.b = b;
        }
        
        @Override
        public void run() {
            if (d.this.c != null) {
                d.this.c.f();
            }
            final View view = (View)d.this.f;
            if (view != null && view.getWindowToken() != null && this.b.c()) {
                d.this.h = this.b;
            }
            d.this.j = null;
        }
    }
    
    private class d extends p implements ActionMenuView.a
    {
        private final float[] b;
        
        public d(final Context context) {
            super(context, null, android.support.v7.a.a.a.actionOverflowButtonStyle);
            this.b = new float[2];
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            this.setOnTouchListener((View$OnTouchListener)new ag(this) {
                @Override
                public s a() {
                    if (d.this.h == null) {
                        return null;
                    }
                    return d.this.h.b();
                }
                
                public boolean b() {
                    d.this.d();
                    return true;
                }
                
                public boolean c() {
                    if (d.this.j != null) {
                        return false;
                    }
                    d.this.e();
                    return true;
                }
            });
        }
        
        @Override
        public boolean c() {
            return false;
        }
        
        @Override
        public boolean d() {
            return false;
        }
        
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            this.playSoundEffect(0);
            android.support.v7.widget.d.this.d();
            return true;
        }
        
        protected boolean setFrame(int n, int height, int paddingTop, int paddingBottom) {
            final boolean setFrame = super.setFrame(n, height, paddingTop, paddingBottom);
            final Drawable drawable = this.getDrawable();
            final Drawable background = this.getBackground();
            if (drawable != null && background != null) {
                final int width = this.getWidth();
                height = this.getHeight();
                n = Math.max(width, height) / 2;
                final int paddingLeft = this.getPaddingLeft();
                final int paddingRight = this.getPaddingRight();
                paddingTop = this.getPaddingTop();
                paddingBottom = this.getPaddingBottom();
                final int n2 = (width + (paddingLeft - paddingRight)) / 2;
                height = (height + (paddingTop - paddingBottom)) / 2;
                android.support.v4.c.a.a.a(background, n2 - n, height - n, n2 + n, height + n);
            }
            return setFrame;
        }
    }
    
    private class e extends n
    {
        public e(final Context context, final h h, final View view, final boolean b) {
            super(context, h, view, b, android.support.v7.a.a.a.actionOverflowMenuStyle);
            this.a(8388613);
            this.a(d.this.k);
        }
        
        @Override
        protected void e() {
            if (d.this.c != null) {
                d.this.c.close();
            }
            d.this.h = null;
            super.e();
        }
    }
    
    private class f implements o.a
    {
        @Override
        public void a(final h h, final boolean b) {
            if (h instanceof u) {
                h.p().a(false);
            }
            final o.a a = d.this.a();
            if (a != null) {
                a.a(h, b);
            }
        }
        
        @Override
        public boolean a(final h h) {
            if (h == null) {
                return false;
            }
            d.this.l = ((u)h).getItem().getItemId();
            final o.a a = d.this.a();
            return a != null && a.a(h);
        }
    }
}
