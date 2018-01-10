// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class s implements Parcelable
{
    public static final Parcelable$Creator<s> CREATOR;
    t[] a;
    int[] b;
    g[] c;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<s>() {
            public s a(final Parcel parcel) {
                return new s(parcel);
            }
            
            public s[] a(final int n) {
                return new s[n];
            }
        };
    }
    
    public s() {
    }
    
    public s(final Parcel parcel) {
        this.a = (t[])parcel.createTypedArray((Parcelable$Creator)t.CREATOR);
        this.b = parcel.createIntArray();
        this.c = (g[])parcel.createTypedArray((Parcelable$Creator)g.CREATOR);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeTypedArray((Parcelable[])this.a, n);
        parcel.writeIntArray(this.b);
        parcel.writeTypedArray((Parcelable[])this.c, n);
    }
}
