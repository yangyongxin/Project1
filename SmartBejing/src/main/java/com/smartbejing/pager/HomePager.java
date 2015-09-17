package com.smartbejing.pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.smartbejing.R;

/**
 * Created by yangyongxin on 15/3/15.
 */
public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        btnMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(false);

        tvTitle.setText("智慧北京");

        TextView text=new TextView(context);
        text.setText("首页");
        text.setTextColor(context.getResources().getColor(R.color.bule));
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        flContent.addView(text);
    }
}
