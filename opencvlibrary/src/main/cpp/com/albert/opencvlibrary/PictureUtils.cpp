//
// Created by Apple on 2019-08-02.
//

#include "stdio.h"
#include <opencv2/opencv.hpp>
#include <jni.h>

using namespace cv;

int main(void)
{
    printf("Hello world.");
    Mat img = imread("/sdcard/Test.jpg");
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