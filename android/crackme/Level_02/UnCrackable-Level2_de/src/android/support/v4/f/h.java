// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

import java.lang.reflect.Array;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;

abstract class h<K, V>
{
    b b;
    c c;
    e d;
    
    public static <K, V> boolean a(final Map<K, V> map, final Collection<?> collection) {
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!map.containsKey(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static <T> boolean a(final Set<T> set, final Object o) {
        final boolean b = true;
        boolean b2 = false;
        if (set == o) {
            b2 = true;
        }
        else if (o instanceof Set) {
            final Set set2 = (Set)o;
            try {
                return set.size() == set2.size() && set.containsAll(set2) && b;
            }
            catch (ClassCastException ex) {
                return false;
            }
            catch (NullPointerException ex2) {
                return false;
            }
        }
        return b2;
    }
    
    public static <K, V> boolean b(final Map<K, V> map, final Collection<?> collection) {
        final int size = map.size();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            map.remove(iterator.next());
        }
        return size != map.size();
    }
    
    public static <K, V> boolean c(final Map<K, V> map, final Collection<?> collection) {
        final int size = map.size();
        final Iterator<K> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return size != map.size();
    }
    
    protected abstract int a();
    
    protected abstract int a(final Object p0);
    
    protected abstract Object a(final int p0, final int p1);
    
    protected abstract V a(final int p0, final V p1);
    
    protected abstract void a(final int p0);
    
    protected abstract void a(final K p0, final V p1);
    
    public <T> T[] a(T[] array, final int n) {
        final int a = this.a();
        if (array.length < a) {
            array = (T[])Array.newInstance(array.getClass().getComponentType(), a);
        }
        for (int i = 0; i < a; ++i) {
            array[i] = (T)this.a(i, n);
        }
        if (array.length > a) {
            array[a] = null;
        }
        return array;
    }
    
    protected abstract int b(final Object p0);
    
    protected abstract Map<K, V> b();
    
    public Object[] b(final int n) {
        final int a = this.a();
        final Object[] array = new Object[a];
        for (int i = 0; i < a; ++i) {
            array[i] = this.a(i, n);
        }
        return array;
    }
    
    protected abstract void c();
    
    public Set<Map.Entry<K, V>> d() {
        if (this.b == null) {
            this.b = new b();
        }
        return this.b;
    }
    
    public Set<K> e() {
        if (this.c == null) {
            this.c = new c();
        }
        return this.c;
    }
    
    public Collection<V> f() {
        if (this.d == null) {
            this.d = new e();
        }
        return this.d;
    }
    
    final class a<T> implements Iterator<T>
    {
        final int a;
        int b;
        int c;
        boolean d;
        
        a(final int a) {
            this.d = false;
            this.a = a;
            this.b = h.this.a();
        }
        
        @Override
        public boolean hasNext() {
            return this.c < this.b;
        }
        
        @Override
        public T next() {
            final Object a = h.this.a(this.c, this.a);
            ++this.c;
            this.d = true;
            return (T)a;
        }
        
        @Override
        public void remove() {
            if (!this.d) {
                throw new IllegalStateException();
            }
            --this.c;
            --this.b;
            this.d = false;
            h.this.a(this.c);
        }
    }
    
    final class b implements Set<Map.Entry<K, V>>
    {
        public boolean a(final Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends Map.Entry<K, V>> collection) {
            final int a = h.this.a();
            for (final Map.Entry<K, V> entry : collection) {
                h.this.a(entry.getKey(), entry.getValue());
            }
            return a != h.this.a();
        }
        
        @Override
        public void clear() {
            h.this.c();
        }
        
        @Override
        public boolean contains(final Object o) {
            if (o instanceof Map.Entry) {
                final Map.Entry entry = (Map.Entry)o;
                final int a = h.this.a(entry.getKey());
                if (a >= 0) {
                    return android.support.v4.f.c.a(h.this.a(a, 1), entry.getValue());
                }
            }
            return false;
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
            return h.a((Set<Object>)this, o);
        }
        
        @Override
        public int hashCode() {
            int i;
            int n;
            int hashCode;
            int hashCode2;
            for (i = h.this.a() - 1, n = 0; i >= 0; --i, n += (hashCode2 ^ hashCode)) {
                final Object a = h.this.a(i, 0);
                final Object a2 = h.this.a(i, 1);
                if (a == null) {
                    hashCode = 0;
                }
                else {
                    hashCode = a.hashCode();
                }
                if (a2 == null) {
                    hashCode2 = 0;
                }
                else {
                    hashCode2 = a2.hashCode();
                }
            }
            return n;
        }
        
        @Override
        public boolean isEmpty() {
            return h.this.a() == 0;
        }
        
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }
        
        @Override
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public int size() {
            return h.this.a();
        }
        
        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            throw new UnsupportedOperationException();
        }
    }
    
    final class c implements Set<K>
    {
        @Override
        public boolean add(final K k) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void clear() {
            h.this.c();
        }
        
        @Override
        public boolean contains(final Object o) {
            return h.this.a(o) >= 0;
        }
        
        @Override
        public boolean containsAll(final Collection<?> collection) {
            return h.a(h.this.b(), collection);
        }
        
        @Override
        public boolean equals(final Object o) {
            return h.a((Set<Object>)this, o);
        }
        
        @Override
        public int hashCode() {
            int i = h.this.a() - 1;
            int n = 0;
            while (i >= 0) {
                final Object a = h.this.a(i, 0);
                int hashCode;
                if (a == null) {
                    hashCode = 0;
                }
                else {
                    hashCode = a.hashCode();
                }
                n += hashCode;
                --i;
            }
            return n;
        }
        
        @Override
        public boolean isEmpty() {
            return h.this.a() == 0;
        }
        
        @Override
        public Iterator<K> iterator() {
            return new a<K>(0);
        }
        
        @Override
        public boolean remove(final Object o) {
            final int a = h.this.a(o);
            if (a >= 0) {
                h.this.a(a);
                return true;
            }
            return false;
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            return h.b(h.this.b(), collection);
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            return h.c(h.this.b(), collection);
        }
        
        @Override
        public int size() {
            return h.this.a();
        }
        
        @Override
        public Object[] toArray() {
            return h.this.b(0);
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            return h.this.a(array, 0);
        }
    }
    
    final class d implements Iterator<Entry<K, V>>, Entry<K, V>
    {
        int a;
        int b;
        boolean c;
        
        d() {
            this.c = false;
            this.a = h.this.a() - 1;
            this.b = -1;
        }
        
        public Entry<K, V> a() {
            ++this.b;
            this.c = true;
            return this;
        }
        
        @Override
        public final boolean equals(final Object o) {
            boolean b = true;
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry entry = (Entry)o;
            if (!android.support.v4.f.c.a(entry.getKey(), h.this.a(this.b, 0)) || !android.support.v4.f.c.a(entry.getValue(), h.this.a(this.b, 1))) {
                b = false;
            }
            return b;
        }
        
        @Override
        public K getKey() {
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (K)h.this.a(this.b, 0);
        }
        
        @Override
        public V getValue() {
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (V)h.this.a(this.b, 1);
        }
        
        @Override
        public boolean hasNext() {
            return this.b < this.a;
        }
        
        @Override
        public final int hashCode() {
            int hashCode = 0;
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            final Object a = h.this.a(this.b, 0);
            final Object a2 = h.this.a(this.b, 1);
            int hashCode2;
            if (a == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = a.hashCode();
            }
            if (a2 != null) {
                hashCode = a2.hashCode();
            }
            return hashCode ^ hashCode2;
        }
        
        @Override
        public void remove() {
            if (!this.c) {
                throw new IllegalStateException();
            }
            h.this.a(this.b);
            --this.b;
            --this.a;
            this.c = false;
        }
        
        @Override
        public V setValue(final V v) {
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return h.this.a(this.b, v);
        }
        
        @Override
        public final String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }
    
    final class e implements Collection<V>
    {
        @Override
        public boolean add(final V v) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void clear() {
            h.this.c();
        }
        
        @Override
        public boolean contains(final Object o) {
            return h.this.b(o) >= 0;
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
        public boolean isEmpty() {
            return h.this.a() == 0;
        }
        
        @Override
        public Iterator<V> iterator() {
            return new a<V>(1);
        }
        
        @Override
        public boolean remove(final Object o) {
            final int b = h.this.b(o);
            if (b >= 0) {
                h.this.a(b);
                return true;
            }
            return false;
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            int i = 0;
            int a = h.this.a();
            boolean b = false;
            while (i < a) {
                int n = i;
                int n2 = a;
                if (collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    n = i - 1;
                    n2 = a - 1;
                    b = true;
                }
                i = n + 1;
                a = n2;
            }
            return b;
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            int i = 0;
            int a = h.this.a();
            boolean b = false;
            while (i < a) {
                int n = i;
                int n2 = a;
                if (!collection.contains(h.this.a(i, 1))) {
                    h.this.a(i);
                    n = i - 1;
                    n2 = a - 1;
                    b = true;
                }
                i = n + 1;
                a = n2;
            }
            return b;
        }
        
        @Override
        public int size() {
            return h.this.a();
        }
        
        @Override
        public Object[] toArray() {
            return h.this.b(1);
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            return h.this.a(array, 1);
        }
    }
}
