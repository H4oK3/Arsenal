// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.PopupMenu;
import android.view.View$OnTouchListener;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class PopupMenuCompatKitKat
{
    public static View$OnTouchListener getDragToOpenListener(final Object o) {
        return ((PopupMenu)o).getDragToOpenListener();
    }
}