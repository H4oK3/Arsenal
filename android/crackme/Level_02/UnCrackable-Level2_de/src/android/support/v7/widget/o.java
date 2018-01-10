// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.support.v7.b.a.b;
import android.widget.ImageView;

public class o
{
    private final ImageView a;
    
    public o(final ImageView a) {
        this.a = a;
    }
    
    public void a(final int n) {
        if (n != 0) {
            final Drawable b = android.support.v7.b.a.b.b(this.a.getContext(), n);
            if (b != null) {
                ad.a(b);
            }
            this.a.setImageDrawable(b);
            return;
        }
        this.a.setImageDrawable((Drawable)null);
    }
    
    public void a(final AttributeSet set, int g) {
        final av av = null;
        av av2 = null;
        av a = av;
        try {
            Drawable drawable2;
            final Drawable drawable = drawable2 = this.a.getDrawable();
            if (drawable == null) {
                a = av;
                final av av3 = a = android.support.v7.widget.av.a(this.a.getContext(), set, android.support.v7.a.a.j.AppCompatImageView, g, 0);
                g = av3.g(android.support.v7.a.a.j.AppCompatImageView_srcCompat, -1);
                drawable2 = drawable;
                av2 = av3;
                if (g != -1) {
                    a = av3;
                    final Drawable imageDrawable = drawable2 = b.b(this.a.getContext(), g);
                    av2 = av3;
                    if (imageDrawable != null) {
                        a = av3;
                        this.a.setImageDrawable(imageDrawable);
                        av2 = av3;
                        drawable2 = imageDrawable;
                    }
                }
            }
            if (drawable2 != null) {
                a = av2;
                ad.a(drawable2);
            }
        }
        finally {
            if (a != null) {
                a.a();
            }
        }
    }
    
    boolean a() {
        final Drawable background = this.a.getBackground();
        return Build$VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable);
    }
}
