**HelloWorld**
A simple Android app helps me understand how it works.

**Some generic notes**

~~~
Patch apk and sign it again
apktool d UnCrackable-Level2.apk

#    invoke-direct {p0}, Lsg/vantagepoint/uncrackable2/MainActivity;->init()V `
#    invoke-static {v0}, Ljava/lang/System;->exit(I)V`

zipalign -v 4 ./dist/UnCrackable-Level2.apk patch.apk

#Sign it
jarsigner -verbose -keystore ~/.android/debug.keystore patch.apk androiddebugkey
#Note Keystore password is `android`, key alias is `androiddebugkey` and key password again `android`.

~~~

**Cert Pinning?**
Look for some generic ones, e.g. okhttp3, x509, etc.

~~~JAVA
TrustManager tm[] = {new PinningTrustManager(some_hash)};
~~~

~~~BASH
# Get server hash:
openssl s_client -connect host:port | openssl x509 -pubkey -noout > /tmp/cert.text
cat /tmp/cert.text |  sed '/^-/d' | awk '{printf "%s", $1}' | shasum -a 256

# Get our own hash
openssl x509 -inform der -in cacert.der -out burp.pem
cat burp.pem | shasum -a 256

# 9451fad31721a78d103918cbb5ac08e8fe3c261ce4da47c0044ba7065893450c
~~~

**Notes about URI Scheme**

~~~XML
<intent-filter>
    <data
        android:host=<HOST>
        android:scheme=<SCHEME>/>

    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.BROWSABLE" />
    <category android:name="android.intent.category.DEFAULT" />
</intent-filter>

~~~


`intent://Host/#Intent;scheme={{scheme}};package={{packagename}};S.browser_fallback_url=https://www.google.com;end`

**Note**: If the activity is not exported, the browser cannot lauch it via intent protocol.

~~~
adb shell am start -a {{ACTION}} -d "{{SCHEME}}://{{HOST}}" {{APP_NAME}}
~~~


**Notes about symlink file leakage**
If the app does this:
Take URI scheme or file path to do copy or backup stuff:
e.g. Target App(TA) takes file name and path and copy it to external storage; it might use several checks such as: file path cannot contain app folder name.

This can be exploit using symlink technique:
In a malicious app, do this:

- Make a symlink file in current app dir pointing to target file;
- chmod 777 on this file(so TA can access this file), supply this file to TA.
- TA would happily accept the symlink and make a copy of target file in external storage.

This blog: <https://hackerone.com/reports/161710> is a great example.

**Notes about webview:**

- Enable Webview Debug:

~~~
setTimeout(function() {
    Java.perform(function() {
      console.log("In da house..")
      clazz_Thread = Java.use("java.lang.Thread");
    WebView_hook = Java.use("android.webkit.WebView");
    var overloadz_WebView_hook = eval("WebView_hook.setWebViewClient.overloads");
    var ovl_count_WebView_hook = overloadz_WebView_hook.length;
    

    var cell = {}

    for (var i = 0; i < ovl_count_WebView_hook; i++) {
      setWebViewClient_hook = eval('WebView_hook.setWebViewClient.overloads[i]')
        setWebViewClient_hook.implementation = function () {
          this.setWebContentsDebuggingEnabled(true)
          this.setWebViewClient.apply(this, arguments)
        }
    }


// Added Hook 

    });
}, 0);
~~~

~~~
webview.load(url);
webview.loadDataWithBaseURL(url, data, "text/HTML", "UTF-8", null);
if the url uses file:/// then it is possible to use AJAX to access local file system.
There are 2 requirement for this attack:
webSettings.setJavaScriptEnabled(true);   // enables javascript
webSettings.setAllowFileAccessFromFileURLs(true);   // enables file:/// access
~~~

The following code snippet allows local file access, code also updated in my `HelloWorld` app in the repo.

~~~JAVA
public void showWebView(View view) {
    WebView webview = new WebView(this);
    setContentView(webview);
    WebSettings webSettings = webview.getSettings();
    webSettings.setJavaScriptEnabled(true);
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
    Log.d(TAG,data);
    webview.loadDataWithBaseURL("file:///bla/bla/bla", data, "text/HTML", "UTF-8", null);
}
~~~

**Little smali Notes**

~~~
# System.loadLibrary("JNIDecrypt");
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 47
    const-string v0, "JNIDecrypt"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 48
    return-void
.end method
~~~

