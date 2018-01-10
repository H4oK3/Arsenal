// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public class k extends a
{
    private final ListView f;
    
    public k(final ListView f) {
        super((View)f);
        this.f = f;
    }
    
    @Override
    public void a(final int n, final int n2) {
        android.support.v4.widget.l.a(this.f, n2);
    }
    
    @Override
    public boolean e(final int n) {
        return false;
    }
    
    @Override
    public boolean f(final int n) {
        final ListView f = this.f;
        final int count = f.getCount();
        if (count != 0) {
            final int childCount = f.getChildCount();
            final int firstVisiblePosition = f.getFirstVisiblePosition();
            if (n > 0) {
                if (firstVisiblePosition + childCount >= count && f.getChildAt(childCount - 1).getBottom() <= f.getHeight()) {
                    return false;
                }
            }
            else {
                if (n >= 0) {
                    return false;
                }
                if (firstVisiblePosition <= 0 && f.getChildAt(0).getTop() >= 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
