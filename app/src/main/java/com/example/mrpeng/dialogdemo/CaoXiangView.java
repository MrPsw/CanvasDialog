package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/8/25.
 */

public class CaoXiangView extends View {
    private Bitmap bitmap;
    private Path clipPath;
    int Progress=0;
    private int width;


    /**
     * 创建对象
     * @param context
     */


    public CaoXiangView(Context context) {
        super(context);
    }

    public CaoXiangView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CaoXiangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);


       int w= width/2;

        Paint mPaint2 = new Paint();
        mPaint2.setDither(true);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(6);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.parseColor("#b71c1c"));

        clipPath=new Path();
        clipPath.addCircle(w,w,w, Path.Direction.CW);
        canvas.clipPath(clipPath);


        canvas.drawCircle(w,w,w,mPaint2);
        int progress=(width/100)*(100-Progress);
        Path path2 = new Path();
        path2.moveTo(0,progress);
        double d = Math.random();
            path2.cubicTo(width/4, (float) (progress-(20*d)),width/2, (float) (progress+(progress-(20*d))),width,progress);
            path2.lineTo(width,width);
            path2.lineTo(0,width);
            path2.close();
        mPaint2.setColor(Color.parseColor("#f44336"));
        //Path对象
        mPaint2.setStyle(Paint.Style.FILL);
        canvas.drawPath(path2,mPaint2);

    }

    public  void setProgress(int progress){
        Progress=progress;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureUtils.measureWidth(widthMeasureSpec);
    }

    public int getProgress() {
        return Progress;
    }
}
