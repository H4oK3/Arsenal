package com.ha0k3.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class IntentReceiver extends AppCompatActivity {

    public static final String TAG = "IntentReceiver";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_receiver);


        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");

        Log.d(TAG, "IntentReceiver: " + msg);
    }
}
