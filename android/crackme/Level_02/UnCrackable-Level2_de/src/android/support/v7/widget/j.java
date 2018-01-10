// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.b.a.b;
import android.widget.TextView;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.CheckedTextView;

public class j extends CheckedTextView
{
    private static final int[] a;
    private y b;
    
    static {
        a = new int[] { 16843016 };
    }
    
    public j(final Context context, final AttributeSet set) {
        this(context, set, 16843720);
    }
    
    public j(final Context context, final AttributeSet set, final int n) {
        super(as.a(context), set, n);
        (this.b = y.a((TextView)this)).a(set, n);
        this.b.a();
        final av a = av.a(this.getContext(), set, j.a, n, 0);
        this.setCheckMarkDrawable(a.a(0));
        a.a();
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.a();
        }
    }
    
    public void setCheckMarkDrawable(final int n) {
        this.setCheckMarkDrawable(android.support.v7.b.a.b.b(this.getContext(), n));
    }
    
    public void setTextAppearance(final Context context, final int n) {
        super.setTextAppearance(context, n);
        if (this.b != null) {
            this.b.a(context, n);
        }
    }
}
