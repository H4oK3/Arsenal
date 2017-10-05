This is the very first time that I am doing IOS app pentest, here I will note import things down.

## Knowledge tree
- What is MVC?
	*A model stores data that is retrieved according to commands from the controller and displayed in the view.*

	*A view generates new output to the user based on changes in the model.*
	
	*A controller can send commands to the model to update the model's state (e.g., editing a document). It can also send commands to its associated view to change the view's presentation of the model (e.g., scrolling through a document, movement of document)*

## Generic Prep work
- SSH to IPhone:
	- need this tool: iPhoneTunnel.
	- ssh over usb: ssh `root@localhost -p2222`

- Install IPA files:
	- To bypass the minimum version requirement:
	
	```BASH
	unzip app.ipa -d .
	open Payload/<app_name>.app/Info.plist
	# change the MinimumOSVersion to ideal version
	
	# Use plutil to transform the plist file
	plutil -convert xml1 Info.plist 
	
	# re-zip the file with .ipa as ext
	zip -r Payload app.ipa
	scp -P 2222 app.ipa root@localhost:/tmp/
	ipainstaller /tmp/app.ipa
	```
	
	- app data(sandbox) dir are under: `/var/mobile/Containers/Data/Application`
	- apps binary from appstore are under `/var/mobile/Containers/Bundle/Application`
	- apps binary are under: `/private/var/mobile/Containers/Bundle/Application/xxx-xxx.../<app_name>.app`
	- To get the excutable of the app:
	`cd <PATH_TO_APP>/<app_name>.app ;`
	`plutil -p Info.plist | grep CFBundleExecutable`

- cycript and 'sandbox' and also with dumpdecrypt:
	- ios app are running within 'sandbox' and can only access its own document folder, in order to find the document folder quickly, we can use cycript, also here we use wechat(downloaded from appstore) as an example:
	
	```BASH
	frida-ps -U | ag wechat
	cycript -p WeChat # device
	cy# [[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask][0]
	#"file:///var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents/"
	```
	
	- dumpdecrypt it:
	
	```BASH
	cd /var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents # this is necessary, dumpdecrypt has the same priv as the target app(wechat), and target app(storeapp) can only write file into its own sandbox, a.k.a the document file here.
	
	cp ~/dumpdecrypted_arm64.dylib .
	/var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents root# DYLD_INSERT_LIBRARIES=dumpdecrypted_arm64.dylib /var/mobile/Containers/Bundle/Application/D86F3ECE-F894-40FC-930D-914C70563CE5/WeChat.app/WeChat
	```


- Useful commands:
	`killall -HUP SpringBoard # Respiring springboard`
	`su mobile -c uicache # reload ui cache`
	
- Tweaky stuff:
	- Here I used a docker to compile `make package`
	- Then use iphoneTunnel to handle the forwarding stuff, make sure you also have `$THEOS/bin/itnl` file ready.
	- `make install`, and the lock screen msg should be changed.
	- On device, `ondeviceconsole` can be used to print syslog msg, we can use `NSLog` in tweak to do some debugging output.


- LLDB and debugserver:
	- use tcprelay to do port forward:
	- Configure debugserver so it can debug other's app
	
	```BASH
	scp -P 2222 root@localhost:/Developer/usr/bin/debugserver ./
	lipo -thin arm64 debugserver -output debugserver

 	wget http://iosre.com/ent.xml
 	ldid -Sent.xml debugserver
	```
	
	- LLDB it
		
	```BASH
	python usbmuxd-1.0.8/python-client/tcprelay.py -t 1234:1234`
	# On device side, run debugserver
	jailbroken-6:~ root# debugserver *:1234 -a "<process name>"
	
	# On host side, run lldb to attach:
	(lldb) process connect connect://127.0.0.1:1234
	```

- LLDB tricks:
	- `image list -o -f`
	`[  1] 0x0000000000084000 /System/Library/CoreServices/SpringBoard.app/SpringBoard(0x0000000100084000) # 0x0000000000084000 is aslr offset, need to do rebase in ida(edit -> segment -> rebase)`
	-  `br s -a <addr>`
	
	```BASH
	# Some common lldb commands, for more details, go http://lldb.llvm.org/lldb-gdb.html:
	register read # info regs
	disass --pc
	br list
	br del/en/dis <bp_number>
	s # step

	p (char *)$x1
	po [$x0 class] # po [SomeClass returnAnObject]

	register write r0 1
	
	(lldb) br com add 1     
	Enter your debugger command(s). Type 'DONE' to end. > po [$r0 class]
	> p (char *)$r1
	>c
	> DONE
	```
	
- aarch64 asm fundation:

