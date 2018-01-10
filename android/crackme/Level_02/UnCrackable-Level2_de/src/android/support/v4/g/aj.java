// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(16)
class aj
{
    public static void a(final View view) {
        view.postInvalidateOnAnimation();
    }
    
    public static void a(final View view, final Drawable background) {
        view.setBackground(background);
    }
    
    public static void a(final View view, final Runnable runnable) {
        view.postOnAnimation(runnable);
    }
    
    public static void a(final View view, final Runnable runnable, final long n) {
        view.postOnAnimationDelayed(runnable, n);
    }
    
    public static int b(final View view) {
        return view.getMinimumHeight();
    }
    
    public static void c(final View view) {
        view.requestFitSystemWindows();
    }
    
    public static boolean d(final View view) {
        return view.hasOverlappingRendering();
    }
}
