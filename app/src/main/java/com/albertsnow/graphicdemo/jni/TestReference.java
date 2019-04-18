package com.albertsnow.graphicdemo.jni;

import android.util.Log;

public class TestReference {

    static {
        System.loadLibrary("reference");
    }

    public static void main(String[] args) {
        TestReference reference = new TestReference();
        Log.i("TestJNI", reference.sayHello("Origin: Hello Word"));
    }

    private native String sayHello(String msg);

}
