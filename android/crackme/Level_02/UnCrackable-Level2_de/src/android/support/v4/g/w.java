// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.ViewParent;
import android.view.View;

public class w
{
    private final View a;
    private ViewParent b;
    private boolean c;
    private int[] d;
    
    public w(final View a) {
        this.a = a;
    }
    
    public void a(final boolean c) {
        if (this.c) {
            ae.p(this.a);
        }
        this.c = c;
    }
    
    public boolean a() {
        return this.c;
    }
    
    public boolean a(final float n, final float n2) {
        return this.a() && this.b != null && aq.a(this.b, this.a, n, n2);
    }
    
    public boolean a(final float n, final float n2, final boolean b) {
        return this.a() && this.b != null && aq.a(this.b, this.a, n, n2, b);
    }
    
    public boolean a(final int n) {
        if (this.b()) {
            return true;
        }
        if (this.a()) {
            ViewParent b = this.a.getParent();
            View a = this.a;
            while (b != null) {
                if (aq.a(b, a, this.a, n)) {
                    aq.b(this.b = b, a, this.a, n);
                    return true;
                }
                if (b instanceof View) {
                    a = (View)b;
                }
                b = b.getParent();
            }
        }
        return false;
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4, final int[] array) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.a()) {
            b2 = b;
            if (this.b != null) {
                if (n != 0 || n2 != 0 || n3 != 0 || n4 != 0) {
                    int n5;
                    int n6;
                    if (array != null) {
                        this.a.getLocationInWindow(array);
                        n5 = array[0];
                        n6 = array[1];
                    }
                    else {
                        n6 = 0;
                        n5 = 0;
                    }
                    aq.a(this.b, this.a, n, n2, n3, n4);
                    if (array != null) {
                        this.a.getLocationInWindow(array);
                        array[0] -= n5;
                        array[1] -= n6;
                    }
                    b2 = true;
                }
                else {
                    b2 = b;
                    if (array != null) {
                        array[1] = (array[0] = 0);
                        return false;
                    }
                }
            }
        }
        return b2;
    }
    
    public boolean a(final int n, final int n2, final int[] array, final int[] array2) {
        boolean b2;
        final boolean b = b2 = false;
        if (this.a()) {
            b2 = b;
            if (this.b != null) {
                if (n != 0 || n2 != 0) {
                    int n3;
                    int n4;
                    if (array2 != null) {
                        this.a.getLocationInWindow(array2);
                        n3 = array2[0];
                        n4 = array2[1];
                    }
                    else {
                        n4 = 0;
                        n3 = 0;
                    }
                    int[] d = array;
                    if (array == null) {
                        if (this.d == null) {
                            this.d = new int[2];
                        }
                        d = this.d;
                    }
                    d[1] = (d[0] = 0);
                    aq.a(this.b, this.a, n, n2, d);
                    if (array2 != null) {
                        this.a.getLocationInWindow(array2);
                        array2[0] -= n3;
                        array2[1] -= n4;
                    }
                    if (d[0] == 0) {
                        b2 = b;
                        if (d[1] == 0) {
                            return b2;
                        }
                    }
                    b2 = true;
                }
                else {
                    b2 = b;
                    if (array2 != null) {
                        array2[1] = (array2[0] = 0);
                        return false;
                    }
                }
            }
        }
        return b2;
    }
    
    public boolean b() {
        return this.b != null;
    }
    
    public void c() {
        if (this.b != null) {
            aq.a(this.b, this.a);
            this.b = null;
        }
    }
}
