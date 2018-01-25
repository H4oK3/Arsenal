// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.app.PendingIntent;
import android.support.annotation.Nullable;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.support.v4.util.Pair;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.os.Build$VERSION;

public class ActivityOptionsCompat
{
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
    
    public static ActivityOptionsCompat makeBasic() {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeBasic());
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeBasic());
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeClipRevealAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeClipRevealAnimation(view, n, n2, n3, n4));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeClipRevealAnimation(view, n, n2, n3, n4));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeCustomAnimation(final Context context, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeCustomAnimation(context, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeCustomAnimation(context, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeCustomAnimation(context, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeScaleUpAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, n, n2, n3, n4));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final View view, final String s) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, view, s));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, view, s));
        }
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, s));
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeSceneTransitionAnimation(final Activity activity, final Pair<View, String>... array) {
        if (Build$VERSION.SDK_INT < 21) {
            return new ActivityOptionsCompat();
        }
        View[] array2 = null;
        String[] array3 = null;
        if (array != null) {
            final View[] array4 = new View[array.length];
            final String[] array5 = new String[array.length];
            int n = 0;
            while (true) {
                array3 = array5;
                array2 = array4;
                if (n >= array.length) {
                    break;
                }
                array4[n] = array[n].first;
                array5[n] = array[n].second;
                ++n;
            }
        }
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, array2, array3));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, array2, array3));
        }
        return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, array2, array3));
    }
    
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeTaskLaunchBehind());
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeTaskLaunchBehind());
        }
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeTaskLaunchBehind());
        }
        return new ActivityOptionsCompat();
    }
    
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(final View view, final Bitmap bitmap, final int n, final int n2) {
        if (Build$VERSION.SDK_INT >= 24) {
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 23) {
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 21) {
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        if (Build$VERSION.SDK_INT >= 16) {
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, n, n2));
        }
        return new ActivityOptionsCompat();
    }
    
    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }
    
    public void requestUsageTimeReport(final PendingIntent pendingIntent) {
    }
    
    public ActivityOptionsCompat setLaunchBounds(@Nullable final Rect rect) {
        return null;
    }
    
    public Bundle toBundle() {
        return null;
    }
    
    public void update(final ActivityOptionsCompat activityOptionsCompat) {
    }
    
    @RequiresApi(21)
    private static class ActivityOptionsImpl21 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat21 mImpl;
        
        ActivityOptionsImpl21(final ActivityOptionsCompat21 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl21) {
                this.mImpl.update(((ActivityOptionsImpl21)activityOptionsCompat).mImpl);
            }
        }
    }
    
    @RequiresApi(23)
    private static class ActivityOptionsImpl23 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat23 mImpl;
        
        ActivityOptionsImpl23(final ActivityOptionsCompat23 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public void requestUsageTimeReport(final PendingIntent pendingIntent) {
            this.mImpl.requestUsageTimeReport(pendingIntent);
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl23) {
                this.mImpl.update(((ActivityOptionsImpl23)activityOptionsCompat).mImpl);
            }
        }
    }
    
    @RequiresApi(24)
    private static class ActivityOptionsImpl24 extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompat24 mImpl;
        
        ActivityOptionsImpl24(final ActivityOptionsCompat24 mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Rect getLaunchBounds() {
            return this.mImpl.getLaunchBounds();
        }
        
        @Override
        public void requestUsageTimeReport(final PendingIntent pendingIntent) {
            this.mImpl.requestUsageTimeReport(pendingIntent);
        }
        
        @Override
        public ActivityOptionsCompat setLaunchBounds(@Nullable final Rect launchBounds) {
            return new ActivityOptionsImpl24(this.mImpl.setLaunchBounds(launchBounds));
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImpl24) {
                this.mImpl.update(((ActivityOptionsImpl24)activityOptionsCompat).mImpl);
            }
        }
    }
    
    @RequiresApi(16)
    private static class ActivityOptionsImplJB extends ActivityOptionsCompat
    {
        private final ActivityOptionsCompatJB mImpl;
        
        ActivityOptionsImplJB(final ActivityOptionsCompatJB mImpl) {
            this.mImpl = mImpl;
        }
        
        @Override
        public Bundle toBundle() {
            return this.mImpl.toBundle();
        }
        
        @Override
        public void update(final ActivityOptionsCompat activityOptionsCompat) {
            if (activityOptionsCompat instanceof ActivityOptionsImplJB) {
                this.mImpl.update(((ActivityOptionsImplJB)activityOptionsCompat).mImpl);
            }
        }
    }
}
