- This POST contains a descriptive method of how I solve [DIVA](https://github.com/payatu/diva-android);

**General**:
The app was easy to reverse since it hasn't been really zipped of obfuscated.

`prep_android diva_beta.apk`

I did this to track down each activities and functions:

1. ag the keywords in the app
2. Found them in strings.xml, e.g. `<string name="d1">1. Insecure Logging</string>`
3. Look in AndroidManifest.xml for d1 and find the activities.
4. In decompiled java file, it gave a Id, e.g. `findViewById(2131492987)`; just ag that id would help me make sense of the code flow.


**Solve**:

1. Sensitive info in logcat.

~~~JAVA
// jakhar.aseem.diva.LogActivity.checkout
Log.e("diva-log", "Error while processing transaction with credit card: " + ((EditText)view).getText().toString());
~~~

2. HardCode secret.

~~~JAVA
// HardcodeActivity.access
if (((EditText)this.findViewById(2131492987)).getText().toString().equals("vendorsecretkey")) {
            Toast.makeText((Context)this, (CharSequence)"Access granted! See you on the other side :)", 0).show();
            return;
        }
~~~

3. User credentials in sharedpref files in plaintext.

~~~JAVA
// InsecureDataStorage1Activity.saveCredentials
public void saveCredentials(final View view) {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences((Context)this).edit();
        final EditText editText = (EditText)this.findViewById(2131493000);
        final EditText editText2 = (EditText)this.findViewById(2131493001);
        edit.putString("user", editText.getText().toString());
        edit.putString("password", editText2.getText().toString());
        edit.commit();
        Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
    }
// Saved to /data/data/jakhar.aseem.diva/shared_prefs/jakhar.aseem.diva_preferences.xml
~~~

4. User creds in database files in plaintext.
Check InsecureDataStorage2Activity file.

5. User creds in file.

~~~JAVA
//InsecureDataStorage3Activity.saveCredentials
public void saveCredentials(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493006);
        final EditText editText2 = (EditText)this.findViewById(2131493007);
        final File file = new File(this.getApplicationInfo().dataDir);
        try {
            final File tempFile = File.createTempFile("uinfo", "tmp", file);
            tempFile.setReadable(true);
            tempFile.setWritable(true);
            final FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write(editText.getText().toString() + ":" + editText2.getText().toString() + "\n");
            fileWriter.close();
            Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
        }
        catch (Exception ex) {
            Toast.makeText((Context)this, (CharSequence)"File error occurred", 0).show();
            Log.d("Diva", "File error: " + ex.getMessage());
        }
    }
// /data/data/jakhar.aseem.diva/uinfo853811659tmp
~~~

6. User creds in external file.

~~~JAVA
//InsecureDataStorage4Activity.saveCredentials
public void saveCredentials(final View view) {
        final EditText editText = (EditText)this.findViewById(2131493010);
        final EditText editText2 = (EditText)this.findViewById(2131493011);
        final File externalStorageDirectory = Environment.getExternalStorageDirectory();
        try {
            final File file = new File(externalStorageDirectory.getAbsolutePath() + "/.uinfo.txt");
            file.setReadable(true);
            file.setWritable(true);
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(editText.getText().toString() + ":" + editText2.getText().toString() + "\n");
            fileWriter.close();
            Toast.makeText((Context)this, (CharSequence)"3rd party credentials saved successfully!", 0).show();
        }
        catch (Exception ex) {
            Toast.makeText((Context)this, (CharSequence)"File error occurred", 0).show();
            Log.d("Diva", "File error: " + ex.getMessage());
        }
    }
// /data/media/0/.uinfo.txt
~~~

7. SQL injection.
Check the `SQLInjectionActivity` file

8. WebView loadURL without validate protocal, can input sth like:
`file:///data/data/jakhar.aseem.diva/uinfo853811659tmp` to access sensitive file
Note that although in the code it enabled javascript; js still cannot be executed in this webview; I think more java code/research needed.
**Mark this as TODO**
~~~JAVA
InputValidation2URISchemeActivity.get
public void get(final View view) {
        ((WebView)this.findViewById(2131492995)).loadUrl(((EditText)this.findViewById(2131492993)).getText().toString());
    }
~~~

9. Sensitive data leakage via Intent exposing;
`adb shell am start -n jakhar.aseem.diva/.APICredsActivity`
The command above can call APICredsActivity activity which was defined in manifest.xml using intent-filter.
To make it secure; sensitive activity should not be defined within <intent-filter>; it should be only accessible within the same app.

~~~XML
// Vulnerable code
<activity android:label="@string/apic_label" android:name="jakhar.aseem.diva.APICredsActivity">
    <intent-filter>
        <action android:name="jakhar.aseem.diva.action.VIEW_CREDS"/>
        <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>
</activity>

//Secure way:
<activity android:label="@string/apic_label" android:name="jakhar.aseem.diva.APICredsActivity">
</activity>

~~~

10. Same issue but need to be triggered with intent extra values.

`adb shell am start -n jakhar.aseem.diva/.APICreds2Activity --ez check_pin false`

~~~JAVA
// In decompiled code:
intent.putExtra(this.getString(2131099686), checked);
if (intent.resolveActivity(this.getPackageManager()) != null) {
    this.startActivity(intent);
    return;
}

