// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.g;

import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater;
import android.annotation.TargetApi;

@TargetApi(21)
class m
{
    static void a(final LayoutInflater layoutInflater, final n n) {
        Object factory2;
        if (n != null) {
            factory2 = new l.a(n);
        }
        else {
            factory2 = null;
        }
        layoutInflater.setFactory2((LayoutInflater$Factory2)factory2);
    }
}
