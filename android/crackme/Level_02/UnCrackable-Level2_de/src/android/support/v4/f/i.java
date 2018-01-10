// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.f;

public class i<F, S>
{
    public final F a;
    public final S b;
    
    private static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof i) {
            final i i = (i)o;
            if (a(i.a, this.a) && a(i.b, this.b)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.a.hashCode();
        }
        if (this.b != null) {
            hashCode = this.b.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
    
    @Override
    public String toString() {
        return "Pair{" + String.valueOf(this.a) + " " + String.valueOf(this.b) + "}";
    }
}
