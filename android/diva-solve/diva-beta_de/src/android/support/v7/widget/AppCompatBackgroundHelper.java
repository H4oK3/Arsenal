// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.TypedArray;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintInfo;

class AppCompatBackgroundHelper
{
    private TintInfo mBackgroundTint;
    private TintInfo mInternalBackgroundTint;
    private final TintManager mTintManager;
    private final View mView;
    
    AppCompatBackgroundHelper(final View mView, final TintManager mTintManager) {
        this.mView = mView;
        this.mTintManager = mTintManager;
    }
    
    void applySupportBackgroundTint() {
        final Drawable background = this.mView.getBackground();
        if (background != null) {
            if (this.mBackgroundTint != null) {
                TintManager.tintDrawable(background, this.mBackgroundTint, this.mView.getDrawableState());
            }
            else if (this.mInternalBackgroundTint != null) {
                TintManager.tintDrawable(background, this.mInternalBackgroundTint, this.mView.getDrawableState());
            }
        }
    }
    
    ColorStateList getSupportBackgroundTintList() {
        if (this.mBackgroundTint != null) {
            return this.mBackgroundTint.mTintList;
        }
        return null;
    }
    
    PorterDuff$Mode getSupportBackgroundTintMode() {
        if (this.mBackgroundTint != null) {
            return this.mBackgroundTint.mTintMode;
        }
        return null;
    }
    
    void loadFromAttributes(AttributeSet obtainStyledAttributes, final int n) {
        obtainStyledAttributes = (AttributeSet)this.mView.getContext().obtainStyledAttributes(obtainStyledAttributes, R.styleable.ViewBackgroundHelper, n, 0);
        try {
            if (((TypedArray)obtainStyledAttributes).hasValue(R.styleable.ViewBackgroundHelper_android_background)) {
                final ColorStateList tintList = this.mTintManager.getTintList(((TypedArray)obtainStyledAttributes).getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1));
                if (tintList != null) {
                    this.setInternalBackgroundTint(tintList);
                }
            }
            if (((TypedArray)obtainStyledAttributes).hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, ((TypedArray)obtainStyledAttributes).getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (((TypedArray)obtainStyledAttributes).hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode(((TypedArray)obtainStyledAttributes).getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
    
    void onSetBackgroundDrawable(final Drawable drawable) {
        this.setInternalBackgroundTint(null);
    }
    
    void onSetBackgroundResource(final int n) {
        ColorStateList tintList;
        if (this.mTintManager != null) {
            tintList = this.mTintManager.getTintList(n);
        }
        else {
            tintList = null;
        }
        this.setInternalBackgroundTint(tintList);
    }
    
    void setInternalBackgroundTint(final ColorStateList mTintList) {
        if (mTintList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            this.mInternalBackgroundTint.mTintList = mTintList;
            this.mInternalBackgroundTint.mHasTintList = true;
        }
        else {
            this.mInternalBackgroundTint = null;
        }
        this.applySupportBackgroundTint();
    }
    
    void setSupportBackgroundTintList(final ColorStateList mTintList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintList = mTintList;
        this.mBackgroundTint.mHasTintList = true;
        this.applySupportBackgroundTint();
    }
    
    void setSupportBackgroundTintMode(final PorterDuff$Mode mTintMode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        this.mBackgroundTint.mTintMode = mTintMode;
        this.mBackgroundTint.mHasTintMode = true;
        this.applySupportBackgroundTint();
    }
}
