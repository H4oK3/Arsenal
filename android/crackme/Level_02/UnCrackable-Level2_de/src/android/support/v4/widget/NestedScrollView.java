// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.support.v4.g.a.a;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.support.v4.g.ac;
import android.os.Parcelable;
import android.widget.FrameLayout$LayoutParams;
import android.util.Log;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$MeasureSpec;
import android.graphics.Canvas;
import android.view.ViewGroup$LayoutParams;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;
import android.util.TypedValue;
import android.view.ViewParent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import android.content.res.TypedArray;
import android.support.v4.g.b;
import android.support.v4.g.ae;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v4.g.w;
import android.support.v4.g.y;
import android.view.VelocityTracker;
import android.view.View;
import android.graphics.Rect;
import android.support.v4.g.x;
import android.support.v4.g.v;
import android.support.v4.g.aa;
import android.widget.FrameLayout;

public class NestedScrollView extends FrameLayout implements aa, v, x
{
    private static final a v;
    private static final int[] w;
    private b A;
    private long a;
    private final Rect b;
    private t c;
    private h d;
    private h e;
    private int f;
    private boolean g;
    private boolean h;
    private View i;
    private boolean j;
    private VelocityTracker k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int[] r;
    private final int[] s;
    private int t;
    private c u;
    private final y x;
    private final w y;
    private float z;
    
    static {
        v = new a();
        w = new int[] { 16843130 };
    }
    
    public NestedScrollView(final Context context) {
        this(context, null);
    }
    
