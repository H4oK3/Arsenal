// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;

class PositionMap<E> implements Cloneable
{
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;
    
    static {
        DELETED = new Object();
    }
    
    public PositionMap() {
        this(10);
    }
    
    public PositionMap(int idealIntArraySize) {
        this.mGarbage = false;
        if (idealIntArraySize == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            idealIntArraySize = idealIntArraySize(idealIntArraySize);
            this.mKeys = new int[idealIntArraySize];
            this.mValues = new Object[idealIntArraySize];
        }
        this.mSize = 0;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        int mSize2 = 0;
        final int[] mKeys = this.mKeys;
        final Object[] mValues = this.mValues;
        int n;
        for (int i = 0; i < mSize; ++i, mSize2 = n) {
            final Object o = mValues[i];
            n = mSize2;
            if (o != PositionMap.DELETED) {
                if (i != mSize2) {
                    mKeys[mSize2] = mKeys[i];
                    mValues[mSize2] = o;
                    mValues[i] = null;
                }
                n = mSize2 + 1;
            }
        }
        this.mGarbage = false;
        this.mSize = mSize2;
    }
    
    static int idealBooleanArraySize(final int n) {
        return idealByteArraySize(n);
    }
    
    static int idealByteArraySize(final int n) {
        int n2 = 4;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= 32) {
                break;
            }
            if (n <= (1 << n2) - 12) {
                n3 = (1 << n2) - 12;
                break;
            }
            ++n2;
        }
        return n3;
    }
    
    static int idealCharArraySize(final int n) {
        return idealByteArraySize(n * 2) / 2;
    }
    
    static int idealFloatArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealIntArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealLongArraySize(final int n) {
        return idealByteArraySize(n * 8) / 8;
    }
    
    static int idealObjectArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealShortArraySize(final int n) {
        return idealByteArraySize(n * 2) / 2;
    }
    
    public void append(final int n, final E e) {
        if (this.mSize != 0 && n <= this.mKeys[this.mSize - 1]) {
            this.put(n, e);
            return;
        }
        if (this.mGarbage && this.mSize >= this.mKeys.length) {
            this.gc();
        }
        final int mSize = this.mSize;
        if (mSize >= this.mKeys.length) {
            final int idealIntArraySize = idealIntArraySize(mSize + 1);
            final int[] mKeys = new int[idealIntArraySize];
            final Object[] mValues = new Object[idealIntArraySize];
            System.arraycopy(this.mKeys, 0, mKeys, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, mValues, 0, this.mValues.length);
            this.mKeys = mKeys;
            this.mValues = mValues;
        }
        this.mKeys[mSize] = n;
        this.mValues[mSize] = e;
        this.mSize = mSize + 1;
    }
    
    public void clear() {
        final int mSize = this.mSize;
        final Object[] mValues = this.mValues;
        for (int i = 0; i < mSize; ++i) {
            mValues[i] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }
    
    public PositionMap<E> clone() {
        PositionMap<E> positionMap = null;
        try {
            final PositionMap positionMap2 = positionMap = (PositionMap)super.clone();
            positionMap2.mKeys = this.mKeys.clone();
            positionMap = positionMap2;
            positionMap2.mValues = this.mValues.clone();
            return positionMap2;
        }
        catch (CloneNotSupportedException ex) {
            return positionMap;
        }
    }
    
    public void delete(int binarySearch) {
        binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, binarySearch);
        if (binarySearch >= 0 && this.mValues[binarySearch] != PositionMap.DELETED) {
            this.mValues[binarySearch] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }
    
    public E get(final int n) {
        return this.get(n, null);
    }
    
    public E get(int binarySearch, final E e) {
        binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, binarySearch);
        if (binarySearch < 0 || this.mValues[binarySearch] == PositionMap.DELETED) {
            return e;
        }
        return (E)this.mValues[binarySearch];
    }
    
    public int indexOfKey(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
    }
    
    public int indexOfValue(final E e) {
        if (this.mGarbage) {
            this.gc();
        }
        for (int i = 0; i < this.mSize; ++i) {
            if (this.mValues[i] == e) {
                return i;
            }
        }
        return -1;
    }
    
    public void insertKeyRange(final int n, final int n2) {
    }
    
    public int keyAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mKeys[n];
    }
    
    public void put(final int n, final E e) {
        final int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = e;
            return;
        }
        final int n2 = ~binarySearch;
        if (n2 < this.mSize && this.mValues[n2] == PositionMap.DELETED) {
            this.mKeys[n2] = n;
            this.mValues[n2] = e;
            return;
        }
        int n3 = n2;
        if (this.mGarbage) {
            n3 = n2;
            if (this.mSize >= this.mKeys.length) {
                this.gc();
                n3 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
            }
        }
        if (this.mSize >= this.mKeys.length) {
            final int idealIntArraySize = idealIntArraySize(this.mSize + 1);
            final int[] mKeys = new int[idealIntArraySize];
            final Object[] mValues = new Object[idealIntArraySize];
            System.arraycopy(this.mKeys, 0, mKeys, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, mValues, 0, this.mValues.length);
            this.mKeys = mKeys;
            this.mValues = mValues;
        }
        if (this.mSize - n3 != 0) {
            System.arraycopy(this.mKeys, n3, this.mKeys, n3 + 1, this.mSize - n3);
            System.arraycopy(this.mValues, n3, this.mValues, n3 + 1, this.mSize - n3);
        }
        this.mKeys[n3] = n;
        this.mValues[n3] = e;
        ++this.mSize;
    }
    
    public void remove(final int n) {
        this.delete(n);
    }
    
    public void removeAt(final int n) {
        if (this.mValues[n] != PositionMap.DELETED) {
            this.mValues[n] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }
    
    public void removeAtRange(int i, int min) {
        for (min = Math.min(this.mSize, i + min); i < min; ++i) {
            this.removeAt(i);
        }
    }
    
    public void removeKeyRange(final ArrayList<E> list, final int n, final int n2) {
    }
    
    public void setValueAt(final int n, final E e) {
        if (this.mGarbage) {
            this.gc();
        }
        this.mValues[n] = e;
    }
    
    public int size() {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mSize;
    }
    
    @Override
    public String toString() {
        if (this.size() <= 0) {
            return "{}";
        }
        final StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.keyAt(i));
            sb.append('=');
            final E value = this.valueAt(i);
            if (value != this) {
                sb.append(value);
            }
            else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
    
    public E valueAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return (E)this.mValues[n];
    }
    
    static class ContainerHelpers
    {
        static final boolean[] EMPTY_BOOLEANS;
        static final int[] EMPTY_INTS;
        static final long[] EMPTY_LONGS;
        static final Object[] EMPTY_OBJECTS;
        
        static {
            EMPTY_BOOLEANS = new boolean[0];
            EMPTY_INTS = new int[0];
            EMPTY_LONGS = new long[0];
            EMPTY_OBJECTS = new Object[0];
        }
        
        static int binarySearch(final int[] array, int i, final int n) {
            final int n2 = 0;
            final int n3 = i - 1;
            i = n2;
            int n4 = n3;
            while (i <= n4) {
                final int n5 = i + n4 >>> 1;
                final int n6 = array[n5];
                if (n6 < n) {
                    i = n5 + 1;
                }
                else {
                    final int n7 = n5;
                    if (n6 <= n) {
                        return n7;
                    }
                    n4 = n5 - 1;
                }
            }
            return ~i;
        }
    }
}
