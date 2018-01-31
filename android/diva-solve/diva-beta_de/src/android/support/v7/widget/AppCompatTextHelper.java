// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.method.TransformationMethod;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.content.res.TypedArray;
import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.internal.widget.TintManager;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.appcompat.R;
import android.widget.TextView;
import android.support.v7.internal.widget.TintInfo;

class AppCompatTextHelper
{
    private static final int[] TEXT_APPEARANCE_ATTRS;
    private static final int[] VIEW_ATTRS;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    final TextView mView;
    
    static {
        VIEW_ATTRS = new int[] { 16842804, 16843119, 16843117, 16843120, 16843118 };
        TEXT_APPEARANCE_ATTRS = new int[] { R.attr.textAllCaps };
    }
    
    AppCompatTextHelper(final TextView mView) {
        this.mView = mView;
    }
    
    static AppCompatTextHelper create(final TextView textView) {
        if (Build$VERSION.SDK_INT >= 17) {
            return new AppCompatTextHelperV17(textView);
        }
        return new AppCompatTextHelper(textView);
    }
    
    final void applyCompoundDrawableTint(final Drawable drawable, final TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            TintManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }
    
    void applyCompoundDrawablesTints() {
        if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            final Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            this.applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            this.applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            this.applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            this.applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
    }
    
    void loadFromAttributes(final AttributeSet set, final int n) {
        final Context context = this.mView.getContext();
        final TintManager value = TintManager.get(context);
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, AppCompatTextHelper.VIEW_ATTRS, n, 0);
        final int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        if (obtainStyledAttributes.hasValue(1)) {
            this.mDrawableLeftTint = new TintInfo();
            this.mDrawableLeftTint.mHasTintList = true;
            this.mDrawableLeftTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(1, 0));
        }
        if (obtainStyledAttributes.hasValue(2)) {
            this.mDrawableTopTint = new TintInfo();
            this.mDrawableTopTint.mHasTintList = true;
            this.mDrawableTopTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(2, 0));
        }
        if (obtainStyledAttributes.hasValue(3)) {
            this.mDrawableRightTint = new TintInfo();
            this.mDrawableRightTint.mHasTintList = true;
            this.mDrawableRightTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(3, 0));
        }
        if (obtainStyledAttributes.hasValue(4)) {
            this.mDrawableBottomTint = new TintInfo();
            this.mDrawableBottomTint.mHasTintList = true;
            this.mDrawableBottomTint.mTintList = value.getTintList(obtainStyledAttributes.getResourceId(4, 0));
        }
        obtainStyledAttributes.recycle();
        if (resourceId != -1) {
            final TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R.styleable.TextAppearance);
            if (obtainStyledAttributes2.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                this.setAllCaps(obtainStyledAttributes2.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
            }
            obtainStyledAttributes2.recycle();
        }
        final TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(set, AppCompatTextHelper.TEXT_APPEARANCE_ATTRS, n, 0);
        if (obtainStyledAttributes3.getBoolean(0, false)) {
            this.setAllCaps(true);
        }
        obtainStyledAttributes3.recycle();
    }
    
    void onSetTextAppearance(final Context context, final int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(n, AppCompatTextHelper.TEXT_APPEARANCE_ATTRS);
        if (obtainStyledAttributes.hasValue(0)) {
            this.setAllCaps(obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
    }
    
    void setAllCaps(final boolean b) {
        final TextView mView = this.mView;
        Object transformationMethod;
        if (b) {
            transformationMethod = new AllCapsTransformationMethod(this.mView.getContext());
        }
        else {
            transformationMethod = null;
        }
        mView.setTransformationMethod((TransformationMethod)transformationMethod);
    }
}
