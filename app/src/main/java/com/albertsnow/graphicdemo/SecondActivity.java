package com.albertsnow.graphicdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deliverArray(new int[]{1, 2, 3});
    }

    native String stringFromJNI();
    native int[] arrayFromJNI();
    native void deliverString(String string);
    native void deliverArray(int[] intData);
}