package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/1.
 */


public class YHView extends View {
    public YHView(Context context) {
        super(context);
        init();
    }


    public YHView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public YHView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

        RectF recf=new RectF(0,0, 100, 100);
        canvas.drawArc(recf,180,60,false,paint);




    }

    private void init() {

    }

}
