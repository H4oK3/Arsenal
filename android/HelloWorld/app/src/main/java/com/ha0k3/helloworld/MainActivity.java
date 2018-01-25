package com.ha0k3.helloworld;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ha0k3.helloworld.database.DBHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.ha0k3.helloworld.MESSAGE";
    public static final String Bind_MESSAGE = "Bind_TAG";

    private static final String Message_TAG = "Message_TAG";
    private static final String Exception_TAG = "Exception_TAG";
    public static final String INTERNAL_FILE = "file.txt";
    public static final String MYPREF = "mypref";
    public static final String MSG_PAYLOAD = "MSGPAYLOAD";
    private static final String EXTERNAL_FILE_NAME = "ext_file.txt";
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private MyService.MyBinder myBinder;

    SQLiteDatabase database;

    public native String helloJni();

    static {
        System.loadLibrary("JniTest");
    }
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

        SQLiteOpenHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();  //will call onCreate if db not exist
        Toast.makeText(this, "Database acquired", Toast.LENGTH_SHORT).show();

        startService = (Button)findViewById(R.id.start_service);
        stopService = (Button)findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        SharedPreferences prefs = getSharedPreferences(MainActivity.MYPREF, MODE_PRIVATE);
        String msg = prefs.getString(MSG_PAYLOAD,"KEY_NOT_FOUND");

        if(!TextUtils.isEmpty(msg)){
            EditText et = (EditText) findViewById(R.id.editText);
            et.setText(msg);
            Toast.makeText(this, "Message retrieved from shared_pref as : " + msg, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Msg is blank", Toast.LENGTH_SHORT).show();
        }

    }

    private File getFile(){
        return new File(Environment.getExternalStorageDirectory(),EXTERNAL_FILE_NAME);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText);
        String message = editText.getText().toString();
        Log.i(Message_TAG,message);

        SharedPreferences.Editor editor = getSharedPreferences(MYPREF, MODE_PRIVATE).edit();
        // PREF HAS TO BE PRIVATE TO CURRENT APP
        editor.putString(MSG_PAYLOAD, message);
        editor.apply();
        // value saved

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

    public void write_to_internal_ext_file(View view) {

        EditText et = (EditText) findViewById(R.id.editText);
        String msg = et.getText().toString();
        FileOutputStream fileOutputStream = null;

        File file = new File(INTERNAL_FILE);
        File extfile = getFile();

        Log.i("Message_TAG", "exfile path: " + extfile.getAbsolutePath());
        // write to internal file
        try {
            fileOutputStream = openFileOutput(INTERNAL_FILE, MODE_PRIVATE);
            fileOutputStream.write(msg.getBytes());
            Toast.makeText(this, "Saving the text to file...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //write to external file
        try {
//            fileOutputStream = openFileOutput(INTERNAL_FILE, MODE_PRIVATE);
            fileOutputStream = new FileOutputStream(extfile);
            fileOutputStream.write(msg.getBytes());
            Toast.makeText(this, "Saving the text to file...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete_internal_ext_file(View view) {
        File file = new File(getFilesDir(), INTERNAL_FILE);
        File extfile = getFile();

        //delete internal file
        if (file.exists()){
            deleteFile(INTERNAL_FILE);
            Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "File doesn't exist", Toast.LENGTH_SHORT).show();
        }

        //external file delete
        try {
            if (extfile.exists()){
    //            deleteFile(INTERNAL_FILE);
                extfile.delete();
                Toast.makeText(this, "External File deleted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "External File doesn't exist", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.i(Exception_TAG, "file delete failed: " + extfile.getAbsolutePath());
            e.printStackTrace();
        }
    }
    public void ViewJniMessage(View view) {
        Toast.makeText(this, helloJni(), Toast.LENGTH_SHORT).show();
    }
}
