package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/8/25.
 */

public class BaguaView extends View {
    private Bitmap bitmap;
    private Path clipPath;
    int Progress=0;
    private int width;


    /**
     * 创建对象
     * @param context
     */


    public BaguaView(Context context) {
        super(context);
    }

    public BaguaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaguaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);


       int w= width/2;

        Paint mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(6);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.parseColor("#000000"));

        /**
         * 裁剪
         */
        clipPath=new Path();
        clipPath.addCircle(w,w,w, Path.Direction.CW);
        canvas.clipPath(clipPath);
        /**
         * 圆边
         */
        canvas.drawCircle(w,w,w,mPaint2);

        /**
         * 基础矩形
         */
        Path path2 = new Path();
        float tw=width/4;
            path2.moveTo(0,width/2);
            path2.lineTo(width,width/2);
            path2.lineTo(width,width);
            path2.lineTo(0,width);
            path2.close();
        mPaint2.setColor(Color.parseColor("#000000"));
        //Path对象
        mPaint2.setStyle(Paint.Style.FILL);
        canvas.drawPath(path2,mPaint2);


        path2.reset();
        path2.addCircle(tw,width/2,tw, Path.Direction.CW);
        canvas.drawPath(path2,mPaint2);

        path2.reset();
        path2.addCircle(tw*3,width/2,tw, Path.Direction.CW);
        mPaint2.setColor(Color.WHITE);
        canvas.drawPath(path2,mPaint2);


        path2.reset();
        path2.addCircle(tw,width/2,30, Path.Direction.CW);
        mPaint2.setColor(Color.WHITE);
        canvas.drawPath(path2,mPaint2);

        path2.reset();
        path2.addCircle(tw*3,width/2,30, Path.Direction.CW);
        mPaint2.setColor(Color.parseColor("#000000"));
        canvas.drawPath(path2,mPaint2);

    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureUtils.measureWidth(widthMeasureSpec);
    }

}
