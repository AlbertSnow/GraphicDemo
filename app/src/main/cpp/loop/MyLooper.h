//
// Created by Apple on 2020-03-08.
//

#ifndef GRAPHICDEMO_MYLOOPER_H
#define GRAPHICDEMO_MYLOOPER_H

#include <jni.h>

int init(JNIEnv* /*env*/, jobject /*thiz*/);

int loopNext(JNIEnv* /*env*/, jobject /*thiz*/, int timeOut);

int enqueueEvent();
int dequeueEvent();

int destroy();

#endif //GRAPHICDEMO_MYLOOPER_H
