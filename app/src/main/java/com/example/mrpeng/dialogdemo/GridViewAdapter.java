package com.example.mrpeng.dialogdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
        List<String> stringList;
        Context  mContext;
       public GridViewAdapter(List<String> list,Context context) {
           this.stringList=list;
           this.mContext=context;
       }

       @Override
       public int getCount() {
           return stringList.size();
       }

       @Override
       public Object getItem(int position) {

           return stringList.get(position) ;
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
       @Override
       public View getView(int position, View convertView, ViewGroup parent) {


           LinearLayout viewgroup = new LinearLayout(mContext);

//           ViewGroup.LayoutParams ViewGrouplp = viewgroup.getLayoutParams();
//           ViewGrouplp.width= LinearLayout.LayoutParams.MATCH_PARENT;
//           ViewGrouplp.height= ViewGroup.LayoutParams.WRAP_CONTENT;
//           viewgroup.setLayoutParams(ViewGrouplp);
           TextView textview = new TextView(mContext);


           WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
           int width = wm.getDefaultDisplay().getWidth();
           int height = wm.getDefaultDisplay().getHeight();
           textview.setHeight(150);
           textview.setWidth(width/3);
           textview.setText(stringList.get(position));
           textview.setGravity(Gravity.CENTER);
           textview.setBackgroundColor(Color.parseColor("#ffffff"));
//            viewgroup.addView(textview);
    textview.setBackground(mContext.getResources().getDrawable(R.drawable.select));





           return textview;
       }
   }