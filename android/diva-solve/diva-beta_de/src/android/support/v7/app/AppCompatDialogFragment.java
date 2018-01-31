// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class AppCompatDialogFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return new AppCompatDialog((Context)this.getActivity(), this.getTheme());
    }
    
    @Override
    public void setupDialog(final Dialog dialog, final int n) {
        if (!(dialog instanceof AppCompatDialog)) {
            super.setupDialog(dialog, n);
            return;
        }
        final AppCompatDialog appCompatDialog = (AppCompatDialog)dialog;
        switch (n) {
            default: {}
            case 3: {
                dialog.getWindow().addFlags(24);
            }
            case 1:
            case 2: {
                appCompatDialog.supportRequestWindowFeature(1);
            }
        }
    }
}
