// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.support.annotation.NonNull;
import android.content.SharedPreferences$Editor;
import android.os.Build$VERSION;

public class SharedPreferencesCompat
{
    public static class EditorCompat
    {
        private static EditorCompat sInstance;
        private final Helper mHelper;
        
        private EditorCompat() {
            if (Build$VERSION.SDK_INT >= 9) {
                this.mHelper = (Helper)new EditorHelperApi9Impl();
                return;
            }
            this.mHelper = (Helper)new EditorHelperBaseImpl();
        }
        
        public static EditorCompat getInstance() {
            if (EditorCompat.sInstance == null) {
                EditorCompat.sInstance = new EditorCompat();
            }
            return EditorCompat.sInstance;
        }
        
        public void apply(@NonNull final SharedPreferences$Editor sharedPreferences$Editor) {
            this.mHelper.apply(sharedPreferences$Editor);
        }
        
        private static class EditorHelperApi9Impl implements Helper
        {
            @Override
            public void apply(@NonNull final SharedPreferences$Editor sharedPreferences$Editor) {
                EditorCompatGingerbread.apply(sharedPreferences$Editor);
            }
        }
        
        private static class EditorHelperBaseImpl implements Helper
        {
            @Override
            public void apply(@NonNull final SharedPreferences$Editor sharedPreferences$Editor) {
                sharedPreferences$Editor.commit();
            }
        }
        
        private interface Helper
        {
            void apply(@NonNull final SharedPreferences$Editor p0);
        }
    }
}
