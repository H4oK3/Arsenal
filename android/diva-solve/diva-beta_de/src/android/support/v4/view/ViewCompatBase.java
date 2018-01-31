// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.view.View;
import java.lang.reflect.Field;

class ViewCompatBase
{
    private static final String TAG = "ViewCompatBase";
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;
    private static Field sMinWidthField;
    private static boolean sMinWidthFieldFetched;
    
    static ColorStateList getBackgroundTintList(final View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintList();
        }
        return null;
    }
    
    static PorterDuff$Mode getBackgroundTintMode(final View view) {
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView)view).getSupportBackgroundTintMode();
        }
        return null;
    }
    
    static int getMinimumHeight(final View view) {
        Label_0027: {
            if (ViewCompatBase.sMinHeightFieldFetched) {
                break Label_0027;
            }
            while (true) {
                try {
                    (ViewCompatBase.sMinHeightField = View.class.getDeclaredField("mMinHeight")).setAccessible(true);
                    ViewCompatBase.sMinHeightFieldFetched = true;
                    if (ViewCompatBase.sMinHeightField != null) {
                        try {
                            return (int)ViewCompatBase.sMinHeightField.get(view);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    static int getMinimumWidth(final View view) {
        Label_0027: {
            if (ViewCompatBase.sMinWidthFieldFetched) {
                break Label_0027;
            }
            while (true) {
                try {
                    (ViewCompatBase.sMinWidthField = View.class.getDeclaredField("mMinWidth")).setAccessible(true);
                    ViewCompatBase.sMinWidthFieldFetched = true;
                    if (ViewCompatBase.sMinWidthField != null) {
                        try {
                            return (int)ViewCompatBase.sMinWidthField.get(view);
                        }
                        catch (Exception ex) {}
                    }
                    return 0;
                }
                catch (NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
        }
    }
    
    static boolean isAttachedToWindow(final View view) {
        return view.getWindowToken() != null;
    }
    
    static boolean isLaidOut(final View view) {
        return view.getWidth() > 0 && view.getHeight() > 0;
    }
    
    static void setBackgroundTintList(final View view, final ColorStateList supportBackgroundTintList) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintList(supportBackgroundTintList);
        }
    }
    
    static void setBackgroundTintMode(final View view, final PorterDuff$Mode supportBackgroundTintMode) {
        if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView)view).setSupportBackgroundTintMode(supportBackgroundTintMode);
        }
    }
}
