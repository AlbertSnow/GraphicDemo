package com.albertsnow.graphicdemo.widget.scroll;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class FitWidthScaleImageView extends TouchScaleImageView {

    public FitWidthScaleImageView(Context context) {
        super(context);
    }

    public FitWidthScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FitWidthScaleImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void doInitScale(Drawable drawable) {
        float scale = 1.0f;
        int bmWidth = drawable.getIntrinsicWidth();
        int bmHeight = drawable.getIntrinsicHeight();
        float scaleX = (float) viewWidth / (float) bmWidth;
        float scaleY = (float) viewHeight / (float) bmHeight;

        scale = scaleX;
        mImgMatrix.setScale(scale, scale);

        if (scaleY >= scaleX) {
            // picture y small than x. scale x, cause y beyond screen.
            // so picture from begin not center layout.
            float redundantYSpace = (viewHeight - (bmHeight * scale)) * 0.5f;
            mImgMatrix.postTranslate(0, redundantYSpace);
        }

        origWidth = scale * bmWidth;
        origHeight = scale * bmHeight;
        setImageMatrix(mImgMatrix);
    }

}

