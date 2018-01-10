// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

public class k<E> implements Cloneable
{
    private static final Object a;
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;
    
    static {
        a = new Object();
    }
    
    public k() {
        this(10);
    }
    
    public k(int a) {
        this.b = false;
        if (a == 0) {
            this.c = android.support.v4.f.c.a;
            this.d = android.support.v4.f.c.c;
        }
        else {
            a = android.support.v4.f.c.a(a);
            this.c = new int[a];
            this.d = new Object[a];
        }
        this.e = 0;
    }
    
    private void d() {
        final int e = this.e;
        final int[] c = this.c;
        final Object[] d = this.d;
        int i = 0;
        int e2 = 0;
        while (i < e) {
            final Object o = d[i];
            int n = e2;
            if (o != k.a) {
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
    
    public k<E> a() {
        k k;
        try {
            final k<E> i;
            k = (i = (k)super.clone());
            final k j = this;
            final int[] array = j.c;
            final int[] array2 = array.clone();
            final int[] array3 = array2;
            i.c = array3;
            final k l = k;
            final k m = this;
            final Object[] array4 = m.d;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            l.d = array6;
            return k;
        }
        catch (CloneNotSupportedException k) {
            return null;
        }
        try {
            final k<E> i = k;
            final k j = this;
            final int[] array = j.c;
            final int[] array2 = array.clone();
            final int[] array3 = array2;
            i.c = array3;
            final k l = k;
            final k m = this;
            final Object[] array4 = m.d;
            final Object[] array5 = array4.clone();
            final Object[] array6 = array5;
            l.d = array6;
            return k;
        }
        catch (CloneNotSupportedException ex) {
            return k;
        }
    }
    
    public E a(final int n) {
        return this.a(n, null);
    }
    
    public E a(int a, final E e) {
        a = android.support.v4.f.c.a(this.c, this.e, a);
        if (a < 0 || this.d[a] == k.a) {
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
    
    public void b(int a) {
        a = android.support.v4.f.c.a(this.c, this.e, a);
        if (a >= 0 && this.d[a] != k.a) {
            this.d[a] = k.a;
            this.b = true;
        }
    }
    
    public void b(final int n, final E e) {
        final int a = android.support.v4.f.c.a(this.c, this.e, n);
        if (a >= 0) {
            this.d[a] = e;
            return;
        }
        final int n2 = ~a;
        if (n2 < this.e && this.d[n2] == k.a) {
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
            final int a2 = android.support.v4.f.c.a(this.e + 1);
            final int[] c = new int[a2];
            final Object[] d = new Object[a2];
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
    
    public void c(final int n) {
        this.b(n);
    }
    
    public void c(final int n, final E e) {
        if (this.e != 0 && n <= this.c[this.e - 1]) {
            this.b(n, e);
            return;
        }
        if (this.b && this.e >= this.c.length) {
            this.d();
        }
        final int e2 = this.e;
        if (e2 >= this.c.length) {
            final int a = android.support.v4.f.c.a(e2 + 1);
            final int[] c = new int[a];
            final Object[] d = new Object[a];
            System.arraycopy(this.c, 0, c, 0, this.c.length);
            System.arraycopy(this.d, 0, d, 0, this.d.length);
            this.c = c;
            this.d = d;
        }
        this.c[e2] = n;
        this.d[e2] = e;
        this.e = e2 + 1;
    }
    
    public int d(final int n) {
        if (this.b) {
            this.d();
        }
        return this.c[n];
    }
    
    public E e(final int n) {
        if (this.b) {
            this.d();
        }
        return (E)this.d[n];
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
            sb.append(this.d(i));
            sb.append('=');
            final E e = this.e(i);
            if (e != this) {
                sb.append(e);
            }
            else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
