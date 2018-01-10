// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import android.os.Build$VERSION;

public final class b
{
    private static final c a;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            a = (c)new a();
            return;
        }
        if (sdk_INT >= 21) {
            a = (c)new d();
            return;
        }
        a = (c)new b();
    }
    
    public static Drawable a(final CompoundButton compoundButton) {
        return b.a.a(compoundButton);
    }
    
    public static void a(final CompoundButton compoundButton, final ColorStateList list) {
        b.a.a(compoundButton, list);
    }
    
    public static void a(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
        b.a.a(compoundButton, porterDuff$Mode);
    }
    
    static class a extends d
    {
        @Override
        public Drawable a(final CompoundButton compoundButton) {
            return android.support.v4.widget.c.a(compoundButton);
        }
    }
    
    static class b implements c
    {
        @Override
        public Drawable a(final CompoundButton compoundButton) {
            return android.support.v4.widget.d.a(compoundButton);
        }
        
        @Override
        public void a(final CompoundButton compoundButton, final ColorStateList list) {
            android.support.v4.widget.d.a(compoundButton, list);
        }
        
        @Override
        public void a(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
            android.support.v4.widget.d.a(compoundButton, porterDuff$Mode);
        }
    }
    
    interface c
    {
        Drawable a(final CompoundButton p0);
        
        void a(final CompoundButton p0, final ColorStateList p1);
        
        void a(final CompoundButton p0, final PorterDuff$Mode p1);
    }
    
    static class d extends b
    {
        @Override
        public void a(final CompoundButton compoundButton, final ColorStateList list) {
            e.a(compoundButton, list);
        }
        
        @Override
        public void a(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
            e.a(compoundButton, porterDuff$Mode);
        }
    }
}
