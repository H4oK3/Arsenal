// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.AdapterView;
import android.content.Intent;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.content.pm.PackageManager;
import android.support.v4.g.ae;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.pm.ResolveInfo;
import android.widget.BaseAdapter;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View;
import android.widget.ListAdapter;
import android.view.ViewTreeObserver;
import android.support.v7.a.a;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.PopupWindow$OnDismissListener;
import android.database.DataSetObserver;
import android.support.v4.g.e;
import android.widget.FrameLayout;
import android.view.ViewGroup;

public class ActivityChooserView extends ViewGroup
{
    final a a;
    final FrameLayout b;
    final FrameLayout c;
    e d;
    final DataSetObserver e;
    PopupWindow$OnDismissListener f;
    boolean g;
    int h;
    private final b i;
    private final ah j;
    private final ImageView k;
    private final int l;
    private final ViewTreeObserver$OnGlobalLayoutListener m;
    private ai n;
    private boolean o;
    private int p;
    
    void a(final int n) {
        if (this.a.d() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener(this.m);
        final boolean b = this.c.getVisibility() == 0;
        final int c = this.a.c();
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n != Integer.MAX_VALUE && c > n2 + n) {
            this.a.a(true);
            this.a.a(n - 1);
        }
        else {
            this.a.a(false);
            this.a.a(n);
        }
        final ai listPopupWindow = this.getListPopupWindow();
        if (!listPopupWindow.d()) {
            if (this.g || !b) {
                this.a.a(true, b);
            }
            else {
                this.a.a(false, false);
            }
            listPopupWindow.g(Math.min(this.a.a(), this.l));
            listPopupWindow.a();
            if (this.d != null) {
                this.d.a(true);
            }
            listPopupWindow.e().setContentDescription((CharSequence)this.getContext().getString(android.support.v7.a.a.h.abc_activitychooserview_choose_application));
        }
    }
    
    public boolean a() {
        if (this.c() || !this.o) {
            return false;
        }
        this.g = false;
        this.a(this.h);
        return true;
    }
    
