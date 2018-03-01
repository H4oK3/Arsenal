package com.ha0k3.helloworld;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ha0k3.helloworld.database.DBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "com.ha0k3.helloworld.MESSAGE";
    public static final String Bind_MESSAGE = "Bind_TAG";

    private static final String Message_TAG = "Message_TAG";
    private static final String Exception_TAG = "Exception_TAG";
    public static final String INTERNAL_FILE = "file.txt";
    public static final String MYPREF = "mypref";
    public static final String MSG_PAYLOAD = "MSGPAYLOAD";
    private static final String EXTERNAL_FILE_NAME = "ext_file.txt";
    public static final String MAL_FILE = "malfiles";
    private static final String SHARED_PROVIDER_AUTHORITY = "com.ha0k3.helloworld.FileProvider";

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private MyService.MyBinder myBinder;
    private BroadcastReceiver br;

    SQLiteDatabase database;

    public static final String message = "haottp message";
    public static final String TAG = "HelloWorld";

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

        // Broadcast Receiver
        br = new MyReceiver();
        IntentFilter filter = new IntentFilter("com.flagstore.ctf.OUTGOING_INTENT");
        registerReceiver(br,filter);
        //        Intent intent = new Intent().setClassName("com.demo.filereceiver", "com.demo.filereceiver.MainActivity");
//        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

    }

    public void share(View view) throws IOException {
        // Create a random image and save it in private app folder
        final File malfiles = new File(getFilesDir(), MAL_FILE);
        malfiles.mkdirs();
        final File sharedFile = File.createTempFile("test", ".html", malfiles);
        sharedFile.createNewFile();
        Log.d("Message_TAG", "createfile success");
        final Uri uri = FileProvider.getUriForFile(this, SHARED_PROVIDER_AUTHORITY, sharedFile);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(sharedFile);
            fileOutputStream.write("<script>alert(1)</script>".getBytes());
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

        Log.d("Message_TAG: uri = ", uri.toString());

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT,"Hey!");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        startActivity(intent);


//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);

        Toast.makeText(this, "Malfile created", Toast.LENGTH_SHORT).show();
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
        ComponentName cn=new ComponentName("com.nccgroup.johnnyfive", "com.nccgroup.johnnyfive.AdminActivity");
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
        Uri file_uri = Uri.fromFile(file);
        Log.i("Message_TAG", "intfile uri: " + file_uri.toString());
        Log.i("Message_TAG", "intfile path: " + file.getAbsolutePath());
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


    private void showMessage() {
        Context mContext = getApplicationContext();
        Toast.makeText(mContext, MainActivity.message, Toast.LENGTH_SHORT).show();
    }


    private void sendRequest() {
//        String hostname = "httpbin.org";
        String hostname = "circle.com";
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
//                .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
                  .add(hostname,"sha256/HCDU/5saOGpZ7R9/WOvLfIjSQeLUTaPrmc9wbL6zhxg=")
//                .add(hostname,"sha1/LHspp8BlMzJeqfRrY/MmhzxEa3Q=")
//                .add(hostname,"sha1/UbxPdxcIz+UJ3enqpVSOkcBneFM=")
//                .add(hostname,"")
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build();

        Request request = new Request.Builder()
                .url("https://" + hostname + "/headers")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if(body != null) {
                String bodyData = body.string();
                JSONObject bodyJSON = new JSONObject(bodyData);

                Log.d(TAG, bodyJSON.toString(4));
            }
        } catch (IOException|JSONException e) {
            Log.e(TAG, null, e);
        }
    }

    public void SendRequest(View view) {
        showMessage();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sendRequest();
            }
        });
        thread.start();
    }

    public void start_diva_activity(View view) {
//        adb shell am start -n jakhar.aseem.diva/.APICredsActivity
        Toast.makeText(MainActivity.this, "Trying to start diva activity!",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction("jakhar.aseem.diva.action.VIEW_CREDS");
        startActivity(intent);
    }

    public void showWebView(View view) {
        WebView webview = new WebView(this);
        setContentView(webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);     // this makes ajax to local file possible
        webSettings.setAllowContentAccess(true);



        String data = "<html><body>You scored <b>1337</b> points.<script>document.write(1)" +
                "</script>\n" +
                "<script>\n" +
                "document.write(2);\n" +
                "var httpRequest;\n" +
                "makeRequest();\n" +
                "function makeRequest() {\n" +
                "  httpRequest = new XMLHttpRequest();\n" +
                "  httpRequest.onreadystatechange = alertContents;\n" +
                "  httpRequest.open(\"GET\", \"file:///etc/hosts\");\n" +
                "  httpRequest.send();\n" +
                "}\n" +
                "function alertContents() {\n" +
                "  if (httpRequest.readyState === XMLHttpRequest.DONE){\n" +
                "    document.write(httpRequest.responseText)\n" +
                "  }\n" +
                "}\n" +
                "</script>";
//        String data = "<html><body>You scored <b>1337</b> points.<button onclick='" + script + ")'>clickme</script></body></html>";
//        String data = "<html><body>You scored <b>1337</b> points.<script>document.write(1)</script>" + script + "</body></html>";
        Log.d(TAG,data);
        webview.loadDataWithBaseURL("file:///bla/bla/bla", data, "text/HTML", "UTF-8", null);
//        webview.loadUrl("file:///etc/hosts");
    }
}
