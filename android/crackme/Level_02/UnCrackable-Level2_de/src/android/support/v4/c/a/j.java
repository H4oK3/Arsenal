// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(11)
class j extends i
{
    j(final Drawable drawable) {
        super(drawable);
    }
    
    j(final i.a a, final Resources resources) {
        super(a, resources);
    }
    
    @Override
    i.a b() {
        return new a(this.b, null);
    }
    
    public void jumpToCurrentState() {
        this.c.jumpToCurrentState();
    }
    
    private static class a extends i.a
    {
        a(final i.a a, final Resources resources) {
            super(a, resources);
        }
        
        @Override
        public Drawable newDrawable(final Resources resources) {
            return new j(this, resources);
        }
    }
}
