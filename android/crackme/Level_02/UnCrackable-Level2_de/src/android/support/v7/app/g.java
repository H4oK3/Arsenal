// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.view.Menu;
import android.view.KeyboardShortcutGroup;
import java.util.List;
import android.view.Window$Callback;
import android.view.Window;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(24)
class g extends j
{
    g(final Context context, final Window window, final android.support.v7.app.d d) {
        super(context, window, d);
    }
    
    @Override
    Window$Callback a(final Window$Callback window$Callback) {
        return (Window$Callback)new a(window$Callback);
    }
    
    class a extends j.a
    {
        a(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public void onProvideKeyboardShortcuts(final List<KeyboardShortcutGroup> list, final Menu menu, final int n) {
            final d a = g.this.a(0, true);
            if (a != null && a.j != null) {
                super.onProvideKeyboardShortcuts(list, (Menu)a.j, n);
                return;
            }
            super.onProvideKeyboardShortcuts(list, menu, n);
        }
    }
}
