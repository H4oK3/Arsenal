// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.a;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class a
{
    public static byte[] a(final byte[] array, final byte[] array2) {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES/ECB/PKCS7Padding");
        final Cipher instance = Cipher.getInstance("AES");
        instance.init(2, secretKeySpec);
        return instance.doFinal(array2);
    }
}
