package com.smartbejing.pager.news;

import android.content.Context;
import android.view.View;

/**
 * Created by yangyongxin on 15/3/16.
 */
public abstract class BaseDetailPager {

    protected   Context context;
    private   View view;

    public View getView() {
        return view;
    }

    public BaseDetailPager(Context context){
        this.context=context;
        view = initView();
    }

    public abstract View initView();

    public void initData(){

    }
}
