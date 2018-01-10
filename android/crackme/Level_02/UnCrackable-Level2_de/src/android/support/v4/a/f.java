// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.os.Build$VERSION;
import java.util.ArrayList;

final class f extends u implements c
{
    static final boolean a;
    final q b;
    ArrayList<a> c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    boolean j;
    boolean k;
    String l;
    boolean m;
    int n;
    int o;
    CharSequence p;
    int q;
    CharSequence r;
    ArrayList<String> s;
    ArrayList<String> t;
    boolean u;
    
    static {
        a = (Build$VERSION.SDK_INT >= 21);
    }
    
    public f(final q b) {
        this.c = new ArrayList<a>();
        this.k = true;
        this.n = -1;
        this.u = false;
        this.b = b;
    }
    
    private static boolean b(final a a) {
        final k b = a.b;
        return b.k && b.G != null && !b.z && !b.y && b.P();
    }
    
    void a() {
        for (int size = this.c.size(), i = 0; i < size; ++i) {
            final a a = this.c.get(i);
            final k b = a.b;
            b.a(this.h, this.i);
            switch (a.a) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + a.a);
                }
                case 1: {
                    b.a(a.c);
                    this.b.a(b, false);
                    break;
                }
                case 3: {
                    b.a(a.d);
                    this.b.g(b);
                    break;
                }
                case 4: {
                    b.a(a.d);
                    this.b.h(b);
                    break;
                }
                case 5: {
                    b.a(a.c);
                    this.b.i(b);
                    break;
                }
                case 6: {
                    b.a(a.d);
                    this.b.j(b);
                    break;
                }
                case 7: {
                    b.a(a.c);
                    this.b.k(b);
                    break;
                }
            }
            if (!this.u && a.a != 1) {
                this.b.d(b);
            }
        }
        if (!this.u) {
            this.b.a(this.b.m, true);
        }
    }
    
    void a(final int n) {
        if (this.j) {
            if (android.support.v4.a.q.a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + n);
            }
            for (int size = this.c.size(), i = 0; i < size; ++i) {
                final a a = this.c.get(i);
                if (a.b != null) {
                    final k b = a.b;
                    b.p += n;
                    if (android.support.v4.a.q.a) {
                        Log.v("FragmentManager", "Bump nesting of " + a.b + " to " + a.b.p);
                    }
                }
            }
        }
    }
    
    void a(final a a) {
        this.c.add(a);
        a.c = this.d;
        a.d = this.e;
        a.e = this.f;
        a.f = this.g;
    }
    
    void a(final k.c c) {
        for (int i = 0; i < this.c.size(); ++i) {
            final a a = this.c.get(i);
            if (b(a)) {
                a.b.a(c);
            }
        }
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.a(s, printWriter, true);
    }
    
    public void a(final String s, final PrintWriter printWriter, final boolean b) {
        if (b) {
            printWriter.print(s);
            printWriter.print("mName=");
            printWriter.print(this.l);
            printWriter.print(" mIndex=");
            printWriter.print(this.n);
            printWriter.print(" mCommitted=");
            printWriter.println(this.m);
            if (this.h != 0) {
                printWriter.print(s);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.h));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.i));
            }
            if (this.d != 0 || this.e != 0) {
                printWriter.print(s);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.f != 0 || this.g != 0) {
                printWriter.print(s);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.o != 0 || this.p != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.o));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.p);
            }
            if (this.q != 0 || this.r != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.q));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.r);
            }
        }
        if (!this.c.isEmpty()) {
            printWriter.print(s);
            printWriter.println("Operations:");
            new StringBuilder().append(s).append("    ").toString();
            for (int size = this.c.size(), i = 0; i < size; ++i) {
                final a a = this.c.get(i);
                String string = null;
                switch (a.a) {
                    default: {
                        string = "cmd=" + a.a;
                        break;
                    }
                    case 0: {
                        string = "NULL";
                        break;
                    }
                    case 1: {
                        string = "ADD";
                        break;
                    }
                    case 2: {
                        string = "REPLACE";
                        break;
                    }
                    case 3: {
                        string = "REMOVE";
                        break;
                    }
                    case 4: {
                        string = "HIDE";
                        break;
                    }
                    case 5: {
                        string = "SHOW";
                        break;
                    }
                    case 6: {
                        string = "DETACH";
                        break;
                    }
                    case 7: {
                        string = "ATTACH";
                        break;
                    }
                }
                printWriter.print(s);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(string);
                printWriter.print(" ");
                printWriter.println(a.b);
                if (b) {
                    if (a.c != 0 || a.d != 0) {
                        printWriter.print(s);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(a.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(a.d));
                    }
                    if (a.e != 0 || a.f != 0) {
                        printWriter.print(s);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(a.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(a.f));
                    }
                }
            }
        }
    }
    
    void a(final ArrayList<k> list) {
        int i = 0;
    Label_0078_Outer:
        while (i < this.c.size()) {
            final a a = this.c.get(i);
            int n = i;
            while (true) {
                switch (a.a) {
                    default: {
                        n = i;
                        break Label_0078;
                    }
                    case 3:
                    case 6: {
                        list.remove(a.b);
                        n = i;
                        break Label_0078;
                    }
                    case 1:
                    case 7: {
                        list.add(a.b);
                        n = i;
                    }
                    case 4:
                    case 5: {
                        i = n + 1;
                        continue Label_0078_Outer;
                    }
                    case 2: {
                        final k b = a.b;
                        final int w = b.w;
                        int j = list.size() - 1;
                        int n2 = 0;
                        while (j >= 0) {
                            final k b2 = list.get(j);
                            int n3;
                            int n4;
                            if (b2.w == w) {
                                if (b2 == b) {
                                    final boolean b3 = true;
                                    n3 = i;
                                    n4 = (b3 ? 1 : 0);
                                }
                                else {
                                    final a a2 = new a();
                                    a2.a = 3;
                                    a2.b = b2;
                                    a2.c = a.c;
                                    a2.e = a.e;
                                    a2.d = a.d;
                                    a2.f = a.f;
                                    this.c.add(i, a2);
                                    list.remove(b2);
                                    final int n5 = i + 1;
                                    n4 = n2;
                                    n3 = n5;
                                }
                            }
                            else {
                                final int n6 = i;
                                n4 = n2;
                                n3 = n6;
                            }
                            final int n7 = j - 1;
                            final int n8 = n3;
                            n2 = n4;
                            i = n8;
                            j = n7;
                        }
                        if (n2 != 0) {
                            this.c.remove(i);
                            n = i - 1;
                            continue;
                        }
                        a.a = 1;
                        list.add(b);
                        n = i;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    void a(final boolean b) {
        for (int i = this.c.size() - 1; i >= 0; --i) {
            final a a = this.c.get(i);
            final k b2 = a.b;
            b2.a(android.support.v4.a.q.d(this.h), this.i);
            switch (a.a) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + a.a);
                }
                case 1: {
                    b2.a(a.f);
                    this.b.g(b2);
                    break;
                }
                case 3: {
                    b2.a(a.e);
                    this.b.a(b2, false);
                    break;
                }
                case 4: {
                    b2.a(a.e);
                    this.b.i(b2);
                    break;
                }
                case 5: {
                    b2.a(a.f);
                    this.b.h(b2);
                    break;
                }
                case 6: {
                    b2.a(a.e);
                    this.b.k(b2);
                    break;
                }
                case 7: {
                    b2.a(a.f);
                    this.b.j(b2);
                    break;
                }
            }
            if (!this.u && a.a != 3) {
                this.b.d(b2);
            }
        }
        if (!this.u && b) {
            this.b.a(this.b.m, true);
        }
    }
    
    boolean a(final ArrayList<f> list, final int n, final int n2) {
        if (n2 == n) {
            return false;
        }
        final int size = this.c.size();
        int n3 = -1;
        for (int i = 0; i < size; ++i) {
            final int w = this.c.get(i).b.w;
            if (w != 0 && w != n3) {
                for (int j = n; j < n2; ++j) {
                    final f f = list.get(j);
                    for (int size2 = f.c.size(), k = 0; k < size2; ++k) {
                        if (f.c.get(k).b.w == w) {
                            return true;
                        }
                    }
                }
                n3 = w;
            }
        }
        return false;
    }
    
    @Override
    public boolean a(final ArrayList<f> list, final ArrayList<Boolean> list2) {
        if (android.support.v4.a.q.a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        list.add(this);
        list2.add(false);
        if (this.j) {
            this.b.a(this);
        }
        return true;
    }
    
    void b(final ArrayList<k> list) {
        for (int i = 0; i < this.c.size(); ++i) {
            final a a = this.c.get(i);
            switch (a.a) {
                case 1:
                case 7: {
                    list.remove(a.b);
                    break;
                }
                case 3:
                case 6: {
                    list.add(a.b);
                    break;
                }
            }
        }
    }
    
    boolean b() {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.c.size()) {
                break;
            }
            if (b(this.c.get(n))) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    boolean b(final int n) {
        for (int size = this.c.size(), i = 0; i < size; ++i) {
            if (this.c.get(i).b.w == n) {
                return true;
            }
        }
        return false;
    }
    
    public String c() {
        return this.l;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.n >= 0) {
            sb.append(" #");
            sb.append(this.n);
        }
        if (this.l != null) {
            sb.append(" ");
            sb.append(this.l);
        }
        sb.append("}");
        return sb.toString();
    }
    
    static final class a
    {
        int a;
        k b;
        int c;
        int d;
        int e;
        int f;
    }
}
