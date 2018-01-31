// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.support.v7.appcompat.R;
import android.support.annotation.StyleRes;
import android.content.Context;
import android.content.res.Resources$Theme;
import android.view.LayoutInflater;
import android.content.ContextWrapper;

public class ContextThemeWrapper extends ContextWrapper
{
    private LayoutInflater mInflater;
    private Resources$Theme mTheme;
    private int mThemeResource;
    
    public ContextThemeWrapper(final Context context, @StyleRes final int mThemeResource) {
        super(context);
        this.mThemeResource = mThemeResource;
    }
    
    public ContextThemeWrapper(final Context context, final Resources$Theme mTheme) {
        super(context);
        this.mTheme = mTheme;
    }
    
    private void initializeTheme() {
        final boolean b = this.mTheme == null;
        if (b) {
            this.mTheme = this.getResources().newTheme();
            final Resources$Theme theme = this.getBaseContext().getTheme();
            if (theme != null) {
                this.mTheme.setTo(theme);
            }
        }
        this.onApplyThemeResource(this.mTheme, this.mThemeResource, b);
    }
    
    public Object getSystemService(final String s) {
        if ("layout_inflater".equals(s)) {
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext((Context)this);
            }
            return this.mInflater;
        }
        return this.getBaseContext().getSystemService(s);
    }
    
    public Resources$Theme getTheme() {
        if (this.mTheme != null) {
            return this.mTheme;
        }
        if (this.mThemeResource == 0) {
            this.mThemeResource = R.style.Theme_AppCompat_Light;
        }
        this.initializeTheme();
        return this.mTheme;
    }
    
    public int getThemeResId() {
        return this.mThemeResource;
    }
    
    protected void onApplyThemeResource(final Resources$Theme resources$Theme, final int n, final boolean b) {
        resources$Theme.applyStyle(n, true);
    }
    
    public void setTheme(final int mThemeResource) {
        if (this.mThemeResource != mThemeResource) {
            this.mThemeResource = mThemeResource;
            this.initializeTheme();
        }
    }
}
