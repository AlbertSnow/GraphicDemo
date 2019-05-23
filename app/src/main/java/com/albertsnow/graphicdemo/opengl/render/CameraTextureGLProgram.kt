package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import android.graphics.Bitmap
import android.opengl.GLES11Ext
import android.opengl.GLES20
import android.util.Log
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.utils.GlUtil
import com.albertsnow.graphicdemo.opengl.utils.TextureHelper
import com.albertsnow.graphicdemo.opengl.utils.flatten
import com.albertsnow.graphicdemo.opengl.utils.generateOneBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

class CameraTextureGLProgram : AbsOpenGLProgram() {
    private var vertex_coord_location: Int = 0
    private var camera_matrix_location: Int = 0
    private var project_matrix_location: Int = 0
    private var vertex_coord_buffer: Int = 0

    private var texture_coord_buffer: Int = 0


    private var index_buffer: Int = 0

    private val box_vert = ("uniform mat4 trans;\n"
            + "uniform mat4 proj;\n"
            + "attribute vec4 coord;\n"
            + "attribute vec2 aTexture;\n"
            + "varying vec2 vtexture;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "    vtexture = aTexture;\n"
            + "    gl_Position = proj*trans*coord;\n"
            + "}\n"
            + "\n")

    private val box_frag = (
            "#extension GL_OES_EGL_image_external : require\n"
                    + "precision mediump float;\n"
                    + "varying vec2 vtexture;\n"
                    + "uniform samplerExternalOES u_TextureUnit;\n"
                    + "uniform sampler2D u_overlay;\n"
                    + "\n"
                    + "void main(void)\n"
                    + "{\n"
                    + "     vec4 camera_preview = texture2D(u_TextureUnit,vtexture);\n"
                    + "     vec4 overlay_color = texture2D(u_overlay,vtexture);\n"
                    + "     gl_FragColor = camera_preview * 0.7 + overlay_color * 0.3;\n"
                    + "}\n"
                    + "\n")

    private var texture_coord_location = -1
    private var u_sampler_2D_location = -1
    private var u_overlay_location = -1

    private lateinit var mContext: Context
    public var callback: CameraGlCallBack ?= null


    companion object {
        var saveCamera = false
    }

    override fun getVertexShaderSource(): String {
        return box_vert
    }

    override fun getFragmentShaderSource(): String {
        return box_frag
    }


    public var externalTextureId: Int = 0

    private var overlayTextureId: Int = 0

