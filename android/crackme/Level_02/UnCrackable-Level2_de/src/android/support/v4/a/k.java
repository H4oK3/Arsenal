// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.os.Looper;
import android.support.v4.f.d;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.content.res.Resources;
import android.view.MenuItem;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.view.MenuInflater;
import android.view.Menu;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.LayoutInflater;
import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.f.j;
import android.view.View$OnCreateContextMenuListener;
import android.content.ComponentCallbacks;

public class k implements ComponentCallbacks, View$OnCreateContextMenuListener
{
    private static final j<String, Class<?>> R;
    static final Object a;
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    ViewGroup F;
    View G;
    View H;
    boolean I;
    boolean J;
    y K;
    boolean L;
    boolean M;
    a N;
    boolean O;
    boolean P;
    float Q;
    int b;
    Bundle c;
    SparseArray<Parcelable> d;
    int e;
    String f;
    Bundle g;
    k h;
    int i;
    int j;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    int p;
    q q;
    o r;
    q s;
    r t;
    k u;
    int v;
    int w;
    String x;
    boolean y;
    boolean z;
    
    static {
        R = new j<String, Class<?>>();
        a = new Object();
    }
    
    public k() {
        this.b = 0;
        this.e = -1;
        this.i = -1;
        this.D = true;
        this.J = true;
    }
    
    private void R() {
        Object i = null;
        if (this.N != null) {
            this.N.h = false;
            i = this.N.i;
            this.N.i = null;
        }
        if (i != null) {
            ((c)i).a();
        }
    }
    
    private a S() {
        if (this.N == null) {
            this.N = new a();
        }
        return this.N;
    }
    
    public static k a(final Context context, final String s) {
        return a(context, s, null);
    }
    
    public static k a(final Context context, final String s, final Bundle g) {
        try {
            Class<?> loadClass;
            if ((loadClass = k.R.get(s)) == null) {
                loadClass = context.getClassLoader().loadClass(s);
                k.R.put(s, loadClass);
            }
            final k k = (k)loadClass.newInstance();
            if (g != null) {
                g.setClassLoader(k.getClass().getClassLoader());
                k.g = g;
            }
            return k;
        }
        catch (ClassNotFoundException ex) {
            throw new b("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex);
        }
        catch (InstantiationException ex2) {
            throw new b("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new b("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an" + " empty constructor that is public", ex3);
        }
    }
    
    static boolean b(final Context context, final String s) {
        try {
            Class<?> loadClass;
            if ((loadClass = k.R.get(s)) == null) {
                loadClass = context.getClassLoader().loadClass(s);
                k.R.put(s, loadClass);
            }
            return k.class.isAssignableFrom(loadClass);
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    void A() {
        if (this.s != null) {
            this.s.h();
            this.s.c();
        }
        this.b = 5;
        this.E = false;
        this.h();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onResume()");
        }
        if (this.s != null) {
            this.s.l();
            this.s.c();
        }
    }
    
    void B() {
        this.onLowMemory();
        if (this.s != null) {
            this.s.r();
        }
    }
    
    void C() {
        if (this.s != null) {
            this.s.m();
        }
        this.b = 4;
        this.E = false;
        this.i();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onPause()");
        }
    }
    
    void D() {
        if (this.s != null) {
            this.s.n();
        }
        this.b = 3;
        this.E = false;
        this.j();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onStop()");
        }
    }
    
    void E() {
        if (this.s != null) {
            this.s.o();
        }
        this.b = 2;
        if (this.L) {
            this.L = false;
            if (!this.M) {
                this.M = true;
                this.K = this.r.a(this.f, this.L, false);
            }
            if (this.K != null) {
                if (!this.r.j()) {
                    this.K.c();
                    return;
                }
                this.K.d();
            }
        }
    }
    
    void F() {
        if (this.s != null) {
            this.s.p();
        }
        this.b = 1;
        this.E = false;
        this.k();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onDestroyView()");
        }
        if (this.K != null) {
            this.K.f();
        }
    }
    
