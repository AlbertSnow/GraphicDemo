package com.albertsnow.graphicdemo.looper;

public class MyLooper {

    static {
        System.loadLibrary("my_looper");
    }



    public native void init();

    public native void wait(int timeout);

}
