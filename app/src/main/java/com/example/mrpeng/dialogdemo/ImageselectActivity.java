package com.example.mrpeng.dialogdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ImageselectActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE = 101;
    private Button bt1;
    private ImageView IAMGE;

    boolean isCrop=false;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageselect);

        bt1=(Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ImageSelect();
                isCrop=false;
            }
        });
        bt2=(Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelect();
                isCrop=true;
            }
        });
    }

    public  void  ImageSelect(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Matisse.from(ImageselectActivity.this)
                                .choose(MimeType.allOf())
                                .countable(true)
                                .capture(true)  // 开启相机，和 captureStrategy 一并使用否则报错
                                .captureStrategy(new CaptureStrategy(true,"com.example.mrpeng.dialogdemo.fileprovider")) // 拍照的图片路径
                                .maxSelectable(1)
                                .gridExpectedSize( getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


    List<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
           IAMGE= (ImageView) findViewById(R.id.IAMGE);

                Glide.with(ImageselectActivity.this)
                        .load(mSelected.get(0))
                        .into(IAMGE);

            if(isCrop){
                Uri destination = Uri.fromFile(new File(getCacheDir(), "m"+System.currentTimeMillis()));
                System.out.println(destination);
                Crop.of(mSelected.get(0), destination).asSquare().start(ImageselectActivity.this);
            }


        }


            if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {



                Glide.with(ImageselectActivity.this)
                        .load(Crop.getOutput(data))
                        .into(IAMGE);

        }
    }
}
