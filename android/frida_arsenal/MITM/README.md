- frida-android-repinning.js : Bypass certificate pinning for OKHTTP@android
	- Usage:

	~~~BASH
	adb push cert-der.crt /data/local/tmp/
	adb shell ./data/local/tmp/frida-server &
	
	frida -U -l frida-android-repinning.js -f <package_name> --no-pause
	~~~
