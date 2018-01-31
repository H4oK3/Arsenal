// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.widget.PopupWindowCompat;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.PopupWindow;

public class AppCompatPopupWindow extends PopupWindow
{
    private static final boolean COMPAT_OVERLAP_ANCHOR;
    private static final String TAG = "AppCompatPopupWindow";
    private boolean mOverlapAnchor;
    
    static {
        COMPAT_OVERLAP_ANCHOR = (Build$VERSION.SDK_INT < 21);
    }
    
    public AppCompatPopupWindow(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.PopupWindow, n, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.PopupWindow_overlapAnchor)) {
            this.setSupportOverlapAnchor(obtainStyledAttributes.getBoolean(R.styleable.PopupWindow_overlapAnchor, false));
        }
        this.setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.PopupWindow_android_popupBackground));
        obtainStyledAttributes.recycle();
        if (Build$VERSION.SDK_INT < 14) {
            wrapOnScrollChangedListener(this);
        }
    }
    
    private static void wrapOnScrollChangedListener(final PopupWindow popupWindow) {
        try {
            final Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            final Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new ViewTreeObserver$OnScrollChangedListener() {
                final /* synthetic */ ViewTreeObserver$OnScrollChangedListener val$originalListener = (ViewTreeObserver$OnScrollChangedListener)declaredField2.get(popupWindow);
                
                public void onScrollChanged() {
                    try {
                        final WeakReference weakReference = (WeakReference)declaredField.get(popupWindow);
                        if (weakReference != null) {
                            if (weakReference.get() == null) {
                                return;
                            }
                            this.val$originalListener.onScrollChanged();
                        }
                    }
                    catch (IllegalAccessException ex) {}
                }
            });
        }
        catch (Exception ex) {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", (Throwable)ex);
        }
    }
    
    public boolean getSupportOverlapAnchor() {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            return this.mOverlapAnchor;
        }
        return PopupWindowCompat.getOverlapAnchor(this);
    }
    
    public void setSupportOverlapAnchor(final boolean mOverlapAnchor) {
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = mOverlapAnchor;
            return;
        }
        PopupWindowCompat.setOverlapAnchor(this, mOverlapAnchor);
    }
    
    public void showAsDropDown(final View view, final int n, final int n2) {
        int n3 = n2;
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            n3 = n2;
            if (this.mOverlapAnchor) {
                n3 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n3);
    }
    
    @TargetApi(19)
    public void showAsDropDown(final View view, final int n, final int n2, final int n3) {
        int n4 = n2;
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            n4 = n2;
            if (this.mOverlapAnchor) {
                n4 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n4, n3);
    }
    
    public void update(final View view, final int n, final int n2, final int n3, final int n4) {
        int n5 = n2;
        if (AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            n5 = n2;
            if (this.mOverlapAnchor) {
                n5 = n2 - view.getHeight();
            }
        }
        super.update(view, n, n5, n3, n4);
    }
}
