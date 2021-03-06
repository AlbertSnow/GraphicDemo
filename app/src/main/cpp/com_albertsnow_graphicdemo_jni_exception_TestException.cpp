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

    jmethodID  jmethodId = env->GetMethodID(cls, "makeException", "()V");

    LOGE("start method call 1");
    env->CallVoidMethod(thisObj, jmethodId);
    LOGE("end method call");

    if (env->ExceptionCheck()) {
        LOGE("Exception occur.");
        env->ExceptionDescribe();
        env->ExceptionClear();

        // 抛出我们自己的异常处理
        jclass newExcCls;
        newExcCls = env->FindClass("java/lang/Exception");
        if (newExcCls == NULL) {
            return;
        }
        env->ThrowNew(newExcCls, "throw from C Code.");
    }

}