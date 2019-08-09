//
// Created by Apple on 2019-08-08.
//
#include <android/log.h>
#include "../../../../../../../../../Library/Android/sdk/ndk-bundle/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include/android/log.h"

bool mIsDebug = false;

void logStr(const char *str) {
    if (!mIsDebug) {
        __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logStr: %s\n", str);
    }
}

void logInt(int intData) {
    if (!mIsDebug) {
        __android_log_print(ANDROID_LOG_DEBUG, "NativeTest", "logInt: %d\n", intData);
    }
}

void setDebug(bool isDebug) {
    mIsDebug = isDebug;
}