    public boolean b() {
        if (this.c()) {
            this.getListPopupWindow().c();
            final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.m);
            }
        }
        return true;
    }
    
    public boolean c() {
        return this.getListPopupWindow().d();
    }
    
    public android.support.v7.widget.e getDataModel() {
        return this.a.d();
    }
    
    ai getListPopupWindow() {
        if (this.n == null) {
            (this.n = new ai(this.getContext())).a((ListAdapter)this.a);
            this.n.b((View)this);
            this.n.a(true);
            this.n.a((AdapterView$OnItemClickListener)this.i);
            this.n.a((PopupWindow$OnDismissListener)this.i);
        }
        return this.n;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final android.support.v7.widget.e d = this.a.d();
        if (d != null) {
            d.registerObserver((Object)this.e);
        }
        this.o = true;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        final android.support.v7.widget.e d = this.a.d();
        if (d != null) {
            d.unregisterObserver((Object)this.e);
        }
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.m);
        }
        if (this.c()) {
            this.b();
        }
        this.o = false;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.j.layout(0, 0, n3 - n, n4 - n2);
        if (!this.c()) {
            this.b();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        final ah j = this.j;
        int measureSpec = n2;
        if (this.c.getVisibility() != 0) {
            measureSpec = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(n2), 1073741824);
        }
        this.measureChild((View)j, n, measureSpec);
        this.setMeasuredDimension(((View)j).getMeasuredWidth(), ((View)j).getMeasuredHeight());
    }
    
    public void setActivityChooserModel(final android.support.v7.widget.e e) {
        this.a.a(e);
        if (this.c()) {
            this.b();
            this.a();
        }
    }
    
    public void setDefaultActionButtonContentDescription(final int p) {
        this.p = p;
    }
    
    public void setExpandActivityOverflowButtonContentDescription(final int n) {
        this.k.setContentDescription((CharSequence)this.getContext().getString(n));
    }
    
    public void setExpandActivityOverflowButtonDrawable(final Drawable imageDrawable) {
        this.k.setImageDrawable(imageDrawable);
    }
    
    public void setInitialActivityCount(final int h) {
        this.h = h;
    }
    
    public void setOnDismissListener(final PopupWindow$OnDismissListener f) {
        this.f = f;
    }
    
    public void setProvider(final e d) {
        this.d = d;
    }
    
    public static class InnerLayout extends ah
    {
        private static final int[] a;
        
        static {
            a = new int[] { 16842964 };
        }
        
        public InnerLayout(final Context context, final AttributeSet set) {
            super(context, set);
            final av a = av.a(context, set, InnerLayout.a);
            this.setBackgroundDrawable(a.a(0));
            a.a();
        }
    }
    
    private class a extends BaseAdapter
    {
        final /* synthetic */ ActivityChooserView a;
        private android.support.v7.widget.e b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        
        public int a() {
            int i = 0;
            final int c = this.c;
            this.c = Integer.MAX_VALUE;
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
            final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
            final int count = this.getCount();
            View view = null;
            int max = 0;
            while (i < count) {
                view = this.getView(i, view, null);
                view.measure(measureSpec, measureSpec2);
                max = Math.max(max, view.getMeasuredWidth());
                ++i;
            }
            this.c = c;
            return max;
        }
        
        public void a(final int c) {
            if (this.c != c) {
                this.c = c;
                this.notifyDataSetChanged();
            }
        }
        
        public void a(final android.support.v7.widget.e b) {
            final android.support.v7.widget.e d = this.a.a.d();
            if (d != null && this.a.isShown()) {
                d.unregisterObserver((Object)this.a.e);
            }
            if ((this.b = b) != null && this.a.isShown()) {
                b.registerObserver((Object)this.a.e);
            }
            this.notifyDataSetChanged();
        }
        
        public void a(final boolean f) {
            if (this.f != f) {
                this.f = f;
                this.notifyDataSetChanged();
            }
        }
        
        public void a(final boolean d, final boolean e) {
            if (this.d != d || this.e != e) {
                this.d = d;
                this.e = e;
                this.notifyDataSetChanged();
            }
        }
        
        public ResolveInfo b() {
            return this.b.b();
        }
        
        public int c() {
            return this.b.a();
        }
        
        public android.support.v7.widget.e d() {
            return this.b;
        }
        
        public boolean e() {
            return this.d;
        }
        
        public int getCount() {
            int a;
            final int n = a = this.b.a();
            if (!this.d) {
                a = n;
                if (this.b.b() != null) {
                    a = n - 1;
                }
            }
            int min = Math.min(a, this.c);
            if (this.f) {
                ++min;
            }
            return min;
        }
        
        public Object getItem(final int n) {
            switch (this.getItemViewType(n)) {
                default: {
                    throw new IllegalArgumentException();
                }
                case 1: {
                    return null;
                }
                case 0: {
                    int n2 = n;
                    if (!this.d) {
                        n2 = n;
                        if (this.b.b() != null) {
                            n2 = n + 1;
                        }
                    }
                    return this.b.a(n2);
                }
            }
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public int getItemViewType(final int n) {
            if (this.f && n == this.getCount() - 1) {
                return 1;
            }
            return 0;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            switch (this.getItemViewType(n)) {
                default: {
                    throw new IllegalArgumentException();
                }
                case 1: {
                    if (view != null) {
                        final View inflate = view;
                        if (view.getId() == 1) {
                            return inflate;
                        }
                    }
                    final View inflate = LayoutInflater.from(this.a.getContext()).inflate(android.support.v7.a.a.g.abc_activity_chooser_view_list_item, viewGroup, false);
                    inflate.setId(1);
                    ((TextView)inflate.findViewById(android.support.v7.a.a.f.title)).setText((CharSequence)this.a.getContext().getString(android.support.v7.a.a.h.abc_activity_chooser_view_see_all));
                    return inflate;
                }
                case 0: {
                    View inflate2 = null;
                    Label_0144: {
                        if (view != null) {
                            inflate2 = view;
                            if (view.getId() == android.support.v7.a.a.f.list_item) {
                                break Label_0144;
                            }
                        }
                        inflate2 = LayoutInflater.from(this.a.getContext()).inflate(android.support.v7.a.a.g.abc_activity_chooser_view_list_item, viewGroup, false);
                    }
                    final PackageManager packageManager = this.a.getContext().getPackageManager();
                    final ImageView imageView = (ImageView)inflate2.findViewById(android.support.v7.a.a.f.icon);
                    final ResolveInfo resolveInfo = (ResolveInfo)this.getItem(n);
                    imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
                    ((TextView)inflate2.findViewById(android.support.v7.a.a.f.title)).setText(resolveInfo.loadLabel(packageManager));
                    if (this.d && n == 0 && this.e) {
                        ae.b(inflate2, true);
                        return inflate2;
                    }
                    ae.b(inflate2, false);
                    return inflate2;
                }
            }
        }
        
        public int getViewTypeCount() {
            return 3;
        }
    }
    
    private class b implements View$OnClickListener, View$OnLongClickListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener
    {
        final /* synthetic */ ActivityChooserView a;
        
        private void a() {
            if (this.a.f != null) {
                this.a.f.onDismiss();
            }
        }
        
        public void onClick(final View view) {
            if (view == this.a.c) {
                this.a.b();
                final Intent b = this.a.a.d().b(this.a.a.d().a(this.a.a.b()));
                if (b != null) {
                    b.addFlags(524288);
                    this.a.getContext().startActivity(b);
                }
                return;
            }
            if (view == this.a.b) {
                this.a.g = false;
                this.a.a(this.a.h);
                return;
            }
            throw new IllegalArgumentException();
        }
        
        public void onDismiss() {
            this.a();
            if (this.a.d != null) {
                this.a.d.a(false);
            }
        }
        
        public void onItemClick(final AdapterView<?> adapterView, final View view, int n, final long n2) {
            switch (((a)adapterView.getAdapter()).getItemViewType(n)) {
                default: {
                    throw new IllegalArgumentException();
                }
                case 1: {
                    this.a.a(Integer.MAX_VALUE);
                    break;
                }
                case 0: {
                    this.a.b();
                    if (this.a.g) {
                        if (n > 0) {
                            this.a.a.d().c(n);
                            return;
                        }
                        break;
                    }
                    else {
                        if (!this.a.a.e()) {
                            ++n;
                        }
                        final Intent b = this.a.a.d().b(n);
                        if (b != null) {
                            b.addFlags(524288);
                            this.a.getContext().startActivity(b);
                            return;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        
        public boolean onLongClick(final View view) {
            if (view == this.a.c) {
                if (this.a.a.getCount() > 0) {
                    this.a.g = true;
                    this.a.a(this.a.h);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }
    }
}
