// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.content.Context;
import java.lang.ref.WeakReference;

class au extends am
{
    private final WeakReference<Context> a;
    
    public au(final Context context, final Resources resources) {
        super(resources);
        this.a = new WeakReference<Context>(context);
    }
    
    @Override
    public Drawable getDrawable(final int n) {
        final Drawable drawable = super.getDrawable(n);
        final Context context = this.a.get();
        if (drawable != null && context != null) {
            l.a();
            l.a(context, n, drawable);
        }
        return drawable;
    }
}
