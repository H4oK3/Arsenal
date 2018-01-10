// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class an
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    
    an() {
        this.a = 0;
        this.b = 0;
        this.c = Integer.MIN_VALUE;
        this.d = Integer.MIN_VALUE;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = false;
    }
    
    public int a() {
        return this.a;
    }
    
    public void a(final int a, final int b) {
        this.c = a;
        this.d = b;
        this.h = true;
        if (this.g) {
            if (b != Integer.MIN_VALUE) {
                this.a = b;
            }
            if (a != Integer.MIN_VALUE) {
                this.b = a;
            }
        }
        else {
            if (a != Integer.MIN_VALUE) {
                this.a = a;
            }
            if (b != Integer.MIN_VALUE) {
                this.b = b;
            }
        }
    }
    
    public void a(final boolean g) {
        if (g == this.g) {
            return;
        }
        this.g = g;
        if (!this.h) {
            this.a = this.e;
            this.b = this.f;
            return;
        }
        if (g) {
            int a;
            if (this.d != Integer.MIN_VALUE) {
                a = this.d;
            }
            else {
                a = this.e;
            }
            this.a = a;
            int b;
            if (this.c != Integer.MIN_VALUE) {
                b = this.c;
            }
            else {
                b = this.f;
            }
            this.b = b;
            return;
        }
        int a2;
        if (this.c != Integer.MIN_VALUE) {
            a2 = this.c;
        }
        else {
            a2 = this.e;
        }
        this.a = a2;
        int b2;
        if (this.d != Integer.MIN_VALUE) {
            b2 = this.d;
        }
        else {
            b2 = this.f;
        }
        this.b = b2;
    }
    
    public int b() {
        return this.b;
    }
    
    public void b(final int n, final int n2) {
        this.h = false;
        if (n != Integer.MIN_VALUE) {
            this.e = n;
            this.a = n;
        }
        if (n2 != Integer.MIN_VALUE) {
            this.f = n2;
            this.b = n2;
        }
    }
    
    public int c() {
        if (this.g) {
            return this.b;
        }
        return this.a;
    }
    
    public int d() {
        if (this.g) {
            return this.a;
        }
        return this.b;
    }
}