    public NestedScrollView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NestedScrollView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.b = new Rect();
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = false;
        this.m = true;
        this.q = -1;
        this.r = new int[2];
        this.s = new int[2];
        this.a();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, NestedScrollView.w, n, 0);
        this.setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.x = new y((ViewGroup)this);
        this.y = new w((View)this);
        this.setNestedScrollingEnabled(true);
        ae.a((View)this, NestedScrollView.v);
    }
    
    private View a(final boolean b, final int n, final int n2) {
        final ArrayList focusables = this.getFocusables(2);
        View view = null;
        int n3 = 0;
        final int size = focusables.size();
        int i = 0;
    Label_0096_Outer:
        while (i < size) {
            View view2 = focusables.get(i);
            final int top = view2.getTop();
            final int bottom = view2.getBottom();
            while (true) {
                Label_0192: {
                    if (n >= bottom || top >= n2) {
                        break Label_0192;
                    }
                    boolean b2;
                    if (n < top && bottom < n2) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    if (view == null) {
                        n3 = (b2 ? 1 : 0);
                    }
                    else {
                        boolean b3;
                        if ((b && top < view.getTop()) || (!b && bottom > view.getBottom())) {
                            b3 = true;
                        }
                        else {
                            b3 = false;
                        }
                        if (n3 != 0) {
                            if (!b2 || !b3) {
                                break Label_0192;
                            }
                        }
                        else if (b2) {
                            n3 = 1;
                        }
                        else if (!b3) {
                            break Label_0192;
                        }
                    }
                    ++i;
                    view = view2;
                    continue Label_0096_Outer;
                }
                view2 = view;
                continue;
            }
        }
        return view;
    }
    
    private void a() {
        this.c = android.support.v4.widget.t.a(this.getContext(), null);
        this.setFocusable(true);
        this.setDescendantFocusability(262144);
        this.setWillNotDraw(false);
        final ViewConfiguration value = ViewConfiguration.get(this.getContext());
        this.n = value.getScaledTouchSlop();
        this.o = value.getScaledMinimumFlingVelocity();
        this.p = value.getScaledMaximumFlingVelocity();
    }
    
    private void a(final MotionEvent motionEvent) {
        final int n = (motionEvent.getAction() & 0xFF00) >> 8;
        if (motionEvent.getPointerId(n) == this.q) {
            int n2;
            if (n == 0) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            this.f = (int)motionEvent.getY(n2);
            this.q = motionEvent.getPointerId(n2);
            if (this.k != null) {
                this.k.clear();
            }
        }
    }
    
    private boolean a(final int n, int n2, final int n3) {
        final boolean b = false;
        final int height = this.getHeight();
        final int scrollY = this.getScrollY();
        final int n4 = scrollY + height;
        final boolean b2 = n == 33;
        Object a;
        if ((a = this.a(b2, n2, n3)) == null) {
            a = this;
        }
        boolean b3;
        if (n2 >= scrollY && n3 <= n4) {
            b3 = b;
        }
        else {
            if (b2) {
                n2 -= scrollY;
            }
            else {
                n2 = n3 - n4;
            }
            this.e(n2);
            b3 = true;
        }
        if (a != this.findFocus()) {
            ((View)a).requestFocus(n);
        }
        return b3;
    }
    
    private boolean a(final Rect rect, final boolean b) {
        final int a = this.a(rect);
        final boolean b2 = a != 0;
        if (b2) {
            if (!b) {
                this.a(0, a);
                return b2;
            }
            this.scrollBy(0, a);
        }
        return b2;
    }
    
    private boolean a(final View view) {
        boolean b = false;
        if (!this.a(view, 0, this.getHeight())) {
            b = true;
        }
        return b;
    }
    
    private boolean a(final View view, final int n, final int n2) {
        view.getDrawingRect(this.b);
        this.offsetDescendantRectToMyCoords(view, this.b);
        return this.b.bottom + n >= this.getScrollY() && this.b.top - n <= this.getScrollY() + n2;
    }
    
    private static boolean a(final View view, final View view2) {
        if (view == view2) {
            return true;
        }
        final ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && a((View)parent, view2);
    }
    
    private static int b(final int n, final int n2, final int n3) {
        int n4;
        if (n2 >= n3 || n < 0) {
            n4 = 0;
        }
        else {
            n4 = n;
            if (n2 + n > n3) {
                return n3 - n2;
            }
        }
        return n4;
    }
    
    private void b(final View view) {
        view.getDrawingRect(this.b);
        this.offsetDescendantRectToMyCoords(view, this.b);
        final int a = this.a(this.b);
        if (a != 0) {
            this.scrollBy(0, a);
        }
    }
    
    private boolean b() {
        final boolean b = false;
        final View child = this.getChildAt(0);
        boolean b2 = b;
        if (child != null) {
            final int height = child.getHeight();
            b2 = b;
            if (this.getHeight() < height + this.getPaddingTop() + this.getPaddingBottom()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    private void c() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
            return;
        }
        this.k.clear();
    }
    
    private boolean c(final int n, final int n2) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.getChildCount() > 0) {
            final int scrollY = this.getScrollY();
            final View child = this.getChildAt(0);
            b2 = b;
            if (n2 >= child.getTop() - scrollY) {
                b2 = b;
                if (n2 < child.getBottom() - scrollY) {
                    b2 = b;
                    if (n >= child.getLeft()) {
                        b2 = b;
                        if (n < child.getRight()) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    private void d() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }
    
    private void e() {
        if (this.k != null) {
            this.k.recycle();
            this.k = null;
        }
    }
    
    private void e(final int n) {
        if (n != 0) {
            if (!this.m) {
                this.scrollBy(0, n);
                return;
            }
            this.a(0, n);
        }
    }
    
    private void f() {
        this.j = false;
        this.e();
        this.stopNestedScroll();
        if (this.d != null) {
            this.d.b();
            this.e.b();
        }
    }
    
    private void f(final int n) {
        final int scrollY = this.getScrollY();
        final boolean b = (scrollY > 0 || n > 0) && (scrollY < this.getScrollRange() || n < 0);
        if (!this.dispatchNestedPreFling(0.0f, n)) {
            this.dispatchNestedFling(0.0f, n, b);
            if (b) {
                this.d(n);
            }
        }
    }
    
    private void g() {
        if (this.getOverScrollMode() != 2) {
            if (this.d == null) {
                final Context context = this.getContext();
                this.d = new h(context);
                this.e = new h(context);
            }
            return;
        }
        this.d = null;
        this.e = null;
    }
    
    private float getVerticalScrollFactorCompat() {
        if (this.z == 0.0f) {
            final TypedValue typedValue = new TypedValue();
            final Context context = this.getContext();
            if (!context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.z;
    }
    
    protected int a(final Rect rect) {
        if (this.getChildCount() == 0) {
            return 0;
        }
        final int height = this.getHeight();
        final int scrollY = this.getScrollY();
        final int n = scrollY + height;
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        int n2 = scrollY;
        if (rect.top > 0) {
            n2 = scrollY + verticalFadingEdgeLength;
        }
        int n3 = n;
        if (rect.bottom < this.getChildAt(0).getHeight()) {
            n3 = n - verticalFadingEdgeLength;
        }
        int n5;
        if (rect.bottom > n3 && rect.top > n2) {
            int n4;
            if (rect.height() > height) {
                n4 = rect.top - n2 + 0;
            }
            else {
                n4 = rect.bottom - n3 + 0;
            }
            n5 = Math.min(n4, this.getChildAt(0).getBottom() - n3);
        }
        else if (rect.top < n2 && rect.bottom < n3) {
            int n6;
            if (rect.height() > height) {
                n6 = 0 - (n3 - rect.bottom);
            }
            else {
                n6 = 0 - (n2 - rect.top);
            }
            n5 = Math.max(n6, -this.getScrollY());
        }
        else {
            n5 = 0;
        }
        return n5;
    }
    
    public final void a(int n, int max) {
        if (this.getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.a > 250L) {
            n = this.getHeight();
            final int max2 = Math.max(0, this.getChildAt(0).getHeight() - (n - this.getPaddingBottom() - this.getPaddingTop()));
            n = this.getScrollY();
            max = Math.max(0, Math.min(n + max, max2));
            this.c.a(this.getScrollX(), n, 0, max - n);
            ae.a((View)this);
        }
        else {
            if (!this.c.a()) {
                this.c.g();
            }
            this.scrollBy(n, max);
        }
        this.a = AnimationUtils.currentAnimationTimeMillis();
    }
    
    public boolean a(final int n) {
        int n2;
        if (n == 130) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final int height = this.getHeight();
        if (n2 != 0) {
            this.b.top = this.getScrollY() + height;
            final int childCount = this.getChildCount();
            if (childCount > 0) {
                final View child = this.getChildAt(childCount - 1);
                if (this.b.top + height > child.getBottom()) {
                    this.b.top = child.getBottom() - height;
                }
            }
        }
        else {
            this.b.top = this.getScrollY() - height;
            if (this.b.top < 0) {
                this.b.top = 0;
            }
        }
        this.b.bottom = this.b.top + height;
        return this.a(n, this.b.top, this.b.bottom);
    }
    
    boolean a(int n, int n2, int n3, int n4, int n5, final int n6, int n7, int n8, final boolean b) {
        final int overScrollMode = this.getOverScrollMode();
        boolean b2;
        if (this.computeHorizontalScrollRange() > this.computeHorizontalScrollExtent()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (this.computeVerticalScrollRange() > this.computeVerticalScrollExtent()) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        int n9;
        if (overScrollMode == 0 || (overScrollMode == 1 && b2)) {
            n9 = 1;
        }
        else {
            n9 = 0;
        }
        int n10;
        if (overScrollMode == 0 || (overScrollMode == 1 && b3)) {
            n10 = 1;
        }
        else {
            n10 = 0;
        }
        n += n3;
        if (n9 == 0) {
            n7 = 0;
        }
        n2 += n4;
        if (n10 == 0) {
            n8 = 0;
        }
        n4 = -n7;
        n7 += n5;
        n3 = -n8;
        n5 = n8 + n6;
        boolean b4;
        if (n > n7) {
            b4 = true;
            n = n7;
        }
        else if (n < n4) {
            b4 = true;
            n = n4;
        }
        else {
            b4 = false;
        }
        boolean b5;
        if (n2 > n5) {
            b5 = true;
            n2 = n5;
        }
        else if (n2 < n3) {
            b5 = true;
            n2 = n3;
        }
        else {
            b5 = false;
        }
        if (b5) {
            this.c.a(n, n2, 0, 0, 0, this.getScrollRange());
        }
        this.onOverScrolled(n, n2, b4, b5);
        return b4 || b5;
    }
    
    public boolean a(final KeyEvent keyEvent) {
        int n = 33;
        final boolean b = false;
        this.b.setEmpty();
        boolean b2;
        if (!this.b()) {
            b2 = b;
            if (this.isFocused()) {
                b2 = b;
                if (keyEvent.getKeyCode() != 4) {
                    View focus;
                    if ((focus = this.findFocus()) == this) {
                        focus = null;
                    }
                    final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, focus, 130);
                    b2 = (nextFocus != null && nextFocus != this && nextFocus.requestFocus(130));
                }
            }
        }
        else {
            b2 = b;
            if (keyEvent.getAction() == 0) {
                switch (keyEvent.getKeyCode()) {
                    default: {
                        return false;
                    }
                    case 19: {
                        if (!keyEvent.isAltPressed()) {
                            return this.c(33);
                        }
                        return this.b(33);
                    }
                    case 20: {
                        if (!keyEvent.isAltPressed()) {
                            return this.c(130);
                        }
                        return this.b(130);
                    }
                    case 62: {
                        if (!keyEvent.isShiftPressed()) {
                            n = 130;
                        }
                        this.a(n);
                        return false;
                    }
                }
            }
        }
        return b2;
    }
    
    public void addView(final View view) {
        if (this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }
    
    public void addView(final View view, final int n) {
        if (this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, n);
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, n, viewGroup$LayoutParams);
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (this.getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, viewGroup$LayoutParams);
    }
    
    public final void b(final int n, final int n2) {
        this.a(n - this.getScrollX(), n2 - this.getScrollY());
    }
    
    public boolean b(final int n) {
        int n2;
        if (n == 130) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final int height = this.getHeight();
        this.b.top = 0;
        this.b.bottom = height;
        if (n2 != 0) {
            final int childCount = this.getChildCount();
            if (childCount > 0) {
                this.b.bottom = this.getChildAt(childCount - 1).getBottom() + this.getPaddingBottom();
                this.b.top = this.b.bottom - height;
            }
        }
        return this.a(n, this.b.top, this.b.bottom);
    }
    
    public boolean c(int descendantFocusability) {
        View focus;
        if ((focus = this.findFocus()) == this) {
            focus = null;
        }
        final View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup)this, focus, descendantFocusability);
        final int maxScrollAmount = this.getMaxScrollAmount();
        if (nextFocus != null && this.a(nextFocus, maxScrollAmount, this.getHeight())) {
            nextFocus.getDrawingRect(this.b);
            this.offsetDescendantRectToMyCoords(nextFocus, this.b);
            this.e(this.a(this.b));
            nextFocus.requestFocus(descendantFocusability);
        }
        else {
            int scrollY;
            if (descendantFocusability == 33 && this.getScrollY() < maxScrollAmount) {
                scrollY = this.getScrollY();
            }
            else {
                scrollY = maxScrollAmount;
                if (descendantFocusability == 130) {
                    scrollY = maxScrollAmount;
                    if (this.getChildCount() > 0) {
                        final int bottom = this.getChildAt(0).getBottom();
                        final int n = this.getScrollY() + this.getHeight() - this.getPaddingBottom();
                        if (bottom - n < (scrollY = maxScrollAmount)) {
                            scrollY = bottom - n;
                        }
                    }
                }
            }
            if (scrollY == 0) {
                return false;
            }
            if (descendantFocusability != 130) {
                scrollY = -scrollY;
            }
            this.e(scrollY);
        }
        if (focus != null && focus.isFocused() && this.a(focus)) {
            descendantFocusability = this.getDescendantFocusability();
            this.setDescendantFocusability(131072);
            this.requestFocus();
            this.setDescendantFocusability(descendantFocusability);
        }
        return true;
    }
    
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }
    
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }
    
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }
    
    public void computeScroll() {
        if (this.c.f()) {
            final int scrollX = this.getScrollX();
            final int scrollY = this.getScrollY();
            final int b = this.c.b();
            final int c = this.c.c();
            if (scrollX != b || scrollY != c) {
                final int scrollRange = this.getScrollRange();
                final int overScrollMode = this.getOverScrollMode();
                int n;
                if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                this.a(b - scrollX, c - scrollY, scrollX, scrollY, 0, scrollRange, 0, 0, false);
                if (n != 0) {
                    this.g();
                    if (c <= 0 && scrollY > 0) {
                        this.d.a((int)this.c.e());
                    }
                    else if (c >= scrollRange && scrollY < scrollRange) {
                        this.e.a((int)this.c.e());
                    }
                }
            }
        }
    }
    
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }
    
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }
    
    public int computeVerticalScrollRange() {
        final int childCount = this.getChildCount();
        int n = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
        if (childCount != 0) {
            final int bottom = this.getChildAt(0).getBottom();
            final int scrollY = this.getScrollY();
            final int max = Math.max(0, bottom - n);
            if (scrollY < 0) {
                return bottom - scrollY;
            }
            n = bottom;
            if (scrollY > max) {
                return bottom + (scrollY - max);
            }
        }
        return n;
    }
    
    public void d(final int n) {
        if (this.getChildCount() > 0) {
            final int n2 = this.getHeight() - this.getPaddingBottom() - this.getPaddingTop();
            this.c.a(this.getScrollX(), this.getScrollY(), 0, n, 0, 0, 0, Math.max(0, this.getChildAt(0).getHeight() - n2), 0, n2 / 2);
            ae.a((View)this);
        }
    }
    
    public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || this.a(keyEvent);
    }
    
    public boolean dispatchNestedFling(final float n, final float n2, final boolean b) {
        return this.y.a(n, n2, b);
    }
    
    public boolean dispatchNestedPreFling(final float n, final float n2) {
        return this.y.a(n, n2);
    }
    
    public boolean dispatchNestedPreScroll(final int n, final int n2, final int[] array, final int[] array2) {
        return this.y.a(n, n2, array, array2);
    }
    
    public boolean dispatchNestedScroll(final int n, final int n2, final int n3, final int n4, final int[] array) {
        return this.y.a(n, n2, n3, n4, array);
    }
    
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (this.d != null) {
            final int scrollY = this.getScrollY();
            if (!this.d.a()) {
                final int save = canvas.save();
                final int width = this.getWidth();
                final int paddingLeft = this.getPaddingLeft();
                final int paddingRight = this.getPaddingRight();
                canvas.translate((float)this.getPaddingLeft(), (float)Math.min(0, scrollY));
                this.d.a(width - paddingLeft - paddingRight, this.getHeight());
                if (this.d.a(canvas)) {
                    ae.a((View)this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.e.a()) {
                final int save2 = canvas.save();
                final int n = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                final int height = this.getHeight();
                canvas.translate((float)(-n + this.getPaddingLeft()), (float)(Math.max(this.getScrollRange(), scrollY) + height));
                canvas.rotate(180.0f, (float)n, 0.0f);
                this.e.a(n, height);
                if (this.e.a(canvas)) {
                    ae.a((View)this);
                }
                canvas.restoreToCount(save2);
            }
        }
    }
    
    protected float getBottomFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        final int n = this.getChildAt(0).getBottom() - this.getScrollY() - (this.getHeight() - this.getPaddingBottom());
        if (n < verticalFadingEdgeLength) {
            return n / verticalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    public int getMaxScrollAmount() {
        return (int)(0.5f * this.getHeight());
    }
    
    public int getNestedScrollAxes() {
        return this.x.a();
    }
    
    int getScrollRange() {
        int max = 0;
        if (this.getChildCount() > 0) {
            max = Math.max(0, this.getChildAt(0).getHeight() - (this.getHeight() - this.getPaddingBottom() - this.getPaddingTop()));
        }
        return max;
    }
    
    protected float getTopFadingEdgeStrength() {
        if (this.getChildCount() == 0) {
            return 0.0f;
        }
        final int verticalFadingEdgeLength = this.getVerticalFadingEdgeLength();
        final int scrollY = this.getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }
    
    public boolean hasNestedScrollingParent() {
        return this.y.b();
    }
    
    public boolean isNestedScrollingEnabled() {
        return this.y.a();
    }
    
    protected void measureChild(final View view, final int n, final int n2) {
        view.measure(getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight(), view.getLayoutParams().width), View$MeasureSpec.makeMeasureSpec(0, 0));
    }
    
    protected void measureChildWithMargins(final View view, int childMeasureSpec, int topMargin, final int n, final int n2) {
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        childMeasureSpec = getChildMeasureSpec(childMeasureSpec, this.getPaddingLeft() + this.getPaddingRight() + viewGroup$MarginLayoutParams.leftMargin + viewGroup$MarginLayoutParams.rightMargin + topMargin, viewGroup$MarginLayoutParams.width);
        topMargin = viewGroup$MarginLayoutParams.topMargin;
        view.measure(childMeasureSpec, View$MeasureSpec.makeMeasureSpec(viewGroup$MarginLayoutParams.bottomMargin + topMargin, 0));
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = false;
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 0x2) != 0x0) {
            switch (motionEvent.getAction()) {
                case 8: {
                    if (this.j) {
                        break;
                    }
                    final float a = android.support.v4.g.t.a(motionEvent, 9);
                    if (a == 0.0f) {
                        break;
                    }
                    final int n = (int)(a * this.getVerticalScrollFactorCompat());
                    final int scrollRange = this.getScrollRange();
                    final int scrollY = this.getScrollY();
                    final int n2 = scrollY - n;
                    int n3;
                    if (n2 < 0) {
                        n3 = 0;
                    }
                    else if (n2 <= (n3 = scrollRange)) {
                        n3 = n2;
                    }
                    if (n3 != scrollY) {
                        super.scrollTo(this.getScrollX(), n3);
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        boolean j = false;
        final int action = motionEvent.getAction();
        if (action == 2 && this.j) {
            return true;
        }
        switch (action & 0xFF) {
            case 2: {
                final int q = this.q;
                if (q == -1) {
                    break;
                }
                final int pointerIndex = motionEvent.findPointerIndex(q);
                if (pointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + q + " in onInterceptTouchEvent");
                    break;
                }
                final int f = (int)motionEvent.getY(pointerIndex);
                if (Math.abs(f - this.f) <= this.n || (this.getNestedScrollAxes() & 0x2) != 0x0) {
                    break;
                }
                this.j = true;
                this.f = f;
                this.d();
                this.k.addMovement(motionEvent);
                this.t = 0;
                final ViewParent parent = this.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            }
            case 0: {
                final int f2 = (int)motionEvent.getY();
                if (!this.c((int)motionEvent.getX(), f2)) {
                    this.j = false;
                    this.e();
                    break;
                }
                this.f = f2;
                this.q = motionEvent.getPointerId(0);
                this.c();
                this.k.addMovement(motionEvent);
                this.c.f();
                if (!this.c.a()) {
                    j = true;
                }
                this.j = j;
                this.startNestedScroll(2);
                break;
            }
            case 1:
            case 3: {
                this.j = false;
                this.q = -1;
                this.e();
                if (this.c.a(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    ae.a((View)this);
                }
                this.stopNestedScroll();
                break;
            }
            case 6: {
                this.a(motionEvent);
                break;
            }
        }
        return this.j;
    }
    
    protected void onLayout(final boolean b, int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.g = false;
        if (this.i != null && a(this.i, (View)this)) {
            this.b(this.i);
        }
        this.i = null;
        if (!this.h) {
            if (this.u != null) {
                this.scrollTo(this.getScrollX(), this.u.a);
                this.u = null;
            }
            if (this.getChildCount() > 0) {
                n = this.getChildAt(0).getMeasuredHeight();
            }
            else {
                n = 0;
            }
            n = Math.max(0, n - (n4 - n2 - this.getPaddingBottom() - this.getPaddingTop()));
            if (this.getScrollY() > n) {
                this.scrollTo(this.getScrollX(), n);
            }
            else if (this.getScrollY() < 0) {
                this.scrollTo(this.getScrollX(), 0);
            }
        }
        this.scrollTo(this.getScrollX(), this.getScrollY());
        this.h = true;
    }
    
    protected void onMeasure(final int n, int measuredHeight) {
        super.onMeasure(n, measuredHeight);
        if (this.l && View$MeasureSpec.getMode(measuredHeight) != 0 && this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            measuredHeight = this.getMeasuredHeight();
            if (child.getMeasuredHeight() < measuredHeight) {
                child.measure(getChildMeasureSpec(n, this.getPaddingLeft() + this.getPaddingRight(), ((FrameLayout$LayoutParams)child.getLayoutParams()).width), View$MeasureSpec.makeMeasureSpec(measuredHeight - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
            }
        }
    }
    
    public boolean onNestedFling(final View view, final float n, final float n2, final boolean b) {
        if (!b) {
            this.f((int)n2);
            return true;
        }
        return false;
    }
    
    public boolean onNestedPreFling(final View view, final float n, final float n2) {
        return this.dispatchNestedPreFling(n, n2);
    }
    
    public void onNestedPreScroll(final View view, final int n, final int n2, final int[] array) {
        this.dispatchNestedPreScroll(n, n2, array, null);
    }
    
    public void onNestedScroll(final View view, int scrollY, final int n, final int n2, final int n3) {
        scrollY = this.getScrollY();
        this.scrollBy(0, n3);
        scrollY = this.getScrollY() - scrollY;
        this.dispatchNestedScroll(0, scrollY, 0, n3 - scrollY, null);
    }
    
    public void onNestedScrollAccepted(final View view, final View view2, final int n) {
        this.x.a(view, view2, n);
        this.startNestedScroll(2);
    }
    
    protected void onOverScrolled(final int n, final int n2, final boolean b, final boolean b2) {
        super.scrollTo(n, n2);
    }
    
    protected boolean onRequestFocusInDescendants(final int n, final Rect rect) {
        int n2;
        if (n == 2) {
            n2 = 130;
        }
        else if ((n2 = n) == 1) {
            n2 = 33;
        }
        View view;
        if (rect == null) {
            view = FocusFinder.getInstance().findNextFocus((ViewGroup)this, (View)null, n2);
        }
        else {
            view = FocusFinder.getInstance().findNextFocusFromRect((ViewGroup)this, rect, n2);
        }
        return view != null && !this.a(view) && view.requestFocus(n2, rect);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final c u = (c)parcelable;
        super.onRestoreInstanceState(u.getSuperState());
        this.u = u;
        this.requestLayout();
    }
    
    protected Parcelable onSaveInstanceState() {
        final c c = new c(super.onSaveInstanceState());
        c.a = this.getScrollY();
        return (Parcelable)c;
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.A != null) {
            this.A.a(this, n, n2, n3, n4);
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        final View focus = this.findFocus();
        if (focus != null && this != focus && this.a(focus, 0, n4)) {
            focus.getDrawingRect(this.b);
            this.offsetDescendantRectToMyCoords(focus, this.b);
            this.e(this.a(this.b));
        }
    }
    
    public boolean onStartNestedScroll(final View view, final View view2, final int n) {
        return (n & 0x2) != 0x0;
    }
    
    public void onStopNestedScroll(final View view) {
        this.x.a(view);
        this.stopNestedScroll();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.d();
        final MotionEvent obtain = MotionEvent.obtain(motionEvent);
        final int a = android.support.v4.g.t.a(motionEvent);
        if (a == 0) {
            this.t = 0;
        }
        obtain.offsetLocation(0.0f, (float)this.t);
        switch (a) {
            case 0: {
                if (this.getChildCount() == 0) {
                    return false;
                }
                final boolean j = !this.c.a();
                this.j = j;
                if (j) {
                    final ViewParent parent = this.getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if (!this.c.a()) {
                    this.c.g();
                }
                this.f = (int)motionEvent.getY();
                this.q = motionEvent.getPointerId(0);
                this.startNestedScroll(2);
                break;
            }
            case 2: {
                final int pointerIndex = motionEvent.findPointerIndex(this.q);
                if (pointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.q + " in onTouchEvent");
                    break;
                }
                final int n = (int)motionEvent.getY(pointerIndex);
                int n3;
                final int n2 = n3 = this.f - n;
                if (this.dispatchNestedPreScroll(0, n2, this.s, this.r)) {
                    n3 = n2 - this.s[1];
                    obtain.offsetLocation(0.0f, (float)this.r[1]);
                    this.t += this.r[1];
                }
                if (!this.j && Math.abs(n3) > this.n) {
                    final ViewParent parent2 = this.getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    this.j = true;
                    if (n3 > 0) {
                        n3 -= this.n;
                    }
                    else {
                        n3 += this.n;
                    }
                }
                if (!this.j) {
                    break;
                }
                this.f = n - this.r[1];
                final int scrollY = this.getScrollY();
                final int scrollRange = this.getScrollRange();
                final int overScrollMode = this.getOverScrollMode();
                final boolean b = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                if (this.a(0, n3, 0, this.getScrollY(), 0, scrollRange, 0, 0, true) && !this.hasNestedScrollingParent()) {
                    this.k.clear();
                }
                final int n4 = this.getScrollY() - scrollY;
                if (this.dispatchNestedScroll(0, n4, 0, n3 - n4, this.r)) {
                    this.f -= this.r[1];
                    obtain.offsetLocation(0.0f, (float)this.r[1]);
                    this.t += this.r[1];
                    break;
                }
                if (!b) {
                    break;
                }
                this.g();
                final int n5 = scrollY + n3;
                if (n5 < 0) {
                    this.d.a(n3 / this.getHeight(), motionEvent.getX(pointerIndex) / this.getWidth());
                    if (!this.e.a()) {
                        this.e.b();
                    }
                }
                else if (n5 > scrollRange) {
                    this.e.a(n3 / this.getHeight(), 1.0f - motionEvent.getX(pointerIndex) / this.getWidth());
                    if (!this.d.a()) {
                        this.d.b();
                    }
                }
                if (this.d != null && (!this.d.a() || !this.e.a())) {
                    ae.a((View)this);
                    break;
                }
                break;
            }
            case 1: {
                if (this.j) {
                    final VelocityTracker k = this.k;
                    k.computeCurrentVelocity(1000, (float)this.p);
                    final int n6 = (int)ac.a(k, this.q);
                    if (Math.abs(n6) > this.o) {
                        this.f(-n6);
                    }
                    else if (this.c.a(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                        ae.a((View)this);
                    }
                }
                this.q = -1;
                this.f();
                break;
            }
            case 3: {
                if (this.j && this.getChildCount() > 0 && this.c.a(this.getScrollX(), this.getScrollY(), 0, 0, 0, this.getScrollRange())) {
                    ae.a((View)this);
                }
                this.q = -1;
                this.f();
                break;
            }
            case 5: {
                final int b2 = android.support.v4.g.t.b(motionEvent);
                this.f = (int)motionEvent.getY(b2);
                this.q = motionEvent.getPointerId(b2);
                break;
            }
            case 6: {
                this.a(motionEvent);
                this.f = (int)motionEvent.getY(motionEvent.findPointerIndex(this.q));
                break;
            }
        }
        if (this.k != null) {
            this.k.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }
    
    public void requestChildFocus(final View view, final View i) {
        if (!this.g) {
            this.b(i);
        }
        else {
            this.i = i;
        }
        super.requestChildFocus(view, i);
    }
    
    public boolean requestChildRectangleOnScreen(final View view, final Rect rect, final boolean b) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return this.a(rect, b);
    }
    
    public void requestDisallowInterceptTouchEvent(final boolean b) {
        if (b) {
            this.e();
        }
        super.requestDisallowInterceptTouchEvent(b);
    }
    
    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }
    
    public void scrollTo(int b, int b2) {
        if (this.getChildCount() > 0) {
            final View child = this.getChildAt(0);
            b = b(b, this.getWidth() - this.getPaddingRight() - this.getPaddingLeft(), child.getWidth());
            b2 = b(b2, this.getHeight() - this.getPaddingBottom() - this.getPaddingTop(), child.getHeight());
            if (b != this.getScrollX() || b2 != this.getScrollY()) {
                super.scrollTo(b, b2);
            }
        }
    }
    
    public void setFillViewport(final boolean l) {
        if (l != this.l) {
            this.l = l;
            this.requestLayout();
        }
    }
    
    public void setNestedScrollingEnabled(final boolean b) {
        this.y.a(b);
    }
    
    public void setOnScrollChangeListener(final b a) {
        this.A = a;
    }
    
    public void setSmoothScrollingEnabled(final boolean m) {
        this.m = m;
    }
    
    public boolean shouldDelayChildPressedState() {
        return true;
    }
    
    public boolean startNestedScroll(final int n) {
        return this.y.a(n);
    }
    
    public void stopNestedScroll() {
        this.y.c();
    }
    
    static class a extends b
    {
        @Override
        public void a(final View view, final android.support.v4.g.a.b b) {
            super.a(view, b);
            final NestedScrollView nestedScrollView = (NestedScrollView)view;
            b.a(ScrollView.class.getName());
            if (nestedScrollView.isEnabled()) {
                final int scrollRange = nestedScrollView.getScrollRange();
                if (scrollRange > 0) {
                    b.a(true);
                    if (nestedScrollView.getScrollY() > 0) {
                        b.a(8192);
                    }
                    if (nestedScrollView.getScrollY() < scrollRange) {
                        b.a(4096);
                    }
                }
            }
        }
        
        @Override
        public boolean a(final View view, int n, final Bundle bundle) {
            if (super.a(view, n, bundle)) {
                return true;
            }
            final NestedScrollView nestedScrollView = (NestedScrollView)view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            switch (n) {
                default: {
                    return false;
                }
                case 4096: {
                    n = Math.min(nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop() + nestedScrollView.getScrollY(), nestedScrollView.getScrollRange());
                    if (n != nestedScrollView.getScrollY()) {
                        nestedScrollView.b(0, n);
                        return true;
                    }
                    return false;
                }
                case 8192: {
                    n = nestedScrollView.getHeight();
                    n = Math.max(nestedScrollView.getScrollY() - (n - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop()), 0);
                    if (n != nestedScrollView.getScrollY()) {
                        nestedScrollView.b(0, n);
                        return true;
                    }
                    return false;
                }
            }
        }
        
        @Override
        public void d(final View view, final AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            final NestedScrollView nestedScrollView = (NestedScrollView)view;
            accessibilityEvent.setClassName((CharSequence)ScrollView.class.getName());
            final android.support.v4.g.a.h a = android.support.v4.g.a.a.a(accessibilityEvent);
            a.a(nestedScrollView.getScrollRange() > 0);
            a.a(nestedScrollView.getScrollX());
            a.b(nestedScrollView.getScrollY());
            a.c(nestedScrollView.getScrollX());
            a.d(nestedScrollView.getScrollRange());
        }
    }
    
    public interface b
    {
        void a(final NestedScrollView p0, final int p1, final int p2, final int p3, final int p4);
    }
    
    static class c extends View$BaseSavedState
    {
        public static final Parcelable$Creator<c> CREATOR;
        public int a;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<c>() {
                public c a(final Parcel parcel) {
                    return new c(parcel);
                }
                
                public c[] a(final int n) {
                    return new c[n];
                }
            };
        }
        
        c(final Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }
        
        c(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.a + "}";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.a);
        }
    }
}
