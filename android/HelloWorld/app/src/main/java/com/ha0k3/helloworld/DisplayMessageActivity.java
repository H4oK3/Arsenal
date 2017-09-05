package com.ha0k3.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import java.io.FileOutputStream;

public class DisplayMessageActivity extends AppCompatActivity {
    public String msg = "";
    public static final String DisplayTag = "Display_Tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(msg);
    }

    public void saveFile(View view){
        // Save to internal File
        Log.d(DisplayTag, "saveFile() called with msg:" + msg);
        String filename = "TestFile";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(msg.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
