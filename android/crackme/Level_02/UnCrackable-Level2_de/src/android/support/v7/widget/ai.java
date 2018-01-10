// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.PopupWindow$OnDismissListener;
import android.view.View$OnTouchListener;
import android.support.v4.widget.o;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AdapterView;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.util.Log;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.widget.AdapterView$OnItemClickListener;
import android.graphics.drawable.Drawable;
import android.database.DataSetObserver;
import android.view.View;
import android.widget.ListAdapter;
import android.content.Context;
import android.widget.PopupWindow;
import android.os.Handler;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemSelectedListener;
import java.lang.reflect.Method;
import android.support.v7.view.menu.s;

public class ai implements s
{
    private static Method a;
    private static Method b;
    private static Method h;
    private AdapterView$OnItemSelectedListener A;
    private final d B;
    private final c C;
    private final a D;
    private Runnable E;
    private final Rect F;
    private Rect G;
    private boolean H;
    ae c;
    int d;
    final e e;
    final Handler f;
    PopupWindow g;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private boolean s;
    private boolean t;
    private View u;
    private int v;
    private DataSetObserver w;
    private View x;
    private Drawable y;
    private AdapterView$OnItemClickListener z;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             Landroid/widget/PopupWindow;.class
        //     2: ldc             "setClipToScreenEnabled"
        //     4: iconst_1       
        //     5: anewarray       Ljava/lang/Class;
        //     8: dup            
        //     9: iconst_0       
        //    10: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    13: aastore        
        //    14: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    17: putstatic       android/support/v7/widget/ai.a:Ljava/lang/reflect/Method;
        //    20: ldc             Landroid/widget/PopupWindow;.class
        //    22: ldc             "getMaxAvailableHeight"
        //    24: iconst_3       
        //    25: anewarray       Ljava/lang/Class;
        //    28: dup            
        //    29: iconst_0       
        //    30: ldc             Landroid/view/View;.class
        //    32: aastore        
        //    33: dup            
        //    34: iconst_1       
        //    35: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    38: aastore        
        //    39: dup            
        //    40: iconst_2       
        //    41: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    44: aastore        
        //    45: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    48: putstatic       android/support/v7/widget/ai.b:Ljava/lang/reflect/Method;
        //    51: ldc             Landroid/widget/PopupWindow;.class
        //    53: ldc             "setEpicenterBounds"
        //    55: iconst_1       
        //    56: anewarray       Ljava/lang/Class;
        //    59: dup            
        //    60: iconst_0       
        //    61: ldc             Landroid/graphics/Rect;.class
        //    63: aastore        
        //    64: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    67: putstatic       android/support/v7/widget/ai.h:Ljava/lang/reflect/Method;
        //    70: return         
        //    71: astore_0       
        //    72: ldc             "ListPopupWindow"
        //    74: ldc             "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well."
        //    76: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    79: pop            
        //    80: goto            20
        //    83: astore_0       
        //    84: ldc             "ListPopupWindow"
        //    86: ldc             "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well."
        //    88: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    91: pop            
        //    92: goto            51
        //    95: astore_0       
        //    96: ldc             "ListPopupWindow"
        //    98: ldc             "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well."
        //   100: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   103: pop            
        //   104: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     71     83     Ljava/lang/NoSuchMethodException;
        //  20     51     83     95     Ljava/lang/NoSuchMethodException;
        //  51     70     95     105    Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 57, Size: 57
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ai(final Context context) {
        this(context, null, android.support.v7.a.a.a.listPopupWindowStyle);
    }
    
