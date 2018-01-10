// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.support.v4.e.d;
import android.os.Parcel;
import android.support.v4.e.e;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public abstract class a implements Parcelable
{
    public static final Parcelable$Creator<a> CREATOR;
    public static final a a;
    private final Parcelable b;
    
    static {
        a = new a() {};
        CREATOR = d.a((e<a>)new e<a>() {
            public a b(final Parcel parcel, final ClassLoader classLoader) {
                if (parcel.readParcelable(classLoader) != null) {
                    throw new IllegalStateException("superState must be null");
                }
                return android.support.v4.g.a.a;
            }
            
            public a[] b(final int n) {
                return new a[n];
            }
        });
    }
    
    private a() {
        this.b = null;
    }
    
    protected a(final Parcel parcel, final ClassLoader classLoader) {
        Object b = parcel.readParcelable(classLoader);
        if (b == null) {
            b = android.support.v4.g.a.a;
        }
        this.b = (Parcelable)b;
    }
    
    protected a(Parcelable b) {
        if (b == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        if (b == android.support.v4.g.a.a) {
            b = null;
        }
        this.b = b;
    }
    
    public final Parcelable a() {
        return this.b;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable(this.b, n);
    }
}
