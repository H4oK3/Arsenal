// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.internal.view.SupportActionModeWrapper;
import android.view.ActionMode;
import android.view.ActionMode$Callback;
import android.view.Window$Callback;
import android.view.Window;
import android.content.Context;

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    private boolean mHandleNativeActionModes;
    
    AppCompatDelegateImplV14(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.mHandleNativeActionModes = true;
    }
    
    @Override
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }
    
    @Override
    public void setHandleNativeActionModesEnabled(final boolean mHandleNativeActionModes) {
        this.mHandleNativeActionModes = mHandleNativeActionModes;
    }
    
    @Override
    Window$Callback wrapWindowCallback(final Window$Callback window$Callback) {
        return (Window$Callback)new AppCompatWindowCallbackV14(window$Callback);
    }
    
    class AppCompatWindowCallbackV14 extends AppCompatWindowCallbackBase
    {
        AppCompatWindowCallbackV14(final Window$Callback window$Callback) {
            super(window$Callback);
        }
        
        @Override
        public ActionMode onWindowStartingActionMode(final ActionMode$Callback actionMode$Callback) {
            if (AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled()) {
                return this.startAsSupportActionMode(actionMode$Callback);
            }
            return super.onWindowStartingActionMode(actionMode$Callback);
        }
        
        final ActionMode startAsSupportActionMode(final ActionMode$Callback actionMode$Callback) {
            final SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, actionMode$Callback);
            final android.support.v7.view.ActionMode startSupportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }
    }
}
