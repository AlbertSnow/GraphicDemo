#include <jni.h>
#include <string>
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_albertsnow_graphicdemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_MainActivity_deliverString(
        JNIEnv* env,
        jobject /* this */, jstring j_str) {
    char buf[128];
    jboolean isCopy;
    const char *str = (*env).GetStringUTFChars(j_str, &isCopy);
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "isCopy: %d\n", isCopy);
    if (str == NULL) {
        return;
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "hello %s", str);
    (*env).ReleaseStringUTFChars(j_str, str);
    return;
}