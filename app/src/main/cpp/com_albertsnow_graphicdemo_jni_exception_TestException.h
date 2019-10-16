//
// Created by Apple on 2019-09-26.
//

#ifndef GRAPHICDEMO_TESTEXCEPTION_H
#define GRAPHICDEMO_TESTEXCEPTION_H


#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

#define SDKVERSION "2.0.5.180309"

JNIEXPORT void JNICALL Java_com_albertsnow_graphicdemo_jni_TestException_testException (JNIEnv *env,
        jobject thisObj, jstring msg);

#ifdef __cplusplus
}
#endif
#endif