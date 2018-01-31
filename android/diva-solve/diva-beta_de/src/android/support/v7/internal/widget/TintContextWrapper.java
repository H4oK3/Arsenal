// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.content.res.Resources$NotFoundException;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.content.res.Resources;
import android.content.ContextWrapper;

public class TintContextWrapper extends ContextWrapper
{
    private Resources mResources;
    
    private TintContextWrapper(final Context context) {
        super(context);
    }
    
    public static Context wrap(final Context context) {
        Object o = context;
        if (!(context instanceof TintContextWrapper)) {
            o = new TintContextWrapper(context);
        }
        return (Context)o;
    }
    
    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = new TintResources(super.getResources(), TintManager.get((Context)this));
        }
        return this.mResources;
    }
    
    static class TintResources extends ResourcesWrapper
    {
        private final TintManager mTintManager;
        
        public TintResources(final Resources resources, final TintManager mTintManager) {
            super(resources);
            this.mTintManager = mTintManager;
        }
        
        @Override
        public Drawable getDrawable(final int n) throws Resources$NotFoundException {
            final Drawable drawable = super.getDrawable(n);
            if (drawable != null) {
                this.mTintManager.tintDrawableUsingColorFilter(n, drawable);
            }
            return drawable;
        }
    }
}
