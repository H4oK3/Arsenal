// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(19)
class av
{
    public static void a(final View view, final ay ay) {
        Object updateListener = null;
        if (ay != null) {
            updateListener = new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                    ay.a(view);
                }
            };
        }
        view.animate().setUpdateListener((ValueAnimator$AnimatorUpdateListener)updateListener);
    }
}
