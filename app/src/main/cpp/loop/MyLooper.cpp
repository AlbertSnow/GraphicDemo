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
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <fcntl.h>

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
int tmp_fd;

#define MAXLINE 5
#define OPEN_MAX 100
#define LISTENQ 20
#define SERV_PORT 5000
#define INFTIM 1000

int init(JNIEnv* /*env*/, jobject /*thiz*/) {
    epfd = epoll_create1(EPOLL_CLOEXEC);

    if (epfd < 0) {
        ALOGE("native epoll_create1 fail");
    } else {
        ALOGE("native epoll_create1 success");
    }

    tmp_fd = socket(AF_UNIX, SOCK_STREAM, 0);
//    int tmp_fd = open("/sdcard/testEpoll.txt", O_RDWR);

    if (tmp_fd < 0) {
        ALOGE("native tmp_fd fail");
    } else {
        ALOGE("native tmp_fd success");
    }

    struct epoll_event eventItem{};
    memset(& eventItem, 0, sizeof(epoll_event)); // zero out unused members of data field union
    eventItem.events = EPOLLIN|EPOLLET;
    eventItem.data.fd = tmp_fd;
    int result = epoll_ctl(epfd, EPOLL_CTL_ADD, tmp_fd, & eventItem);

    ALOGI("native mylooper init, ctl add： %d", result);
    if (result < 0) {
        ALOGE("native error (errno=%d)", errno); //EBADF
    } else {
        ALOGI("native mylooper init, ctl add： %d", result);
    }


    struct sockaddr_in clientaddr;
    struct sockaddr_in serveraddr;

    bzero(&serveraddr, sizeof(serveraddr));
    serveraddr.sin_family = AF_INET;
    char *local_addr="127.0.0.1";
    inet_aton(local_addr,&(serveraddr.sin_addr));//htons(portnumber);

    serveraddr.sin_port=htons(1992);
    bind(tmp_fd,(sockaddr *)&serveraddr, sizeof(serveraddr));
    listen(tmp_fd, LISTENQ);
//    maxi = 0;

    return 0;
}

int loopNext(JNIEnv * /*env*/, jobject /*thiz*/, int timeOut) {
    ALOGE("native loopNext start");

    struct epoll_event events[EPOLL_MAX_EVENTS];

//    if (!events) {
//        ALOGE("native malloc");
//        return 1;
//    }

    ALOGI("native mylooper epoll_wait, epfd: %d", epfd);

    int eventCount = epoll_wait(epfd, events, EPOLL_MAX_EVENTS, timeOut);

    ALOGI("native mylooper epoll_wait, eventCount: %d", eventCount);

    if (eventCount < 0) {
        ALOGE("native epoll_wait");
        free(events);
        return 1;
    }


    // Check for poll timeout.
    if (eventCount == 0) {
        ALOGI("native epoll timeout");
    }


//    for (int i = 0; i < eventCount; ++i) {
//        ALOGI("event=%d on fd=%d\n" + events[i].events,
//                events[i].data.fd);
//    }
    ALOGI("native mylooper loopNext over");

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
//        ALOGE("native Native registration unable to find class '%s'", className);
//        return JNI_FALSE;
//    }
//    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
//        ALOGE("native RegisterNatives failed for '%s'", className);
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
//        ALOGE("native ERROR: GetEnv failed");
////        goto bail;
//    }
//    env = uenv.env;
//    if (registerNatives(env) != JNI_TRUE) {
//        ALOGE("native ERROR: registerNatives failed");
////        goto bail;
//    }
//
//    result = JNI_VERSION_1_4;
//
////    bail:
//    return result;
//}



#endif LOOPER_MYLOOPER_H