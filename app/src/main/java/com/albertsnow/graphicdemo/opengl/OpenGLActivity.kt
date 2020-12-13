package com.albertsnow.graphicdemo.opengl

import android.opengl.GLSurfaceView
import android.view.View
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.render.OpenGLRender

class OpenGLActivity : AbsOpenGLActivity() {
    override fun getOpenGLRender(): GLSurfaceView.Renderer {
        return OpenGLRender(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.opengl_layout
    }


}