package com.albertsnow.graphicdemo.opengl.utils

import android.opengl.GLES20


fun flatten(a: Array<FloatArray>): FloatArray {
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

fun flatten(a: Array<IntArray>): IntArray {
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

fun flatten(a: Array<ShortArray>): ShortArray {
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

fun flatten(a: Array<ByteArray>): ByteArray {
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

fun byteArrayFromIntArray(a: IntArray): ByteArray {
    val l = ByteArray(a.size)
    var k = 0
    while (k < a.size) {
        l[k] = (a[k] and 0xFF).toByte()
        k += 1
    }
    return l
}

fun generateOneBuffer(): Int {
    val buffer = intArrayOf(0)
    GLES20.glGenBuffers(1, buffer, 0)
    return buffer[0]
}