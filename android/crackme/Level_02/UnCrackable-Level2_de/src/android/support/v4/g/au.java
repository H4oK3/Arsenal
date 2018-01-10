// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(16)
class au
{
    public static void a(final View view, final aw aw) {
        if (aw != null) {
            view.animate().setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                public void onAnimationCancel(final Animator animator) {
                    aw.c(view);
                }
                
                public void onAnimationEnd(final Animator animator) {
                    aw.b(view);
                }
                
                public void onAnimationStart(final Animator animator) {
                    aw.a(view);
                }
            });
            return;
        }
        view.animate().setListener((Animator$AnimatorListener)null);
    }
}
