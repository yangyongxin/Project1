package com.smartbejing.pager.news;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.smartbejing.R;

/**
 * Created by yangyongxin on 15/3/16.
 */
public class TopicDetailMenuPager extends BaseDetailPager {

    public TopicDetailMenuPager(Context context){
        super(context);
    }

    public View initView(){
        TextView text=new TextView(context);
        text.setText("菜单详情页--专题");
        text.setTextColor(context.getResources().getColor(R.color.bule));
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        return text;
    }

    public void initData(){

    }
}
