// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.AttributeSet;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.graphics.drawable.ClipDrawable;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.c.a.h;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.widget.ProgressBar;

class s
{
    private static final int[] a;
    private final ProgressBar b;
    private Bitmap c;
    
    static {
        a = new int[] { 16843067, 16843068 };
    }
    
    s(final ProgressBar b) {
        this.b = b;
    }
    
    private Drawable a(final Drawable drawable) {
        Object o = drawable;
        if (drawable instanceof AnimationDrawable) {
            final AnimationDrawable animationDrawable = (AnimationDrawable)drawable;
            final int numberOfFrames = animationDrawable.getNumberOfFrames();
            o = new AnimationDrawable();
            ((AnimationDrawable)o).setOneShot(animationDrawable.isOneShot());
            for (int i = 0; i < numberOfFrames; ++i) {
                final Drawable a = this.a(animationDrawable.getFrame(i), true);
                a.setLevel(10000);
                ((AnimationDrawable)o).addFrame(a, animationDrawable.getDuration(i));
            }
            ((AnimationDrawable)o).setLevel(10000);
        }
        return (Drawable)o;
    }
    
    private Drawable a(Drawable drawable, final boolean b) {
        final int n = 0;
        if (drawable instanceof h) {
            final Drawable a = ((h)drawable).a();
            if (a != null) {
                ((h)drawable).a(this.a(a, b));
            }
        }
        else if (drawable instanceof LayerDrawable) {
            final LayerDrawable layerDrawable = (LayerDrawable)drawable;
            final int numberOfLayers = layerDrawable.getNumberOfLayers();
            final Drawable[] array = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; ++i) {
                final int id = layerDrawable.getId(i);
                array[i] = this.a(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            final Object o = new LayerDrawable(array);
            int n2 = n;
            while (true) {
                drawable = (Drawable)o;
                if (n2 >= numberOfLayers) {
                    break;
                }
                ((LayerDrawable)o).setId(n2, layerDrawable.getId(n2));
                ++n2;
            }
        }
        else if (drawable instanceof BitmapDrawable) {
            final BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
            final Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.c == null) {
                this.c = bitmap;
            }
            final ShapeDrawable shapeDrawable = new ShapeDrawable(this.b());
            shapeDrawable.getPaint().setShader((Shader)new BitmapShader(bitmap, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            if (b) {
                return (Drawable)new ClipDrawable((Drawable)shapeDrawable, 3, 1);
            }
            return (Drawable)shapeDrawable;
        }
        return drawable;
    }
    
    private Shape b() {
        return (Shape)new RoundRectShape(new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f }, (RectF)null, (float[])null);
    }
    
    Bitmap a() {
        return this.c;
    }
    
    void a(final AttributeSet set, final int n) {
        final av a = av.a(this.b.getContext(), set, s.a, n, 0);
        final Drawable b = a.b(0);
        if (b != null) {
            this.b.setIndeterminateDrawable(this.a(b));
        }
        final Drawable b2 = a.b(1);
        if (b2 != null) {
            this.b.setProgressDrawable(this.a(b2, false));
        }
        a.a();
    }
}
