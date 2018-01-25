// 
// Decompiled by Procyon v0.5.30
// 

package com.ha0k3.helloworld;

import android.content.SharedPreferences$Editor;
import android.widget.EditText;
import android.text.TextUtils;
import com.ha0k3.helloworld.database.DBHelper;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import android.view.View;
import android.os.Environment;
import java.io.File;
import android.os.IBinder;
import android.content.ComponentName;
import android.database.sqlite.SQLiteDatabase;
import android.content.ServiceConnection;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View$OnClickListener
{
    public static final String Bind_MESSAGE = "Bind_TAG";
    private static final String EXTERNAL_FILE_NAME = "ext_file.txt";
    public static final String EXTRA_MESSAGE = "com.ha0k3.helloworld.MESSAGE";
    private static final String Exception_TAG = "Exception_TAG";
    public static final String INTERNAL_FILE = "file.txt";
    public static final String MSG_PAYLOAD = "MSGPAYLOAD";
    public static final String MYPREF = "mypref";
    private static final String Message_TAG = "Message_TAG";
    private Button bindService;
    private ServiceConnection connection;
    SQLiteDatabase database;
    private MyService.MyBinder myBinder;
    private Button startService;
    private Button stopService;
    private Button unbindService;
    
    static {
        System.loadLibrary("JniTest");
    }
    
    public MainActivity() {
        this.connection = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                MainActivity.this.myBinder = (MyService.MyBinder)binder;
                MainActivity.this.myBinder.startDownload("evil.bin");
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
            }
        };
    }
    
    private File getFile() {
        return new File(Environment.getExternalStorageDirectory(), "ext_file.txt");
    }
    
    public void ViewJniMessage(final View view) {
        Toast.makeText((Context)this, (CharSequence)this.helloJni(), 0).show();
    }
    
    public void delete_internal_ext_file(View file) {
        final File file2 = new File(this.getFilesDir(), "file.txt");
        file = (View)this.getFile();
        Label_0066: {
            if (!file2.exists()) {
                break Label_0066;
            }
            this.deleteFile("file.txt");
            Toast.makeText((Context)this, (CharSequence)"File deleted", 0).show();
            try {
                while (true) {
                    if (((File)file).exists()) {
                        ((File)file).delete();
                        Toast.makeText((Context)this, (CharSequence)"External File deleted", 0).show();
                        return;
                    }
                    Toast.makeText((Context)this, (CharSequence)"External File doesn't exist", 0).show();
                    return;
                    Toast.makeText((Context)this, (CharSequence)"File doesn't exist", 0).show();
                    continue;
                }
            }
            catch (Exception ex) {
                Log.i("Exception_TAG", "file delete failed: " + ((File)file).getAbsolutePath());
                ex.printStackTrace();
            }
        }
    }
    
    public native String helloJni();
    
    public void onClick(final View view) {
        switch (view.getId()) {
            default: {}
            case 2131427425: {
                this.startService(new Intent((Context)this, (Class)MyService.class));
            }
            case 2131427426: {
                this.stopService(new Intent((Context)this, (Class)MyService.class));
            }
            case 2131427427: {
                final Intent intent = new Intent((Context)this, (Class)MyService.class);
                intent.putExtra("Bind_TAG", "test_file_name");
                this.bindService(intent, this.connection, 1);
            }
            case 2131427428: {
                this.unbindService(this.connection);
            }
        }
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968604);
        this.database = new DBHelper((Context)this).getWritableDatabase();
        Toast.makeText((Context)this, (CharSequence)"Database acquired", 0).show();
        this.startService = (Button)this.findViewById(2131427425);
        this.stopService = (Button)this.findViewById(2131427426);
        this.bindService = (Button)this.findViewById(2131427427);
        this.unbindService = (Button)this.findViewById(2131427428);
        this.startService.setOnClickListener((View$OnClickListener)this);
        this.stopService.setOnClickListener((View$OnClickListener)this);
        this.bindService.setOnClickListener((View$OnClickListener)this);
        this.unbindService.setOnClickListener((View$OnClickListener)this);
        final String string = this.getSharedPreferences("mypref", 0).getString("MSGPAYLOAD", "KEY_NOT_FOUND");
        if (!TextUtils.isEmpty((CharSequence)string)) {
            ((EditText)this.findViewById(2131427430)).setText((CharSequence)string);
            Toast.makeText((Context)this, (CharSequence)("Message retrieved from shared_pref as : " + string), 0).show();
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Msg is blank", 0).show();
    }
    
    public void sendMessage(final View view) {
        final Intent intent = new Intent((Context)this, (Class)DisplayMessageActivity.class);
        final String string = ((EditText)this.findViewById(2131427430)).getText().toString();
        Log.i("Message_TAG", string);
        final SharedPreferences$Editor edit = this.getSharedPreferences("mypref", 0).edit();
        edit.putString("MSGPAYLOAD", string);
        edit.apply();
        intent.putExtra("com.ha0k3.helloworld.MESSAGE", string);
        this.startActivity(intent);
    }
    
    public void start_export(final View view) {
        Toast.makeText((Context)this, (CharSequence)"Trying to start export activity!", 1).show();
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.nccgroup.johnnyfive", "com.nccgroup.johnnyfive.AdminActivity"));
        this.startActivity(intent);
    }
    
    public void write_to_internal_ext_file(final View p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             2131427430
        //     3: invokevirtual   com/ha0k3/helloworld/MainActivity.findViewById:(I)Landroid/view/View;
        //     6: checkcast       Landroid/widget/EditText;
        //     9: invokevirtual   android/widget/EditText.getText:()Landroid/text/Editable;
        //    12: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //    15: astore          4
        //    17: aconst_null    
        //    18: astore_3       
        //    19: aconst_null    
        //    20: astore_1       
        //    21: new             Ljava/io/File;
        //    24: dup            
        //    25: ldc             "file.txt"
        //    27: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    30: pop            
        //    31: aload_0        
        //    32: invokespecial   com/ha0k3/helloworld/MainActivity.getFile:()Ljava/io/File;
        //    35: astore          5
        //    37: ldc             "Message_TAG"
        //    39: new             Ljava/lang/StringBuilder;
        //    42: dup            
        //    43: invokespecial   java/lang/StringBuilder.<init>:()V
        //    46: ldc_w           "exfile path: "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: aload           5
        //    54: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    63: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    66: pop            
        //    67: aload_0        
        //    68: ldc             "file.txt"
        //    70: iconst_0       
        //    71: invokevirtual   com/ha0k3/helloworld/MainActivity.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    74: astore_2       
        //    75: aload_2        
        //    76: astore_1       
        //    77: aload_2        
        //    78: astore_3       
        //    79: aload_2        
        //    80: aload           4
        //    82: invokevirtual   java/lang/String.getBytes:()[B
        //    85: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    88: aload_2        
        //    89: astore_1       
        //    90: aload_2        
        //    91: astore_3       
        //    92: aload_0        
        //    93: ldc_w           "Saving the text to file..."
        //    96: iconst_0       
        //    97: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   100: invokevirtual   android/widget/Toast.show:()V
        //   103: aload_2        
        //   104: invokevirtual   java/io/FileOutputStream.close:()V
        //   107: aload_2        
        //   108: astore_1       
        //   109: new             Ljava/io/FileOutputStream;
        //   112: dup            
        //   113: aload           5
        //   115: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   118: astore_2       
        //   119: aload_2        
        //   120: astore_1       
        //   121: aload_2        
        //   122: aload           4
        //   124: invokevirtual   java/lang/String.getBytes:()[B
        //   127: invokevirtual   java/io/FileOutputStream.write:([B)V
        //   130: aload_2        
        //   131: astore_1       
        //   132: aload_0        
        //   133: ldc_w           "Saving the text to file..."
        //   136: iconst_0       
        //   137: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   140: invokevirtual   android/widget/Toast.show:()V
        //   143: aload_2        
        //   144: invokevirtual   java/io/FileOutputStream.close:()V
        //   147: return         
        //   148: astore_1       
        //   149: aload_1        
        //   150: invokevirtual   java/io/IOException.printStackTrace:()V
        //   153: aload_2        
        //   154: astore_1       
        //   155: goto            109
        //   158: astore_2       
        //   159: aload_1        
        //   160: astore_3       
        //   161: aload_2        
        //   162: invokevirtual   java/io/IOException.printStackTrace:()V
        //   165: aload_1        
        //   166: astore_3       
        //   167: aload_0        
        //   168: new             Ljava/lang/StringBuilder;
        //   171: dup            
        //   172: invokespecial   java/lang/StringBuilder.<init>:()V
        //   175: ldc_w           "Exception: "
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: aload_2        
        //   182: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   191: iconst_0       
        //   192: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   195: invokevirtual   android/widget/Toast.show:()V
        //   198: aload_1        
        //   199: invokevirtual   java/io/FileOutputStream.close:()V
        //   202: goto            109
        //   205: astore_2       
        //   206: aload_2        
        //   207: invokevirtual   java/io/IOException.printStackTrace:()V
        //   210: goto            109
        //   213: astore_1       
        //   214: aload_3        
        //   215: invokevirtual   java/io/FileOutputStream.close:()V
        //   218: aload_1        
        //   219: athrow         
        //   220: astore_2       
        //   221: aload_2        
        //   222: invokevirtual   java/io/IOException.printStackTrace:()V
        //   225: goto            218
        //   228: astore_1       
        //   229: aload_1        
        //   230: invokevirtual   java/io/IOException.printStackTrace:()V
        //   233: return         
        //   234: astore_3       
        //   235: aload_1        
        //   236: astore_2       
        //   237: aload_2        
        //   238: astore_1       
        //   239: aload_3        
        //   240: invokevirtual   java/io/IOException.printStackTrace:()V
        //   243: aload_2        
        //   244: astore_1       
        //   245: aload_0        
        //   246: new             Ljava/lang/StringBuilder;
        //   249: dup            
        //   250: invokespecial   java/lang/StringBuilder.<init>:()V
        //   253: ldc_w           "Exception: "
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload_3        
        //   260: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: iconst_0       
        //   270: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   273: invokevirtual   android/widget/Toast.show:()V
        //   276: aload_2        
        //   277: invokevirtual   java/io/FileOutputStream.close:()V
        //   280: return         
        //   281: astore_1       
        //   282: aload_1        
        //   283: invokevirtual   java/io/IOException.printStackTrace:()V
        //   286: return         
        //   287: astore_3       
        //   288: aload_1        
        //   289: astore_2       
        //   290: aload_3        
        //   291: astore_1       
        //   292: aload_2        
        //   293: invokevirtual   java/io/FileOutputStream.close:()V
        //   296: aload_1        
        //   297: athrow         
        //   298: astore_2       
        //   299: aload_2        
        //   300: invokevirtual   java/io/IOException.printStackTrace:()V
        //   303: goto            296
        //   306: astore_3       
        //   307: aload_1        
        //   308: astore_2       
        //   309: aload_3        
        //   310: astore_1       
        //   311: goto            292
        //   314: astore_3       
        //   315: goto            237
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  67     75     158    213    Ljava/io/IOException;
        //  67     75     213    228    Any
        //  79     88     158    213    Ljava/io/IOException;
        //  79     88     213    228    Any
        //  92     103    158    213    Ljava/io/IOException;
        //  92     103    213    228    Any
        //  103    107    148    158    Ljava/io/IOException;
        //  109    119    234    237    Ljava/io/IOException;
        //  109    119    287    292    Any
        //  121    130    314    318    Ljava/io/IOException;
        //  121    130    306    314    Any
        //  132    143    314    318    Ljava/io/IOException;
        //  132    143    306    314    Any
        //  143    147    228    234    Ljava/io/IOException;
        //  161    165    213    228    Any
        //  167    198    213    228    Any
        //  198    202    205    213    Ljava/io/IOException;
        //  214    218    220    228    Ljava/io/IOException;
        //  239    243    306    314    Any
        //  245    276    306    314    Any
        //  276    280    281    287    Ljava/io/IOException;
        //  292    296    298    306    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 174, Size: 174
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
