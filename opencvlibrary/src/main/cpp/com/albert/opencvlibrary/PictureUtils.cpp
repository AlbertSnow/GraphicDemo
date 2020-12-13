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
    //The file on apk assets
    const char* picturePath = "/sdcard/OpenCvTest/test.png";
    const char* writePath = "/sdcard/OpenCvTest/testGray.jpg";

    int fd = open(picturePath, O_RDONLY);
    if (fd == -1) {
        logStr("------- File can't access -------");
        return -1;
    } else {
        logStr("+++++++++++++ File access +++++++++++++");
    }


    Mat img = imread(picturePath);
    Mat dest(img.rows, img.cols, CV_8UC1);
    cvtColor(img, dest, COLOR_BGR2GRAY);

    imwrite(writePath, dest);



    printf("-------- Every body see that --------");

    return 0;
}

extern "C" JNIEXPORT void JNICALL Java_com_albert_opencvlibrary_CVPictureUtils_nativeMainMethod
        (JNIEnv * env, jclass clazz) {
    main();
}