// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.widget.AdapterView;
import android.widget.PopupWindow$OnDismissListener;
import android.view.MenuItem;
import android.widget.HeaderViewListAdapter;
import android.view.View;
import android.widget.FrameLayout;
import android.view.View$MeasureSpec;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.graphics.Rect;
import android.widget.AdapterView$OnItemClickListener;

abstract class m implements o, s, AdapterView$OnItemClickListener
{
    private Rect a;
    
    protected static int a(final ListAdapter listAdapter, ViewGroup viewGroup, final Context context, final int n) {
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        final int count = listAdapter.getCount();
        int i = 0;
        int n2 = 0;
        View view = null;
        int n3 = 0;
        while (i < count) {
            final int itemViewType = listAdapter.getItemViewType(i);
            int n4;
            if (itemViewType != (n4 = n2)) {
                n4 = itemViewType;
                view = null;
            }
            if (viewGroup == null) {
                viewGroup = (ViewGroup)new FrameLayout(context);
            }
            view = listAdapter.getView(i, view, viewGroup);
            view.measure(measureSpec, measureSpec2);
            final int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= n) {
                return n;
            }
            if (measuredWidth > n3) {
                n3 = measuredWidth;
            }
            ++i;
            n2 = n4;
        }
        return n3;
    }
    
    protected static g a(final ListAdapter listAdapter) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            return (g)((HeaderViewListAdapter)listAdapter).getWrappedAdapter();
        }
        return (g)listAdapter;
    }
    
    protected static boolean b(final h h) {
        final boolean b = false;
        final int size = h.size();
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= size) {
                break;
            }
            final MenuItem item = h.getItem(n);
            if (item.isVisible() && item.getIcon() != null) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public abstract void a(final int p0);
    
    @Override
    public void a(final Context context, final h h) {
    }
    
    public void a(final Rect a) {
        this.a = a;
    }
    
    public abstract void a(final h p0);
    
    public abstract void a(final View p0);
    
    public abstract void a(final PopupWindow$OnDismissListener p0);
    
    public abstract void a(final boolean p0);
    
    @Override
    public boolean a(final h h, final j j) {
        return false;
    }
    
    public abstract void b(final int p0);
    
    @Override
    public boolean b(final h h, final j j) {
        return false;
    }
    
    public abstract void c(final int p0);
    
    public abstract void c(final boolean p0);
    
    protected boolean f() {
        return true;
    }
    
    public Rect g() {
        return this.a;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, int n, final long n2) {
        final ListAdapter listAdapter = (ListAdapter)adapterView.getAdapter();
        final h b = a(listAdapter).b;
        final MenuItem menuItem = (MenuItem)listAdapter.getItem(n);
        if (this.f()) {
            n = 0;
        }
        else {
            n = 4;
        }
        b.a(menuItem, this, n);
    }
}
