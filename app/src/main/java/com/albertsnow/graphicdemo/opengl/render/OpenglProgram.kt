package com.albertsnow.graphicdemo.opengl.render

import android.content.Context
import android.opengl.GLES20
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.utils.TextureHelper
import java.nio.FloatBuffer
import java.nio.ShortBuffer


class OpenglProgram {
    private var program_box: Int = 0
    private var pos_coord_box: Int = 0
    private var pos_trans_box: Int = 0
    private var pos_proj_box: Int = 0
    private var vbo_coord_box: Int = 0

    private var vbo_texture_coord: Int = 0


    private var vbo_faces_box: Int = 0

    private val box_vert = ("uniform mat4 trans;\n"
            + "uniform mat4 proj;\n"
            + "attribute vec4 coord;\n"
            + "attribute vec2 aTexture;\n"
            + "varying vec2 vtexture;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "     vtexture = aTexture;\n"
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

    private fun flatten(a: Array<FloatArray>): FloatArray {
        var size = 0
        run {
            var k = 0
            while (k < a.size) {
                size += a[k].size
                k += 1
            }
        }
        val l = FloatArray(size)
        var offset = 0
        var k = 0
        while (k < a.size) {
            System.arraycopy(a[k], 0, l, offset, a[k].size)
            offset += a[k].size
            k += 1
        }
        return l
    }

    private fun flatten(a: Array<IntArray>): IntArray {
        var size = 0
        run {
            var k = 0
            while (k < a.size) {
                size += a[k].size
                k += 1
            }
        }
        val l = IntArray(size)
        var offset = 0
        var k = 0
        while (k < a.size) {
            System.arraycopy(a[k], 0, l, offset, a[k].size)
            offset += a[k].size
            k += 1
        }
        return l
    }

    private fun flatten(a: Array<ShortArray>): ShortArray {
        var size = 0
        run {
            var k = 0
            while (k < a.size) {
                size += a[k].size
                k += 1
            }
        }
        val l = ShortArray(size)
        var offset = 0
        var k = 0
        while (k < a.size) {
            System.arraycopy(a[k], 0, l, offset, a[k].size)
            offset += a[k].size
            k += 1
        }
        return l
    }

    private fun flatten(a: Array<ByteArray>): ByteArray {
        var size = 0
        run {
            var k = 0
            while (k < a.size) {
                size += a[k].size
                k += 1
            }
        }
        val l = ByteArray(size)
        var offset = 0
        var k = 0
        while (k < a.size) {
            System.arraycopy(a[k], 0, l, offset, a[k].size)
            offset += a[k].size
            k += 1
        }
        return l
    }

    private fun byteArrayFromIntArray(a: IntArray): ByteArray {
        val l = ByteArray(a.size)
        var k = 0
        while (k < a.size) {
            l[k] = (a[k] and 0xFF).toByte()
            k += 1
        }
        return l
    }

    private fun generateOneBuffer(): Int {
        val buffer = intArrayOf(0)
        GLES20.glGenBuffers(1, buffer, 0)
        return buffer[0]
    }

    private var textureId = -1
    private var textLoc = -1
    private var u_TextureUnit = -1

    fun init(context: Context) {
        program_box = GLES20.glCreateProgram()
        val vertShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
        GLES20.glShaderSource(vertShader, box_vert)
        GLES20.glCompileShader(vertShader)
        val fragShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
        GLES20.glShaderSource(fragShader, box_frag)
        GLES20.glCompileShader(fragShader)
        GLES20.glAttachShader(program_box, vertShader)
        GLES20.glAttachShader(program_box, fragShader)
        GLES20.glLinkProgram(program_box)
        GLES20.glUseProgram(program_box)
        pos_coord_box = GLES20.glGetAttribLocation(program_box, "coord")
        textLoc = GLES20.glGetAttribLocation(program_box, "aTexture")
        pos_trans_box = GLES20.glGetUniformLocation(program_box, "trans")
        pos_proj_box = GLES20.glGetUniformLocation(program_box, "proj")
        u_TextureUnit = GLES20.glGetUniformLocation(program_box, "u_TextureUnit")

        vbo_coord_box = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo_coord_box)
        val cube_vertices = arrayOf(
                /* +z */floatArrayOf(1.0f / 2, 1.0f / 2, 0.01f / 2),
                        floatArrayOf(1.0f / 2, -1.0f / 2, 0.01f / 2),
                        floatArrayOf(-1.0f / 2, -1.0f / 2, 0.01f / 2),
                        floatArrayOf(-1.0f / 2, 1.0f / 2, 0.01f / 2),
                /* -z */floatArrayOf(1.0f / 2, 1.0f / 2, -0.01f / 2),
                        floatArrayOf(1.0f / 2, -1.0f / 2, -0.01f / 2),
                        floatArrayOf(-1.0f / 2, -1.0f / 2, -0.01f / 2),
                        floatArrayOf(-1.0f / 2, 1.0f / 2, -0.01f / 2))
        val cube_vertices_buffer = FloatBuffer.wrap(flatten(cube_vertices))
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cube_vertices_buffer.limit() * 4, cube_vertices_buffer, GLES20.GL_DYNAMIC_DRAW)


