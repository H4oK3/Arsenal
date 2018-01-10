// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.view.LayoutInflater;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.view.View;
import android.support.v4.f.j;
import android.os.Handler;
import android.content.Context;
import android.app.Activity;

public abstract class o<E> extends m
{
    private final Activity a;
    final Context b;
    final int c;
    final q d;
    private final Handler e;
    private j<String, x> f;
    private boolean g;
    private y h;
    private boolean i;
    private boolean j;
    
    o(final Activity a, final Context b, final Handler e, final int c) {
        this.d = new q();
        this.a = a;
        this.b = b;
        this.e = e;
        this.c = c;
    }
    
    o(final l l) {
        this(l, (Context)l, l.c, 0);
    }
    
    y a(final String s, final boolean b, final boolean b2) {
        if (this.f == null) {
            this.f = new j<String, x>();
        }
        final y y = this.f.get(s);
        y y3;
        if (y == null && b2) {
            final y y2 = new y(s, this, b);
            this.f.put(s, y2);
            y3 = y2;
        }
        else {
            y3 = y;
            if (b && (y3 = y) != null) {
                y3 = y;
                if (!y.e) {
                    y.b();
                    return y;
                }
            }
        }
        return y3;
    }
    
    @Override
    public View a(final int n) {
        return null;
    }
    
    void a(final j<String, x> f) {
        if (f != null) {
            for (int size = f.size(), i = 0; i < size; ++i) {
                f.c(i).a(this);
            }
        }
        this.f = f;
    }
    
    void a(final String s) {
        if (this.f != null) {
            final y y = this.f.get(s);
            if (y != null && !y.f) {
                y.h();
                this.f.remove(s);
            }
        }
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    void a(final boolean g) {
        this.g = g;
        if (this.h == null || !this.j) {
            return;
        }
        this.j = false;
        if (g) {
            this.h.d();
            return;
        }
        this.h.c();
    }
    
    @Override
    public boolean a() {
        return true;
    }
    
    public boolean a(final k k) {
        return true;
    }
    
    public LayoutInflater b() {
        return (LayoutInflater)this.b.getSystemService("layout_inflater");
    }
    
    void b(final k k) {
    }
    
    void b(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.j);
        if (this.h != null) {
            printWriter.print(s);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.h)));
            printWriter.println(":");
            this.h.a(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    public void c() {
    }
    
    public boolean d() {
        return true;
    }
    
    public int e() {
        return this.c;
    }
    
    Activity f() {
        return this.a;
    }
    
    Context g() {
        return this.b;
    }
    
    Handler h() {
        return this.e;
    }
    
    q i() {
        return this.d;
    }
    
    boolean j() {
        return this.g;
    }
    
    void k() {
        if (this.j) {
            return;
        }
        this.j = true;
        if (this.h != null) {
            this.h.b();
        }
        else if (!this.i) {
            this.h = this.a("(root)", this.j, false);
            if (this.h != null && !this.h.e) {
                this.h.b();
            }
        }
        this.i = true;
    }
    
    void l() {
        if (this.h == null) {
            return;
        }
        this.h.h();
    }
    
    void m() {
        if (this.f != null) {
            final int size = this.f.size();
            final y[] array = new y[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = (y)this.f.c(i);
            }
            for (int j = 0; j < size; ++j) {
                final y y = array[j];
                y.e();
                y.g();
            }
        }
    }
    
    j<String, x> n() {
        int n = 0;
        int n3;
        if (this.f != null) {
            final int size = this.f.size();
            final y[] array = new y[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = (y)this.f.c(i);
            }
            final boolean j = this.j();
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= size) {
                    break;
                }
                final y y = array[n];
                if (!y.f && j) {
                    if (!y.e) {
                        y.b();
                    }
                    y.d();
                }
                if (y.f) {
                    n2 = 1;
                }
                else {
                    y.h();
                    this.f.remove(y.d);
                }
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        if (n3 != 0) {
            return this.f;
        }
        return null;
    }
}
