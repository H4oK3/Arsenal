- In this folder, I throw things I have done while I was reading the IOSRE book.
	- Useful notes:
		- There is no need to write a whole tweak if just want to test some function, we can just use cycript or frida: here if we want to use C functions in cycript, we need to wrap them up with `dlsym`, example as follows:

	```
	dict = [NSMutableDictionary dictionaryWithContentsOfFile:@"/var/mobile/Library/Preferences/com.apple.Accessibility.plist"]
	[dict setValue:[NSNumber numberWithInt:1] forKey:@"InvertColorsEnabled"]
	[dict writeToFile: @"/var/mobile/Library/Preferences/com.apple.Accessibility.plist" atomically: YES];
	np = @encode(unsigned int(*)(char const*))(dlsym(RTLD_DEFAULT, "notify_post"))
	(*np)("com.apple.accessibility.cache.invert.colors");
	```
	- invertcolor: 
		- Steps of doing this:
			- The goal of this tweak is to change the invertcolor behavior, which can be access under Preference category, related files are copied to `invertcolor/clue`.
			- `/System/Library/PreferenceBundles/AccessibilitySettings.bundle/Accessibility.plist`, under this plist, we can see useful info:
			```
			3 => {
	       "set" => "accessibilitySetPreference:specifier:"
	       "get" => "accessibilityPreferenceForSpecifier:"
	       "defaults" => "com.apple.Accessibility"
	       "cell" => "PSSwitchCell"
	       "label" => "INVERT_COLORS"
	       "key" => "InvertColorsEnabled"
	       "default" => 0
	     	}
			```
			Here we can see setter,getter,and also **defaults**, which means this `com.apple.Accessibility` guy have all the default settings.
			- Now where is this `com.apple.Accessibility`? Well we can just grep it, or based on our knowlege, `/var/mobile/Library/Preferences` should be the one contains all the user preference data, and our guy is right there: `/var/mobile/Library/Preferences/com.apple.Accessibility.plist`, and it contains this: `"InvertColorsEnabled" => 0`;
			- Next step we used `Notificatcher` from `https://github.com/iosre/Notificatcher.git` to watch the system log, and we found this: `notify_post(com.apple.accessibility.cache.invert.colors)`. So the process is straight forward now, `toggle the button -> change entry under com.apple.Accessibility.plist -> singnal notify_post to tell system pref changed`.
			- Now we can have our tweak or cycript written up.
		- A small trial that I have done to hook the SpringBoard and change some accessibility behavior. 
*Do not intall the tweak on your phone if you do not know what are you doing*
		- *TODO: Need to find other function to hook to avoid recovery mode.*
	