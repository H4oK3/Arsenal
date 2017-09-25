# include $(THEOS)/makefiles/common.mk

# TWEAK_NAME = firstwk
# firstwk_FILES = Tweak.xm

# include $(THEOS_MAKE_PATH)/tweak.mk

# after-install::
# 	install.exec "killall -9 SpringBoard"
ARCHS = armv7 armv7s arm64
include $(THEOS)/makefiles/common.mk

TWEAK_NAME = firstwk
firstwk_FILES = Tweak.xm
firstwk_FRAMEWORKS = UIKit

include $(THEOS_MAKE_PATH)/tweak.mk

after-install::
	install.exec "killall -9 SpringBoard"
SUBPROJECTS += unlockchanger
# include $(THEOS_MAKE_PATH)/aggregate.mk
