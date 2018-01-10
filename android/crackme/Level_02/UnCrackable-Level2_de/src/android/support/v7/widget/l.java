// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.support.a.a.c;
import android.content.res.Resources$Theme;
import android.annotation.TargetApi;
import android.support.v7.b.a.b;
import android.util.AttributeSet;
import android.content.res.XmlResourceParser;
import android.content.res.Resources;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.a.a.g;
import android.os.Build$VERSION;
import android.util.Log;
import android.graphics.ColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuffColorFilter;
import android.util.TypedValue;
import android.graphics.drawable.Drawable$ConstantState;
import java.lang.ref.WeakReference;
import android.support.v4.f.f;
import android.support.v4.f.a;
import android.content.res.ColorStateList;
import android.support.v4.f.k;
import android.content.Context;
import java.util.WeakHashMap;
import android.graphics.PorterDuff$Mode;

public final class l
{
    private static final PorterDuff$Mode a;
    private static l b;
    private static final b c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private WeakHashMap<Context, k<ColorStateList>> j;
    private android.support.v4.f.a<String, c> k;
    private k<String> l;
    private final Object m;
    private final WeakHashMap<Context, f<WeakReference<Drawable$ConstantState>>> n;
    private TypedValue o;
    private boolean p;
    
    static {
        a = PorterDuff$Mode.SRC_IN;
        c = new b(6);
        d = new int[] { android.support.v7.a.a.e.abc_textfield_search_default_mtrl_alpha, android.support.v7.a.a.e.abc_textfield_default_mtrl_alpha, android.support.v7.a.a.e.abc_ab_share_pack_mtrl_alpha };
        e = new int[] { android.support.v7.a.a.e.abc_ic_commit_search_api_mtrl_alpha, android.support.v7.a.a.e.abc_seekbar_tick_mark_material, android.support.v7.a.a.e.abc_ic_menu_share_mtrl_alpha, android.support.v7.a.a.e.abc_ic_menu_copy_mtrl_am_alpha, android.support.v7.a.a.e.abc_ic_menu_cut_mtrl_alpha, android.support.v7.a.a.e.abc_ic_menu_selectall_mtrl_alpha, android.support.v7.a.a.e.abc_ic_menu_paste_mtrl_am_alpha };
        f = new int[] { android.support.v7.a.a.e.abc_textfield_activated_mtrl_alpha, android.support.v7.a.a.e.abc_textfield_search_activated_mtrl_alpha, android.support.v7.a.a.e.abc_cab_background_top_mtrl_alpha, android.support.v7.a.a.e.abc_text_cursor_material, android.support.v7.a.a.e.abc_text_select_handle_left_mtrl_dark, android.support.v7.a.a.e.abc_text_select_handle_middle_mtrl_dark, android.support.v7.a.a.e.abc_text_select_handle_right_mtrl_dark, android.support.v7.a.a.e.abc_text_select_handle_left_mtrl_light, android.support.v7.a.a.e.abc_text_select_handle_middle_mtrl_light, android.support.v7.a.a.e.abc_text_select_handle_right_mtrl_light };
        g = new int[] { android.support.v7.a.a.e.abc_popup_background_mtrl_mult, android.support.v7.a.a.e.abc_cab_background_internal_bg, android.support.v7.a.a.e.abc_menu_hardkey_panel_mtrl_mult };
        h = new int[] { android.support.v7.a.a.e.abc_tab_indicator_material, android.support.v7.a.a.e.abc_textfield_search_material };
        i = new int[] { android.support.v7.a.a.e.abc_btn_check_material, android.support.v7.a.a.e.abc_btn_radio_material };
    }
    
    public l() {
        this.m = new Object();
        this.n = new WeakHashMap<Context, f<WeakReference<Drawable$ConstantState>>>(0);
    }
    
    private static long a(final TypedValue typedValue) {
        return typedValue.assetCookie << 32 | typedValue.data;
    }
    
