// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics;

import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.ColorInt;
import android.graphics.Color;
import android.support.annotation.NonNull;

public class ColorUtils
{
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    
    @ColorInt
    public static int HSLToColor(@NonNull final float[] array) {
        final float n = array[0];
        final float n2 = array[1];
        final float n3 = array[2];
        final float n4 = (1.0f - Math.abs(2.0f * n3 - 1.0f)) * n2;
        final float n5 = n3 - 0.5f * n4;
        final float n6 = n4 * (1.0f - Math.abs(n / 60.0f % 2.0f - 1.0f));
        final int n7 = (int)n / 60;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        switch (n7) {
            case 0: {
                n8 = Math.round(255.0f * (n4 + n5));
                n9 = Math.round(255.0f * (n6 + n5));
                n10 = Math.round(255.0f * n5);
                break;
            }
            case 1: {
                n8 = Math.round(255.0f * (n6 + n5));
                n9 = Math.round(255.0f * (n4 + n5));
                n10 = Math.round(255.0f * n5);
                break;
            }
            case 2: {
                n8 = Math.round(255.0f * n5);
                n9 = Math.round(255.0f * (n4 + n5));
                n10 = Math.round(255.0f * (n6 + n5));
                break;
            }
            case 3: {
                n8 = Math.round(255.0f * n5);
                n9 = Math.round(255.0f * (n6 + n5));
                n10 = Math.round(255.0f * (n4 + n5));
                break;
            }
            case 4: {
                n8 = Math.round(255.0f * (n6 + n5));
                n9 = Math.round(255.0f * n5);
                n10 = Math.round(255.0f * (n4 + n5));
                break;
            }
            case 5:
            case 6: {
                n8 = Math.round(255.0f * (n4 + n5));
                n9 = Math.round(255.0f * n5);
                n10 = Math.round(255.0f * (n6 + n5));
                break;
            }
        }
        return Color.rgb(constrain(n8, 0, 255), constrain(n9, 0, 255), constrain(n10, 0, 255));
    }
    
    public static void RGBToHSL(@IntRange(from = 0L, to = 255L) final int n, @IntRange(from = 0L, to = 255L) final int n2, @IntRange(from = 0L, to = 255L) final int n3, @NonNull final float[] array) {
        final float n4 = n / 255.0f;
        final float n5 = n2 / 255.0f;
        final float n6 = n3 / 255.0f;
        final float max = Math.max(n4, Math.max(n5, n6));
        final float min = Math.min(n4, Math.min(n5, n6));
        final float n7 = max - min;
        final float n8 = (max + min) / 2.0f;
        float n9;
        float n10;
        if (max == min) {
            n9 = 0.0f;
            n10 = 0.0f;
        }
        else {
            float n11;
            if (max == n4) {
                n11 = (n5 - n6) / n7 % 6.0f;
            }
            else if (max == n5) {
                n11 = (n6 - n4) / n7 + 2.0f;
            }
            else {
                n11 = (n4 - n5) / n7 + 4.0f;
            }
            final float n12 = n7 / (1.0f - Math.abs(2.0f * n8 - 1.0f));
            n10 = n11;
            n9 = n12;
        }
        float n14;
        final float n13 = n14 = 60.0f * n10 % 360.0f;
        if (n13 < 0.0f) {
            n14 = n13 + 360.0f;
        }
        array[0] = constrain(n14, 0.0f, 360.0f);
        array[1] = constrain(n9, 0.0f, 1.0f);
        array[2] = constrain(n8, 0.0f, 1.0f);
    }
    
    public static double calculateContrast(@ColorInt final int n, @ColorInt final int n2) {
        if (Color.alpha(n2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(n2));
        }
        int compositeColors = n;
        if (Color.alpha(n) < 255) {
            compositeColors = compositeColors(n, n2);
        }
        final double n3 = calculateLuminance(compositeColors) + 0.05;
        final double n4 = calculateLuminance(n2) + 0.05;
        return Math.max(n3, n4) / Math.min(n3, n4);
    }
    
    @FloatRange(from = 0.0, to = 1.0)
    public static double calculateLuminance(@ColorInt final int n) {
        final double n2 = Color.red(n) / 255.0;
        double pow;
        if (n2 < 0.03928) {
            pow = n2 / 12.92;
        }
        else {
            pow = Math.pow((0.055 + n2) / 1.055, 2.4);
        }
        final double n3 = Color.green(n) / 255.0;
        double pow2;
        if (n3 < 0.03928) {
            pow2 = n3 / 12.92;
        }
        else {
            pow2 = Math.pow((0.055 + n3) / 1.055, 2.4);
        }
        final double n4 = Color.blue(n) / 255.0;
        double pow3;
        if (n4 < 0.03928) {
            pow3 = n4 / 12.92;
        }
        else {
            pow3 = Math.pow((0.055 + n4) / 1.055, 2.4);
        }
        return 0.2126 * pow + 0.7152 * pow2 + 0.0722 * pow3;
    }
    
    public static int calculateMinimumAlpha(@ColorInt final int n, @ColorInt final int n2, final float n3) {
        if (Color.alpha(n2) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(n2));
        }
        int n4;
        if (calculateContrast(setAlphaComponent(n, 255), n2) < n3) {
            n4 = -1;
        }
        else {
            int n5 = 0;
            int n6 = 0;
            int n7 = 255;
            while (true) {
                n4 = n7;
                if (n5 > 10) {
                    break;
                }
                n4 = n7;
                if (n7 - n6 <= 1) {
                    break;
                }
                final int n8 = (n6 + n7) / 2;
                if (calculateContrast(setAlphaComponent(n, n8), n2) < n3) {
                    n6 = n8;
                }
                else {
                    n7 = n8;
                }
                ++n5;
            }
        }
        return n4;
    }
    
    public static void colorToHSL(@ColorInt final int n, @NonNull final float[] array) {
        RGBToHSL(Color.red(n), Color.green(n), Color.blue(n), array);
    }
    
    private static int compositeAlpha(final int n, final int n2) {
        return 255 - (255 - n2) * (255 - n) / 255;
    }
    
    public static int compositeColors(@ColorInt final int n, @ColorInt final int n2) {
        final int alpha = Color.alpha(n2);
        final int alpha2 = Color.alpha(n);
        final int compositeAlpha = compositeAlpha(alpha2, alpha);
        return Color.argb(compositeAlpha, compositeComponent(Color.red(n), alpha2, Color.red(n2), alpha, compositeAlpha), compositeComponent(Color.green(n), alpha2, Color.green(n2), alpha, compositeAlpha), compositeComponent(Color.blue(n), alpha2, Color.blue(n2), alpha, compositeAlpha));
    }
    
    private static int compositeComponent(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n5 == 0) {
            return 0;
        }
        return (n * 255 * n2 + n3 * n4 * (255 - n2)) / (n5 * 255);
    }
    
    private static float constrain(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    private static int constrain(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    @ColorInt
    public static int setAlphaComponent(@ColorInt final int n, @IntRange(from = 0L, to = 255L) final int n2) {
        if (n2 < 0 || n2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (0xFFFFFF & n) | n2 << 24;
    }
}
