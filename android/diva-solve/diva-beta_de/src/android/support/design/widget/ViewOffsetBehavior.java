// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

class ViewOffsetBehavior<V extends View> extends Behavior<V>
{
    private int mTempLeftRightOffset;
    private int mTempTopBottomOffset;
    private ViewOffsetHelper mViewOffsetHelper;
    
    public ViewOffsetBehavior() {
        this.mTempTopBottomOffset = 0;
        this.mTempLeftRightOffset = 0;
    }
    
    public ViewOffsetBehavior(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTempTopBottomOffset = 0;
        this.mTempLeftRightOffset = 0;
    }
    
    public int getLeftAndRightOffset() {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.getLeftAndRightOffset();
        }
        return 0;
    }
    
    public int getTopAndBottomOffset() {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.getTopAndBottomOffset();
        }
        return 0;
    }
    
    @Override
    public boolean onLayoutChild(final CoordinatorLayout coordinatorLayout, final V v, final int n) {
        coordinatorLayout.onLayoutChild(v, n);
        if (this.mViewOffsetHelper == null) {
            this.mViewOffsetHelper = new ViewOffsetHelper(v);
        }
        this.mViewOffsetHelper.onViewLayout();
        if (this.mTempTopBottomOffset != 0) {
            this.mViewOffsetHelper.setTopAndBottomOffset(this.mTempTopBottomOffset);
            this.mTempTopBottomOffset = 0;
        }
        if (this.mTempLeftRightOffset != 0) {
            this.mViewOffsetHelper.setLeftAndRightOffset(this.mTempLeftRightOffset);
            this.mTempLeftRightOffset = 0;
        }
        return true;
    }
    
    public boolean setLeftAndRightOffset(final int n) {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setLeftAndRightOffset(n);
        }
        this.mTempLeftRightOffset = n;
        return false;
    }
    
    public boolean setTopAndBottomOffset(final int n) {
        if (this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setTopAndBottomOffset(n);
        }
        this.mTempTopBottomOffset = n;
        return false;
    }
}
