// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.e;

import android.os.Parcel;
import android.os.Build$VERSION;
import android.os.Parcelable$Creator;

public final class d
{
    public static <T> Parcelable$Creator<T> a(final e<T> e) {
        if (Build$VERSION.SDK_INT >= 13) {
            return g.a(e);
        }
        return (Parcelable$Creator<T>)new a((e<Object>)e);
    }
    
    static class a<T> implements Parcelable$Creator<T>
    {
        final e<T> a;
        
        public a(final e<T> a) {
            this.a = a;
        }
        
        public T createFromParcel(final Parcel parcel) {
            return this.a.a(parcel, null);
        }
        
        public T[] newArray(final int n) {
            return this.a.a(n);
        }
    }
}
