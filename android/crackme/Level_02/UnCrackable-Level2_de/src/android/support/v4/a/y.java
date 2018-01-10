// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import java.lang.reflect.Modifier;
import android.os.Bundle;
import android.support.v4.b.h;
import android.support.v4.f.d;
import android.util.Log;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.v4.f.k;

class y extends x
{
    static boolean a;
    final k<a> b;
    final k<a> c;
    final String d;
    boolean e;
    boolean f;
    o g;
    
    static {
        y.a = false;
    }
    
    y(final String d, final o g, final boolean e) {
        this.b = new k<a>();
        this.c = new k<a>();
        this.d = d;
        this.g = g;
        this.e = e;
    }
    
    void a(final o g) {
        this.g = g;
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final int n = 0;
        if (this.b.b() > 0) {
            printWriter.print(s);
            printWriter.println("Active Loaders:");
            final String string = s + "    ";
            for (int i = 0; i < this.b.b(); ++i) {
                final a a = this.b.e(i);
                printWriter.print(s);
                printWriter.print("  #");
                printWriter.print(this.b.d(i));
                printWriter.print(": ");
                printWriter.println(a.toString());
                a.a(string, fileDescriptor, printWriter, array);
            }
        }
        if (this.c.b() > 0) {
            printWriter.print(s);
            printWriter.println("Inactive Loaders:");
            final String string2 = s + "    ";
            for (int j = n; j < this.c.b(); ++j) {
                final a a2 = this.c.e(j);
                printWriter.print(s);
                printWriter.print("  #");
                printWriter.print(this.c.d(j));
                printWriter.print(": ");
                printWriter.println(a2.toString());
                a2.a(string2, fileDescriptor, printWriter, array);
            }
        }
    }
    
    @Override
    public boolean a() {
        final int b = this.b.b();
        int i = 0;
        boolean b2 = false;
        while (i < b) {
            final a a = this.b.e(i);
            b2 |= (a.h && !a.f);
            ++i;
        }
        return b2;
    }
    
