// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v4.g.ao;
import android.view.ViewConfiguration;
import android.os.Build$VERSION;
import android.content.res.Resources;
import android.content.Context;

public class a
{
    private Context a;
    
    private a(final Context a) {
        this.a = a;
    }
    
    public static a a(final Context context) {
        return new a(context);
    }
    
    public int a() {
        final Resources resources = this.a.getResources();
        final int b = android.support.v4.b.a.a.b(resources);
        final int a = android.support.v4.b.a.a.a(resources);
        if (android.support.v4.b.a.a.c(resources) > 600 || b > 600 || (b > 960 && a > 720) || (b > 720 && a > 960)) {
            return 5;
        }
        if (b >= 500 || (b > 640 && a > 480) || (b > 480 && a > 640)) {
            return 4;
        }
        if (b >= 360) {
            return 3;
        }
        return 2;
    }
    
    public boolean b() {
        return Build$VERSION.SDK_INT >= 19 || !ao.a(ViewConfiguration.get(this.a));
    }
    
    public int c() {
        return this.a.getResources().getDisplayMetrics().widthPixels / 2;
    }
    
    public boolean d() {
        return this.a.getResources().getBoolean(android.support.v7.a.a.b.abc_action_bar_embed_tabs);
    }
    
    public int e() {
        final TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes((AttributeSet)null, android.support.v7.a.a.j.ActionBar, android.support.v7.a.a.a.actionBarStyle, 0);
        final int layoutDimension = obtainStyledAttributes.getLayoutDimension(android.support.v7.a.a.j.ActionBar_height, 0);
        final Resources resources = this.a.getResources();
        int min = layoutDimension;
        if (!this.d()) {
            min = Math.min(layoutDimension, resources.getDimensionPixelSize(android.support.v7.a.a.d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return min;
    }
    
    public boolean f() {
        return this.a.getApplicationInfo().targetSdkVersion < 14;
    }
    
    public int g() {
        return this.a.getResources().getDimensionPixelSize(android.support.v7.a.a.d.abc_action_bar_stacked_tab_max_width);
    }
}
