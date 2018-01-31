// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.util.Calendar;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.v4.content.PermissionChecker;
import android.location.Location;
import android.location.LocationManager;
import android.content.Context;

class TwilightManager
{
    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private static final TwilightState sTwilightState;
    private final Context mContext;
    private final LocationManager mLocationManager;
    
    static {
        sTwilightState = new TwilightState();
    }
    
    TwilightManager(final Context mContext) {
        this.mContext = mContext;
        this.mLocationManager = (LocationManager)mContext.getSystemService("location");
    }
    
    private Location getLastKnownLocation() {
        Location lastKnownLocationForProvider = null;
        Location lastKnownLocationForProvider2 = null;
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            lastKnownLocationForProvider = this.getLastKnownLocationForProvider("network");
        }
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            lastKnownLocationForProvider2 = this.getLastKnownLocationForProvider("gps");
        }
        if (lastKnownLocationForProvider2 != null && lastKnownLocationForProvider != null) {
            if (lastKnownLocationForProvider2.getTime() <= lastKnownLocationForProvider.getTime()) {
                return lastKnownLocationForProvider;
            }
        }
        else if (lastKnownLocationForProvider2 == null) {
            return lastKnownLocationForProvider;
        }
        return lastKnownLocationForProvider2;
    }
    
    private Location getLastKnownLocationForProvider(final String s) {
        if (this.mLocationManager != null) {
            try {
                if (this.mLocationManager.isProviderEnabled(s)) {
                    return this.mLocationManager.getLastKnownLocation(s);
                }
            }
            catch (Exception ex) {
                Log.d("TwilightManager", "Failed to get last known location", (Throwable)ex);
            }
        }
        return null;
    }
    
    private boolean isStateValid(final TwilightState twilightState) {
        return twilightState != null && twilightState.nextUpdate > System.currentTimeMillis();
    }
    
    private void updateState(@NonNull final Location location) {
        final TwilightState sTwilightState = TwilightManager.sTwilightState;
        final long currentTimeMillis = System.currentTimeMillis();
        final TwilightCalculator instance = TwilightCalculator.getInstance();
        instance.calculateTwilight(currentTimeMillis - 86400000L, location.getLatitude(), location.getLongitude());
        final long sunset = instance.sunset;
        instance.calculateTwilight(currentTimeMillis, location.getLatitude(), location.getLongitude());
        final boolean isNight = instance.state == 1;
        final long sunrise = instance.sunrise;
        final long sunset2 = instance.sunset;
        instance.calculateTwilight(86400000L + currentTimeMillis, location.getLatitude(), location.getLongitude());
        final long sunrise2 = instance.sunrise;
        long nextUpdate;
        if (sunrise == -1L || sunset2 == -1L) {
            nextUpdate = currentTimeMillis + 43200000L;
        }
        else {
            long n;
            if (currentTimeMillis > sunset2) {
                n = 0L + sunrise2;
            }
            else if (currentTimeMillis > sunrise) {
                n = 0L + sunset2;
            }
            else {
                n = 0L + sunrise;
            }
            nextUpdate = n + 60000L;
        }
        sTwilightState.isNight = isNight;
        sTwilightState.yesterdaySunset = sunset;
        sTwilightState.todaySunrise = sunrise;
        sTwilightState.todaySunset = sunset2;
        sTwilightState.tomorrowSunrise = sunrise2;
        sTwilightState.nextUpdate = nextUpdate;
    }
    
    boolean isNight() {
        final TwilightState sTwilightState = TwilightManager.sTwilightState;
        if (this.isStateValid(sTwilightState)) {
            return sTwilightState.isNight;
        }
        final Location lastKnownLocation = this.getLastKnownLocation();
        if (lastKnownLocation != null) {
            this.updateState(lastKnownLocation);
            return sTwilightState.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        final int value = Calendar.getInstance().get(11);
        return value < 6 || value >= 22;
    }
    
    private static class TwilightState
    {
        boolean isNight;
        long nextUpdate;
        long todaySunrise;
        long todaySunset;
        long tomorrowSunrise;
        long yesterdaySunset;
    }
}
