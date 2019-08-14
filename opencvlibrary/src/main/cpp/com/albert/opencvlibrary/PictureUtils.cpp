//
// Created by Apple on 2019-08-02.
//

                                                                                                             #include <cstdio>
#include <opencv2/opencv.hpp>
#include <jni.h>
#include <fcntl.h>
#include "DebugUtils.h"

using namespace cv;

int main(void)
{
    printf("Hello world.");
    const char* picturePath = "/sdcard/Test.jpg";

    int fd = open(picturePath, O_RDONLY);
    if (fd == -1) {
        logStr("------- File can't access -------");
        return -1;
    } else {
        logStr("+++++++++++++ File access +++++++++++++");
    }


    Mat img = imread(picturePath);
    Scalar intensity = img.at<uchar>(1, 2);

    uchar blue = intensity.val[0];
    uchar green = intensity.val[1];
    uchar red = intensity.val[2];
    printf("-------- Every body see that --------");

    return 0;
}

extern "C" JNIEXPORT void JNICALL Java_com_albert_opencvlibrary_CVPictureUtils_nativeMainMethod
        (JNIEnv * env, jclass clazz) {
    main();
}