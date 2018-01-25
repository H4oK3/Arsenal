// 
// Decompiled by Procyon v0.5.30
// 

package com.ha0k3.helloworld;

import android.os.Binder;
import android.util.Log;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public class MyService extends Service
{
    public static final String ServiceTag = "Service_Tag";
    private MyBinder mBinder;
    
    public MyService() {
        this.mBinder = new MyBinder();
    }
    
    public IBinder onBind(final Intent intent) {
        intent.getStringExtra("Bind_TAG");
        Log.d("Service_Tag", "onBind() executed");
        return (IBinder)this.mBinder;
    }
    
    public void onCreate() {
        Log.d("Service_Tag", "Created Srvis..");
        super.onCreate();
    }
    
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service_Tag", "onDestroy() executed");
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        Log.d("Service_Tag", "onStartCommand() executed");
        return super.onStartCommand(intent, n, n2);
    }
    
    class MyBinder extends Binder
    {
        public void startDownload(final String s) {
            Log.d("Service_Tag", "startDownload() executed, trying to download: " + s);
        }
    }
}