    static PorterDuff$Mode a(final int n) {
        PorterDuff$Mode multiply = null;
        if (n == android.support.v7.a.a.e.abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    public static PorterDuffColorFilter a(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter a;
        if ((a = l.c.a(n, porterDuff$Mode)) == null) {
            a = new PorterDuffColorFilter(n, porterDuff$Mode);
            l.c.a(n, porterDuff$Mode, a);
        }
        return a;
    }
    
    private static PorterDuffColorFilter a(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return a(list.getColorForState(array, 0), porterDuff$Mode);
    }
    
    private Drawable a(final Context context, final int n, final boolean b, final Drawable drawable) {
        final ColorStateList b2 = this.b(context, n);
        Drawable drawable2;
        if (b2 != null) {
            Drawable mutate = drawable;
            if (ad.b(drawable)) {
                mutate = drawable.mutate();
            }
            final Drawable f = android.support.v4.c.a.a.f(mutate);
            android.support.v4.c.a.a.a(f, b2);
            final PorterDuff$Mode a = a(n);
            drawable2 = f;
            if (a != null) {
                android.support.v4.c.a.a.a(f, a);
                drawable2 = f;
            }
        }
        else {
            if (n == android.support.v7.a.a.e.abc_seekbar_track_material) {
                final LayerDrawable layerDrawable = (LayerDrawable)drawable;
                a(layerDrawable.findDrawableByLayerId(16908288), aq.a(context, android.support.v7.a.a.a.colorControlNormal), android.support.v7.widget.l.a);
                a(layerDrawable.findDrawableByLayerId(16908303), aq.a(context, android.support.v7.a.a.a.colorControlNormal), android.support.v7.widget.l.a);
                a(layerDrawable.findDrawableByLayerId(16908301), aq.a(context, android.support.v7.a.a.a.colorControlActivated), android.support.v7.widget.l.a);
                return drawable;
            }
            if (n == android.support.v7.a.a.e.abc_ratingbar_material || n == android.support.v7.a.a.e.abc_ratingbar_indicator_material || n == android.support.v7.a.a.e.abc_ratingbar_small_material) {
                final LayerDrawable layerDrawable2 = (LayerDrawable)drawable;
                a(layerDrawable2.findDrawableByLayerId(16908288), aq.c(context, android.support.v7.a.a.a.colorControlNormal), android.support.v7.widget.l.a);
                a(layerDrawable2.findDrawableByLayerId(16908303), aq.a(context, android.support.v7.a.a.a.colorControlActivated), android.support.v7.widget.l.a);
                a(layerDrawable2.findDrawableByLayerId(16908301), aq.a(context, android.support.v7.a.a.a.colorControlActivated), android.support.v7.widget.l.a);
                return drawable;
            }
            drawable2 = drawable;
            if (!a(context, n, drawable)) {
                drawable2 = drawable;
                if (b) {
                    return null;
                }
            }
        }
        return drawable2;
    }
    
    private Drawable a(final Context context, final long n) {
        Label_0090: {
            final f<WeakReference<Drawable$ConstantState>> f;
            synchronized (this.m) {
                f = this.n.get(context);
                if (f == null) {
                    return null;
                }
                final WeakReference<Drawable$ConstantState> weakReference = f.a(n);
                if (weakReference == null) {
                    break Label_0090;
                }
                final Drawable$ConstantState drawable$ConstantState = weakReference.get();
                if (drawable$ConstantState != null) {
                    return drawable$ConstantState.newDrawable(context.getResources());
                }
            }
            f.b(n);
        }
        // monitorexit(o)
        return null;
    }
    
    public static l a() {
        if (l.b == null) {
            a(l.b = new l());
        }
        return l.b;
    }
    
    private void a(final Context context, final int n, final ColorStateList list) {
        if (this.j == null) {
            this.j = new WeakHashMap<Context, k<ColorStateList>>();
        }
        k<ColorStateList> k;
        if ((k = this.j.get(context)) == null) {
            k = new k<ColorStateList>();
            this.j.put(context, k);
        }
        k.c(n, list);
    }
    
    private static void a(final Drawable drawable, final int n, final PorterDuff$Mode porterDuff$Mode) {
        Drawable mutate = drawable;
        if (ad.b(drawable)) {
            mutate = drawable.mutate();
        }
        PorterDuff$Mode a;
        if ((a = porterDuff$Mode) == null) {
            a = l.a;
        }
        mutate.setColorFilter((ColorFilter)a(n, a));
    }
    
    static void a(final Drawable drawable, final at at, final int[] array) {
        if (ad.b(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        }
        else {
            if (at.d || at.c) {
                ColorStateList a;
                if (at.d) {
                    a = at.a;
                }
                else {
                    a = null;
                }
                PorterDuff$Mode porterDuff$Mode;
                if (at.c) {
                    porterDuff$Mode = at.b;
                }
                else {
                    porterDuff$Mode = l.a;
                }
                drawable.setColorFilter((ColorFilter)a(a, porterDuff$Mode, array));
            }
            else {
                drawable.clearColorFilter();
            }
            if (Build$VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }
    
    private static void a(final l l) {
        if (Build$VERSION.SDK_INT < 24) {
            l.a("vector", (c)new d());
            if (Build$VERSION.SDK_INT >= 11) {
                l.a("animated-vector", (c)new a());
            }
        }
    }
    
    private void a(final String s, final c c) {
        if (this.k == null) {
            this.k = new android.support.v4.f.a<String, c>();
        }
        this.k.put(s, c);
    }
    
    static boolean a(final Context context, int round, final Drawable drawable) {
        PorterDuff$Mode porterDuff$Mode = l.a;
        int n;
        int n2;
        if (a(l.d, round)) {
            n = android.support.v7.a.a.a.colorControlNormal;
            n2 = 1;
            round = -1;
        }
        else if (a(l.f, round)) {
            n = android.support.v7.a.a.a.colorControlActivated;
            n2 = 1;
            round = -1;
        }
        else if (a(l.g, round)) {
            porterDuff$Mode = PorterDuff$Mode.MULTIPLY;
            n2 = 1;
            n = 16842801;
            round = -1;
        }
        else if (round == android.support.v7.a.a.e.abc_list_divider_mtrl_alpha) {
            n = 16842800;
            round = Math.round(40.8f);
            n2 = 1;
        }
        else if (round == android.support.v7.a.a.e.abc_dialog_material_background) {
            n = 16842801;
            n2 = 1;
            round = -1;
        }
        else {
            round = -1;
            n = 0;
            n2 = 0;
        }
        if (n2 != 0) {
            Drawable mutate = drawable;
            if (ad.b(drawable)) {
                mutate = drawable.mutate();
            }
            mutate.setColorFilter((ColorFilter)a(aq.a(context, n), porterDuff$Mode));
            if (round != -1) {
                mutate.setAlpha(round);
            }
            return true;
        }
        return false;
    }
    
    private boolean a(final Context context, final long n, final Drawable drawable) {
        final Drawable$ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            synchronized (this.m) {
                f<WeakReference<Drawable$ConstantState>> f;
                if ((f = this.n.get(context)) == null) {
                    f = new f<WeakReference<Drawable$ConstantState>>();
                    this.n.put(context, f);
                }
                f.b(n, new WeakReference<Drawable$ConstantState>(constantState));
                return true;
            }
        }
        return false;
    }
    
    private static boolean a(final Drawable drawable) {
        return drawable instanceof g || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
    
    private static boolean a(final int[] array, final int n) {
        final boolean b = false;
        final int length = array.length;
        int n2 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n2 >= length) {
                break;
            }
            if (array[n2] == n) {
                b2 = true;
                break;
            }
            ++n2;
        }
        return b2;
    }
    
    private ColorStateList b(final Context context) {
        return this.f(context, aq.a(context, android.support.v7.a.a.a.colorButtonNormal));
    }
    
    private ColorStateList c(final Context context) {
        return this.f(context, 0);
    }
    
    private Drawable c(final Context context, final int n) {
        if (this.o == null) {
            this.o = new TypedValue();
        }
        final TypedValue o = this.o;
        context.getResources().getValue(n, o, true);
        final long a = a(o);
        Object a2 = this.a(context, a);
        Drawable drawable;
        if (a2 != null) {
            drawable = (Drawable)a2;
        }
        else {
            if (n == android.support.v7.a.a.e.abc_cab_background_top_material) {
                a2 = new LayerDrawable(new Drawable[] { this.a(context, android.support.v7.a.a.e.abc_cab_background_internal_bg), this.a(context, android.support.v7.a.a.e.abc_cab_background_top_mtrl_alpha) });
            }
            if ((drawable = (Drawable)a2) != null) {
                ((Drawable)a2).setChangingConfigurations(o.changingConfigurations);
                this.a(context, a, (Drawable)a2);
                return (Drawable)a2;
            }
        }
        return drawable;
    }
    
    private ColorStateList d(final Context context) {
        return this.f(context, aq.a(context, android.support.v7.a.a.a.colorAccent));
    }
    
    private Drawable d(final Context context, final int n) {
        if (this.k == null || this.k.isEmpty()) {
            return null;
        }
        Label_0082: {
            if (this.l == null) {
                this.l = new k<String>();
                break Label_0082;
            }
            final String s = this.l.a(n);
            if (!"appcompat_skip_skip".equals(s) && (s == null || this.k.get(s) != null)) {
                break Label_0082;
            }
            return null;
        }
        if (this.o == null) {
            this.o = new TypedValue();
        }
        final TypedValue o = this.o;
        final Resources resources = context.getResources();
        resources.getValue(n, o, true);
        final long a = a(o);
        final Drawable a2 = this.a(context, a);
        if (a2 != null) {
            return a2;
        }
        Drawable drawable2 = a2;
        Drawable drawable3 = null;
        Label_0260: {
            Label_0257: {
                if (o.string != null) {
                    drawable2 = a2;
                    if (o.string.toString().endsWith(".xml")) {
                        drawable2 = a2;
                        XmlResourceParser xml = null;
                        AttributeSet attributeSet = null;
                        Label_0280: {
                            try {
                                xml = resources.getXml(n);
                                drawable2 = a2;
                                attributeSet = Xml.asAttributeSet((XmlPullParser)xml);
                                int next;
                                do {
                                    drawable2 = a2;
                                    next = ((XmlPullParser)xml).next();
                                } while (next != 2 && next != 1);
                                if (next != 2) {
                                    drawable2 = a2;
                                    throw new XmlPullParserException("No start tag found");
                                }
                                break Label_0280;
                            }
                            catch (Exception ex) {
                                Log.e("AppCompatDrawableManager", "Exception while inflating drawable", (Throwable)ex);
                            }
                            break Label_0257;
                        }
                        final String name = ((XmlPullParser)xml).getName();
                        this.l.c(n, name);
                        final c c = this.k.get(name);
                        Drawable a3 = a2;
                        if (c != null) {
                            a3 = c.a(context, (XmlPullParser)xml, attributeSet, context.getTheme());
                        }
                        if (a3 != null) {
                            a3.setChangingConfigurations(o.changingConfigurations);
                            if (this.a(context, a, a3)) {}
                        }
                        drawable3 = a3;
                        break Label_0260;
                    }
                }
            }
            drawable3 = drawable2;
        }
        final Drawable drawable = drawable3;
        if (drawable3 == null) {
            this.l.c(n, "appcompat_skip_skip");
            return drawable3;
        }
        return drawable;
    }
    
    private ColorStateList e(final Context context, final int n) {
        if (this.j == null) {
            return null;
        }
        final k<ColorStateList> k = this.j.get(context);
        if (k != null) {
            return k.a(n);
        }
        return null;
    }
    
    private void e(final Context context) {
        if (!this.p) {
            this.p = true;
            final Drawable a = this.a(context, android.support.v7.a.a.e.abc_vector_test);
            if (a == null || !a(a)) {
                this.p = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }
    
    private ColorStateList f(final Context context, final int n) {
        final int a = aq.a(context, android.support.v7.a.a.a.colorControlHighlight);
        return new ColorStateList(new int[][] { aq.a, aq.d, aq.b, aq.h }, new int[] { aq.c(context, android.support.v7.a.a.a.colorButtonNormal), android.support.v4.c.a.a(a, n), android.support.v4.c.a.a(a, n), n });
    }
    
    public Drawable a(final Context context, final int n) {
        return this.a(context, n, false);
    }
    
    Drawable a(final Context context, final int n, final boolean b) {
        this.e(context);
        Drawable drawable;
        if ((drawable = this.d(context, n)) == null) {
            drawable = this.c(context, n);
        }
        Drawable a;
        if ((a = drawable) == null) {
            a = android.support.v4.b.a.a(context, n);
        }
        Drawable a2;
        if ((a2 = a) != null) {
            a2 = this.a(context, n, b, a);
        }
        if (a2 != null) {
            ad.a(a2);
        }
        return a2;
    }
    
    Drawable a(final Context context, final ax ax, final int n) {
        Drawable drawable;
        if ((drawable = this.d(context, n)) == null) {
            drawable = ax.a(n);
        }
        if (drawable != null) {
            return this.a(context, n, false, drawable);
        }
        return null;
    }
    
    public void a(final Context context) {
        synchronized (this.m) {
            final f<WeakReference<Drawable$ConstantState>> f = this.n.get(context);
            if (f != null) {
                f.c();
            }
        }
    }
    
    ColorStateList b(final Context context, final int n) {
        ColorStateList e;
        ColorStateList list = e = this.e(context, n);
        if (list == null) {
            if (n == android.support.v7.a.a.e.abc_edit_text_material) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_edittext);
            }
            else if (n == android.support.v7.a.a.e.abc_switch_track_mtrl_alpha) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_switch_track);
            }
            else if (n == android.support.v7.a.a.e.abc_switch_thumb_material) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_switch_thumb);
            }
            else if (n == android.support.v7.a.a.e.abc_btn_default_mtrl_shape) {
                list = this.b(context);
            }
            else if (n == android.support.v7.a.a.e.abc_btn_borderless_material) {
                list = this.c(context);
            }
            else if (n == android.support.v7.a.a.e.abc_btn_colored_material) {
                list = this.d(context);
            }
            else if (n == android.support.v7.a.a.e.abc_spinner_mtrl_am_alpha || n == android.support.v7.a.a.e.abc_spinner_textfield_background_material) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_spinner);
            }
            else if (a(android.support.v7.widget.l.e, n)) {
                list = aq.b(context, android.support.v7.a.a.a.colorControlNormal);
            }
            else if (a(android.support.v7.widget.l.h, n)) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_default);
            }
            else if (a(android.support.v7.widget.l.i, n)) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_btn_checkable);
            }
            else if (n == android.support.v7.a.a.e.abc_seekbar_thumb_material) {
                list = android.support.v7.b.a.b.a(context, android.support.v7.a.a.c.abc_tint_seek_thumb);
            }
            if ((e = list) != null) {
                this.a(context, n, list);
                e = list;
            }
        }
        return e;
    }
    
    @TargetApi(11)
    private static class a implements c
    {
        @SuppressLint({ "NewApi" })
        @Override
        public Drawable a(final Context context, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
            try {
                return c.a(context, context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", (Throwable)ex);
                return null;
            }
        }
    }
    
    private static class b extends g<Integer, PorterDuffColorFilter>
    {
        public b(final int n) {
            super(n);
        }
        
        private static int b(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return (n + 31) * 31 + porterDuff$Mode.hashCode();
        }
        
        PorterDuffColorFilter a(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return this.a(Integer.valueOf(b(n, porterDuff$Mode)));
        }
        
        PorterDuffColorFilter a(final int n, final PorterDuff$Mode porterDuff$Mode, final PorterDuffColorFilter porterDuffColorFilter) {
            return this.a(b(n, porterDuff$Mode), porterDuffColorFilter);
        }
    }
    
    private interface c
    {
        Drawable a(final Context p0, final XmlPullParser p1, final AttributeSet p2, final Resources$Theme p3);
    }
    
    private static class d implements c
    {
        @SuppressLint({ "NewApi" })
        @Override
        public Drawable a(final Context context, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
            try {
                return android.support.a.a.g.a(context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", (Throwable)ex);
                return null;
            }
        }
    }
}
