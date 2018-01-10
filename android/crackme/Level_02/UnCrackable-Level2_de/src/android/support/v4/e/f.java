// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.e;

import android.os.Parcel;
import android.annotation.TargetApi;
import android.os.Parcelable$ClassLoaderCreator;

@TargetApi(13)
class f<T> implements Parcelable$ClassLoaderCreator<T>
{
    private final e<T> a;
    
    public f(final e<T> a) {
        this.a = a;
    }
    
    public T createFromParcel(final Parcel parcel) {
        return this.a.a(parcel, null);
    }
    
    public T createFromParcel(final Parcel parcel, final ClassLoader classLoader) {
        return this.a.a(parcel, classLoader);
    }
    
    public T[] newArray(final int n) {
        return this.a.a(n);
    }
}
