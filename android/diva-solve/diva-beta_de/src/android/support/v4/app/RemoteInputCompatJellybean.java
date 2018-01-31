// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.ClipDescription;
import android.content.ClipData;
import android.os.Bundle;
import android.content.Intent;

class RemoteInputCompatJellybean
{
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_LABEL = "label";
    private static final String KEY_RESULT_KEY = "resultKey";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    
    static void addResultsToIntent(final RemoteInputCompatBase.RemoteInput[] array, final Intent intent, final Bundle bundle) {
        final Bundle bundle2 = new Bundle();
        for (int length = array.length, i = 0; i < length; ++i) {
            final RemoteInputCompatBase.RemoteInput remoteInput = array[i];
            final Object value = bundle.get(remoteInput.getResultKey());
            if (value instanceof CharSequence) {
                bundle2.putCharSequence(remoteInput.getResultKey(), (CharSequence)value);
            }
        }
        final Intent intent2 = new Intent();
        intent2.putExtra("android.remoteinput.resultsData", bundle2);
        intent.setClipData(ClipData.newIntent((CharSequence)"android.remoteinput.results", intent2));
    }
    
    static RemoteInputCompatBase.RemoteInput fromBundle(final Bundle bundle, final RemoteInputCompatBase.RemoteInput.Factory factory) {
        return factory.build(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), bundle.getBundle("extras"));
    }
    
    static RemoteInputCompatBase.RemoteInput[] fromBundleArray(final Bundle[] array, final RemoteInputCompatBase.RemoteInput.Factory factory) {
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
                array3[n] = fromBundle(array[n], factory);
                ++n;
            }
        }
        return array2;
    }
    
    static Bundle getResultsFromIntent(final Intent intent) {
        final ClipData clipData = intent.getClipData();
        if (clipData != null) {
            final ClipDescription description = clipData.getDescription();
            if (description.hasMimeType("text/vnd.android.intent") && description.getLabel().equals("android.remoteinput.results")) {
                return (Bundle)clipData.getItemAt(0).getIntent().getExtras().getParcelable("android.remoteinput.resultsData");
            }
        }
        return null;
    }
    
    static Bundle toBundle(final RemoteInputCompatBase.RemoteInput remoteInput) {
        final Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.getResultKey());
        bundle.putCharSequence("label", remoteInput.getLabel());
        bundle.putCharSequenceArray("choices", remoteInput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInput.getExtras());
        return bundle;
    }
    
    static Bundle[] toBundleArray(final RemoteInputCompatBase.RemoteInput[] array) {
        Bundle[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final Bundle[] array3 = new Bundle[array.length];
            int n = 0;
            while (true) {
                array2 = array3;
                if (n >= array.length) {
                    break;
                }
                array3[n] = toBundle(array[n]);
                ++n;
            }
        }
        return array2;
    }
}
