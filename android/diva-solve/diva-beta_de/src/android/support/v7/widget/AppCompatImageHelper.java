// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.support.v7.internal.widget.TintManager;

class AppCompatImageHelper
{
    private static final int[] VIEW_ATTRS;
    private final TintManager mTintManager;
    private final ImageView mView;
    
    static {
        VIEW_ATTRS = new int[] { 16843033 };
    }
    
    AppCompatImageHelper(final ImageView mView, final TintManager mTintManager) {
        this.mView = mView;
        this.mTintManager = mTintManager;
    }
    
    void loadFromAttributes(AttributeSet obtainStyledAttributes, final int n) {
        obtainStyledAttributes = (AttributeSet)TintTypedArray.obtainStyledAttributes(this.mView.getContext(), obtainStyledAttributes, AppCompatImageHelper.VIEW_ATTRS, n, 0);
        try {
            if (((TintTypedArray)obtainStyledAttributes).hasValue(0)) {
                this.mView.setImageDrawable(((TintTypedArray)obtainStyledAttributes).getDrawable(0));
            }
        }
        finally {
            ((TintTypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    void setImageResource(final int n) {
        final ImageView mView = this.mView;
        Drawable imageDrawable;
        if (this.mTintManager != null) {
            imageDrawable = this.mTintManager.getDrawable(n);
        }
        else {
            imageDrawable = ContextCompat.getDrawable(this.mView.getContext(), n);
        }
        mView.setImageDrawable(imageDrawable);
    }
}
