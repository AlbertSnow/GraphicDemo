#include <jni.h>
#include <string>
#include <android/log.h>

void logStr(const char* str) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logStr: %s\n", str);
}

void logInt(int intData) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logInt: %d\n", intData);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_deliverString(
        JNIEnv* env,
        jobject /* this */, jstring j_str) {
    jboolean isCopy;
    const char *str = env->GetStringUTFChars(j_str, &isCopy);
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "isCopy: %d\n", isCopy);
    if (str == NULL) {
        return;
    }
    logStr(str);
    env->ReleaseStringUTFChars(j_str, str);
    return;
}

extern "C" JNIEXPORT jintArray JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_arrayFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    int a[10] = {1, 2, 3, 4, 5};

    jintArray javaArray = env->NewIntArray(5);
    if (javaArray == NULL) {
        return NULL;
    }
    env->SetIntArrayRegion(javaArray, 0, 4, a);


    return javaArray;
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_deliverArray(
        JNIEnv* env,
        jobject /* this */,
        jintArray javaArray) {
    jsize  size = env->GetArrayLength(javaArray);
    logInt(size);
    jboolean isCopy;
    jint* element = env->GetIntArrayElements(javaArray, &isCopy);
    for (int i = 0; i < size; ++i) {
        logInt(element[i]);
    }
}
