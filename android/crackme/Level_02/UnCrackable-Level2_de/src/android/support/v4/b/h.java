// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.b;

import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.v4.f.d;

public class h<D>
{
    int a;
    b<D> b;
    a<D> c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    
    public String a(final D n) {
        final StringBuilder sb = new StringBuilder(64);
        android.support.v4.f.d.a(n, sb);
        sb.append("}");
        return sb.toString();
    }
    
    public final void a() {
        this.d = true;
        this.f = false;
        this.e = false;
        this.b();
    }
    
    public void a(final int a, final b<D> b) {
        if (this.b != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.b = b;
        this.a = a;
    }
    
    public void a(final a<D> c) {
        if (this.c != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.c = c;
    }
    
    public void a(final b<D> b) {
        if (this.b == null) {
            throw new IllegalStateException("No listener register");
        }
        if (this.b != b) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.b = null;
    }
    
    public void a(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mId=");
        printWriter.print(this.a);
        printWriter.print(" mListener=");
        printWriter.println(this.b);
        if (this.d || this.g || this.h) {
            printWriter.print(s);
            printWriter.print("mStarted=");
            printWriter.print(this.d);
            printWriter.print(" mContentChanged=");
            printWriter.print(this.g);
            printWriter.print(" mProcessingChange=");
            printWriter.println(this.h);
        }
        if (this.e || this.f) {
            printWriter.print(s);
            printWriter.print("mAbandoned=");
            printWriter.print(this.e);
            printWriter.print(" mReset=");
            printWriter.println(this.f);
        }
    }
    
    protected void b() {
    }
    
    public void b(final a<D> a) {
        if (this.c == null) {
            throw new IllegalStateException("No listener register");
        }
        if (this.c != a) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.c = null;
    }
    
    public void c() {
        this.d = false;
        this.d();
    }
    
    protected void d() {
    }
    
    public void e() {
        this.f();
        this.f = true;
        this.d = false;
        this.e = false;
        this.g = false;
        this.h = false;
    }
    
    protected void f() {
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        android.support.v4.f.d.a(this, sb);
        sb.append(" id=");
        sb.append(this.a);
        sb.append("}");
        return sb.toString();
    }
    
    public interface a<D>
    {
    }
    
    public interface b<D>
    {
    }
}
