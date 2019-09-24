LOCAL_LDFLAGS := -static -ggdb
LOCAL_CFLAGS := -ggdb -O0
LOCAL_CPPFLAGS := -ggdb -O0
cmd-strip :=
LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := lightpointer
LOCAL_SRC_FILES := light.cpp
LOCAL_SHARED_LIBRARIES := \
	libcutils \
	libutils
include $(BUILD_EXECUTABLE)


