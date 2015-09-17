package com.greentree.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.greentree.R;
import com.greentree.activity.HomeAcitvity;
import com.greentree.adapter.BannerAdapter;
import com.greentree.bean.Banner;
import com.greentree.utils.NetWorkUtil;
import com.greentree.utils.Urls;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @ViewInject(R.id.vp_banner)
    private ViewPager vpBanner;
    @ViewInject(R.id.ll_point)
    private LinearLayout llPoint;

    private List<Banner.ResponseDataEntity.BannerListEntity> bannerList=new ArrayList<Banner.ResponseDataEntity.BannerListEntity>();
    private BannerAdapter adapter;
    private List<ImageView> points;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home_page, null);
        ViewUtils.inject(this, view);
        initData();
        return view;

    }

    private void initData() {
        NetWorkUtil.doGetRequest(Urls.GET_BANNER, null,
                new NetWorkUtil.MyRequestCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        Gson gson = new Gson();
                        Banner banner = gson.fromJson(str, Banner.class);
                        List<Banner.ResponseDataEntity.BannerListEntity> bannerListEntities = banner.getResponseData().getBannerList();
                        bannerList.addAll(bannerListEntities);
                        addPoints();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(String str) {

                    }
                });
        adapter = new BannerAdapter(getActivity(),bannerList);
        vpBanner.setAdapter(adapter);
        vpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < points.size(); j++) {
                    points.get(j).setSelected(j == i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setCurrentContent(R.id.fl_home_page_select, new HomePageSelectFragment());
    }

    private void addPoints(){
        points=new ArrayList<ImageView>();
        ImageView point;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < bannerList.size(); i++) {
            point=new ImageView(getActivity());
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            point.setLayoutParams(params);
            point.setImageResource(R.drawable.selector_banner_point);
            point.setSelected(i == 0);
            points.add(point);
            llPoint.addView(point);
        }
    }


    @OnClick(R.id.iv_menu)
    public void onClick(View view) {
        HomeAcitvity acitvity = (HomeAcitvity) getActivity();
        acitvity.toggle();
    }


    @OnRadioGroupCheckedChange(R.id.rg_home_page)
    public void OnRadioGroupCheckedChange(RadioGroup group, int checkedId){
        switch (checkedId){
            case R.id.rb_hotelselet:
                setCurrentContent(R.id.fl_home_page_select, new HomePageSelectFragment());
                break;
        }
    }

    private void setCurrentContent(int containerId, Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId,fragment);
        transaction.commit();
    }

}
