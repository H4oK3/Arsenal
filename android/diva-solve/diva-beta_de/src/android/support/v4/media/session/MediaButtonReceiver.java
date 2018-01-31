// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.List;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.content.Context;
import android.view.KeyEvent;
import android.content.Intent;
import android.content.BroadcastReceiver;

public class MediaButtonReceiver extends BroadcastReceiver
{
    public static KeyEvent handleIntent(final MediaSessionCompat mediaSessionCompat, final Intent intent) {
        if (mediaSessionCompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            return null;
        }
        final KeyEvent keyEvent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        mediaSessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
        return keyEvent;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent2.setPackage(context.getPackageName());
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent2, 0);
        if (queryIntentServices.size() != 1) {
            throw new IllegalStateException("Expected 1 Service that handles android.intent.action.MEDIA_BUTTON, found " + queryIntentServices.size());
        }
        final ResolveInfo resolveInfo = queryIntentServices.get(0);
        intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
        context.startService(intent);
    }
}
