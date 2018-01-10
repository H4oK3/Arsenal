// 
// Decompiled by Procyon v0.5.30
// 

package android.support.a.a;

import android.graphics.drawable.VectorDrawable;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Paint$Style;
import android.graphics.PathMeasure;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;
import java.util.Stack;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;
import android.support.v4.b.a.d;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuffColorFilter;
import java.util.Collection;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.content.res.TypedArray;
import android.animation.AnimatorInflater;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.support.v4.f.a;
import android.os.Build$VERSION;
import android.animation.TypeEvaluator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import java.util.ArrayList;
import android.animation.Animator$AnimatorListener;
import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.drawable.Drawable$Callback;
import android.annotation.SuppressLint;

@SuppressLint({ "NewApi" })
public class c extends f implements android.support.a.a.b
{
    final Drawable$Callback a;
    private a c;
    private Context d;
    private ArgbEvaluator e;
    private Animator$AnimatorListener f;
    private ArrayList<Object> g;
    
    c() {
        this(null, null, null);
    }
    
    private c(final Context context) {
        this(context, null, null);
    }
    
    private c(final Context d, final a c, final Resources resources) {
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = (Drawable$Callback)new Drawable$Callback() {
            public void invalidateDrawable(final Drawable drawable) {
                android.support.a.a.c.this.invalidateSelf();
            }
            
            public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
                android.support.a.a.c.this.scheduleSelf(runnable, n);
            }
            
            public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
                android.support.a.a.c.this.unscheduleSelf(runnable);
            }
        };
        this.d = d;
        if (c != null) {
            this.c = c;
            return;
        }
        this.c = new a(d, c, this.a, resources);
    }
    
    public static c a(final Context context, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final c c = new c(context);
        c.inflate(resources, xmlPullParser, set, resources$Theme);
        return c;
    }
    
    private void a(final Animator animator) {
        if (animator instanceof AnimatorSet) {
            final ArrayList childAnimations = ((AnimatorSet)animator).getChildAnimations();
            if (childAnimations != null) {
                for (int i = 0; i < childAnimations.size(); ++i) {
                    this.a((Animator)childAnimations.get(i));
                }
            }
        }
        if (animator instanceof ObjectAnimator) {
            final ObjectAnimator objectAnimator = (ObjectAnimator)animator;
            final String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.e == null) {
                    this.e = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator((TypeEvaluator)this.e);
            }
        }
    }
    
    private void a(final String s, final Animator animator) {
        animator.setTarget(this.c.b.a(s));
        if (Build$VERSION.SDK_INT < 21) {
            this.a(animator);
        }
        if (this.c.e == null) {
            this.c.e = (ArrayList<Animator>)new ArrayList();
            this.c.d = new android.support.v4.f.a<Animator, String>();
        }
        this.c.e.add(animator);
        this.c.d.put(animator, s);
    }
    
    @Override
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, resources$Theme);
        }
    }
    
    public boolean canApplyTheme() {
        return this.b != null && android.support.v4.c.a.a.d(this.b);
    }
    
    public void draw(final Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
        }
        else {
            this.c.b.draw(canvas);
            if (this.c.c.isStarted()) {
                this.invalidateSelf();
            }
        }
    }
    
    public int getAlpha() {
        if (this.b != null) {
            return android.support.v4.c.a.a.c(this.b);
        }
        return this.c.b.getAlpha();
    }
    
    public int getChangingConfigurations() {
        if (this.b != null) {
            return this.b.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.c.a;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.b != null) {
            return new b(this.b.getConstantState());
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return this.c.b.getIntrinsicHeight();
    }
    
    public int getIntrinsicWidth() {
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return this.c.b.getIntrinsicWidth();
    }
    
    public int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return this.c.b.getOpacity();
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set) {
        this.inflate(resources, xmlPullParser, set, null);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, resources, xmlPullParser, set, resources$Theme);
            return;
        }
        for (int n = xmlPullParser.getEventType(), depth = xmlPullParser.getDepth(); n != 1 && (xmlPullParser.getDepth() >= depth + 1 || n != 3); n = xmlPullParser.next()) {
            if (n == 2) {
                final String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    final TypedArray a = android.support.a.a.f.a(resources, resources$Theme, set, android.support.a.a.a.e);
                    final int resourceId = a.getResourceId(0, 0);
                    if (resourceId != 0) {
                        final g a2 = android.support.a.a.g.a(resources, resourceId, resources$Theme);
                        a2.a(false);
                        a2.setCallback(this.a);
                        if (this.c.b != null) {
                            this.c.b.setCallback((Drawable$Callback)null);
                        }
                        this.c.b = a2;
                    }
                    a.recycle();
                }
                else if ("target".equals(name)) {
                    final TypedArray obtainAttributes = resources.obtainAttributes(set, android.support.a.a.a.f);
                    final String string = obtainAttributes.getString(0);
                    final int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        if (this.d == null) {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        this.a(string, AnimatorInflater.loadAnimator(this.d, resourceId2));
                    }
                    obtainAttributes.recycle();
                }
            }
        }
        this.c.a();
    }
    
    public boolean isAutoMirrored() {
        if (this.b != null) {
            return android.support.v4.c.a.a.b(this.b);
        }
        return this.c.b.isAutoMirrored();
    }
    
    public boolean isRunning() {
        if (this.b != null) {
            return ((AnimatedVectorDrawable)this.b).isRunning();
        }
        return this.c.c.isRunning();
    }
    
    public boolean isStateful() {
        if (this.b != null) {
            return this.b.isStateful();
        }
        return this.c.b.isStateful();
    }
    
    public Drawable mutate() {
        if (this.b != null) {
            this.b.mutate();
        }
        return this;
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        if (this.b != null) {
            this.b.setBounds(rect);
            return;
        }
        this.c.b.setBounds(rect);
    }
    
    @Override
    protected boolean onLevelChange(final int n) {
        if (this.b != null) {
            return this.b.setLevel(n);
        }
        return this.c.b.setLevel(n);
    }
    
    protected boolean onStateChange(final int[] array) {
        if (this.b != null) {
            return this.b.setState(array);
        }
        return this.c.b.setState(array);
    }
    
    public void setAlpha(final int n) {
        if (this.b != null) {
            this.b.setAlpha(n);
            return;
        }
        this.c.b.setAlpha(n);
    }
    
    public void setAutoMirrored(final boolean b) {
        if (this.b != null) {
            this.b.setAutoMirrored(b);
            return;
        }
        this.c.b.setAutoMirrored(b);
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
            return;
        }
        this.c.b.setColorFilter(colorFilter);
    }
    
    public void setTint(final int tint) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, tint);
            return;
        }
        this.c.b.setTint(tint);
    }
    
    public void setTintList(final ColorStateList tintList) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, tintList);
            return;
        }
        this.c.b.setTintList(tintList);
    }
    
    public void setTintMode(final PorterDuff$Mode tintMode) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, tintMode);
            return;
        }
        this.c.b.setTintMode(tintMode);
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        if (this.b != null) {
            return this.b.setVisible(b, b2);
        }
        this.c.b.setVisible(b, b2);
        return super.setVisible(b, b2);
    }
    
    public void start() {
        if (this.b != null) {
            ((AnimatedVectorDrawable)this.b).start();
        }
        else if (!this.c.c.isStarted()) {
            this.c.c.start();
            this.invalidateSelf();
        }
    }
    
    public void stop() {
        if (this.b != null) {
            ((AnimatedVectorDrawable)this.b).stop();
            return;
        }
        this.c.c.end();
    }
    
    private static class a extends Drawable$ConstantState
    {
        int a;
        g b;
        AnimatorSet c;
        android.support.v4.f.a<Animator, String> d;
        private ArrayList<Animator> e;
        
        public a(final Context context, final a a, final Drawable$Callback callback, final Resources resources) {
            int i = 0;
            if (a != null) {
                this.a = a.a;
                if (a.b != null) {
                    final Drawable$ConstantState constantState = a.b.getConstantState();
                    if (resources != null) {
                        this.b = (g)constantState.newDrawable(resources);
                    }
                    else {
                        this.b = (g)constantState.newDrawable();
                    }
                    (this.b = (g)this.b.mutate()).setCallback(callback);
                    this.b.setBounds(a.b.getBounds());
                    this.b.a(false);
                }
                if (a.e != null) {
                    final int size = a.e.size();
                    this.e = new ArrayList<Animator>(size);
                    this.d = new android.support.v4.f.a<Animator, String>(size);
                    while (i < size) {
                        final Animator animator = a.e.get(i);
                        final Animator clone = animator.clone();
                        final String s = a.d.get(animator);
                        clone.setTarget(this.b.a(s));
                        this.e.add(clone);
                        this.d.put(clone, s);
                        ++i;
                    }
                    this.a();
                }
            }
        }
        
        public void a() {
            if (this.c == null) {
                this.c = new AnimatorSet();
            }
            this.c.playTogether((Collection)this.e);
        }
        
        public int getChangingConfigurations() {
            return this.a;
        }
        
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
        
        public Drawable newDrawable(final Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }
    
    private static class b extends Drawable$ConstantState
    {
        private final Drawable$ConstantState a;
        
        public b(final Drawable$ConstantState a) {
            this.a = a;
        }
        
        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }
        
        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
        
        public Drawable newDrawable() {
            final c c = new c();
            (c.b = this.a.newDrawable()).setCallback(c.a);
            return c;
        }
        
        public Drawable newDrawable(final Resources resources) {
            final c c = new c();
            (c.b = this.a.newDrawable(resources)).setCallback(c.a);
            return c;
        }
        
        public Drawable newDrawable(final Resources resources, final Resources$Theme resources$Theme) {
            final c c = new c();
            (c.b = this.a.newDrawable(resources, resources$Theme)).setCallback(c.a);
            return c;
        }
    }
}
