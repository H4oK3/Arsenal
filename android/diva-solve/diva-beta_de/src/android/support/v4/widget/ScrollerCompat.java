// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.Scroller;
import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import android.content.Context;

public class ScrollerCompat
{
    static final int CHASE_FRAME_TIME = 16;
    private static final String TAG = "ScrollerCompat";
    ScrollerCompatImpl mImpl;
    Object mScroller;
    
    private ScrollerCompat(final int n, final Context context, final Interpolator interpolator) {
        if (n >= 14) {
            this.mImpl = (ScrollerCompatImpl)new ScrollerCompatImplIcs();
        }
        else if (n >= 9) {
            this.mImpl = (ScrollerCompatImpl)new ScrollerCompatImplGingerbread();
        }
        else {
            this.mImpl = (ScrollerCompatImpl)new ScrollerCompatImplBase();
        }
        this.mScroller = this.mImpl.createScroller(context, interpolator);
    }
    
    ScrollerCompat(final Context context, final Interpolator interpolator) {
        this(Build$VERSION.SDK_INT, context, interpolator);
    }
    
    public static ScrollerCompat create(final Context context) {
        return create(context, null);
    }
    
    public static ScrollerCompat create(final Context context, final Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }
    
    public void abortAnimation() {
        this.mImpl.abortAnimation(this.mScroller);
    }
    
