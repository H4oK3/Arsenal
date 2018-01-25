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

    })
})
~~~


  + JNI hooking example(strncmp), also check the crackme writeup for details under this repo:
  
  ~~~
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
  ~~~
  [About Interceptor](https://www.frida.re/docs/javascript-api/#interceptor)
[This blog post is helpful for get started in writting frida scripts.](https://www.codemetrix.net/hacking-android-apps-with-frida-1/)

