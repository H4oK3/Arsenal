// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.MotionEvent;
import android.graphics.Canvas;
import android.support.v4.c.a.a;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.ListAdapter;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.util.AttributeSet;
import android.content.Context;
import java.lang.reflect.Field;
import android.graphics.Rect;
import android.widget.ListView;

public class aj extends ListView
{
    private static final int[] g;
    final Rect a;
    int b;
    int c;
    int d;
    int e;
    protected int f;
    private Field h;
    private a i;
    
    static {
        g = new int[] { 0 };
    }
    
    public aj(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a = new Rect();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        try {
            (this.h = AbsListView.class.getDeclaredField("mIsChildViewEnabled")).setAccessible(true);
        }
        catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }
    
    public int a(int n, int listPaddingTop, int dividerHeight, final int n2, final int n3) {
        listPaddingTop = this.getListPaddingTop();
        final int listPaddingBottom = this.getListPaddingBottom();
        this.getListPaddingLeft();
        this.getListPaddingRight();
        dividerHeight = this.getDividerHeight();
        final Drawable divider = this.getDivider();
        final ListAdapter adapter = this.getAdapter();
        if (adapter != null) {
            listPaddingTop += listPaddingBottom;
            if (dividerHeight <= 0 || divider == null) {
                dividerHeight = 0;
            }
            int n4 = 0;
            View view = null;
            int n5 = 0;
            final int count = adapter.getCount();
            int i = 0;
            while (i < count) {
                final int itemViewType = adapter.getItemViewType(i);
                if (itemViewType != n5) {
                    view = null;
                    n5 = itemViewType;
                }
                final View view2 = adapter.getView(i, view, (ViewGroup)this);
                ViewGroup$LayoutParams layoutParams;
                if ((layoutParams = view2.getLayoutParams()) == null) {
                    layoutParams = this.generateDefaultLayoutParams();
                    view2.setLayoutParams(layoutParams);
                }
                int n6;
                if (layoutParams.height > 0) {
                    n6 = View$MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                else {
                    n6 = View$MeasureSpec.makeMeasureSpec(0, 0);
                }
                view2.measure(n, n6);
                view2.forceLayout();
                if (i > 0) {
                    listPaddingTop += dividerHeight;
                }
                listPaddingTop += view2.getMeasuredHeight();
                if (listPaddingTop >= n2) {
                    n = n2;
                    if (n3 < 0) {
                        return n;
                    }
                    n = n2;
                    if (i <= n3) {
                        return n;
                    }
                    n = n2;
                    if (n4 > 0 && listPaddingTop != (n = n2)) {
                        return n4;
                    }
                    return n;
                }
                else {
                    int n7 = n4;
                    if (n3 >= 0) {
                        n7 = n4;
                        if (i >= n3) {
                            n7 = listPaddingTop;
                        }
                    }
                    ++i;
                    n4 = n7;
                    view = view2;
                }
            }
            return listPaddingTop;
        }
        n = listPaddingTop + listPaddingBottom;
        return n;
    }
    
    protected void a(final int n, final View view) {
        boolean b = true;
        final Drawable selector = this.getSelector();
        boolean b2;
        if (selector != null && n != -1) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2) {
            selector.setVisible(false, false);
        }
        this.b(n, view);
        if (b2) {
            final Rect a = this.a;
            final float exactCenterX = a.exactCenterX();
            final float exactCenterY = a.exactCenterY();
            if (this.getVisibility() != 0) {
                b = false;
            }
            selector.setVisible(b, false);
            android.support.v4.c.a.a.a(selector, exactCenterX, exactCenterY);
        }
    }
    
    protected void a(final int n, final View view, final float n2, final float n3) {
        this.a(n, view);
        final Drawable selector = this.getSelector();
        if (selector != null && n != -1) {
            android.support.v4.c.a.a.a(selector, n2, n3);
        }
    }
    
    protected void a(final Canvas canvas) {
        if (!this.a.isEmpty()) {
            final Drawable selector = this.getSelector();
            if (selector != null) {
                selector.setBounds(this.a);
                selector.draw(canvas);
            }
        }
    }
    
    protected boolean a() {
        return false;
    }
    
    protected void b() {
        final Drawable selector = this.getSelector();
        if (selector != null && this.c()) {
            selector.setState(this.getDrawableState());
        }
    }
    
    protected void b(final int n, final View view) {
        final Rect a = this.a;
        a.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        a.left -= this.b;
        a.top -= this.c;
        a.right += this.d;
        a.bottom += this.e;
        try {
            final boolean boolean1 = this.h.getBoolean(this);
            if (view.isEnabled() != boolean1) {
                this.h.set(this, !boolean1);
                if (n != -1) {
                    this.refreshDrawableState();
                }
            }
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
    
    protected boolean c() {
        return this.a() && this.isPressed();
    }
    
    protected void dispatchDraw(final Canvas canvas) {
        this.a(canvas);
        super.dispatchDraw(canvas);
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.setSelectorEnabled(true);
        this.b();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.f = this.pointToPosition((int)motionEvent.getX(), (int)motionEvent.getY());
                break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setSelector(final Drawable drawable) {
        a i;
        if (drawable != null) {
            i = new a(drawable);
        }
        else {
            i = null;
        }
        super.setSelector((Drawable)(this.i = i));
        final Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.b = rect.left;
        this.c = rect.top;
        this.d = rect.right;
        this.e = rect.bottom;
    }
    
    protected void setSelectorEnabled(final boolean b) {
        if (this.i != null) {
            this.i.a(b);
        }
    }
    
    private static class a extends android.support.v7.c.a.a
    {
        private boolean a;
        
        public a(final Drawable drawable) {
            super(drawable);
            this.a = true;
        }
        
        void a(final boolean a) {
            this.a = a;
        }
        
        @Override
        public void draw(final Canvas canvas) {
            if (this.a) {
                super.draw(canvas);
            }
        }
        
        @Override
        public void setHotspot(final float n, final float n2) {
            if (this.a) {
                super.setHotspot(n, n2);
            }
        }
        
        @Override
        public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
            if (this.a) {
                super.setHotspotBounds(n, n2, n3, n4);
            }
        }
        
        @Override
        public boolean setState(final int[] state) {
            return this.a && super.setState(state);
        }
        
        @Override
        public boolean setVisible(final boolean b, final boolean b2) {
            return this.a && super.setVisible(b, b2);
        }
    }
}
