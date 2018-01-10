// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;

@TargetApi(9)
class b extends Drawable
{
    final ActionBarContainer a;
    
    public b(final ActionBarContainer a) {
        this.a = a;
    }
    
    public void draw(final Canvas canvas) {
        if (this.a.d) {
            if (this.a.c != null) {
                this.a.c.draw(canvas);
            }
        }
        else {
            if (this.a.a != null) {
                this.a.a.draw(canvas);
            }
            if (this.a.b != null && this.a.e) {
                this.a.b.draw(canvas);
            }
        }
    }
    
    public int getOpacity() {
        return 0;
    }
    
    public void setAlpha(final int n) {
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
    }
}
