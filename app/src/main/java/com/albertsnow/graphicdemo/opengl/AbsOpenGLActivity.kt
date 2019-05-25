package com.albertsnow.graphicdemo.opengl

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.render.OpenGLRender

abstract class AbsOpenGLActivity : Activity() {
    lateinit var render: GLSurfaceView.Renderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        render = getOpenGLRender()

        val glView = findViewById<GLSurfaceView>(R.id.opengl_gl_view)
        glView.setEGLContextClientVersion(2)
        glView.setRenderer(render)
        glView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }

    abstract fun getOpenGLRender(): GLSurfaceView.Renderer

    abstract fun getLayoutId(): Int

}