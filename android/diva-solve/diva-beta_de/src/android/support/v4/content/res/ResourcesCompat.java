// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.content.res.Resources$NotFoundException;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.content.res.Resources$Theme;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.content.res.Resources;

public class ResourcesCompat
{
    @Nullable
    public static Drawable getDrawable(@NonNull final Resources resources, @DrawableRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        if (Build$VERSION.SDK_INT >= 21) {
            return ResourcesCompatApi21.getDrawable(resources, n, resources$Theme);
        }
        return resources.getDrawable(n);
    }
    
    @Nullable
    public static Drawable getDrawableForDensity(@NonNull final Resources resources, @DrawableRes final int n, final int n2, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        if (Build$VERSION.SDK_INT >= 21) {
            return ResourcesCompatApi21.getDrawableForDensity(resources, n, n2, resources$Theme);
        }
        if (Build$VERSION.SDK_INT >= 15) {
            return ResourcesCompatIcsMr1.getDrawableForDensity(resources, n, n2);
        }
        return resources.getDrawable(n);
    }
    
    @ColorInt
    public int getColor(@NonNull final Resources resources, @ColorRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        if (Build$VERSION.SDK_INT >= 23) {
            return ResourcesCompatApi23.getColor(resources, n, resources$Theme);
        }
        return resources.getColor(n);
    }
    
    @Nullable
    public ColorStateList getColorStateList(@NonNull final Resources resources, @ColorRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        if (Build$VERSION.SDK_INT >= 23) {
            return ResourcesCompatApi23.getColorStateList(resources, n, resources$Theme);
        }
        return resources.getColorStateList(n);
    }
}
