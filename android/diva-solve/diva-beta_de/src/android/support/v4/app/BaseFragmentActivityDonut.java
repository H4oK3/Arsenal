// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.LayoutInflater$Factory;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.app.Activity;

abstract class BaseFragmentActivityDonut extends Activity
{
    abstract View dispatchFragmentsOnCreateView(final View p0, final String p1, final Context p2, final AttributeSet p3);
    
    protected void onCreate(final Bundle bundle) {
        if (Build$VERSION.SDK_INT < 11 && this.getLayoutInflater().getFactory() == null) {
            this.getLayoutInflater().setFactory((LayoutInflater$Factory)this);
        }
        super.onCreate(bundle);
    }
    
    public View onCreateView(final String s, final Context context, final AttributeSet set) {
        View view;
        if ((view = this.dispatchFragmentsOnCreateView(null, s, context, set)) == null) {
            view = super.onCreateView(s, context, set);
        }
        return view;
    }
}
