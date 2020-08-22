package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import android.opengl.GLES20
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.utils.TextureHelper
import com.albertsnow.graphicdemo.opengl.utils.flatten
import com.albertsnow.graphicdemo.opengl.utils.generateOneBuffer
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class DynamicTextureGLProgram : AbsOpenGLProgram() {
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

    private val box_frag = ("#ifdef GL_ES\n"
            + "precision highp float;\n"
            + "#endif\n"
            + "varying vec2 vtexture;\n"
            + "uniform sampler2D u_TextureUnit;\n"
            + "\n"
            + "void main(void)\n"
            + "{\n"
            + "     gl_FragColor=texture2D(u_TextureUnit,vtexture);\n"
            + "}\n"
            + "\n")

    private var texture_coord_location = -1
    private var u_sampler_2D_location = -1
    private var countDown = 0

    private lateinit var mContext : Context


    override fun getVertexShaderSource(): String {
        return box_vert
    }

    override fun getFragmentShaderSource(): String {
        return box_frag
    }


    override fun initProgram(context: Context) {
        super.initProgram(context)
        mContext = context

        vertex_coord_location = GLES20.glGetAttribLocation(programPointer, "coord")
        texture_coord_location = GLES20.glGetAttribLocation(programPointer, "aTexture")
        camera_matrix_location = GLES20.glGetUniformLocation(programPointer, "trans")
        project_matrix_location = GLES20.glGetUniformLocation(programPointer, "proj")
        u_sampler_2D_location = GLES20.glGetUniformLocation(programPointer, "u_TextureUnit")

        vertex_coord_buffer = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex_coord_buffer)
        val cube_vertices = arrayOf(
                floatArrayOf(1.0f / 2, 1.0f / 2, 0.01f / 2),
                floatArrayOf(1.0f / 2, -1.0f / 2, 0.01f / 2),
                floatArrayOf(-1.0f / 2, -1.0f / 2, 0.01f / 2),
                floatArrayOf(-1.0f / 2, 1.0f / 2, 0.01f / 2)
        )
        val cube_vertices_buffer = FloatBuffer.wrap(flatten(cube_vertices))
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cube_vertices_buffer.limit() * 4,
                cube_vertices_buffer, GLES20.GL_DYNAMIC_DRAW)


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
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, texture_buffer.limit() * 4, texture_buffer,
                GLES20.GL_DYNAMIC_DRAW)


        index_buffer = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, index_buffer)
        val cube_faces = arrayOf(
                shortArrayOf(2, 1, 0, 0, 3, 2)
        )
        val cube_faces_buffer = ShortBuffer.wrap(flatten(cube_faces))
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, cube_faces_buffer.limit() * 2,
                cube_faces_buffer, GLES20.GL_STATIC_DRAW)
    }


    override fun draw(projectionMatrix: Matrix44F, cameraview: Matrix44F,
                      modelMatrix: Matrix44F, size: Vec2F) {
        GLES20.glUseProgram(programPointer)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vertex_coord_buffer)
        GLES20.glEnableVertexAttribArray(vertex_coord_location)
        GLES20.glVertexAttribPointer(vertex_coord_location, 3, GLES20.GL_FLOAT,
                false, 0, 0)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, texture_coord_buffer)
        GLES20.glEnableVertexAttribArray(texture_coord_location)
        GLES20.glVertexAttribPointer(texture_coord_location, 2, GLES20.GL_FLOAT,
                false, 0, 0)

        GLES20.glUniformMatrix4fv(camera_matrix_location, 1, false,
                cameraview.data, 0)
        GLES20.glUniformMatrix4fv(project_matrix_location, 1, false,
                projectionMatrix.data, 0)

        handleTexture()

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, index_buffer)
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, 0)
    }

    private fun handleTexture() {
        var resId = 0
        if (countDown > 20) {
            resId = R.drawable.fix_motor
        } else {
            resId = R.drawable.flower
        }
        var textureId = TextureHelper.loadTexture(mContext, resId)
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId)

        countDown++
        if (countDown > 40) {
            countDown = 0
        }
    }


}
