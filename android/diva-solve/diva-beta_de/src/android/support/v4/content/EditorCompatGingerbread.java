// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.support.annotation.NonNull;
import android.content.SharedPreferences$Editor;

class EditorCompatGingerbread
{
    public static void apply(@NonNull final SharedPreferences$Editor sharedPreferences$Editor) {
        try {
            sharedPreferences$Editor.apply();
        }
        catch (AbstractMethodError abstractMethodError) {
            sharedPreferences$Editor.commit();
        }
    }
}
