// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.TextView;
import android.support.annotation.RequiresApi;

@RequiresApi(16)
class TextViewCompatJb
{
    static int getMaxLines(final TextView textView) {
        return textView.getMaxLines();
    }
    
    static int getMinLines(final TextView textView) {
        return textView.getMinLines();
    }
}
