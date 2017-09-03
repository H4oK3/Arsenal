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

~~~

- Useful Frida scripts examples

	[How to write frida scripts](https://www.frida.re/docs/javascript-api/#java)

~~~javascript
// enumerate classes
Java.perform(function(){Java.enumerateLoadedClasses({"onMatch":function(className){ console.log(className) },"onComplete":function(){}})})

//hook into some function
Java.perform(function () {
    var Activity = Java.use("<package_name>");
    Activity.<function_name>.implementation = function () {
        console.log("[*] <function_name> got called!");
        this.<function_name>();
    };
});

~~~

[This blog post is helpful for get started in writting frida scripts.](https://www.codemetrix.net/hacking-android-apps-with-frida-1/)

