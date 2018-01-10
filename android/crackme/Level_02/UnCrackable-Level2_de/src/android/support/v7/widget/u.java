// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Bitmap;
import android.support.v4.g.ae;
import android.widget.ProgressBar;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RatingBar;

public class u extends RatingBar
{
    private s a;
    
    public u(final Context context, final AttributeSet set) {
        this(context, set, android.support.v7.a.a.a.ratingBarStyle);
    }
    
    public u(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        (this.a = new s((ProgressBar)this)).a(set, n);
    }
    
    protected void onMeasure(final int n, final int n2) {
        synchronized (this) {
            super.onMeasure(n, n2);
            final Bitmap a = this.a.a();
            if (a != null) {
                this.setMeasuredDimension(ae.a(a.getWidth() * this.getNumStars(), n, 0), this.getMeasuredHeight());
            }
        }
    }
}
