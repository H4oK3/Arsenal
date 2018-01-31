// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.annotation.LayoutRes;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.MenuInflater;
import android.util.AttributeSet;
import android.support.annotation.NonNull;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.os.Build$VERSION;
import android.view.Window;
import android.app.Dialog;
import android.content.Context;
import android.app.Activity;

public abstract class AppCompatDelegate
{
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    static final String TAG = "AppCompatDelegate";
    
    public static AppCompatDelegate create(final Activity activity, final AppCompatCallback appCompatCallback) {
        return create((Context)activity, activity.getWindow(), appCompatCallback);
    }
    
    public static AppCompatDelegate create(final Dialog dialog, final AppCompatCallback appCompatCallback) {
        return create(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }
    
    private static AppCompatDelegate create(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            return new AppCompatDelegateImplV23(context, window, appCompatCallback);
        }
        if (sdk_INT >= 14) {
            return new AppCompatDelegateImplV14(context, window, appCompatCallback);
        }
        if (sdk_INT >= 11) {
            return new AppCompatDelegateImplV11(context, window, appCompatCallback);
        }
        return new AppCompatDelegateImplV7(context, window, appCompatCallback);
    }
    
    public abstract void addContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract View createView(final View p0, final String p1, @NonNull final Context p2, @NonNull final AttributeSet p3);
    
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
    
    public abstract MenuInflater getMenuInflater();
    
    public abstract ActionBar getSupportActionBar();
    
    public abstract boolean hasWindowFeature(final int p0);
    
    public abstract void installViewFactory();
    
    public abstract void invalidateOptionsMenu();
    
    public abstract boolean isHandleNativeActionModesEnabled();
    
    public abstract void onConfigurationChanged(final Configuration p0);
    
    public abstract void onCreate(final Bundle p0);
    
    public abstract void onDestroy();
    
    public abstract void onPostCreate(final Bundle p0);
    
    public abstract void onPostResume();
    
    public abstract void onStop();
    
    public abstract boolean requestWindowFeature(final int p0);
    
    public abstract void setContentView(@LayoutRes final int p0);
    
    public abstract void setContentView(final View p0);
    
    public abstract void setContentView(final View p0, final ViewGroup$LayoutParams p1);
    
    public abstract void setHandleNativeActionModesEnabled(final boolean p0);
    
    public abstract void setSupportActionBar(final Toolbar p0);
    
    public abstract void setTitle(final CharSequence p0);
    
    public abstract ActionMode startSupportActionMode(final ActionMode.Callback p0);
}
