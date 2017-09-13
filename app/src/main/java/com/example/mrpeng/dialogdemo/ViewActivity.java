package com.example.mrpeng.dialogdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewActivity extends AppCompatActivity {

    private CaoXiangView caox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        caox=(CaoXiangView) findViewById(R.id.caox);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
    }
   int  progress=0;
    private static final int MSG_PROGRESS_UPDATE = 0x110;
    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            caox.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);

            }
            mHandler.sendEmptyMessageDelayed(MSG_PROGRESS_UPDATE, 100);
        };
    };
}
