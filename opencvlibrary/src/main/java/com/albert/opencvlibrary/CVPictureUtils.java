package com.albert.opencvlibrary;

public class CVPictureUtils {
    static {
        System.loadLibrary("graphic");
    }

    public static void testMainMethod() {
        nativeMainMethod();
    }

    private static native void nativeMainMethod();
}