    void b() {
        if (y.a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.e) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, (Throwable)ex);
        }
        else {
            this.e = true;
            for (int i = this.b.b() - 1; i >= 0; --i) {
                this.b.e(i).a();
            }
        }
    }
    
    void c() {
        if (y.a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (!this.e) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, (Throwable)ex);
            return;
        }
        for (int i = this.b.b() - 1; i >= 0; --i) {
            this.b.e(i).e();
        }
        this.e = false;
    }
    
    void d() {
        if (y.a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (!this.e) {
            final RuntimeException ex = new RuntimeException("here");
            ex.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, (Throwable)ex);
        }
        else {
            this.f = true;
            this.e = false;
            for (int i = this.b.b() - 1; i >= 0; --i) {
                this.b.e(i).b();
            }
        }
    }
    
    void e() {
        if (this.f) {
            if (y.a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f = false;
            for (int i = this.b.b() - 1; i >= 0; --i) {
                this.b.e(i).c();
            }
        }
    }
    
    void f() {
        for (int i = this.b.b() - 1; i >= 0; --i) {
            this.b.e(i).k = true;
        }
    }
    
    void g() {
        for (int i = this.b.b() - 1; i >= 0; --i) {
            this.b.e(i).d();
        }
    }
    
    void h() {
        if (!this.f) {
            if (y.a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (int i = this.b.b() - 1; i >= 0; --i) {
                this.b.e(i).f();
            }
            this.b.c();
        }
        if (y.a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (int j = this.c.b() - 1; j >= 0; --j) {
            this.c.e(j).f();
        }
        this.c.c();
        this.g = null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        android.support.v4.f.d.a(this.g, sb);
        sb.append("}}");
        return sb.toString();
    }
    
    final class a implements h.a<Object>, b<Object>
    {
        final int a;
        final Bundle b;
        x.a<Object> c;
        h<Object> d;
        boolean e;
        boolean f;
        Object g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        a n;
        final /* synthetic */ y o;
        
        void a() {
            if (this.i && this.j) {
                this.h = true;
            }
            else if (!this.h) {
                this.h = true;
                if (y.a) {
                    Log.v("LoaderManager", "  Starting: " + this);
                }
                if (this.d == null && this.c != null) {
                    this.d = this.c.a(this.a, this.b);
                }
                if (this.d != null) {
                    if (this.d.getClass().isMemberClass() && !Modifier.isStatic(this.d.getClass().getModifiers())) {
                        throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.d);
                    }
                    if (!this.m) {
                        this.d.a(this.a, (h.b<Object>)this);
                        this.d.a((h.a<Object>)this);
                        this.m = true;
                    }
                    this.d.a();
                }
            }
        }
        
        void a(final h<Object> h, final Object o) {
            if (this.c == null) {
                return;
            }
            while (true) {
                Label_0158: {
                    if (this.o.g == null) {
                        break Label_0158;
                    }
                    final String u = this.o.g.d.u;
                    this.o.g.d.u = "onLoadFinished";
                    try {
                        if (y.a) {
                            Log.v("LoaderManager", "  onLoadFinished in " + h + ": " + h.a(o));
                        }
                        this.c.a(h, o);
                        if (this.o.g != null) {
                            this.o.g.d.u = u;
                        }
                        this.f = true;
                        return;
                    }
                    finally {
                        if (this.o.g != null) {
                            this.o.g.d.u = u;
                        }
                    }
                }
                final String u = null;
                continue;
            }
        }
        
        public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
            printWriter.print(s);
            printWriter.print("mId=");
            printWriter.print(this.a);
            printWriter.print(" mArgs=");
            printWriter.println(this.b);
            printWriter.print(s);
            printWriter.print("mCallbacks=");
            printWriter.println(this.c);
            printWriter.print(s);
            printWriter.print("mLoader=");
            printWriter.println(this.d);
            if (this.d != null) {
                this.d.a(s + "  ", fileDescriptor, printWriter, array);
            }
            if (this.e || this.f) {
                printWriter.print(s);
                printWriter.print("mHaveData=");
                printWriter.print(this.e);
                printWriter.print("  mDeliveredData=");
                printWriter.println(this.f);
                printWriter.print(s);
                printWriter.print("mData=");
                printWriter.println(this.g);
            }
            printWriter.print(s);
            printWriter.print("mStarted=");
            printWriter.print(this.h);
            printWriter.print(" mReportNextStart=");
            printWriter.print(this.k);
            printWriter.print(" mDestroyed=");
            printWriter.println(this.l);
            printWriter.print(s);
            printWriter.print("mRetaining=");
            printWriter.print(this.i);
            printWriter.print(" mRetainingStarted=");
            printWriter.print(this.j);
            printWriter.print(" mListenerRegistered=");
            printWriter.println(this.m);
            if (this.n != null) {
                printWriter.print(s);
                printWriter.println("Pending Loader ");
                printWriter.print(this.n);
                printWriter.println(":");
                this.n.a(s + "  ", fileDescriptor, printWriter, array);
            }
        }
        
        void b() {
            if (y.a) {
                Log.v("LoaderManager", "  Retaining: " + this);
            }
            this.i = true;
            this.j = this.h;
            this.h = false;
            this.c = null;
        }
        
        void c() {
            if (this.i) {
                if (y.a) {
                    Log.v("LoaderManager", "  Finished Retaining: " + this);
                }
                this.i = false;
                if (this.h != this.j && !this.h) {
                    this.e();
                }
            }
            if (this.h && this.e && !this.k) {
                this.a(this.d, this.g);
            }
        }
        
        void d() {
            if (this.h && this.k) {
                this.k = false;
                if (this.e && !this.i) {
                    this.a(this.d, this.g);
                }
            }
        }
        
        void e() {
            if (y.a) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.h = false;
            if (!this.i && this.d != null && this.m) {
                this.m = false;
                this.d.a((h.b<Object>)this);
                this.d.b((h.a<Object>)this);
                this.d.c();
            }
        }
        
        void f() {
            if (y.a) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.l = true;
            final boolean f = this.f;
            this.f = false;
            while (true) {
                Label_0178: {
                    if (this.c == null || this.d == null || !this.e || !f) {
                        break Label_0178;
                    }
                    if (y.a) {
                        Log.v("LoaderManager", "  Resetting: " + this);
                    }
                    if (this.o.g == null) {
                        break Label_0178;
                    }
                    final String u = this.o.g.d.u;
                    this.o.g.d.u = "onLoaderReset";
                    try {
                        this.c.a(this.d);
                        if (this.o.g != null) {
                            this.o.g.d.u = u;
                        }
                        this.c = null;
                        this.g = null;
                        this.e = false;
                        if (this.d != null) {
                            if (this.m) {
                                this.m = false;
                                this.d.a((h.b<Object>)this);
                                this.d.b((h.a<Object>)this);
                            }
                            this.d.e();
                        }
                        if (this.n != null) {
                            this.n.f();
                        }
                        return;
                    }
                    finally {
                        if (this.o.g != null) {
                            this.o.g.d.u = u;
                        }
                    }
                }
                final String u = null;
                continue;
            }
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.a);
            sb.append(" : ");
            android.support.v4.f.d.a(this.d, sb);
            sb.append("}}");
            return sb.toString();
        }
    }
}
