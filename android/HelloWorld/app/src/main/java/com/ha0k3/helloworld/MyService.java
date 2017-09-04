package com.ha0k3.helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by hawk3 on 9/4/17.
 */

public class MyService extends Service {
    public static final String ServiceTag = "Service_Tag";

    private MyBinder mBinder = new MyBinder();
    @Override
    public void onCreate(){
        Log.d(ServiceTag, "Created Srvis..");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(ServiceTag, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTag, "onDestroy() executed");
    }


    @Override
    public IBinder onBind(Intent intent){
        // Relate Service and Activity
        String msg = intent.getStringExtra(MainActivity.Bind_MESSAGE);
        Log.d(ServiceTag, "onBind() executed");
        return mBinder;
    }

    class MyBinder extends Binder {
        public void startDownload(String fileName) {
            Log.d(ServiceTag, "startDownload() executed, trying to download: " + fileName);
            // Do download as backgroud service
        }
    }
}
