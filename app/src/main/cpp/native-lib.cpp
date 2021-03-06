#include <jni.h>
#include <string>
#include <android/log.h>
#include <sys/types.h>
#include <unistd.h>

void logStr(const char *str) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logStr: %s\n", str);
}

void logInt(int intData) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logInt: %d\n", intData);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_deliverString(
        JNIEnv *env,
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
        JNIEnv *env,
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
        JNIEnv *env,
        jobject /* this */,
        jintArray javaArray) {
    jsize size = env->GetArrayLength(javaArray);
    logInt(size);
    jboolean isCopy;
    jint *element = env->GetIntArrayElements(javaArray, &isCopy);
    for (int i = 0; i < size; ++i) {
        logInt(element[i]);
    }
    env->ReleaseIntArrayElements(javaArray, element, 0);
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_reflectObject(
        JNIEnv *env,
        jobject thisParam, jobject getFieldClass) {
    jclass clazz = env->FindClass("com/albertsnow/graphicdemo/TestJNI");
    jfieldID filedId = env->GetFieldID(clazz, "testField", "I");
    jint filedValue = env->GetIntField(getFieldClass, filedId);
    logInt(filedValue);
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_mallocInt(
        JNIEnv *env,
        jobject thisParam) {
    void *memory = malloc(1024 * 1024 * 1);
}


extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_fork(
        JNIEnv *env,
        jobject thisParam) {
//    void *memory = malloc(1024 * 1024 * 1);
    pid_t pid;
    pid = fork();
    if (pid > 0) {
        logStr("I am the parent");
    } else if (!pid) {
        logStr("I am the child process");
    } else {
        logStr("Some error occur");
    }
}


static jstring func1(JNIEnv *env) {
    env = 0;
    return env->NewStringUTF("hello ");
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_testException(
        JNIEnv *env,
        jobject thisParam) {
    func1(env);
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_writeFile(
        JNIEnv *env,
        jobject thisParam) {
    FILE *stream = fopen("/sdcard/TestFile.txt", "w");
    if (NULL == stream) {
        logStr("file open null");
        return;
    }
    logStr("file open success");

    char data[] = {'h', 'e', 'l', 'l', 'o', '\n'};
    size_t count = sizeof(data) / sizeof(data[0]);

    if (count != fwrite(data, sizeof(char), count, stream)) {
        logStr("file error");
    }

    fputs("world", stream);
    fputc('\n', stream);
    fprintf(stream, "\n hello %s i like this", "Albert Snow");

    fclose(stream);
    if (EOF == fflush(stream)) {
        logStr("file flush error");
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_readFile(
        JNIEnv *env,
        jobject thisParam) {
    FILE *stream = fopen("/sdcard/TestFile.txt", "r");
    if (NULL == stream) {
        logStr("file open null");
        return;
    }

    char buffer[5];
    int count = 4;
    fread(buffer, sizeof(char), count, stream);
    logStr(buffer);


}
