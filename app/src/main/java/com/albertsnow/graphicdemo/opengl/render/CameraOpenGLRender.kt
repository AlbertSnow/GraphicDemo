package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import android.opengl.GLES11Ext
import android.opengl.GLES20
import android.opengl.Matrix
import com.albertsnow.graphicdemo.opengl.shape.Triangle
import com.albertsnow.graphicdemo.opengl.utils.GlUtil

class CameraOpenGLRender(var context: Context, val program: CameraTextureGLProgram) {

    private lateinit var size : Vec2F

    private var mWidth: Int = 0
    private var mHeight: Int = 0

    private val projectionMatrix = Matrix44F()
    private val cameraMatrix = Matrix44F()


     fun onSurfaceCreated() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        Matrix.setLookAtM(cameraMatrix.data, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        program.initProgram(context)
    }


    private lateinit var mTriangle: Any

     fun onSurfaceChanged(width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        mWidth = width
        mHeight = height
        size = Vec2F(floatArrayOf(width.toFloat(), height.toFloat()))

        val ratio: Float = mWidth.toFloat() / mHeight.toFloat()
        Matrix.frustumM(projectionMatrix.data, 0, -ratio, ratio, -1f, 1f, 3f, 7f)


        mTriangle = Triangle()
    }

     fun onDrawFrame() {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        GlUtil.checkGlError("glClear")

//        // Set the camera position (View matrix)
        Matrix.setLookAtM(cameraMatrix.data, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        GlUtil.checkGlError("setLookAtM")

        program.draw(projectionMatrix = projectionMatrix,
                cameraview = cameraMatrix,
                size = size)
    }

    fun createTextureObject() : Int{
        return program.createTextureObject(GLES11Ext.GL_TEXTURE_EXTERNAL_OES)
    }


}
