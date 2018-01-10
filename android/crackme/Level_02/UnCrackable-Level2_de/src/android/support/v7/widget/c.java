// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Outline;
import android.annotation.TargetApi;

@TargetApi(21)
class c extends b
{
    public c(final ActionBarContainer actionBarContainer) {
        super(actionBarContainer);
    }
    
    public void getOutline(final Outline outline) {
        if (this.a.d) {
            if (this.a.c != null) {
                this.a.c.getOutline(outline);
            }
        }
        else if (this.a.a != null) {
            this.a.a.getOutline(outline);
        }
    }
}
