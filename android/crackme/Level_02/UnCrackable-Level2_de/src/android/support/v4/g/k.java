// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater;
import android.annotation.TargetApi;

@TargetApi(9)
class k
{
    static n a(final LayoutInflater layoutInflater) {
        final LayoutInflater$Factory factory = layoutInflater.getFactory();
        if (factory instanceof a) {
            return ((a)factory).a;
        }
        return null;
    }
    
    static void a(final LayoutInflater layoutInflater, final n n) {
        Object factory;
        if (n != null) {
            factory = new a(n);
        }
        else {
            factory = null;
        }
        layoutInflater.setFactory((LayoutInflater$Factory)factory);
    }
    
    static class a implements LayoutInflater$Factory
    {
        final n a;
        
        a(final n a) {
            this.a = a;
        }
        
        public View onCreateView(final String s, final Context context, final AttributeSet set) {
            return this.a.a(null, s, context, set);
        }
        
        @Override
        public String toString() {
            return this.getClass().getName() + "{" + this.a + "}";
        }
    }
}
