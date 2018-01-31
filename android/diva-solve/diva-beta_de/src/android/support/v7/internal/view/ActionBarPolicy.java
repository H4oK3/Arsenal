// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.view;

import android.support.v4.view.ViewConfigurationCompat;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.content.Context;

public class ActionBarPolicy
{
    private Context mContext;
    
    private ActionBarPolicy(final Context mContext) {
        this.mContext = mContext;
    }
    
    public static ActionBarPolicy get(final Context context) {
        return new ActionBarPolicy(context);
    }
    
    public boolean enableHomeButtonByDefault() {
        return this.mContext.getApplicationInfo().targetSdkVersion < 14;
    }
    
    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }
    
    public int getMaxActionButtons() {
        return this.mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
    }
    
    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }
    
    public int getTabContainerHeight() {
        final TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes((AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        final int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
        final Resources resources = this.mContext.getResources();
        int min = layoutDimension;
        if (!this.hasEmbeddedTabs()) {
            min = Math.min(layoutDimension, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return min;
    }
    
    public boolean hasEmbeddedTabs() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 16) {
            return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
        }
        return this.mContext.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
    }
    
    public boolean showsOverflowMenuButton() {
        return Build$VERSION.SDK_INT >= 19 || !ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext));
    }
}
