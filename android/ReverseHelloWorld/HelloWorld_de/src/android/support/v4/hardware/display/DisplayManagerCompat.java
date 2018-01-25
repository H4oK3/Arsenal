// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.display;

import android.view.WindowManager;
import android.view.Display;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> sInstances;
    
    static {
        sInstances = new WeakHashMap<Context, DisplayManagerCompat>();
    }
    
    public static DisplayManagerCompat getInstance(final Context context) {
        synchronized (DisplayManagerCompat.sInstances) {
            DisplayManagerCompat displayManagerCompat;
            if ((displayManagerCompat = DisplayManagerCompat.sInstances.get(context)) == null) {
                if (Build$VERSION.SDK_INT >= 17) {
                    displayManagerCompat = new JellybeanMr1Impl(context);
                }
                else {
                    displayManagerCompat = new LegacyImpl(context);
                }
                DisplayManagerCompat.sInstances.put(context, displayManagerCompat);
            }
            return displayManagerCompat;
        }
    }
    
    public abstract Display getDisplay(final int p0);
    
    public abstract Display[] getDisplays();
    
    public abstract Display[] getDisplays(final String p0);
    
    private static class JellybeanMr1Impl extends DisplayManagerCompat
    {
        private final Object mDisplayManagerObj;
        
        public JellybeanMr1Impl(final Context context) {
            this.mDisplayManagerObj = DisplayManagerJellybeanMr1.getDisplayManager(context);
        }
        
        @Override
        public Display getDisplay(final int n) {
            return DisplayManagerJellybeanMr1.getDisplay(this.mDisplayManagerObj, n);
        }
        
        @Override
        public Display[] getDisplays() {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj);
        }
        
        @Override
        public Display[] getDisplays(final String s) {
            return DisplayManagerJellybeanMr1.getDisplays(this.mDisplayManagerObj, s);
        }
    }
    
    private static class LegacyImpl extends DisplayManagerCompat
    {
        private final WindowManager mWindowManager;
        
        public LegacyImpl(final Context context) {
            this.mWindowManager = (WindowManager)context.getSystemService("window");
        }
        
        @Override
        public Display getDisplay(final int n) {
            final Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
            if (defaultDisplay.getDisplayId() == n) {
                return defaultDisplay;
            }
            return null;
        }
        
        @Override
        public Display[] getDisplays() {
            return new Display[] { this.mWindowManager.getDefaultDisplay() };
        }
        
        @Override
        public Display[] getDisplays(final String s) {
            if (s == null) {
                return this.getDisplays();
            }
            return new Display[0];
        }
    }
}
