// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.view.View;

class CoordinatorLayoutInsetsHelperLollipop implements CoordinatorLayoutInsetsHelper
{
    @Override
    public void setupForWindowInsets(final View view, final OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (ViewCompat.getFitsSystemWindows(view)) {
            ViewCompat.setOnApplyWindowInsetsListener(view, onApplyWindowInsetsListener);
            view.setSystemUiVisibility(1280);
        }
    }
}
