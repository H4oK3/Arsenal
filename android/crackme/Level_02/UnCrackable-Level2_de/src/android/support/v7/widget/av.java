// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.support.v7.b.a.b;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.content.Context;

public class av
{
    private final Context a;
    private final TypedArray b;
    
    private av(final Context a, final TypedArray b) {
        this.a = a;
        this.b = b;
    }
    
    public static av a(final Context context, final int n, final int[] array) {
        return new av(context, context.obtainStyledAttributes(n, array));
    }
    
    public static av a(final Context context, final AttributeSet set, final int[] array) {
        return new av(context, context.obtainStyledAttributes(set, array));
    }
    
    public static av a(final Context context, final AttributeSet set, final int[] array, final int n, final int n2) {
        return new av(context, context.obtainStyledAttributes(set, array, n, n2));
    }
    
    public float a(final int n, final float n2) {
        return this.b.getFloat(n, n2);
    }
    
    public int a(final int n, final int n2) {
        return this.b.getInt(n, n2);
    }
    
    public Drawable a(final int n) {
        if (this.b.hasValue(n)) {
            final int resourceId = this.b.getResourceId(n, 0);
            if (resourceId != 0) {
                return android.support.v7.b.a.b.b(this.a, resourceId);
            }
        }
        return this.b.getDrawable(n);
    }
    
    public void a() {
        this.b.recycle();
    }
    
    public boolean a(final int n, final boolean b) {
        return this.b.getBoolean(n, b);
    }
    
    public int b(final int n, final int n2) {
        return this.b.getColor(n, n2);
    }
    
    public Drawable b(int resourceId) {
        if (this.b.hasValue(resourceId)) {
            resourceId = this.b.getResourceId(resourceId, 0);
            if (resourceId != 0) {
                return l.a().a(this.a, resourceId, true);
            }
        }
        return null;
    }
    
    public int c(final int n, final int n2) {
        return this.b.getInteger(n, n2);
    }
    
    public CharSequence c(final int n) {
        return this.b.getText(n);
    }
    
    public int d(final int n, final int n2) {
        return this.b.getDimensionPixelOffset(n, n2);
    }
    
    public String d(final int n) {
        return this.b.getString(n);
    }
    
    public int e(final int n, final int n2) {
        return this.b.getDimensionPixelSize(n, n2);
    }
    
    public ColorStateList e(final int n) {
        if (this.b.hasValue(n)) {
            final int resourceId = this.b.getResourceId(n, 0);
            if (resourceId != 0) {
                final ColorStateList a = android.support.v7.b.a.b.a(this.a, resourceId);
                if (a != null) {
                    return a;
                }
            }
        }
        return this.b.getColorStateList(n);
    }
    
    public int f(final int n, final int n2) {
        return this.b.getLayoutDimension(n, n2);
    }
    
    public CharSequence[] f(final int n) {
        return this.b.getTextArray(n);
    }
    
    public int g(final int n, final int n2) {
        return this.b.getResourceId(n, n2);
    }
    
    public boolean g(final int n) {
        return this.b.hasValue(n);
    }
}
