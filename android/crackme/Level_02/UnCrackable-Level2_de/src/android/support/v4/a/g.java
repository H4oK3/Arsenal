// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import java.util.List;
import android.util.Log;
import android.text.TextUtils;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class g implements Parcelable
{
    public static final Parcelable$Creator<g> CREATOR;
    final int[] a;
    final int b;
    final int c;
    final String d;
    final int e;
    final int f;
    final CharSequence g;
    final int h;
    final CharSequence i;
    final ArrayList<String> j;
    final ArrayList<String> k;
    final boolean l;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<g>() {
            public g a(final Parcel parcel) {
                return new g(parcel);
            }
            
            public g[] a(final int n) {
                return new g[n];
            }
        };
    }
    
    public g(final Parcel parcel) {
        this.a = parcel.createIntArray();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.h = parcel.readInt();
        this.i = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = (ArrayList<String>)parcel.createStringArrayList();
        this.k = (ArrayList<String>)parcel.createStringArrayList();
        this.l = (parcel.readInt() != 0);
    }
    
    public g(final f f) {
        final int size = f.c.size();
        this.a = new int[size * 6];
        if (!f.j) {
            throw new IllegalStateException("Not on back stack");
        }
        int i = 0;
        int n = 0;
        while (i < size) {
            final f.a a = f.c.get(i);
            final int[] a2 = this.a;
            final int n2 = n + 1;
            a2[n] = a.a;
            final int[] a3 = this.a;
            final int n3 = n2 + 1;
            int e;
            if (a.b != null) {
                e = a.b.e;
            }
            else {
                e = -1;
            }
            a3[n2] = e;
            final int[] a4 = this.a;
            final int n4 = n3 + 1;
            a4[n3] = a.c;
            final int[] a5 = this.a;
            final int n5 = n4 + 1;
            a5[n4] = a.d;
            final int[] a6 = this.a;
            final int n6 = n5 + 1;
            a6[n5] = a.e;
            final int[] a7 = this.a;
            n = n6 + 1;
            a7[n6] = a.f;
            ++i;
        }
        this.b = f.h;
        this.c = f.i;
        this.d = f.l;
        this.e = f.n;
        this.f = f.o;
        this.g = f.p;
        this.h = f.q;
        this.i = f.r;
        this.j = f.s;
        this.k = f.t;
        this.l = f.u;
    }
    
    public f a(final q q) {
        int i = 0;
        final f f = new f(q);
        int n = 0;
        while (i < this.a.length) {
            final f.a a = new f.a();
            final int[] a2 = this.a;
            final int n2 = i + 1;
            a.a = a2[i];
            if (q.a) {
                Log.v("FragmentManager", "Instantiate " + f + " op #" + n + " base fragment #" + this.a[n2]);
            }
            final int[] a3 = this.a;
            final int n3 = n2 + 1;
            final int n4 = a3[n2];
            if (n4 >= 0) {
                a.b = q.e.get(n4);
            }
            else {
                a.b = null;
            }
            final int[] a4 = this.a;
            final int n5 = n3 + 1;
            a.c = a4[n3];
            final int[] a5 = this.a;
            final int n6 = n5 + 1;
            a.d = a5[n5];
            final int[] a6 = this.a;
            final int n7 = n6 + 1;
            a.e = a6[n6];
            a.f = this.a[n7];
            f.d = a.c;
            f.e = a.d;
            f.f = a.e;
            f.g = a.f;
            f.a(a);
            ++n;
            i = n7 + 1;
        }
        f.h = this.b;
        f.i = this.c;
        f.l = this.d;
        f.n = this.e;
        f.j = true;
        f.o = this.f;
        f.p = this.g;
        f.q = this.h;
        f.r = this.i;
        f.s = this.j;
        f.t = this.k;
        f.u = this.l;
        f.a(1);
        return f;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        n = 0;
        parcel.writeIntArray(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        TextUtils.writeToParcel(this.g, parcel, 0);
        parcel.writeInt(this.h);
        TextUtils.writeToParcel(this.i, parcel, 0);
        parcel.writeStringList((List)this.j);
        parcel.writeStringList((List)this.k);
        if (this.l) {
            n = 1;
        }
        parcel.writeInt(n);
    }
}