```BASH
0x10009506c <+69740>: stp    x28, x27, [sp, #-0x60]!
0x100095070 <+69744>: stp    x26, x25, [sp, #0x10]
0x100095074 <+69748>: stp    x24, x23, [sp, #0x20]
0x100095078 <+69752>: stp    x22, x21, [sp, #0x30]

STP: store pare register
#1 : sp -= 60; mov [sp],x28; sp++; mov [sp],x27
e.t.c it just created stack space and push those regs into stack
```

---


## Hack time
- hack the UI with cycript and reveal
	- `Reveal` the app
	- Use `cycript` to look the current UI view and get the button id and so on:
	
	
	```
	[[UIApp keyWindow] recursiveDescription ].toString() # which will print everything for the current UI view;
	cy# #"<UIButton: 0x147deac40; frame = (257 337; 37 30); opaque = NO; autoresize = RM+BM; layer = <CALayer: 0x147d1ff00>>"

	cy# [#0x147deac40 setHidden: YES] # now we can test if we hide the button.
	```
	**Notice that we need UIButton here, UIButtonLabel is a subview of that button and it is useless.**
	
- Find the UI function:

	- Example for `Mail` app `Edit` **button**
	**Note that UIToolbarButton and UIButton they are different, the method listed below is only for UIToolbarButton**
		```
		cycript -p 943 	# hooking the Mail process
		cy# button=#0x1457c9ab0
		#"<UIToolbarButton: 0x1457c9ab0; frame = (375 0; 23 44); opaque = NO; gestureRecognizers = <NSArray: 0x146860230>; layer = <CALayer: 0x1457c9830>>"
		cy#
		cy# [button allTargets ]
		[NSSet setWithArray:@[#"<ComposeButtonItem: 0x1457c8f10>"]]]
		cy# [button allControlEvents ]
		8192
		cy# [button actionsForTarget: #0x1457c8f10 forControlEvent: 8192]
		@["_sendAction:withEvent:"]
		```


	- Example for `Settings` app `Phone` **cell**
		- Find Controller from View.
		
		```
		# hook the app use cycript -p
		cy# cell=#0x1381b3a00
		#"<PSTableCell: 0x1381b3a00; baseClass = UITableViewCell; frame = (0 35; 414 45); text = 'My Number'; autoresize = W; tag = 4; layer = <CALayer: 0x138b24ea0>>"
		cy# [#0x138a4ffe0 nextResponder]
		#"<UITableViewCellContentView: 0x138b39f40; frame = (0 0; 414 44.6667); gestureRecognizers = <NSArray: 0x138b04840>; layer = <CALayer: 0x138b32ba0>>"
		cy# [#0x138b39f40 nextResponder]
		#"<PSTableCell: 0x1381b3a00; baseClass = UITableViewCell; frame = (0 35; 414 45); text = 'My Number'; autoresize = W; tag = 4; layer = <CALayer: 0x138b24ea0>>"
		cy# _
		#"<PSTableCell: 0x1381b3a00; baseClass = UITableViewCell; frame = (0 35; 414 45); text = 'My Number'; autoresize = W; tag = 4; layer = <CALayer: 0x138b24ea0>>"
		cy# [#0x1381b3a00 nextResponder]
		#"<UITableViewWrapperView: 0x1379b4600; frame = (0 0; 414 672); gestureRecognizers = <NSArray: 0x1377711b0>; layer = <CALayer: 0x138a007c0>; contentOffset: {0, 0}; contentSize: {414, 672}>"
		cy# [#0x1379b4600 nextResponder]
		#"<UITableView: 0x1378fce00; frame = (0 0; 414 736); autoresize = W+H; gestureRecognizers = <NSArray: 0x1375b9a90>; layer = <CALayer: 0x138a7ab00>; contentOffset: {0, -64}; contentSize: {414, 658.33333333333337}>"
		cy# [#0x1379b4600 nextResponder]
		#"<UITableView: 0x1378fce00; frame = (0 0; 414 736); autoresize = W+H; gestureRecognizers = <NSArray: 0x1375b9a90>; layer = <CALayer: 0x138a7ab00>; contentOffset: {0, -64}; contentSize: {414, 658.33333333333337}>"
		cy# [#0x1378fce00 nextResponder]
		#"<PSListContainerView: 0x138afa600; frame = (0 0; 414 736); autoresize = W+H; layer = <CALayer: 0x138c0bf30>>"
		cy# [#0x138afa600 nextResponder]
		#"<PhoneSettingsController 0x138197400: navItem <UINavigationItem: 0x138b16450>, view <UITableView: 0x1378fce00; frame = (0 0; 414 736); autoresize = W+H; gestureRecognizers = <NSArray: 0x1375b9a90>; layer = <CALayer: 0x138a7ab00>; contentOffset: {0, -64}; contentSize: {414, 658.33333333333337}>>"

		# we found PhoneSettingsController by recursively call nextResponder started from the cell id
		```
		- Find Module from Controller(Also with a better way of using classdump).

		```
		jailbroken-6:~ root# grep -r PhoneSettingsController /Applications/Preferences.app/ # returns nothing
		jailbroken-6:~ root# grep -r PhoneSettingsController /System/Library/ 2>/dev/null	# find it in bundle

		# Back to cycript:
		[NSBundle loadedBundles ]		# get all loaded bundles
		bundle=[NSBundle bundleWithPath:@"/System/Library/PreferenceBundles/MobilePhoneSettings.bundle"]


		cy# dlopen("/usr/lib/libclassdumpdyld.dylib",RTLD_NOW)
		(typedef void*)(0x138a84ff0)
		cy# extern "C" NSString * dumpBundle(NSBundle *aBundle);
		(extern "C" NSString *dumpBundle(NSBundle *))

		##
		# Wrap up dumpclass_dylb C functions here
		extern "C" NSString * dumpClass(Class *aClass);
		extern "C" NSString * dumpBundle(NSBundle *aBundle);
		extern "C" NSString * dumpBundleForClass(Class *aClass);
		##

		# Dump it
		cy# dumpBundle(bundle)
		@"Wrote all headers to /tmp/MobilePhoneSettings"
		
		# Where is that exact binary(MobilePhoneSettings) located?
		image list -o -f | grep -i MobilePhoneSettings
		# This gave me the exact binary of that bundle: 
		# /Users/hke/Library/Developer/Xcode/iOS DeviceSupport/9.0.1 (13A405)/Symbols/System/Library/PreferenceBundles/MobilePhoneSettings.bundle/MobilePhoneSettings, we can drop this binary in Hopper/Ida.

		```

		- LLDB it( here I will use the example of Settings app).
		
		```
		# address can be calculated via: hex(aslr + addr_in_hopper)

		(lldb) disass -s 0x188d8e99c --count 5
		MobilePhoneSettings`-[PhoneSettingsController tableView:cellForRowAtIndexPath:]:
		    0x188d8e99c <+0>:  stp    x24, x23, [sp, #-0x40]!
		    0x188d8e9a0 <+4>:  stp    x22, x21, [sp, #0x10]
		    0x188d8e9a4 <+8>:  stp    x20, x19, [sp, #0x20]
		    0x188d8e9a8 <+12>: stp    x29, x30, [sp, #0x30]
		    0x188d8e9ac <+16>: add    x29, sp, #0x30            ; =0x30
		(lldb) br s -a 0x188d8eae4
		Breakpoint 1: where = MobilePhoneSettings`-[PhoneSettingsController tableView:cellForRowAtIndexPath:] + 328, address = 0x0000000188d8eae4
		(lldb) c
		Process 1030 resuming
		Process 1030 stopped
		+ thread #1, queue = 'com.apple.main-thread', stop reason = breakpoint 1.1
		    frame #0: 0x0000000188d8eae4 MobilePhoneSettings`-[PhoneSettingsController tableView:cellForRowAtIndexPath:] + 328
		MobilePhoneSettings`-[PhoneSettingsController tableView:cellForRowAtIndexPath:]:
		->  0x188d8eae4 <+328>: sub    sp, x29, #0x30            ; =0x30
		    0x188d8eae8 <+332>: ldp    x29, x30, [sp, #0x30]
		    0x188d8eaec <+336>: ldp    x20, x19, [sp, #0x20]
		    0x188d8eaf0 <+340>: ldp    x22, x21, [sp, #0x10]
		Target 0: (Preferences) stopped.

		# On device, goto settings -> phone -> observe output in lldb

		(lldb) po $x0
		<PSTableCell: 0x1378da200; baseClass = UITableViewCell; frame = (0 0; 320 44); text = 'My Number'; tag = 4; layer = <CALayer: 0x138e10950>>

		(lldb) po [$x0 subviews]
		<__NSArrayM 0x1377d1790>(
		<UITableViewCellContentView: 0x1375a1c30; frame = (0 0; 320 44); gestureRecognizers = <NSArray: 0x1375ce200>; layer = <CALayer: 0x1377f00b0>>
		)

		(lldb) po [$x0 detailTextLabel]
		<UITableViewLabel: 0x138aea820; frame = (0 0; 0 0); text = 'Unknown'; userInteractionEnabled = NO; layer = <_UILabelLayer: 0x1375dc4a0>>

		# Bingo, we have found our 'Unknown', which means we are in correct UIfunction.
		```







