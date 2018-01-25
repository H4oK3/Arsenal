// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.annotation.TargetApi;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import android.view.View;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat
{
    static final ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
    private WeakReference<View> mView;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new ViewPropertyAnimatorCompatApi21Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new ViewPropertyAnimatorCompatApi19Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 18) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new ViewPropertyAnimatorCompatApi18Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new ViewPropertyAnimatorCompatApi16Impl();
            return;
        }
        IMPL = (ViewPropertyAnimatorCompatImpl)new ViewPropertyAnimatorCompatBaseImpl();
    }
    
    ViewPropertyAnimatorCompat(final View view) {
        this.mStartAction = null;
        this.mEndAction = null;
        this.mOldLayerType = -1;
        this.mView = new WeakReference<View>(view);
    }
    
    public ViewPropertyAnimatorCompat alpha(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().alpha(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat alphaBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().alphaBy(n);
        }
        return this;
    }
    
    public void cancel() {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }
    
    public long getDuration() {
        final View view = this.mView.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }
    
    public Interpolator getInterpolator() {
        final View view = this.mView.get();
        if (view != null) {
            return ViewPropertyAnimatorCompat.IMPL.getInterpolator(this, view);
        }
        return null;
    }
    
    public long getStartDelay() {
        final View view = this.mView.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0L;
    }
    
    public ViewPropertyAnimatorCompat rotation(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotation(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotationBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotationX(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotationXBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotationY(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().rotationYBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().scaleX(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().scaleXBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().scaleY(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().scaleYBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setDuration(final long duration) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().setDuration(duration);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setInterpolator(final Interpolator interpolator) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().setInterpolator((TimeInterpolator)interpolator);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setListener(final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setListener(this, view, viewPropertyAnimatorListener);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setStartDelay(final long startDelay) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().setStartDelay(startDelay);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setUpdateListener(this, view, viewPropertyAnimatorUpdateListener);
        }
        return this;
    }
    
    public void start() {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().start();
        }
    }
    
    public ViewPropertyAnimatorCompat translationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().translationX(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().translationXBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().translationY(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().translationYBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZ(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZ(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withEndAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withEndAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withLayer() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withLayer(this, view);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withStartAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withStartAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat x(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().x(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat xBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().xBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat y(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().y(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat yBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            view.animate().yBy(n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat z(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.z(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat zBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.zBy(this, view, n);
        }
        return this;
    }
    
    @TargetApi(16)
    static class ViewPropertyAnimatorCompatApi16Impl extends ViewPropertyAnimatorCompatBaseImpl
    {
        @Override
        public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            ViewPropertyAnimatorCompatJB.setListener(view, viewPropertyAnimatorListener);
        }
        
        @Override
        public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
            view.animate().withEndAction(runnable);
        }
        
        @Override
        public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            view.animate().withLayer();
        }
        
        @Override
        public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
            view.animate().withStartAction(runnable);
        }
    }
    
    @TargetApi(18)
    static class ViewPropertyAnimatorCompatApi18Impl extends ViewPropertyAnimatorCompatApi16Impl
    {
        @Override
        public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return (Interpolator)view.animate().getInterpolator();
        }
    }
    
    @TargetApi(19)
    static class ViewPropertyAnimatorCompatApi19Impl extends ViewPropertyAnimatorCompatApi18Impl
    {
        @Override
        public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, viewPropertyAnimatorUpdateListener);
        }
    }
    
    @TargetApi(21)
    static class ViewPropertyAnimatorCompatApi21Impl extends ViewPropertyAnimatorCompatApi19Impl
    {
        @Override
        public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            view.animate().translationZ(n);
        }
        
        @Override
        public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            view.animate().translationZBy(n);
        }
        
        @Override
        public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            view.animate().z(n);
        }
        
        @Override
        public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            view.animate().zBy(n);
        }
    }
    
    static class ViewPropertyAnimatorCompatBaseImpl implements ViewPropertyAnimatorCompatImpl
    {
        @Override
        public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return null;
        }
        
        @Override
        public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }
        
        @Override
        public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        }
        
        @Override
        public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mEndAction) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mEndAction = mEndAction;
        }
        
        @Override
        public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            viewPropertyAnimatorCompat.mOldLayerType = view.getLayerType();
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }
        
        @Override
        public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mStartAction) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mStartAction = mStartAction;
        }
        
        @Override
        public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        static class MyVpaListener implements ViewPropertyAnimatorListener
        {
            boolean mAnimEndCalled;
            ViewPropertyAnimatorCompat mVpa;
            
            MyVpaListener(final ViewPropertyAnimatorCompat mVpa) {
                this.mVpa = mVpa;
            }
            
            @Override
            public void onAnimationCancel(final View view) {
                final Object tag = view.getTag(2113929216);
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                if (tag instanceof ViewPropertyAnimatorListener) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }
            }
            
            @Override
            public void onAnimationEnd(final View view) {
                if (this.mVpa.mOldLayerType >= 0) {
                    view.setLayerType(this.mVpa.mOldLayerType, (Paint)null);
                    this.mVpa.mOldLayerType = -1;
                }
                if (Build$VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
                    if (this.mVpa.mEndAction != null) {
                        final Runnable mEndAction = this.mVpa.mEndAction;
                        this.mVpa.mEndAction = null;
                        mEndAction.run();
                    }
                    final Object tag = view.getTag(2113929216);
                    ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                    if (tag instanceof ViewPropertyAnimatorListener) {
                        viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                    }
                    if (viewPropertyAnimatorListener != null) {
                        viewPropertyAnimatorListener.onAnimationEnd(view);
                    }
                    this.mAnimEndCalled = true;
                }
            }
            
            @Override
            public void onAnimationStart(final View view) {
                this.mAnimEndCalled = false;
                if (this.mVpa.mOldLayerType >= 0) {
                    view.setLayerType(2, (Paint)null);
                }
                if (this.mVpa.mStartAction != null) {
                    final Runnable mStartAction = this.mVpa.mStartAction;
                    this.mVpa.mStartAction = null;
                    mStartAction.run();
                }
                final Object tag = view.getTag(2113929216);
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                if (tag instanceof ViewPropertyAnimatorListener) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            }
        }
    }
    
    interface ViewPropertyAnimatorCompatImpl
    {
        Interpolator getInterpolator(final ViewPropertyAnimatorCompat p0, final View p1);
        
        void setListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorListener p2);
        
        void setUpdateListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorUpdateListener p2);
        
        void translationZ(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationZBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void withEndAction(final ViewPropertyAnimatorCompat p0, final View p1, final Runnable p2);
        
        void withLayer(final ViewPropertyAnimatorCompat p0, final View p1);
        
        void withStartAction(final ViewPropertyAnimatorCompat p0, final View p1, final Runnable p2);
        
        void z(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void zBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
    }
}
