package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * 创建人：Mr.peng
 * 创建时间：2017/8/7.
 * 功能描述：
 * 语录：写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道
 */

public class MyScrollView extends ScrollView{
   ViewGroup mHeadView;//头部
   float EstartY=-1;//按下的位置
    float EcurrentY=-1;//当前Y
    float EalterantY=-1;//改变的Y
    private float mProportion;//改变后的比例
    private int mInitializeWidth;//初始宽度
    private int mInitializeHeight;//初始高度


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override//布局完成
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            if(getChildAt(0)!=null&&getChildAt(0)instanceof ViewGroup){
                mHeadView= (ViewGroup) ((ViewGroup) getChildAt(0)).getChildAt(0);
                mInitializeWidth=mHeadView.getMeasuredWidth();
                mInitializeHeight=mHeadView.getMeasuredHeight();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            //按下
            case MotionEvent.ACTION_DOWN:
                EstartY=ev.getY();
                break;
            //拖动
            case MotionEvent.ACTION_MOVE:
                if(getScrollY()==0){
                    EcurrentY=ev.getY();
                    if(EstartY>=0&&EcurrentY>=0){
                        if(EcurrentY-EstartY>=0){
                            EalterantY= EcurrentY-EstartY;
                        }
                    }
                    startAnimation();
                }

                break;
            //抬起
            case MotionEvent.ACTION_UP:
               endAnimation();
                break;
        }
        return super.onTouchEvent(ev);
    }

   private void startAnimation(){

        if(mHeadView!=null&&EalterantY>-1){
            ViewGroup.LayoutParams lp = mHeadView.getLayoutParams();
            if(mInitializeHeight<(mInitializeHeight+EalterantY)){
                mProportion=(mInitializeHeight+EalterantY)/mInitializeHeight;

                lp.height= (int) (mInitializeHeight+EalterantY);
                lp.width= (int) (mInitializeWidth*mProportion);
                ((MarginLayoutParams) lp).setMargins(-(lp.width -mInitializeWidth ) / 2, 0, 0, 0);
                mHeadView.setLayoutParams(lp);
            }

        }
       System.out.println(mInitializeHeight+"mInitializeHeight");
       System.out.println(mInitializeWidth+"mInitializeWidth");
       System.out.println(EalterantY+"EalterantY");
       System.out.println(EcurrentY+"EcurrentY");
       System.out.println(EstartY+"EstartY");
       System.out.println(mProportion+"mProportion");
    }

    private void endAnimation(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(EalterantY>0){
                    EalterantY= EalterantY-40;
                    postDelayed(this,10);
                    startAnimation();
                }else{
                    EalterantY=0;

                }


            }
        },10);

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mInitializeHeight = mHeadView.getMeasuredHeight();
        mInitializeWidth = mHeadView.getMeasuredWidth();

    }
}