    void G() {
        if (this.s != null) {
            this.s.q();
        }
        this.b = 0;
        this.E = false;
        this.l();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onDestroy()");
        }
        this.s = null;
    }
    
    void H() {
        this.E = false;
        this.n();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onDetach()");
        }
        if (this.s != null) {
            if (!this.B) {
                throw new IllegalStateException("Child FragmentManager of " + this + " was not " + " destroyed and this fragment is not retaining instance");
            }
            this.s.q();
            this.s = null;
        }
    }
    
    int I() {
        if (this.N == null) {
            return 0;
        }
        return this.N.c;
    }
    
    int J() {
        if (this.N == null) {
            return 0;
        }
        return this.N.d;
    }
    
    int K() {
        if (this.N == null) {
            return 0;
        }
        return this.N.e;
    }
    
    ad L() {
        if (this.N == null) {
            return null;
        }
        return this.N.f;
    }
    
    ad M() {
        if (this.N == null) {
            return null;
        }
        return this.N.g;
    }
    
    View N() {
        if (this.N == null) {
            return null;
        }
        return this.N.a;
    }
    
    int O() {
        if (this.N == null) {
            return 0;
        }
        return this.N.b;
    }
    
    boolean P() {
        return this.N != null && this.N.h;
    }
    
    boolean Q() {
        return this.N != null && this.N.j;
    }
    
    k a(final String s) {
        if (s.equals(this.f)) {
            return this;
        }
        if (this.s != null) {
            return this.s.b(s);
        }
        return null;
    }
    
    public View a(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return null;
    }
    
    public Animation a(final int n, final boolean b, final int n2) {
        return null;
    }
    
    void a(final int c) {
        if (this.N == null && c == 0) {
            return;
        }
        this.S().c = c;
    }
    
    void a(final int d, final int e) {
        if (this.N == null && d == 0 && e == 0) {
            return;
        }
        this.S();
        this.N.d = d;
        this.N.e = e;
    }
    
    public void a(final int n, final int n2, final Intent intent) {
    }
    
    final void a(final int e, final k k) {
        this.e = e;
        if (k != null) {
            this.f = k.f + ":" + this.e;
            return;
        }
        this.f = "android:fragment:" + this.e;
    }
    
    public void a(final int n, final String[] array, final int[] array2) {
    }
    
    @Deprecated
    public void a(final Activity activity) {
        this.E = true;
    }
    
    @Deprecated
    public void a(final Activity activity, final AttributeSet set, final Bundle bundle) {
        this.E = true;
    }
    
    public void a(final Context context) {
        this.E = true;
        Activity f;
        if (this.r == null) {
            f = null;
        }
        else {
            f = this.r.f();
        }
        if (f != null) {
            this.E = false;
            this.a(f);
        }
    }
    
    public void a(final Context context, final AttributeSet set, final Bundle bundle) {
        this.E = true;
        Activity f;
        if (this.r == null) {
            f = null;
        }
        else {
            f = this.r.f();
        }
        if (f != null) {
            this.E = false;
            this.a(f, set, bundle);
        }
    }
    
    void a(final Configuration configuration) {
        this.onConfigurationChanged(configuration);
        if (this.s != null) {
            this.s.a(configuration);
        }
    }
    
    final void a(final Bundle bundle) {
        if (this.d != null) {
            this.H.restoreHierarchyState((SparseArray)this.d);
            this.d = null;
        }
        this.E = false;
        this.f(bundle);
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }
    
    void a(final c i) {
        this.S();
        if (i != this.N.i) {
            if (i != null && this.N.i != null) {
                throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
            }
            if (this.N.h) {
                this.N.i = i;
            }
            if (i != null) {
                i.b();
            }
        }
    }
    
    public void a(final k k) {
    }
    
    public void a(final Menu menu) {
    }
    
    public void a(final Menu menu, final MenuInflater menuInflater) {
    }
    
    void a(final View a) {
        this.S().a = a;
    }
    
    public void a(final View view, final Bundle bundle) {
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.v));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.w));
        printWriter.print(" mTag=");
        printWriter.println(this.x);
        printWriter.print(s);
        printWriter.print("mState=");
        printWriter.print(this.b);
        printWriter.print(" mIndex=");
        printWriter.print(this.e);
        printWriter.print(" mWho=");
        printWriter.print(this.f);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.p);
        printWriter.print(s);
        printWriter.print("mAdded=");
        printWriter.print(this.k);
        printWriter.print(" mRemoving=");
        printWriter.print(this.l);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.m);
        printWriter.print(" mInLayout=");
        printWriter.println(this.n);
        printWriter.print(s);
        printWriter.print("mHidden=");
        printWriter.print(this.y);
        printWriter.print(" mDetached=");
        printWriter.print(this.z);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.D);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.C);
        printWriter.print(s);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.A);
        printWriter.print(" mRetaining=");
        printWriter.print(this.B);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.J);
        if (this.q != null) {
            printWriter.print(s);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.q);
        }
        if (this.r != null) {
            printWriter.print(s);
            printWriter.print("mHost=");
            printWriter.println(this.r);
        }
        if (this.u != null) {
            printWriter.print(s);
            printWriter.print("mParentFragment=");
            printWriter.println(this.u);
        }
        if (this.g != null) {
            printWriter.print(s);
            printWriter.print("mArguments=");
            printWriter.println(this.g);
        }
        if (this.c != null) {
            printWriter.print(s);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.c);
        }
        if (this.d != null) {
            printWriter.print(s);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.d);
        }
        if (this.h != null) {
            printWriter.print(s);
            printWriter.print("mTarget=");
            printWriter.print(this.h);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.j);
        }
        if (this.I() != 0) {
            printWriter.print(s);
            printWriter.print("mNextAnim=");
            printWriter.println(this.I());
        }
        if (this.F != null) {
            printWriter.print(s);
            printWriter.print("mContainer=");
            printWriter.println(this.F);
        }
        if (this.G != null) {
            printWriter.print(s);
            printWriter.print("mView=");
            printWriter.println(this.G);
        }
        if (this.H != null) {
            printWriter.print(s);
            printWriter.print("mInnerView=");
            printWriter.println(this.G);
        }
        if (this.N() != null) {
            printWriter.print(s);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.N());
            printWriter.print(s);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.O());
        }
        if (this.K != null) {
            printWriter.print(s);
            printWriter.println("Loader Manager:");
            this.K.a(s + "  ", fileDescriptor, printWriter, array);
        }
        if (this.s != null) {
            printWriter.print(s);
            printWriter.println("Child " + this.s + ":");
            this.s.a(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    public void a(final boolean b) {
    }
    
    final boolean a() {
        return this.p > 0;
    }
    
    public boolean a(final MenuItem menuItem) {
        return false;
    }
    
    public final l b() {
        if (this.r == null) {
            return null;
        }
        return (l)this.r.f();
    }
    
    public LayoutInflater b(final Bundle bundle) {
        final LayoutInflater b = this.r.b();
        this.e();
        android.support.v4.g.j.a(b, this.s.s());
        return b;
    }
    
    View b(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        if (this.s != null) {
            this.s.h();
        }
        return this.a(layoutInflater, viewGroup, bundle);
    }
    
    void b(final int b) {
        this.S().b = b;
    }
    
    public void b(final Menu menu) {
    }
    
    public void b(final boolean b) {
    }
    
    boolean b(final Menu menu, final MenuInflater menuInflater) {
        boolean b = false;
        final boolean b2 = false;
        if (!this.y) {
            boolean b3 = b2;
            if (this.C) {
                b3 = b2;
                if (this.D) {
                    b3 = true;
                    this.a(menu, menuInflater);
                }
            }
            b = b3;
            if (this.s != null) {
                b = (b3 | this.s.a(menu, menuInflater));
            }
        }
        return b;
    }
    
    public boolean b(final MenuItem menuItem) {
        return false;
    }
    
    public final Resources c() {
        if (this.r == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        return this.r.g().getResources();
    }
    
    public void c(final Bundle bundle) {
        this.E = true;
        this.d(bundle);
        if (this.s != null && !this.s.a(1)) {
            this.s.i();
        }
    }
    
    public void c(final boolean b) {
    }
    
    boolean c(final Menu menu) {
        boolean b = false;
        final boolean b2 = false;
        if (!this.y) {
            boolean b3 = b2;
            if (this.C) {
                b3 = b2;
                if (this.D) {
                    b3 = true;
                    this.a(menu);
                }
            }
            b = b3;
            if (this.s != null) {
                b = (b3 | this.s.a(menu));
            }
        }
        return b;
    }
    
    boolean c(final MenuItem menuItem) {
        return !this.y && ((this.C && this.D && this.a(menuItem)) || (this.s != null && this.s.a(menuItem)));
    }
    
    public final p d() {
        return this.q;
    }
    
    void d(final Bundle bundle) {
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.s == null) {
                    this.y();
                }
                this.s.a(parcelable, this.t);
                this.t = null;
                this.s.i();
            }
        }
    }
    
    void d(final Menu menu) {
        if (!this.y) {
            if (this.C && this.D) {
                this.b(menu);
            }
            if (this.s != null) {
                this.s.b(menu);
            }
        }
    }
    
    void d(final boolean b) {
        this.b(b);
        if (this.s != null) {
            this.s.a(b);
        }
    }
    
    boolean d(final MenuItem menuItem) {
        return !this.y && (this.b(menuItem) || (this.s != null && this.s.b(menuItem)));
    }
    
    public final p e() {
        if (this.s == null) {
            this.y();
            if (this.b >= 5) {
                this.s.l();
            }
            else if (this.b >= 4) {
                this.s.k();
            }
            else if (this.b >= 2) {
                this.s.j();
            }
            else if (this.b >= 1) {
                this.s.i();
            }
        }
        return this.s;
    }
    
    public void e(final Bundle bundle) {
        this.E = true;
    }
    
    void e(final boolean b) {
        this.c(b);
        if (this.s != null) {
            this.s.b(b);
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        return super.equals(o);
    }
    
    public View f() {
        return this.G;
    }
    
    public void f(final Bundle bundle) {
        this.E = true;
    }
    
    void f(final boolean j) {
        this.S().j = j;
    }
    
    public void g() {
        this.E = true;
        if (!this.L) {
            this.L = true;
            if (!this.M) {
                this.M = true;
                this.K = this.r.a(this.f, this.L, false);
            }
            if (this.K != null) {
                this.K.b();
            }
        }
    }
    
    public void g(final Bundle bundle) {
    }
    
    public void h() {
        this.E = true;
    }
    
    void h(final Bundle bundle) {
        if (this.s != null) {
            this.s.h();
        }
        this.b = 1;
        this.E = false;
        this.c(bundle);
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onCreate()");
        }
    }
    
    @Override
    public final int hashCode() {
        return super.hashCode();
    }
    
    public void i() {
        this.E = true;
    }
    
    void i(final Bundle bundle) {
        if (this.s != null) {
            this.s.h();
        }
        this.b = 2;
        this.E = false;
        this.e(bundle);
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
        if (this.s != null) {
            this.s.j();
        }
    }
    
    public void j() {
        this.E = true;
    }
    
    void j(final Bundle bundle) {
        this.g(bundle);
        if (this.s != null) {
            final Parcelable g = this.s.g();
            if (g != null) {
                bundle.putParcelable("android:support:fragments", g);
            }
        }
    }
    
    public void k() {
        this.E = true;
    }
    
    public void l() {
        this.E = true;
        if (!this.M) {
            this.M = true;
            this.K = this.r.a(this.f, this.L, false);
        }
        if (this.K != null) {
            this.K.h();
        }
    }
    
    void m() {
        this.e = -1;
        this.f = null;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = 0;
        this.q = null;
        this.s = null;
        this.r = null;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = false;
        this.z = false;
        this.B = false;
        this.K = null;
        this.L = false;
        this.M = false;
    }
    
    public void n() {
        this.E = true;
    }
    
    public void o() {
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.E = true;
    }
    
    public void onCreateContextMenu(final ContextMenu contextMenu, final View view, final ContextMenu$ContextMenuInfo contextMenu$ContextMenuInfo) {
        this.b().onCreateContextMenu(contextMenu, view, contextMenu$ContextMenuInfo);
    }
    
    public void onLowMemory() {
        this.E = true;
    }
    
    public Object p() {
        if (this.N == null) {
            return null;
        }
        return this.N.k;
    }
    
    public Object q() {
        if (this.N == null) {
            return null;
        }
        if (this.N.l == android.support.v4.a.k.a) {
            return this.p();
        }
        return this.N.l;
    }
    
    public Object r() {
        if (this.N == null) {
            return null;
        }
        return this.N.m;
    }
    
    public Object s() {
        if (this.N == null) {
            return null;
        }
        if (this.N.n == android.support.v4.a.k.a) {
            return this.r();
        }
        return this.N.n;
    }
    
    public Object t() {
        if (this.N == null) {
            return null;
        }
        return this.N.o;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        android.support.v4.f.d.a(this, sb);
        if (this.e >= 0) {
            sb.append(" #");
            sb.append(this.e);
        }
        if (this.v != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.v));
        }
        if (this.x != null) {
            sb.append(" ");
            sb.append(this.x);
        }
        sb.append('}');
        return sb.toString();
    }
    
    public Object u() {
        if (this.N == null) {
            return null;
        }
        if (this.N.p == android.support.v4.a.k.a) {
            return this.t();
        }
        return this.N.p;
    }
    
    public boolean v() {
        return this.N == null || this.N.r == null || this.N.r;
    }
    
    public boolean w() {
        return this.N == null || this.N.q == null || this.N.q;
    }
    
    public void x() {
        if (this.q == null || this.q.n == null) {
            this.S().h = false;
            return;
        }
        if (Looper.myLooper() != this.q.n.h().getLooper()) {
            this.q.n.h().postAtFrontOfQueue((Runnable)new Runnable() {
                @Override
                public void run() {
                    android.support.v4.a.k.this.R();
                }
            });
            return;
        }
        this.R();
    }
    
    void y() {
        if (this.r == null) {
            throw new IllegalStateException("Fragment has not been attached yet.");
        }
        (this.s = new q()).a(this.r, new m() {
            @Override
            public View a(final int n) {
                if (android.support.v4.a.k.this.G == null) {
                    throw new IllegalStateException("Fragment does not have a view");
                }
                return android.support.v4.a.k.this.G.findViewById(n);
            }
            
            @Override
            public boolean a() {
                return android.support.v4.a.k.this.G != null;
            }
        }, this);
    }
    
    void z() {
        if (this.s != null) {
            this.s.h();
            this.s.c();
        }
        this.b = 4;
        this.E = false;
        this.g();
        if (!this.E) {
            throw new ae("Fragment " + this + " did not call through to super.onStart()");
        }
        if (this.s != null) {
            this.s.k();
        }
        if (this.K != null) {
            this.K.g();
        }
    }
    
    static class a
    {
        View a;
        int b;
        int c;
        int d;
        int e;
        ad f;
        ad g;
        boolean h;
        c i;
        boolean j;
        private Object k;
        private Object l;
        private Object m;
        private Object n;
        private Object o;
        private Object p;
        private Boolean q;
        private Boolean r;
        
        a() {
            this.k = null;
            this.l = k.a;
            this.m = null;
            this.n = k.a;
            this.o = null;
            this.p = k.a;
            this.f = null;
            this.g = null;
        }
    }
    
    public static class b extends RuntimeException
    {
        public b(final String s, final Exception ex) {
            super(s, ex);
        }
    }
    
    interface c
    {
        void a();
        
        void b();
    }
}
