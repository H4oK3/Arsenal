// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

import java.util.Map;

public class j<K, V>
{
    static Object[] b;
    static int c;
    static Object[] d;
    static int e;
    int[] f;
    Object[] g;
    int h;
    
    public j() {
        this.f = android.support.v4.f.c.a;
        this.g = android.support.v4.f.c.c;
        this.h = 0;
    }
    
    public j(final int n) {
        if (n == 0) {
            this.f = android.support.v4.f.c.a;
            this.g = android.support.v4.f.c.c;
        }
        else {
            this.e(n);
        }
        this.h = 0;
    }
    
    private static void a(final int[] array, final Object[] array2, int i) {
    Label_0117:
        while (true) {
            Label_0037: {
                Block_1: {
                    if (array.length == 8) {
                        break Block_1;
                    }
                    break Label_0037;
                    while (i >= 2) {
                        array2[i] = null;
                        --i;
                    }
                    break Label_0037;
                }
                synchronized (a.class) {
                    if (j.e < 10) {
                        array2[0] = j.d;
                        array2[1] = array;
                        i = (i << 1) - 1;
                        continue Label_0117;
                    }
                    return;
                    j.d = array2;
                    ++j.e;
                    return;
                }
            }
            if (array.length != 4) {
                return;
            }
            while (true) {
                while (true) {
                    Label_0134: {
                        synchronized (a.class) {
                            if (j.c < 10) {
                                array2[0] = j.b;
                                array2[1] = array;
                                i = (i << 1) - 1;
                                break Label_0134;
                            }
                            return;
                            j.b = array2;
                            ++j.c;
                            return;
                        }
                        continue Label_0117;
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
    
    private void e(final int n) {
        while (true) {
            Label_0096: {
                if (n != 8) {
                    break Label_0096;
                }
                synchronized (a.class) {
                    if (j.d != null) {
                        final Object[] d = j.d;
                        this.g = d;
                        j.d = (Object[])d[0];
                        this.f = (int[])d[1];
                        d[0] = (d[1] = null);
                        --j.e;
                        return;
                    }
                    // monitorexit(a.class)
                    this.f = new int[n];
                    this.g = new Object[n << 1];
                    return;
                }
            }
            if (n == 4) {
                synchronized (a.class) {
                    if (j.b != null) {
                        final Object[] b = j.b;
                        this.g = b;
                        j.b = (Object[])b[0];
                        this.f = (int[])b[1];
                        b[0] = (b[1] = null);
                        --j.c;
                        return;
                    }
                }
                // monitorexit(a.class)
                continue;
            }
            continue;
        }
    }
    
    int a() {
        final int h = this.h;
        int n;
        if (h == 0) {
            n = -1;
        }
        else {
            final int a = android.support.v4.f.c.a(this.f, h, 0);
            if ((n = a) >= 0) {
                n = a;
                if (this.g[a << 1] != null) {
                    int n2;
                    for (n2 = a + 1; n2 < h && this.f[n2] == 0; ++n2) {
                        if (this.g[n2 << 1] == null) {
                            return n2;
                        }
                    }
                    for (int n3 = a - 1; n3 >= 0 && this.f[n3] == 0; --n3) {
                        n = n3;
                        if (this.g[n3 << 1] == null) {
                            return n;
                        }
                    }
                    return ~n2;
                }
            }
        }
        return n;
    }
    
    public int a(final Object o) {
        if (o == null) {
            return this.a();
        }
        return this.a(o, o.hashCode());
    }
    
    int a(final Object o, final int n) {
        final int h = this.h;
        int n2;
        if (h == 0) {
            n2 = -1;
        }
        else {
            final int a = android.support.v4.f.c.a(this.f, h, n);
            if ((n2 = a) >= 0) {
                n2 = a;
                if (!o.equals(this.g[a << 1])) {
                    int n3;
                    for (n3 = a + 1; n3 < h && this.f[n3] == n; ++n3) {
                        if (o.equals(this.g[n3 << 1])) {
                            return n3;
                        }
                    }
                    for (int n4 = a - 1; n4 >= 0 && this.f[n4] == n; --n4) {
                        n2 = n4;
                        if (o.equals(this.g[n4 << 1])) {
                            return n2;
                        }
                    }
                    return ~n3;
                }
            }
        }
        return n2;
    }
    
    public V a(int n, final V v) {
        n = (n << 1) + 1;
        final Object o = this.g[n];
        this.g[n] = v;
        return (V)o;
    }
    
    public void a(final int n) {
        if (this.f.length < n) {
            final int[] f = this.f;
            final Object[] g = this.g;
            this.e(n);
            if (this.h > 0) {
                System.arraycopy(f, 0, this.f, 0, this.h);
                System.arraycopy(g, 0, this.g, 0, this.h << 1);
            }
            a(f, g, this.h);
        }
    }
    
    int b(final Object o) {
        int i = 1;
        final int n = 1;
        final int n2 = this.h * 2;
        final Object[] g = this.g;
        if (o == null) {
            for (int j = n; j < n2; j += 2) {
                if (g[j] == null) {
                    return j >> 1;
                }
            }
        }
        else {
            while (i < n2) {
                if (o.equals(g[i])) {
                    return i >> 1;
                }
                i += 2;
            }
        }
        return -1;
    }
    
    public K b(final int n) {
        return (K)this.g[n << 1];
    }
    
    public V c(final int n) {
        return (V)this.g[(n << 1) + 1];
    }
    
    public void clear() {
        if (this.h != 0) {
            a(this.f, this.g, this.h);
            this.f = android.support.v4.f.c.a;
            this.g = android.support.v4.f.c.c;
            this.h = 0;
        }
    }
    
    public boolean containsKey(final Object o) {
        return this.a(o) >= 0;
    }
    
    public boolean containsValue(final Object o) {
        return this.b(o) >= 0;
    }
    
    public V d(final int n) {
        int n2 = 8;
        final Object o = this.g[(n << 1) + 1];
        if (this.h <= 1) {
            a(this.f, this.g, this.h);
            this.f = android.support.v4.f.c.a;
            this.g = android.support.v4.f.c.c;
            this.h = 0;
        }
        else {
            if (this.f.length <= 8 || this.h >= this.f.length / 3) {
                --this.h;
                if (n < this.h) {
                    System.arraycopy(this.f, n + 1, this.f, n, this.h - n);
                    System.arraycopy(this.g, n + 1 << 1, this.g, n << 1, this.h - n << 1);
                }
                this.g[this.h << 1] = null;
                this.g[(this.h << 1) + 1] = null;
                return (V)o;
            }
            if (this.h > 8) {
                n2 = this.h + (this.h >> 1);
            }
            final int[] f = this.f;
            final Object[] g = this.g;
            this.e(n2);
            --this.h;
            if (n > 0) {
                System.arraycopy(f, 0, this.f, 0, n);
                System.arraycopy(g, 0, this.g, 0, n << 1);
            }
            if (n < this.h) {
                System.arraycopy(f, n + 1, this.f, n, this.h - n);
                System.arraycopy(g, n + 1 << 1, this.g, n << 1, this.h - n << 1);
                return (V)o;
            }
        }
        return (V)o;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof j) {
                final j j = (j)o;
                if (this.size() != j.size()) {
                    return false;
                }
                int i = 0;
                try {
                    while (i < this.h) {
                        final K b = this.b(i);
                        final V c = this.c(i);
                        final Object value = j.get(b);
                        if (c == null) {
                            if (value != null || !j.containsKey(b)) {
                                return false;
                            }
                        }
                        else if (!c.equals(value)) {
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
            if (o instanceof Map) {
                final Map map = (Map)o;
                if (this.size() != map.size()) {
                    return false;
                }
                int k = 0;
                try {
                    while (k < this.h) {
                        final K b2 = this.b(k);
                        final V c2 = this.c(k);
                        final Object value2 = map.get(b2);
                        if (c2 == null) {
                            if (value2 != null || !map.containsKey(b2)) {
                                return false;
                            }
                        }
                        else if (!c2.equals(value2)) {
                            return false;
                        }
                        ++k;
                    }
                    return true;
                }
                catch (NullPointerException ex3) {
                    return false;
                }
                catch (ClassCastException ex4) {
                    return false;
                }
                return false;
            }
            return false;
        }
        return true;
    }
    
    public V get(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            return (V)this.g[(a << 1) + 1];
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        final int[] f = this.f;
        final Object[] g = this.g;
        final int h = this.h;
        int n = 1;
        int i = 0;
        int n2 = 0;
        while (i < h) {
            final Object o = g[n];
            final int n3 = f[i];
            int hashCode;
            if (o == null) {
                hashCode = 0;
            }
            else {
                hashCode = o.hashCode();
            }
            n2 += (hashCode ^ n3);
            ++i;
            n += 2;
        }
        return n2;
    }
    
    public boolean isEmpty() {
        return this.h <= 0;
    }
    
    public V put(final K k, final V v) {
        final int n = 8;
        int n2;
        int hashCode;
        if (k == null) {
            n2 = this.a();
            hashCode = 0;
        }
        else {
            hashCode = k.hashCode();
            n2 = this.a(k, hashCode);
        }
        if (n2 >= 0) {
            final int n3 = (n2 << 1) + 1;
            final Object o = this.g[n3];
            this.g[n3] = v;
            return (V)o;
        }
        final int n4 = ~n2;
        if (this.h >= this.f.length) {
            int n5;
            if (this.h >= 8) {
                n5 = this.h + (this.h >> 1);
            }
            else {
                n5 = n;
                if (this.h < 4) {
                    n5 = 4;
                }
            }
            final int[] f = this.f;
            final Object[] g = this.g;
            this.e(n5);
            if (this.f.length > 0) {
                System.arraycopy(f, 0, this.f, 0, f.length);
                System.arraycopy(g, 0, this.g, 0, g.length);
            }
            a(f, g, this.h);
        }
        if (n4 < this.h) {
            System.arraycopy(this.f, n4, this.f, n4 + 1, this.h - n4);
            System.arraycopy(this.g, n4 << 1, this.g, n4 + 1 << 1, this.h - n4 << 1);
        }
        this.f[n4] = hashCode;
        this.g[n4 << 1] = k;
        this.g[(n4 << 1) + 1] = v;
        ++this.h;
        return null;
    }
    
    public V remove(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            return this.d(a);
        }
        return null;
    }
    
    public int size() {
        return this.h;
    }
    
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(this.h * 28);
        sb.append('{');
        for (int i = 0; i < this.h; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            final K b = this.b(i);
            if (b != this) {
                sb.append(b);
            }
            else {
                sb.append("(this Map)");
            }
            sb.append('=');
            final V c = this.c(i);
            if (c != this) {
                sb.append(c);
            }
            else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
