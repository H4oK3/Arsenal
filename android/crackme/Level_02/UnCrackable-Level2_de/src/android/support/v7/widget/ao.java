// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.widget.Toast;
import android.os.Build$VERSION;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityEvent;
import android.view.ViewParent;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View$OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View$MeasureSpec;
import android.widget.AdapterView;
import android.content.res.Configuration;
import android.view.View$OnClickListener;
import android.widget.AbsListView$LayoutParams;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.support.v7.a.a;
import android.widget.SpinnerAdapter;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Spinner;
import android.view.animation.Interpolator;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.HorizontalScrollView;

public class ao extends HorizontalScrollView implements AdapterView$OnItemSelectedListener
{
    private static final Interpolator j;
    Runnable a;
    ah b;
    int c;
    int d;
    private b e;
    private Spinner f;
    private boolean g;
    private int h;
    private int i;
    
    static {
        j = (Interpolator)new DecelerateInterpolator();
    }
    
    private boolean a() {
        return this.f != null && this.f.getParent() == this;
    }
    
    private void b() {
        if (this.a()) {
            return;
        }
        if (this.f == null) {
            this.f = this.d();
        }
        this.removeView((View)this.b);
        this.addView((View)this.f, new ViewGroup$LayoutParams(-2, -1));
        if (this.f.getAdapter() == null) {
            this.f.setAdapter((SpinnerAdapter)new a());
        }
        if (this.a != null) {
            this.removeCallbacks(this.a);
            this.a = null;
        }
        this.f.setSelection(this.i);
    }
    
    private boolean c() {
        if (!this.a()) {
            return false;
        }
        this.removeView((View)this.f);
        this.addView((View)this.b, new ViewGroup$LayoutParams(-2, -1));
        this.setTabSelected(this.f.getSelectedItemPosition());
        return false;
    }
    
