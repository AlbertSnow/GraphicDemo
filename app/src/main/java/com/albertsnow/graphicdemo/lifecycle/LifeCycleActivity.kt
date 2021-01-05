package com.albertsnow.graphicdemo.lifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.albertsnow.graphicdemo.R

class LifeCycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }
}