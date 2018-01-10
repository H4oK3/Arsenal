// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

public class f<E> implements Cloneable
{
    private static final Object a;
    private boolean b;
    private long[] c;
    private Object[] d;
    private int e;
    
    static {
        a = new Object();
    }
    
    public f() {
        this(10);
    }
    
    public f(int b) {
        this.b = false;
        if (b == 0) {
            this.c = android.support.v4.f.c.b;
            this.d = android.support.v4.f.c.c;
        }
        else {
            b = android.support.v4.f.c.b(b);
            this.c = new long[b];
            this.d = new Object[b];
        }
        this.e = 0;
    }
    
    private void d() {
        final int e = this.e;
        final long[] c = this.c;
        final Object[] d = this.d;
        int i = 0;
        int e2 = 0;
        while (i < e) {
            final Object o = d[i];
            int n = e2;
            if (o != f.a) {
                if (i != e2) {
                    c[e2] = c[i];
                    d[e2] = o;
                    d[i] = null;
                }
                n = e2 + 1;
            }
            ++i;
            e2 = n;
        }
        this.b = false;
        this.e = e2;
    }
    
    public long a(final int n) {
        if (this.b) {
            this.d();
        }
        return this.c[n];
    }
    
    public f<E> a() {
        f f;
        try {
            final f<E> f2;
            f = (f2 = (f)super.clone());
            final f f3 = this;
            final long[] array = f3.c;
            final long[] array2 = array.clone();
            final long[] array3 = array2;
            f2.c = array3;
            final f f4 = f;
            final f f5 = this;
            final Object[] array4 = f5.d;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            f4.d = array6;
            return f;
        }
        catch (CloneNotSupportedException f) {
            return null;
        }
        try {
            final f<E> f2 = f;
            final f f3 = this;
            final long[] array = f3.c;
            final long[] array2 = array.clone();
            final long[] array3 = array2;
            f2.c = array3;
            final f f4 = f;
            final f f5 = this;
            final Object[] array4 = f5.d;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            f4.d = array6;
            return f;
        }
        catch (CloneNotSupportedException ex) {
            return f;
        }
    }
    
    public E a(final long n) {
        return this.a(n, null);
    }
    
    public E a(final long n, final E e) {
        final int a = android.support.v4.f.c.a(this.c, this.e, n);
        if (a < 0 || this.d[a] == f.a) {
            return e;
        }
        return (E)this.d[a];
    }
    
    public int b() {
        if (this.b) {
            this.d();
        }
        return this.e;
    }
    
    public E b(final int n) {
        if (this.b) {
            this.d();
        }
        return (E)this.d[n];
    }
    
    public void b(final long n) {
        final int a = android.support.v4.f.c.a(this.c, this.e, n);
        if (a >= 0 && this.d[a] != f.a) {
            this.d[a] = f.a;
            this.b = true;
        }
    }
    
    public void b(final long n, final E e) {
        final int a = android.support.v4.f.c.a(this.c, this.e, n);
        if (a >= 0) {
            this.d[a] = e;
            return;
        }
        final int n2 = ~a;
        if (n2 < this.e && this.d[n2] == f.a) {
            this.c[n2] = n;
            this.d[n2] = e;
            return;
        }
        int n3 = n2;
        if (this.b) {
            n3 = n2;
            if (this.e >= this.c.length) {
                this.d();
                n3 = ~android.support.v4.f.c.a(this.c, this.e, n);
            }
        }
        if (this.e >= this.c.length) {
            final int b = android.support.v4.f.c.b(this.e + 1);
            final long[] c = new long[b];
            final Object[] d = new Object[b];
            System.arraycopy(this.c, 0, c, 0, this.c.length);
            System.arraycopy(this.d, 0, d, 0, this.d.length);
            this.c = c;
            this.d = d;
        }
        if (this.e - n3 != 0) {
            System.arraycopy(this.c, n3, this.c, n3 + 1, this.e - n3);
            System.arraycopy(this.d, n3, this.d, n3 + 1, this.e - n3);
        }
        this.c[n3] = n;
        this.d[n3] = e;
        ++this.e;
    }
    
    public void c() {
        final int e = this.e;
        final Object[] d = this.d;
        for (int i = 0; i < e; ++i) {
            d[i] = null;
        }
        this.e = 0;
        this.b = false;
    }
    
    @Override
    public String toString() {
        if (this.b() <= 0) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(this.e * 28);
        sb.append('{');
        for (int i = 0; i < this.e; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.a(i));
            sb.append('=');
            final E b = this.b(i);
            if (b != this) {
                sb.append(b);
            }
            else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
