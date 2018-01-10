// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.view.ViewTreeObserver;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.view.View$OnAttachStateChangeListener;

class ac implements View$OnAttachStateChangeListener, ViewTreeObserver$OnPreDrawListener
{
    private final View a;
    private ViewTreeObserver b;
    private final Runnable c;
    
    private ac(final View a, final Runnable c) {
        this.a = a;
        this.b = a.getViewTreeObserver();
        this.c = c;
    }
    
    public static ac a(final View view, final Runnable runnable) {
        final ac ac = new ac(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)ac);
        view.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)ac);
        return ac;
    }
    
    public void a() {
        if (this.b.isAlive()) {
            this.b.removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        }
        else {
            this.a.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        }
        this.a.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
    }
    
    public boolean onPreDraw() {
        this.a();
        this.c.run();
        return true;
    }
    
    public void onViewAttachedToWindow(final View view) {
        this.b = view.getViewTreeObserver();
    }
    
    public void onViewDetachedFromWindow(final View view) {
        this.a();
    }
}
