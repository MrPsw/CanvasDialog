package com.example.mrpeng.dialogdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt1;
    private Button bt2;
    private Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bt1=(Button) findViewById(R.id.bt1);
         bt2=(Button) findViewById(R.id.bt2);
        bt3=(Button) findViewById(R.id.bt3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                startWindow(new Intent(firstActivity.this,ZhifubaoActivity.class));
                break;
            case R.id.bt2:
                startWindow(new Intent(firstActivity.this,ViewActivity.class));
                break;
            case R.id.bt3:
                startWindow(new Intent(firstActivity.this,ImageselectActivity.class));
                break;
        }

    }

    private  void startWindow(Intent intent){
        startActivity(intent);
    }

}
