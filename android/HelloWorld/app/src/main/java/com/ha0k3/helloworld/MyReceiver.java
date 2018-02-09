package com.ha0k3.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.ha0k3.helloworld.MainActivity.TAG;

public class MyReceiver extends BroadcastReceiver {

    public static String msg;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "Action : " + intent.getAction());
        msg = intent.getStringExtra("msg");
        Log.d(TAG, "onReceive: " + msg.toString());
    }
}
