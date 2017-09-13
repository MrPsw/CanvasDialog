package com.example.mrpeng.dialogdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class KeyboardView extends DialogFragment {
    private  EditText mEditText;
    private View mContentView;
    private GridView gridview;

    //监听输入法关闭
    private MonitorDismiss mMonitorDismiss;

    public KeyboardView(){

    }
    public KeyboardView(EditText editText) {
        this.mEditText=editText;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        getDialog().getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.TranslucentNoTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView=inflater.inflate(R.layout.keyboard_layout,null);
        gridview=(MyGridView) mContentView.findViewById(R.id.key);
         List<String> keylist=new ArrayList<>();
        for (int i=1;i<13;i++){
            if(i>=10){
                if(i==11){
                    keylist.add("0");
                }else if(i==12){
                    keylist.add("完成");
                }else{
                    keylist.add("删除");
                }

            }else{
                keylist.add(i+"");
            }

            if(mMonitorDismiss!=null){
                mMonitorDismiss.show();
            }
        }
        gridview.setAdapter(new GridViewAdapter(keylist,getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=9&&position!=11&&position!=10){
                    mEditText.append((position+1)+"");
                }else{
                    if(position==10){
                        mEditText.append("0");
                    }
                    if(position==9){
                        String str = mEditText.getText().toString();
                        if (str!= null && !str.equals("")){
                            String string = new String();
                            if (str.length() > 1){
                                string =   str.substring(0, str.length() - 1);
                            }else {
                                string =  "";
                            }
                            mEditText.setText(string);}
                    }
                    if(position==11){
                        dismiss();
                        Toast.makeText(getActivity(), ""+mEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.windowAnimations=R.style.animate_dialog;
        window.setAttributes(lp);
        return mContentView;
    }
    public void Colse(){
        this.dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(mMonitorDismiss!=null){
            mMonitorDismiss.dismiss();
        }

    }

    interface  MonitorDismiss{
       void dismiss();
        void show();
    }

   public void  setMonitorDismiss(MonitorDismiss mMonitorDismiss){
        this.mMonitorDismiss=mMonitorDismiss;
    }
}
