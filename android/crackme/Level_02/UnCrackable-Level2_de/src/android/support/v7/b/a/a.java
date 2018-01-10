// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.b.a;

import android.util.StateSet;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.graphics.Color;

final class a
{
    private static int a(final int n, final float n2) {
        return android.support.v4.c.a.b(n, Math.round(Color.alpha(n) * n2));
    }
    
    public static ColorStateList a(final Resources resources, final XmlPullParser xmlPullParser, final Resources$Theme resources$Theme) {
        final AttributeSet attributeSet = Xml.asAttributeSet(xmlPullParser);
        int next;
        do {
            next = xmlPullParser.next();
        } while (next != 2 && next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return a(resources, xmlPullParser, attributeSet, resources$Theme);
    }
    
    private static ColorStateList a(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final String name = xmlPullParser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        return b(resources, xmlPullParser, set, resources$Theme);
    }
    
    private static TypedArray a(final Resources resources, final Resources$Theme resources$Theme, final AttributeSet set, final int[] array) {
        if (resources$Theme == null) {
            return resources.obtainAttributes(set, array);
        }
        return resources$Theme.obtainStyledAttributes(set, array, 0, 0);
    }
    
    private static ColorStateList b(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final int n = xmlPullParser.getDepth() + 1;
        int[][] array = new int[20][];
        int[] a = new int[array.length];
        int n2 = 0;
        while (true) {
            final int next = xmlPullParser.next();
            if (next == 1) {
                break;
            }
            final int depth = xmlPullParser.getDepth();
            if (depth < n && next == 3) {
                break;
            }
            if (next != 2 || depth > n || !xmlPullParser.getName().equals("item")) {
                continue;
            }
            final TypedArray a2 = a(resources, resources$Theme, set, android.support.v7.a.a.j.ColorStateListItem);
            final int color = a2.getColor(android.support.v7.a.a.j.ColorStateListItem_android_color, -65281);
            float n3 = 1.0f;
            if (a2.hasValue(android.support.v7.a.a.j.ColorStateListItem_android_alpha)) {
                n3 = a2.getFloat(android.support.v7.a.a.j.ColorStateListItem_android_alpha, 1.0f);
            }
            else if (a2.hasValue(android.support.v7.a.a.j.ColorStateListItem_alpha)) {
                n3 = a2.getFloat(android.support.v7.a.a.j.ColorStateListItem_alpha, 1.0f);
            }
            a2.recycle();
            int n4 = 0;
            final int attributeCount = set.getAttributeCount();
            final int[] array2 = new int[attributeCount];
            for (int i = 0; i < attributeCount; ++i) {
                int attributeNameResource = set.getAttributeNameResource(i);
                if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != android.support.v7.a.a.a.alpha) {
                    if (!set.getAttributeBooleanValue(i, false)) {
                        attributeNameResource = -attributeNameResource;
                    }
                    array2[n4] = attributeNameResource;
                    ++n4;
                }
            }
            final int[] trimStateSet = StateSet.trimStateSet(array2, n4);
            final int a3 = a(color, n3);
            if (n2 == 0 || trimStateSet.length == 0) {}
            a = c.a(a, n2, a3);
            array = c.a(array, n2, trimStateSet);
            ++n2;
        }
        final int[] array3 = new int[n2];
        final int[][] array4 = new int[n2][];
        System.arraycopy(a, 0, array3, 0, n2);
        System.arraycopy(array, 0, array4, 0, n2);
        return new ColorStateList(array4, array3);
    }
}
