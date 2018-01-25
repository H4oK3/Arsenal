// 
// Decompiled by Procyon v0.5.30
// 

package com.ha0k3.helloworld;

import java.io.FileOutputStream;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity
{
    public static final String DisplayTag = "Display_Tag";
    public String msg;
    
    public DisplayMessageActivity() {
        this.msg = "";
    }
    
    public boolean isExternalStorageReadable() {
        final String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }
    
    public boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968603);
        this.msg = this.getIntent().getStringExtra("com.ha0k3.helloworld.MESSAGE");
        ((TextView)this.findViewById(2131427422)).setText((CharSequence)this.msg);
    }
    
    public void saveFile(final View view) {
        Log.d("Display_Tag", "saveFile() called with msg:" + this.msg);
        try {
            final FileOutputStream openFileOutput = this.openFileOutput("TestFile", 0);
            openFileOutput.write(this.msg.getBytes());
            openFileOutput.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