    private Spinner d() {
        final x x = new x(this.getContext(), null, android.support.v7.a.a.a.actionDropDownStyle);
        x.setLayoutParams((ViewGroup$LayoutParams)new ah.a(-2, -1));
        x.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)this);
        return x;
    }
    
    c a(final android.support.v7.app.a.c c, final boolean b) {
        final c c2 = new c(this.getContext(), c, b);
        if (b) {
            c2.setBackgroundDrawable((Drawable)null);
            c2.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, this.h));
            return c2;
        }
        c2.setFocusable(true);
        if (this.e == null) {
            this.e = new b();
        }
        c2.setOnClickListener((View$OnClickListener)this.e);
        return c2;
    }
    
    public void a(final int n) {
        final View child = this.b.getChildAt(n);
        if (this.a != null) {
            this.removeCallbacks(this.a);
        }
        this.post(this.a = new Runnable() {
            @Override
            public void run() {
                ao.this.smoothScrollTo(child.getLeft() - (ao.this.getWidth() - child.getWidth()) / 2, 0);
                ao.this.a = null;
            }
        });
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            this.post(this.a);
        }
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        final android.support.v7.view.a a = android.support.v7.view.a.a(this.getContext());
        this.setContentHeight(a.e());
        this.d = a.g();
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.removeCallbacks(this.a);
        }
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        ((c)view).b().d();
    }
    
    public void onMeasure(int measuredWidth, int measuredWidth2) {
        measuredWidth2 = 1;
        final int mode = View$MeasureSpec.getMode(measuredWidth);
        final boolean fillViewport = mode == 1073741824;
        this.setFillViewport(fillViewport);
        final int childCount = this.b.getChildCount();
        if (childCount > 1 && (mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            if (childCount > 2) {
                this.c = (int)(View$MeasureSpec.getSize(measuredWidth) * 0.4f);
            }
            else {
                this.c = View$MeasureSpec.getSize(measuredWidth) / 2;
            }
            this.c = Math.min(this.c, this.d);
        }
        else {
            this.c = -1;
        }
        final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.h, 1073741824);
        if (fillViewport || !this.g) {
            measuredWidth2 = 0;
        }
        if (measuredWidth2 != 0) {
            this.b.measure(0, measureSpec);
            if (this.b.getMeasuredWidth() > View$MeasureSpec.getSize(measuredWidth)) {
                this.b();
            }
            else {
                this.c();
            }
        }
        else {
            this.c();
        }
        measuredWidth2 = this.getMeasuredWidth();
        super.onMeasure(measuredWidth, measureSpec);
        measuredWidth = this.getMeasuredWidth();
        if (fillViewport && measuredWidth2 != measuredWidth) {
            this.setTabSelected(this.i);
        }
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
    
    public void setAllowCollapse(final boolean g) {
        this.g = g;
    }
    
    public void setContentHeight(final int h) {
        this.h = h;
        this.requestLayout();
    }
    
    public void setTabSelected(final int n) {
        this.i = n;
        for (int childCount = this.b.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = this.b.getChildAt(i);
            final boolean selected = i == n;
            child.setSelected(selected);
            if (selected) {
                this.a(n);
            }
        }
        if (this.f != null && n >= 0) {
            this.f.setSelection(n);
        }
    }
    
    private class a extends BaseAdapter
    {
        public int getCount() {
            return ao.this.b.getChildCount();
        }
        
        public Object getItem(final int n) {
            return ((c)ao.this.b.getChildAt(n)).b();
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            if (view == null) {
                return (View)ao.this.a((android.support.v7.app.a.c)this.getItem(n), true);
            }
            ((c)view).a((android.support.v7.app.a.c)this.getItem(n));
            return view;
        }
    }
    
    private class b implements View$OnClickListener
    {
        public void onClick(final View view) {
            ((c)view).b().d();
            for (int childCount = ao.this.b.getChildCount(), i = 0; i < childCount; ++i) {
                final View child = ao.this.b.getChildAt(i);
                child.setSelected(child == view);
            }
        }
    }
    
    private class c extends ah implements View$OnLongClickListener
    {
        private final int[] b;
        private android.support.v7.app.a.c c;
        private TextView d;
        private ImageView e;
        private View f;
        
        public c(final Context context, final android.support.v7.app.a.c c, final boolean b) {
            super(context, null, android.support.v7.a.a.a.actionBarTabStyle);
            this.b = new int[] { 16842964 };
            this.c = c;
            final av a2 = av.a(context, null, this.b, android.support.v7.a.a.a.actionBarTabStyle, 0);
            if (a2.g(0)) {
                this.setBackgroundDrawable(a2.a(0));
            }
            a2.a();
            if (b) {
                this.setGravity(8388627);
            }
            this.a();
        }
        
        public void a() {
            final android.support.v7.app.a.c c = this.c;
            final View c2 = c.c();
            if (c2 != null) {
                final ViewParent parent = c2.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((c)parent).removeView(c2);
                    }
                    this.addView(c2);
                }
                this.f = c2;
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setImageDrawable((Drawable)null);
                }
                return;
            }
            if (this.f != null) {
                this.removeView(this.f);
                this.f = null;
            }
            final Drawable a = c.a();
            final CharSequence b = c.b();
            if (a != null) {
                if (this.e == null) {
                    final p e = new p(this.getContext());
                    final ah.a layoutParams = new ah.a(-2, -2);
                    layoutParams.h = 16;
                    e.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                    this.addView((View)e, 0);
                    this.e = e;
                }
                this.e.setImageDrawable(a);
                this.e.setVisibility(0);
            }
            else if (this.e != null) {
                this.e.setVisibility(8);
                this.e.setImageDrawable((Drawable)null);
            }
            boolean b2;
            if (!TextUtils.isEmpty(b)) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2) {
                if (this.d == null) {
                    final aa d = new aa(this.getContext(), null, android.support.v7.a.a.a.actionBarTabTextStyle);
                    d.setEllipsize(TextUtils$TruncateAt.END);
                    final ah.a layoutParams2 = new ah.a(-2, -2);
                    layoutParams2.h = 16;
                    d.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                    this.addView((View)d);
                    this.d = d;
                }
                this.d.setText(b);
                this.d.setVisibility(0);
            }
            else if (this.d != null) {
                this.d.setVisibility(8);
                this.d.setText((CharSequence)null);
            }
            if (this.e != null) {
                this.e.setContentDescription(c.e());
            }
            if (!b2 && !TextUtils.isEmpty(c.e())) {
                this.setOnLongClickListener((View$OnLongClickListener)this);
                return;
            }
            this.setOnLongClickListener((View$OnLongClickListener)null);
            this.setLongClickable(false);
        }
        
        public void a(final android.support.v7.app.a.c c) {
            this.c = c;
            this.a();
        }
        
        public android.support.v7.app.a.c b() {
            return this.c;
        }
        
        @Override
        public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName((CharSequence)android.support.v7.app.a.c.class.getName());
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (Build$VERSION.SDK_INT >= 14) {
                accessibilityNodeInfo.setClassName((CharSequence)android.support.v7.app.a.c.class.getName());
            }
        }
        
        public boolean onLongClick(final View view) {
            final int[] array = new int[2];
            this.getLocationOnScreen(array);
            final Context context = this.getContext();
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            final Toast text = Toast.makeText(context, this.c.e(), 0);
            text.setGravity(49, array[0] + width / 2 - widthPixels / 2, height);
            text.show();
            return true;
        }
        
        public void onMeasure(final int n, final int n2) {
            super.onMeasure(n, n2);
            if (ao.this.c > 0 && this.getMeasuredWidth() > ao.this.c) {
                super.onMeasure(View$MeasureSpec.makeMeasureSpec(ao.this.c, 1073741824), n2);
            }
        }
        
        public void setSelected(final boolean selected) {
            boolean b;
            if (this.isSelected() != selected) {
                b = true;
            }
            else {
                b = false;
            }
            super.setSelected(selected);
            if (b && selected) {
                this.sendAccessibilityEvent(4);
            }
        }
    }
}
