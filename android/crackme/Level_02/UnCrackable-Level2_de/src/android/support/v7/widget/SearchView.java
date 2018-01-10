// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.support.v4.e.d;
import android.os.Parcel;
import android.support.v4.e.e;
import android.os.Parcelable$Creator;
import android.os.ResultReceiver;
import java.lang.reflect.Method;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.view.TouchDelegate;
import android.widget.AutoCompleteTextView;
import android.text.Editable;
import android.widget.ListAdapter;
import android.database.Cursor;
import android.support.v7.a.a;
import android.text.style.ImageSpan;
import android.text.SpannableStringBuilder;
import android.content.Context;
import android.text.TextUtils;
import android.net.Uri;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.view.View;
import android.app.SearchableInfo;
import android.support.v4.widget.f;
import android.view.View$OnFocusChangeListener;
import android.widget.ImageView;
import android.graphics.drawable.Drawable$ConstantState;
import java.util.WeakHashMap;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.support.v7.view.c;

public class SearchView extends ah implements android.support.v7.view.c
{
    static final a i;
    private View$OnClickListener A;
    private boolean B;
    private boolean C;
    private boolean D;
    private CharSequence E;
    private boolean F;
    private boolean G;
    private int H;
    private boolean I;
    private CharSequence J;
    private boolean K;
    private int L;
    private Bundle M;
    private Runnable N;
    private final Runnable O;
    private Runnable P;
    private final WeakHashMap<String, Drawable$ConstantState> Q;
    final SearchAutoComplete a;
    final ImageView b;
    final ImageView c;
    final ImageView d;
    final ImageView e;
    View$OnFocusChangeListener f;
    android.support.v4.widget.f g;
    SearchableInfo h;
    private final View j;
    private final View k;
    private f l;
    private Rect m;
    private Rect n;
    private int[] o;
    private int[] p;
    private final ImageView q;
    private final Drawable r;
    private final int s;
    private final int t;
    private final Intent u;
    private final Intent v;
    private final CharSequence w;
    private c x;
    private b y;
    private d z;
    
    static {
        i = new a();
    }
    
    private Intent a(final String s, final Uri data, final String s2, final String s3, final int n, final String s4) {
        final Intent intent = new Intent(s);
        intent.addFlags(268435456);
        if (data != null) {
            intent.setData(data);
        }
        intent.putExtra("user_query", this.J);
        if (s3 != null) {
            intent.putExtra("query", s3);
        }
        if (s2 != null) {
            intent.putExtra("intent_extra_data_key", s2);
        }
        if (this.M != null) {
            intent.putExtra("app_data", this.M);
        }
        if (n != 0) {
            intent.putExtra("action_key", n);
            intent.putExtra("action_msg", s4);
        }
        intent.setComponent(this.h.getSearchActivity());
        return intent;
    }
    
    private void a(final View view, final Rect rect) {
        view.getLocationInWindow(this.o);
        this.getLocationInWindow(this.p);
        final int n = this.o[1] - this.p[1];
        final int n2 = this.o[0] - this.p[0];
        rect.set(n2, n, view.getWidth() + n2, view.getHeight() + n);
    }
    
    private void a(final boolean c) {
        final boolean b = true;
        final int n = 8;
        this.C = c;
        int visibility;
        if (c) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        final boolean b2 = !TextUtils.isEmpty((CharSequence)this.a.getText());
        this.b.setVisibility(visibility);
        this.b(b2);
        final View j = this.j;
        int visibility2;
        if (c) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        j.setVisibility(visibility2);
        int visibility3 = n;
        if (this.q.getDrawable() != null) {
            if (this.B) {
                visibility3 = n;
            }
            else {
                visibility3 = 0;
            }
        }
        this.q.setVisibility(visibility3);
        this.m();
        this.c(!b2 && b);
        this.l();
    }
    
