// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.os.Build$VERSION;
import android.util.Log;
import android.os.Process;
import android.support.annotation.NonNull;
import android.content.Context;
import java.io.File;

public class ContextCompat
{
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";
    
    private static File buildPath(File file, final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (file == null) {
                file = new File(s);
            }
            else if (s != null) {
                file = new File(file, s);
            }
        }
        return file;
    }
    
    public static int checkSelfPermission(@NonNull final Context context, @NonNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(s, Process.myPid(), Process.myUid());
    }
    
    private static File createFilesDir(final File file) {
        // monitorenter(ContextCompat.class)
        File file2 = file;
        try {
            if (!file.exists()) {
                file2 = file;
                if (!file.mkdirs()) {
                    if (file.exists()) {
                        file2 = file;
                    }
                    else {
                        Log.w("ContextCompat", "Unable to create files subdir " + file.getPath());
                        file2 = null;
                    }
                }
            }
            return file2;
        }
        finally {
        }
        // monitorexit(ContextCompat.class)
    }
    
    public static final int getColor(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColor(context, n);
        }
        return context.getResources().getColor(n);
    }
    
    public static final ColorStateList getColorStateList(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            return ContextCompatApi23.getColorStateList(context, n);
        }
        return context.getResources().getColorStateList(n);
    }
    
    public static final Drawable getDrawable(final Context context, final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getDrawable(context, n);
        }
        return context.getResources().getDrawable(n);
    }
    
    public static File[] getExternalCacheDirs(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 19) {
            return ContextCompatKitKat.getExternalCacheDirs(context);
        }
        File file;
        if (sdk_INT >= 8) {
            file = ContextCompatFroyo.getExternalCacheDir(context);
        }
        else {
            file = buildPath(Environment.getExternalStorageDirectory(), "Android", "data", context.getPackageName(), "cache");
        }
        return new File[] { file };
    }
    
    public static File[] getExternalFilesDirs(final Context context, final String s) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 19) {
            return ContextCompatKitKat.getExternalFilesDirs(context, s);
        }
        File file;
        if (sdk_INT >= 8) {
            file = ContextCompatFroyo.getExternalFilesDir(context, s);
        }
        else {
            file = buildPath(Environment.getExternalStorageDirectory(), "Android", "data", context.getPackageName(), "files", s);
        }
        return new File[] { file };
    }
    
    public static File[] getObbDirs(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 19) {
            return ContextCompatKitKat.getObbDirs(context);
        }
        File file;
        if (sdk_INT >= 11) {
            file = ContextCompatHoneycomb.getObbDir(context);
        }
        else {
            file = buildPath(Environment.getExternalStorageDirectory(), "Android", "obb", context.getPackageName());
        }
        return new File[] { file };
    }
    
    public static boolean startActivities(final Context context, final Intent[] array) {
        return startActivities(context, array, null);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array, final Bundle bundle) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            ContextCompatJellybean.startActivities(context, array, bundle);
            return true;
        }
        if (sdk_INT >= 11) {
            ContextCompatHoneycomb.startActivities(context, array);
            return true;
        }
        return false;
    }
    
    public final File getCodeCacheDir(final Context context) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getCodeCacheDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }
    
    public final File getNoBackupFilesDir(final Context context) {
        if (Build$VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getNoBackupFilesDir(context);
        }
        return createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }
}
