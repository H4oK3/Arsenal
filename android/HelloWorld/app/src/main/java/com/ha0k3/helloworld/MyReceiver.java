package com.ha0k3.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.ha0k3.helloworld.MainActivity.TAG;

public class MyReceiver extends BroadcastReceiver {

    public static String msg;
    public static String uri_data;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Received sth!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Action : " + intent.getAction());
//        msg = intent.getStringExtra("msg");
        uri_data = intent.getDataString();
        Log.d(TAG, "onReceive: " + uri_data.toString());
    }
}
