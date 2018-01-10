// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.a;

import java.util.Iterator;
import android.support.v4.b.a;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.app.Activity;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.ArrayList;
import android.content.Intent;

public final class ag implements Iterable<Intent>
{
    private static final b a;
    private final ArrayList<Intent> b;
    private final Context c;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            a = (b)new d();
            return;
        }
        a = (b)new c();
    }
    
    private ag(final Context c) {
        this.b = new ArrayList<Intent>();
        this.c = c;
    }
    
    public static ag a(final Context context) {
        return new ag(context);
    }
    
    public ag a(final Activity activity) {
        Intent a = null;
        if (activity instanceof a) {
            a = ((a)activity).a();
        }
        Intent a2;
        if (a == null) {
            a2 = z.a(activity);
        }
        else {
            a2 = a;
        }
        if (a2 != null) {
            ComponentName componentName;
            if ((componentName = a2.getComponent()) == null) {
                componentName = a2.resolveActivity(this.c.getPackageManager());
            }
            this.a(componentName);
            this.a(a2);
        }
        return this;
    }
    
    public ag a(final ComponentName componentName) {
        final int size = this.b.size();
        try {
            for (Intent intent = z.a(this.c, componentName); intent != null; intent = z.a(this.c, intent.getComponent())) {
                this.b.add(size, intent);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException((Throwable)ex);
        }
        return this;
    }
    
    public ag a(final Intent intent) {
        this.b.add(intent);
        return this;
    }
    
    public void a() {
        this.a((Bundle)null);
    }
    
    public void a(final Bundle bundle) {
        if (this.b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        final Intent[] array = this.b.toArray(new Intent[this.b.size()]);
        array[0] = new Intent(array[0]).addFlags(268484608);
        if (!android.support.v4.b.a.a(this.c, array, bundle)) {
            final Intent intent = new Intent(array[array.length - 1]);
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        }
    }
    
    @Deprecated
    @Override
    public Iterator<Intent> iterator() {
        return this.b.iterator();
    }
    
    public interface a
    {
        Intent a();
    }
    
    interface b
    {
    }
    
    static class c implements b
    {
    }
    
    static class d implements b
    {
    }
}
