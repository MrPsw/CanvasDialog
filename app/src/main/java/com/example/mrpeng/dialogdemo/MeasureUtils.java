package com.example.mrpeng.dialogdemo;

import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MeasureUtils {

    //根据xml的设定获取高度
    public static int measureHeight(int measureSpec) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        //wrap_content
        if (specMode == View.MeasureSpec.AT_MOST){

        }
        //fill_parent或者精确值
        else if (specMode == View.MeasureSpec.EXACTLY){

        }
        Log.i("这个控件的高度----------","specMode:"+specMode+" specSize:"+specSize);

        return specSize;
    }
    //根据xml的设定获取宽度
    public static int measureWidth(int measureSpec) {
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        //wrap_content
        if (specMode == View.MeasureSpec.AT_MOST){

        }
        //fill_parent或者精确值
        else if (specMode == View.MeasureSpec.EXACTLY){

        }
        Log.i("这个控件的宽度----------","specMode="+specMode+" specSize="+specSize);

        return specSize;
    }
}
