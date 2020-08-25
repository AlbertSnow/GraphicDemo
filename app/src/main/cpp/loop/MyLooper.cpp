//
// Created by Apple on 2020-03-08.
//
#ifndef LOOPER_MYLOOPER_H
#define LOOPER_MYLOOPER_H

#include "MyLooper.h"
#include <sys/epoll.h>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <fcntl.h>
#include <jni.h>
#include <android/log.h>
#include <errno.h>

#define LOG_TAG "MyLooper"
#define ALOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#define ALOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define ALOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define ALOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define ALOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)


// --- Looper ---
// Hint for number of file descriptors to be associated with the epoll instance.
static const int EPOLL_SIZE_HINT = 8;
// Maximum number of file descriptors for which to retrieve poll events each iteration.
static const int EPOLL_MAX_EVENTS = 16;

int epfd;

int init(JNIEnv* /*env*/, jobject /*thiz*/) {
    epfd = epoll_create1(EPOLL_CLOEXEC);

    if (epfd < 0) {
        ALOGE("epoll_create1");
    }

    int tmp_fd = open("/sdcard/testEpoll.txt", O_RDWR);

    struct epoll_event eventItem;
    memset(& eventItem, 0, sizeof(epoll_event)); // zero out unused members of data field union
    eventItem.events = EPOLLIN;
    eventItem.data.fd = tmp_fd;
    int result = epoll_ctl(epfd, EPOLL_CTL_ADD, tmp_fd, & eventItem);

    ALOGI("mylooper init, ctl add： %d", result);
    if (result < 0) {
        ALOGE("error (errno=%d)", errno); //EBADF
    }

    return result;
}

int loopNext(JNIEnv* /*env*/, jobject /*thiz*/, int timeOut) {

    struct epoll_event events[EPOLL_MAX_EVENTS];

    if (!events) {
        ALOGE("malloc");
        return 1;
    }

    ALOGI("mylooper epoll_wait, epfd: %d", epfd);

    int eventCount = epoll_wait(epfd, events, EPOLL_MAX_EVENTS, timeOut);

    ALOGI("mylooper epoll_wait, eventCount: %d", eventCount);

    if (eventCount < 0) {
        ALOGE("epoll_wait");
        free(events);
        return 1;
    }


    // Check for poll timeout.
    if (eventCount == 0) {
        ALOGI("epoll timeout");
    }


    for (int i = 0; i < eventCount; ++i) {
//        ALOGI("event=%d on fd=%d\n" + events[i].events,
//                events[i].data.fd);
    }
    ALOGI("mylooper loopNext over");

    return 0;
}

int enqueueEvent() {



}

int dequeueEvent() {
    return 0;
}

int destroy() {
    return 0;
}


//
//static const char *classPathName = "com/albertsnow/graphicdemo/looper/MyLooper";
//
//static JNINativeMethod methods[] = {
//        {"init", "()V", (void*)init },
//        {"wait", "(I)V", (void*)loopNext},
//};
///*
// * Register several native methods for one class.
// */
//static int registerNativeMethods(JNIEnv* env, const char* className,
//                                 JNINativeMethod* gMethods, int numMethods)
//{
//    jclass clazz;
//    clazz = env->FindClass(className);
//    if (clazz == NULL) {
//        ALOGE("Native registration unable to find class '%s'", className);
//        return JNI_FALSE;
//    }
//    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
//        ALOGE("RegisterNatives failed for '%s'", className);
//        return JNI_FALSE;
//    }
//    return JNI_TRUE;
//}
//
///*
// * Register native methods for all classes we know about.
// *
// * returns JNI_TRUE on success.
// */
//static int registerNatives(JNIEnv* env) {
//    if (!registerNativeMethods(env, classPathName,
//                               methods, sizeof(methods) / sizeof(methods[0]))) {
//        return JNI_FALSE;
//    }
//    return JNI_TRUE;
//}
//
//
///*
// * This is called by the VM when the shared library is first loaded.
// */
//
//typedef union {
//    JNIEnv* env;
//    void* venv;
//} UnionJNIEnvToVoid;
//
//
//jint JNI_OnLoad(JavaVM* vm, void* /*reserved*/)
//{
//    UnionJNIEnvToVoid uenv;
//    uenv.venv = NULL;
//    jint result = -1;
//    JNIEnv* env = NULL;
//
//    ALOGI("JNI_OnLoad");
//    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_4) != JNI_OK) {
//        ALOGE("ERROR: GetEnv failed");
////        goto bail;
//    }
//    env = uenv.env;
//    if (registerNatives(env) != JNI_TRUE) {
//        ALOGE("ERROR: registerNatives failed");
////        goto bail;
//    }
//
//    result = JNI_VERSION_1_4;
//
////    bail:
//    return result;
//}



#endif LOOPER_MYLOOPER_H