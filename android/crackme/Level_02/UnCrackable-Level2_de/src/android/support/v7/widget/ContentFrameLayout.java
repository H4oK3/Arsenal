// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.DisplayMetrics;
import android.view.View$MeasureSpec;
import android.view.View;
import android.support.v4.g.ae;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout
{
    private TypedValue a;
    private TypedValue b;
    private TypedValue c;
    private TypedValue d;
    private TypedValue e;
    private TypedValue f;
    private final Rect g;
    private a h;
    
    public ContentFrameLayout(final Context context) {
        this(context, null);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public ContentFrameLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.g = new Rect();
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.g.set(n, n2, n3, n4);
        if (ae.q((View)this)) {
            this.requestLayout();
        }
    }
    
    public void a(final Rect rect) {
        this.fitSystemWindows(rect);
    }
    
    public TypedValue getFixedHeightMajor() {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        return this.e;
    }
    
    public TypedValue getFixedHeightMinor() {
        if (this.f == null) {
            this.f = new TypedValue();
        }
        return this.f;
    }
    
    public TypedValue getFixedWidthMajor() {
        if (this.c == null) {
            this.c = new TypedValue();
        }
        return this.c;
    }
    
    public TypedValue getFixedWidthMinor() {
        if (this.d == null) {
            this.d = new TypedValue();
        }
        return this.d;
    }
    
    public TypedValue getMinWidthMajor() {
        if (this.a == null) {
            this.a = new TypedValue();
        }
        return this.a;
    }
    
    public TypedValue getMinWidthMinor() {
        if (this.b == null) {
            this.b = new TypedValue();
        }
        return this.b;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h != null) {
            this.h.a();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.h != null) {
            this.h.b();
        }
    }
    
    protected void onMeasure(int measureSpec, int n) {
        final int n2 = 0;
        final DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
        boolean b;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            b = true;
        }
        else {
            b = false;
        }
        final int mode = View$MeasureSpec.getMode(measureSpec);
        final int mode2 = View$MeasureSpec.getMode(n);
        while (true) {
            Label_0495: {
                if (mode != Integer.MIN_VALUE) {
                    break Label_0495;
                }
                TypedValue typedValue;
                if (b) {
                    typedValue = this.d;
                }
                else {
                    typedValue = this.c;
                }
                if (typedValue == null || typedValue.type == 0) {
                    break Label_0495;
                }
                int n3;
                if (typedValue.type == 5) {
                    n3 = (int)typedValue.getDimension(displayMetrics);
                }
                else if (typedValue.type == 6) {
                    n3 = (int)typedValue.getFraction((float)displayMetrics.widthPixels, (float)displayMetrics.widthPixels);
                }
                else {
                    n3 = 0;
                }
                if (n3 <= 0) {
                    break Label_0495;
                }
                final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(Math.min(n3 - (this.g.left + this.g.right), View$MeasureSpec.getSize(measureSpec)), 1073741824);
                final int n4 = 1;
                int measureSpec3 = n;
                if (mode2 == Integer.MIN_VALUE) {
                    TypedValue typedValue2;
                    if (b) {
                        typedValue2 = this.e;
                    }
                    else {
                        typedValue2 = this.f;
                    }
                    measureSpec3 = n;
                    if (typedValue2 != null) {
                        measureSpec3 = n;
                        if (typedValue2.type != 0) {
                            if (typedValue2.type == 5) {
                                measureSpec = (int)typedValue2.getDimension(displayMetrics);
                            }
                            else if (typedValue2.type == 6) {
                                measureSpec = (int)typedValue2.getFraction((float)displayMetrics.heightPixels, (float)displayMetrics.heightPixels);
                            }
                            else {
                                measureSpec = 0;
                            }
                            measureSpec3 = n;
                            if (measureSpec > 0) {
                                measureSpec3 = View$MeasureSpec.makeMeasureSpec(Math.min(measureSpec - (this.g.top + this.g.bottom), View$MeasureSpec.getSize(n)), 1073741824);
                            }
                        }
                    }
                }
                super.onMeasure(measureSpec2, measureSpec3);
                final int measuredWidth = this.getMeasuredWidth();
                final int measureSpec4 = View$MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
                while (true) {
                    Label_0476: {
                        if (n4 != 0 || mode != Integer.MIN_VALUE) {
                            break Label_0476;
                        }
                        TypedValue typedValue3;
                        if (b) {
                            typedValue3 = this.b;
                        }
                        else {
                            typedValue3 = this.a;
                        }
                        if (typedValue3 == null || typedValue3.type == 0) {
                            break Label_0476;
                        }
                        if (typedValue3.type == 5) {
                            measureSpec = (int)typedValue3.getDimension(displayMetrics);
                        }
                        else if (typedValue3.type == 6) {
                            measureSpec = (int)typedValue3.getFraction((float)displayMetrics.widthPixels, (float)displayMetrics.widthPixels);
                        }
                        else {
                            measureSpec = 0;
                        }
                        n = measureSpec;
                        if (measureSpec > 0) {
                            n = measureSpec - (this.g.left + this.g.right);
                        }
                        if (measuredWidth >= n) {
                            break Label_0476;
                        }
                        measureSpec = View$MeasureSpec.makeMeasureSpec(n, 1073741824);
                        n = 1;
                        if (n != 0) {
                            super.onMeasure(measureSpec, measureSpec3);
                        }
                        return;
                    }
                    measureSpec = measureSpec4;
                    n = n2;
                    continue;
                }
            }
            final int n4 = 0;
            final int measureSpec2 = measureSpec;
            continue;
        }
    }
    
    public void setAttachListener(final a h) {
        this.h = h;
    }
    
    public interface a
    {
        void a();
        
        void b();
    }
}
