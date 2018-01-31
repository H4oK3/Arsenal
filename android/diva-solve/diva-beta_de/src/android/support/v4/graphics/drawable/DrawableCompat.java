// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;

public class DrawableCompat
{
    static final DrawableImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            IMPL = (DrawableImpl)new MDrawableImpl();
            return;
        }
        if (sdk_INT >= 22) {
            IMPL = (DrawableImpl)new LollipopMr1DrawableImpl();
            return;
        }
        if (sdk_INT >= 21) {
            IMPL = (DrawableImpl)new LollipopDrawableImpl();
            return;
        }
        if (sdk_INT >= 19) {
            IMPL = (DrawableImpl)new KitKatDrawableImpl();
            return;
        }
        if (sdk_INT >= 17) {
            IMPL = (DrawableImpl)new JellybeanMr1DrawableImpl();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = (DrawableImpl)new HoneycombDrawableImpl();
            return;
        }
        IMPL = (DrawableImpl)new BaseDrawableImpl();
    }
    
    public static int getLayoutDirection(final Drawable drawable) {
        return DrawableCompat.IMPL.getLayoutDirection(drawable);
    }
    
    public static boolean isAutoMirrored(final Drawable drawable) {
        return DrawableCompat.IMPL.isAutoMirrored(drawable);
    }
    
    public static void jumpToCurrentState(final Drawable drawable) {
        DrawableCompat.IMPL.jumpToCurrentState(drawable);
    }
    
    public static void setAutoMirrored(final Drawable drawable, final boolean b) {
        DrawableCompat.IMPL.setAutoMirrored(drawable, b);
    }
    
    public static void setHotspot(final Drawable drawable, final float n, final float n2) {
        DrawableCompat.IMPL.setHotspot(drawable, n, n2);
    }
    
    public static void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        DrawableCompat.IMPL.setHotspotBounds(drawable, n, n2, n3, n4);
    }
    
    public static void setLayoutDirection(final Drawable drawable, final int n) {
        DrawableCompat.IMPL.setLayoutDirection(drawable, n);
    }
    
    public static void setTint(final Drawable drawable, final int n) {
        DrawableCompat.IMPL.setTint(drawable, n);
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList list) {
        DrawableCompat.IMPL.setTintList(drawable, list);
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompat.IMPL.setTintMode(drawable, porterDuff$Mode);
    }
    
    public static <T extends Drawable> T unwrap(final Drawable drawable) {
        Drawable wrappedDrawable = drawable;
        if (drawable instanceof DrawableWrapper) {
            wrappedDrawable = ((DrawableWrapper)drawable).getWrappedDrawable();
        }
        return (T)wrappedDrawable;
    }
    
    public static Drawable wrap(final Drawable drawable) {
        return DrawableCompat.IMPL.wrap(drawable);
    }
    
    static class BaseDrawableImpl implements DrawableImpl
    {
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            return 0;
        }
        
        @Override
        public boolean isAutoMirrored(final Drawable drawable) {
            return false;
        }
        
        @Override
        public void jumpToCurrentState(final Drawable drawable) {
        }
        
        @Override
        public void setAutoMirrored(final Drawable drawable, final boolean b) {
        }
        
        @Override
        public void setHotspot(final Drawable drawable, final float n, final float n2) {
        }
        
        @Override
        public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        }
        
        @Override
        public void setLayoutDirection(final Drawable drawable, final int n) {
        }
        
        @Override
        public void setTint(final Drawable drawable, final int n) {
            DrawableCompatBase.setTint(drawable, n);
        }
        
        @Override
        public void setTintList(final Drawable drawable, final ColorStateList list) {
            DrawableCompatBase.setTintList(drawable, list);
        }
        
        @Override
        public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            DrawableCompatBase.setTintMode(drawable, porterDuff$Mode);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatBase.wrapForTinting(drawable);
        }
    }
    
    interface DrawableImpl
    {
        int getLayoutDirection(final Drawable p0);
        
        boolean isAutoMirrored(final Drawable p0);
        
        void jumpToCurrentState(final Drawable p0);
        
        void setAutoMirrored(final Drawable p0, final boolean p1);
        
        void setHotspot(final Drawable p0, final float p1, final float p2);
        
        void setHotspotBounds(final Drawable p0, final int p1, final int p2, final int p3, final int p4);
        
        void setLayoutDirection(final Drawable p0, final int p1);
        
        void setTint(final Drawable p0, final int p1);
        
        void setTintList(final Drawable p0, final ColorStateList p1);
        
        void setTintMode(final Drawable p0, final PorterDuff$Mode p1);
        
        Drawable wrap(final Drawable p0);
    }
    
    static class HoneycombDrawableImpl extends BaseDrawableImpl
    {
        @Override
        public void jumpToCurrentState(final Drawable drawable) {
            DrawableCompatHoneycomb.jumpToCurrentState(drawable);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatHoneycomb.wrapForTinting(drawable);
        }
    }
    
    static class JellybeanMr1DrawableImpl extends HoneycombDrawableImpl
    {
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            final int layoutDirection = DrawableCompatJellybeanMr1.getLayoutDirection(drawable);
            if (layoutDirection >= 0) {
                return layoutDirection;
            }
            return 0;
        }
        
        @Override
        public void setLayoutDirection(final Drawable drawable, final int n) {
            DrawableCompatJellybeanMr1.setLayoutDirection(drawable, n);
        }
    }
    
    static class KitKatDrawableImpl extends JellybeanMr1DrawableImpl
    {
        @Override
        public boolean isAutoMirrored(final Drawable drawable) {
            return DrawableCompatKitKat.isAutoMirrored(drawable);
        }
        
        @Override
        public void setAutoMirrored(final Drawable drawable, final boolean b) {
            DrawableCompatKitKat.setAutoMirrored(drawable, b);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatKitKat.wrapForTinting(drawable);
        }
    }
    
    static class LollipopDrawableImpl extends KitKatDrawableImpl
    {
        @Override
        public void setHotspot(final Drawable drawable, final float n, final float n2) {
            DrawableCompatLollipop.setHotspot(drawable, n, n2);
        }
        
        @Override
        public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
            DrawableCompatLollipop.setHotspotBounds(drawable, n, n2, n3, n4);
        }
        
        @Override
        public void setTint(final Drawable drawable, final int n) {
            DrawableCompatLollipop.setTint(drawable, n);
        }
        
        @Override
        public void setTintList(final Drawable drawable, final ColorStateList list) {
            DrawableCompatLollipop.setTintList(drawable, list);
        }
        
        @Override
        public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            DrawableCompatLollipop.setTintMode(drawable, porterDuff$Mode);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatLollipop.wrapForTinting(drawable);
        }
    }
    
    static class LollipopMr1DrawableImpl extends LollipopDrawableImpl
    {
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatApi22.wrapForTinting(drawable);
        }
    }
    
    static class MDrawableImpl extends LollipopMr1DrawableImpl
    {
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            return DrawableCompatApi23.getLayoutDirection(drawable);
        }
        
        @Override
        public void setLayoutDirection(final Drawable drawable, final int n) {
            DrawableCompatApi23.setLayoutDirection(drawable, n);
        }
    }
}
