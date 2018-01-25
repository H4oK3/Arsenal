This app spawned 2 processes, one was parent one was debugger;
Also it used an anonymous function to do the root check

- patch it

~~~
apktool d UnCrackable-Level2.apk

#    invoke-direct {p0}, Lsg/vantagepoint/uncrackable2/MainActivity;->init()V `
#    invoke-static {v0}, Ljava/lang/System;->exit(I)V`

zipalign -v 4 ./dist/UnCrackable-Level2.apk patch.apk

#Sign it
jarsigner -verbose -keystore ~/.android/debug.keystore patch.apk androiddebugkey
# Note Keystore password is `android`, key alias is `androiddebugkey` and key password again `android`.

~~~

check tmp folder for patched one.
**Note: Changing the smali code directly would be dangerous, do not do that if the change is simple enough. For this case, comment out init function would actually cause problem with the strncmp function, the frida script won't work.**
[Check this blogPost for details.](https://www.codemetrix.net/hacking-android-apps-with-frida-3/)

- Frida it
`frida -U -f sg.vantagepoint.uncrackable2 --no-pause -l poc.js. # this will spawn one process`

~~~
setImmediate(function() {
    console.log("[*] Starting script");
    Java.perform(function() {
        exitClass = Java.use("java.lang.System");
        exitClass.exit.implementation = function() {
            console.log("[*] System.exit called");
        }
        console.log("[*] Hooking calls to System.exit");
    });
});
~~~
Overwrite the exit function to bypass root check

- Solve the password
A little bit reverse yield that the password check was implemented using JNI lib, which can be hook using [Interceptor](https://www.frida.re/docs/javascript-api/#interceptor).
Check the poc.js for detailed solution.

