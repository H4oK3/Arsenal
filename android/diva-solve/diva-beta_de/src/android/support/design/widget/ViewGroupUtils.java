// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build$VERSION;

class ViewGroupUtils
{
    private static final ViewGroupUtilsImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (ViewGroupUtilsImpl)new ViewGroupUtilsImplHoneycomb();
            return;
        }
        IMPL = (ViewGroupUtilsImpl)new ViewGroupUtilsImplBase();
    }
    
    static void getDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        offsetDescendantRect(viewGroup, view, rect);
    }
    
    static void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        ViewGroupUtils.IMPL.offsetDescendantRect(viewGroup, view, rect);
    }
    
    private interface ViewGroupUtilsImpl
    {
        void offsetDescendantRect(final ViewGroup p0, final View p1, final Rect p2);
    }
    
    private static class ViewGroupUtilsImplBase implements ViewGroupUtilsImpl
    {
        @Override
        public void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
            viewGroup.offsetDescendantRectToMyCoords(view, rect);
        }
    }
    
    private static class ViewGroupUtilsImplHoneycomb implements ViewGroupUtilsImpl
    {
        @Override
        public void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
            ViewGroupUtilsHoneycomb.offsetDescendantRect(viewGroup, view, rect);
        }
    }
}
