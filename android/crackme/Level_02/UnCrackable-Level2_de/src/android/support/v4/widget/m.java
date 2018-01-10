// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;
import android.annotation.TargetApi;

@TargetApi(9)
class m
{
    static void a(final ListView listView, final int n) {
        final int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition != -1) {
            final View child = listView.getChildAt(0);
            if (child != null) {
                listView.setSelectionFromTop(firstVisiblePosition, child.getTop() - n);
            }
        }
    }
}
