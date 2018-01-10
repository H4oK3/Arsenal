// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;
import android.annotation.TargetApi;

@TargetApi(19)
class r
{
    public static void a(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        popupWindow.showAsDropDown(view, n, n2, n3);
    }
}