~~~
# public native byte[] mydecryto(final byte[] array, final String s)
# jbyteArray mydecryto(JNIEnv *env, jobject jclazz, jbyteArray paramArrayOfByte, jstring paramString)
.method public native mydecryto([BLjava/lang/String;)[B
.end method
~~~

- `invoke-static` 是类静态方法的调用，编译时，静态确定的；
- `invoke-virtual` 虚方法调用，调用的方法运行时确认实际调用，和实例引用的实际对象有关，动态确认的，一般是带有修饰符protected或public的方法；
- `invoke-direct{param1, param2},Caller;->func_name(Param_type)Ret_type;`; param1 usually is `this`

~~~
getSharedPreferences("mypref", MODE_PRIVATE).edit();


				||
				VV
const-string v4, "mypref"

const/4 v5, 0x0

invoke-virtual {p0, v4, v5}, Lcom/ha0k3/helloworld/MainActivity;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

move-result-object v4	
~~~



**prep_android**

A simple bash program to get apk decompiled and reversed.

**Useful adb command:**

~~~

adb shell pm list packages    # list all packages
adb shell pm path <package_name> 	#find apk
adb shell dumpsys package | grep -i <package_name> | grep Activity

adb shell am start -n <package_name>/.<Activity_name> 	#start Activity
~~~

~~~
adb shell am
usage: am [subcommand] [options]
usage: am start [-D] [-W] [-P <FILE>] [--start-profiler <FILE>]
               [--R COUNT] [-S] [--opengl-trace] <INTENT>
       am startservice <INTENT>
       am force-stop <PACKAGE>
       am kill <PACKAGE>
       am kill-all
       am broadcast <INTENT>
       am instrument [-r] [-e <NAME> <VALUE>] [-p <FILE>] [-w]
               [--no-window-animation] <COMPONENT>
       am profile start <PROCESS> <FILE>
       am profile stop [<PROCESS>]
       am dumpheap [flags] <PROCESS> <FILE>
       am set-debug-app [-w] [--persistent] <PACKAGE>
       am clear-debug-app
       am monitor [--gdb <port>]
       am screen-compat [on|off] <PACKAGE>
       am display-size [reset|MxN]
       am to-uri [INTENT]
       am to-intent-uri [INTENT]

am start: start an Activity.  Options are:
    -D: enable debugging
    -W: wait for launch to complete
    --start-profiler <FILE>: start profiler and send results to <FILE>
    -P <FILE>: like above, but profiling stops when app goes idle
    -R: repeat the activity launch <COUNT> times.  Prior to each repeat,
        the top activity will be finished.
    -S: force stop the target app before starting the activity
    --opengl-trace: enable tracing of OpenGL functions

am startservice: start a Service.

am force-stop: force stop everything associated with <PACKAGE>.

am kill: Kill all processes associated with <PACKAGE>.  Only kills.
  processes that are safe to kill -- that is, will not impact the user
  experience.

am kill-all: Kill all background processes.

am broadcast: send a broadcast Intent.

am instrument: start an Instrumentation.  Typically this target <COMPONENT>
  is the form <TEST_PACKAGE>/<RUNNER_CLASS>.  Options are:
    -r: print raw results (otherwise decode REPORT_KEY_STREAMRESULT).  Use with
        [-e perf true] to generate raw output for performance measurements.
    -e <NAME> <VALUE>: set argument <NAME> to <VALUE>.  For test runners a
        common form is [-e <testrunner_flag> <value>[,<value>...]].
    -p <FILE>: write profiling data to <FILE>
    -w: wait for instrumentation to finish before returning.  Required for
        test runners.
    --no-window-animation: turn off window animations will running.

am profile: start and stop profiler on a process.

am dumpheap: dump the heap of a process.  Options are:
    -n: dump native heap instead of managed heap

am set-debug-app: set application <PACKAGE> to debug.  Options are:
    -w: wait for debugger when application starts
    --persistent: retain this value

am clear-debug-app: clear the previously set-debug-app.

am monitor: start monitoring for crashes or ANRs.
    --gdb: start gdbserv on the given port at crash/ANR

am screen-compat: control screen compatibility mode of <PACKAGE>.

am display-size: override display size.

am to-uri: print the given Intent specification as a URI.

am to-intent-uri: print the given Intent specification as an intent: URI.

<INTENT> specifications include these flags and arguments:
    [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>]
    [-c <CATEGORY> [-c <CATEGORY>] ...]
    [-e|--es <EXTRA_KEY> <EXTRA_STRING_VALUE> ...]
    [--esn <EXTRA_KEY> ...]
    [--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE> ...]
    [--ei <EXTRA_KEY> <EXTRA_INT_VALUE> ...]
    [--el <EXTRA_KEY> <EXTRA_LONG_VALUE> ...]
    [--ef <EXTRA_KEY> <EXTRA_FLOAT_VALUE> ...]
    [--eu <EXTRA_KEY> <EXTRA_URI_VALUE> ...]
    [--ecn <EXTRA_KEY> <EXTRA_COMPONENT_NAME_VALUE>]
    [--eia <EXTRA_KEY> <EXTRA_INT_VALUE>[,<EXTRA_INT_VALUE...]]
    [--ela <EXTRA_KEY> <EXTRA_LONG_VALUE>[,<EXTRA_LONG_VALUE...]]
    [--efa <EXTRA_KEY> <EXTRA_FLOAT_VALUE>[,<EXTRA_FLOAT_VALUE...]]
    [-n <COMPONENT>] [-f <FLAGS>]
    [--grant-read-uri-permission] [--grant-write-uri-permission]
    [--debug-log-resolution] [--exclude-stopped-packages]
    [--include-stopped-packages]
    [--activity-brought-to-front] [--activity-clear-top]
    [--activity-clear-when-task-reset] [--activity-exclude-from-recents]
    [--activity-launched-from-history] [--activity-multiple-task]
    [--activity-no-animation] [--activity-no-history]
    [--activity-no-user-action] [--activity-previous-is-top]
    [--activity-reorder-to-front] [--activity-reset-task-if-needed]
    [--activity-single-top] [--activity-clear-task]
    [--activity-task-on-home]
    [--receiver-registered-only] [--receiver-replace-pending]
    [--selector]
    [<URI> | <PACKAGE> | <COMPONENT>]
~~~

**Frida_writeup**

- Get Frida started

~~~BASH
# Make sure SELinux protection is off
setenforce 0
./data/local/tmp/frida-server & 

frida -U -l poc.js <package_name>		# load poc.js and hook <package_name>
frida -U -f <package_name> --no-pause -l poc.js # spawn a new process and load script
~~~

- Useful Frida scripts examples

	[How to write frida scripts](https://www.frida.re/docs/javascript-api/#java)

~~~javascript
// enumerate classes
Java.perform(function(){
  Java.enumerateLoadedClasses(
  {
  "onMatch": function(className){ 
        if (className.match(/vantagepoint/i)){
          console.log(className)
        }     
    },
  "onComplete":function(){}
  })
})

//hook into some function, wrap with setImmediate will avoid frida timeout
setImmediate(function() {
    console.log("[*] Starting script");
	Java.perform(function () {
	    var Activity = Java.use("<package_name>");
	    Activity.<function_name>.implementation = function () {
	        console.log("[*] <function_name> got called!");
	        this.<function_name>();
	    };
	});
});

~~~


~~~javascript
setImmediate(function() { //prevent timeout
    console.log("[*] Starting script");

    Java.perform(function() {

      bClass = Java.use("sg.vantagepoint.uncrackable1.b");
      bClass.onClick.implementation = function(v) {
         console.log("[*] onClick called");
      }
      console.log("[*] onClick handler modified")

      var clazzIntent = Java.use("android.content.Intent");   //hook putExtra to log all intents
      clazzIntent.putExtra.overload('java.lang.String', 'java.lang.String').implementation = function(arg1,arg2){
        console.log(arg1.toString() + " : " + arg2.toString());
        retval = this.putExtra(arg1,arg2);
        return retval;
      }

    })
})
~~~


  + JNI hooking example(strncmp), also check the crackme writeup for details under this repo:

  ~~~
  // package_name: sg.vantagepoint.uncrackable2
  // thanks for all the fish

  setImmediate(function() {
    Java.perform(function() {
        console.log("[*] Here we go");

        system_class = Java.use("java.lang.System");
        system_class.exit.implementation = function(){
          console.log("[*] Exit function hooked here, we are all good")
        }

        var strncmp = undefined;
        imports = Module.enumerateImportsSync("libfoo.so");

        for(i = 0; i < imports.length; i++) {
        if(imports[i].name == "strncmp") {
                strncmp = imports[i].address;
                break;
            }
        }

        Interceptor.attach(strncmp, {
            onEnter: function (args) {
               if(args[2].toInt32() == 23 && Memory.readUtf8String(args[0],23) == "01234567890123456789012") {
                    console.log("[*] Secret string at " + args[1] + ": " + Memory.readUtf8String(args[1],23));
                }
             },
        });
        console.log("[*] Intercepting strncmp");
    });
  });


  getPhrase = Module.findExportByName("libnative-lib.so","Java_com_flagstore_ctf_flagstore_CTFReceiver_getPhrase");   // Export Func

  ~~~
  
  [About Interceptor](https://www.frida.re/docs/javascript-api/#interceptor)
[This blog post is helpful for get started in writting frida scripts.](https://www.codemetrix.net/hacking-android-apps-with-frida-1/)

