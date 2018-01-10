// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.Window$Callback;
import android.view.Window;
import android.content.Context;
import android.app.UiModeManager;
import android.annotation.TargetApi;

@TargetApi(23)
class j extends i
{
    private final UiModeManager t;
    
    j(final Context context, final Window window, final android.support.v7.app.d d) {
        super(context, window, d);
        this.t = (UiModeManager)context.getSystemService("uimode");
    }
    
    @Override
    Window$Callback a(final Window$Callback window$Callback) {
        return (Window$Callback)new a(window$Callback);
    }
    
    @Override
    int d(final int n) {
        if (n == 0 && this.t.getNightMode() == 0) {
            return -1;
        }
        return super.d(n);
    }
    
    class a extends android.support.v7.app.i.a
    {
        a(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
            return null;
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback, final int n) {
            if (j.this.o()) {
                switch (n) {
                    case 0: {
                        return ((android.support.v7.app.i.a)this).a(actionMode$Callback);
                    }
                }
            }
            return super.onWindowStartingActionMode(actionMode$Callback, n);
        }
    }
}
