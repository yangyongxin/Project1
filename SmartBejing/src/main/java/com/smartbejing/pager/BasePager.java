package com.smartbejing.pager;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.smartbejing.R;
import com.smartbejing.activity.MainActivity;

/**
 * Created by yangyongxin on 15/3/15.
 */
public class BasePager {

    protected Context context;
    protected View view;
    protected TextView tvTitle;
    protected FrameLayout flContent;
    protected ImageButton btnMenu;

    public View getView() {
        return view;
    }

    public BasePager(Context context){
        this.context=context;
        initView();
    }

    public void initView(){
        view = View.inflate(context, R.layout.base_content_pager, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }

    public void initData(){

    }

    protected void setSlidingMenuEnable(boolean enable){
        MainActivity mainActivity = (MainActivity) context;
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    protected void toggleSlidingMenu() {
        MainActivity mainActivity = (MainActivity) context;
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        slidingMenu.toggle();
    }
}
