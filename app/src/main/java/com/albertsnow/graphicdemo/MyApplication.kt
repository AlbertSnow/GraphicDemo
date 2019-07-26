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

}
