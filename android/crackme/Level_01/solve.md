The app itself was quite straight forward, only 1 activity with 2 level checks:
- Check if its rooted phone/ debuggable
- Check the password

~~~
prep_android UnCrackable-Level1.apk # reverse it
adb shell pm list packages | ag crackme # find package name

frida -U -l poc.js sg.vantagepoint.uncrackable1
~~~

[This blogpost was helpful](https://www.codemetrix.net/hacking-android-apps-with-frida-2/)