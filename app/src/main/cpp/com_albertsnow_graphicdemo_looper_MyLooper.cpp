//
// Created by Apple on 2019-09-26.
//
#include "com_albertsnow_graphicdemo_looper_MyLooper.h"

#include <jni.h>
#include <android/log.h>
#include <jni.h>
#include <jni.h>

#define TAG "TestException"
#define LOGE(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)


extern "C" JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_looper_MyLooper_init(JNIEnv
* env, jobject thiz
) {

}

extern "C" JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_looper_MyLooper_wait(JNIEnv
* env, jobject thiz, jint timeout) {
}