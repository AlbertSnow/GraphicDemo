package com.albertsnow.graphicdemo.home;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.albertsnow.graphicdemo.MainActivity;
import com.albertsnow.graphicdemo.R;
import com.albertsnow.graphicdemo.jni.exception.ExceptionActivity;
import com.albertsnow.graphicdemo.looper.LooperActivity;
import com.albertsnow.graphicdemo.opencv.OpenCVActivity;
import com.albertsnow.graphicdemo.widget.scroll.MultiScrollActivity;

public class HomeActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter adapter = ArrayAdapter.createFromResource(this, R.array.module_list, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

        handlePermission();
    }

    private void handlePermission() {
        boolean noWritePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED;
        boolean noCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED;
        if (noWritePermission || noCameraPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    10);
        } else {
            Toast.makeText(this, "All permission done", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent();
        boolean jumpActivity = true;

        switch (position) {
            case 0:
                intent.setClass(this, MainActivity.class);
                break;
            case 1:
                intent.setClass(this, OpenCVActivity.class);
                break;
            case 2:
                intent.setClass(this, ExceptionActivity.class);
                break;
            case 3:
                intent.setClass(this, MultiScrollActivity.class);
                break;
            case 4:
                intent.setClass(this, LooperActivity.class);
                break;
            case 5:
                handlePermission();
                jumpActivity = false;
                break;
            default:
                jumpActivity = false;
                throw new IllegalStateException("未注册点击");
        }

        if (jumpActivity) {
            startActivity(intent);
        }
    }

}
