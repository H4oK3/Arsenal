// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.content.Context;
import android.util.Log;
import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class t implements Parcelable
{
    public static final Parcelable$Creator<t> CREATOR;
    final String a;
    final int b;
    final boolean c;
    final int d;
    final int e;
    final String f;
    final boolean g;
    final boolean h;
    final Bundle i;
    final boolean j;
    Bundle k;
    k l;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<t>() {
            public t a(final Parcel parcel) {
                return new t(parcel);
            }
            
            public t[] a(final int n) {
                return new t[n];
            }
        };
    }
    
    public t(final Parcel parcel) {
        final boolean b = true;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.c = (parcel.readInt() != 0);
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = (parcel.readInt() != 0);
        this.h = (parcel.readInt() != 0);
        this.i = parcel.readBundle();
        this.j = (parcel.readInt() != 0 && b);
        this.k = parcel.readBundle();
    }
    
    public t(final k k) {
        this.a = k.getClass().getName();
        this.b = k.e;
        this.c = k.m;
        this.d = k.v;
        this.e = k.w;
        this.f = k.x;
        this.g = k.A;
        this.h = k.z;
        this.i = k.g;
        this.j = k.y;
    }
    
    public k a(final o o, final k k, final r t) {
        if (this.l == null) {
            final Context g = o.g();
            if (this.i != null) {
                this.i.setClassLoader(g.getClassLoader());
            }
            this.l = k.a(g, this.a, this.i);
            if (this.k != null) {
                this.k.setClassLoader(g.getClassLoader());
                this.l.c = this.k;
            }
            this.l.a(this.b, k);
            this.l.m = this.c;
            this.l.o = true;
            this.l.v = this.d;
            this.l.w = this.e;
            this.l.x = this.f;
            this.l.A = this.g;
            this.l.z = this.h;
            this.l.y = this.j;
            this.l.q = o.d;
            if (q.a) {
                Log.v("FragmentManager", "Instantiated fragment " + this.l);
            }
        }
        this.l.t = t;
        return this.l;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        if (this.c) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        if (this.g) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.h) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeBundle(this.i);
        if (this.j) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeBundle(this.k);
    }
}
