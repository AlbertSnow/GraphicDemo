package com.albertsnow.graphicdemo.jni.exception;

import android.util.Log;

public class TestException {

    static {
        System.loadLibrary("NativeException");
    }

    public static void main(String[] args) {
        TestException reference = new TestException();
//        Log.i("TestJNI", reference.testException("Origin: Hello Word") );
        reference.testException("Origin: Hello Word");
    }

    public native void testException(String msg);


    public void say() {
        Log.i("TestLog", "HelloEjlfajdfja;dfjlsdf+++++++++++");
    }

    public void makeException() {
        int i = 1 / 0;
    }


}
