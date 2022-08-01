package com.albertsnow.graphicdemo.looper

import android.util.Log

const val TAG = "native_looper_manager"

object LooperManager {

    /**
     * epoll not fit for regular file change.
     * is design for read\write block or unblock change.
     * pip, socket etc.
     */
    fun initLoop() {

        val testThread = Thread {
            Log.i(TAG, "looper start")

            val myLooper = MyLooper()
            myLooper.init()
            myLooper.wait(60 * 1000)

            Log.i(TAG, "looper finish")

        }
        testThread.name = "MyLooperTest"
        testThread.start()
    }

}