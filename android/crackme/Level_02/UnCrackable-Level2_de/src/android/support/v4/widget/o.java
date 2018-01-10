// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.g.f;
import android.support.v4.g.ae;
import java.lang.reflect.Method;
import android.view.View;
import android.widget.PopupWindow;
import android.os.Build$VERSION;

public final class o
{
    static final e a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            a = (e)new b();
            return;
        }
        if (sdk_INT >= 21) {
            a = (e)new a();
            return;
        }
        if (sdk_INT >= 19) {
            a = (e)new d();
            return;
        }
        a = (e)new c();
    }
    
    public static void a(final PopupWindow popupWindow, final int n) {
        o.a.a(popupWindow, n);
    }
    
    public static void a(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
        o.a.a(popupWindow, view, n, n2, n3);
    }
    
    public static void a(final PopupWindow popupWindow, final boolean b) {
        o.a.a(popupWindow, b);
    }
    
    static class a extends d
    {
        @Override
        public void a(final PopupWindow popupWindow, final boolean b) {
            p.a(popupWindow, b);
        }
    }
    
    static class b extends a
    {
        @Override
        public void a(final PopupWindow popupWindow, final int n) {
            q.a(popupWindow, n);
        }
        
        @Override
        public void a(final PopupWindow popupWindow, final boolean b) {
            q.a(popupWindow, b);
        }
    }
    
    static class c implements e
    {
        private static Method a;
        private static boolean b;
        
        @Override
        public void a(final PopupWindow ex, final int n) {
            Label_0037: {
                if (c.b) {
                    break Label_0037;
                }
                while (true) {
                    try {
                        (c.a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE)).setAccessible(true);
                        c.b = true;
                        if (c.a == null) {
                            return;
                        }
                        try {
                            c.a.invoke(ex, n);
                        }
                        catch (Exception ex) {}
                    }
                    catch (Exception ex2) {
                        continue;
                    }
                    break;
                }
            }
        }
        
        @Override
        public void a(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
            int n4 = n;
            if ((f.a(n3, ae.c(view)) & 0x7) == 0x5) {
                n4 = n - (popupWindow.getWidth() - view.getWidth());
            }
            popupWindow.showAsDropDown(view, n4, n2);
        }
        
        @Override
        public void a(final PopupWindow popupWindow, final boolean b) {
        }
    }
    
    static class d extends c
    {
        @Override
        public void a(final PopupWindow popupWindow, final View view, final int n, final int n2, final int n3) {
            r.a(popupWindow, view, n, n2, n3);
        }
    }
    
    interface e
    {
        void a(final PopupWindow p0, final int p1);
        
        void a(final PopupWindow p0, final View p1, final int p2, final int p3, final int p4);
        
        void a(final PopupWindow p0, final boolean p1);
    }
}
