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
	
	- app data(sandbox) dir are under: `/var/mobile/Containers/Data/Application`
	- apps binary from appstore are under `/var/mobile/Containers/Bundle/Application`
	- apps binary are under: `/private/var/mobile/Containers/Bundle/Application/xxx-xxx.../<app_name>.app`
	- To get the excutable of the app:
	`cd <PATH_TO_APP>/<app_name>.app ;`
	`plutil -p Info.plist | grep CFBundleExecutable`

- cycript and 'sandbox' and also with dumpdecrypt:
	- ios app are running within 'sandbox' and can only access its own document folder, in order to find the document folder quickly, we can use cycript, also here we use wechat(downloaded from appstore) as an example:
	
	~~~BASH
	frida-ps -U | ag wechat
	cycript -p WeChat # device
	cy# [[NSFileManager defaultManager] URLsForDirectory:NSDocumentDirectory inDomains:NSUserDomainMask][0]
#"file:///var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents/"
	~~~
	
	- dumpdecrypt it:
	
	~~~BASH
	cd /var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents # this is necessary, dumpdecrypt has the same priv as the target app(wechat), and target app(storeapp) can only write file into its own sandbox, a.k.a the document file here.
	
	cp ~/dumpdecrypted_arm64.dylib .
	/var/mobile/Containers/Data/Application/0A620880-8C7E-4F2B-BCC9-46FD9D0CF865/Documents root# DYLD_INSERT_LIBRARIES=dumpdecrypted_arm64.dylib /var/mobile/Containers/Bundle/Application/D86F3ECE-F894-40FC-930D-914C70563CE5/WeChat.app/WeChat
	~~~


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
	
	~~~BASH
	scp -P 2222 root@localhost:/Developer/usr/bin/debugserver ./
lipo -thin arm64 debugserver -output debugserver

 wget http://iosre.com/ent.xml
 ldid -Sent.xml debugserver
	~~~
	
	- LLDB it
		
	~~~BASH
	python usbmuxd-1.0.8/python-client/tcprelay.py -t 1234:1234`
	# On device side, run debugserver
	jailbroken-6:~ root# debugserver *:1234 -a "<process name>"
	
	# On host side, run lldb to attach:
	(lldb) process connect connect://127.0.0.1:1234
	~~~

- LLDB tricks:
	- `image list -o -f`
	`[  1] 0x0000000000084000 /System/Library/CoreServices/SpringBoard.app/SpringBoard(0x0000000100084000) # 0x0000000000084000 is aslr offset, need to do rebase in ida(edit -> segment -> rebase)`
	-  `br s -a <addr>`
	
	~~~BASH
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
	~~~
	
- aarch64 asm fundation:

~~~BASH
0x10009506c <+69740>: stp    x28, x27, [sp, #-0x60]!
0x100095070 <+69744>: stp    x26, x25, [sp, #0x10]
0x100095074 <+69748>: stp    x24, x23, [sp, #0x20]
0x100095078 <+69752>: stp    x22, x21, [sp, #0x30]

STP: store pare register
#1 : sp -= 60; mov [sp],x28; sp++; mov [sp],x27
e.t.c it just created stack space and push those regs into stack
~~~
