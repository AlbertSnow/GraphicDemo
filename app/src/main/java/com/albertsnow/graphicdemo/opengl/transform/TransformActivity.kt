package com.albertsnow.graphicdemo.opengl.transform

import android.opengl.GLSurfaceView
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.AbsOpenGLActivity

class TransformActivity : AbsOpenGLActivity() {

    override fun getOpenGLRender(): GLSurfaceView.Renderer {
        return TransformOpenGLRender(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.transform_layout
    }


}