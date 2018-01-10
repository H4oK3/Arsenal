// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

import java.util.Map;
import java.util.LinkedHashMap;

public class g<K, V>
{
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    
    public g(final int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = c;
        this.a = new LinkedHashMap<K, V>(0, 0.75f, true);
    }
    
    private int c(final K k, final V v) {
        final int b = this.b(k, v);
        if (b < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return b;
    }
    
    public final V a(final K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V v;
        synchronized (this) {
            v = this.a.get(k);
            if (v != null) {
                ++this.g;
                return v;
            }
            ++this.h;
            // monitorexit(this)
            v = this.b(k);
            if (v == null) {
                return null;
            }
        }
        synchronized (this) {
            ++this.e;
            final K i;
            final V put = this.a.put(i, v);
            if (put != null) {
                this.a.put(i, put);
            }
            else {
                this.b += this.c(i, v);
            }
            // monitorexit(this)
            if (put != null) {
                this.a(false, i, v, put);
                return put;
            }
        }
        this.a(this.c);
        return v;
    }
    
    public final V a(final K k, final V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            ++this.d;
            this.b += this.c(k, v);
            final V put = this.a.put(k, v);
            if (put != null) {
                this.b -= this.c(k, put);
            }
            // monitorexit(this)
            if (put != null) {
                this.a(false, k, put, v);
            }
            this.a(this.c);
            return put;
        }
    }
    
    public void a(final int n) {
        while (true) {
            synchronized (this) {
                if (this.b < 0 || (this.a.isEmpty() && this.b != 0)) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
            }
            if (this.b <= n || this.a.isEmpty()) {
                break;
            }
            final Map.Entry<K, V> entry = this.a.entrySet().iterator().next();
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.a.remove(key);
            this.b -= this.c(key, value);
            ++this.f;
            // monitorexit(this)
            this.a(true, key, value, null);
        }
    }
    // monitorexit(this)
    
    protected void a(final boolean b, final K k, final V v, final V v2) {
    }
    
    protected int b(final K k, final V v) {
        return 1;
    }
    
    protected V b(final K k) {
        return null;
    }
    
    @Override
    public final String toString() {
        int n = 0;
        synchronized (this) {
            final int n2 = this.g + this.h;
            if (n2 != 0) {
                n = this.g * 100 / n2;
            }
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.c, this.g, this.h, n);
        }
    }
}
