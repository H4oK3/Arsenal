// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.View;
import android.view.ViewParent;
import android.graphics.RectF;
import android.graphics.Matrix;

class ViewGroupUtilsHoneycomb
{
    private static final Matrix IDENTITY;
    private static final ThreadLocal<Matrix> sMatrix;
    private static final ThreadLocal<RectF> sRectF;
    
    static {
        sMatrix = new ThreadLocal<Matrix>();
        sRectF = new ThreadLocal<RectF>();
        IDENTITY = new Matrix();
    }
    
    static void offsetDescendantMatrix(final ViewParent viewParent, final View view, final Matrix matrix) {
        final ViewParent parent = view.getParent();
        if (parent instanceof View && parent != viewParent) {
            final View view2 = (View)parent;
            offsetDescendantMatrix(viewParent, view2, matrix);
            matrix.preTranslate((float)(-view2.getScrollX()), (float)(-view2.getScrollY()));
        }
        matrix.preTranslate((float)view.getLeft(), (float)view.getTop());
        if (!view.getMatrix().isIdentity()) {
            matrix.preConcat(view.getMatrix());
        }
    }
    
    public static void offsetDescendantRect(final ViewGroup viewGroup, final View view, final Rect rect) {
        Matrix matrix = ViewGroupUtilsHoneycomb.sMatrix.get();
        if (matrix == null) {
            matrix = new Matrix();
            ViewGroupUtilsHoneycomb.sMatrix.set(matrix);
        }
        else {
            matrix.set(ViewGroupUtilsHoneycomb.IDENTITY);
        }
        offsetDescendantMatrix((ViewParent)viewGroup, view, matrix);
        RectF rectF;
        if ((rectF = ViewGroupUtilsHoneycomb.sRectF.get()) == null) {
            rectF = new RectF();
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int)(rectF.left + 0.5f), (int)(rectF.top + 0.5f), (int)(rectF.right + 0.5f), (int)(rectF.bottom + 0.5f));
    }
}
