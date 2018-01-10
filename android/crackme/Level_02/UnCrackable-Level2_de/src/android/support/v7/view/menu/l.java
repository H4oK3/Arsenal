// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.view.View;
import android.view.MenuItem;
import android.support.v4.g.e;
import android.view.ActionProvider$VisibilityListener;
import android.view.ActionProvider;
import android.support.v4.d.a.b;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
class l extends k
{
    l(final Context context, final android.support.v4.d.a.b b) {
        super(context, b);
    }
    
    @Override
    k.a a(final ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
    
    class a extends k.a implements ActionProvider$VisibilityListener
    {
        e.b c;
        
        public a(final Context context, final ActionProvider actionProvider) {
            super(context, actionProvider);
        }
        
        public View a(final MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }
        
        public void a(final e.b c) {
            this.c = c;
            final ActionProvider a = this.a;
            Object visibilityListener;
            if (c != null) {
                visibilityListener = this;
            }
            else {
                visibilityListener = null;
            }
            a.setVisibilityListener((ActionProvider$VisibilityListener)visibilityListener);
        }
        
        public boolean b() {
            return this.a.overridesItemVisibility();
        }
        
        public boolean c() {
            return this.a.isVisible();
        }
        
        public void onActionProviderVisibilityChanged(final boolean b) {
            if (this.c != null) {
                this.c.a(b);
            }
        }
    }
}
