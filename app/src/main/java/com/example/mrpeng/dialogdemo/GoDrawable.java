package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/30.
 */

public class GoDrawable extends View {

    //线条高度
    int lineHeight=20;
    //线条宽度
    int lineWidth=100;
    //线条间隔
    int lineinterval=10;




    public GoDrawable(Context context) {
        super(context);
    }


    public GoDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GoDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.TRANSPARENT); //背景色
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#000000"));

//        Path path=new Path();
//        path.moveTo(10,20);
//        path.quadTo(0,10,10,0);
//        path.lineTo(60,0);
//        path.quadTo(70,10,60,20);
//        path.lineTo(10,20);
//        path.close();

        Path path=new Path();


        for(int i=0;i<3;i++){
            path.moveTo(lineHeight/2,lineHeight);
            path.quadTo(i*lineHeight,lineHeight/2,lineHeight/2,i*lineHeight);
            path.lineTo(lineWidth-(lineHeight/2),i*lineHeight);
            path.quadTo(lineWidth,lineHeight/2,lineWidth-(lineHeight/2),lineHeight);
            path.lineTo(lineHeight/2,lineHeight);
            path.close();
            canvas.drawPath(path,paint);
        }







    }
}
