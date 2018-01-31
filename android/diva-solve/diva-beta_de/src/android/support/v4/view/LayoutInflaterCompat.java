// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.LayoutInflater;
import android.os.Build$VERSION;

public class LayoutInflaterCompat
{
    static final LayoutInflaterCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (LayoutInflaterCompatImpl)new LayoutInflaterCompatImplV21();
            return;
        }
        if (sdk_INT >= 11) {
            IMPL = (LayoutInflaterCompatImpl)new LayoutInflaterCompatImplV11();
            return;
        }
        IMPL = (LayoutInflaterCompatImpl)new LayoutInflaterCompatImplBase();
    }
    
    public static void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        LayoutInflaterCompat.IMPL.setFactory(layoutInflater, layoutInflaterFactory);
    }
    
    interface LayoutInflaterCompatImpl
    {
        void setFactory(final LayoutInflater p0, final LayoutInflaterFactory p1);
    }
    
    static class LayoutInflaterCompatImplBase implements LayoutInflaterCompatImpl
    {
        @Override
        public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatBase.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }
    
    static class LayoutInflaterCompatImplV11 extends LayoutInflaterCompatImplBase
    {
        @Override
        public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatHC.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }
    
    static class LayoutInflaterCompatImplV21 extends LayoutInflaterCompatImplV11
    {
        @Override
        public void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
            LayoutInflaterCompatLollipop.setFactory(layoutInflater, layoutInflaterFactory);
        }
    }
}
