// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.internal.widget.TintManager;
import android.widget.SeekBar;

public class AppCompatSeekBar extends SeekBar
{
    private AppCompatSeekBarHelper mAppCompatSeekBarHelper;
    private TintManager mTintManager;
    
    public AppCompatSeekBar(final Context context) {
        this(context, null);
    }
    
    public AppCompatSeekBar(final Context context, final AttributeSet set) {
        this(context, set, R.attr.seekBarStyle);
    }
    
    public AppCompatSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mTintManager = TintManager.get(context);
        (this.mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this, this.mTintManager)).loadFromAttributes(set, n);
    }
}
