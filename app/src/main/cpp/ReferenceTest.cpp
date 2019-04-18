//
// Created by albertsnow on 19-3-19.
//

#import "com_albertsnow_graphicdemo_jni_TestReference.h"
#import "native-lib.h"

JNIEXPORT jstring JNICALL Java_com_albertsnow_graphicdemo_jni_TestReference_sayHello
        (JNIEnv *env, jobject thisObj, jstring inJNIStr) {
    const char* msg = env->GetStringUTFChars(inJNIStr, NULL);
    logStr(msg);
    env->ReleaseStringUTFChars(inJNIStr, msg);

    return env->NewStringUTF("hello everybody, I like H");
}
