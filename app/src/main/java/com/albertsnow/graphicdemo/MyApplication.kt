package com.albertsnow.graphicdemo

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        application = base
    }

    companion object {

        var application: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        com.albertsnow.graphicdemo.jni.TestJNI().seeHello()
    }

}
