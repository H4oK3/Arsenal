// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.a;

import android.os.Build;
import java.io.File;

public class c
{
    public static boolean a() {
        final boolean b = false;
        final String[] split = System.getenv("PATH").split(":");
        final int length = split.length;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            if (new File(split[n], "su").exists()) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public static boolean b() {
        final String tags = Build.TAGS;
        return tags != null && tags.contains("test-keys");
    }
    
    public static boolean c() {
        final String[] array = { "/system/app/Superuser.apk", "/system/xbin/daemonsu", "/system/etc/init.d/99SuperSUDaemon", "/system/bin/.ext/.su", "/system/etc/.has_su_daemon", "/system/etc/.installed_su_daemon", "/dev/com.koushikdutta.superuser.daemon/" };
        for (int length = array.length, i = 0; i < length; ++i) {
            if (new File(array[i]).exists()) {
                return true;
            }
        }
        return false;
    }
}
