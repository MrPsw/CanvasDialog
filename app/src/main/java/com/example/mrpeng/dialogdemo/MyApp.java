package com.example.mrpeng.dialogdemo;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        //初始化PatchManager
//        PatchManager patchManager=new PatchManager(this);
//        //初始化补丁版本号
//        patchManager.init("1.0.1");
//        patchManager.loadPatch();
//
//        String paht="/data/data/包名";
//        try {
//            patchManager.addPatch(paht);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
