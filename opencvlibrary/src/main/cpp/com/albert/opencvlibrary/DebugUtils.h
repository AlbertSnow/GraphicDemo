//
// Created by Apple on 2019-08-08.
//
//#include <android/log.h>
#include "../../../../../../../../../Library/Android/sdk/ndk-bundle/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include/android/log.h"

#ifndef GRAPHICDEMO_DEBUGUTILS_H
#define GRAPHICDEMO_DEBUGUTILS_H

#endif //GRAPHICDEMO_DEBUGUTILS_H


void logStr(const char *str) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logStr: %s\n", str);
}

void logInt(int intData) {
    __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logInt: %d\n", intData);
}
