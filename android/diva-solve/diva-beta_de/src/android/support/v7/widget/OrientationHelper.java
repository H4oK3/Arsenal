// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

public abstract class OrientationHelper
{
    public static final int HORIZONTAL = 0;
    private static final int INVALID_SIZE = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    private int mLastTotalSpace;
    protected final RecyclerView.LayoutManager mLayoutManager;
    
    private OrientationHelper(final RecyclerView.LayoutManager mLayoutManager) {
        this.mLastTotalSpace = Integer.MIN_VALUE;
        this.mLayoutManager = mLayoutManager;
    }
    
    public static OrientationHelper createHorizontalHelper(final RecyclerView.LayoutManager layoutManager) {
        return new OrientationHelper(layoutManager) {
            @Override
            public int getDecoratedEnd(final View view) {
                return this.mLayoutManager.getDecoratedRight(view) + ((RecyclerView.LayoutParams)view.getLayoutParams()).rightMargin;
            }
            
            @Override
            public int getDecoratedMeasurement(final View view) {
                final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            
            @Override
            public int getDecoratedMeasurementInOther(final View view) {
                final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            
            @Override
            public int getDecoratedStart(final View view) {
                return this.mLayoutManager.getDecoratedLeft(view) - ((RecyclerView.LayoutParams)view.getLayoutParams()).leftMargin;
            }
            
            @Override
            public int getEnd() {
                return this.mLayoutManager.getWidth();
            }
            
            @Override
            public int getEndAfterPadding() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
            }
            
            @Override
            public int getEndPadding() {
                return this.mLayoutManager.getPaddingRight();
            }
            
            @Override
            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingLeft();
            }
            
            @Override
            public int getTotalSpace() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft() - this.mLayoutManager.getPaddingRight();
            }
            
            @Override
            public void offsetChild(final View view, final int n) {
                view.offsetLeftAndRight(n);
            }
            
            @Override
            public void offsetChildren(final int n) {
                this.mLayoutManager.offsetChildrenHorizontal(n);
            }
        };
    }
    
    public static OrientationHelper createOrientationHelper(final RecyclerView.LayoutManager layoutManager, final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("invalid orientation");
            }
            case 0: {
                return createHorizontalHelper(layoutManager);
            }
            case 1: {
                return createVerticalHelper(layoutManager);
            }
        }
    }
    
    public static OrientationHelper createVerticalHelper(final RecyclerView.LayoutManager layoutManager) {
        return new OrientationHelper(layoutManager) {
            @Override
            public int getDecoratedEnd(final View view) {
                return this.mLayoutManager.getDecoratedBottom(view) + ((RecyclerView.LayoutParams)view.getLayoutParams()).bottomMargin;
            }
            
            @Override
            public int getDecoratedMeasurement(final View view) {
                final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            
            @Override
            public int getDecoratedMeasurementInOther(final View view) {
                final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            
            @Override
            public int getDecoratedStart(final View view) {
                return this.mLayoutManager.getDecoratedTop(view) - ((RecyclerView.LayoutParams)view.getLayoutParams()).topMargin;
            }
            
            @Override
            public int getEnd() {
                return this.mLayoutManager.getHeight();
            }
            
            @Override
            public int getEndAfterPadding() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
            }
            
            @Override
            public int getEndPadding() {
                return this.mLayoutManager.getPaddingBottom();
            }
            
            @Override
            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingTop();
            }
            
            @Override
            public int getTotalSpace() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop() - this.mLayoutManager.getPaddingBottom();
            }
            
            @Override
            public void offsetChild(final View view, final int n) {
                view.offsetTopAndBottom(n);
            }
            
            @Override
            public void offsetChildren(final int n) {
                this.mLayoutManager.offsetChildrenVertical(n);
            }
        };
    }
    
    public abstract int getDecoratedEnd(final View p0);
    
    public abstract int getDecoratedMeasurement(final View p0);
    
    public abstract int getDecoratedMeasurementInOther(final View p0);
    
    public abstract int getDecoratedStart(final View p0);
    
    public abstract int getEnd();
    
    public abstract int getEndAfterPadding();
    
    public abstract int getEndPadding();
    
    public abstract int getStartAfterPadding();
    
    public abstract int getTotalSpace();
    
    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.mLastTotalSpace) {
            return 0;
        }
        return this.getTotalSpace() - this.mLastTotalSpace;
    }
    
    public abstract void offsetChild(final View p0, final int p1);
    
    public abstract void offsetChildren(final int p0);
    
    public void onLayoutComplete() {
        this.mLastTotalSpace = this.getTotalSpace();
    }
}
