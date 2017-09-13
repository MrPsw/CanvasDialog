package com.example.mrpeng.dialogdemo;

import android.Manifest;
import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ZhifubaoActivity extends AppCompatActivity {

    private TextView mText;
    private int mTextWidth=0;
    private int mTextHeight=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyEt editText= (MyEt)findViewById(R.id.myet);


        mText= (TextView)findViewById(R.id.text);
        mText.post(new Runnable() {
            @Override
            public void run() {
             mTextWidth=  mText.getWidth();
             mTextHeight=  mText.getHeight();
                mText.setVisibility(View.GONE);
            }
        });
        editText .setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                KeyboardView dialogFragment = new KeyboardView(editText);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(dialogFragment, "UpdateAppFragment");
                transaction.commitAllowingStateLoss();//解决Can not perform this action after onSaveInstanceState with DialogFragment

                dialogFragment.setMonitorDismiss(new KeyboardView.MonitorDismiss() {
                    @Override
                    public void dismiss() {
                            endAnim();
                    }

                    @Override
                    public void show() {
                        startAnim();
                    }
                });



            }
        });


//        ViewAnimationUtils.createCircularReveal(dialogFragment,0,0,)

    }

   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
   public void startAnim(){

        //起始大小半径
        float startX=0f;
       // 结束半径    hypot(double ,double ) 表示斜线的长度
        Animator animator=ViewAnimationUtils.createCircularReveal(mText,0,0,startX, (float) Math.hypot(mTextWidth,mTextHeight));
       mText.setVisibility(View.VISIBLE);
        //在动画开始的地方速率改变比较慢,然后开始加速
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(300);
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void endAnim(){

        //起始大小半径
        float startX=0f;
        Animator animator=ViewAnimationUtils.createCircularReveal(mText,0,0,(float) Math.hypot(mTextWidth,mTextHeight),startX );
        //在动画开始的地方速率改变比较慢,然后开始加速
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(300);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }




}
