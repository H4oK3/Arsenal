// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent$Callback;
import android.view.KeyEvent;

class KeyEventCompatEclair
{
    public static boolean dispatch(final KeyEvent keyEvent, final KeyEvent$Callback keyEvent$Callback, final Object o, final Object o2) {
        return keyEvent.dispatch(keyEvent$Callback, (KeyEvent$DispatcherState)o, o2);
    }
    
    public static Object getKeyDispatcherState(final View view) {
        return view.getKeyDispatcherState();
    }
    
    public static boolean isTracking(final KeyEvent keyEvent) {
        return keyEvent.isTracking();
    }
    
    public static void startTracking(final KeyEvent keyEvent) {
        keyEvent.startTracking();
    }
}
