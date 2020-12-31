package com.albertsnow.graphicdemo.widget.scroll;

/**
 * Created by handy on 17/6/5.
 * modified by Linchen: add support for menu photo
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;

public class TouchScaleImageView extends android.support.v7.widget.AppCompatImageView {

    protected Matrix mImgMatrix;

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF last = new PointF();
    PointF start = new PointF();
    float minScale = 1f;
    float maxScale = 3f;
    float[] m;


    int viewWidth, viewHeight;
    protected float saveScale = 1f;
    protected float origWidth, origHeight;
    int oldMeasuredWidth, oldMeasuredHeight;
    private int mTouchSlop;
    protected boolean mTouchable = true;
    ScaleGestureDetector mScaleDetector;
    private OnScaleCallback mScaleCallback;
    Context context;

    public TouchScaleImageView(Context context) {
        super(context);
        sharedConstructing(context);
    }

    public TouchScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructing(context);
    }

    public TouchScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sharedConstructing(context);
    }

    public interface OnScaleCallback {
        void onScale();
        void onScaleEnd();
    }

    public void setScaleCallback(OnScaleCallback callback) {
        mScaleCallback = callback;
    }

    public void setTouchable(boolean touchable) {
        mTouchable = touchable;
    }

    private void sharedConstructing(Context context) {
        super.setClickable(true);
        this.context = context;
        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        mImgMatrix = new Matrix();
        m = new float[9];
        setImageMatrix(mImgMatrix);
        setScaleType(ImageView.ScaleType.MATRIX);

        setOnTouchListener((v, event) -> {
            if (!mTouchable) {
                return false;
            }
            mScaleDetector.onTouchEvent(event);
            PointF curr = new PointF(event.getX(), event.getY());

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    last.set(curr);
                    start.set(last);
                    mode = DRAG;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        float deltaX = curr.x - last.x;
                        float deltaY = curr.y - last.y;
                        float fixTransX = getFixDragTrans(deltaX, viewWidth, origWidth * saveScale);
                        float fixTransY = getFixDragTrans(deltaY, viewHeight, origHeight * saveScale);
                        mImgMatrix.postTranslate(fixTransX, fixTransY);
                        fixTrans();
                        last.set(curr.x, curr.y);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    mode = NONE;
                    int xDiff = (int) Math.abs(curr.x - start.x);
                    int yDiff = (int) Math.abs(curr.y - start.y);
                    if (xDiff < mTouchSlop && yDiff < mTouchSlop)
                        if (!executeClickAction(curr.x, curr.y)) {
                            //点击无菜单时候才执行click操作
                            performClick();
                        }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    break;
            }

            setImageMatrix(mImgMatrix);
            invalidate();
            return true; // indicate event was handled
        });
    }

    protected boolean executeClickAction(float x, float y) {
        return false;
    }

    public void setMaxZoom(float x) {
        maxScale = x;
    }

    public boolean isScaled() {
        return saveScale != 1;
    }

    public void resetStatus() {
        saveScale = 1;
    }

    protected void onTouchScaling() {
        if (mScaleCallback != null) {
            mScaleCallback.onScale();
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mode = ZOOM;
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float mScaleFactor = detector.getScaleFactor();
            float origScale = saveScale;
            saveScale *= mScaleFactor;
            if (saveScale > maxScale) {
                saveScale = maxScale;
                mScaleFactor = maxScale / origScale;
            } else if (saveScale < minScale) {
                saveScale = minScale;
                mScaleFactor = minScale / origScale;
            }

            if (origWidth * saveScale <= viewWidth || origHeight * saveScale <= viewHeight)
                mImgMatrix.postScale(mScaleFactor, mScaleFactor, viewWidth / 2, viewHeight / 2);
            else
                mImgMatrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());

            fixTrans();
            onTouchScaling();
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);

            if (mScaleCallback != null) {
                mScaleCallback.onScaleEnd();
            }
        }
    }

    void fixTrans() {
        mImgMatrix.getValues(m);
        float transX = m[Matrix.MTRANS_X];
        float transY = m[Matrix.MTRANS_Y];

        float fixTransX = getFixTrans(transX, viewWidth, origWidth * saveScale);
        float fixTransY = getFixTrans(transY, viewHeight, origHeight * saveScale);

        if (fixTransX != 0 || fixTransY != 0)
            mImgMatrix.postTranslate(fixTransX, fixTransY);
    }

    float getFixTrans(float trans, float viewSize, float contentSize) {
        float minTrans, maxTrans;

        if (contentSize <= viewSize) {
            minTrans = 0;
            maxTrans = viewSize - contentSize;
        } else {
            minTrans = viewSize - contentSize;
            maxTrans = 0;
        }

        if (trans < minTrans)
            return -trans + minTrans;
        if (trans > maxTrans)
            return -trans + maxTrans;
        return 0;
    }

    float getFixDragTrans(float delta, float viewSize, float contentSize) {
        if (contentSize <= viewSize) {
            return 0;
        }
        return delta;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        //
        // Rescales image on rotation
        //
        if (oldMeasuredHeight == viewWidth && oldMeasuredHeight == viewHeight
                || viewWidth == 0 || viewHeight == 0)
            return;
        oldMeasuredHeight = viewHeight;
        oldMeasuredWidth = viewWidth;

        if (saveScale == 1) {
            //Fit to screen.
            float scale;

            Drawable drawable = getDrawable();
            if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0)
                return;
            doInitScale(drawable);
        }
        fixTrans();
    }

    /**
     * 设置Image初始化时的效果
     * @param drawable
     */
    protected void doInitScale(Drawable drawable) {
        float scale;
        int bmWidth = drawable.getIntrinsicWidth();
        int bmHeight = drawable.getIntrinsicHeight();
        float scaleX = (float) viewWidth / (float) bmWidth;
        float scaleY = (float) viewHeight / (float) bmHeight;
        scale = Math.min(scaleX, scaleY);
        mImgMatrix.setScale(scale, scale);

        // Center the image
        float redundantYSpace = (float) viewHeight - (scale * (float) bmHeight);
        float redundantXSpace = (float) viewWidth - (scale * (float) bmWidth);
        redundantYSpace /= (float) 2;
        redundantXSpace /= (float) 2;

        mImgMatrix.postTranslate(redundantXSpace, redundantYSpace);

        origWidth = viewWidth - 2 * redundantXSpace;
        origHeight = viewHeight - 2 * redundantYSpace;
        setImageMatrix(mImgMatrix);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mNeedBlackBg) {
            canvas.drawColor(Color.BLACK);
        }
        super.onDraw(canvas);
    }

    private boolean mNeedBlackBg = false;

    public void needBlackBg(boolean isNeed) {
        mNeedBlackBg = isNeed;
    }
}

