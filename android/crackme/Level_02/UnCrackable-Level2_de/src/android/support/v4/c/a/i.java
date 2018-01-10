// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.c.a;

import android.graphics.ColorFilter;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable$ConstantState;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff$Mode;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

@TargetApi(9)
class i extends Drawable implements Drawable$Callback, h, m
{
    static final PorterDuff$Mode a;
    a b;
    Drawable c;
    private int d;
    private PorterDuff$Mode e;
    private boolean f;
    private boolean g;
    
    static {
        a = PorterDuff$Mode.SRC_IN;
    }
    
    i(final Drawable drawable) {
        this.b = this.b();
        this.a(drawable);
    }
    
    i(final a b, final Resources resources) {
        this.b = b;
        this.a(resources);
    }
    
    private void a(final Resources resources) {
        if (this.b != null && this.b.b != null) {
            this.a(this.a(this.b.b, resources));
        }
    }
    
    private boolean a(final int[] array) {
        if (this.c()) {
            final ColorStateList c = this.b.c;
            final PorterDuff$Mode d = this.b.d;
            if (c == null || d == null) {
                this.f = false;
                this.clearColorFilter();
                return false;
            }
            final int colorForState = c.getColorForState(array, c.getDefaultColor());
            if (!this.f || colorForState != this.d || d != this.e) {
                this.setColorFilter(colorForState, d);
                this.d = colorForState;
                this.e = d;
                return this.f = true;
            }
        }
        return false;
    }
    
    public final Drawable a() {
        return this.c;
    }
    
    protected Drawable a(final Drawable$ConstantState drawable$ConstantState, final Resources resources) {
        return drawable$ConstantState.newDrawable(resources);
    }
    
    public final void a(final Drawable c) {
        if (this.c != null) {
            this.c.setCallback((Drawable$Callback)null);
        }
        if ((this.c = c) != null) {
            c.setCallback((Drawable$Callback)this);
            this.setVisible(c.isVisible(), true);
            this.setState(c.getState());
            this.setLevel(c.getLevel());
            this.setBounds(c.getBounds());
            if (this.b != null) {
                this.b.b = c.getConstantState();
            }
        }
        this.invalidateSelf();
    }
    
    a b() {
        return (a)new b(this.b, null);
    }
    
    protected boolean c() {
        return true;
    }
    
    public void draw(final Canvas canvas) {
        this.c.draw(canvas);
    }
    
    public int getChangingConfigurations() {
        final int changingConfigurations = super.getChangingConfigurations();
        int changingConfigurations2;
        if (this.b != null) {
            changingConfigurations2 = this.b.getChangingConfigurations();
        }
        else {
            changingConfigurations2 = 0;
        }
        return changingConfigurations2 | changingConfigurations | this.c.getChangingConfigurations();
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.b != null && this.b.a()) {
            this.b.a = this.getChangingConfigurations();
            return this.b;
        }
        return null;
    }
    
    public Drawable getCurrent() {
        return this.c.getCurrent();
    }
    
    public int getIntrinsicHeight() {
        return this.c.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        return this.c.getIntrinsicWidth();
    }
    
    public int getMinimumHeight() {
        return this.c.getMinimumHeight();
    }
    
    public int getMinimumWidth() {
        return this.c.getMinimumWidth();
    }
    
    public int getOpacity() {
        return this.c.getOpacity();
    }
    
    public boolean getPadding(final Rect rect) {
        return this.c.getPadding(rect);
    }
    
    public int[] getState() {
        return this.c.getState();
    }
    
    public Region getTransparentRegion() {
        return this.c.getTransparentRegion();
    }
    
    public void invalidateDrawable(final Drawable drawable) {
        this.invalidateSelf();
    }
    
    public boolean isStateful() {
        ColorStateList c;
        if (this.c() && this.b != null) {
            c = this.b.c;
        }
        else {
            c = null;
        }
        return (c != null && c.isStateful()) || this.c.isStateful();
    }
    
    public Drawable mutate() {
        if (!this.g && super.mutate() == this) {
            this.b = this.b();
            if (this.c != null) {
                this.c.mutate();
            }
            if (this.b != null) {
                final a b = this.b;
                Drawable$ConstantState constantState;
                if (this.c != null) {
                    constantState = this.c.getConstantState();
                }
                else {
                    constantState = null;
                }
                b.b = constantState;
            }
            this.g = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect bounds) {
        if (this.c != null) {
            this.c.setBounds(bounds);
        }
    }
    
    protected boolean onLevelChange(final int level) {
        return this.c.setLevel(level);
    }
    
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        this.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int alpha) {
        this.c.setAlpha(alpha);
    }
    
    public void setChangingConfigurations(final int changingConfigurations) {
        this.c.setChangingConfigurations(changingConfigurations);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
    }
    
    public void setDither(final boolean dither) {
        this.c.setDither(dither);
    }
    
    public void setFilterBitmap(final boolean filterBitmap) {
        this.c.setFilterBitmap(filterBitmap);
    }
    
    public boolean setState(final int[] state) {
        final boolean setState = this.c.setState(state);
        return this.a(state) || setState;
    }
    
    public void setTint(final int n) {
        this.setTintList(ColorStateList.valueOf(n));
    }
    
    public void setTintList(final ColorStateList c) {
        this.b.c = c;
        this.a(this.getState());
    }
    
    public void setTintMode(final PorterDuff$Mode d) {
        this.b.d = d;
        this.a(this.getState());
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        return super.setVisible(b, b2) || this.c.setVisible(b, b2);
    }
    
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        this.unscheduleSelf(runnable);
    }
    
    protected abstract static class a extends Drawable$ConstantState
    {
        int a;
        Drawable$ConstantState b;
        ColorStateList c;
        PorterDuff$Mode d;
        
        a(final a a, final Resources resources) {
            this.c = null;
            this.d = i.a;
            if (a != null) {
                this.a = a.a;
                this.b = a.b;
                this.c = a.c;
                this.d = a.d;
            }
        }
        
        boolean a() {
            return this.b != null;
        }
        
        public int getChangingConfigurations() {
            final int a = this.a;
            int changingConfigurations;
            if (this.b != null) {
                changingConfigurations = this.b.getChangingConfigurations();
            }
            else {
                changingConfigurations = 0;
            }
            return changingConfigurations | a;
        }
        
        public Drawable newDrawable() {
            return this.newDrawable(null);
        }
        
        public abstract Drawable newDrawable(final Resources p0);
    }
    
    private static class b extends a
    {
        b(final a a, final Resources resources) {
            super(a, resources);
        }
        
        @Override
        public Drawable newDrawable(final Resources resources) {
            return new i((a)this, resources);
        }
    }
}
