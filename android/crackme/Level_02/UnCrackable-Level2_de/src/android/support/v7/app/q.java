// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.util.Calendar;
import android.support.v4.b.i;
import android.util.Log;
import android.location.Location;
import android.location.LocationManager;
import android.content.Context;

class q
{
    private static q a;
    private final Context b;
    private final LocationManager c;
    private final a d;
    
    q(final Context b, final LocationManager c) {
        this.d = new a();
        this.b = b;
        this.c = c;
    }
    
    private Location a(final String s) {
        if (this.c != null) {
            try {
                if (this.c.isProviderEnabled(s)) {
                    return this.c.getLastKnownLocation(s);
                }
            }
            catch (Exception ex) {
                Log.d("TwilightManager", "Failed to get last known location", (Throwable)ex);
            }
        }
        return null;
    }
    
    static q a(Context applicationContext) {
        if (q.a == null) {
            applicationContext = applicationContext.getApplicationContext();
            q.a = new q(applicationContext, (LocationManager)applicationContext.getSystemService("location"));
        }
        return q.a;
    }
    
    private void a(final Location location) {
        final a d = this.d;
        final long currentTimeMillis = System.currentTimeMillis();
        final p a = p.a();
        a.a(currentTimeMillis - 86400000L, location.getLatitude(), location.getLongitude());
        final long a2 = a.a;
        a.a(currentTimeMillis, location.getLatitude(), location.getLongitude());
        final boolean a3 = a.c == 1;
        final long b = a.b;
        final long a4 = a.a;
        a.a(86400000L + currentTimeMillis, location.getLatitude(), location.getLongitude());
        final long b2 = a.b;
        long f;
        if (b == -1L || a4 == -1L) {
            f = 43200000L + currentTimeMillis;
        }
        else {
            long n;
            if (currentTimeMillis > a4) {
                n = 0L + b2;
            }
            else if (currentTimeMillis > b) {
                n = 0L + a4;
            }
            else {
                n = 0L + b;
            }
            f = n + 60000L;
        }
        d.a = a3;
        d.b = a2;
        d.c = b;
        d.d = a4;
        d.e = b2;
        d.f = f;
    }
    
    private Location b() {
        Location a = null;
        Location a2;
        if (i.a(this.b, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            a2 = this.a("network");
        }
        else {
            a2 = null;
        }
        if (i.a(this.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            a = this.a("gps");
        }
        if (a != null && a2 != null) {
            Location location = a2;
            if (a.getTime() > a2.getTime()) {
                location = a;
            }
            return location;
        }
        if (a == null) {
            a = a2;
        }
        return a;
    }
    
    private boolean c() {
        return this.d != null && this.d.f > System.currentTimeMillis();
    }
    
    boolean a() {
        final a d = this.d;
        if (this.c()) {
            return d.a;
        }
        final Location b = this.b();
        if (b != null) {
            this.a(b);
            return d.a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        final int value = Calendar.getInstance().get(11);
        return value < 6 || value >= 22;
    }
    
    private static class a
    {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;
    }
}
