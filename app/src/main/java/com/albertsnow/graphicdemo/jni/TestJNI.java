package com.albertsnow.graphicdemo.jni;

public class TestJNI {

    static {
        System.loadLibrary("test_jni");
    }


    public native void seeHello();

}
