// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import android.util.Log;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.support.v4.b.f;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Build$VERSION;

public final class z
{
    private static final a a;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            a = (a)new c();
            return;
        }
        a = (a)new b();
    }
    
    public static Intent a(final Activity activity) {
        return z.a.a(activity);
    }
    
    public static Intent a(final Context context, ComponentName component) {
        final String b = b(context, component);
        if (b == null) {
            return null;
        }
        component = new ComponentName(component.getPackageName(), b);
        if (b(context, component) == null) {
            return f.a(component);
        }
        return new Intent().setComponent(component);
    }
    
    public static boolean a(final Activity activity, final Intent intent) {
        return z.a.a(activity, intent);
    }
    
    public static String b(final Activity activity) {
        try {
            return b((Context)activity, activity.getComponentName());
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new IllegalArgumentException((Throwable)ex);
        }
    }
    
    public static String b(final Context context, final ComponentName componentName) {
        return z.a.a(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }
    
    public static void b(final Activity activity, final Intent intent) {
        z.a.b(activity, intent);
    }
    
    interface a
    {
        Intent a(final Activity p0);
        
        String a(final Context p0, final ActivityInfo p1);
        
        boolean a(final Activity p0, final Intent p1);
        
        void b(final Activity p0, final Intent p1);
    }
    
    static class b implements a
    {
        @Override
        public Intent a(final Activity activity) {
            final String b = z.b(activity);
            if (b == null) {
                return null;
            }
            final ComponentName component = new ComponentName((Context)activity, b);
            try {
                if (z.b((Context)activity, component) == null) {
                    return f.a(component);
                }
                return new Intent().setComponent(component);
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + b + "' in manifest");
                return null;
            }
        }
        
        @Override
        public String a(final Context context, final ActivityInfo activityInfo) {
            String s;
            if (activityInfo.metaData == null) {
                s = null;
            }
            else {
                final String string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
                if (string == null) {
                    return null;
                }
                s = string;
                if (string.charAt(0) == '.') {
                    return context.getPackageName() + string;
                }
            }
            return s;
        }
        
        @Override
        public boolean a(final Activity activity, final Intent intent) {
            final String action = activity.getIntent().getAction();
            return action != null && !action.equals("android.intent.action.MAIN");
        }
        
        @Override
        public void b(final Activity activity, final Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }
    }
    
    static class c extends b
    {
        @Override
        public Intent a(final Activity activity) {
            Intent intent;
            if ((intent = aa.a(activity)) == null) {
                intent = this.b(activity);
            }
            return intent;
        }
        
        @Override
        public String a(final Context context, final ActivityInfo activityInfo) {
            String s;
            if ((s = aa.a(activityInfo)) == null) {
                s = super.a(context, activityInfo);
            }
            return s;
        }
        
        @Override
        public boolean a(final Activity activity, final Intent intent) {
            return aa.a(activity, intent);
        }
        
        Intent b(final Activity activity) {
            return super.a(activity);
        }
        
        @Override
        public void b(final Activity activity, final Intent intent) {
            aa.b(activity, intent);
        }
    }
}
