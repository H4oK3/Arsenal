// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.app.e;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.content.res.Resources;

public class ax extends Resources
{
    private final WeakReference<Context> a;
    
    public ax(final Context context, final Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.a = new WeakReference<Context>(context);
    }
    
    public static boolean a() {
        return e.k() && Build$VERSION.SDK_INT <= 20;
    }
    
    final Drawable a(final int n) {
        return super.getDrawable(n);
    }
    
    public Drawable getDrawable(final int n) {
        final Context context = this.a.get();
        if (context != null) {
            return l.a().a(context, this, n);
        }
        return super.getDrawable(n);
    }
}
