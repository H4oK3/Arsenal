// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public final class b<E> implements Collection<E>, Set<E>
{
    static Object[] a;
    static int b;
    static Object[] c;
    static int d;
    private static final int[] j;
    private static final Object[] k;
    final boolean e;
    int[] f;
    Object[] g;
    int h;
    h<E, E> i;
    
    static {
        j = new int[0];
        k = new Object[0];
    }
    
    public b() {
        this(0, false);
    }
    
    public b(final int n, final boolean e) {
        this.e = e;
        if (n == 0) {
            this.f = android.support.v4.f.b.j;
            this.g = android.support.v4.f.b.k;
        }
        else {
            this.d(n);
        }
        this.h = 0;
    }
    
    private int a() {
        final int h = this.h;
        int n;
        if (h == 0) {
            n = -1;
        }
        else {
            final int a = android.support.v4.f.c.a(this.f, h, 0);
            if ((n = a) >= 0) {
                n = a;
                if (this.g[a] != null) {
                    int n2;
                    for (n2 = a + 1; n2 < h && this.f[n2] == 0; ++n2) {
                        if (this.g[n2] == null) {
                            return n2;
                        }
                    }
                    for (int n3 = a - 1; n3 >= 0 && this.f[n3] == 0; --n3) {
                        n = n3;
                        if (this.g[n3] == null) {
                            return n;
                        }
                    }
                    return ~n2;
                }
            }
        }
        return n;
    }
    
    private int a(final Object o, final int n) {
        final int h = this.h;
        int n2;
        if (h == 0) {
            n2 = -1;
        }
        else {
            final int a = android.support.v4.f.c.a(this.f, h, n);
            if ((n2 = a) >= 0) {
                n2 = a;
                if (!o.equals(this.g[a])) {
                    int n3;
                    for (n3 = a + 1; n3 < h && this.f[n3] == n; ++n3) {
                        if (o.equals(this.g[n3])) {
                            return n3;
                        }
                    }
                    for (int n4 = a - 1; n4 >= 0 && this.f[n4] == n; --n4) {
                        n2 = n4;
                        if (o.equals(this.g[n4])) {
                            return n2;
                        }
                    }
                    return ~n3;
                }
            }
        }
        return n2;
    }
    
    private static void a(final int[] array, final Object[] array2, int i) {
    Label_0113:
        while (true) {
            Label_0035: {
                Block_1: {
                    if (array.length == 8) {
                        break Block_1;
                    }
                    break Label_0035;
                    while (i >= 2) {
                        array2[i] = null;
                        --i;
                    }
                    break Label_0035;
                }
                synchronized (b.class) {
                    if (android.support.v4.f.b.d < 10) {
                        array2[0] = android.support.v4.f.b.c;
                        array2[1] = array;
                        --i;
                        continue Label_0113;
                    }
                    return;
                    android.support.v4.f.b.c = array2;
                    ++android.support.v4.f.b.d;
                    return;
                }
            }
            if (array.length != 4) {
                return;
            }
            while (true) {
                while (true) {
                    Label_0130: {
                        synchronized (b.class) {
                            if (android.support.v4.f.b.b < 10) {
                                array2[0] = android.support.v4.f.b.a;
                                array2[1] = array;
                                --i;
                                break Label_0130;
                            }
                            return;
                            android.support.v4.f.b.a = array2;
                            ++android.support.v4.f.b.b;
                            return;
                        }
                        continue Label_0113;
                    }
                    while (i >= 2) {
                        array2[i] = null;
                        --i;
                    }
                    continue;
                }
            }
            break;
        }
    }
    
    private h<E, E> b() {
        if (this.i == null) {
            this.i = new h<E, E>() {
                @Override
                protected int a() {
                    return b.this.h;
                }
                
                @Override
                protected int a(final Object o) {
                    return b.this.a(o);
                }
                
                @Override
                protected Object a(final int n, final int n2) {
                    return b.this.g[n];
                }
                
                @Override
                protected E a(final int n, final E e) {
                    throw new UnsupportedOperationException("not a map");
                }
                
                @Override
                protected void a(final int n) {
                    b.this.c(n);
                }
                
                @Override
                protected void a(final E e, final E e2) {
                    b.this.add(e);
                }
                
                @Override
                protected int b(final Object o) {
                    return b.this.a(o);
                }
                
                @Override
                protected Map<E, E> b() {
                    throw new UnsupportedOperationException("not a map");
                }
                
                @Override
                protected void c() {
                    b.this.clear();
                }
            };
        }
        return this.i;
    }
    
    private void d(final int n) {
        while (true) {
            Label_0094: {
                if (n != 8) {
                    break Label_0094;
                }
                synchronized (b.class) {
                    if (android.support.v4.f.b.c != null) {
                        final Object[] c = android.support.v4.f.b.c;
                        this.g = c;
                        android.support.v4.f.b.c = (Object[])c[0];
                        this.f = (int[])c[1];
                        c[0] = (c[1] = null);
                        --android.support.v4.f.b.d;
                        return;
                    }
                    // monitorexit(b.class)
                    this.f = new int[n];
                    this.g = new Object[n];
                    return;
                }
            }
            if (n == 4) {
                synchronized (b.class) {
                    if (android.support.v4.f.b.a != null) {
                        final Object[] a = android.support.v4.f.b.a;
                        this.g = a;
                        android.support.v4.f.b.a = (Object[])a[0];
                        this.f = (int[])a[1];
                        a[0] = (a[1] = null);
                        --android.support.v4.f.b.b;
                        return;
                    }
                }
                // monitorexit(b.class)
                continue;
            }
            continue;
        }
    }
    
    public int a(final Object o) {
        if (o == null) {
            return this.a();
        }
        int n;
        if (this.e) {
            n = System.identityHashCode(o);
        }
        else {
            n = o.hashCode();
        }
        return this.a(o, n);
    }
    
    public void a(final int n) {
        if (this.f.length < n) {
            final int[] f = this.f;
            final Object[] g = this.g;
            this.d(n);
            if (this.h > 0) {
                System.arraycopy(f, 0, this.f, 0, this.h);
                System.arraycopy(g, 0, this.g, 0, this.h);
            }
            a(f, g, this.h);
        }
    }
    
    @Override
    public boolean add(final E e) {
        int n;
        int n2;
        if (e == null) {
            n = this.a();
            n2 = 0;
        }
        else {
            int n3;
            if (this.e) {
                n3 = System.identityHashCode(e);
            }
            else {
                n3 = e.hashCode();
            }
            n = this.a(e, n3);
            n2 = n3;
        }
        if (n >= 0) {
            return false;
        }
        final int n4 = ~n;
        if (this.h >= this.f.length) {
            int n5;
            if (this.h >= 8) {
                n5 = this.h + (this.h >> 1);
            }
            else if (this.h >= 4) {
                n5 = 8;
            }
            else {
                n5 = 4;
            }
            final int[] f = this.f;
            final Object[] g = this.g;
            this.d(n5);
            if (this.f.length > 0) {
                System.arraycopy(f, 0, this.f, 0, f.length);
                System.arraycopy(g, 0, this.g, 0, g.length);
            }
            a(f, g, this.h);
        }
        if (n4 < this.h) {
            System.arraycopy(this.f, n4, this.f, n4 + 1, this.h - n4);
            System.arraycopy(this.g, n4, this.g, n4 + 1, this.h - n4);
        }
        this.f[n4] = n2;
        this.g[n4] = e;
        ++this.h;
        return true;
    }
    
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        this.a(this.h + collection.size());
        boolean b = false;
        final Iterator<? extends E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            b |= this.add(iterator.next());
        }
        return b;
    }
    
    public E b(final int n) {
        return (E)this.g[n];
    }
    
    public E c(final int n) {
        int n2 = 8;
        final Object o = this.g[n];
        if (this.h <= 1) {
            a(this.f, this.g, this.h);
            this.f = android.support.v4.f.b.j;
            this.g = android.support.v4.f.b.k;
            this.h = 0;
        }
        else {
            if (this.f.length <= 8 || this.h >= this.f.length / 3) {
                --this.h;
                if (n < this.h) {
                    System.arraycopy(this.f, n + 1, this.f, n, this.h - n);
                    System.arraycopy(this.g, n + 1, this.g, n, this.h - n);
                }
                this.g[this.h] = null;
                return (E)o;
            }
            if (this.h > 8) {
                n2 = this.h + (this.h >> 1);
            }
            final int[] f = this.f;
            final Object[] g = this.g;
            this.d(n2);
            --this.h;
            if (n > 0) {
                System.arraycopy(f, 0, this.f, 0, n);
                System.arraycopy(g, 0, this.g, 0, n);
            }
            if (n < this.h) {
                System.arraycopy(f, n + 1, this.f, n, this.h - n);
                System.arraycopy(g, n + 1, this.g, n, this.h - n);
                return (E)o;
            }
        }
        return (E)o;
    }
    
    @Override
    public void clear() {
        if (this.h != 0) {
            a(this.f, this.g, this.h);
            this.f = android.support.v4.f.b.j;
            this.g = android.support.v4.f.b.k;
            this.h = 0;
        }
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.a(o) >= 0;
    }
    
    @Override
    public boolean containsAll(final Collection<?> collection) {
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof Set) {
                final Set set = (Set)o;
                if (this.size() != set.size()) {
                    return false;
                }
                int i = 0;
                try {
                    while (i < this.h) {
                        if (!set.contains(this.b(i))) {
                            return false;
                        }
                        ++i;
                    }
                    return true;
                }
                catch (NullPointerException ex) {
                    return false;
                }
                catch (ClassCastException ex2) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int i = 0;
        final int[] f = this.f;
        final int h = this.h;
        int n = 0;
        while (i < h) {
            n += f[i];
            ++i;
        }
        return n;
    }
    
    @Override
    public boolean isEmpty() {
        return this.h <= 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return this.b().e().iterator();
    }
    
    @Override
    public boolean remove(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            this.c(a);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeAll(final Collection<?> collection) {
        boolean b = false;
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            b |= this.remove(iterator.next());
        }
        return b;
    }
    
    @Override
    public boolean retainAll(final Collection<?> collection) {
        final int h = this.h;
        boolean b = false;
        for (int i = h - 1; i >= 0; --i) {
            if (!collection.contains(this.g[i])) {
                this.c(i);
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public int size() {
        return this.h;
    }
    
    @Override
    public Object[] toArray() {
        final Object[] array = new Object[this.h];
        System.arraycopy(this.g, 0, array, 0, this.h);
        return array;
    }
    
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < this.h) {
            array = (T[])Array.newInstance(array.getClass().getComponentType(), this.h);
        }
        System.arraycopy(this.g, 0, array, 0, this.h);
        if (array.length > this.h) {
            array[this.h] = null;
        }
        return array;
    }
    
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(this.h * 14);
        sb.append('{');
        for (int i = 0; i < this.h; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            final E b = this.b(i);
            if (b != this) {
                sb.append(b);
            }
            else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
