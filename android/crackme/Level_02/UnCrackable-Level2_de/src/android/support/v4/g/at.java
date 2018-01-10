// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(14)
class at
{
    public static long a(final View view) {
        return view.animate().getDuration();
    }
    
    public static void a(final View view, final float n) {
        view.animate().alpha(n);
    }
    
    public static void a(final View view, final long duration) {
        view.animate().setDuration(duration);
    }
    
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
    
    public static void a(final View view, final Interpolator interpolator) {
        view.animate().setInterpolator((TimeInterpolator)interpolator);
    }
    
    public static void b(final View view) {
        view.animate().cancel();
    }
    
    public static void b(final View view, final float n) {
        view.animate().translationY(n);
    }
    
    public static void b(final View view, final long startDelay) {
        view.animate().setStartDelay(startDelay);
    }
    
    public static void c(final View view) {
        view.animate().start();
    }
}
