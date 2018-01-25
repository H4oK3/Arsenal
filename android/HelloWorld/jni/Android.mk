LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := JniTest
LOCAL_SRC_FILES := JniTest.c

include $(BUILD_SHARED_LIBRARY)