#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_qihoo_test_first_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

//package com.qihoo.test.first;
//
//import java.io.InputStream;
//import java.io.IOException;
//import android.os.Bundle;
//import javax.crypto.SecretKey;
//import java.security.Key;
//import javax.crypto.Cipher;
//import java.security.spec.KeySpec;
//import javax.crypto.spec.DESKeySpec;
//import javax.crypto.SecretKeyFactory;
//import java.security.SecureRandom;
//import android.widget.EditText;
//import android.content.res.AssetManager;
//import android.app.Activity;

//this.decrypt(this.inputByte, "Qihoo123");
//private byte[] decrypt(final byte[] array, final String s) throws Exception {
//        final SecureRandom secureRandom = new SecureRandom();
//        final SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(s.getBytes()));
//        final Cipher instance = Cipher.getInstance("DES");
//        instance.init(2, generateSecret, secureRandom);
//        return instance.doFinal(array);

jbyteArray mydecryto(JNIEnv *env, jobject jclazz, jbyteArray paramArrayOfByte, jstring paramString)
{

    // 转换SecureRandom localSecureRandom = new SecureRandom();
    // SecureRandom类在java.security.SecureRandom
    jclass jc_SecureRandom = env->FindClass("java/security/SecureRandom"); // 注意是/
    //构造函数的GetMethodID第二个参数为<init> 第三个参数为格式【参数+返回值】，并且返回值为空
    jmethodID jm_Cons_SecureRandom = env->GetMethodID(jc_SecureRandom, "<init>", "()V");
    // new SecureRandom();
    jobject jo_SecureRandom = env->NewObject(jc_SecureRandom, jm_Cons_SecureRandom);




    // 转换DESKeySpec localDESKeySpec = new DESKeySpec(paramString.getBytes());
    // DESKeySpec类在javax.crypto.spec.DESKeySpec
    jclass jc_DESKeySpec = env->FindClass("javax/crypto/spec/DESKeySpec");
    jmethodID jm_Cons_DESKeySpec = env->GetMethodID(jc_DESKeySpec, "<init>", "([B)V");
    jclass jc_String = env->FindClass("java/lang/String");
    jmethodID jm_getBytes = env->GetMethodID(jc_String, "getBytes", "()[B");
    // paramString.getBytes()
    jbyteArray jb_paramString = (jbyteArray)env->CallObjectMethod(paramString, jm_getBytes);
    // new DESKeySpec(paramString.getBytes());
    jobject jo_DESKeySpec = env->NewObject(jc_DESKeySpec, jm_Cons_DESKeySpec, jb_paramString);




    //SecretKey localSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec);
    jclass jc_SecretKeyFactory = env->FindClass("javax/crypto/SecretKeyFactory");
    jmethodID jm_getInstance = env->GetStaticMethodID(jc_SecretKeyFactory,"getInstance","(Ljava/lang/String;)Ljavax/crypto/SecretKeyFactory;");
    jobject jo_SecretKeyFactory = env->CallStaticObjectMethod(jc_SecretKeyFactory,jm_getInstance,env->NewStringUTF("DES"));

    jmethodID jm_generateSecret = env->GetMethodID(jc_SecretKeyFactory,"generateSecret","(Ljava/security/spec/KeySpec;)Ljavax/crypto/SecretKey;");
    jobject jo_SecretKey = env->CallObjectMethod(jo_SecretKeyFactory,jm_generateSecret,jo_DESKeySpec);

    //Cipher localCipher = Cipher.getInstance("DES");
    jclass jc_Cipher = env->FindClass("javax/crypto/Cipher");
    jmethodID jm_Cipher_getInstance = env->GetStaticMethodID(jc_Cipher,"getInstance","(Ljava/lang/String;)Ljavax/crypto/Cipher;");
    jobject jo_Cipher = env->CallStaticObjectMethod(jc_Cipher,jm_Cipher_getInstance,env->NewStringUTF("DES"));

    //localCipher.init(2, localSecretKey, localSecureRandom);
    jmethodID jm_init = env->GetMethodID(jc_Cipher,"init","(ILjava/security/Key;Ljava/security/SecureRandom;)V");
    env->CallVoidMethod(jo_Cipher,jm_init,2,jo_SecretKey,jo_SecureRandom);

    //localCipher.doFinal(paramArrayOfByte)
    jmethodID jm_doFinal = env->GetMethodID(jc_Cipher,"doFinal","([B)[B");
    jbyteArray jbA = (jbyteArray)env->CallObjectMethod(jo_Cipher,jm_doFinal,paramArrayOfByte);  //返回值就是paramArrayOfByte

    jthrowable throwable = env->ExceptionOccurred();
    if(throwable!=NULL)
    {
        env->ExceptionDescribe();
        env->ExceptionClear();
        return NULL;
    }
    return jbA;

    return NULL;
}

JNIEXPORT jbyteArray JNICALL Java_com_qihoo_test_first_MainActivity_QihooDecode
        (JNIEnv *env, jobject thiz, jbyteArray paramArrayOfByte, jstring paramString)
{
    return mydecryto(env, thiz, paramArrayOfByte, paramString);

}
