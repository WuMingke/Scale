package com.erkuai.scale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/10/9.
 */

public class DoodleView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener {

    private float basePointX;
    private float basePointY;

    private Paint mPaint;
    private Path mPath;
    private Bitmap bitmap;
    private int width,height;
    private List<Path> paths = new ArrayList<>();

    private ScaleGestureDetector gestureDetector;
    private boolean hasScaled;
    private float initScale;
    private float mScale;
    private float maxScale;
    private Matrix matrix;

    public DoodleView(Context context) {
        super(context);
        init();
    }

    public DoodleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();

        matrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        gestureDetector = new ScaleGestureDetector(getContext(),this);
       // setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath,mPaint);
       // paths.add(mPath);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount()==1){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    //mPath.reset();

                    basePointX = event.getX();
                    basePointY = event.getY();

                    mPath.moveTo(basePointX,basePointY);
                    break;
                case MotionEvent.ACTION_MOVE:

                    float x = event.getX();
                    float y = event.getY();

                    float absX = Math.abs(x - basePointX);
                    float absY = Math.abs(y - basePointY);

                    if (absX>3||absY>3){
                        float cX = (x + basePointX) / 2;
                        float cY = (y + basePointY) / 2;

                        mPath.quadTo(basePointX,basePointY,cX,cY);

                        basePointX = x;
                        basePointY = y;
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    if (null!=bitmap){//频繁GC 有问题
                        bitmap.recycle();
                        System.gc();
                    }else {
                        bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
                        bitmap.eraseColor(Color.WHITE);
                    }
                    break;
            }
            postInvalidate();

        }else {
            return gestureDetector.onTouchEvent(event);
        }

        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
/*        float[] floats = new float[9];
        matrix.getValues(floats);
        float scale = floats[Matrix.MSCALE_X];
        float scaleFactor = detector.getScaleFactor();*/
        float scaleFactor = detector.getScaleFactor();
        matrix.postScale(scaleFactor,scaleFactor,detector.getFocusX(),detector.getFocusY());
        setImageMatrix(matrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }
}
