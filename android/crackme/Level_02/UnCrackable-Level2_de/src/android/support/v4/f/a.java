// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.Map;

public class a<K, V> extends j<K, V> implements Map<K, V>
{
    h<K, V> a;
    
    public a() {
    }
    
    public a(final int n) {
        super(n);
    }
    
    private h<K, V> b() {
        if (this.a == null) {
            this.a = new h<K, V>() {
                @Override
                protected int a() {
                    return android.support.v4.f.a.this.h;
                }
                
                @Override
                protected int a(final Object o) {
                    return android.support.v4.f.a.this.a(o);
                }
                
                @Override
                protected Object a(final int n, final int n2) {
                    return android.support.v4.f.a.this.g[(n << 1) + n2];
                }
                
                @Override
                protected V a(final int n, final V v) {
                    return android.support.v4.f.a.this.a(n, v);
                }
                
                @Override
                protected void a(final int n) {
                    android.support.v4.f.a.this.d(n);
                }
                
                @Override
                protected void a(final K k, final V v) {
                    android.support.v4.f.a.this.put(k, v);
                }
                
                @Override
                protected int b(final Object o) {
                    return android.support.v4.f.a.this.b(o);
                }
                
                @Override
                protected Map<K, V> b() {
                    return (Map<K, V>)android.support.v4.f.a.this;
                }
                
                @Override
                protected void c() {
                    android.support.v4.f.a.this.clear();
                }
            };
        }
        return this.a;
    }
    
    public boolean a(final Collection<?> collection) {
        return android.support.v4.f.h.c((Map<Object, Object>)this, collection);
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.b().d();
    }
    
    @Override
    public Set<K> keySet() {
        return this.b().e();
    }
    
    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        this.a(this.h + map.size());
        for (final Entry<? extends K, ? extends V> entry : map.entrySet()) {
            this.put((K)entry.getKey(), (V)entry.getValue());
        }
    }
    
    @Override
    public Collection<V> values() {
        return this.b().f();
    }
}
