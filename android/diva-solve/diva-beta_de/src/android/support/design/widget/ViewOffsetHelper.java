// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.ViewParent;
import android.os.Build$VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;

class ViewOffsetHelper
{
    private int mLayoutLeft;
    private int mLayoutTop;
    private int mOffsetLeft;
    private int mOffsetTop;
    private final View mView;
    
    public ViewOffsetHelper(final View mView) {
        this.mView = mView;
    }
    
    private static void tickleInvalidationFlag(final View view) {
        final float translationX = ViewCompat.getTranslationX(view);
        ViewCompat.setTranslationY(view, 1.0f + translationX);
        ViewCompat.setTranslationY(view, translationX);
    }
    
    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
        ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
        if (Build$VERSION.SDK_INT < 23) {
            tickleInvalidationFlag(this.mView);
            final ViewParent parent = this.mView.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View)parent);
            }
        }
    }
    
    public int getLeftAndRightOffset() {
        return this.mOffsetLeft;
    }
    
    public int getTopAndBottomOffset() {
        return this.mOffsetTop;
    }
    
    public void onViewLayout() {
        this.mLayoutTop = this.mView.getTop();
        this.mLayoutLeft = this.mView.getLeft();
        this.updateOffsets();
    }
    
    public boolean setLeftAndRightOffset(final int mOffsetLeft) {
        if (this.mOffsetLeft != mOffsetLeft) {
            this.mOffsetLeft = mOffsetLeft;
            this.updateOffsets();
            return true;
        }
        return false;
    }
    
    public boolean setTopAndBottomOffset(final int mOffsetTop) {
        if (this.mOffsetTop != mOffsetTop) {
            this.mOffsetTop = mOffsetTop;
            this.updateOffsets();
            return true;
        }
        return false;
    }
}