    static boolean a(final Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
    
    private CharSequence b(final CharSequence charSequence) {
        if (!this.B || this.r == null) {
            return charSequence;
        }
        final int n = (int)(this.a.getTextSize() * 1.25);
        this.r.setBounds(0, 0, n, n);
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)"   ");
        spannableStringBuilder.setSpan((Object)new ImageSpan(this.r), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return (CharSequence)spannableStringBuilder;
    }
    
    private void b(final boolean b) {
        int visibility;
        final int n = visibility = 8;
        Label_0045: {
            if (this.D) {
                visibility = n;
                if (this.k()) {
                    visibility = n;
                    if (this.hasFocus()) {
                        if (!b) {
                            visibility = n;
                            if (this.I) {
                                break Label_0045;
                            }
                        }
                        visibility = 0;
                    }
                }
            }
        }
        this.c.setVisibility(visibility);
    }
    
    private void c(final boolean b) {
        int visibility;
        if (this.I && !this.c() && b) {
            visibility = 0;
            this.c.setVisibility(8);
        }
        else {
            visibility = 8;
        }
        this.e.setVisibility(visibility);
    }
    
    private int getPreferredHeight() {
        return this.getContext().getResources().getDimensionPixelSize(android.support.v7.a.a.d.abc_search_view_preferred_height);
    }
    
    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(android.support.v7.a.a.d.abc_search_view_preferred_width);
    }
    
    private boolean i() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.h != null) {
            b2 = b;
            if (this.h.getVoiceSearchEnabled()) {
                Intent intent = null;
                if (this.h.getVoiceSearchLaunchWebSearch()) {
                    intent = this.u;
                }
                else if (this.h.getVoiceSearchLaunchRecognizer()) {
                    intent = this.v;
                }
                b2 = b;
                if (intent != null) {
                    b2 = b;
                    if (this.getContext().getPackageManager().resolveActivity(intent, 65536) != null) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    private boolean k() {
        return (this.D || this.I) && !this.c();
    }
    
    private void l() {
        int visibility = 8;
        Label_0036: {
            if (this.k()) {
                if (this.c.getVisibility() != 0) {
                    visibility = visibility;
                    if (this.e.getVisibility() != 0) {
                        break Label_0036;
                    }
                }
                visibility = 0;
            }
        }
        this.k.setVisibility(visibility);
    }
    
    private void m() {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (!TextUtils.isEmpty((CharSequence)this.a.getText())) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4 = b;
        if (!b3) {
            b4 = (this.B && !this.K && b);
        }
        final ImageView d = this.d;
        int visibility;
        if (b4) {
            visibility = (b2 ? 1 : 0);
        }
        else {
            visibility = 8;
        }
        d.setVisibility(visibility);
        final Drawable drawable = this.d.getDrawable();
        if (drawable != null) {
            int[] state;
            if (b3) {
                state = SearchView.ENABLED_STATE_SET;
            }
            else {
                state = SearchView.EMPTY_STATE_SET;
            }
            drawable.setState(state);
        }
    }
    
    private void n() {
        this.post(this.O);
    }
    
    private void o() {
        final CharSequence queryHint = this.getQueryHint();
        final SearchAutoComplete a = this.a;
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        a.setHint(this.b(charSequence));
    }
    
    private void p() {
        final boolean b = true;
        this.a.setThreshold(this.h.getSuggestThreshold());
        this.a.setImeOptions(this.h.getImeOptions());
        int inputType;
        final int n = inputType = this.h.getInputType();
        if ((n & 0xF) == 0x1) {
            inputType = (n & 0xFFFEFFFF);
            if (this.h.getSuggestAuthority() != null) {
                inputType = (inputType | 0x10000 | 0x80000);
            }
        }
        this.a.setInputType(inputType);
        if (this.g != null) {
            this.g.a((Cursor)null);
        }
        if (this.h.getSuggestAuthority() != null) {
            this.g = new ap(this.getContext(), this, this.h, this.Q);
            this.a.setAdapter((ListAdapter)this.g);
            final ap ap = (ap)this.g;
            int n2 = b ? 1 : 0;
            if (this.F) {
                n2 = 2;
            }
            ap.a(n2);
        }
    }
    
    private void q() {
        this.a.dismissDropDown();
    }
    
    private void setQuery(final CharSequence text) {
        this.a.setText(text);
        final SearchAutoComplete a = this.a;
        int length;
        if (TextUtils.isEmpty(text)) {
            length = 0;
        }
        else {
            length = text.length();
        }
        a.setSelection(length);
    }
    
    @Override
    public void a() {
        if (this.K) {
            return;
        }
        this.K = true;
        this.L = this.a.getImeOptions();
        this.a.setImeOptions(this.L | 0x2000000);
        this.a.setText((CharSequence)"");
        this.setIconified(false);
    }
    
    void a(final int n, final String s, final String s2) {
        this.getContext().startActivity(this.a("android.intent.action.SEARCH", null, null, s2, n, s));
    }
    
    void a(final CharSequence query) {
        this.setQuery(query);
    }
    
    public void a(final CharSequence charSequence, final boolean b) {
        this.a.setText(charSequence);
        if (charSequence != null) {
            this.a.setSelection(this.a.length());
            this.J = charSequence;
        }
        if (b && !TextUtils.isEmpty(charSequence)) {
            this.d();
        }
    }
    
    @Override
    public void b() {
        this.a("", false);
        this.clearFocus();
        this.a(true);
        this.a.setImeOptions(this.L);
        this.K = false;
    }
    
    public boolean c() {
        return this.C;
    }
    
    public void clearFocus() {
        this.G = true;
        this.setImeVisibility(false);
        super.clearFocus();
        this.a.clearFocus();
        this.G = false;
    }
    
    void d() {
        final Editable text = this.a.getText();
        if (text != null && TextUtils.getTrimmedLength((CharSequence)text) > 0 && (this.x == null || !this.x.a(((CharSequence)text).toString()))) {
            if (this.h != null) {
                this.a(0, null, ((CharSequence)text).toString());
            }
            this.setImeVisibility(false);
            this.q();
        }
    }
    
    void e() {
        if (TextUtils.isEmpty((CharSequence)this.a.getText())) {
            if (this.B && (this.y == null || !this.y.a())) {
                this.clearFocus();
                this.a(true);
            }
            return;
        }
        this.a.setText((CharSequence)"");
        this.a.requestFocus();
        this.setImeVisibility(true);
    }
    
    void f() {
        this.a(false);
        this.a.requestFocus();
        this.setImeVisibility(true);
        if (this.A != null) {
            this.A.onClick((View)this);
        }
    }
    
    void g() {
        this.a(this.c());
        this.n();
        if (this.a.hasFocus()) {
            this.h();
        }
    }
    
    public int getImeOptions() {
        return this.a.getImeOptions();
    }
    
    public int getInputType() {
        return this.a.getInputType();
    }
    
    public int getMaxWidth() {
        return this.H;
    }
    
    public CharSequence getQuery() {
        return (CharSequence)this.a.getText();
    }
    
    public CharSequence getQueryHint() {
        if (this.E != null) {
            return this.E;
        }
        if (this.h != null && this.h.getHintId() != 0) {
            return this.getContext().getText(this.h.getHintId());
        }
        return this.w;
    }
    
    int getSuggestionCommitIconResId() {
        return this.t;
    }
    
    int getSuggestionRowLayout() {
        return this.s;
    }
    
    public android.support.v4.widget.f getSuggestionsAdapter() {
        return this.g;
    }
    
    void h() {
        SearchView.i.a(this.a);
        SearchView.i.b(this.a);
    }
    
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.O);
        this.post(this.P);
        super.onDetachedFromWindow();
    }
    
    @Override
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        if (b) {
            this.a((View)this.a, this.m);
            this.n.set(this.m.left, 0, this.m.right, n4 - n2);
            if (this.l != null) {
                this.l.a(this.n, this.m);
                return;
            }
            this.setTouchDelegate((TouchDelegate)(this.l = new f(this.n, this.m, (View)this.a)));
        }
    }
    
    @Override
    protected void onMeasure(int n, int n2) {
        if (this.c()) {
            super.onMeasure(n, n2);
            return;
        }
        final int mode = View$MeasureSpec.getMode(n);
        final int size = View$MeasureSpec.getSize(n);
        switch (mode) {
            default: {
                n = size;
                break;
            }
            case Integer.MIN_VALUE: {
                if (this.H > 0) {
                    n = Math.min(this.H, size);
                    break;
                }
                n = Math.min(this.getPreferredWidth(), size);
                break;
            }
            case 1073741824: {
                n = size;
                if (this.H > 0) {
                    n = Math.min(this.H, size);
                    break;
                }
                break;
            }
            case 0: {
                if (this.H > 0) {
                    n = this.H;
                    break;
                }
                n = this.getPreferredWidth();
                break;
            }
        }
        final int mode2 = View$MeasureSpec.getMode(n2);
        n2 = View$MeasureSpec.getSize(n2);
        switch (mode2) {
            case Integer.MIN_VALUE: {
                n2 = Math.min(this.getPreferredHeight(), n2);
                break;
            }
            case 0: {
                n2 = this.getPreferredHeight();
                break;
            }
        }
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), View$MeasureSpec.makeMeasureSpec(n2, 1073741824));
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof e)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        final e e = (e)parcelable;
        super.onRestoreInstanceState(e.a());
        this.a(e.b);
        this.requestLayout();
    }
    
    protected Parcelable onSaveInstanceState() {
        final e e = new e(super.onSaveInstanceState());
        e.b = this.c();
        return (Parcelable)e;
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        this.n();
    }
    
    public boolean requestFocus(final int n, final Rect rect) {
        if (this.G || !this.isFocusable()) {
            return false;
        }
        if (!this.c()) {
            final boolean requestFocus = this.a.requestFocus(n, rect);
            if (requestFocus) {
                this.a(false);
            }
            return requestFocus;
        }
        return super.requestFocus(n, rect);
    }
    
    public void setAppSearchData(final Bundle m) {
        this.M = m;
    }
    
    public void setIconified(final boolean b) {
        if (b) {
            this.e();
            return;
        }
        this.f();
    }
    
    public void setIconifiedByDefault(final boolean b) {
        if (this.B == b) {
            return;
        }
        this.a(this.B = b);
        this.o();
    }
    
    public void setImeOptions(final int imeOptions) {
        this.a.setImeOptions(imeOptions);
    }
    
    void setImeVisibility(final boolean b) {
        if (b) {
            this.post(this.N);
        }
        else {
            this.removeCallbacks(this.N);
            final InputMethodManager inputMethodManager = (InputMethodManager)this.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }
    }
    
    public void setInputType(final int inputType) {
        this.a.setInputType(inputType);
    }
    
    public void setMaxWidth(final int h) {
        this.H = h;
        this.requestLayout();
    }
    
    public void setOnCloseListener(final b y) {
        this.y = y;
    }
    
    public void setOnQueryTextFocusChangeListener(final View$OnFocusChangeListener f) {
        this.f = f;
    }
    
    public void setOnQueryTextListener(final c x) {
        this.x = x;
    }
    
    public void setOnSearchClickListener(final View$OnClickListener a) {
        this.A = a;
    }
    
    public void setOnSuggestionListener(final d z) {
        this.z = z;
    }
    
    public void setQueryHint(final CharSequence e) {
        this.E = e;
        this.o();
    }
    
    public void setQueryRefinementEnabled(final boolean f) {
        this.F = f;
        if (this.g instanceof ap) {
            final ap ap = (ap)this.g;
            int n;
            if (f) {
                n = 2;
            }
            else {
                n = 1;
            }
            ap.a(n);
        }
    }
    
    public void setSearchableInfo(final SearchableInfo h) {
        this.h = h;
        if (this.h != null) {
            this.p();
            this.o();
        }
        this.I = this.i();
        if (this.I) {
            this.a.setPrivateImeOptions("nm");
        }
        this.a(this.c());
    }
    
    public void setSubmitButtonEnabled(final boolean d) {
        this.D = d;
        this.a(this.c());
    }
    
    public void setSuggestionsAdapter(final android.support.v4.widget.f g) {
        this.g = g;
        this.a.setAdapter((ListAdapter)this.g);
    }
    
    public static class SearchAutoComplete extends f
    {
        private int a;
        private SearchView b;
        
        public SearchAutoComplete(final Context context) {
            this(context, null);
        }
        
        public SearchAutoComplete(final Context context, final AttributeSet set) {
            this(context, set, a.a.autoCompleteTextViewStyle);
        }
        
        public SearchAutoComplete(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
            this.a = this.getThreshold();
        }
        
        private int getSearchViewTextMinWidthDp() {
            final Configuration configuration = this.getResources().getConfiguration();
            final int b = android.support.v4.b.a.a.b(this.getResources());
            final int a = android.support.v4.b.a.a.a(this.getResources());
            if (b >= 960 && a >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (b >= 600 || (b >= 640 && a >= 480)) {
                return 192;
            }
            return 160;
        }
        
        public boolean enoughToFilter() {
            return this.a <= 0 || super.enoughToFilter();
        }
        
        protected void onFinishInflate() {
            super.onFinishInflate();
            this.setMinWidth((int)TypedValue.applyDimension(1, (float)this.getSearchViewTextMinWidthDp(), this.getResources().getDisplayMetrics()));
        }
        
        protected void onFocusChanged(final boolean b, final int n, final Rect rect) {
            super.onFocusChanged(b, n, rect);
            this.b.g();
        }
        
        public boolean onKeyPreIme(final int n, final KeyEvent keyEvent) {
            if (n == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    final KeyEvent$DispatcherState keyDispatcherState = this.getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, (Object)this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    final KeyEvent$DispatcherState keyDispatcherState2 = this.getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.b.clearFocus();
                        this.b.setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(n, keyEvent);
        }
        
        public void onWindowFocusChanged(final boolean b) {
            super.onWindowFocusChanged(b);
            if (b && this.b.hasFocus() && this.getVisibility() == 0) {
                ((InputMethodManager)this.getContext().getSystemService("input_method")).showSoftInput((View)this, 0);
                if (SearchView.a(this.getContext())) {
                    SearchView.i.a(this, true);
                }
            }
        }
        
        public void performCompletion() {
        }
        
        protected void replaceText(final CharSequence charSequence) {
        }
        
        void setSearchView(final SearchView b) {
            this.b = b;
        }
        
        public void setThreshold(final int n) {
            super.setThreshold(n);
            this.a = n;
        }
    }
    
    private static class a
    {
        private Method a;
        private Method b;
        private Method c;
        private Method d;
        
        a() {
            while (true) {
                try {
                    (this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", (Class<?>[])new Class[0])).setAccessible(true);
                    try {
                        (this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", (Class<?>[])new Class[0])).setAccessible(true);
                        try {
                            (this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE)).setAccessible(true);
                            try {
                                (this.d = InputMethodManager.class.getMethod("showSoftInputUnchecked", Integer.TYPE, ResultReceiver.class)).setAccessible(true);
                            }
                            catch (NoSuchMethodException ex) {}
                        }
                        catch (NoSuchMethodException ex2) {}
                    }
                    catch (NoSuchMethodException ex3) {}
                }
                catch (NoSuchMethodException ex4) {
                    continue;
                }
                break;
            }
        }
        
        void a(final AutoCompleteTextView autoCompleteTextView) {
            if (this.a == null) {
                return;
            }
            try {
                this.a.invoke(autoCompleteTextView, new Object[0]);
            }
            catch (Exception ex) {}
        }
        
        void a(final AutoCompleteTextView autoCompleteTextView, final boolean b) {
            if (this.c == null) {
                return;
            }
            try {
                this.c.invoke(autoCompleteTextView, b);
            }
            catch (Exception ex) {}
        }
        
        void b(final AutoCompleteTextView autoCompleteTextView) {
            if (this.b == null) {
                return;
            }
            try {
                this.b.invoke(autoCompleteTextView, new Object[0]);
            }
            catch (Exception ex) {}
        }
    }
    
    public interface b
    {
        boolean a();
    }
    
    public interface c
    {
        boolean a(final String p0);
    }
    
    public interface d
    {
    }
    
    static class e extends android.support.v4.g.a
    {
        public static final Parcelable$Creator<e> CREATOR;
        boolean b;
        
        static {
            CREATOR = android.support.v4.e.d.a((android.support.v4.e.e<e>)new android.support.v4.e.e<e>() {
                public SearchView.e b(final Parcel parcel, final ClassLoader classLoader) {
                    return new SearchView.e(parcel, classLoader);
                }
                
                public SearchView.e[] b(final int n) {
                    return new SearchView.e[n];
                }
            });
        }
        
        public e(final Parcel parcel, final ClassLoader classLoader) {
            super(parcel, classLoader);
            this.b = (boolean)parcel.readValue((ClassLoader)null);
        }
        
        e(final Parcelable parcelable) {
            super(parcelable);
        }
        
        @Override
        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.b + "}";
        }
        
        @Override
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeValue((Object)this.b);
        }
    }
    
    private static class f extends TouchDelegate
    {
        private final View a;
        private final Rect b;
        private final Rect c;
        private final Rect d;
        private final int e;
        private boolean f;
        
        public f(final Rect rect, final Rect rect2, final View a) {
            super(rect, a);
            this.e = ViewConfiguration.get(a.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            this.a(rect, rect2);
            this.a = a;
        }
        
        public void a(final Rect rect, final Rect rect2) {
            this.b.set(rect);
            this.d.set(rect);
            this.d.inset(-this.e, -this.e);
            this.c.set(rect2);
        }
        
        public boolean onTouchEvent(final MotionEvent motionEvent) {
            final boolean b = true;
            boolean dispatchTouchEvent = false;
            final int n = (int)motionEvent.getX();
            final int n2 = (int)motionEvent.getY();
            int f = 0;
            boolean b2 = false;
            Label_0057: {
                switch (motionEvent.getAction()) {
                    case 0: {
                        if (this.b.contains(n, n2)) {
                            this.f = true;
                            f = 1;
                            b2 = b;
                            break Label_0057;
                        }
                        break;
                    }
                    case 1:
                    case 2: {
                        final boolean f2 = this.f;
                        b2 = b;
                        f = (f2 ? 1 : 0);
                        if (!f2) {
                            break Label_0057;
                        }
                        b2 = b;
                        f = (f2 ? 1 : 0);
                        if (!this.d.contains(n, n2)) {
                            b2 = false;
                            f = (f2 ? 1 : 0);
                        }
                        break Label_0057;
                    }
                    case 3: {
                        f = (this.f ? 1 : 0);
                        this.f = false;
                        b2 = b;
                        break Label_0057;
                    }
                }
                f = 0;
                b2 = b;
            }
            if (f != 0) {
                if (b2 && !this.c.contains(n, n2)) {
                    motionEvent.setLocation((float)(this.a.getWidth() / 2), (float)(this.a.getHeight() / 2));
                }
                else {
                    motionEvent.setLocation((float)(n - this.c.left), (float)(n2 - this.c.top));
                }
                dispatchTouchEvent = this.a.dispatchTouchEvent(motionEvent);
            }
            return dispatchTouchEvent;
        }
    }
}
