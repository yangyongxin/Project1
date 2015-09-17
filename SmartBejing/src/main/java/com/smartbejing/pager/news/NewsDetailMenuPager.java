package com.smartbejing.pager.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.smartbejing.R;
import com.smartbejing.bean.News;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyongxin on 15/3/16.
 */
public class NewsDetailMenuPager extends BaseDetailPager {

    private ArrayList<NewsDetailContentPager> pagerList;
    private List<News.NewsMenuData.NewsTabData> newsTabData;
    private ViewPager vpMenuDetail;
    private TabPageIndicator indicator;

    public NewsDetailMenuPager(Context context, List<News.NewsMenuData.NewsTabData> newsTabData) {
        super(context);
        this.newsTabData=newsTabData;
    }

    public View initView() {
        View view = View.inflate(context, R.layout.news_detail_menu, null);
        vpMenuDetail = (ViewPager) view.findViewById(R.id.vp_menu_detail);

        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);

        ImageButton btnNext = (ImageButton) view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = vpMenuDetail.getCurrentItem();
                vpMenuDetail.setCurrentItem(++currentItem);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        pagerList = new ArrayList<NewsDetailContentPager>();
        for (int i = 0; i < newsTabData.size(); i++) {
            NewsDetailContentPager pager = new NewsDetailContentPager(context,newsTabData.get(i));
            pagerList.add(pager);
        }
        vpMenuDetail.setAdapter(new MenuDetailAdapter());
        indicator.setViewPager(vpMenuDetail);

    }

    class MenuDetailAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return  newsTabData.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsDetailContentPager pager = pagerList.get(position);
            container.addView(pager.getView());
            pager.initData();
            return pager.getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
