// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.View;
import android.widget.AdapterView;
import android.view.MenuItem;
import android.support.v7.widget.av;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements b, p, AdapterView$OnItemClickListener
{
    private static final int[] a;
    private h b;
    private int c;
    
    static {
        a = new int[] { 16842964, 16843049 };
    }
    
    public ExpandedMenuView(final Context context, final AttributeSet set) {
        this(context, set, 16842868);
    }
    
    public ExpandedMenuView(final Context context, final AttributeSet set, final int n) {
        super(context, set);
        this.setOnItemClickListener((AdapterView$OnItemClickListener)this);
        final av a = av.a(context, set, ExpandedMenuView.a, n, 0);
        if (a.g(0)) {
            this.setBackgroundDrawable(a.a(0));
        }
        if (a.g(1)) {
            this.setDivider(a.a(1));
        }
        a.a();
    }
    
    public void a(final h b) {
        this.b = b;
    }
    
    public boolean a(final j j) {
        return this.b.a((MenuItem)j, 0);
    }
    
    public int getWindowAnimations() {
        return this.c;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setChildrenDrawingCacheEnabled(false);
    }
    
    public void onItemClick(final AdapterView adapterView, final View view, final int n, final long n2) {
        this.a((j)this.getAdapter().getItem(n));
    }
}
