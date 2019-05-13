package com.albertsnow.graphicdemo.opengl.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLUtils



object TextureHelper {

    fun loadTexture(context: Context, resId: Int): Int {
        val textureObjIds = IntArray(1)
        GLES20.glGenTextures(1, textureObjIds, 0)
        if (textureObjIds[0] == 0) {
            return 0
        }
        val options = BitmapFactory.Options()
        options.inScaled = false
        val bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options)
        if (bitmap == null) {
            GLES20.glDeleteTextures(1, textureObjIds, 0)
            return 0
        }
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjIds[0])//bind
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)
        bitmap.recycle()
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)//unbind
        return textureObjIds[0]
    }

}