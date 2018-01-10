// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.View$MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.support.v7.widget.ActionMenuView;
import android.view.View$OnClickListener;
import android.support.v7.widget.av;
import android.widget.LinearLayout;
import android.support.v7.view.menu.p;
import android.view.MotionEvent;
import android.app.Dialog;
import android.support.v4.g.j;
import android.view.ViewGroup$MarginLayoutParams;
import org.xmlpull.v1.XmlPullParser;
import android.view.LayoutInflater$Factory;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.l;
import android.content.res.Configuration;
import android.support.v7.view.e;
import android.support.v7.widget.ViewStubCompat;
import android.support.v4.g.aw;
import android.support.v4.g.ax;
import android.util.AttributeSet;
import android.util.AndroidRuntimeException;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ay;
import android.support.v7.widget.af;
import android.support.v4.g.az;
import android.support.v4.g.z;
import android.view.LayoutInflater;
import android.text.TextUtils;
import android.util.Log;
import android.media.AudioManager;
import android.view.KeyCharacterMap;
import android.content.res.Resources$Theme;
import android.support.v7.a.a;
import android.util.TypedValue;
import android.support.v4.g.ae;
import android.support.v4.g.ao;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.Window$Callback;
import android.view.WindowManager$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.WindowManager;
import android.view.Menu;
import android.view.KeyEvent;
import android.view.Window;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.TextView;
import android.view.ViewGroup;
import android.support.v7.widget.ab;
import android.support.v4.g.as;
import android.widget.PopupWindow;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.view.b;
import android.graphics.Rect;
import android.view.View;
import android.annotation.TargetApi;
import android.support.v7.view.menu.h;
import android.support.v4.g.n;

@TargetApi(9)
class k extends f implements n, h.a
{
    private static final boolean t;
    private View A;
    private boolean B;
    private boolean C;
    private boolean D;
    private d[] E;
    private d F;
    private boolean G;
    private final Runnable H;
    private boolean I;
    private Rect J;
    private Rect K;
    private m L;
    android.support.v7.view.b m;
    ActionBarContextView n;
    PopupWindow o;
    Runnable p;
    as q;
    boolean r;
    int s;
    private ab u;
    private a v;
    private e w;
    private boolean x;
    private ViewGroup y;
    private TextView z;
    
    static {
        t = (Build$VERSION.SDK_INT < 21);
    }
    
    k(final Context context, final Window window, final android.support.v7.app.d d) {
        super(context, window, d);
        this.q = null;
        this.H = new Runnable() {
            @Override
            public void run() {
                if ((k.this.s & 0x1) != 0x0) {
                    k.this.f(0);
                }
                if ((k.this.s & 0x1000) != 0x0) {
                    k.this.f(108);
                }
                k.this.r = false;
                k.this.s = 0;
            }
        };
    }
    
