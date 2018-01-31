// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.os.Build$VERSION;

class ViewUtils
{
    static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR;
    private static final ViewUtilsImpl IMPL;
    
    static {
        DEFAULT_ANIMATOR_CREATOR = new ValueAnimatorCompat.Creator() {
            @Override
            public ValueAnimatorCompat createAnimator() {
                Impl impl;
                if (Build$VERSION.SDK_INT >= 12) {
                    impl = new ValueAnimatorCompatImplHoneycombMr1();
                }
                else {
                    impl = new ValueAnimatorCompatImplEclairMr1();
                }
                return new ValueAnimatorCompat(impl);
            }
        };
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (ViewUtilsImpl)new ViewUtilsImplLollipop();
            return;
        }
        IMPL = (ViewUtilsImpl)new ViewUtilsImplBase();
    }
    
    static ValueAnimatorCompat createAnimator() {
        return ViewUtils.DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }
    
    static void setBoundsViewOutlineProvider(final View boundsViewOutlineProvider) {
        ViewUtils.IMPL.setBoundsViewOutlineProvider(boundsViewOutlineProvider);
    }
    
    private interface ViewUtilsImpl
    {
        void setBoundsViewOutlineProvider(final View p0);
    }
    
    private static class ViewUtilsImplBase implements ViewUtilsImpl
    {
        @Override
        public void setBoundsViewOutlineProvider(final View view) {
        }
    }
    
    private static class ViewUtilsImplLollipop implements ViewUtilsImpl
    {
        @Override
        public void setBoundsViewOutlineProvider(final View boundsViewOutlineProvider) {
            ViewUtilsLollipop.setBoundsViewOutlineProvider(boundsViewOutlineProvider);
        }
    }
}
