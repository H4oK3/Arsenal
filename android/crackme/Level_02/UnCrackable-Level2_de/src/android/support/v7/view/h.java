// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import java.util.Iterator;
import android.view.View;
import android.support.v4.g.ax;
import android.view.animation.Interpolator;
import android.support.v4.g.aw;
import android.support.v4.g.as;
import java.util.ArrayList;

public class h
{
    final ArrayList<as> a;
    aw b;
    private long c;
    private Interpolator d;
    private boolean e;
    private final ax f;
    
    public h() {
        this.c = -1L;
        this.f = new ax() {
            private boolean b = false;
            private int c = 0;
            
            void a() {
                this.c = 0;
                this.b = false;
                h.this.b();
            }
            
            @Override
            public void a(final View view) {
                if (!this.b) {
                    this.b = true;
                    if (h.this.b != null) {
                        h.this.b.a(null);
                    }
                }
            }
            
            @Override
            public void b(final View view) {
                final int c = this.c + 1;
                this.c = c;
                if (c == h.this.a.size()) {
                    if (h.this.b != null) {
                        h.this.b.b(null);
                    }
                    this.a();
                }
            }
        };
        this.a = new ArrayList<as>();
    }
    
    public h a(final long c) {
        if (!this.e) {
            this.c = c;
        }
        return this;
    }
    
    public h a(final as as) {
        if (!this.e) {
            this.a.add(as);
        }
        return this;
    }
    
    public h a(final as as, final as as2) {
        this.a.add(as);
        as2.b(as.a());
        this.a.add(as2);
        return this;
    }
    
    public h a(final aw b) {
        if (!this.e) {
            this.b = b;
        }
        return this;
    }
    
    public h a(final Interpolator d) {
        if (!this.e) {
            this.d = d;
        }
        return this;
    }
    
    public void a() {
        if (this.e) {
            return;
        }
        for (final as as : this.a) {
            if (this.c >= 0L) {
                as.a(this.c);
            }
            if (this.d != null) {
                as.a(this.d);
            }
            if (this.b != null) {
                as.a(this.f);
            }
            as.c();
        }
        this.e = true;
    }
    
    void b() {
        this.e = false;
    }
    
    public void c() {
        if (!this.e) {
            return;
        }
        final Iterator<as> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().b();
        }
        this.e = false;
    }
}
