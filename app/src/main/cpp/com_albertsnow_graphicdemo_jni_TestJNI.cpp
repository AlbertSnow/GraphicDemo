//
// Created by Apple on 2019-09-26.
//
#include "com_albertsnow_graphicdemo_jni_exception_TestException.h"

#include <jni.h>
#include <android/log.h>

#define TAG "TestException"
#define LOGE(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)


extern "C" JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_jni_TestJNI_seeHello (
        JNIEnv *env, jobject thisObj) {

    LOGE("Say hello world h.");
}