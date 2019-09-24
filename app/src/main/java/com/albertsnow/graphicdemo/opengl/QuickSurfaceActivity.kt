package com.albertsnow.graphicdemo.opengl

import android.app.Activity
import android.opengl.GLES10
import android.opengl.GLES20
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView

import com.albertsnow.graphicdemo.R

class QuickSurfaceActivity : Activity(), SurfaceHolder.Callback {
    val TAG = "QuickSurfaceActivity"

    private lateinit var mySurfaceView: SurfaceView

    @Volatile
    private var mBouncing: Boolean = false
    private var mBounceThread: Thread? = null

    private val BOUNCE_STEPS = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quick_surface_activity)
        initView()
    }

    private fun initView() {
        mySurfaceView = findViewById(R.id.quick_surface_view)
        mySurfaceView.holder.addCallback(this)
    }


    override fun onPause() {
        super.onPause()
        if (mBounceThread != null) {
            stopBouncing()
        }
    }


    private fun clickBounce(): Unit {
        if (mBounceThread == null) {
            startBouncing()
        } else {
            stopBouncing()
        }
    }

    private fun startBouncing() {
        val surface = mySurfaceView.holder.surface
        if (surface == null || !surface.isValid) {
            Log.e(TAG, "surface view ")
            return
        }

        mBounceThread = Thread {
            while (true) {
                val startWhen = System.nanoTime()

                for (i in 0 until BOUNCE_STEPS step 1) {
                    if (!mBouncing) return@Thread
                    drawSomething(surface, i)
                }

                val duration = System.nanoTime() - startWhen
                val framePerSec = 1000000000.0 / (duration / (BOUNCE_STEPS * 2.0))
                Log.d(TAG, "Bouncing at $framePerSec fps")
            }
        }

        mBouncing = true;
        mBounceThread?.name = "Bouncer"
        mBounceThread?.start()
    }

    private fun stopBouncing() {
        mBouncing = false
        mBounceThread?.join()
        mBounceThread = null
    }

    private fun drawSomething(surface: Surface, i: Int) {
        val eglCore = EglCore()
        val win = WindowSurface(eglCore, surface, false)
        win.makeCurrent()

        GLES20.glClearColor(0f, 0f, 0f, 0f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        GLES20.glEnable(GLES20.GL_SCISSOR_TEST)
        GLES20.glScissor(0, 0, 100, 100)
        GLES20.glClearColor(0.0f, 0.0f, 1f, 0.25f)

//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glDisable(GLES20.GL_SCISSOR_TEST)

        win.swapBuffers()
        win.release()
        eglCore.release()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        drawSomething(holder!!.surface, 1)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
    }


}
