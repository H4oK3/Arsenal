// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

class p
{
    private static p d;
    public long a;
    public long b;
    public int c;
    
    static p a() {
        if (p.d == null) {
            p.d = new p();
        }
        return p.d;
    }
    
    public void a(final long n, double sin, double n2) {
        final float n3 = (n - 946728000000L) / 8.64E7f;
        final float n4 = 6.24006f + 0.01720197f * n3;
        final double n5 = n4 + 0.03341960161924362 * Math.sin(n4) + 3.4906598739326E-4 * Math.sin(2.0f * n4) + 5.236000106378924E-6 * Math.sin(3.0f * n4) + 1.796593063 + 3.141592653589793;
        n2 = -n2 / 360.0;
        n2 = Math.sin(n4) * 0.0053 + (n2 + (Math.round(n3 - 9.0E-4f - n2) + 9.0E-4f)) + -0.0069 * Math.sin(2.0 * n5);
        final double asin = Math.asin(Math.sin(n5) * Math.sin(0.4092797040939331));
        final double n6 = 0.01745329238474369 * sin;
        sin = Math.sin(-0.10471975803375244);
        sin = (sin - Math.sin(n6) * Math.sin(asin)) / (Math.cos(asin) * Math.cos(n6));
        if (sin >= 1.0) {
            this.c = 1;
            this.a = -1L;
            this.b = -1L;
            return;
        }
        if (sin <= -1.0) {
            this.c = 0;
            this.a = -1L;
            this.b = -1L;
            return;
        }
        final float n7 = (float)(Math.acos(sin) / 6.283185307179586);
        this.a = Math.round((n7 + n2) * 8.64E7) + 946728000000L;
        this.b = Math.round((n2 - n7) * 8.64E7) + 946728000000L;
        if (this.b < n && this.a > n) {
            this.c = 0;
            return;
        }
        this.c = 1;
    }
}
