// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.graphics.Paint;
import java.util.WeakHashMap;
import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import android.view.View;
import java.lang.ref.WeakReference;

public final class as
{
    static final g d;
    Runnable a;
    Runnable b;
    int c;
    private WeakReference<View> e;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            d = (g)new f();
            return;
        }
        if (sdk_INT >= 19) {
            d = (g)new e();
            return;
        }
        if (sdk_INT >= 18) {
            d = (g)new c();
            return;
        }
        if (sdk_INT >= 16) {
            d = (g)new d();
            return;
        }
        if (sdk_INT >= 14) {
            d = (g)new b();
            return;
        }
        d = (g)new a();
    }
    
    as(final View view) {
        this.a = null;
        this.b = null;
        this.c = -1;
        this.e = new WeakReference<View>(view);
    }
    
    public long a() {
        final View view = this.e.get();
        if (view != null) {
            return as.d.a(this, view);
        }
        return 0L;
    }
    
    public as a(final float n) {
        final View view = this.e.get();
        if (view != null) {
            as.d.a(this, view, n);
        }
        return this;
    }
    
    public as a(final long n) {
        final View view = this.e.get();
        if (view != null) {
            as.d.a(this, view, n);
        }
        return this;
    }
    
    public as a(final aw aw) {
        final View view = this.e.get();
        if (view != null) {
            as.d.a(this, view, aw);
        }
        return this;
    }
    
    public as a(final ay ay) {
        final View view = this.e.get();
        if (view != null) {
            as.d.a(this, view, ay);
        }
        return this;
    }
    
    public as a(final Interpolator interpolator) {
        final View view = this.e.get();
        if (view != null) {
            as.d.a(this, view, interpolator);
        }
        return this;
    }
    
    public as b(final float n) {
        final View view = this.e.get();
        if (view != null) {
            as.d.b(this, view, n);
        }
        return this;
    }
    
    public as b(final long n) {
        final View view = this.e.get();
        if (view != null) {
            as.d.b(this, view, n);
        }
        return this;
    }
    
    public void b() {
        final View view = this.e.get();
        if (view != null) {
            as.d.b(this, view);
        }
    }
    
    public void c() {
        final View view = this.e.get();
        if (view != null) {
            as.d.c(this, view);
        }
    }
    
    static class a implements g
    {
        WeakHashMap<View, Runnable> a;
        
        a() {
            this.a = null;
        }
        
        private void a(final View view) {
            if (this.a != null) {
                final Runnable runnable = this.a.get(view);
                if (runnable != null) {
                    view.removeCallbacks(runnable);
                }
            }
        }
        
        private void e(final as as, final View view) {
            Runnable runnable = null;
            if (this.a != null) {
                runnable = this.a.get(view);
            }
            Runnable runnable2;
            if ((runnable2 = runnable) == null) {
                runnable2 = new as.a.a(as, view);
                if (this.a == null) {
                    this.a = new WeakHashMap<View, Runnable>();
                }
                this.a.put(view, runnable2);
            }
            view.removeCallbacks(runnable2);
            view.post(runnable2);
        }
        
        @Override
        public long a(final as as, final View view) {
            return 0L;
        }
        
        @Override
        public void a(final as as, final View view, final float n) {
            this.e(as, view);
        }
        
        @Override
        public void a(final as as, final View view, final long n) {
        }
        
        @Override
        public void a(final as as, final View view, final aw aw) {
            view.setTag(2113929216, (Object)aw);
        }
        
        @Override
        public void a(final as as, final View view, final ay ay) {
        }
        
        @Override
        public void a(final as as, final View view, final Interpolator interpolator) {
        }
        
        @Override
        public void b(final as as, final View view) {
            this.e(as, view);
        }
        
        @Override
        public void b(final as as, final View view, final float n) {
            this.e(as, view);
        }
        
        @Override
        public void b(final as as, final View view, final long n) {
        }
        
        @Override
        public void c(final as as, final View view) {
            this.a(view);
            this.d(as, view);
        }
        
        void d(final as as, final View view) {
            final Object tag = view.getTag(2113929216);
            aw aw;
            if (tag instanceof aw) {
                aw = (aw)tag;
            }
            else {
                aw = null;
            }
            final Runnable a = as.a;
            final Runnable b = as.b;
            as.a = null;
            as.b = null;
            if (a != null) {
                a.run();
            }
            if (aw != null) {
                aw.a(view);
                aw.b(view);
            }
            if (b != null) {
                b.run();
            }
            if (this.a != null) {
                this.a.remove(view);
            }
        }
        
        class a implements Runnable
        {
            WeakReference<View> a;
            as b;
            
            a(final as b, final View view) {
                this.a = new WeakReference<View>(view);
                this.b = b;
            }
            
            @Override
            public void run() {
                final View view = this.a.get();
                if (view != null) {
                    as.a.this.d(this.b, view);
                }
            }
        }
    }
    
    static class b extends as.a
    {
        WeakHashMap<View, Integer> b;
        
        b() {
            this.b = null;
        }
        
        @Override
        public long a(final as as, final View view) {
            return at.a(view);
        }
        
        @Override
        public void a(final as as, final View view, final float n) {
            at.a(view, n);
        }
        
        @Override
        public void a(final as as, final View view, final long n) {
            at.a(view, n);
        }
        
        @Override
        public void a(final as as, final View view, final aw aw) {
            view.setTag(2113929216, (Object)aw);
            at.a(view, new a(as));
        }
        
        @Override
        public void a(final as as, final View view, final Interpolator interpolator) {
            at.a(view, interpolator);
        }
        
        @Override
        public void b(final as as, final View view) {
            at.b(view);
        }
        
        @Override
        public void b(final as as, final View view, final float n) {
            at.b(view, n);
        }
        
        @Override
        public void b(final as as, final View view, final long n) {
            at.b(view, n);
        }
        
        @Override
        public void c(final as as, final View view) {
            at.c(view);
        }
        
        static class a implements aw
        {
            as a;
            boolean b;
            
            a(final as a) {
                this.a = a;
            }
            
            @Override
            public void a(final View view) {
                this.b = false;
                if (this.a.c >= 0) {
                    ae.a(view, 2, null);
                }
                if (this.a.a != null) {
                    final Runnable a = this.a.a;
                    this.a.a = null;
                    a.run();
                }
                final Object tag = view.getTag(2113929216);
                aw aw;
                if (tag instanceof aw) {
                    aw = (aw)tag;
                }
                else {
                    aw = null;
                }
                if (aw != null) {
                    aw.a(view);
                }
            }
            
            @Override
            public void b(final View view) {
                if (this.a.c >= 0) {
                    ae.a(view, this.a.c, null);
                    this.a.c = -1;
                }
                if (Build$VERSION.SDK_INT >= 16 || !this.b) {
                    if (this.a.b != null) {
                        final Runnable b = this.a.b;
                        this.a.b = null;
                        b.run();
                    }
                    final Object tag = view.getTag(2113929216);
                    aw aw;
                    if (tag instanceof aw) {
                        aw = (aw)tag;
                    }
                    else {
                        aw = null;
                    }
                    if (aw != null) {
                        aw.b(view);
                    }
                    this.b = true;
                }
            }
            
            @Override
            public void c(final View view) {
                final Object tag = view.getTag(2113929216);
                aw aw;
                if (tag instanceof aw) {
                    aw = (aw)tag;
                }
                else {
                    aw = null;
                }
                if (aw != null) {
                    aw.c(view);
                }
            }
        }
    }
    
    static class c extends d
    {
    }
    
    static class d extends b
    {
        @Override
        public void a(final as as, final View view, final aw aw) {
            au.a(view, aw);
        }
    }
    
    static class e extends c
    {
        @Override
        public void a(final as as, final View view, final ay ay) {
            av.a(view, ay);
        }
    }
    
    static class f extends e
    {
    }
    
    interface g
    {
        long a(final as p0, final View p1);
        
        void a(final as p0, final View p1, final float p2);
        
        void a(final as p0, final View p1, final long p2);
        
        void a(final as p0, final View p1, final aw p2);
        
        void a(final as p0, final View p1, final ay p2);
        
        void a(final as p0, final View p1, final Interpolator p2);
        
        void b(final as p0, final View p1);
        
        void b(final as p0, final View p1, final float p2);
        
        void b(final as p0, final View p1, final long p2);
        
        void c(final as p0, final View p1);
    }
}
