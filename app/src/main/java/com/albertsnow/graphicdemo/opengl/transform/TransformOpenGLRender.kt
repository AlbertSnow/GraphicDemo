package com.albertsnow.graphicdemo.opengl.transform

import android.content.Context
import android.opengl.GLES20
import android.opengl.Matrix
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.render.AbsOpenGLProgram
import com.albertsnow.graphicdemo.opengl.render.OpenGLRender
import com.albertsnow.graphicdemo.opengl.render.OpenglProgram
import com.albertsnow.graphicdemo.opengl.render.Vec2F
import com.albertsnow.graphicdemo.opengl.shape.Triangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TransformOpenGLRender(context: Context, program: AbsOpenGLProgram = OpenglProgram(textureResourceID = R.drawable.fix_motor))
    : OpenGLRender(context, program){

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        Matrix.setLookAtM(viewMatrix.data, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        program.initProgram(context)
    }


    private lateinit var mTriangle: Any

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        mWidth = width
        mHeight = height
        size = Vec2F(floatArrayOf(width.toFloat(), height.toFloat()))

        val ratio: Float = mWidth.toFloat() / mHeight.toFloat()
        Matrix.frustumM(projectionMatrix.data, 0, -ratio, ratio, -1f, 1f, 3f, 7f)


        mTriangle = Triangle()
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

//        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix.data, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        Matrix.setIdentityM(modelMatrix.data, 0)
        Matrix.rotateM(modelMatrix.data, 0, 55f, 1f, 0f, 0f)

        program.draw(projectionMatrix = projectionMatrix,
                viewMatrix = viewMatrix,
                modelMatrix = modelMatrix,
                size = size)
    }


}