    override fun initProgram(context: Context) {
        super.initProgram(context)
        mContext = context

        vertex_coord_location = GLES20.glGetAttribLocation(programPointer, "coord")
        GlUtil.checkLocation(vertex_coord_location, "coord")
        texture_coord_location = GLES20.glGetAttribLocation(programPointer, "aTexture")
        GlUtil.checkLocation(texture_coord_location, "aTexture")
        camera_matrix_location = GLES20.glGetUniformLocation(programPointer, "trans")
        GlUtil.checkLocation(camera_matrix_location, "trans")
        project_matrix_location = GLES20.glGetUniformLocation(programPointer, "proj")
        GlUtil.checkLocation(project_matrix_location, "proj")
        u_sampler_2D_location = GLES20.glGetUniformLocation(programPointer, "u_TextureUnit")
        GlUtil.checkLocation(u_sampler_2D_location, "u_TextureUnit")
        u_overlay_location = GLES20.glGetUniformLocation(programPointer, "u_overlay")
        GlUtil.checkLocation(u_overlay_location, "u_overlay")

        vertex_coord_buffer = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex_coord_buffer)
        val cube_vertices = arrayOf(
                floatArrayOf(-1.0f, -1.0f, 0.01f),
                floatArrayOf(-1.0f, 1.0f, 0.01f),
                floatArrayOf(1.0f, 1.0f, 0.01f),
                floatArrayOf(1.0f, -1.0f, 0.01f)
        )
        val cube_vertices_buffer = FloatBuffer.wrap(flatten(cube_vertices))
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cube_vertices_buffer.limit() * 4, cube_vertices_buffer, GLES20.GL_DYNAMIC_DRAW)


        //bottom-left is (0,0)
        texture_coord_buffer = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, texture_coord_buffer)
        val cubeTextureCoordinateData = floatArrayOf(
                1f, 1f,
                1f, 0f,
                0f, 0f,
                0f, 1f
        )
        val texture_buffer = FloatBuffer.wrap(cubeTextureCoordinateData)
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, texture_buffer.limit() * 4, texture_buffer, GLES20.GL_DYNAMIC_DRAW)


        index_buffer = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, index_buffer)
        val cube_faces = arrayOf(
                shortArrayOf(2, 1, 0, 0, 3, 2)
        )
        val cube_faces_buffer = ShortBuffer.wrap(flatten(cube_faces))
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, cube_faces_buffer.limit() * 2, cube_faces_buffer, GLES20.GL_STATIC_DRAW)

        overlayTextureId = createResourceTexture(R.drawable.flower)
        externalTextureId = createExternalTexture()
        GlUtil.checkGlError("createExternalTexture")
    }


    private var screenWidth: Int = 0
    private var screenHeight: Int = 0


    override fun draw(projectionMatrix: Matrix44F, cameraview: Matrix44F, size: Vec2F) {
        GLES20.glUseProgram(programPointer)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex_coord_buffer)
        GLES20.glEnableVertexAttribArray(vertex_coord_location)
        GLES20.glVertexAttribPointer(vertex_coord_location, 3, GLES20.GL_FLOAT, false, 0, 0)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, texture_coord_buffer)
        GLES20.glEnableVertexAttribArray(texture_coord_location)
        GLES20.glVertexAttribPointer(texture_coord_location, 2, GLES20.GL_FLOAT, false, 0, 0)

        GLES20.glUniformMatrix4fv(camera_matrix_location, 1, false, cameraview.data, 0)
        GLES20.glUniformMatrix4fv(project_matrix_location, 1, false, projectionMatrix.data, 0)

        GlUtil.checkGlError("camera_matrix_location project_matrix_location")

        GLES20.glUniform1i(u_sampler_2D_location, 0)
        GlUtil.checkGlError("u_sampler_2D_location")
        GLES20.glUniform1i(u_overlay_location, 1)
        GlUtil.checkGlError("u_overlay_location")

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, index_buffer)
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, 0)


        screenWidth = size.data[0].toInt()
        screenHeight = size.data[1].toInt()

        if (CameraTextureGLProgram.saveCamera) {
            CameraTextureGLProgram.saveCamera = false
            val pixels = IntBuffer.allocate(screenWidth * screenHeight)

            val startTime = System.currentTimeMillis()
            GLES20.glReadPixels(0, 0, screenWidth, screenHeight, GLES20.GL_RGBA,  GLES20.GL_UNSIGNED_BYTE,  pixels )
            Log.i("TimeCast", "cast: " + (System.currentTimeMillis() - startTime))


            val castStartTime = System.currentTimeMillis()
            val stitchBmp = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888)
            stitchBmp.copyPixelsFromBuffer(pixels)
            Log.i("TimeCast", "castStartTime: " + (System.currentTimeMillis() - castStartTime))

            callback?.onCapture(stitchBmp)
        }

    }

    fun createExternalTexture(): Int {
        val textures = IntArray(1)
        GLES20.glGenTextures(1, textures, 0)
        GlUtil.checkGlError("glGenTextures")

        val texId = textures[0]
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texId)
        GlUtil.checkGlError("glBindTexture $texId")

        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST.toFloat())
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_CLAMP_TO_EDGE)
        GlUtil.checkGlError("glTexParameter")

        return texId
    }

    fun createResourceTexture(drawableId: Int): Int {
        var textureId = TextureHelper.loadTexture(mContext, drawableId)
        GLES20.glActiveTexture(GLES20.GL_TEXTURE1)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId)
        GlUtil.checkGlError("GL_TEXTURE_2D")

        return textureId
    }


    interface CameraGlCallBack {
        fun onCapture(frame: Bitmap)
    }

}
