// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build$VERSION;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(21)
class l extends k
{
    l(final Drawable drawable) {
        super(drawable);
    }
    
    l(final i.a a, final Resources resources) {
        super(a, resources);
    }
    
    @Override
    i.a b() {
        return new a(this.b, null);
    }
    
    @Override
    protected boolean c() {
        boolean b = false;
        if (Build$VERSION.SDK_INT == 21) {
            final Drawable c = this.c;
            if (!(c instanceof GradientDrawable) && !(c instanceof DrawableContainer)) {
                b = b;
                if (!(c instanceof InsetDrawable)) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
    
    public Rect getDirtyBounds() {
        return this.c.getDirtyBounds();
    }
    
    public void getOutline(final Outline outline) {
        this.c.getOutline(outline);
    }
    
    public void setHotspot(final float n, final float n2) {
        this.c.setHotspot(n, n2);
    }
    
    public void setHotspotBounds(final int n, final int n2, final int n3, final int n4) {
        this.c.setHotspotBounds(n, n2, n3, n4);
    }
    
    @Override
    public boolean setState(final int[] state) {
        if (super.setState(state)) {
            this.invalidateSelf();
            return true;
        }
        return false;
    }
    
    @Override
    public void setTint(final int n) {
        if (this.c()) {
            super.setTint(n);
            return;
        }
        this.c.setTint(n);
    }
    
    @Override
    public void setTintList(final ColorStateList list) {
        if (this.c()) {
            super.setTintList(list);
            return;
        }
        this.c.setTintList(list);
    }
    
    @Override
    public void setTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.c()) {
            super.setTintMode(porterDuff$Mode);
            return;
        }
        this.c.setTintMode(porterDuff$Mode);
    }
    
    private static class a extends i.a
    {
        a(final i.a a, final Resources resources) {
            super(a, resources);
        }
        
        @Override
        public Drawable newDrawable(final Resources resources) {
            return new l(this, resources);
        }
    }
}
