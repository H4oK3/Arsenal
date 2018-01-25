// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.text.method.TransformationMethod;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.TextView;
import android.support.annotation.RequiresApi;

@RequiresApi(9)
class AppCompatTextHelper
{
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableTopTint;
    final TextView mView;
    
    AppCompatTextHelper(final TextView mView) {
        this.mView = mView;
    }
    
    static AppCompatTextHelper create(final TextView textView) {
        if (Build$VERSION.SDK_INT >= 17) {
            return new AppCompatTextHelperV17(textView);
        }
        return new AppCompatTextHelper(textView);
    }
    
    protected static TintInfo createTintInfo(final Context context, final AppCompatDrawableManager appCompatDrawableManager, final int n) {
        final ColorStateList tintList = appCompatDrawableManager.getTintList(context, n);
        if (tintList != null) {
            final TintInfo tintInfo = new TintInfo();
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = tintList;
            return tintInfo;
        }
        return null;
    }
    
    final void applyCompoundDrawableTint(final Drawable drawable, final TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
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
    
    void loadFromAttributes(final AttributeSet set, int n) {
        final Context context = this.mView.getContext();
        final AppCompatDrawableManager value = AppCompatDrawableManager.get();
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.AppCompatTextHelper, n, 0);
        final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftTint = createTintInfo(context, value, obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
            this.mDrawableTopTint = createTintInfo(context, value, obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
            this.mDrawableRightTint = createTintInfo(context, value, obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.mDrawableBottomTint = createTintInfo(context, value, obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        obtainStyledAttributes.recycle();
        final boolean b = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean boolean1 = false;
        final boolean b2 = false;
        int n2 = 0;
        final boolean b3 = false;
        final ColorStateList list = null;
        ColorStateList colorStateList = null;
        final ColorStateList list2 = null;
        ColorStateList list3 = null;
        final ColorStateList list4 = null;
        if (resourceId != -1) {
            final TintTypedArray obtainStyledAttributes2 = TintTypedArray.obtainStyledAttributes(context, resourceId, R.styleable.TextAppearance);
            boolean1 = b2;
            n2 = (b3 ? 1 : 0);
            if (!b) {
                boolean1 = b2;
                n2 = (b3 ? 1 : 0);
                if (obtainStyledAttributes2.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                    n2 = 1;
                    boolean1 = obtainStyledAttributes2.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
                }
            }
            colorStateList = list;
            ColorStateList colorStateList2 = list4;
            if (Build$VERSION.SDK_INT < 23) {
                ColorStateList colorStateList3 = list2;
                if (obtainStyledAttributes2.hasValue(R.styleable.TextAppearance_android_textColor)) {
                    colorStateList3 = obtainStyledAttributes2.getColorStateList(R.styleable.TextAppearance_android_textColor);
                }
                colorStateList = colorStateList3;
                colorStateList2 = list4;
                if (obtainStyledAttributes2.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                    colorStateList2 = obtainStyledAttributes2.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
                    colorStateList = colorStateList3;
                }
            }
            obtainStyledAttributes2.recycle();
            list3 = colorStateList2;
        }
        final TintTypedArray obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.TextAppearance, n, 0);
        boolean boolean2 = boolean1;
        n = n2;
        if (!b) {
            boolean2 = boolean1;
            n = n2;
            if (obtainStyledAttributes3.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                n = 1;
                boolean2 = obtainStyledAttributes3.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
            }
        }
        ColorStateList textColor = colorStateList;
        ColorStateList colorStateList4 = list3;
        if (Build$VERSION.SDK_INT < 23) {
            if (obtainStyledAttributes3.hasValue(R.styleable.TextAppearance_android_textColor)) {
                colorStateList = obtainStyledAttributes3.getColorStateList(R.styleable.TextAppearance_android_textColor);
            }
            textColor = colorStateList;
            colorStateList4 = list3;
            if (obtainStyledAttributes3.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
                colorStateList4 = obtainStyledAttributes3.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
                textColor = colorStateList;
            }
        }
        obtainStyledAttributes3.recycle();
        if (textColor != null) {
            this.mView.setTextColor(textColor);
        }
        if (colorStateList4 != null) {
            this.mView.setHintTextColor(colorStateList4);
        }
        if (!b && n != 0) {
            this.setAllCaps(boolean2);
        }
    }
    
    void onSetTextAppearance(final Context context, final int n) {
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, n, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            this.setAllCaps(obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (Build$VERSION.SDK_INT < 23 && obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
            if (colorStateList != null) {
                this.mView.setTextColor(colorStateList);
            }
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