    public ai(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, 0);
    }
    
    public ai(final Context i, final AttributeSet set, final int n, final int n2) {
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.q = true;
        this.r = 0;
        this.s = false;
        this.t = false;
        this.d = Integer.MAX_VALUE;
        this.v = 0;
        this.e = new e();
        this.B = new d();
        this.C = new c();
        this.D = new a();
        this.F = new Rect();
        this.i = i;
        this.f = new Handler(i.getMainLooper());
        final TypedArray obtainStyledAttributes = i.obtainStyledAttributes(set, android.support.v7.a.a.j.ListPopupWindow, n, n2);
        this.m = obtainStyledAttributes.getDimensionPixelOffset(android.support.v7.a.a.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = obtainStyledAttributes.getDimensionPixelOffset(android.support.v7.a.a.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.n != 0) {
            this.p = true;
        }
        obtainStyledAttributes.recycle();
        if (Build$VERSION.SDK_INT >= 11) {
            this.g = new r(i, set, n, n2);
        }
        else {
            this.g = new r(i, set, n);
        }
        this.g.setInputMethodMode(1);
    }
    
    private int a(final View view, final int n, final boolean b) {
        if (ai.b != null) {
            try {
                return (int)ai.b.invoke(this.g, view, n, b);
            }
            catch (Exception ex) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.g.getMaxAvailableHeight(view, n);
    }
    
    private void b() {
        if (this.u != null) {
            final ViewParent parent = this.u.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.u);
            }
        }
    }
    
    private void b(final boolean b) {
        if (ai.a == null) {
            return;
        }
        try {
            ai.a.invoke(this.g, b);
        }
        catch (Exception ex) {
            Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }
    
    private int f() {
        final boolean b = true;
        int n2;
        if (this.c == null) {
            final Context i = this.i;
            this.E = new Runnable() {
                @Override
                public void run() {
                    final View i = ai.this.i();
                    if (i != null && i.getWindowToken() != null) {
                        ai.this.a();
                    }
                }
            };
            this.c = this.a(i, !this.H);
            if (this.y != null) {
                this.c.setSelector(this.y);
            }
            this.c.setAdapter(this.j);
            this.c.setOnItemClickListener(this.z);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (n != -1) {
                        final ae c = ai.this.c;
                        if (c != null) {
                            c.setListSelectionHidden(false);
                        }
                    }
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            this.c.setOnScrollListener((AbsListView$OnScrollListener)this.C);
            if (this.A != null) {
                this.c.setOnItemSelectedListener(this.A);
            }
            Object c = this.c;
            final View u = this.u;
            if (u != null) {
                final LinearLayout linearLayout = new LinearLayout(i);
                linearLayout.setOrientation(1);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, 0, 1.0f);
                switch (this.v) {
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.v);
                        break;
                    }
                    case 1: {
                        linearLayout.addView((View)c, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        linearLayout.addView(u);
                        break;
                    }
                    case 0: {
                        linearLayout.addView(u);
                        linearLayout.addView((View)c, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        break;
                    }
                }
                int l;
                int n;
                if (this.l >= 0) {
                    l = this.l;
                    n = Integer.MIN_VALUE;
                }
                else {
                    n = 0;
                    l = 0;
                }
                u.measure(View$MeasureSpec.makeMeasureSpec(l, n), 0);
                final LinearLayout$LayoutParams linearLayout$LayoutParams2 = (LinearLayout$LayoutParams)u.getLayoutParams();
                n2 = linearLayout$LayoutParams2.bottomMargin + (u.getMeasuredHeight() + linearLayout$LayoutParams2.topMargin);
                c = linearLayout;
            }
            else {
                n2 = 0;
            }
            this.g.setContentView((View)c);
        }
        else {
            final ViewGroup viewGroup = (ViewGroup)this.g.getContentView();
            final View u2 = this.u;
            if (u2 != null) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams3 = (LinearLayout$LayoutParams)u2.getLayoutParams();
                n2 = linearLayout$LayoutParams3.bottomMargin + (u2.getMeasuredHeight() + linearLayout$LayoutParams3.topMargin);
            }
            else {
                n2 = 0;
            }
        }
        final Drawable background = this.g.getBackground();
        int n3;
        if (background != null) {
            background.getPadding(this.F);
            n3 = this.F.top + this.F.bottom;
            if (!this.p) {
                this.n = -this.F.top;
            }
        }
        else {
            this.F.setEmpty();
            n3 = 0;
        }
        final int a = this.a(this.i(), this.n, this.g.getInputMethodMode() == 2 && b);
        if (this.s || this.k == -1) {
            return a + n3;
        }
        int n4 = 0;
        switch (this.l) {
            default: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.l, 1073741824);
                break;
            }
            case -2: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.F.left + this.F.right), Integer.MIN_VALUE);
                break;
            }
            case -1: {
                n4 = View$MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.F.left + this.F.right), 1073741824);
                break;
            }
        }
        final int a2 = this.c.a(n4, 0, -1, a - n2, -1);
        int n5 = n2;
        if (a2 > 0) {
            n5 = n2 + (this.c.getPaddingTop() + this.c.getPaddingBottom() + n3);
        }
        return a2 + n5;
    }
    
    ae a(final Context context, final boolean b) {
        return new ae(context, b);
    }
    
    @Override
    public void a() {
        boolean outsideTouchable = true;
        final boolean b = false;
        final int n = -1;
        int height = this.f();
        final boolean n2 = this.n();
        android.support.v4.widget.o.a(this.g, this.o);
        if (this.g.isShowing()) {
            int n3;
            if (this.l == -1) {
                n3 = -1;
            }
            else if (this.l == -2) {
                n3 = this.i().getWidth();
            }
            else {
                n3 = this.l;
            }
            if (this.k == -1) {
                if (!n2) {
                    height = -1;
                }
                if (n2) {
                    final PopupWindow g = this.g;
                    int width;
                    if (this.l == -1) {
                        width = -1;
                    }
                    else {
                        width = 0;
                    }
                    g.setWidth(width);
                    this.g.setHeight(0);
                }
                else {
                    final PopupWindow g2 = this.g;
                    int width2;
                    if (this.l == -1) {
                        width2 = -1;
                    }
                    else {
                        width2 = 0;
                    }
                    g2.setWidth(width2);
                    this.g.setHeight(-1);
                }
            }
            else if (this.k != -2) {
                height = this.k;
            }
            final PopupWindow g3 = this.g;
            boolean outsideTouchable2 = b;
            if (!this.t) {
                outsideTouchable2 = b;
                if (!this.s) {
                    outsideTouchable2 = true;
                }
            }
            g3.setOutsideTouchable(outsideTouchable2);
            final PopupWindow g4 = this.g;
            final View i = this.i();
            final int m = this.m;
            final int n4 = this.n;
            int n5;
            if ((n5 = n3) < 0) {
                n5 = -1;
            }
            if (height < 0) {
                height = n;
            }
            g4.update(i, m, n4, n5, height);
        }
        else {
            Label_0468: {
                if (this.l != -1) {
                    break Label_0468;
                }
                int width3 = -1;
            Label_0309_Outer:
                while (true) {
                    Label_0496: {
                        if (this.k != -1) {
                            break Label_0496;
                        }
                        height = -1;
                    Label_0350_Outer:
                        while (true) {
                            this.g.setWidth(width3);
                            this.g.setHeight(height);
                            this.b(true);
                            final PopupWindow g5 = this.g;
                            Label_0513: {
                                if (this.t || this.s) {
                                    break Label_0513;
                                }
                            Label_0396_Outer:
                                while (true) {
                                    g5.setOutsideTouchable(outsideTouchable);
                                    this.g.setTouchInterceptor((View$OnTouchListener)this.B);
                                    while (true) {
                                        if (ai.h == null) {
                                            break Label_0396;
                                        }
                                        try {
                                            ai.h.invoke(this.g, this.G);
                                            android.support.v4.widget.o.a(this.g, this.i(), this.m, this.n, this.r);
                                            this.c.setSelection(-1);
                                            if (!this.H || this.c.isInTouchMode()) {
                                                this.m();
                                            }
                                            if (!this.H) {
                                                this.f.post((Runnable)this.D);
                                                return;
                                            }
                                            return;
                                            // iftrue(Label_0488:, this.l != -2)
                                            // iftrue(Label_0309:, this.k == -2)
                                            Block_21: {
                                                Block_20: {
                                                    break Block_20;
                                                    break Block_21;
                                                }
                                                width3 = this.i().getWidth();
                                                continue Label_0309_Outer;
                                                Label_0488: {
                                                    width3 = this.l;
                                                }
                                                continue Label_0309_Outer;
                                            }
                                            height = this.k;
                                            continue Label_0350_Outer;
                                            outsideTouchable = false;
                                            continue Label_0396_Outer;
                                        }
                                        catch (Exception ex) {
                                            Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", (Throwable)ex);
                                            continue;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void a(final int v) {
        this.v = v;
    }
    
    public void a(final Rect g) {
        this.G = g;
    }
    
    public void a(final Drawable backgroundDrawable) {
        this.g.setBackgroundDrawable(backgroundDrawable);
    }
    
    public void a(final AdapterView$OnItemClickListener z) {
        this.z = z;
    }
    
    public void a(final ListAdapter j) {
        if (this.w == null) {
            this.w = new b();
        }
        else if (this.j != null) {
            this.j.unregisterDataSetObserver(this.w);
        }
        this.j = j;
        if (this.j != null) {
            j.registerDataSetObserver(this.w);
        }
        if (this.c != null) {
            this.c.setAdapter(this.j);
        }
    }
    
    public void a(final PopupWindow$OnDismissListener onDismissListener) {
        this.g.setOnDismissListener(onDismissListener);
    }
    
    public void a(final boolean b) {
        this.H = b;
        this.g.setFocusable(b);
    }
    
    public void b(final int animationStyle) {
        this.g.setAnimationStyle(animationStyle);
    }
    
    public void b(final View x) {
        this.x = x;
    }
    
    @Override
    public void c() {
        this.g.dismiss();
        this.b();
        this.g.setContentView((View)null);
        this.c = null;
        this.f.removeCallbacks((Runnable)this.e);
    }
    
    public void c(final int m) {
        this.m = m;
    }
    
    public void d(final int n) {
        this.n = n;
        this.p = true;
    }
    
    @Override
    public boolean d() {
        return this.g.isShowing();
    }
    
    @Override
    public ListView e() {
        return this.c;
    }
    
    public void e(final int r) {
        this.r = r;
    }
    
    public void f(final int l) {
        this.l = l;
    }
    
    public void g(final int n) {
        final Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.F);
            this.l = this.F.left + this.F.right + n;
            return;
        }
        this.f(n);
    }
    
    public boolean g() {
        return this.H;
    }
    
    public Drawable h() {
        return this.g.getBackground();
    }
    
    public void h(final int inputMethodMode) {
        this.g.setInputMethodMode(inputMethodMode);
    }
    
    public View i() {
        return this.x;
    }
    
    public void i(final int selection) {
        final ae c = this.c;
        if (this.d() && c != null) {
            c.setListSelectionHidden(false);
            c.setSelection(selection);
            if (Build$VERSION.SDK_INT >= 11 && c.getChoiceMode() != 0) {
                c.setItemChecked(selection, true);
            }
        }
    }
    
    public int j() {
        return this.m;
    }
    
    public int k() {
        if (!this.p) {
            return 0;
        }
        return this.n;
    }
    
    public int l() {
        return this.l;
    }
    
    public void m() {
        final ae c = this.c;
        if (c != null) {
            c.setListSelectionHidden(true);
            c.requestLayout();
        }
    }
    
    public boolean n() {
        return this.g.getInputMethodMode() == 2;
    }
    
    private class a implements Runnable
    {
        @Override
        public void run() {
            ai.this.m();
        }
    }
    
    private class b extends DataSetObserver
    {
        public void onChanged() {
            if (ai.this.d()) {
                ai.this.a();
            }
        }
        
        public void onInvalidated() {
            ai.this.c();
        }
    }
    
    private class c implements AbsListView$OnScrollListener
    {
        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        }
        
        public void onScrollStateChanged(final AbsListView absListView, final int n) {
            if (n == 1 && !ai.this.n() && ai.this.g.getContentView() != null) {
                ai.this.f.removeCallbacks((Runnable)ai.this.e);
                ai.this.e.run();
            }
        }
    }
    
    private class d implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final int action = motionEvent.getAction();
            final int n = (int)motionEvent.getX();
            final int n2 = (int)motionEvent.getY();
            if (action == 0 && ai.this.g != null && ai.this.g.isShowing() && n >= 0 && n < ai.this.g.getWidth() && n2 >= 0 && n2 < ai.this.g.getHeight()) {
                ai.this.f.postDelayed((Runnable)ai.this.e, 250L);
            }
            else if (action == 1) {
                ai.this.f.removeCallbacks((Runnable)ai.this.e);
            }
            return false;
        }
    }
    
    private class e implements Runnable
    {
        @Override
        public void run() {
            if (ai.this.c != null && android.support.v4.g.ae.r((View)ai.this.c) && ai.this.c.getCount() > ai.this.c.getChildCount() && ai.this.c.getChildCount() <= ai.this.d) {
                ai.this.g.setInputMethodMode(2);
                ai.this.a();
            }
        }
    }
}
