package com.albertsnow.graphicdemo.jni.exception;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.albertsnow.graphicdemo.R;

public class ExceptionActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);

        initView();
    }

    private void initView() {
        findViewById(R.id.make_exception).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestException().testException("Just Test Exception");

                Log.i("ExceptionActivity", "receive some inoiooooooooo");
            }
        });
    }
}
