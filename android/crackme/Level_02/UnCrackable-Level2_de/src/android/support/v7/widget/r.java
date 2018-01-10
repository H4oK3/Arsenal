// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.widget.o;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.content.Context;
import android.os.Build$VERSION;
import android.widget.PopupWindow;

class r extends PopupWindow
{
    private static final boolean a;
    private boolean b;
    
    static {
        a = (Build$VERSION.SDK_INT < 21);
    }
    
    public r(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a(context, set, n, 0);
    }
    
    public r(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.a(context, set, n, n2);
    }
    
    private void a(final Context context, final AttributeSet set, int sdk_INT, final int n) {
        final av a = av.a(context, set, android.support.v7.a.a.j.PopupWindow, sdk_INT, n);
        if (a.g(android.support.v7.a.a.j.PopupWindow_overlapAnchor)) {
            this.a(a.a(android.support.v7.a.a.j.PopupWindow_overlapAnchor, false));
        }
        this.setBackgroundDrawable(a.a(android.support.v7.a.a.j.PopupWindow_android_popupBackground));
        sdk_INT = Build$VERSION.SDK_INT;
        if (n != 0 && sdk_INT < 11 && a.g(android.support.v7.a.a.j.PopupWindow_android_popupAnimationStyle)) {
            this.setAnimationStyle(a.g(android.support.v7.a.a.j.PopupWindow_android_popupAnimationStyle, -1));
        }
        a.a();
        if (Build$VERSION.SDK_INT < 14) {
            a(this);
        }
    }
    
    private static void a(final PopupWindow popupWindow) {
        try {
            final Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            final Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new ViewTreeObserver$OnScrollChangedListener() {
                final /* synthetic */ ViewTreeObserver$OnScrollChangedListener c = (ViewTreeObserver$OnScrollChangedListener)declaredField2.get(popupWindow);
                
                public void onScrollChanged() {
                    try {
                        final WeakReference weakReference = (WeakReference)declaredField.get(popupWindow);
                        if (weakReference != null) {
                            if (weakReference.get() == null) {
                                return;
                            }
                            this.c.onScrollChanged();
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
    
    public void a(final boolean b) {
        if (r.a) {
            this.b = b;
            return;
        }
        o.a(this, b);
    }
    
    public void showAsDropDown(final View view, final int n, final int n2) {
        int n3 = n2;
        if (r.a) {
            n3 = n2;
            if (this.b) {
                n3 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n3);
    }
    
    @TargetApi(19)
    public void showAsDropDown(final View view, final int n, final int n2, final int n3) {
        int n4 = n2;
        if (r.a) {
            n4 = n2;
            if (this.b) {
                n4 = n2 - view.getHeight();
            }
        }
        super.showAsDropDown(view, n, n4, n3);
    }
    
    public void update(final View view, final int n, int n2, final int n3, final int n4) {
        if (r.a && this.b) {
            n2 -= view.getHeight();
        }
        super.update(view, n, n2, n3, n4);
    }
}
