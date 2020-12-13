//
// Created by Apple on 2019-08-02.
//

                                                                                                             #include <cstdio>
#include <opencv2/opencv.hpp>
#include <jni.h>
#include <fcntl.h>
#include "DebugUtils.h"

using namespace cv;
using namespace std;

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


    Mat dst, cdst, cdstP;

    Mat src = imread(picturePath,IMREAD_GRAYSCALE);

    if (src.empty()) {
        logStr("Load picture empty");
        return -1;
    }

    Canny(src, dst, 50, 200, 3);
    cvtColor(dst, cdst, COLOR_GRAY2BGR);
    cdstP = cdst.clone();

    vector<Vec2f> lines;
    HoughLines(dst, lines, 1, CV_PI / 180, 150, 0, 0);

    for (size_t i =0; i < lines.size(); i++) {
        float rho = lines[i][0], theta = lines[i][1];
        Point pt1, pt2;
        double a = cos(theta), b = sin(theta);
        double x0 = a*rho, y0 = b*rho;
        pt1.x = cvRound(x0 + 1000*(-b));
        pt1.y = cvRound(y0 + 1000*(a));
        pt2.x = cvRound(x0 - 1000*(-b));
        pt2.y = cvRound(y0 - 1000*(a));
        line( cdst, pt1, pt2, Scalar(0,0,255), 3, LINE_AA);
    }

    if (cdst.empty()) {
        printf("--__- hough line is empty");
    } else {
        imwrite(writePath, cdst);
    }
    printf("-------- Every body see that --------");

    return 0;
}

extern "C" JNIEXPORT void JNICALL Java_com_albert_opencvlibrary_CVPictureUtils_nativeMainMethod
        (JNIEnv * env, jclass clazz) {
    main();
}