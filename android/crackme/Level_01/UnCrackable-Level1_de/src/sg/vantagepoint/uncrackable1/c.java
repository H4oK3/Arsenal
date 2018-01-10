// 
// Decompiled by Procyon v0.5.30
// 

package sg.vantagepoint.uncrackable1;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class c implements DialogInterface$OnClickListener
{
    final /* synthetic */ MainActivity a;
    
    c(final MainActivity a) {
        this.a = a;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        dialogInterface.dismiss();
    }
}
