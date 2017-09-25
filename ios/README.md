This is the very first time that I am doing IOS app pentest, here I will note import things down.

- SSH to IPhone:
	- need this tool: iPhoneTunnel.
	- ssh over usb: ssh `root@localhost -p2222`

- Install IPA files:
	- To bypass the minimum version requirement:
	
	~~~BASH
	unzip app.ipa -d .
	open Payload/<app_name>.app/Info.plist
	# change the MinimumOSVersion to ideal version
	
	# Use plutil to transform the plist file
	plutil -convert xml1 Info.plist 
	
	# re-zip the file with .ipa as ext
	zip -r Payload app.ipa
	scp -P 2222 app.ipa root@localhost:/tmp/
	ipainstaller /tmp/app.ipa
	~~~
	
	- app dir are under: `/var/mobile/Containers/Data/Application`
	- apps are under: `/private/var/mobile/Containers/Bundle/Application/xxx-xxx.../<app_name>.app`
	- To get the excutable of the app:
	`cd <PATH_TO_APP>/<app_name>.app ;`
	`plutil -p Info.plist | grep CFBundleExecutable`

- Useful commands:
	`killall -HUP SpringBoard # Respiring springboard`
	`su mobile -c uicache # reload ui cache`
	
- Tweaky stuff:
	- Here I used a docker to compile `make package`
	- Then use iphoneTunnel to handle the forwarding stuff, make sure you also have `$THEOS/bin/itnl` file ready.
	- `make install`, and the lock screen msg should be changed.
	- On device, `ondeviceconsole` can be used to print syslog msg, we can use `NSLog` in tweak to do some debugging output.