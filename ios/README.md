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
	
	- apps are under: `/var/mobile/Containers/Data/Application`
	
	