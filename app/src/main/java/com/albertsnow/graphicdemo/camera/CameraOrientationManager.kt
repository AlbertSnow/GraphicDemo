package com.albertsnow.graphicdemo.camera

import android.content.Context
import android.view.Surface
import android.view.WindowManager
import com.albertsnow.graphicdemo.Utils.LogUtil



// the rotation relative to nature orientation (vertical orientation int portrait nature orientation is 0
fun getCameraOrientation(context: Context): Int {
    val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val rotation = display.rotation

    LogUtil.debug("Rotation： ${rotation}")

    var orientation: Int
    val expectPortrait: Boolean
    when (rotation) {
        Surface.ROTATION_0 -> {
            orientation = 90
            expectPortrait = true
        }
        Surface.ROTATION_90 -> {
            orientation = 0
            expectPortrait = false
        }
        Surface.ROTATION_180 -> {
            orientation = 270
            expectPortrait = true
        }
        Surface.ROTATION_270 -> {
            orientation = 180
            expectPortrait = false
        }
        else -> {
            orientation = 90
            expectPortrait = true
        }
    }
    val isPortrait = display.height > display.width

    if (isPortrait != expectPortrait) {
        orientation = (orientation + 270) % 360
    }

    LogUtil.debug("isPortrait： ${isPortrait}, orientation: ${orientation}")

    return orientation
}