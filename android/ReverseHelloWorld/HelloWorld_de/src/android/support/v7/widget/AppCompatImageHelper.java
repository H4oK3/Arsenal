// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.content.res.AppCompatResources;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build$VERSION;
import android.widget.ImageView;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo.Scope.LIBRARY_GROUP })
public class AppCompatImageHelper
{
    private final ImageView mView;
    
    public AppCompatImageHelper(final ImageView mView) {
        this.mView = mView;
    }
    
    boolean hasOverlappingRendering() {
        final Drawable background = this.mView.getBackground();
        return Build$VERSION.SDK_INT < 21 || !(background instanceof RippleDrawable);
    }
    
    public void loadFromAttributes(final AttributeSet set, int resourceId) {
        final TintTypedArray tintTypedArray = null;
        TintTypedArray tintTypedArray2 = null;
        TintTypedArray obtainStyledAttributes = tintTypedArray;
        try {
            Drawable drawable2;
            final Drawable drawable = drawable2 = this.mView.getDrawable();
            if (drawable == null) {
                obtainStyledAttributes = tintTypedArray;
                final TintTypedArray tintTypedArray3 = obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), set, R.styleable.AppCompatImageView, resourceId, 0);
                resourceId = tintTypedArray3.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
                tintTypedArray2 = tintTypedArray3;
                drawable2 = drawable;
                if (resourceId != -1) {
                    obtainStyledAttributes = tintTypedArray3;
                    final Drawable drawable3 = AppCompatResources.getDrawable(this.mView.getContext(), resourceId);
                    tintTypedArray2 = tintTypedArray3;
                    if ((drawable2 = drawable3) != null) {
                        obtainStyledAttributes = tintTypedArray3;
                        this.mView.setImageDrawable(drawable3);
                        drawable2 = drawable3;
                        tintTypedArray2 = tintTypedArray3;
                    }
                }
            }
            if (drawable2 != null) {
                obtainStyledAttributes = tintTypedArray2;
                DrawableUtils.fixDrawable(drawable2);
            }
        }
        finally {
            if (obtainStyledAttributes != null) {
                obtainStyledAttributes.recycle();
            }
        }
    }
    
    public void setImageResource(final int n) {
        if (n != 0) {
            final Drawable drawable = AppCompatResources.getDrawable(this.mView.getContext(), n);
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            this.mView.setImageDrawable(drawable);
            return;
        }
        this.mView.setImageDrawable((Drawable)null);
    }
}
