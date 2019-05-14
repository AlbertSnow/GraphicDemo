package com.albertsnow.graphicdemo.opengl.shape

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

const val COORDS_PER_VERTEX = 3
var triangleCoords = floatArrayOf(     // in counterclockwise order:
        0.0f, 0.622008459f, 0.0f,      // top
        -0.5f, -0.311004243f, 0.0f,    // bottom left
        0.5f, -0.311004243f, 0.0f      // bottom right
)

class Triangle {

    val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    private var vertexBuffer: FloatBuffer =
            ByteBuffer.allocateDirect(triangleCoords.size * 4).run {
                order(ByteOrder.nativeOrder())

                asFloatBuffer().apply {
                    put(triangleCoords)
                    position(0)
                }
            }
}