// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

class ScrollbarHelper
{
    static int computeScrollExtent(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1;
        }
        return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view));
    }
    
    static int computeScrollOffset(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b, final boolean b2) {
        int n2;
        final int n = n2 = 0;
        if (layoutManager.getChildCount() != 0) {
            n2 = n;
            if (state.getItemCount() != 0) {
                n2 = n;
                if (view != null) {
                    if (view2 == null) {
                        n2 = n;
                    }
                    else {
                        final int min = Math.min(layoutManager.getPosition(view), layoutManager.getPosition(view2));
                        final int max = Math.max(layoutManager.getPosition(view), layoutManager.getPosition(view2));
                        int n3;
                        if (b2) {
                            n3 = Math.max(0, state.getItemCount() - max - 1);
                        }
                        else {
                            n3 = Math.max(0, min);
                        }
                        n2 = n3;
                        if (b) {
                            return Math.round(n3 * (Math.abs(orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1)) + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(view)));
                        }
                    }
                }
            }
        }
        return n2;
    }
    
    static int computeScrollRange(final RecyclerView.State state, final OrientationHelper orientationHelper, final View view, final View view2, final RecyclerView.LayoutManager layoutManager, final boolean b) {
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!b) {
            return state.getItemCount();
        }
        return (orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(layoutManager.getPosition(view) - layoutManager.getPosition(view2)) + 1) * state.getItemCount();
    }
}
