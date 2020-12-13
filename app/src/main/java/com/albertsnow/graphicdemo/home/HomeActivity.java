package com.albertsnow.graphicdemo.home;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.albertsnow.graphicdemo.MainActivity;
import com.albertsnow.graphicdemo.R;
import com.albertsnow.graphicdemo.jni.exception.ExceptionActivity;
import com.albertsnow.graphicdemo.looper.LooperActivity;
import com.albertsnow.graphicdemo.opencv.OpenCVActivity;
import com.albertsnow.graphicdemo.widget.WidgetActivity;

public class HomeActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter adapter = ArrayAdapter.createFromResource(this, R.array.module_list, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent();

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
                intent.setClass(this, WidgetActivity.class);
                break;
            case 4:
                intent.setClass(this, LooperActivity.class);
                break;
                default:
                    throw new IllegalStateException("未注册点击");
        }

        startActivity(intent);
    }

}