    private void a(final d d, final KeyEvent keyEvent) {
        final int n = -1;
        if (!d.o && !this.p()) {
            if (d.a == 0) {
                final Context a = this.a;
                boolean b;
                if ((a.getResources().getConfiguration().screenLayout & 0xF) == 0x4) {
                    b = true;
                }
                else {
                    b = false;
                }
                boolean b2;
                if (a.getApplicationInfo().targetSdkVersion >= 11) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    return;
                }
            }
            final Window$Callback q = this.q();
            if (q != null && !q.onMenuOpened(d.a, (Menu)d.j)) {
                this.a(d, true);
                return;
            }
            final WindowManager windowManager = (WindowManager)this.a.getSystemService("window");
            if (windowManager != null && this.b(d, keyEvent)) {
                int n2 = 0;
                Label_0306: {
                    if (d.g == null || d.q) {
                        if (d.g == null) {
                            if (!this.a(d) || d.g == null) {
                                return;
                            }
                        }
                        else if (d.q && d.g.getChildCount() > 0) {
                            d.g.removeAllViews();
                        }
                        if (!this.c(d) || !d.a()) {
                            return;
                        }
                        ViewGroup$LayoutParams layoutParams = d.h.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup$LayoutParams(-2, -2);
                        }
                        d.g.setBackgroundResource(d.b);
                        final ViewParent parent = d.h.getParent();
                        if (parent != null && parent instanceof ViewGroup) {
                            ((ViewGroup)parent).removeView(d.h);
                        }
                        d.g.addView(d.h, layoutParams);
                        if (!d.h.hasFocus()) {
                            d.h.requestFocus();
                        }
                        n2 = -2;
                    }
                    else {
                        if (d.i != null) {
                            final ViewGroup$LayoutParams layoutParams2 = d.i.getLayoutParams();
                            if (layoutParams2 != null) {
                                n2 = n;
                                if (layoutParams2.width == -1) {
                                    break Label_0306;
                                }
                            }
                        }
                        n2 = -2;
                    }
                }
                d.n = false;
                final WindowManager$LayoutParams windowManager$LayoutParams = new WindowManager$LayoutParams(n2, -2, d.d, d.e, 1002, 8519680, -3);
                windowManager$LayoutParams.gravity = d.c;
                windowManager$LayoutParams.windowAnimations = d.f;
                windowManager.addView((View)d.g, (ViewGroup$LayoutParams)windowManager$LayoutParams);
                d.o = true;
            }
        }
    }
    
    private void a(final h h, final boolean b) {
        if (this.u != null && this.u.e() && (!ao.a(ViewConfiguration.get(this.a)) || this.u.g())) {
            final Window$Callback q = this.q();
            if (!this.u.f() || !b) {
                if (q != null && !this.p()) {
                    if (this.r && (this.s & 0x1) != 0x0) {
                        this.b.getDecorView().removeCallbacks(this.H);
                        this.H.run();
                    }
                    final d a = this.a(0, true);
                    if (a.j != null && !a.r && q.onPreparePanel(0, a.i, (Menu)a.j)) {
                        q.onMenuOpened(108, (Menu)a.j);
                        this.u.h();
                    }
                }
            }
            else {
                this.u.i();
                if (!this.p()) {
                    q.onPanelClosed(108, (Menu)this.a(0, true).j);
                }
            }
            return;
        }
        final d a2 = this.a(0, true);
        a2.q = true;
        this.a(a2, false);
        this.a(a2, null);
    }
    
    private boolean a(final d d) {
        d.a(this.n());
        d.g = (ViewGroup)new c(d.l);
        d.c = 81;
        return true;
    }
    
    private boolean a(final d d, final int n, final KeyEvent keyEvent, final int n2) {
        final boolean b = false;
        final boolean b2 = false;
        boolean b3;
        if (keyEvent.isSystem()) {
            b3 = b2;
        }
        else {
            boolean performShortcut = false;
            Label_0064: {
                if (!d.m) {
                    performShortcut = b;
                    if (!this.b(d, keyEvent)) {
                        break Label_0064;
                    }
                }
                performShortcut = b;
                if (d.j != null) {
                    performShortcut = d.j.performShortcut(n, keyEvent, n2);
                }
            }
            b3 = performShortcut;
            if (performShortcut) {
                b3 = performShortcut;
                if ((n2 & 0x1) == 0x0) {
                    b3 = performShortcut;
                    if (this.u == null) {
                        this.a(d, true);
                        return performShortcut;
                    }
                }
            }
        }
        return b3;
    }
    
    private boolean a(ViewParent parent) {
        if (parent == null) {
            return false;
        }
        final View decorView = this.b.getDecorView();
        while (parent != null) {
            if (parent == decorView || !(parent instanceof View) || ae.r((View)parent)) {
                return false;
            }
            parent = parent.getParent();
        }
        return true;
    }
    
    private boolean b(final d d) {
        final Context a = this.a;
        while (true) {
            Label_0203: {
                if ((d.a != 0 && d.a != 108) || this.u == null) {
                    break Label_0203;
                }
                final TypedValue typedValue = new TypedValue();
                final Resources$Theme theme = a.getTheme();
                theme.resolveAttribute(android.support.v7.a.a.a.actionBarTheme, typedValue, true);
                Resources$Theme theme2 = null;
                if (typedValue.resourceId != 0) {
                    theme2 = a.getResources().newTheme();
                    theme2.setTo(theme);
                    theme2.applyStyle(typedValue.resourceId, true);
                    theme2.resolveAttribute(android.support.v7.a.a.a.actionBarWidgetTheme, typedValue, true);
                }
                else {
                    theme.resolveAttribute(android.support.v7.a.a.a.actionBarWidgetTheme, typedValue, true);
                }
                Resources$Theme theme3 = theme2;
                if (typedValue.resourceId != 0) {
                    if ((theme3 = theme2) == null) {
                        theme3 = a.getResources().newTheme();
                        theme3.setTo(theme);
                    }
                    theme3.applyStyle(typedValue.resourceId, true);
                }
                if (theme3 == null) {
                    break Label_0203;
                }
                final Object o = new android.support.v7.view.d(a, 0);
                ((Context)o).getTheme().setTo(theme3);
                final h h = new h((Context)o);
                h.a((h.a)this);
                d.a(h);
                return true;
            }
            final Object o = a;
            continue;
        }
    }
    
    private boolean b(final d f, final KeyEvent keyEvent) {
        if (!this.p()) {
            if (f.m) {
                return true;
            }
            if (this.F != null && this.F != f) {
                this.a(this.F, false);
            }
            final Window$Callback q = this.q();
            if (q != null) {
                f.i = q.onCreatePanelView(f.a);
            }
            boolean b;
            if (f.a == 0 || f.a == 108) {
                b = true;
            }
            else {
                b = false;
            }
            if (b && this.u != null) {
                this.u.j();
            }
            if (f.i == null && (!b || !(this.m() instanceof o))) {
                if (f.j == null || f.r) {
                    if (f.j == null && (!this.b(f) || f.j == null)) {
                        return false;
                    }
                    if (b && this.u != null) {
                        if (this.v == null) {
                            this.v = new a();
                        }
                        this.u.a((Menu)f.j, this.v);
                    }
                    f.j.g();
                    if (!q.onCreatePanelMenu(f.a, (Menu)f.j)) {
                        f.a((h)null);
                        if (b && this.u != null) {
                            this.u.a(null, this.v);
                            return false;
                        }
                        return false;
                    }
                    else {
                        f.r = false;
                    }
                }
                f.j.g();
                if (f.s != null) {
                    f.j.b(f.s);
                    f.s = null;
                }
                if (!q.onPreparePanel(0, f.i, (Menu)f.j)) {
                    if (b && this.u != null) {
                        this.u.a(null, this.v);
                    }
                    f.j.h();
                    return false;
                }
                int deviceId;
                if (keyEvent != null) {
                    deviceId = keyEvent.getDeviceId();
                }
                else {
                    deviceId = -1;
                }
                f.p = (KeyCharacterMap.load(deviceId).getKeyboardType() != 1);
                f.j.setQwertyMode(f.p);
                f.j.h();
            }
            f.m = true;
            f.n = false;
            this.F = f;
            return true;
        }
        return false;
    }
    
    private boolean c(final d d) {
        if (d.i != null) {
            d.h = d.i;
            return true;
        }
        if (d.j == null) {
            return false;
        }
        if (this.w == null) {
            this.w = new e();
        }
        d.h = (View)d.a(this.w);
        return d.h != null;
    }
    
    private void d(final int n) {
        this.s |= 1 << n;
        if (!this.r) {
            ae.a(this.b.getDecorView(), this.H);
            this.r = true;
        }
    }
    
    private boolean d(final int n, final KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            final d a = this.a(n, true);
            if (!a.o) {
                return this.b(a, keyEvent);
            }
        }
        return false;
    }
    
    private boolean e(final int n, final KeyEvent keyEvent) {
        final boolean b = true;
        if (this.m != null) {
            return false;
        }
        final d a = this.a(n, true);
        while (true) {
            Label_0229: {
                boolean b2;
                if (n == 0 && this.u != null && this.u.e() && !ao.a(ViewConfiguration.get(this.a))) {
                    if (!this.u.f()) {
                        if (this.p() || !this.b(a, keyEvent)) {
                            break Label_0229;
                        }
                        b2 = this.u.h();
                    }
                    else {
                        b2 = this.u.i();
                    }
                }
                else if (a.o || a.n) {
                    b2 = a.o;
                    this.a(a, true);
                }
                else {
                    if (!a.m) {
                        break Label_0229;
                    }
                    int b3;
                    if (a.r) {
                        a.m = false;
                        b3 = (this.b(a, keyEvent) ? 1 : 0);
                    }
                    else {
                        b3 = 1;
                    }
                    if (b3 == 0) {
                        break Label_0229;
                    }
                    this.a(a, keyEvent);
                    b2 = b;
                }
                if (b2) {
                    final AudioManager audioManager = (AudioManager)this.a.getSystemService("audio");
                    if (audioManager != null) {
                        audioManager.playSoundEffect(0);
                    }
                    else {
                        Log.w("AppCompatDelegate", "Couldn't get audio manager");
                    }
                }
                return b2;
            }
            boolean b2 = false;
            continue;
        }
    }
    
    private int h(final int n) {
        int n2;
        if (n == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            n2 = 108;
        }
        else if ((n2 = n) == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
        return n2;
    }
    
    private void w() {
        if (!this.x) {
            this.y = this.x();
            final CharSequence r = this.r();
            if (!TextUtils.isEmpty(r)) {
                this.b(r);
            }
            this.y();
            this.a(this.y);
            this.x = true;
            final d a = this.a(0, false);
            if (!this.p() && (a == null || a.j == null)) {
                this.d(108);
            }
        }
    }
    
    private ViewGroup x() {
        final TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(android.support.v7.a.a.j.AppCompatTheme);
        if (!obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AppCompatTheme_windowNoTitle, false)) {
            this.c(1);
        }
        else if (obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AppCompatTheme_windowActionBar, false)) {
            this.c(108);
        }
        if (obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AppCompatTheme_windowActionBarOverlay, false)) {
            this.c(109);
        }
        if (obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AppCompatTheme_windowActionModeOverlay, false)) {
            this.c(10);
        }
        this.k = obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        this.b.getDecorView();
        final LayoutInflater from = LayoutInflater.from(this.a);
        ViewGroup contentView;
        if (!this.l) {
            if (this.k) {
                contentView = (ViewGroup)from.inflate(android.support.v7.a.a.g.abc_dialog_title_material, (ViewGroup)null);
                this.i = false;
                this.h = false;
            }
            else if (this.h) {
                final TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(android.support.v7.a.a.a.actionBarTheme, typedValue, true);
                Object a;
                if (typedValue.resourceId != 0) {
                    a = new android.support.v7.view.d(this.a, typedValue.resourceId);
                }
                else {
                    a = this.a;
                }
                contentView = (ViewGroup)LayoutInflater.from((Context)a).inflate(android.support.v7.a.a.g.abc_screen_toolbar, (ViewGroup)null);
                (this.u = (ab)contentView.findViewById(android.support.v7.a.a.f.decor_content_parent)).setWindowCallback(this.q());
                if (this.i) {
                    this.u.a(109);
                }
                if (this.B) {
                    this.u.a(2);
                }
                if (this.C) {
                    this.u.a(5);
                }
            }
            else {
                contentView = null;
            }
        }
        else {
            if (this.j) {
                contentView = (ViewGroup)from.inflate(android.support.v7.a.a.g.abc_screen_simple_overlay_action_mode, (ViewGroup)null);
            }
            else {
                contentView = (ViewGroup)from.inflate(android.support.v7.a.a.g.abc_screen_simple, (ViewGroup)null);
            }
            if (Build$VERSION.SDK_INT >= 21) {
                ae.a((View)contentView, new z() {
                    @Override
                    public az a(final View view, final az az) {
                        final int b = az.b();
                        final int g = k.this.g(b);
                        az a = az;
                        if (b != g) {
                            a = az.a(az.a(), g, az.c(), az.d());
                        }
                        return ae.a(view, a);
                    }
                });
            }
            else {
                ((af)contentView).setOnFitSystemWindowsListener((af.a)new af.a() {
                    @Override
                    public void a(final Rect rect) {
                        rect.top = k.this.g(rect.top);
                    }
                });
            }
        }
        if (contentView == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.h + ", windowActionBarOverlay: " + this.i + ", android:windowIsFloating: " + this.k + ", windowActionModeOverlay: " + this.j + ", windowNoTitle: " + this.l + " }");
        }
        if (this.u == null) {
            this.z = (TextView)contentView.findViewById(android.support.v7.a.a.f.title);
        }
        ay.b((View)contentView);
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)contentView.findViewById(android.support.v7.a.a.f.action_bar_activity_content);
        final ViewGroup viewGroup = (ViewGroup)this.b.findViewById(16908290);
        if (viewGroup != null) {
            while (viewGroup.getChildCount() > 0) {
                final View child = viewGroup.getChildAt(0);
                viewGroup.removeViewAt(0);
                contentFrameLayout.addView(child);
            }
            viewGroup.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup instanceof FrameLayout) {
                ((FrameLayout)viewGroup).setForeground((Drawable)null);
            }
        }
        this.b.setContentView((View)contentView);
        contentFrameLayout.setAttachListener((ContentFrameLayout.a)new ContentFrameLayout.a() {
            @Override
            public void a() {
            }
            
            @Override
            public void b() {
                k.this.v();
            }
        });
        return contentView;
    }
    
    private void y() {
        final ContentFrameLayout contentFrameLayout = (ContentFrameLayout)this.y.findViewById(16908290);
        final View decorView = this.b.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        final TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(android.support.v7.a.a.j.AppCompatTheme);
        obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(android.support.v7.a.a.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }
    
    private void z() {
        if (this.x) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }
    
    protected d a(final int n, final boolean b) {
        final d[] e = this.E;
        d[] e2 = null;
        Label_0049: {
            if (e != null) {
                e2 = e;
                if (e.length > n) {
                    break Label_0049;
                }
            }
            e2 = new d[n + 1];
            if (e != null) {
                System.arraycopy(e, 0, e2, 0, e.length);
            }
            this.E = e2;
        }
        final d d = e2[n];
        if (d == null) {
            return e2[n] = new d(n);
        }
        return d;
    }
    
    d a(final Menu menu) {
        final d[] e = this.E;
        int length;
        if (e != null) {
            length = e.length;
        }
        else {
            length = 0;
        }
        for (int i = 0; i < length; ++i) {
            final d d = e[i];
            if (d != null && d.j == menu) {
                return d;
            }
        }
        return null;
    }
    
    @Override
    android.support.v7.view.b a(final android.support.v7.view.b.a a) {
        this.t();
        if (this.m != null) {
            this.m.c();
        }
        android.support.v7.view.b.a a2 = a;
        if (!(a instanceof b)) {
            a2 = new b(a);
        }
    Label_0065:
        while (true) {
            if (this.e == null || this.p()) {
                final android.support.v7.view.b a3 = null;
                break Label_0065;
            }
            while (true) {
                while (true) {
                    try {
                        final android.support.v7.view.b a3 = this.e.a(a2);
                        if (a3 != null) {
                            this.m = a3;
                            if (this.m != null && this.e != null) {
                                this.e.a(this.m);
                            }
                            return this.m;
                        }
                    }
                    catch (AbstractMethodError abstractMethodError) {
                        final android.support.v7.view.b a3 = null;
                        continue Label_0065;
                    }
                    if (this.n == null) {
                        if (this.k) {
                            final TypedValue typedValue = new TypedValue();
                            final Resources$Theme theme = this.a.getTheme();
                            theme.resolveAttribute(a.a.actionBarTheme, typedValue, true);
                            Object a4;
                            if (typedValue.resourceId != 0) {
                                final Resources$Theme theme2 = this.a.getResources().newTheme();
                                theme2.setTo(theme);
                                theme2.applyStyle(typedValue.resourceId, true);
                                a4 = new android.support.v7.view.d(this.a, 0);
                                ((Context)a4).getTheme().setTo(theme2);
                            }
                            else {
                                a4 = this.a;
                            }
                            this.n = new ActionBarContextView((Context)a4);
                            android.support.v4.widget.o.a(this.o = new PopupWindow((Context)a4, (AttributeSet)null, a.a.actionModePopupWindowStyle), 2);
                            this.o.setContentView((View)this.n);
                            this.o.setWidth(-1);
                            ((Context)a4).getTheme().resolveAttribute(a.a.actionBarSize, typedValue, true);
                            this.n.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, ((Context)a4).getResources().getDisplayMetrics()));
                            this.o.setHeight(-2);
                            this.p = new Runnable() {
                                @Override
                                public void run() {
                                    k.this.o.showAtLocation((View)k.this.n, 55, 0, 0);
                                    k.this.t();
                                    if (k.this.s()) {
                                        ae.b((View)k.this.n, 0.0f);
                                        (k.this.q = ae.h((View)k.this.n).a(1.0f)).a(new ax() {
                                            @Override
                                            public void a(final View view) {
                                                k.this.n.setVisibility(0);
                                            }
                                            
                                            @Override
                                            public void b(final View view) {
                                                ae.b((View)k.this.n, 1.0f);
                                                k.this.q.a((aw)null);
                                                k.this.q = null;
                                            }
                                        });
                                        return;
                                    }
                                    ae.b((View)k.this.n, 1.0f);
                                    k.this.n.setVisibility(0);
                                }
                            };
                        }
                        else {
                            final ViewStubCompat viewStubCompat = (ViewStubCompat)this.y.findViewById(a.f.action_mode_bar_stub);
                            if (viewStubCompat != null) {
                                viewStubCompat.setLayoutInflater(LayoutInflater.from(this.n()));
                                this.n = (ActionBarContextView)viewStubCompat.a();
                            }
                        }
                    }
                    if (this.n == null) {
                        continue;
                    }
                    this.t();
                    this.n.c();
                    final android.support.v7.view.e m = new android.support.v7.view.e(this.n.getContext(), this.n, a2, this.o == null);
                    if (!a2.a(m, m.b())) {
                        this.m = null;
                        continue;
                    }
                    m.d();
                    this.n.a(m);
                    this.m = m;
                    if (this.s()) {
                        ae.b((View)this.n, 0.0f);
                        (this.q = ae.h((View)this.n).a(1.0f)).a(new ax() {
                            @Override
                            public void a(final View view) {
                                k.this.n.setVisibility(0);
                                k.this.n.sendAccessibilityEvent(32);
                                if (k.this.n.getParent() instanceof View) {
                                    ae.k((View)k.this.n.getParent());
                                }
                            }
                            
                            @Override
                            public void b(final View view) {
                                ae.b((View)k.this.n, 1.0f);
                                k.this.q.a((aw)null);
                                k.this.q = null;
                            }
                        });
                    }
                    else {
                        ae.b((View)this.n, 1.0f);
                        this.n.setVisibility(0);
                        this.n.sendAccessibilityEvent(32);
                        if (this.n.getParent() instanceof View) {
                            ae.k((View)this.n.getParent());
                        }
                    }
                    if (this.o != null) {
                        this.b.getDecorView().post(this.p);
                        continue;
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    @Override
    public View a(final int n) {
        this.w();
        return this.b.findViewById(n);
    }
    
    @Override
    public final View a(final View view, final String s, final Context context, final AttributeSet set) {
        final View b = this.b(view, s, context, set);
        if (b != null) {
            return b;
        }
        return this.c(view, s, context, set);
    }
    
    void a(final int n, final d d, final Menu menu) {
        d d2 = d;
        Object j = menu;
        if (menu == null) {
            d d3;
            if ((d3 = d) == null) {
                d3 = d;
                if (n >= 0) {
                    d3 = d;
                    if (n < this.E.length) {
                        d3 = this.E[n];
                    }
                }
            }
            d2 = d3;
            j = menu;
            if (d3 != null) {
                j = d3.j;
                d2 = d3;
            }
        }
        if ((d2 == null || d2.o) && !this.p()) {
            this.c.onPanelClosed(n, (Menu)j);
        }
    }
    
    @Override
    void a(final int n, final Menu menu) {
        if (n == 108) {
            final android.support.v7.app.a a = this.a();
            if (a != null) {
                a.e(false);
            }
        }
        else if (n == 0) {
            final d a2 = this.a(n, true);
            if (a2.o) {
                this.a(a2, false);
            }
        }
    }
    
    @Override
    public void a(final Configuration configuration) {
        if (this.h && this.x) {
            final android.support.v7.app.a a = this.a();
            if (a != null) {
                a.a(configuration);
            }
        }
        l.a().a(this.a);
        this.i();
    }
    
    @Override
    public void a(final Bundle bundle) {
        if (this.c instanceof Activity && android.support.v4.a.z.b((Activity)this.c) != null) {
            final android.support.v7.app.a m = this.m();
            if (m != null) {
                m.c(true);
                return;
            }
            this.I = true;
        }
    }
    
    void a(final d d, final boolean b) {
        if (b && d.a == 0 && this.u != null && this.u.f()) {
            this.b(d.j);
        }
        else {
            final WindowManager windowManager = (WindowManager)this.a.getSystemService("window");
            if (windowManager != null && d.o && d.g != null) {
                windowManager.removeView((View)d.g);
                if (b) {
                    this.a(d.a, d, null);
                }
            }
            d.m = false;
            d.n = false;
            d.o = false;
            d.h = null;
            d.q = true;
            if (this.F == d) {
                this.F = null;
            }
        }
    }
    
    @Override
    public void a(final h h) {
        this.a(h, true);
    }
    
    @Override
    public void a(final View view) {
        this.w();
        final ViewGroup viewGroup = (ViewGroup)this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }
    
    @Override
    public void a(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.w();
        final ViewGroup viewGroup = (ViewGroup)this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, viewGroup$LayoutParams);
        this.c.onContentChanged();
    }
    
    void a(final ViewGroup viewGroup) {
    }
    
    @Override
    boolean a(final int n, final KeyEvent keyEvent) {
        final android.support.v7.app.a a = this.a();
        if (a == null || !a.a(n, keyEvent)) {
            if (this.F == null || !this.a(this.F, keyEvent.getKeyCode(), keyEvent, 1)) {
                if (this.F == null) {
                    final d a2 = this.a(0, true);
                    this.b(a2, keyEvent);
                    final boolean a3 = this.a(a2, keyEvent.getKeyCode(), keyEvent, 1);
                    a2.m = false;
                    if (a3) {
                        return true;
                    }
                }
                return false;
            }
            if (this.F != null) {
                return this.F.n = true;
            }
        }
        return true;
    }
    
    @Override
    public boolean a(final h h, final MenuItem menuItem) {
        final Window$Callback q = this.q();
        if (q != null && !this.p()) {
            final d a = this.a((Menu)h.p());
            if (a != null) {
                return q.onMenuItemSelected(a.a, menuItem);
            }
        }
        return false;
    }
    
    @Override
    boolean a(final KeyEvent keyEvent) {
        int n = 1;
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            n = 0;
        }
        if (n != 0) {
            return this.c(keyCode, keyEvent);
        }
        return this.b(keyCode, keyEvent);
    }
    
    public android.support.v7.view.b b(final android.support.v7.view.b.a a) {
        if (a == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.m != null) {
            this.m.c();
        }
        final b b = new b(a);
        final android.support.v7.app.a a2 = this.a();
        if (a2 != null) {
            this.m = a2.a(b);
            if (this.m != null && this.e != null) {
                this.e.a(this.m);
            }
        }
        if (this.m == null) {
            this.m = this.a(b);
        }
        return this.m;
    }
    
    View b(View onCreateView, final String s, final Context context, final AttributeSet set) {
        if (this.c instanceof LayoutInflater$Factory) {
            onCreateView = ((LayoutInflater$Factory)this.c).onCreateView(s, context, set);
            if (onCreateView != null) {
                return onCreateView;
            }
        }
        return null;
    }
    
    @Override
    public void b(final int n) {
        this.w();
        final ViewGroup viewGroup = (ViewGroup)this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(n, viewGroup);
        this.c.onContentChanged();
    }
    
    @Override
    public void b(final Bundle bundle) {
        this.w();
    }
    
    void b(final h h) {
        if (this.D) {
            return;
        }
        this.D = true;
        this.u.k();
        final Window$Callback q = this.q();
        if (q != null && !this.p()) {
            q.onPanelClosed(108, (Menu)h);
        }
        this.D = false;
    }
    
    @Override
    public void b(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.w();
        ((ViewGroup)this.y.findViewById(16908290)).addView(view, viewGroup$LayoutParams);
        this.c.onContentChanged();
    }
    
    @Override
    void b(final CharSequence charSequence) {
        if (this.u != null) {
            this.u.setWindowTitle(charSequence);
        }
        else {
            if (this.m() != null) {
                this.m().a(charSequence);
                return;
            }
            if (this.z != null) {
                this.z.setText(charSequence);
            }
        }
    }
    
    boolean b(final int n, final KeyEvent keyEvent) {
        boolean b = true;
        switch (n) {
            case 82: {
                this.e(0, keyEvent);
                return true;
            }
            case 4: {
                final boolean g = this.G;
                this.G = false;
                final d a = this.a(0, false);
                if (a != null && a.o) {
                    if (!g) {
                        this.a(a, true);
                        return true;
                    }
                    return b;
                }
                else {
                    if (this.u()) {
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        b = false;
        return b;
    }
    
    @Override
    boolean b(final int n, final Menu menu) {
        if (n == 108) {
            final android.support.v7.app.a a = this.a();
            if (a != null) {
                a.e(true);
            }
            return true;
        }
        return false;
    }
    
    public View c(final View view, final String s, final Context context, final AttributeSet set) {
        if (this.L == null) {
            this.L = new m();
        }
        boolean a;
        if (k.t) {
            if (set instanceof XmlPullParser) {
                a = (((XmlPullParser)set).getDepth() > 1);
            }
            else {
                a = this.a((ViewParent)view);
            }
        }
        else {
            a = false;
        }
        return this.L.a(view, s, context, set, a, k.t, true, android.support.v7.widget.ax.a());
    }
    
    @Override
    public boolean c(int h) {
        h = this.h(h);
        if (this.l && h == 108) {
            return false;
        }
        if (this.h && h == 1) {
            this.h = false;
        }
        switch (h) {
            default: {
                return this.b.requestFeature(h);
            }
            case 108: {
                this.z();
                return this.h = true;
            }
            case 109: {
                this.z();
                return this.i = true;
            }
            case 10: {
                this.z();
                return this.j = true;
            }
            case 2: {
                this.z();
                return this.B = true;
            }
            case 5: {
                this.z();
                return this.C = true;
            }
            case 1: {
                this.z();
                return this.l = true;
            }
        }
    }
    
    boolean c(final int n, final KeyEvent keyEvent) {
        boolean g = true;
        switch (n) {
            case 82: {
                this.d(0, keyEvent);
                return true;
            }
            case 4: {
                if ((keyEvent.getFlags() & 0x80) == 0x0) {
                    g = false;
                }
                this.G = g;
                break;
            }
        }
        if (Build$VERSION.SDK_INT < 11) {
            this.a(n, keyEvent);
        }
        return false;
    }
    
    @Override
    public void d() {
        final android.support.v7.app.a a = this.a();
        if (a != null) {
            a.d(false);
        }
    }
    
    @Override
    public void e() {
        final android.support.v7.app.a a = this.a();
        if (a != null) {
            a.d(true);
        }
    }
    
    void e(final int n) {
        this.a(this.a(n, true), true);
    }
    
    @Override
    public void f() {
        final android.support.v7.app.a a = this.a();
        if (a != null && a.e()) {
            return;
        }
        this.d(0);
    }
    
    void f(final int n) {
        final d a = this.a(n, true);
        if (a.j != null) {
            final Bundle s = new Bundle();
            a.j.a(s);
            if (s.size() > 0) {
                a.s = s;
            }
            a.j.g();
            a.j.clear();
        }
        a.r = true;
        a.q = true;
        if ((n == 108 || n == 0) && this.u != null) {
            final d a2 = this.a(0, false);
            if (a2 != null) {
                a2.m = false;
                this.b(a2, null);
            }
        }
    }
    
    int g(int n) {
        int n2 = 1;
        boolean b = true;
        final boolean b2 = false;
        int n6;
        if (this.n != null && this.n.getLayoutParams() instanceof ViewGroup$MarginLayoutParams) {
            final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)this.n.getLayoutParams();
            if (this.n.isShown()) {
                if (this.J == null) {
                    this.J = new Rect();
                    this.K = new Rect();
                }
                final Rect j = this.J;
                final Rect k = this.K;
                j.set(0, n, 0, 0);
                ay.a((View)this.y, j, k);
                int n3;
                if (k.top == 0) {
                    n3 = n;
                }
                else {
                    n3 = 0;
                }
                int n4;
                if (layoutParams.topMargin != n3) {
                    layoutParams.topMargin = n;
                    if (this.A == null) {
                        (this.A = new View(this.a)).setBackgroundColor(this.a.getResources().getColor(android.support.v7.a.a.c.abc_input_method_navigation_guard));
                        this.y.addView(this.A, -1, new ViewGroup$LayoutParams(-1, n));
                        n4 = 1;
                    }
                    else {
                        final ViewGroup$LayoutParams layoutParams2 = this.A.getLayoutParams();
                        if (layoutParams2.height != n) {
                            layoutParams2.height = n;
                            this.A.setLayoutParams(layoutParams2);
                        }
                        n4 = 1;
                    }
                }
                else {
                    n4 = 0;
                }
                if (this.A == null) {
                    b = false;
                }
                int n5 = n;
                if (!this.j) {
                    n5 = n;
                    if (b) {
                        n5 = 0;
                    }
                }
                n = n5;
                n2 = n4;
                n6 = (b ? 1 : 0);
            }
            else if (layoutParams.topMargin != 0) {
                layoutParams.topMargin = 0;
                n6 = 0;
            }
            else {
                n2 = 0;
                n6 = 0;
            }
            if (n2 != 0) {
                this.n.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        }
        else {
            n6 = 0;
        }
        if (this.A != null) {
            final View a = this.A;
            int visibility;
            if (n6 != 0) {
                visibility = (b2 ? 1 : 0);
            }
            else {
                visibility = 8;
            }
            a.setVisibility(visibility);
        }
        return n;
    }
    
    @Override
    public void g() {
        if (this.r) {
            this.b.getDecorView().removeCallbacks(this.H);
        }
        super.g();
        if (this.f != null) {
            this.f.h();
        }
    }
    
    @Override
    public void h() {
        final LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            j.a(from, this);
        }
        else if (!(j.a(from) instanceof k)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }
    
    public void l() {
        this.w();
        if (this.h && this.f == null) {
            if (this.c instanceof Activity) {
                this.f = new r((Activity)this.c, this.i);
            }
            else if (this.c instanceof Dialog) {
                this.f = new r((Dialog)this.c);
            }
            if (this.f != null) {
                this.f.c(this.I);
            }
        }
    }
    
    final boolean s() {
        return this.x && this.y != null && ae.q((View)this.y);
    }
    
    void t() {
        if (this.q != null) {
            this.q.b();
        }
    }
    
    boolean u() {
        if (this.m != null) {
            this.m.c();
        }
        else {
            final android.support.v7.app.a a = this.a();
            if (a == null || !a.f()) {
                return false;
            }
        }
        return true;
    }
    
    void v() {
        if (this.u != null) {
            this.u.k();
        }
        Label_0060: {
            if (this.o == null) {
                break Label_0060;
            }
            this.b.getDecorView().removeCallbacks(this.p);
            while (true) {
                if (!this.o.isShowing()) {
                    break Label_0055;
                }
                try {
                    this.o.dismiss();
                    this.o = null;
                    this.t();
                    final d a = this.a(0, false);
                    if (a != null && a.j != null) {
                        a.j.close();
                    }
                }
                catch (IllegalArgumentException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    private final class a implements o.a
    {
        @Override
        public void a(final h h, final boolean b) {
            k.this.b(h);
        }
        
        @Override
        public boolean a(final h h) {
            final Window$Callback q = k.this.q();
            if (q != null) {
                q.onMenuOpened(108, (Menu)h);
            }
            return true;
        }
    }
    
    class b implements android.support.v7.view.b.a
    {
        private android.support.v7.view.b.a b;
        
        public b(final android.support.v7.view.b.a b) {
            this.b = b;
        }
        
        @Override
        public void a(final android.support.v7.view.b b) {
            this.b.a(b);
            if (k.this.o != null) {
                k.this.b.getDecorView().removeCallbacks(k.this.p);
            }
            if (k.this.n != null) {
                k.this.t();
                (k.this.q = ae.h((View)k.this.n).a(0.0f)).a(new ax() {
                    @Override
                    public void b(final View view) {
                        k.this.n.setVisibility(8);
                        if (k.this.o != null) {
                            k.this.o.dismiss();
                        }
                        else if (k.this.n.getParent() instanceof View) {
                            ae.k((View)k.this.n.getParent());
                        }
                        k.this.n.removeAllViews();
                        k.this.q.a((aw)null);
                        k.this.q = null;
                    }
                });
            }
            if (k.this.e != null) {
                k.this.e.b(k.this.m);
            }
            k.this.m = null;
        }
        
        @Override
        public boolean a(final android.support.v7.view.b b, final Menu menu) {
            return this.b.a(b, menu);
        }
        
        @Override
        public boolean a(final android.support.v7.view.b b, final MenuItem menuItem) {
            return this.b.a(b, menuItem);
        }
        
        @Override
        public boolean b(final android.support.v7.view.b b, final Menu menu) {
            return this.b.b(b, menu);
        }
    }
    
    private class c extends ContentFrameLayout
    {
        public c(final Context context) {
            super(context);
        }
        
        private boolean a(final int n, final int n2) {
            return n < -5 || n2 < -5 || n > this.getWidth() + 5 || n2 > this.getHeight() + 5;
        }
        
        public boolean dispatchKeyEvent(final KeyEvent keyEvent) {
            return k.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && this.a((int)motionEvent.getX(), (int)motionEvent.getY())) {
                k.this.e(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }
        
        public void setBackgroundResource(final int n) {
            this.setBackgroundDrawable(android.support.v7.b.a.b.b(this.getContext(), n));
        }
    }
    
    protected static final class d
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        h j;
        android.support.v7.view.menu.f k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q;
        boolean r;
        Bundle s;
        
        d(final int a) {
            this.a = a;
            this.q = false;
        }
        
        p a(final android.support.v7.view.menu.o.a a) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                (this.k = new android.support.v7.view.menu.f(this.l, android.support.v7.a.a.g.abc_list_menu_item_layout)).a(a);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }
        
        void a(final Context context) {
            final TypedValue typedValue = new TypedValue();
            final Resources$Theme theme = context.getResources().newTheme();
            theme.setTo(context.getTheme());
            theme.resolveAttribute(android.support.v7.a.a.a.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            theme.resolveAttribute(android.support.v7.a.a.a.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                theme.applyStyle(typedValue.resourceId, true);
            }
            else {
                theme.applyStyle(android.support.v7.a.a.i.Theme_AppCompat_CompactMenu, true);
            }
            final android.support.v7.view.d l = new android.support.v7.view.d(context, 0);
            ((Context)l).getTheme().setTo(theme);
            this.l = (Context)l;
            final TypedArray obtainStyledAttributes = ((Context)l).obtainStyledAttributes(android.support.v7.a.a.j.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }
        
        void a(final h j) {
            if (j != this.j) {
                if (this.j != null) {
                    this.j.b(this.k);
                }
                this.j = j;
                if (j != null && this.k != null) {
                    j.a(this.k);
                }
            }
        }
        
        public boolean a() {
            final boolean b = true;
            boolean b2;
            if (this.h == null) {
                b2 = false;
            }
            else {
                b2 = b;
                if (this.i == null) {
                    b2 = b;
                    if (this.k.a().getCount() <= 0) {
                        return false;
                    }
                }
            }
            return b2;
        }
    }
    
    private final class e implements o.a
    {
        @Override
        public void a(h h, final boolean b) {
            final Object p2 = h.p();
            boolean b2;
            if (p2 != h) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final k a = k.this;
            if (b2) {
                h = (h)p2;
            }
            final d a2 = a.a((Menu)h);
            if (a2 != null) {
                if (!b2) {
                    k.this.a(a2, b);
                    return;
                }
                k.this.a(a2.a, a2, (Menu)p2);
                k.this.a(a2, true);
            }
        }
        
        @Override
        public boolean a(final h h) {
            if (h == null && k.this.h) {
                final Window$Callback q = k.this.q();
                if (q != null && !k.this.p()) {
                    q.onMenuOpened(108, (Menu)h);
                }
            }
            return true;
        }
    }
}
