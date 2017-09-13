package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/8/25.
 */

public class BiaoqingView extends View {
    private Bitmap bitmap;
    private Path clipPath;
    int Progress=0;
    private int width;


    /**
     * 创建对象
     * @param context
     */


    public BiaoqingView(Context context) {
        super(context);
    }

    public BiaoqingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BiaoqingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setStrokeWidth(1);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.parseColor("#ffea00"));

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
         * 眼睛
         */

        float tw=width/4;
        RectF rect=new RectF(tw-20,tw,tw+20,tw+60);
        mPaint2.setColor(Color.parseColor("#000000"));
        mPaint2.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rect,mPaint2);


        /**
         * 眼睛
         */
        RectF rect2=new RectF((tw*3)-20,tw,(tw*3)+20,tw+60);
        mPaint2.setColor(Color.parseColor("#000000"));
        mPaint2.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rect2,mPaint2);

        Path path=new Path();
        path.moveTo(width/2-10,tw*3);
        path.lineTo(tw*3,tw*3-10);
        path.cubicTo(width/2-10,tw*3,width/2,tw*3,tw*3,tw*3-10);
        canvas.drawPath(path,mPaint2);



    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureUtils.measureWidth(widthMeasureSpec);
    }

}
