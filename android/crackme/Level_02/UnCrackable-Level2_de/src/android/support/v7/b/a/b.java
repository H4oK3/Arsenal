// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.b.a;

import android.content.res.Configuration;
import android.content.res.XmlResourceParser;
import android.content.res.Resources;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import android.support.v7.widget.l;
import android.graphics.drawable.Drawable;
import android.support.v4.b.a;
import android.os.Build$VERSION;
import android.content.res.ColorStateList;
import android.util.SparseArray;
import android.content.Context;
import java.util.WeakHashMap;
import android.util.TypedValue;

public final class b
{
    private static final ThreadLocal<TypedValue> a;
    private static final WeakHashMap<Context, SparseArray<a>> b;
    private static final Object c;
    
    static {
        a = new ThreadLocal<TypedValue>();
        b = new WeakHashMap<Context, SparseArray<a>>(0);
        c = new Object();
    }
    
    public static ColorStateList a(final Context context, final int n) {
        ColorStateList list;
        if (Build$VERSION.SDK_INT >= 23) {
            list = context.getColorStateList(n);
        }
        else if ((list = d(context, n)) == null) {
            final ColorStateList c = c(context, n);
            if (c != null) {
                a(context, n, c);
                return c;
            }
            return android.support.v4.b.a.b(context, n);
        }
        return list;
    }
    
    private static TypedValue a() {
        TypedValue typedValue;
        if ((typedValue = android.support.v7.b.a.b.a.get()) == null) {
            typedValue = new TypedValue();
            android.support.v7.b.a.b.a.set(typedValue);
        }
        return typedValue;
    }
    
    private static void a(final Context context, final int n, final ColorStateList list) {
        synchronized (android.support.v7.b.a.b.c) {
            SparseArray sparseArray;
            if ((sparseArray = android.support.v7.b.a.b.b.get(context)) == null) {
                sparseArray = new SparseArray();
                android.support.v7.b.a.b.b.put(context, (SparseArray<a>)sparseArray);
            }
            sparseArray.append(n, (Object)new a(list, context.getResources().getConfiguration()));
        }
    }
    
    public static Drawable b(final Context context, final int n) {
        return l.a().a(context, n);
    }
    
    private static ColorStateList c(final Context context, final int n) {
        if (e(context, n)) {
            return null;
        }
        final Resources resources = context.getResources();
        final XmlResourceParser xml = resources.getXml(n);
        try {
            return android.support.v7.b.a.a.a(resources, (XmlPullParser)xml, context.getTheme());
        }
        catch (Exception ex) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", (Throwable)ex);
            return null;
        }
    }
    
    private static ColorStateList d(final Context context, final int n) {
        synchronized (android.support.v7.b.a.b.c) {
            final SparseArray<a> sparseArray = android.support.v7.b.a.b.b.get(context);
            if (sparseArray != null && sparseArray.size() > 0) {
                final a a = (a)sparseArray.get(n);
                if (a != null) {
                    if (a.b.equals(context.getResources().getConfiguration())) {
                        return a.a;
                    }
                    sparseArray.remove(n);
                }
            }
            return null;
        }
    }
    
    private static boolean e(final Context context, final int n) {
        final Resources resources = context.getResources();
        final TypedValue a = a();
        resources.getValue(n, a, true);
        return a.type >= 28 && a.type <= 31;
    }
    
    private static class a
    {
        final ColorStateList a;
        final Configuration b;
        
        a(final ColorStateList a, final Configuration b) {
            this.a = a;
            this.b = b;
        }
    }
}
