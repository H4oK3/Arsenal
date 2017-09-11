package com.ha0k3.helloworld;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.aware.PublishConfig;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.ha0k3.helloworld.MESSAGE";
    public static final String Bind_MESSAGE = "Bind_TAG";

    private static final String Message_TAG = "Message_TAG";
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            String test_file_name = "evil.bin";
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload(test_file_name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button)findViewById(R.id.start_service);
        stopService = (Button)findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText);
        String message = editText.getText().toString();
        Log.i(Message_TAG,message);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // call exported activity
    public void start_export(View view){
        Toast.makeText(MainActivity.this, "Trying to start export activity!",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        ComponentName cn=new ComponentName("com.nccgroup.johnnyfive",
                "com.nccgroup.johnnyfive.AdminActivity");
        intent.setComponent(cn);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindIntent.putExtra(Bind_MESSAGE,"test_file_name");
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
