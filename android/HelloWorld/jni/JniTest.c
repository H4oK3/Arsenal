#include <jni.h>
#include <string.h>

jstring Java_com_ha0k3_helloworld_MainActivity_helloJni(JNIEnv* env, jobject thiz ){
    return (*env)->NewStringUTF(env, "Hello from JNI ! ");
}