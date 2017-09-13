package com.example.mrpeng.dialogdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/30.
 */

public class App  extends Application{
   public static Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this.getApplicationContext();
    }
}
