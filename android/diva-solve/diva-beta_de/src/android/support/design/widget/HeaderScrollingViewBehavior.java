// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View$MeasureSpec;
import android.support.v4.view.ViewCompat;
import java.util.List;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View>
{
    public HeaderScrollingViewBehavior() {
    }
    
    public HeaderScrollingViewBehavior(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    abstract View findFirstDependency(final List<View> p0);
    
    int getScrollRange(final View view) {
        return view.getMeasuredHeight();
    }
    
    @Override
    public boolean onMeasureChild(final CoordinatorLayout coordinatorLayout, final View view, final int n, final int n2, int n3, final int n4) {
        final int height = view.getLayoutParams().height;
        if (height == -1 || height == -2) {
            final List<View> dependencies = coordinatorLayout.getDependencies(view);
            if (dependencies.isEmpty()) {
                return false;
            }
            final View firstDependency = this.findFirstDependency(dependencies);
            if (firstDependency != null && ViewCompat.isLaidOut(firstDependency)) {
                if (ViewCompat.getFitsSystemWindows(firstDependency)) {
                    ViewCompat.setFitsSystemWindows(view, true);
                }
                if ((n3 = View$MeasureSpec.getSize(n3)) == 0) {
                    n3 = coordinatorLayout.getHeight();
                }
                final int measuredHeight = firstDependency.getMeasuredHeight();
                final int scrollRange = this.getScrollRange(firstDependency);
                int n5;
                if (height == -1) {
                    n5 = 1073741824;
                }
                else {
                    n5 = Integer.MIN_VALUE;
                }
                coordinatorLayout.onMeasureChild(view, n, n2, View$MeasureSpec.makeMeasureSpec(n3 - measuredHeight + scrollRange, n5), n4);
                return true;
            }
        }
        return false;
    }
}
