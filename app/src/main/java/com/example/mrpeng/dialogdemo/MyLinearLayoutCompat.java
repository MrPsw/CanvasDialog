package com.example.mrpeng.dialogdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MyLinearLayoutCompat extends LinearLayout {


    private  int mDividerPadding;
    private Drawable mDivider;
    private int mDividerWidth;
    private int mDividerHeight;
    private int mDividerTop=0;
    private int mHeight=0;
    private int mWidth=0;
    private boolean IsDraw=false;


    public MyLinearLayoutCompat(Context context) {
        super(context);
    }

    public MyLinearLayoutCompat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.MyLinearLayoutCompat);
        setDividerDrawable(a.getDrawable(R.styleable.MyLinearLayoutCompat_divider));
        mDividerPadding = a.getDimensionPixelSize(R.styleable.MyLinearLayoutCompat_dividerPadding, 0);
        a.recycle();
        System.out.println("初始化控件 进入构造");

        setWillNotDraw(false);
    }

    public MyLinearLayoutCompat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(getOrientation()==VERTICAL){
            System.out.println("绘制开始");
            if(!IsDraw){
                DrawDivider(canvas);
                IsDraw=true;
            }

        }

        super.onDraw(canvas);
    }

    private void DrawDivider(Canvas canvas) {

            for (int i = 0 ; i < getChildCount() ; i++) {
                MyLayoutParams childLayoutParams = (MyLayoutParams) getChildAt(i).getLayoutParams();
                System.out.println("测量高度DIVIDER1: "+childLayoutParams.mShowDividers+"-"+mDivider+""+childLayoutParams.divider);
                if (childLayoutParams.mShowDividers == 1 && mDivider != null || childLayoutParams.divider != null) {
//                    drawDivider(canvas, mHeight);
                    Paint paint=new Paint();
                    paint.setColor(Color.parseColor("#9c9c9c"));
                    paint.setAntiAlias(true);
                    paint.setStrokeWidth(1);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawLine(0,mHeight,800,mHeight,paint);
                    canvas.save();

                    System.out.println("绘制高度: "+mHeight);
                    if (mDivider != null) {
                        System.out.println("测量高度DIVIDER1: "+mDivider.getIntrinsicHeight());
                        mHeight = mHeight + getChildAt(i).getMeasuredHeight() + mDivider.getIntrinsicHeight();
                    } else {
                        System.out.println("测量高度DIVIDER2: "+childLayoutParams.divider.getIntrinsicHeight());
                        mHeight = mHeight + getChildAt(i).getMeasuredHeight() + childLayoutParams.divider.getIntrinsicHeight();
                    }
                } else {
                    mHeight = mHeight + getChildAt(i).getMeasuredHeight();
                }

                System.out.println("获取高度: "+mHeight);
            }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height=0;
        for (int i = 0 ; i < getChildCount() ; i++) {
            MyLayoutParams childLayoutParams = (MyLayoutParams) getChildAt(i).getLayoutParams();

            if (childLayoutParams.mShowDividers == 1 && mDivider != null || childLayoutParams.divider != null) {

                if (mDivider != null) {
                    height = height + getChildAt(i).getMeasuredHeight() + mDivider.getIntrinsicHeight();
                } else {

                    height = height + getChildAt(i).getMeasuredHeight() + childLayoutParams.divider.getIntrinsicHeight();
                }
            } else {
                height = height + getChildAt(i).getMeasuredHeight();
            }

            System.out.println("测量高度: "+mHeight);
        }

        setMeasuredDimension(800,height);
        System.out.println(800+"--"+height);
    }



    public void setDividerDrawable(Drawable divider) {
        if (divider == mDivider) {
            return;
        }
        mDivider = divider;
        if (divider != null) {
            mDividerWidth = divider.getIntrinsicWidth();
            mDividerHeight = divider.getIntrinsicHeight();
        } else {
            mDividerWidth = 0;
            mDividerHeight = 0;
        }

        requestLayout();
    }




    public   void drawDivider(Canvas canvas, int top) {
            mDivider.setBounds(0, top,
                   800, top + 1);
            mDivider.draw(canvas);
        System.out.println("绘制");

        }



    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(),attrs);
    }





    public static class MyLayoutParams extends LinearLayout.LayoutParams {

        private  Drawable divider;
        private  int mShowDividers;


        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            //子View属性获取
            TypedArray a =  c.obtainStyledAttributes(attrs,R.styleable.MyLinearLayoutCompat);
            mShowDividers = a.getInt(R.styleable.MyLinearLayoutCompat_child_visibility,1);
            divider= a.getDrawable(R.styleable.MyLinearLayoutCompat_divider);
            a.recycle();

        }

    }
}
