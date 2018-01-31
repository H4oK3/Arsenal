// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import java.lang.reflect.Field;
import android.util.Log;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.graphics.Rect;

public class DrawableUtils
{
    public static final Rect INSETS_NONE;
    private static final String TAG = "DrawableUtils";
    private static Class<?> sInsetsClazz;
    
    static {
        INSETS_NONE = new Rect();
        if (Build$VERSION.SDK_INT < 18) {
            return;
        }
        try {
            DrawableUtils.sInsetsClazz = Class.forName("android.graphics.Insets");
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public static Rect getOpticalBounds(Drawable unwrap) {
        if (DrawableUtils.sInsetsClazz != null) {
            while (true) {
            Label_0192_Outer:
                while (true) {
                    int n = 0;
                Label_0296:
                    while (true) {
                        Object invoke = null;
                        Rect rect = null;
                        Field field = null;
                        int n2 = 0;
                        Label_0263: {
                            try {
                                unwrap = DrawableCompat.unwrap(unwrap);
                                invoke = unwrap.getClass().getMethod("getOpticalInsets", (Class<?>[])new Class[0]).invoke(unwrap, new Object[0]);
                                if (invoke == null) {
                                    break;
                                }
                                rect = new Rect();
                                final Field[] fields = DrawableUtils.sInsetsClazz.getFields();
                                final int length = fields.length;
                                n = 0;
                                final Rect insets_NONE = rect;
                                if (n >= length) {
                                    return insets_NONE;
                                }
                                field = fields[n];
                                final String name = field.getName();
                                n2 = -1;
                                switch (name.hashCode()) {
                                    case 3317767: {
                                        if (name.equals("left")) {
                                            n2 = 0;
                                        }
                                        break Label_0263;
                                    }
                                    case 115029: {
                                        if (name.equals("top")) {
                                            n2 = 1;
                                        }
                                        break Label_0263;
                                    }
                                    case 108511772: {
                                        if (name.equals("right")) {
                                            n2 = 2;
                                        }
                                        break Label_0263;
                                    }
                                    case -1383228885: {
                                        if (name.equals("bottom")) {
                                            n2 = 3;
                                        }
                                        break Label_0263;
                                    }
                                    default: {
                                        break Label_0263;
                                    }
                                }
                                rect.left = field.getInt(invoke);
                                break Label_0296;
                            }
                            catch (Exception ex) {
                                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
                            }
                            break;
                        }
                        switch (n2) {
                            case 0: {
                                continue;
                            }
                            case 1: {
                                rect.top = field.getInt(invoke);
                                break;
                            }
                            case 2: {
                                rect.right = field.getInt(invoke);
                                break;
                            }
                            case 3: {
                                rect.bottom = field.getInt(invoke);
                                break;
                            }
                        }
                        break;
                    }
                    ++n;
                    continue Label_0192_Outer;
                }
            }
        }
        return DrawableUtils.INSETS_NONE;
    }
}
