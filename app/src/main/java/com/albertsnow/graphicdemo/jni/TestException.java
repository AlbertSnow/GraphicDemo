package com.albertsnow.graphicdemo.jni;

import android.util.Log;

public class TestException {

    static {
        System.loadLibrary("NativeException");
    }

    public static void main(String[] args) {
        TestException reference = new TestException();
        Log.i("TestJNI", reference.testException("Origin: Hello Word"));
    }

    private native String testException(String msg);


}
