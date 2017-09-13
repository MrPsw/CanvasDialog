package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


/**
 * 创建人：Mr.peng
 * 创建时间：2017/8/7.
 * 功能描述：
 * 语录：写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道
 */

public class MyEt extends EditText {
    private Paint mPaint;//边框画笔
    private int mHeight;
    private int mWidth;
    private int contentWidth;
    private int mager=4;
    private int radius=8;

    private int textWidth=4;

    public MyEt(Context context) {
        super(context);
        init();

    }

    public MyEt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init(){
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        setCursorVisible(false);
        setTextSize(0);
        setInputType(InputType.TYPE_NULL);


        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    //抬起的时候执行 如弹出自定义对话框

                }
                return false;
            }
        });

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        contentWidth=getWidth()/6;
        mPaint=new Paint();
        mPaint.setStrokeWidth(textWidth);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        // 边框
        RectF rect = new RectF(0+textWidth, 0+textWidth, getWidth()-textWidth, getHeight()-textWidth);
        mPaint.setColor(Color.GRAY);// 设置灰色
        mPaint.setStyle(Paint.Style.STROKE);//设置填满
        canvas.drawRoundRect(rect, radius,radius, mPaint);
        setBackgroundColor(Color.WHITE);


        for(int i=1;i<6;i++){
            mPaint.setColor(Color.GRAY);//设置灰色
            mPaint.setStyle(Paint.Style.STROKE);//设置填满
            mPaint.setStrokeWidth(textWidth);
            canvas.drawLine(contentWidth*i,0+textWidth,contentWidth*i,getHeight()-textWidth,mPaint);
        }

        if(getText().length()>0){
            for (int i = 0; i < getText().toString().length(); i++) {
                char mchar = getText().toString().charAt(i);
                Paint textPaint = new Paint();
                textPaint.setColor(Color.GRAY);
                textPaint.setTextSize(60);
                textPaint.setStyle(Paint.Style.FILL);
                mPaint.setTextAlign(Paint.Align.CENTER);

                Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
                // 转载请注明出处：http://blog.csdn.net/hursing
                int baseline = (int) ((rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2);
                // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
                textPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("●",((getWidth()/6)*i)+((getWidth()/6)/2), baseline, textPaint);

//                Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//                float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
//                float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
//                int baseLineY = (int) (rect.centerY() - top/2 - bottom/2);//基线中间点的y轴计算公式
//                canvas.drawText(String.valueOf(mchar),((getWidth()/6)*i)+((getWidth()/6)/2),baseLineY,textPaint);


            }

        }


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeight=this.getMeasuredHeight();
        mWidth=this.getMeasuredWidth();

    }


}
