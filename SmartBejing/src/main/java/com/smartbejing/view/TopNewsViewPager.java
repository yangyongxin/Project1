package com.smartbejing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yangyongxin on 15/3/17.
 */
public class TopNewsViewPager extends ViewPager {

    public TopNewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopNewsViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

}
