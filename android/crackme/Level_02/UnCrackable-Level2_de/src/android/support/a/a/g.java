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
import java.util.ArrayList;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.support.v4.c.a.a;
import java.util.Stack;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.annotation.SuppressLint;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.v4.b.a.d;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff$Mode;

public class g extends android.support.a.a.f
{
    static final PorterDuff$Mode a;
    private f c;
    private PorterDuffColorFilter d;
    private ColorFilter e;
    private boolean f;
    private boolean g;
    private Drawable$ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;
    
    static {
        a = PorterDuff$Mode.SRC_IN;
    }
    
    g() {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = new f();
    }
    
    g(final f c) {
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = c;
        this.d = this.a(this.d, c.c, c.d);
    }
    
    static int a(final int n, final float n2) {
        return (int)(Color.alpha(n) * n2) << 24 | (0xFFFFFF & n);
    }
    
    private static PorterDuff$Mode a(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
            case 16: {
                if (Build$VERSION.SDK_INT >= 11) {
                    return PorterDuff$Mode.ADD;
                }
                break;
            }
        }
        return porterDuff$Mode;
    }
    
    @SuppressLint({ "NewApi" })
    public static g a(final Resources resources, int next, final Resources$Theme resources$Theme) {
        if (Build$VERSION.SDK_INT >= 24) {
            final g g = new g();
            g.b = android.support.v4.b.a.d.a(resources, next, resources$Theme);
            g.h = new g(g.b.getConstantState());
            return g;
        }
        try {
            final XmlResourceParser xml = resources.getXml(next);
            Xml.asAttributeSet((XmlPullParser)xml);
            do {
                next = ((XmlPullParser)xml).next();
            } while (next != 2 && next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            goto Label_0102;
        }
        catch (XmlPullParserException ex) {
            Log.e("VectorDrawableCompat", "parser error", (Throwable)ex);
        }
        catch (IOException ex2) {
            Log.e("VectorDrawableCompat", "parser error", (Throwable)ex2);
            goto Label_0100;
        }
    }
    
    @SuppressLint({ "NewApi" })
    public static g a(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final g g = new g();
        g.inflate(resources, xmlPullParser, set, resources$Theme);
        return g;
    }
    
    private void a(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
        final f c = this.c;
        final e b = c.b;
        c.d = a(android.support.a.a.e.a(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff$Mode.SRC_IN);
        final ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            c.c = colorStateList;
        }
        c.e = android.support.a.a.e.a(typedArray, xmlPullParser, "autoMirrored", 5, c.e);
        b.d = android.support.a.a.e.a(typedArray, xmlPullParser, "viewportWidth", 7, b.d);
        b.e = android.support.a.a.e.a(typedArray, xmlPullParser, "viewportHeight", 8, b.e);
        if (b.d <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (b.e <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        b.b = typedArray.getDimension(3, b.b);
        b.c = typedArray.getDimension(2, b.c);
        if (b.b <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (b.c <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        b.setAlpha(android.support.a.a.e.a(typedArray, xmlPullParser, "alpha", 4, b.getAlpha()));
        final String string = typedArray.getString(0);
        if (string != null) {
            b.g = string;
            b.h.put(string, b);
        }
    }
    
    @SuppressLint({ "NewApi" })
    private boolean a() {
        boolean b = true;
        if (Build$VERSION.SDK_INT < 17) {
            return false;
        }
        if (!this.isAutoMirrored() || this.getLayoutDirection() != 1) {
            b = false;
        }
        return b;
    }
    
    private void b(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final f c = this.c;
        final e b = c.b;
        final Stack<c> stack = new Stack<c>();
        stack.push(b.a);
        int n = xmlPullParser.getEventType();
        final int depth = xmlPullParser.getDepth();
        int n2 = 1;
        while (n != 1 && (xmlPullParser.getDepth() >= depth + 1 || n != 3)) {
            int n3;
            if (n == 2) {
                final String name = xmlPullParser.getName();
                final c c2 = stack.peek();
                if ("path".equals(name)) {
                    final b b2 = new b();
                    b2.a(resources, set, resources$Theme, xmlPullParser);
                    c2.a.add(b2);
                    if (((d)b2).getPathName() != null) {
                        b.h.put(((d)b2).getPathName(), b2);
                    }
                    n2 = 0;
                    c.a |= b2.o;
                }
                else if ("clip-path".equals(name)) {
                    final a a = new a();
                    a.a(resources, set, resources$Theme, xmlPullParser);
                    c2.a.add(a);
                    if (((d)a).getPathName() != null) {
                        b.h.put(((d)a).getPathName(), a);
                    }
                    c.a |= a.o;
                }
                else if ("group".equals(name)) {
                    final c c3 = new c();
                    c3.a(resources, set, resources$Theme, xmlPullParser);
                    c2.a.add(c3);
                    stack.push(c3);
                    if (c3.getGroupName() != null) {
                        b.h.put(c3.getGroupName(), c3);
                    }
                    c.a |= c3.c;
                }
                n3 = n2;
            }
            else {
                n3 = n2;
                if (n == 3) {
                    n3 = n2;
                    if ("group".equals(xmlPullParser.getName())) {
                        stack.pop();
                        n3 = n2;
                    }
                }
            }
            n = xmlPullParser.next();
            n2 = n3;
        }
        if (n2 != 0) {
            final StringBuffer sb = new StringBuffer();
            if (sb.length() > 0) {
                sb.append(" or ");
            }
            sb.append("path");
            throw new XmlPullParserException("no " + (Object)sb + " defined");
        }
    }
    
    PorterDuffColorFilter a(final PorterDuffColorFilter porterDuffColorFilter, final ColorStateList list, final PorterDuff$Mode porterDuff$Mode) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(list.getColorForState(this.getState(), 0), porterDuff$Mode);
    }
    
    Object a(final String s) {
        return this.c.b.h.get(s);
    }
    
    void a(final boolean g) {
        this.g = g;
    }
    
    public boolean canApplyTheme() {
        if (this.b != null) {
            android.support.v4.c.a.a.d(this.b);
        }
        return false;
    }
    
    public void draw(final Canvas canvas) {
        if (this.b != null) {
            this.b.draw(canvas);
        }
        else {
            this.copyBounds(this.k);
            if (this.k.width() > 0 && this.k.height() > 0) {
                Object o;
                if (this.e == null) {
                    o = this.d;
                }
                else {
                    o = this.e;
                }
                canvas.getMatrix(this.j);
                this.j.getValues(this.i);
                float abs = Math.abs(this.i[0]);
                float abs2 = Math.abs(this.i[4]);
                final float abs3 = Math.abs(this.i[1]);
                final float abs4 = Math.abs(this.i[3]);
                if (abs3 != 0.0f || abs4 != 0.0f) {
                    abs2 = 1.0f;
                    abs = 1.0f;
                }
                final int n = (int)(abs * this.k.width());
                final int n2 = (int)(abs2 * this.k.height());
                final int min = Math.min(2048, n);
                final int min2 = Math.min(2048, n2);
                if (min > 0 && min2 > 0) {
                    final int save = canvas.save();
                    canvas.translate((float)this.k.left, (float)this.k.top);
                    if (this.a()) {
                        canvas.translate((float)this.k.width(), 0.0f);
                        canvas.scale(-1.0f, 1.0f);
                    }
                    this.k.offsetTo(0, 0);
                    this.c.b(min, min2);
                    if (!this.g) {
                        this.c.a(min, min2);
                    }
                    else if (!this.c.b()) {
                        this.c.a(min, min2);
                        this.c.c();
                    }
                    this.c.a(canvas, (ColorFilter)o, this.k);
                    canvas.restoreToCount(save);
                }
            }
        }
    }
    
    public int getAlpha() {
        if (this.b != null) {
            return android.support.v4.c.a.a.c(this.b);
        }
        return this.c.b.getRootAlpha();
    }
    
    public int getChangingConfigurations() {
        if (this.b != null) {
            return this.b.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.c.getChangingConfigurations();
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.b != null) {
            return new g(this.b.getConstantState());
        }
        this.c.a = this.getChangingConfigurations();
        return this.c;
    }
    
    public int getIntrinsicHeight() {
        if (this.b != null) {
            return this.b.getIntrinsicHeight();
        }
        return (int)this.c.b.c;
    }
    
    public int getIntrinsicWidth() {
        if (this.b != null) {
            return this.b.getIntrinsicWidth();
        }
        return (int)this.c.b.b;
    }
    
    public int getOpacity() {
        if (this.b != null) {
            return this.b.getOpacity();
        }
        return -3;
    }
    
    @SuppressLint({ "NewApi" })
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set) {
        if (this.b != null) {
            this.b.inflate(resources, xmlPullParser, set);
            return;
        }
        this.inflate(resources, xmlPullParser, set, null);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, resources, xmlPullParser, set, resources$Theme);
            return;
        }
        final f c = this.c;
        c.b = new e();
        final TypedArray a = android.support.a.a.f.a(resources, resources$Theme, set, android.support.a.a.a.a);
        this.a(a, xmlPullParser);
        a.recycle();
        c.a = this.getChangingConfigurations();
        c.k = true;
        this.b(resources, xmlPullParser, set, resources$Theme);
        this.d = this.a(this.d, c.c, c.d);
    }
    
    public void invalidateSelf() {
        if (this.b != null) {
            this.b.invalidateSelf();
            return;
        }
        super.invalidateSelf();
    }
    
    public boolean isAutoMirrored() {
        if (this.b != null) {
            return android.support.v4.c.a.a.b(this.b);
        }
        return this.c.e;
    }
    
    public boolean isStateful() {
        if (this.b != null) {
            return this.b.isStateful();
        }
        return super.isStateful() || (this.c != null && this.c.c != null && this.c.c.isStateful());
    }
    
    public Drawable mutate() {
        if (this.b != null) {
            this.b.mutate();
        }
        else if (!this.f && super.mutate() == this) {
            this.c = new f(this.c);
            this.f = true;
            return this;
        }
        return this;
    }
    
    @Override
    protected void onBoundsChange(final Rect bounds) {
        if (this.b != null) {
            this.b.setBounds(bounds);
        }
    }
    
    protected boolean onStateChange(final int[] state) {
        if (this.b != null) {
            return this.b.setState(state);
        }
        final f c = this.c;
        if (c.c != null && c.d != null) {
            this.d = this.a(this.d, c.c, c.d);
            this.invalidateSelf();
            return true;
        }
        return false;
    }
    
    public void scheduleSelf(final Runnable runnable, final long n) {
        if (this.b != null) {
            this.b.scheduleSelf(runnable, n);
            return;
        }
        super.scheduleSelf(runnable, n);
    }
    
    public void setAlpha(final int n) {
        if (this.b != null) {
            this.b.setAlpha(n);
        }
        else if (this.c.b.getRootAlpha() != n) {
            this.c.b.setRootAlpha(n);
            this.invalidateSelf();
        }
    }
    
    public void setAutoMirrored(final boolean e) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, e);
            return;
        }
        this.c.e = e;
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (this.b != null) {
            this.b.setColorFilter(colorFilter);
            return;
        }
        this.e = colorFilter;
        this.invalidateSelf();
    }
    
    @SuppressLint({ "NewApi" })
    public void setTint(final int n) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, n);
            return;
        }
        this.setTintList(ColorStateList.valueOf(n));
    }
    
    public void setTintList(final ColorStateList c) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, c);
        }
        else {
            final f c2 = this.c;
            if (c2.c != c) {
                c2.c = c;
                this.d = this.a(this.d, c, c2.d);
                this.invalidateSelf();
            }
        }
    }
    
    public void setTintMode(final PorterDuff$Mode d) {
        if (this.b != null) {
            android.support.v4.c.a.a.a(this.b, d);
        }
        else {
            final f c = this.c;
            if (c.d != d) {
                c.d = d;
                this.d = this.a(this.d, c.c, d);
                this.invalidateSelf();
            }
        }
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        if (this.b != null) {
            return this.b.setVisible(b, b2);
        }
        return super.setVisible(b, b2);
    }
    
    public void unscheduleSelf(final Runnable runnable) {
        if (this.b != null) {
            this.b.unscheduleSelf(runnable);
            return;
        }
        super.unscheduleSelf(runnable);
    }
    
    private static class a extends d
    {
        public a() {
        }
        
        public a(final a a) {
            super((d)a);
        }
        
        private void a(final TypedArray typedArray) {
            final String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            final String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.m = d.a(string2);
            }
        }
        
        public void a(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
            if (!android.support.a.a.e.a(xmlPullParser, "pathData")) {
                return;
            }
            final TypedArray a = android.support.a.a.f.a(resources, resources$Theme, set, android.support.a.a.a.d);
            this.a(a);
            a.recycle();
        }
        
        @Override
        public boolean a() {
            return true;
        }
    }
    
    private static class b extends d
    {
        int a;
        float b;
        int c;
        float d;
        int e;
        float f;
        float g;
        float h;
        float i;
        Paint$Cap j;
        Paint$Join k;
        float l;
        private int[] p;
        
        public b() {
            this.a = 0;
            this.b = 0.0f;
            this.c = 0;
            this.d = 1.0f;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Paint$Cap.BUTT;
            this.k = Paint$Join.MITER;
            this.l = 4.0f;
        }
        
        public b(final b b) {
            super((d)b);
            this.a = 0;
            this.b = 0.0f;
            this.c = 0;
            this.d = 1.0f;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Paint$Cap.BUTT;
            this.k = Paint$Join.MITER;
            this.l = 4.0f;
            this.p = b.p;
            this.a = b.a;
            this.b = b.b;
            this.d = b.d;
            this.c = b.c;
            this.e = b.e;
            this.f = b.f;
            this.g = b.g;
            this.h = b.h;
            this.i = b.i;
            this.j = b.j;
            this.k = b.k;
            this.l = b.l;
        }
        
        private Paint$Cap a(final int n, final Paint$Cap paint$Cap) {
            switch (n) {
                default: {
                    return paint$Cap;
                }
                case 0: {
                    return Paint$Cap.BUTT;
                }
                case 1: {
                    return Paint$Cap.ROUND;
                }
                case 2: {
                    return Paint$Cap.SQUARE;
                }
            }
        }
        
        private Paint$Join a(final int n, final Paint$Join paint$Join) {
            switch (n) {
                default: {
                    return paint$Join;
                }
                case 0: {
                    return Paint$Join.MITER;
                }
                case 1: {
                    return Paint$Join.ROUND;
                }
                case 2: {
                    return Paint$Join.BEVEL;
                }
            }
        }
        
        private void a(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
            this.p = null;
            if (!android.support.a.a.e.a(xmlPullParser, "pathData")) {
                return;
            }
            final String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            final String string2 = typedArray.getString(2);
            if (string2 != null) {
                this.m = android.support.a.a.d.a(string2);
            }
            this.c = android.support.a.a.e.b(typedArray, xmlPullParser, "fillColor", 1, this.c);
            this.f = android.support.a.a.e.a(typedArray, xmlPullParser, "fillAlpha", 12, this.f);
            this.j = this.a(android.support.a.a.e.a(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.j);
            this.k = this.a(android.support.a.a.e.a(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.k);
            this.l = android.support.a.a.e.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.l);
            this.a = android.support.a.a.e.b(typedArray, xmlPullParser, "strokeColor", 3, this.a);
            this.d = android.support.a.a.e.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.d);
            this.b = android.support.a.a.e.a(typedArray, xmlPullParser, "strokeWidth", 4, this.b);
            this.h = android.support.a.a.e.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.h);
            this.i = android.support.a.a.e.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.i);
            this.g = android.support.a.a.e.a(typedArray, xmlPullParser, "trimPathStart", 5, this.g);
        }
        
        public void a(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
            final TypedArray a = android.support.a.a.f.a(resources, resources$Theme, set, android.support.a.a.a.c);
            this.a(a, xmlPullParser);
            a.recycle();
        }
        
        float getFillAlpha() {
            return this.f;
        }
        
        int getFillColor() {
            return this.c;
        }
        
        float getStrokeAlpha() {
            return this.d;
        }
        
        int getStrokeColor() {
            return this.a;
        }
        
        float getStrokeWidth() {
            return this.b;
        }
        
        float getTrimPathEnd() {
            return this.h;
        }
        
        float getTrimPathOffset() {
            return this.i;
        }
        
        float getTrimPathStart() {
            return this.g;
        }
        
        void setFillAlpha(final float f) {
            this.f = f;
        }
        
        void setFillColor(final int c) {
            this.c = c;
        }
        
        void setStrokeAlpha(final float d) {
            this.d = d;
        }
        
        void setStrokeColor(final int a) {
            this.a = a;
        }
        
        void setStrokeWidth(final float b) {
            this.b = b;
        }
        
        void setTrimPathEnd(final float h) {
            this.h = h;
        }
        
        void setTrimPathOffset(final float i) {
            this.i = i;
        }
        
        void setTrimPathStart(final float g) {
            this.g = g;
        }
    }
    
    private static class c
    {
        final ArrayList<Object> a;
        float b;
        int c;
        private final Matrix d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        private float j;
        private final Matrix k;
        private int[] l;
        private String m;
        
        public c() {
            this.d = new Matrix();
            this.a = new ArrayList<Object>();
            this.b = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = new Matrix();
            this.m = null;
        }
        
        public c(c c, final android.support.v4.f.a<String, Object> a) {
            this.d = new Matrix();
            this.a = new ArrayList<Object>();
            this.b = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 1.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = new Matrix();
            this.m = null;
            this.b = c.b;
            this.e = c.e;
            this.f = c.f;
            this.g = c.g;
            this.h = c.h;
            this.i = c.i;
            this.j = c.j;
            this.l = c.l;
            this.m = c.m;
            this.c = c.c;
            if (this.m != null) {
                a.put(this.m, this);
            }
            this.k.set(c.k);
            final ArrayList<Object> a2 = c.a;
            for (int i = 0; i < a2.size(); ++i) {
                final c value = a2.get(i);
                if (value instanceof c) {
                    c = value;
                    this.a.add(new c(c, a));
                }
                else {
                    d d;
                    if (value instanceof b) {
                        d = new b((b)value);
                    }
                    else {
                        if (!(value instanceof a)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        d = new a((a)value);
                    }
                    this.a.add(d);
                    if (d.n != null) {
                        a.put(d.n, d);
                    }
                }
            }
        }
        
        private void a() {
            this.k.reset();
            this.k.postTranslate(-this.e, -this.f);
            this.k.postScale(this.g, this.h);
            this.k.postRotate(this.b, 0.0f, 0.0f);
            this.k.postTranslate(this.i + this.e, this.j + this.f);
        }
        
        private void a(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
            this.l = null;
            this.b = android.support.a.a.e.a(typedArray, xmlPullParser, "rotation", 5, this.b);
            this.e = typedArray.getFloat(1, this.e);
            this.f = typedArray.getFloat(2, this.f);
            this.g = android.support.a.a.e.a(typedArray, xmlPullParser, "scaleX", 3, this.g);
            this.h = android.support.a.a.e.a(typedArray, xmlPullParser, "scaleY", 4, this.h);
            this.i = android.support.a.a.e.a(typedArray, xmlPullParser, "translateX", 6, this.i);
            this.j = android.support.a.a.e.a(typedArray, xmlPullParser, "translateY", 7, this.j);
            final String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            this.a();
        }
        
        public void a(final Resources resources, final AttributeSet set, final Resources$Theme resources$Theme, final XmlPullParser xmlPullParser) {
            final TypedArray a = android.support.a.a.f.a(resources, resources$Theme, set, android.support.a.a.a.b);
            this.a(a, xmlPullParser);
            a.recycle();
        }
        
        public String getGroupName() {
            return this.m;
        }
        
        public Matrix getLocalMatrix() {
            return this.k;
        }
        
        public float getPivotX() {
            return this.e;
        }
        
        public float getPivotY() {
            return this.f;
        }
        
        public float getRotation() {
            return this.b;
        }
        
        public float getScaleX() {
            return this.g;
        }
        
        public float getScaleY() {
            return this.h;
        }
        
        public float getTranslateX() {
            return this.i;
        }
        
        public float getTranslateY() {
            return this.j;
        }
        
        public void setPivotX(final float e) {
            if (e != this.e) {
                this.e = e;
                this.a();
            }
        }
        
        public void setPivotY(final float f) {
            if (f != this.f) {
                this.f = f;
                this.a();
            }
        }
        
        public void setRotation(final float b) {
            if (b != this.b) {
                this.b = b;
                this.a();
            }
        }
        
        public void setScaleX(final float g) {
            if (g != this.g) {
                this.g = g;
                this.a();
            }
        }
        
        public void setScaleY(final float h) {
            if (h != this.h) {
                this.h = h;
                this.a();
            }
        }
        
        public void setTranslateX(final float i) {
            if (i != this.i) {
                this.i = i;
                this.a();
            }
        }
        
        public void setTranslateY(final float j) {
            if (j != this.j) {
                this.j = j;
                this.a();
            }
        }
    }
    
    private static class d
    {
        protected android.support.a.a.d.b[] m;
        String n;
        int o;
        
        public d() {
            this.m = null;
        }
        
        public d(final d d) {
            this.m = null;
            this.n = d.n;
            this.o = d.o;
            this.m = android.support.a.a.d.a(d.m);
        }
        
        public void a(final Path path) {
            path.reset();
            if (this.m != null) {
                android.support.a.a.d.b.a(this.m, path);
            }
        }
        
        public boolean a() {
            return false;
        }
        
        public android.support.a.a.d.b[] getPathData() {
            return this.m;
        }
        
        public String getPathName() {
            return this.n;
        }
        
        public void setPathData(final android.support.a.a.d.b[] array) {
            if (!android.support.a.a.d.a(this.m, array)) {
                this.m = android.support.a.a.d.a(array);
                return;
            }
            android.support.a.a.d.b(this.m, array);
        }
    }
    
    private static class e
    {
        private static final Matrix k;
        final c a;
        float b;
        float c;
        float d;
        float e;
        int f;
        String g;
        final android.support.v4.f.a<String, Object> h;
        private final Path i;
        private final Path j;
        private final Matrix l;
        private Paint m;
        private Paint n;
        private PathMeasure o;
        private int p;
        
        static {
            k = new Matrix();
        }
        
        public e() {
            this.l = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 255;
            this.g = null;
            this.h = new android.support.v4.f.a<String, Object>();
            this.a = new c();
            this.i = new Path();
            this.j = new Path();
        }
        
        public e(final e e) {
            this.l = new Matrix();
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 255;
            this.g = null;
            this.h = new android.support.v4.f.a<String, Object>();
            this.a = new c(e.a, this.h);
            this.i = new Path(e.i);
            this.j = new Path(e.j);
            this.b = e.b;
            this.c = e.c;
            this.d = e.d;
            this.e = e.e;
            this.p = e.p;
            this.f = e.f;
            this.g = e.g;
            if (e.g != null) {
                this.h.put(e.g, this);
            }
        }
        
        private static float a(final float n, final float n2, final float n3, final float n4) {
            return n * n4 - n2 * n3;
        }
        
        private float a(final Matrix matrix) {
            float n = 0.0f;
            final float[] array2;
            final float[] array = array2 = new float[4];
            array2[0] = 0.0f;
            array2[2] = (array2[1] = 1.0f);
            array2[3] = 0.0f;
            matrix.mapVectors(array);
            final float n2 = (float)Math.hypot(array[0], array[1]);
            final float n3 = (float)Math.hypot(array[2], array[3]);
            final float a = a(array[0], array[1], array[2], array[3]);
            final float max = Math.max(n2, n3);
            if (max > 0.0f) {
                n = Math.abs(a) / max;
            }
            return n;
        }
        
        private void a(final c c, final Matrix matrix, final Canvas canvas, final int n, final int n2, final ColorFilter colorFilter) {
            c.d.set(matrix);
            c.d.preConcat(c.k);
            canvas.save();
            for (int i = 0; i < c.a.size(); ++i) {
                final Object value = c.a.get(i);
                if (value instanceof c) {
                    this.a((c)value, c.d, canvas, n, n2, colorFilter);
                }
                else if (value instanceof d) {
                    this.a(c, (d)value, canvas, n, n2, colorFilter);
                }
            }
            canvas.restore();
        }
        
        private void a(final c c, final d d, final Canvas canvas, final int n, final int n2, final ColorFilter colorFilter) {
            final float n3 = n / this.d;
            final float n4 = n2 / this.e;
            final float min = Math.min(n3, n4);
            final Matrix a = c.d;
            this.l.set(a);
            this.l.postScale(n3, n4);
            final float a2 = this.a(a);
            if (a2 != 0.0f) {
                d.a(this.i);
                final Path i = this.i;
                this.j.reset();
                if (d.a()) {
                    this.j.addPath(i, this.l);
                    canvas.clipPath(this.j);
                    return;
                }
                final b b = (b)d;
                if (b.g != 0.0f || b.h != 1.0f) {
                    final float g = b.g;
                    final float j = b.i;
                    final float h = b.h;
                    final float k = b.i;
                    if (this.o == null) {
                        this.o = new PathMeasure();
                    }
                    this.o.setPath(this.i, false);
                    final float length = this.o.getLength();
                    final float n5 = (g + j) % 1.0f * length;
                    final float n6 = (h + k) % 1.0f * length;
                    i.reset();
                    if (n5 > n6) {
                        this.o.getSegment(n5, length, i, true);
                        this.o.getSegment(0.0f, n6, i, true);
                    }
                    else {
                        this.o.getSegment(n5, n6, i, true);
                    }
                    i.rLineTo(0.0f, 0.0f);
                }
                this.j.addPath(i, this.l);
                if (b.c != 0) {
                    if (this.n == null) {
                        (this.n = new Paint()).setStyle(Paint$Style.FILL);
                        this.n.setAntiAlias(true);
                    }
                    final Paint n7 = this.n;
                    n7.setColor(android.support.a.a.g.a(b.c, b.f));
                    n7.setColorFilter(colorFilter);
                    canvas.drawPath(this.j, n7);
                }
                if (b.a != 0) {
                    if (this.m == null) {
                        (this.m = new Paint()).setStyle(Paint$Style.STROKE);
                        this.m.setAntiAlias(true);
                    }
                    final Paint m = this.m;
                    if (b.k != null) {
                        m.setStrokeJoin(b.k);
                    }
                    if (b.j != null) {
                        m.setStrokeCap(b.j);
                    }
                    m.setStrokeMiter(b.l);
                    m.setColor(android.support.a.a.g.a(b.a, b.d));
                    m.setColorFilter(colorFilter);
                    m.setStrokeWidth(a2 * min * b.b);
                    canvas.drawPath(this.j, m);
                }
            }
        }
        
        public void a(final Canvas canvas, final int n, final int n2, final ColorFilter colorFilter) {
            this.a(this.a, android.support.a.a.g.e.k, canvas, n, n2, colorFilter);
        }
        
        public float getAlpha() {
            return this.getRootAlpha() / 255.0f;
        }
        
        public int getRootAlpha() {
            return this.f;
        }
        
        public void setAlpha(final float n) {
            this.setRootAlpha((int)(255.0f * n));
        }
        
        public void setRootAlpha(final int f) {
            this.f = f;
        }
    }
    
    private static class f extends Drawable$ConstantState
    {
        int a;
        e b;
        ColorStateList c;
        PorterDuff$Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff$Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;
        
        public f() {
            this.c = null;
            this.d = g.a;
            this.b = new e();
        }
        
        public f(final f f) {
            this.c = null;
            this.d = g.a;
            if (f != null) {
                this.a = f.a;
                this.b = new e(f.b);
                if (f.b.n != null) {
                    this.b.n = new Paint(f.b.n);
                }
                if (f.b.m != null) {
                    this.b.m = new Paint(f.b.m);
                }
                this.c = f.c;
                this.d = f.d;
                this.e = f.e;
            }
        }
        
        public Paint a(final ColorFilter colorFilter) {
            if (!this.a() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                (this.l = new Paint()).setFilterBitmap(true);
            }
            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }
        
        public void a(final int n, final int n2) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), n, n2, null);
        }
        
        public void a(final Canvas canvas, final ColorFilter colorFilter, final Rect rect) {
            canvas.drawBitmap(this.f, (Rect)null, rect, this.a(colorFilter));
        }
        
        public boolean a() {
            return this.b.getRootAlpha() < 255;
        }
        
        public void b(final int n, final int n2) {
            if (this.f == null || !this.c(n, n2)) {
                this.f = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
                this.k = true;
            }
        }
        
        public boolean b() {
            return !this.k && this.g == this.c && this.h == this.d && this.j == this.e && this.i == this.b.getRootAlpha();
        }
        
        public void c() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }
        
        public boolean c(final int n, final int n2) {
            return n == this.f.getWidth() && n2 == this.f.getHeight();
        }
        
        public int getChangingConfigurations() {
            return this.a;
        }
        
        public Drawable newDrawable() {
            return new g(this);
        }
        
        public Drawable newDrawable(final Resources resources) {
            return new g(this);
        }
    }
    
    private static class g extends Drawable$ConstantState
    {
        private final Drawable$ConstantState a;
        
        public g(final Drawable$ConstantState a) {
            this.a = a;
        }
        
        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }
        
        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
        
        public Drawable newDrawable() {
            final android.support.a.a.g g = new android.support.a.a.g();
            g.b = this.a.newDrawable();
            return g;
        }
        
        public Drawable newDrawable(final Resources resources) {
            final android.support.a.a.g g = new android.support.a.a.g();
            g.b = this.a.newDrawable(resources);
            return g;
        }
        
        public Drawable newDrawable(final Resources resources, final Resources$Theme resources$Theme) {
            final android.support.a.a.g g = new android.support.a.a.g();
            g.b = this.a.newDrawable(resources, resources$Theme);
            return g;
        }
    }
}
