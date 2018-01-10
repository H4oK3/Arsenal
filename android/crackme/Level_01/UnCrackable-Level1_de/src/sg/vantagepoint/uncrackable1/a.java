// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.uncrackable1;

import android.util.Log;
import android.util.Base64;

public class a
{
    public static boolean a(final String s) {
        final byte[] decode = Base64.decode("5UJiFctbmgbDoLXmpL12mkno8HT4Lv8dlat8FxR2GOc=", 0);
        byte[] a = new byte[0];
        while (true) {
            try {
                a = sg.vantagepoint.a.a.a(b("8d127684cbc37c17616d806cf50473cc"), decode);
                if (s.equals(new String(a))) {
                    return true;
                }
            }
            catch (Exception ex) {
                Log.d("CodeCheck", "AES error:" + ex.getMessage());
                continue;
            }
            break;
        }
        return false;
    }
    
    public static byte[] b(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
}
