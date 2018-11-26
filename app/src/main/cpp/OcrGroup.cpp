#include <jni.h>
#include <string>
#include "native-lib.h"

extern "C" JNIEXPORT void JNICALL
Java_com_albertsnow_graphicdemo_SecondActivity_testBuild(
        JNIEnv *env,
        jobject thisObject,
        jobjectArray objectArray ) {

    jsize start = 0;
    int count = env->GetArrayLength(objectArray);

    jclass testClass = env->FindClass("com/albertsnow/graphicdemo/TestJNI");
    jfieldID  groupIdFiledId = env->GetFieldID(testClass, "groupId", "I");


    for(int i=0;i<count;i++){
        jobject object = env->GetObjectArrayElement(objectArray, start);
        jint index = env->GetIntField(object, groupIdFiledId);

        std::string message = "index:" + std::to_string(index);
        char *messageChar = new char[message.size() + 1];
        std::strncpy(messageChar, message.c_str(), message.size());

        const char *finalMessage = &messageChar[0];
        logStr(finalMessage);

        delete[] messageChar;
    }
}
