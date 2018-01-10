// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.lang.ref.WeakReference;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ArrayAdapter;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnCancelListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.database.Cursor;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.view.KeyEvent;
import android.content.DialogInterface$OnClickListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.os.Build$VERSION;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.view.LayoutInflater;
import android.support.v4.g.ae;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.content.DialogInterface;
import android.view.Window;
import android.content.Context;
import android.os.Handler;
import android.widget.ListAdapter;
import android.support.v4.widget.NestedScrollView;
import android.os.Message;
import android.widget.Button;
import android.widget.ListView;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

class AlertController
{
    private boolean A;
    private CharSequence B;
    private CharSequence C;
    private CharSequence D;
    private int E;
    private Drawable F;
    private ImageView G;
    private TextView H;
    private TextView I;
    private View J;
    private int K;
    private int L;
    private boolean M;
    private int N;
    private final View$OnClickListener O;
    final l a;
    ListView b;
    Button c;
    Message d;
    Button e;
    Message f;
    Button g;
    Message h;
    NestedScrollView i;
    ListAdapter j;
    int k;
    int l;
    int m;
    int n;
    int o;
    Handler p;
    private final Context q;
    private final Window r;
    private CharSequence s;
    private CharSequence t;
    private View u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    
    public AlertController(final Context q, final l a, final Window r) {
        this.A = false;
        this.E = 0;
        this.k = -1;
        this.N = 0;
        this.O = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Message message;
                if (view == AlertController.this.c && AlertController.this.d != null) {
                    message = Message.obtain(AlertController.this.d);
                }
                else if (view == AlertController.this.e && AlertController.this.f != null) {
                    message = Message.obtain(AlertController.this.f);
                }
                else if (view == AlertController.this.g && AlertController.this.h != null) {
                    message = Message.obtain(AlertController.this.h);
                }
                else {
                    message = null;
                }
                if (message != null) {
                    message.sendToTarget();
                }
                AlertController.this.p.obtainMessage(1, (Object)AlertController.this.a).sendToTarget();
            }
        };
        this.q = q;
        this.a = a;
        this.r = r;
        this.p = new b((DialogInterface)a);
        final TypedArray obtainStyledAttributes = q.obtainStyledAttributes((AttributeSet)null, android.support.v7.a.a.j.AlertDialog, android.support.v7.a.a.a.alertDialogStyle, 0);
        this.K = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_android_layout, 0);
        this.L = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_buttonPanelSideLayout, 0);
        this.l = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_listLayout, 0);
        this.m = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_multiChoiceItemLayout, 0);
        this.n = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_singleChoiceItemLayout, 0);
        this.o = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.AlertDialog_listItemLayout, 0);
        this.M = obtainStyledAttributes.getBoolean(android.support.v7.a.a.j.AlertDialog_showTitle, true);
        obtainStyledAttributes.recycle();
        a.a(1);
    }
    
    private ViewGroup a(View view, final View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view = ((ViewStub)view2).inflate();
            }
            else {
                view = view2;
            }
            return (ViewGroup)view;
        }
        if (view2 != null) {
            final ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub)view).inflate();
        }
        return (ViewGroup)view;
    }
    
    static void a(final View view, final View view2, final View view3) {
        final boolean b = false;
        if (view2 != null) {
            int visibility;
            if (ae.a(view, -1)) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            view2.setVisibility(visibility);
        }
        if (view3 != null) {
            int visibility2;
            if (ae.a(view, 1)) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 4;
            }
            view3.setVisibility(visibility2);
        }
    }
    
    private void a(final ViewGroup viewGroup) {
        boolean b = false;
        View view;
        if (this.u != null) {
            view = this.u;
        }
        else if (this.v != 0) {
            view = LayoutInflater.from(this.q).inflate(this.v, viewGroup, false);
        }
        else {
            view = null;
        }
        if (view != null) {
            b = true;
        }
        if (!b || !a(view)) {
            this.r.setFlags(131072, 131072);
        }
        if (b) {
            final FrameLayout frameLayout = (FrameLayout)this.r.findViewById(android.support.v7.a.a.f.custom);
            frameLayout.addView(view, new ViewGroup$LayoutParams(-1, -1));
            if (this.A) {
                frameLayout.setPadding(this.w, this.x, this.y, this.z);
            }
            if (this.b != null) {
                ((LinearLayout$LayoutParams)viewGroup.getLayoutParams()).weight = 0.0f;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }
    
    private void a(final ViewGroup viewGroup, View view, final int n, final int n2) {
        View view2 = null;
        final View viewById = this.r.findViewById(android.support.v7.a.a.f.scrollIndicatorUp);
        final View viewById2 = this.r.findViewById(android.support.v7.a.a.f.scrollIndicatorDown);
        if (Build$VERSION.SDK_INT >= 23) {
            ae.a(view, n, n2);
            if (viewById != null) {
                viewGroup.removeView(viewById);
            }
            if (viewById2 != null) {
                viewGroup.removeView(viewById2);
            }
        }
        else {
            if ((view = viewById) != null) {
                view = viewById;
                if ((n & 0x1) == 0x0) {
                    viewGroup.removeView(viewById);
                    view = null;
                }
            }
            if (viewById2 != null && (n & 0x2) == 0x0) {
                viewGroup.removeView(viewById2);
            }
            else {
                view2 = viewById2;
            }
            if (view != null || view2 != null) {
                if (this.t != null) {
                    this.i.setOnScrollChangeListener((NestedScrollView.b)new NestedScrollView.b() {
                        @Override
                        public void a(final NestedScrollView nestedScrollView, final int n, final int n2, final int n3, final int n4) {
                            AlertController.a((View)nestedScrollView, view, view2);
                        }
                    });
                    this.i.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            AlertController.a((View)AlertController.this.i, view, view2);
                        }
                    });
                    return;
                }
                if (this.b != null) {
                    this.b.setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
                        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
                            AlertController.a((View)absListView, view, view2);
                        }
                        
                        public void onScrollStateChanged(final AbsListView absListView, final int n) {
                        }
                    });
                    this.b.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            AlertController.a((View)AlertController.this.b, view, view2);
                        }
                    });
                    return;
                }
                if (view != null) {
                    viewGroup.removeView(view);
                }
                if (view2 != null) {
                    viewGroup.removeView(view2);
                }
            }
        }
    }
    
    private void a(final Button button) {
        final LinearLayout$LayoutParams layoutParams = (LinearLayout$LayoutParams)button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private static boolean a(final Context context) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.support.v7.a.a.a.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }
    
    static boolean a(final View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        final ViewGroup viewGroup = (ViewGroup)view;
        int i = viewGroup.getChildCount();
        while (i > 0) {
            if (a(viewGroup.getChildAt(--i))) {
                return true;
            }
        }
        return false;
    }
    
    private int b() {
        if (this.L == 0) {
            return this.K;
        }
        if (this.N == 1) {
            return this.L;
        }
        return this.K;
    }
    
    private void b(final ViewGroup viewGroup) {
        if (this.J != null) {
            viewGroup.addView(this.J, 0, new ViewGroup$LayoutParams(-1, -2));
            this.r.findViewById(android.support.v7.a.a.f.title_template).setVisibility(8);
            return;
        }
        this.G = (ImageView)this.r.findViewById(16908294);
        boolean b;
        if (!TextUtils.isEmpty(this.s)) {
            b = true;
        }
        else {
            b = false;
        }
        if (!b || !this.M) {
            this.r.findViewById(android.support.v7.a.a.f.title_template).setVisibility(8);
            this.G.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        (this.H = (TextView)this.r.findViewById(android.support.v7.a.a.f.alertTitle)).setText(this.s);
        if (this.E != 0) {
            this.G.setImageResource(this.E);
            return;
        }
        if (this.F != null) {
            this.G.setImageDrawable(this.F);
            return;
        }
        this.H.setPadding(this.G.getPaddingLeft(), this.G.getPaddingTop(), this.G.getPaddingRight(), this.G.getPaddingBottom());
        this.G.setVisibility(8);
    }
    
    private void c() {
        final View viewById = this.r.findViewById(android.support.v7.a.a.f.parentPanel);
        final View viewById2 = viewById.findViewById(android.support.v7.a.a.f.topPanel);
        final View viewById3 = viewById.findViewById(android.support.v7.a.a.f.contentPanel);
        final View viewById4 = viewById.findViewById(android.support.v7.a.a.f.buttonPanel);
        final ViewGroup viewGroup = (ViewGroup)viewById.findViewById(android.support.v7.a.a.f.customPanel);
        this.a(viewGroup);
        final View viewById5 = viewGroup.findViewById(android.support.v7.a.a.f.topPanel);
        final View viewById6 = viewGroup.findViewById(android.support.v7.a.a.f.contentPanel);
        final View viewById7 = viewGroup.findViewById(android.support.v7.a.a.f.buttonPanel);
        final ViewGroup a = this.a(viewById5, viewById2);
        final ViewGroup a2 = this.a(viewById6, viewById3);
        final ViewGroup a3 = this.a(viewById7, viewById4);
        this.c(a2);
        this.d(a3);
        this.b(a);
        boolean b;
        if (viewGroup != null && viewGroup.getVisibility() != 8) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = a != null && a.getVisibility() != 8;
        final boolean b3 = a3 != null && a3.getVisibility() != 8;
        if (!b3 && a2 != null) {
            final View viewById8 = a2.findViewById(android.support.v7.a.a.f.textSpacerNoButtons);
            if (viewById8 != null) {
                viewById8.setVisibility(0);
            }
        }
        if (b2) {
            if (this.i != null) {
                this.i.setClipToPadding(true);
            }
            final View view = null;
            View viewById9 = null;
            Label_0284: {
                if (this.t == null && this.b == null) {
                    viewById9 = view;
                    if (!b) {
                        break Label_0284;
                    }
                }
                viewById9 = view;
                if (!b) {
                    viewById9 = a.findViewById(android.support.v7.a.a.f.titleDividerNoCustom);
                }
            }
            if (viewById9 != null) {
                viewById9.setVisibility(0);
            }
        }
        else if (a2 != null) {
            final View viewById10 = a2.findViewById(android.support.v7.a.a.f.textSpacerNoTitle);
            if (viewById10 != null) {
                viewById10.setVisibility(0);
            }
        }
        if (this.b instanceof RecycleListView) {
            ((RecycleListView)this.b).a(b2, b3);
        }
        if (!b) {
            Object o;
            if (this.b != null) {
                o = this.b;
            }
            else {
                o = this.i;
            }
            if (o != null) {
                boolean b4;
                if (b2) {
                    b4 = true;
                }
                else {
                    b4 = false;
                }
                int n;
                if (b3) {
                    n = 2;
                }
                else {
                    n = 0;
                }
                this.a(a2, (View)o, n | (b4 ? 1 : 0), 3);
            }
        }
        final ListView b5 = this.b;
        if (b5 != null && this.j != null) {
            b5.setAdapter(this.j);
            final int k = this.k;
            if (k > -1) {
                b5.setItemChecked(k, true);
                b5.setSelection(k);
            }
        }
    }
    
    private void c(ViewGroup viewGroup) {
        (this.i = (NestedScrollView)this.r.findViewById(android.support.v7.a.a.f.scrollView)).setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.I = (TextView)viewGroup.findViewById(16908299);
        if (this.I == null) {
            return;
        }
        if (this.t != null) {
            this.I.setText(this.t);
            return;
        }
        this.I.setVisibility(8);
        this.i.removeView((View)this.I);
        if (this.b != null) {
            viewGroup = (ViewGroup)this.i.getParent();
            final int indexOfChild = viewGroup.indexOfChild((View)this.i);
            viewGroup.removeViewAt(indexOfChild);
            viewGroup.addView((View)this.b, indexOfChild, new ViewGroup$LayoutParams(-1, -1));
            return;
        }
        viewGroup.setVisibility(8);
    }
    
    private void d(final ViewGroup viewGroup) {
        final boolean b = true;
        (this.c = (Button)viewGroup.findViewById(16908313)).setOnClickListener(this.O);
        int n;
        if (TextUtils.isEmpty(this.B)) {
            this.c.setVisibility(8);
            n = 0;
        }
        else {
            this.c.setText(this.B);
            this.c.setVisibility(0);
            n = 1;
        }
        (this.e = (Button)viewGroup.findViewById(16908314)).setOnClickListener(this.O);
        if (TextUtils.isEmpty(this.C)) {
            this.e.setVisibility(8);
        }
        else {
            this.e.setText(this.C);
            this.e.setVisibility(0);
            n |= 0x2;
        }
        (this.g = (Button)viewGroup.findViewById(16908315)).setOnClickListener(this.O);
        if (TextUtils.isEmpty(this.D)) {
            this.g.setVisibility(8);
        }
        else {
            this.g.setText(this.D);
            this.g.setVisibility(0);
            n |= 0x4;
        }
        if (a(this.q)) {
            if (n == 1) {
                this.a(this.c);
            }
            else if (n == 2) {
                this.a(this.e);
            }
            else if (n == 4) {
                this.a(this.g);
            }
        }
        if (n == 0 || !b) {
            viewGroup.setVisibility(8);
        }
    }
    
    public void a() {
        this.a.setContentView(this.b());
        this.c();
    }
    
    public void a(final int v) {
        this.u = null;
        this.v = v;
        this.A = false;
    }
    
    public void a(final int n, final CharSequence d, final DialogInterface$OnClickListener dialogInterface$OnClickListener, final Message message) {
        Message obtainMessage = message;
        if (message == null) {
            obtainMessage = message;
            if (dialogInterface$OnClickListener != null) {
                obtainMessage = this.p.obtainMessage(n, (Object)dialogInterface$OnClickListener);
            }
        }
        switch (n) {
            default: {
                throw new IllegalArgumentException("Button does not exist");
            }
            case -1: {
                this.B = d;
                this.d = obtainMessage;
            }
            case -2: {
                this.C = d;
                this.f = obtainMessage;
            }
            case -3: {
                this.D = d;
                this.h = obtainMessage;
            }
        }
    }
    
    public void a(final Drawable drawable) {
        this.F = drawable;
        this.E = 0;
        if (this.G != null) {
            if (drawable == null) {
                this.G.setVisibility(8);
                return;
            }
            this.G.setVisibility(0);
            this.G.setImageDrawable(drawable);
        }
    }
    
    public void a(final View u, final int w, final int x, final int y, final int z) {
        this.u = u;
        this.v = 0;
        this.A = true;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void a(final CharSequence charSequence) {
        this.s = charSequence;
        if (this.H != null) {
            this.H.setText(charSequence);
        }
    }
    
    public boolean a(final int n, final KeyEvent keyEvent) {
        return this.i != null && this.i.a(keyEvent);
    }
    
    public void b(final int e) {
        this.F = null;
        this.E = e;
        if (this.G != null) {
            if (e == 0) {
                this.G.setVisibility(8);
                return;
            }
            this.G.setVisibility(0);
            this.G.setImageResource(this.E);
        }
    }
    
    public void b(final View j) {
        this.J = j;
    }
    
    public void b(final CharSequence charSequence) {
        this.t = charSequence;
        if (this.I != null) {
            this.I.setText(charSequence);
        }
    }
    
    public boolean b(final int n, final KeyEvent keyEvent) {
        return this.i != null && this.i.a(keyEvent);
    }
    
    public int c(final int n) {
        final TypedValue typedValue = new TypedValue();
        this.q.getTheme().resolveAttribute(n, typedValue, true);
        return typedValue.resourceId;
    }
    
    public void c(final View u) {
        this.u = u;
        this.v = 0;
        this.A = false;
    }
    
    public static class RecycleListView extends ListView
    {
        private final int a;
        private final int b;
        
        public RecycleListView(final Context context) {
            this(context, null);
        }
        
        public RecycleListView(final Context context, final AttributeSet set) {
            super(context, set);
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, a.j.RecycleListView);
            this.b = obtainStyledAttributes.getDimensionPixelOffset(a.j.RecycleListView_paddingBottomNoButtons, -1);
            this.a = obtainStyledAttributes.getDimensionPixelOffset(a.j.RecycleListView_paddingTopNoTitle, -1);
        }
        
        public void a(final boolean b, final boolean b2) {
            if (!b2 || !b) {
                final int paddingLeft = this.getPaddingLeft();
                int n;
                if (b) {
                    n = this.getPaddingTop();
                }
                else {
                    n = this.a;
                }
                final int paddingRight = this.getPaddingRight();
                int n2;
                if (b2) {
                    n2 = this.getPaddingBottom();
                }
                else {
                    n2 = this.b;
                }
                this.setPadding(paddingLeft, n, paddingRight, n2);
            }
        }
    }
    
    public static class a
    {
        public int A;
        public boolean B;
        public boolean[] C;
        public boolean D;
        public boolean E;
        public int F;
        public DialogInterface$OnMultiChoiceClickListener G;
        public Cursor H;
        public String I;
        public String J;
        public AdapterView$OnItemSelectedListener K;
        public AlertController.a.a L;
        public boolean M;
        public final Context a;
        public final LayoutInflater b;
        public int c;
        public Drawable d;
        public int e;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public DialogInterface$OnClickListener j;
        public CharSequence k;
        public DialogInterface$OnClickListener l;
        public CharSequence m;
        public DialogInterface$OnClickListener n;
        public boolean o;
        public DialogInterface$OnCancelListener p;
        public DialogInterface$OnDismissListener q;
        public DialogInterface$OnKeyListener r;
        public CharSequence[] s;
        public ListAdapter t;
        public DialogInterface$OnClickListener u;
        public int v;
        public View w;
        public int x;
        public int y;
        public int z;
        
        public a(final Context a) {
            this.c = 0;
            this.e = 0;
            this.B = false;
            this.F = -1;
            this.M = true;
            this.a = a;
            this.o = true;
            this.b = (LayoutInflater)a.getSystemService("layout_inflater");
        }
        
        private void b(final AlertController alertController) {
            final RecycleListView b = (RecycleListView)this.b.inflate(alertController.l, (ViewGroup)null);
            Object t;
            if (this.D) {
                if (this.H == null) {
                    t = new ArrayAdapter<CharSequence>(this.a, alertController.m, 16908308, this.s) {
                        public View getView(final int n, View view, final ViewGroup viewGroup) {
                            view = super.getView(n, view, viewGroup);
                            if (AlertController.a.this.C != null && AlertController.a.this.C[n]) {
                                b.setItemChecked(n, true);
                            }
                            return view;
                        }
                    };
                }
                else {
                    t = new CursorAdapter(this.a, this.H, false) {
                        private final int d;
                        private final int e;
                        
                        {
                            final Cursor cursor2 = this.getCursor();
                            this.d = cursor2.getColumnIndexOrThrow(AlertController.a.this.I);
                            this.e = cursor2.getColumnIndexOrThrow(AlertController.a.this.J);
                        }
                        
                        public void bindView(final View view, final Context context, final Cursor cursor) {
                            ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.d));
                            b.setItemChecked(cursor.getPosition(), cursor.getInt(this.e) == 1);
                        }
                        
                        public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
                            return AlertController.a.this.b.inflate(alertController.m, viewGroup, false);
                        }
                    };
                }
            }
            else {
                int n;
                if (this.E) {
                    n = alertController.n;
                }
                else {
                    n = alertController.o;
                }
                if (this.H != null) {
                    t = new SimpleCursorAdapter(this.a, n, this.H, new String[] { this.I }, new int[] { 16908308 });
                }
                else if (this.t != null) {
                    t = this.t;
                }
                else {
                    t = new c(this.a, n, 16908308, this.s);
                }
            }
            if (this.L != null) {
                this.L.a(b);
            }
            alertController.j = (ListAdapter)t;
            alertController.k = this.F;
            if (this.u != null) {
                b.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        AlertController.a.this.u.onClick((DialogInterface)alertController.a, n);
                        if (!AlertController.a.this.E) {
                            alertController.a.dismiss();
                        }
                    }
                });
            }
            else if (this.G != null) {
                b.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        if (AlertController.a.this.C != null) {
                            AlertController.a.this.C[n] = b.isItemChecked(n);
                        }
                        AlertController.a.this.G.onClick((DialogInterface)alertController.a, n, b.isItemChecked(n));
                    }
                });
            }
            if (this.K != null) {
                b.setOnItemSelectedListener(this.K);
            }
            if (this.E) {
                b.setChoiceMode(1);
            }
            else if (this.D) {
                b.setChoiceMode(2);
            }
            alertController.b = b;
        }
        
        public void a(final AlertController alertController) {
            if (this.g != null) {
                alertController.b(this.g);
            }
            else {
                if (this.f != null) {
                    alertController.a(this.f);
                }
                if (this.d != null) {
                    alertController.a(this.d);
                }
                if (this.c != 0) {
                    alertController.b(this.c);
                }
                if (this.e != 0) {
                    alertController.b(alertController.c(this.e));
                }
            }
            if (this.h != null) {
                alertController.b(this.h);
            }
            if (this.i != null) {
                alertController.a(-1, this.i, this.j, null);
            }
            if (this.k != null) {
                alertController.a(-2, this.k, this.l, null);
            }
            if (this.m != null) {
                alertController.a(-3, this.m, this.n, null);
            }
            if (this.s != null || this.H != null || this.t != null) {
                this.b(alertController);
            }
            if (this.w != null) {
                if (!this.B) {
                    alertController.c(this.w);
                    return;
                }
                alertController.a(this.w, this.x, this.y, this.z, this.A);
            }
            else if (this.v != 0) {
                alertController.a(this.v);
            }
        }
        
        public interface a
        {
            void a(final ListView p0);
        }
    }
    
    private static final class b extends Handler
    {
        private WeakReference<DialogInterface> a;
        
        public b(final DialogInterface dialogInterface) {
            this.a = new WeakReference<DialogInterface>(dialogInterface);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {}
                case -3:
                case -2:
                case -1: {
                    ((DialogInterface$OnClickListener)message.obj).onClick((DialogInterface)this.a.get(), message.what);
                }
                case 1: {
                    ((DialogInterface)message.obj).dismiss();
                }
            }
        }
    }
    
    private static class c extends ArrayAdapter<CharSequence>
    {
        public c(final Context context, final int n, final int n2, final CharSequence[] array) {
            super(context, n, n2, (Object[])array);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public boolean hasStableIds() {
            return true;
        }
    }
}
