// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.graphics.Paint;
import android.animation.ValueAnimator;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(11)
class ag
{
    public static int a(final int n, final int n2) {
        return View.combineMeasuredStates(n, n2);
    }
    
    public static int a(final int n, final int n2, final int n3) {
        return View.resolveSizeAndState(n, n2, n3);
    }
    
    public static int a(final View view) {
        return view.getLayerType();
    }
    
    static long a() {
        return ValueAnimator.getFrameDelay();
    }
    
    public static void a(final View view, final float translationY) {
        view.setTranslationY(translationY);
    }
    
    public static void a(final View view, final int n, final Paint paint) {
        view.setLayerType(n, paint);
    }
    
    public static void a(final View view, final boolean saveFromParentEnabled) {
        view.setSaveFromParentEnabled(saveFromParentEnabled);
    }
    
    public static int b(final View view) {
        return view.getMeasuredWidthAndState();
    }
    
    public static void b(final View view, final float alpha) {
        view.setAlpha(alpha);
    }
    
    public static void b(final View view, final boolean activated) {
        view.setActivated(activated);
    }
    
    public static int c(final View view) {
        return view.getMeasuredState();
    }
    
    public static float d(final View view) {
        return view.getTranslationY();
    }
    
    public static void e(final View view) {
        view.jumpDrawablesToCurrentState();
    }
}
