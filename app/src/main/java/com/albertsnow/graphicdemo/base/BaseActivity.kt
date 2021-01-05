package com.albertsnow.graphicdemo.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.albertsnow.graphicdemo.TAG_DEFAULT

open class BaseActivity: AppCompatActivity() {

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG_DEFAULT, "onRestoreInstanceState: ${this.javaClass.name?:""}")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG_DEFAULT, "onSaveInstanceState: ${this.javaClass.name?:""}")
    }

}