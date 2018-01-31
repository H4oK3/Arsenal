// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

public interface DrawableWrapper
{
    Drawable getWrappedDrawable();
    
    void setTint(final int p0);
    
    void setTintList(final ColorStateList p0);
    
    void setTintMode(final PorterDuff$Mode p0);
    
    void setWrappedDrawable(final Drawable p0);
}
