package com.albertsnow.graphicdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log

const val TAG_DEFAULT = "tag_default"
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
        registerActivityLifecycleCallbacks(object: ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
                Log.d(TAG_DEFAULT, "onActivityPaused: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivityResumed(activity: Activity?) {
                Log.d(TAG_DEFAULT, "onActivityResumed: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivityStarted(activity: Activity?) {
                Log.d(TAG_DEFAULT, "onActivityStarted: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivityDestroyed(activity: Activity?) {
                Log.d(TAG_DEFAULT, "onActivityDestroyed: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                Log.d(TAG_DEFAULT, "onActivitySaveInstanceState: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivityStopped(activity: Activity?) {
                Log.d(TAG_DEFAULT, "onActivityStopped: ${activity?.javaClass?.name?:""}")
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                Log.d(TAG_DEFAULT, "onActivityCreated: ${activity?.javaClass?.name?:""}")
            }

        })

    }

}
