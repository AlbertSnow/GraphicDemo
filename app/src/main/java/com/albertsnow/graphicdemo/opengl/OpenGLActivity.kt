package com.albertsnow.graphicdemo.opengl

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.render.OpenGLRender

class OpenGLActivity : Activity() {
    lateinit var render: OpenGLRender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opengl_layout)

        render = OpenGLRender(this)

        val glView = findViewById<GLSurfaceView>(R.id.opengl_gl_view)
        glView.setEGLContextClientVersion(2)
        glView.setRenderer(render)
        glView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }

}