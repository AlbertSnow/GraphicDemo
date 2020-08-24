//
// Created by Apple on 2019-09-26.
//

#ifndef GRAPHICDEMO_TESTEXCEPTION_H
#define GRAPHICDEMO_TESTEXCEPTION_H


#include <jni.h>
#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_looper_MyLooper_init(JNIEnv* env, jobject thiz);
JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_looper_MyLooper_wait(JNIEnv* env, jobject thiz, int timeout);

#ifdef __cplusplus
}
#endif
#endif