        vbo_texture_coord = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo_texture_coord)

        val cubeTextureCoordinateData = floatArrayOf(
                // Front face
                0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f)
        val texture_buffer = FloatBuffer.wrap(cubeTextureCoordinateData)
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, texture_buffer.limit() * 4, texture_buffer, GLES20.GL_DYNAMIC_DRAW)


        vbo_faces_box = generateOneBuffer()
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, vbo_faces_box)
        val cube_faces = arrayOf(
                /* +z */shortArrayOf(3, 2, 1, 0), /* -y */shortArrayOf(2, 3, 7, 6), /* +y */shortArrayOf(0, 1, 5, 4),
                /* -x */shortArrayOf(3, 0, 4, 7), /* +x */shortArrayOf(1, 2, 6, 5), /* -z */shortArrayOf(4, 5, 6, 7))
        val cube_faces_buffer = ShortBuffer.wrap(flatten(cube_faces))
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, cube_faces_buffer.limit() * 2, cube_faces_buffer, GLES20.GL_STATIC_DRAW)

        textureId = TextureHelper.loadTexture(context, R.drawable.flower)
    }


    fun render(projectionMatrix: Matrix44F, cameraview: Matrix44F, size: Vec2F) {
        val size0 = size.data[0]
        val size1 = size.data[1]

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo_coord_box)
        val height = size0 / 1000
        val cube_vertices = arrayOf(
                floatArrayOf(size0 / 2, size1 / 2, height / 2),
                floatArrayOf(size0 / 2, -size1 / 2, height / 2),
                floatArrayOf(-size0 / 2, -size1 / 2, height / 2),
                floatArrayOf(-size0 / 2, size1 / 2, height / 2))
        val cube_vertices_buffer = FloatBuffer.wrap(flatten(cube_vertices))
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, cube_vertices_buffer.limit() * 4, cube_vertices_buffer, GLES20.GL_DYNAMIC_DRAW)

        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)
        GLES20.glUseProgram(program_box)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo_coord_box)
        GLES20.glEnableVertexAttribArray(pos_coord_box)
        GLES20.glVertexAttribPointer(pos_coord_box, 3, GLES20.GL_FLOAT, false, 0, 0)

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo_texture_coord)
        GLES20.glEnableVertexAttribArray(textLoc)
        GLES20.glVertexAttribPointer(textLoc, 2, GLES20.GL_FLOAT, false, 0, 0)


        GLES20.glUniformMatrix4fv(pos_trans_box, 1, false, cameraview.data, 0)
        GLES20.glUniformMatrix4fv(pos_proj_box, 1, false, projectionMatrix.data, 0)

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId)

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, vbo_faces_box)
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, 4, GLES20.GL_UNSIGNED_SHORT, 0)
    }
}