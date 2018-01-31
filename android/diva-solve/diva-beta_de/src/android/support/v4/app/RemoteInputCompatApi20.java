// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.RemoteInput$Builder;
import android.app.RemoteInput;
import android.os.Bundle;
import android.content.Intent;

class RemoteInputCompatApi20
{
    static void addResultsToIntent(final RemoteInputCompatBase.RemoteInput[] array, final Intent intent, final Bundle bundle) {
        RemoteInput.addResultsToIntent(fromCompat(array), intent, bundle);
    }
    
    static RemoteInput[] fromCompat(final RemoteInputCompatBase.RemoteInput[] array) {
        RemoteInput[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final RemoteInput[] array3 = new RemoteInput[array.length];
            int n = 0;
            while (true) {
                array2 = array3;
                if (n >= array.length) {
                    break;
                }
                final RemoteInputCompatBase.RemoteInput remoteInput = array[n];
                array3[n] = new RemoteInput$Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
                ++n;
            }
        }
        return array2;
    }
    
    static Bundle getResultsFromIntent(final Intent intent) {
        return RemoteInput.getResultsFromIntent(intent);
    }
    
    static RemoteInputCompatBase.RemoteInput[] toCompat(final RemoteInput[] array, final RemoteInputCompatBase.RemoteInput.Factory factory) {
        RemoteInputCompatBase.RemoteInput[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final RemoteInputCompatBase.RemoteInput[] array3 = factory.newArray(array.length);
            int n = 0;
            while (true) {
                array2 = array3;
                if (n >= array.length) {
                    break;
                }
                final RemoteInput remoteInput = array[n];
                array3[n] = factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras());
                ++n;
            }
        }
        return array2;
    }
}
