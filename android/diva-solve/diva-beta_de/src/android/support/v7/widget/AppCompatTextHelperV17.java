// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.content.Context;
import android.support.v7.internal.widget.TintManager;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.support.v7.internal.widget.TintInfo;

class AppCompatTextHelperV17 extends AppCompatTextHelper
{
    private static final int[] VIEW_ATTRS_v17;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableStartTint;
    
    static {
        VIEW_ATTRS_v17 = new int[] { 16843666, 16843667 };
    }
    
    AppCompatTextHelperV17(final TextView textView) {
        super(textView);
    }
    
    @Override
    void applyCompoundDrawablesTints() {
        super.applyCompoundDrawablesTints();
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            final Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            this.applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            this.applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }
    
    @Override
    void loadFromAttributes(final AttributeSet set, final int n) {
        super.loadFromAttributes(set, n);
        final Context context = this.mView.getContext();
        final TintManager value = TintManager.get(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, AppCompatTextHelperV17.VIEW_ATTRS_v17, n, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            this.mDrawableStartTint = new TintInfo();
            this.mDrawableStartTint.mHasTintList = true;
            this.mDrawableStartTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(0, 0));
        }
        if (obtainStyledAttributes.hasValue(1)) {
            this.mDrawableEndTint = new TintInfo();
            this.mDrawableEndTint.mHasTintList = true;
            this.mDrawableEndTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(1, 0));
        }
        obtainStyledAttributes.recycle();
    }
}