    public boolean computeScrollOffset() {
        return this.mImpl.computeScrollOffset(this.mScroller);
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.mImpl.fling(this.mScroller, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.mImpl.fling(this.mScroller, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public float getCurrVelocity() {
        return this.mImpl.getCurrVelocity(this.mScroller);
    }
    
    public int getCurrX() {
        return this.mImpl.getCurrX(this.mScroller);
    }
    
    public int getCurrY() {
        return this.mImpl.getCurrY(this.mScroller);
    }
    
    public int getFinalX() {
        return this.mImpl.getFinalX(this.mScroller);
    }
    
    public int getFinalY() {
        return this.mImpl.getFinalY(this.mScroller);
    }
    
    public boolean isFinished() {
        return this.mImpl.isFinished(this.mScroller);
    }
    
    public boolean isOverScrolled() {
        return this.mImpl.isOverScrolled(this.mScroller);
    }
    
    public void notifyHorizontalEdgeReached(final int n, final int n2, final int n3) {
        this.mImpl.notifyHorizontalEdgeReached(this.mScroller, n, n2, n3);
    }
    
    public void notifyVerticalEdgeReached(final int n, final int n2, final int n3) {
        this.mImpl.notifyVerticalEdgeReached(this.mScroller, n, n2, n3);
    }
    
    public boolean springBack(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.mImpl.springBack(this.mScroller, n, n2, n3, n4, n5, n6);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4) {
        this.mImpl.startScroll(this.mScroller, n, n2, n3, n4);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.mImpl.startScroll(this.mScroller, n, n2, n3, n4, n5);
    }
    
    interface ScrollerCompatImpl
    {
        void abortAnimation(final Object p0);
        
        boolean computeScrollOffset(final Object p0);
        
        Object createScroller(final Context p0, final Interpolator p1);
        
        void fling(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
        
        void fling(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
        
        float getCurrVelocity(final Object p0);
        
        int getCurrX(final Object p0);
        
        int getCurrY(final Object p0);
        
        int getFinalX(final Object p0);
        
        int getFinalY(final Object p0);
        
        boolean isFinished(final Object p0);
        
        boolean isOverScrolled(final Object p0);
        
        void notifyHorizontalEdgeReached(final Object p0, final int p1, final int p2, final int p3);
        
        void notifyVerticalEdgeReached(final Object p0, final int p1, final int p2, final int p3);
        
        boolean springBack(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
        
        void startScroll(final Object p0, final int p1, final int p2, final int p3, final int p4);
        
        void startScroll(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    }
    
    static class ScrollerCompatImplBase implements ScrollerCompatImpl
    {
        @Override
        public void abortAnimation(final Object o) {
            ((Scroller)o).abortAnimation();
        }
        
        @Override
        public boolean computeScrollOffset(final Object o) {
            return ((Scroller)o).computeScrollOffset();
        }
        
        @Override
        public Object createScroller(final Context context, final Interpolator interpolator) {
            if (interpolator != null) {
                return new Scroller(context, interpolator);
            }
            return new Scroller(context);
        }
        
        @Override
        public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
            ((Scroller)o).fling(n, n2, n3, n4, n5, n6, n7, n8);
        }
        
        @Override
        public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
            ((Scroller)o).fling(n, n2, n3, n4, n5, n6, n7, n8);
        }
        
        @Override
        public float getCurrVelocity(final Object o) {
            return 0.0f;
        }
        
        @Override
        public int getCurrX(final Object o) {
            return ((Scroller)o).getCurrX();
        }
        
        @Override
        public int getCurrY(final Object o) {
            return ((Scroller)o).getCurrY();
        }
        
        @Override
        public int getFinalX(final Object o) {
            return ((Scroller)o).getFinalX();
        }
        
        @Override
        public int getFinalY(final Object o) {
            return ((Scroller)o).getFinalY();
        }
        
        @Override
        public boolean isFinished(final Object o) {
            return ((Scroller)o).isFinished();
        }
        
        @Override
        public boolean isOverScrolled(final Object o) {
            return false;
        }
        
        @Override
        public void notifyHorizontalEdgeReached(final Object o, final int n, final int n2, final int n3) {
        }
        
        @Override
        public void notifyVerticalEdgeReached(final Object o, final int n, final int n2, final int n3) {
        }
        
        @Override
        public boolean springBack(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
            return false;
        }
        
        @Override
        public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4) {
            ((Scroller)o).startScroll(n, n2, n3, n4);
        }
        
        @Override
        public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4, final int n5) {
            ((Scroller)o).startScroll(n, n2, n3, n4, n5);
        }
    }
    
    static class ScrollerCompatImplGingerbread implements ScrollerCompatImpl
    {
        @Override
        public void abortAnimation(final Object o) {
            ScrollerCompatGingerbread.abortAnimation(o);
        }
        
        @Override
        public boolean computeScrollOffset(final Object o) {
            return ScrollerCompatGingerbread.computeScrollOffset(o);
        }
        
        @Override
        public Object createScroller(final Context context, final Interpolator interpolator) {
            return ScrollerCompatGingerbread.createScroller(context, interpolator);
        }
        
        @Override
        public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
            ScrollerCompatGingerbread.fling(o, n, n2, n3, n4, n5, n6, n7, n8);
        }
        
        @Override
        public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
            ScrollerCompatGingerbread.fling(o, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        }
        
        @Override
        public float getCurrVelocity(final Object o) {
            return 0.0f;
        }
        
        @Override
        public int getCurrX(final Object o) {
            return ScrollerCompatGingerbread.getCurrX(o);
        }
        
        @Override
        public int getCurrY(final Object o) {
            return ScrollerCompatGingerbread.getCurrY(o);
        }
        
        @Override
        public int getFinalX(final Object o) {
            return ScrollerCompatGingerbread.getFinalX(o);
        }
        
        @Override
        public int getFinalY(final Object o) {
            return ScrollerCompatGingerbread.getFinalY(o);
        }
        
        @Override
        public boolean isFinished(final Object o) {
            return ScrollerCompatGingerbread.isFinished(o);
        }
        
        @Override
        public boolean isOverScrolled(final Object o) {
            return ScrollerCompatGingerbread.isOverScrolled(o);
        }
        
        @Override
        public void notifyHorizontalEdgeReached(final Object o, final int n, final int n2, final int n3) {
            ScrollerCompatGingerbread.notifyHorizontalEdgeReached(o, n, n2, n3);
        }
        
        @Override
        public void notifyVerticalEdgeReached(final Object o, final int n, final int n2, final int n3) {
            ScrollerCompatGingerbread.notifyVerticalEdgeReached(o, n, n2, n3);
        }
        
        @Override
        public boolean springBack(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
            return ScrollerCompatGingerbread.springBack(o, n, n2, n3, n4, n5, n6);
        }
        
        @Override
        public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4) {
            ScrollerCompatGingerbread.startScroll(o, n, n2, n3, n4);
        }
        
        @Override
        public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4, final int n5) {
            ScrollerCompatGingerbread.startScroll(o, n, n2, n3, n4, n5);
        }
    }
    
    static class ScrollerCompatImplIcs extends ScrollerCompatImplGingerbread
    {
        @Override
        public float getCurrVelocity(final Object o) {
            return ScrollerCompatIcs.getCurrVelocity(o);
        }
    }
}
