// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.KeyEvent;
import android.widget.ListAdapter;
import android.support.v7.view.menu.g;
import android.widget.HeaderViewListAdapter;
import android.view.MotionEvent;
import android.content.res.Configuration;
import android.transition.Transition;
import android.os.Build$VERSION;
import android.view.MenuItem;
import android.support.v7.view.menu.h;
import android.util.AttributeSet;
import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class al extends ai implements ak
{
    private static Method a;
    private ak b;
    
    static {
        try {
            al.a = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        }
        catch (NoSuchMethodException ex) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }
    
    public al(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    @Override
    ae a(final Context context, final boolean b) {
        final a a = new a(context, b);
        a.setHoverListener(this);
        return a;
    }
    
    @Override
    public void a(final h h, final MenuItem menuItem) {
        if (this.b != null) {
            this.b.a(h, menuItem);
        }
    }
    
    public void a(final ak b) {
        this.b = b;
    }
    
    public void a(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.g.setEnterTransition((Transition)o);
        }
    }
    
    @Override
    public void b(final h h, final MenuItem menuItem) {
        if (this.b != null) {
            this.b.b(h, menuItem);
        }
    }
    
    public void b(final Object o) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.g.setExitTransition((Transition)o);
        }
    }
    
    public void b(final boolean b) {
        if (al.a == null) {
            return;
        }
        try {
            al.a.invoke(this.g, b);
        }
        catch (Exception ex) {
            Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
        }
    }
    
    public static class a extends ae
    {
        final int g;
        final int h;
        private ak i;
        private MenuItem j;
        
        public a(final Context context, final boolean b) {
            super(context, b);
            final Configuration configuration = context.getResources().getConfiguration();
            if (Build$VERSION.SDK_INT >= 17 && 1 == configuration.getLayoutDirection()) {
                this.g = 21;
                this.h = 22;
                return;
            }
            this.g = 22;
            this.h = 21;
        }
        
        public boolean onHoverEvent(final MotionEvent motionEvent) {
            if (this.i != null) {
                final ListAdapter adapter = this.getAdapter();
                int headersCount;
                g g;
                if (adapter instanceof HeaderViewListAdapter) {
                    final HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter)adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    g = (g)headerViewListAdapter.getWrappedAdapter();
                }
                else {
                    headersCount = 0;
                    g = (g)adapter;
                }
                while (true) {
                    Label_0178: {
                        if (motionEvent.getAction() == 10) {
                            break Label_0178;
                        }
                        final int pointToPosition = this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY());
                        if (pointToPosition == -1) {
                            break Label_0178;
                        }
                        final int n = pointToPosition - headersCount;
                        if (n < 0 || n >= g.getCount()) {
                            break Label_0178;
                        }
                        final Object a = g.a(n);
                        final MenuItem j = this.j;
                        if (j == a) {
                            return super.onHoverEvent(motionEvent);
                        }
                        final h a2 = g.a();
                        if (j != null) {
                            this.i.a(a2, j);
                        }
                        if ((this.j = (MenuItem)a) != null) {
                            this.i.b(a2, (MenuItem)a);
                            return super.onHoverEvent(motionEvent);
                        }
                        return super.onHoverEvent(motionEvent);
                    }
                    final Object a = null;
                    continue;
                }
            }
            return super.onHoverEvent(motionEvent);
        }
        
        public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
            final ListMenuItemView listMenuItemView = (ListMenuItemView)this.getSelectedView();
            if (listMenuItemView != null && n == this.g) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    this.performItemClick((View)listMenuItemView, this.getSelectedItemPosition(), this.getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView != null && n == this.h) {
                this.setSelection(-1);
                ((g)this.getAdapter()).a().a(false);
                return true;
            }
            return super.onKeyDown(n, keyEvent);
        }
        
        public void setHoverListener(final ak i) {
            this.i = i;
        }
    }
}
