package com.smartbejing.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.smartbejing.activity.MainActivity;
import com.smartbejing.bean.News;
import com.smartbejing.fragment.LeftMenuFragment;
import com.smartbejing.global.GlobalContants;
import com.smartbejing.pager.news.BaseDetailPager;
import com.smartbejing.pager.news.InteractDetailMenuPager;
import com.smartbejing.pager.news.NewsDetailMenuPager;
import com.smartbejing.pager.news.PhotoDetailMenuPager;
import com.smartbejing.pager.news.TopicDetailMenuPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyongxin on 15/3/15.
 */
public class NewsPager extends BasePager {

    private static final String TAG = "NewsPager";
    private List<BaseDetailPager> pagerList;
    private News data;

    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        btnMenu.setVisibility(View.VISIBLE);
        setSlidingMenuEnable(true);

        getDataFromServer();
    }

    public void getDataFromServer() {

             HttpUtils httpUtils = new HttpUtils("utf-8");
            httpUtils.send(HttpRequest.HttpMethod.GET, GlobalContants.CATEGORIES_URL,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo responseInfo) {
                            String result = (String) responseInfo.result;
                            Log.i(TAG, "onSuccess ============" + result);
                            parseData(result);
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            Log.i(TAG, "onFailure --------------" + msg);
                        }

                    });
    }


    private void parseData(String result) {
        Gson gson = new Gson();
        data = gson.fromJson(result, News.class);

        MainActivity mainActivity = (MainActivity) context;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        leftMenuFragment.setMenuData(data);

        pagerList = new ArrayList<BaseDetailPager>();
        pagerList.add(new NewsDetailMenuPager(context, data.getNewsMenuData().get(0).getNewsTabData()));
        pagerList.add(new TopicDetailMenuPager(context));
        pagerList.add(new PhotoDetailMenuPager(context));
        pagerList.add(new InteractDetailMenuPager(context));

        setCurrentDetailMenuPager(0);


    }

    public void setCurrentDetailMenuPager(int position) {
        BaseDetailPager pager = pagerList.get(position);
        flContent.removeAllViews();
        flContent.addView(pager.getView());

        tvTitle.setText(data.getNewsMenuData().get(position).getTitle());
        pager.initData();
    }

}
