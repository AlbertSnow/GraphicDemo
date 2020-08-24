package com.albertsnow.graphicdemo.looper

import android.util.Log

const val TAG = "looper_manager"
object LooperManager {

    fun initLoop() {

        val testThread = Thread {
            Log.i(TAG, "looper start")

            val myLooper =  MyLooper()
            myLooper.init()
            myLooper.wait(60 * 1000)

            Log.i(TAG, "looper finish")

        }
        testThread.name = "MyLooperTest"
        testThread.start()
    }

}