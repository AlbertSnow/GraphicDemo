package com.albertsnow.graphicdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        deliverArray(new int[]{1, 2, 3});
        reflectObject(new TestJNI());
        findViewById(R.id.main_malloc_btn).setOnClickListener(this);
        findViewById(R.id.main_file_btn).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

    }

    native String stringFromJNI();
    native int[] arrayFromJNI();
    native void deliverString(String string);
    native void deliverArray(int[] intData);
    native void reflectObject(Object testJni);
    native void mallocInt();
    native void testException();
    native void writeFile();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_malloc_btn:
                mallocInt();
                break;
            case R.id.main_file_btn:
                writeFile();
                break;
        }
    }
}
