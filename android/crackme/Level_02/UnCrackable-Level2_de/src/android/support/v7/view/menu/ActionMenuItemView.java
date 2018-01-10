// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.MotionEvent;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.widget.Toast;
import android.support.v4.g.ae;
import android.graphics.Rect;
import android.view.View;
import android.text.TextUtils;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.content.res.Resources;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.ag;
import android.graphics.drawable.Drawable;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.aa;

public class ActionMenuItemView extends aa implements p.a, ActionMenuView.a, View$OnClickListener, View$OnLongClickListener
{
    j a;
    h.b b;
    b c;
    private CharSequence d;
    private Drawable e;
    private ag f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    
    public ActionMenuItemView(final Context context) {
        this(context, null);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ActionMenuItemView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final Resources resources = context.getResources();
        this.g = this.e();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.ActionMenuItemView, n, 0);
        this.i = obtainStyledAttributes.getDimensionPixelSize(android.support.v7.a.a.j.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.k = (int)(resources.getDisplayMetrics().density * 32.0f + 0.5f);
        this.setOnClickListener((View$OnClickListener)this);
        this.setOnLongClickListener((View$OnLongClickListener)this);
        this.j = -1;
        this.setSaveEnabled(false);
    }
    
    private boolean e() {
        final Configuration configuration = this.getContext().getResources().getConfiguration();
        final int b = android.support.v4.b.a.a.b(this.getResources());
        final int a = android.support.v4.b.a.a.a(this.getResources());
        return b >= 480 || (b >= 640 && a >= 480) || configuration.orientation == 2;
    }
    
    private void f() {
        final boolean b = false;
        final boolean b2 = !TextUtils.isEmpty(this.d) && true;
        boolean b3 = false;
        Label_0051: {
            if (this.e != null) {
                b3 = b;
                if (!this.a.m()) {
                    break Label_0051;
                }
                if (!this.g) {
                    b3 = b;
                    if (!this.h) {
                        break Label_0051;
                    }
                }
            }
            b3 = true;
        }
        CharSequence d;
        if (b2 & b3) {
            d = this.d;
        }
        else {
            d = null;
        }
        this.setText(d);
    }
    
    @Override
    public void a(final j a, int visibility) {
        this.a = a;
        this.setIcon(a.getIcon());
        this.setTitle(a.a(this));
        this.setId(a.getItemId());
        if (a.isVisible()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        this.setVisibility(visibility);
        this.setEnabled(a.isEnabled());
        if (a.hasSubMenu() && this.f == null) {
            this.f = new a();
        }
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    public boolean b() {
        return !TextUtils.isEmpty(this.getText());
    }
    
    @Override
    public boolean c() {
        return this.b() && this.a.getIcon() == null;
    }
    
    @Override
    public boolean d() {
        return this.b();
    }
    
    @Override
    public j getItemData() {
        return this.a;
    }
    
    public void onClick(final View view) {
        if (this.b != null) {
            this.b.a(this.a);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.g = this.e();
        this.f();
    }
    
    public boolean onLongClick(final View view) {
        if (this.b()) {
            return false;
        }
        final int[] array = new int[2];
        final Rect rect = new Rect();
        this.getLocationOnScreen(array);
        this.getWindowVisibleDisplayFrame(rect);
        final Context context = this.getContext();
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = array[1];
        final int n2 = height / 2;
        int n3 = width / 2 + array[0];
        if (ae.c(view) == 0) {
            n3 = context.getResources().getDisplayMetrics().widthPixels - n3;
        }
        final Toast text = Toast.makeText(context, this.a.getTitle(), 0);
        if (n + n2 < rect.height()) {
            text.setGravity(8388661, n3, array[1] + height - rect.top);
        }
        else {
            text.setGravity(81, 0, height);
        }
        text.show();
        return true;
    }
    
    protected void onMeasure(int n, final int n2) {
        final boolean b = this.b();
        if (b && this.j >= 0) {
            super.setPadding(this.j, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
        super.onMeasure(n, n2);
        final int mode = View$MeasureSpec.getMode(n);
        n = View$MeasureSpec.getSize(n);
        final int measuredWidth = this.getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            n = Math.min(n, this.i);
        }
        else {
            n = this.i;
        }
        if (mode != 1073741824 && this.i > 0 && measuredWidth < n) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(n, 1073741824), n2);
        }
        if (!b && this.e != null) {
            super.setPadding((this.getMeasuredWidth() - this.e.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable)null);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.a.hasSubMenu() && this.f != null && this.f.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public void setCheckable(final boolean b) {
    }
    
    public void setChecked(final boolean b) {
    }
    
    public void setExpandedFormat(final boolean h) {
        if (this.h != h) {
            this.h = h;
            if (this.a != null) {
                this.a.h();
            }
        }
    }
    
    public void setIcon(final Drawable e) {
        this.e = e;
        if (e != null) {
            final int intrinsicWidth = e.getIntrinsicWidth();
            int intrinsicHeight;
            final int n = intrinsicHeight = e.getIntrinsicHeight();
            int k;
            if ((k = intrinsicWidth) > this.k) {
                final float n2 = this.k / intrinsicWidth;
                k = this.k;
                intrinsicHeight = (int)(n * n2);
            }
            int i = intrinsicHeight;
            int n3 = k;
            if (intrinsicHeight > this.k) {
                final float n4 = this.k / intrinsicHeight;
                i = this.k;
                n3 = (int)(k * n4);
            }
            e.setBounds(0, 0, n3, i);
        }
        this.setCompoundDrawables(e, (Drawable)null, (Drawable)null, (Drawable)null);
        this.f();
    }
    
    public void setItemInvoker(final h.b b) {
        this.b = b;
    }
    
    public void setPadding(final int j, final int n, final int n2, final int n3) {
        super.setPadding(this.j = j, n, n2, n3);
    }
    
    public void setPopupCallback(final b c) {
        this.c = c;
    }
    
    public void setTitle(final CharSequence d) {
        this.setContentDescription(this.d = d);
        this.f();
    }
    
    private class a extends ag
    {
        public a() {
            super((View)ActionMenuItemView.this);
        }
        
        @Override
        public s a() {
            if (ActionMenuItemView.this.c != null) {
                return ActionMenuItemView.this.c.a();
            }
            return null;
        }
        
        @Override
        protected boolean b() {
            boolean b2;
            final boolean b = b2 = false;
            if (ActionMenuItemView.this.b != null) {
                b2 = b;
                if (ActionMenuItemView.this.b.a(ActionMenuItemView.this.a)) {
                    final s a = this.a();
                    b2 = b;
                    if (a != null) {
                        b2 = b;
                        if (a.d()) {
                            b2 = true;
                        }
                    }
                }
            }
            return b2;
        }
    }
    
    public abstract static class b
    {
        public abstract s a();
    }
}
