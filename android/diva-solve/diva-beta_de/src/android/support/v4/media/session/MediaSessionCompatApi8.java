// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.AudioManager;
import android.content.ComponentName;
import android.content.Context;

class MediaSessionCompatApi8
{
    public static void registerMediaButtonEventReceiver(final Context context, final ComponentName componentName) {
        ((AudioManager)context.getSystemService("audio")).registerMediaButtonEventReceiver(componentName);
    }
    
    public static void unregisterMediaButtonEventReceiver(final Context context, final ComponentName componentName) {
        ((AudioManager)context.getSystemService("audio")).unregisterMediaButtonEventReceiver(componentName);
    }
}
