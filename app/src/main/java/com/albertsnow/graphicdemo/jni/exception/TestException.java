package com.albertsnow.graphicdemo.jni.exception;

import android.util.Log;

public class TestException {

    static {
        System.loadLibrary("NativeException");
    }

    public static void main(String[] args) {
        TestException reference = new TestException();
        Log.i("TestJNI", reference.testException("Origin: Hello Word"));
    }

    public native String testException(String msg);

    public void makeException() {
        testException("hello");
    }


}
