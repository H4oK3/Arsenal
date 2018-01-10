// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import android.support.v7.widget.v;
import android.support.v7.widget.u;
import android.support.v7.widget.q;
import android.support.v7.widget.f;
import android.support.v7.widget.j;
import android.support.v7.widget.t;
import android.support.v7.widget.i;
import android.support.v7.widget.n;
import android.support.v7.widget.x;
import android.support.v7.widget.h;
import android.support.v7.widget.p;
import android.support.v7.widget.aa;
import android.support.v7.widget.as;
import android.view.View$OnClickListener;
import android.support.v4.g.ae;
import android.os.Build$VERSION;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v7.view.d;
import android.util.Log;
import android.support.v4.f.a;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

class m
{
    private static final Class<?>[] a;
    private static final int[] b;
    private static final String[] c;
    private static final Map<String, Constructor<? extends View>> d;
    private final Object[] e;
    
    static {
        a = new Class[] { Context.class, AttributeSet.class };
        b = new int[] { 16843375 };
        c = new String[] { "android.widget.", "android.view.", "android.webkit." };
        d = new android.support.v4.f.a<String, Constructor<? extends View>>();
    }
    
    m() {
        this.e = new Object[2];
    }
    
    private static Context a(final Context context, final AttributeSet set, final boolean b, final boolean b2) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, android.support.v7.a.a.j.View, 0, 0);
        int resourceId;
        if (b) {
            resourceId = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.View_android_theme, 0);
        }
        else {
            resourceId = 0;
        }
        int n = resourceId;
        if (b2 && (n = resourceId) == 0) {
            final int resourceId2 = obtainStyledAttributes.getResourceId(android.support.v7.a.a.j.View_theme, 0);
            if ((n = resourceId2) != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
                n = resourceId2;
            }
        }
        obtainStyledAttributes.recycle();
        Object o = context;
        if (n != 0) {
            if (context instanceof d) {
                o = context;
                if (((d)context).a() == n) {
                    return (Context)o;
                }
            }
            o = new d(context, n);
        }
        return (Context)o;
    }
    
    private View a(final Context context, final String s, final AttributeSet set) {
        String attributeValue = s;
        if (s.equals("view")) {
            attributeValue = set.getAttributeValue((String)null, "class");
        }
        try {
            this.e[0] = context;
            this.e[1] = set;
            if (-1 == attributeValue.indexOf(46)) {
                for (int i = 0; i < m.c.length; ++i) {
                    final View a = this.a(context, attributeValue, m.c[i]);
                    if (a != null) {
                        return a;
                    }
                }
                return null;
            }
            return this.a(context, attributeValue, (String)null);
        }
        catch (Exception ex) {
            return null;
        }
        finally {
            this.e[0] = null;
            this.e[1] = null;
        }
    }
    
    private View a(final Context context, final String s, final String s2) {
        Label_0083: {
            Constructor<? extends View> constructor;
            if ((constructor = m.d.get(s)) != null) {
                break Label_0083;
            }
            try {
                final ClassLoader classLoader = context.getClassLoader();
                String string;
                if (s2 != null) {
                    string = s2 + s;
                }
                else {
                    string = s;
                }
                constructor = classLoader.loadClass(string).asSubclass(View.class).getConstructor(m.a);
                m.d.put(s, constructor);
                constructor.setAccessible(true);
                return (View)constructor.newInstance(this.e);
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
    
    private void a(final View view, final AttributeSet set) {
        final Context context = view.getContext();
        if (!(context instanceof ContextWrapper) || (Build$VERSION.SDK_INT >= 15 && !ae.s(view))) {
            return;
        }
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, m.b);
        final String string = obtainStyledAttributes.getString(0);
        if (string != null) {
            view.setOnClickListener((View$OnClickListener)new a(view, string));
        }
        obtainStyledAttributes.recycle();
    }
    
    public final View a(View a, final String s, final Context context, final AttributeSet set, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        Context context2;
        if (b && a != null) {
            context2 = ((View)a).getContext();
        }
        else {
            context2 = context;
        }
        Context a2 = null;
        Label_0040: {
            if (!b2) {
                a2 = context2;
                if (!b3) {
                    break Label_0040;
                }
            }
            a2 = a(context2, set, b2, b3);
        }
        Context a3 = a2;
        if (b4) {
            a3 = as.a(a2);
        }
        a = null;
        switch (s) {
            case "TextView": {
                a = new aa(a3, set);
                break;
            }
            case "ImageView": {
                a = new p(a3, set);
                break;
            }
            case "Button": {
                a = new h(a3, set);
                break;
            }
            case "EditText": {
                a = new android.support.v7.widget.m(a3, set);
                break;
            }
            case "Spinner": {
                a = new x(a3, set);
                break;
            }
            case "ImageButton": {
                a = new n(a3, set);
                break;
            }
            case "CheckBox": {
                a = new i(a3, set);
                break;
            }
            case "RadioButton": {
                a = new t(a3, set);
                break;
            }
            case "CheckedTextView": {
                a = new j(a3, set);
                break;
            }
            case "AutoCompleteTextView": {
                a = new f(a3, set);
                break;
            }
            case "MultiAutoCompleteTextView": {
                a = new q(a3, set);
                break;
            }
            case "RatingBar": {
                a = new u(a3, set);
                break;
            }
            case "SeekBar": {
                a = new v(a3, set);
                break;
            }
        }
        if (a == null && context != a3) {
            a = this.a(a3, s, set);
        }
        if (a != null) {
            this.a((View)a, set);
        }
        return (View)a;
    }
    
    private static class a implements View$OnClickListener
    {
        private final View a;
        private final String b;
        private Method c;
        private Context d;
        
        public a(final View a, final String b) {
            this.a = a;
            this.b = b;
        }
        
        private void a(Context baseContext, final String s) {
            while (baseContext != null) {
                try {
                    if (!baseContext.isRestricted()) {
                        final Method method = baseContext.getClass().getMethod(this.b, View.class);
                        if (method != null) {
                            this.c = method;
                            this.d = baseContext;
                            return;
                        }
                    }
                }
                catch (NoSuchMethodException ex) {}
                if (baseContext instanceof ContextWrapper) {
                    baseContext = ((ContextWrapper)baseContext).getBaseContext();
                }
                else {
                    baseContext = null;
                }
            }
            final int id = this.a.getId();
            String string;
            if (id == -1) {
                string = "";
            }
            else {
                string = " with id '" + this.a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.b + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.a.getClass() + string);
        }
        
        public void onClick(final View view) {
            if (this.c == null) {
                this.a(this.a.getContext(), this.b);
            }
            try {
                this.c.invoke(this.d, view);
            }
            catch (IllegalAccessException ex) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", ex);
            }
            catch (InvocationTargetException ex2) {
                throw new IllegalStateException("Could not execute method for android:onClick", ex2);
            }
        }
    }
}
