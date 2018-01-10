// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.content.res.Resources$Theme;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;

public final class a
{
    static final b a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            a = (b)new g();
            return;
        }
        if (sdk_INT >= 21) {
            a = (b)new f();
            return;
        }
        if (sdk_INT >= 19) {
            a = (b)new e();
            return;
        }
        if (sdk_INT >= 17) {
            a = (b)new d();
            return;
        }
        if (sdk_INT >= 11) {
            a = (b)new c();
            return;
        }
        a = (b)new a();
    }
    
    public static void a(final Drawable drawable) {
        android.support.v4.c.a.a.a.a(drawable);
    }
    
    public static void a(final Drawable drawable, final float n, final float n2) {
        android.support.v4.c.a.a.a.a(drawable, n, n2);
    }
    
    public static void a(final Drawable drawable, final int n) {
        android.support.v4.c.a.a.a.a(drawable, n);
    }
    
    public static void a(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        android.support.v4.c.a.a.a.a(drawable, n, n2, n3, n4);
    }
    
    public static void a(final Drawable drawable, final ColorStateList list) {
        android.support.v4.c.a.a.a.a(drawable, list);
    }
    
    public static void a(final Drawable drawable, final Resources$Theme resources$Theme) {
        android.support.v4.c.a.a.a.a(drawable, resources$Theme);
    }
    
    public static void a(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        android.support.v4.c.a.a.a.a(drawable, resources, xmlPullParser, set, resources$Theme);
    }
    
    public static void a(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
        android.support.v4.c.a.a.a.a(drawable, porterDuff$Mode);
    }
    
    public static void a(final Drawable drawable, final boolean b) {
        android.support.v4.c.a.a.a.a(drawable, b);
    }
    
    public static boolean b(final Drawable drawable) {
        return android.support.v4.c.a.a.a.b(drawable);
    }
    
    public static boolean b(final Drawable drawable, final int n) {
        return android.support.v4.c.a.a.a.b(drawable, n);
    }
    
    public static int c(final Drawable drawable) {
        return android.support.v4.c.a.a.a.d(drawable);
    }
    
    public static boolean d(final Drawable drawable) {
        return android.support.v4.c.a.a.a.e(drawable);
    }
    
    public static ColorFilter e(final Drawable drawable) {
        return android.support.v4.c.a.a.a.f(drawable);
    }
    
    public static Drawable f(final Drawable drawable) {
        return android.support.v4.c.a.a.a.c(drawable);
    }
    
    static class a implements b
    {
        @Override
        public void a(final Drawable drawable) {
        }
        
        @Override
        public void a(final Drawable drawable, final float n, final float n2) {
        }
        
        @Override
        public void a(final Drawable drawable, final int n) {
            android.support.v4.c.a.c.a(drawable, n);
        }
        
        @Override
        public void a(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        }
        
        @Override
        public void a(final Drawable drawable, final ColorStateList list) {
            android.support.v4.c.a.c.a(drawable, list);
        }
        
        @Override
        public void a(final Drawable drawable, final Resources$Theme resources$Theme) {
        }
        
        @Override
        public void a(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
            android.support.v4.c.a.c.a(drawable, resources, xmlPullParser, set, resources$Theme);
        }
        
        @Override
        public void a(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            android.support.v4.c.a.c.a(drawable, porterDuff$Mode);
        }
        
        @Override
        public void a(final Drawable drawable, final boolean b) {
        }
        
        @Override
        public boolean b(final Drawable drawable) {
            return false;
        }
        
        @Override
        public boolean b(final Drawable drawable, final int n) {
            return false;
        }
        
        @Override
        public Drawable c(final Drawable drawable) {
            return android.support.v4.c.a.c.a(drawable);
        }
        
        @Override
        public int d(final Drawable drawable) {
            return 0;
        }
        
        @Override
        public boolean e(final Drawable drawable) {
            return false;
        }
        
        @Override
        public ColorFilter f(final Drawable drawable) {
            return null;
        }
    }
    
    interface b
    {
        void a(final Drawable p0);
        
        void a(final Drawable p0, final float p1, final float p2);
        
        void a(final Drawable p0, final int p1);
        
        void a(final Drawable p0, final int p1, final int p2, final int p3, final int p4);
        
        void a(final Drawable p0, final ColorStateList p1);
        
        void a(final Drawable p0, final Resources$Theme p1);
        
        void a(final Drawable p0, final Resources p1, final XmlPullParser p2, final AttributeSet p3, final Resources$Theme p4);
        
        void a(final Drawable p0, final PorterDuff$Mode p1);
        
        void a(final Drawable p0, final boolean p1);
        
        boolean b(final Drawable p0);
        
        boolean b(final Drawable p0, final int p1);
        
        Drawable c(final Drawable p0);
        
        int d(final Drawable p0);
        
        boolean e(final Drawable p0);
        
        ColorFilter f(final Drawable p0);
    }
    
    static class c extends a
    {
        @Override
        public void a(final Drawable drawable) {
            android.support.v4.c.a.d.a(drawable);
        }
        
        @Override
        public Drawable c(final Drawable drawable) {
            return android.support.v4.c.a.d.b(drawable);
        }
    }
    
    static class d extends c
    {
        @Override
        public boolean b(final Drawable drawable, final int n) {
            return android.support.v4.c.a.e.a(drawable, n);
        }
    }
    
    static class e extends d
    {
        @Override
        public void a(final Drawable drawable, final boolean b) {
            android.support.v4.c.a.f.a(drawable, b);
        }
        
        @Override
        public boolean b(final Drawable drawable) {
            return android.support.v4.c.a.f.a(drawable);
        }
        
        @Override
        public Drawable c(final Drawable drawable) {
            return android.support.v4.c.a.f.b(drawable);
        }
        
        @Override
        public int d(final Drawable drawable) {
            return android.support.v4.c.a.f.c(drawable);
        }
    }
    
    static class f extends e
    {
        @Override
        public void a(final Drawable drawable, final float n, final float n2) {
            android.support.v4.c.a.g.a(drawable, n, n2);
        }
        
        @Override
        public void a(final Drawable drawable, final int n) {
            android.support.v4.c.a.g.a(drawable, n);
        }
        
        @Override
        public void a(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
            android.support.v4.c.a.g.a(drawable, n, n2, n3, n4);
        }
        
        @Override
        public void a(final Drawable drawable, final ColorStateList list) {
            android.support.v4.c.a.g.a(drawable, list);
        }
        
        @Override
        public void a(final Drawable drawable, final Resources$Theme resources$Theme) {
            android.support.v4.c.a.g.a(drawable, resources$Theme);
        }
        
        @Override
        public void a(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
            android.support.v4.c.a.g.a(drawable, resources, xmlPullParser, set, resources$Theme);
        }
        
        @Override
        public void a(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            android.support.v4.c.a.g.a(drawable, porterDuff$Mode);
        }
        
        @Override
        public Drawable c(final Drawable drawable) {
            return android.support.v4.c.a.g.a(drawable);
        }
        
        @Override
        public boolean e(final Drawable drawable) {
            return android.support.v4.c.a.g.b(drawable);
        }
        
        @Override
        public ColorFilter f(final Drawable drawable) {
            return android.support.v4.c.a.g.c(drawable);
        }
    }
    
    static class g extends f
    {
        @Override
        public boolean b(final Drawable drawable, final int n) {
            return android.support.v4.c.a.b.a(drawable, n);
        }
        
        @Override
        public Drawable c(final Drawable drawable) {
            return drawable;
        }
    }
}
