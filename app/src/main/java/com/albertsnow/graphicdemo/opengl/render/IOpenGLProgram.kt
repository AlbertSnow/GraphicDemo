package com.albertsnow.graphicdemo.opengl.render

import android.content.Context

interface IOpenGLProgram {
    fun initProgram(context: Context)
    fun draw(projectionMatrix: Matrix44F, cameraview: Matrix44F, size: Vec2F)
    fun getVertexShaderSource(): String
    fun getFragmentShaderSource(): String
}
