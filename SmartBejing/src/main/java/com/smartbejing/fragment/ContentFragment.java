package com.smartbejing.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.smartbejing.R;
import com.smartbejing.pager.BasePager;
import com.smartbejing.pager.HomePager;
import com.smartbejing.pager.NewsPager;
import com.smartbejing.pager.ServicePager;
import com.smartbejing.pager.SettingPager;
import com.smartbejing.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyongxin on 15/9/10.
 */
public class ContentFragment extends BaseFragment {
    private RadioGroup rgGroup;

    private NoScrollViewPager vpContent;

    private List<BasePager> pagerList;

    @Override
    public View initViews() {
        View view = View.inflate(getActivity(), R.layout.fragment_content, null);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
        vpContent = (NoScrollViewPager) view.findViewById(R.id.vp_content);

        return view;
    }

    @Override
    public void initData() {
        pagerList = new ArrayList<BasePager>();
        pagerList.add(new HomePager(getActivity()));
        pagerList.add(new NewsPager(getActivity()));
        pagerList.add(new ServicePager(getActivity()));
        pagerList.add(new SettingPager(getActivity()));

        rgGroup.check(R.id.rb_home);
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0, false);
                        break;
                    case R.id.rb_news:
                        vpContent.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        vpContent.setCurrentItem(2, false);
                        break;
                    case R.id.rb_setting:
                        vpContent.setCurrentItem(3, false);
                        break;
                }
            }
        });

        vpContent.setAdapter(new ContentAdapter());
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                pagerList.get(i).initData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pagerList.get(0).initData();

    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view =pagerList.get(position).getView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public NewsPager getNewsPager(){
        return (NewsPager) pagerList.get(1);
    }
}
