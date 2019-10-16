//
// Created by Apple on 2019-09-26.
//
#include "com_albertsnow_graphicdemo_jni_exception_TestException.h"

#include <jni.h>
#include <android/log.h>

#define TAG "TestException"
#define LOGE(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)


extern "C" JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_jni_exception_TestException_testException (
        JNIEnv *env, jobject thisObj, jstring msg) {
    jclass cls = env->GetObjectClass(thisObj);

    jmethodID  jmethodId = env->GetMethodID(cls, "say", "()V");

    LOGE("start method call 1");
    env->CallVoidMethod(thisObj, jmethodId);
    LOGE("end method call");

}