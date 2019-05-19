package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import android.opengl.GLES20

abstract class AbsOpenGLProgram : IOpenGLProgram {

    var programPointer: Int = 0

    private fun getProgram(vertexSource: String, fragmentSource: String) : Int {
        val programPointer = GLES20.glCreateProgram()
        val vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
        GLES20.glShaderSource(vertexShader, vertexSource)
        GLES20.glCompileShader(vertexShader)
        val fragShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
        GLES20.glShaderSource(fragShader, fragmentSource)
        GLES20.glCompileShader(fragShader)

        GLES20.glAttachShader(programPointer, vertexShader)
        GLES20.glAttachShader(programPointer, fragShader)
        GLES20.glLinkProgram(programPointer)
        GLES20.glUseProgram(programPointer)
        return programPointer
    }

    override fun initProgram(context: Context) {
        programPointer = getProgram(getVertexShaderSource(), getFragmentShaderSource())
    }


}