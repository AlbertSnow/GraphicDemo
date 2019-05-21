package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import com.albertsnow.graphicdemo.opengl.utils.GlUtil

abstract class AbsOpenGLProgram : IOpenGLProgram {

    var programPointer: Int = 0

    private fun getProgram(vertexSource: String, fragmentSource: String) : Int {
        programPointer = GlUtil.createProgram(vertexSource, fragmentSource)
        return programPointer
    }

    override fun initProgram(context: Context) {
        programPointer = getProgram(getVertexShaderSource(), getFragmentShaderSource())
    }


}