package com.albertsnow.graphicdemo.looper

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.albertsnow.graphicdemo.R

import kotlinx.android.synthetic.main.activity_looper.*
import java.io.File

class LooperActivity : AppCompatActivity() {
    val file = File("/sdcard/testEpoll.txt")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_looper)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun createFile(view: View) {
        if (!file.exists()) {
            file.createNewFile()
        }
        file.writeText("Hello World")

        Toast.makeText(this, "write text", Toast.LENGTH_LONG).show();
    }

    fun initLooperThread(view: View) {
        LooperManager.initLoop()
    }

}
