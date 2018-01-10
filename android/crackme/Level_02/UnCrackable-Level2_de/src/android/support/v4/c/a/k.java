// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.annotation.TargetApi;

@TargetApi(19)
class k extends j
{
    k(final Drawable drawable) {
        super(drawable);
    }
    
    k(final i.a a, final Resources resources) {
        super(a, resources);
    }
    
    @Override
    i.a b() {
        return new a(this.b, null);
    }
    
    public boolean isAutoMirrored() {
        return this.c.isAutoMirrored();
    }
    
    public void setAutoMirrored(final boolean autoMirrored) {
        this.c.setAutoMirrored(autoMirrored);
    }
    
    private static class a extends i.a
    {
        a(final i.a a, final Resources resources) {
            super(a, resources);
        }
        
        @Override
        public Drawable newDrawable(final Resources resources) {
            return new k(this, resources);
        }
    }
}
