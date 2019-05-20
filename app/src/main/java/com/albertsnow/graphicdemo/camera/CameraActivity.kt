package com.albertsnow.graphicdemo.camera

import android.app.Activity
import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.opengl.GLES11Ext
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import android.widget.Toast
import com.albertsnow.graphicdemo.R
import com.albertsnow.graphicdemo.opengl.render.CameraTextureGLProgram
import com.albertsnow.graphicdemo.opengl.utils.CameraUtils
import com.albertsnow.graphicdemo.opengl.utils.PermissionHelper
import com.albertsnow.graphicdemo.widget.AspectFrameLayout
import java.io.IOException

class CameraActivity : Activity(), SurfaceHolder.Callback, SurfaceTexture.OnFrameAvailableListener {
    private val TAG : String = "CameraActivity"
    private val VIDEO_WIDTH = 1280  // dimensions for 720p video
    private val VIDEO_HEIGHT = 720
    private val DESIRED_PREVIEW_FPS = 15

    private var mCamera: Camera? = null
    private lateinit var mCameraTextureProgram: CameraTextureGLProgram
    private lateinit var cameraTexture: SurfaceTexture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_activity)
        val surfaceView = findViewById<SurfaceView>(R.id.camera_surface_view)

        mCameraTextureProgram = CameraTextureGLProgram()

        surfaceView.holder.addCallback(this)
    }

    override fun onResume() {
        super.onResume()

        if (!PermissionHelper.hasCameraPermission(this)) {
            PermissionHelper.requestCameraPermission(this, false)
        } else {
            if (mCamera == null) {
                // Ideally, the frames from the camera are at the same resolution as the input to
                // the video encoder so we don't have to scale.
                openCamera(VIDEO_WIDTH, VIDEO_HEIGHT, DESIRED_PREVIEW_FPS)
            }
//            startPreview()
        }
    }

    override fun onPause() {
        super.onPause()
        releaseCamera()
    }

    private fun startPreview() {
        if (mCamera != null) {
            Log.d(TAG, "starting camera preview")
            try {
                mCamera!!.setPreviewTexture(cameraTexture)
            } catch (ioe: IOException) {
                throw RuntimeException(ioe)
            }

            mCamera!!.startPreview()
        }
    }

    private fun openCamera(desiredWidth: Int, desiredHeight: Int, desiredFps: Int) {
        if (mCamera != null) {
            throw RuntimeException("camera already initialized")
        }

        val info = Camera.CameraInfo()

        // Try to find a front-facing camera (e.g. for videoconferencing).
        val numCameras = Camera.getNumberOfCameras()
        for (i in 0 until numCameras) {
            Camera.getCameraInfo(i, info)
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mCamera = Camera.open(i)
                break
            }
        }
        if (mCamera == null) {
            mCamera = Camera.open()    // opens first back-facing camera
        }
        if (mCamera == null) {
            throw RuntimeException("Unable to open camera")
        }

        val parms = mCamera!!.parameters

        CameraUtils.choosePreviewSize(parms, desiredWidth, desiredHeight)

        // Give the camera a hint that we're recording video.  This can have a big
        // impact on frame rate.
        parms.setRecordingHint(true)

        mCamera!!.setParameters(parms)

        val cameraPreviewSize = parms.getPreviewSize()

        val layout = findViewById(R.id.continuousCapture_afl) as AspectFrameLayout

        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        if (display.rotation == Surface.ROTATION_0) {
            mCamera!!.setDisplayOrientation(90)
            layout.setAspectRatio(cameraPreviewSize.height.toDouble() / cameraPreviewSize.width)
        } else if (display.rotation == Surface.ROTATION_270) {
            layout.setAspectRatio(cameraPreviewSize.height.toDouble() / cameraPreviewSize.width)
            mCamera!!.setDisplayOrientation(180)
        } else {
            // Set the preview aspect ratio.
            layout.setAspectRatio(cameraPreviewSize.width.toDouble() / cameraPreviewSize.height)
        }
    }

    /**
     * Stops camera preview, and releases the camera to the system.
     */
    private fun releaseCamera() {
        if (mCamera != null) {
            mCamera!!.stopPreview()
            mCamera!!.release()
            mCamera = null
            Log.d(TAG, "releaseCamera -- done")
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.d(TAG, "surfaceChanged fmt=" + format + " size=" + width + "x" + height +
                " holder=" + holder)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        Log.d(TAG, "surfaceDestroyed holder=$holder")
    }


    override fun surfaceCreated(holder: SurfaceHolder?) {
        val textureId = mCameraTextureProgram.createTextureObject(GLES11Ext.GL_TEXTURE_EXTERNAL_OES)
        cameraTexture = SurfaceTexture(textureId)
        cameraTexture.setOnFrameAvailableListener(this)
        startPreview()
    }

    override fun onFrameAvailable(surfaceTexture: SurfaceTexture?) {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!PermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(this,
                    "Camera permission is needed to run this application", Toast.LENGTH_LONG).show()
            PermissionHelper.launchPermissionSettings(this)
            finish()
        } else {
            openCamera(VIDEO_WIDTH, VIDEO_HEIGHT, DESIRED_PREVIEW_FPS)
        }
    }

}