// track the ID 2131099686 ->  R.java:
public static final int chk_pin = 2131099686;

// track the chk_pin  ->  diva-beta_de/res/values/strings.xml:
<string name="chk_pin">check_pin</string>
~~~

11. Sensitive data exposed via Content Provider;
`adb shell content query --uri content://jakhar.aseem.diva.provider.notesprovider/notes`

    + About Content provider:
    A content provider manages access to a central repository of data. A provider is part of an Android application, which often provides its own UI for working with the data. However, content providers are primarily intended to be used by other applications, which access the provider using a provider client object. Together, providers and provider clients offer a consistent, standard interface to data that also handles inter-process communication and secure data access.

For this app, follow these steps to track it down:

~~~JAVA

// AndroidManifest.xml
<provider android:authorities="jakhar.aseem.diva.provider.notesprovider" android:enabled="true" android:exported="true" android:name="jakhar.aseem.diva.NotesProvider"/>

// AccessControl3NotesActivity.java(decompiled)
((ListView)this.findViewById(2131492981)).setAdapter((ListAdapter)new SimpleCursorAdapter((Context)this, 2130968630, this.getContentResolver().query(NotesProvider.CONTENT_URI, new String[] { "_id", "title", "note" }, (String)null, (String[])null, (String)null), new String[] { "title", "note" }, new int[] { 2131493043, 2131493044 }, 0));
            editText.setVisibility(4);
            button.setVisibility(4);
            return;
        }

// -> track down the core function should be in NotesProvider.java
// NotesProvider class is inherited from ContentProvider class, it is basically an API for all data storage operations;

CONTENT_URI = Uri.parse("content://jakhar.aseem.diva.provider.notesprovider/notes");
// Track down the URI, and can use adb to query it
~~~

12. HardCoded secret in JNI

~~~JAVA

// Hardcode2Activity.java(decompiled)
private DivaJni djni;
public void access(final View view) {
    if (this.djni.access(((EditText)this.findViewById(2131492990)).getText().toString()) != 0) {
        Toast.makeText((Context)this, (CharSequence)"Access granted! See you on the other side :)", 0).show();
        return;
    }
    Toast.makeText((Context)this, (CharSequence)"Access denied! See you in hell :D", 0).show();
}
~~~

Should be able to solve in 2 ways; 
    + Frida hook access func(JNI); 
    
    ~~~JAVASCRIPT
    // frida -U -f jakhar.aseem.diva --no-pause -l poc.js
    // olsdfgad;lh

    setImmediate(function() {
      Java.perform(function() {
          console.log("[*] Here we go");
          var strncmp = undefined;
          imports = Module.enumerateImportsSync("libdivajni.so");
          for(i = 0; i < imports.length; i++) {
          if(imports[i].name == "strncmp") {
                  strncmp = imports[i].address;
                  break;
              }
          }
          Interceptor.attach(Module.findExportByName("libdivajni.so", "Java_jakhar_aseem_diva_DivaJni_access"), {
          onEnter: function(args) {
            console.log("Access-jni called");
          },
          // onLeave: function(retval) {
          //   if (this.flag) {
          //     // print retval
          //     console.log("\nretval: " + retval);
          //     console.warn("\n*** exiting open");
          //   }
          // }
          });
          Interceptor.attach(strncmp, {
              onEnter: function (args) {
                var arg0 = Memory.readCString(args[0]).toString();
                var arg1 = Memory.readCString(args[1]).toString();
                if(!(arg0.startsWith('debug')) & (arg1 == 'aaaaaaaaaaa')){
                console.log(arg0 + ": " + arg1)
                // [*] Intercepting strncmp
                // olsdfgad;lh: aaaaaaaaaaa
              }
            },
          });
          console.log("[*] Intercepting strncmp");
        });
    });
    ~~~

    + Reverse engineering jni lib:
    
    ~~~
    // Hopper pseudo code:
    int Java_jakhar_aseem_diva_DivaJni_access(int arg0) {
    r0 = (*(*arg0 + 0x2a4))(arg0, r2, 0x0, *(*arg0 + 0x2a4), r3, lr);
    r0 = strncmp("olsdfgad;lh", r0, 0xb);
    r0 = 0x1 - r0;
    if (r0 < 0x0) {
            r0 = 0x0;
    }
    return r0;
    }
    ~~~


13. Buffer Overflow in JNI function:

~~~
// disassemble in Hopper
int Java_jakhar_aseem_diva_DivaJni_initiateLaunchSequence(int arg0) {
    sp = sp - 0x20;
    strcpy(sp + 0x4, (*(*arg0 + 0x2a4))(arg0, r2, 0x0, *(*arg0 + 0x2a4), stack[2040], stack[2041]));
    r3 = stack[2041];
    r1 = sp + 0x4;
    if (r3 == 0x21) {
            r3 = 0x2e;
    }
    r0 = strncmp(".dotdot", r1, 0x7);
    r0 = 0x1 - r0;
    if (r0 < 0x0) {
            r0 = 0x0;
    }
    return r0;
}
~~~
If buffer exceeds 20; it would cause buffer overflow; also same approach used for Level 12 can be used here to get the secret key.




