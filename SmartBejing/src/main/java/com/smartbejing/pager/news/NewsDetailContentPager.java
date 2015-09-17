package com.smartbejing.pager.news;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.smartbejing.R;
import com.smartbejing.activity.MainActivity;
import com.smartbejing.bean.News;
import com.smartbejing.bean.NewsDetail;
import com.smartbejing.global.GlobalContants;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

/**
 * Created by yangyongxin on 15/3/16.
 */
public class NewsDetailContentPager extends BaseDetailPager {
    private static final String TAG = "NewsDetailContentPager";
    private News.NewsMenuData.NewsTabData newsTabData;
    private TextView text;
    private String url;
    private NewsDetail data;
    private List<NewsDetail.DataEntity.TopnewsEntity> topnewsList;
    private ViewPager vpNews;

    public NewsDetailContentPager(Context context, News.NewsMenuData.NewsTabData newsTabData) {
        super(context);
        this.newsTabData = newsTabData;
        url = GlobalContants.SERVER_URL + newsTabData.getUrl();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.news_detail_content, null);
        vpNews = (ViewPager) view.findViewById(R.id.vp_news);

        TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        vpNews.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                MainActivity mainActivity= (MainActivity) context;
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                if (i==0){
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else {
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    @Override
    public void initData() {
        getDataFromServer();


    }

    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils("utf-8");
        httpUtils.send(HttpRequest.HttpMethod.GET, url,
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

                }

        );
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        data = gson.fromJson(result, NewsDetail.class);
        topnewsList = data.getData().getTopnews();
        vpNews.setAdapter(new TopNewsAdapter());
    }

    class TopNewsAdapter extends PagerAdapter {

        private final BitmapUtils bitmapUtils;

        public TopNewsAdapter() {
            bitmapUtils = new BitmapUtils(context);
            bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.topnews_item_default);
        }

        @Override
        public int getCount() {
            return topnewsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            String commenturl = topnewsList.get(position).getTopimage();
            bitmapUtils.display(imageView,commenturl.replace("http://10.0.2.2:8080/zhbj",GlobalContants.SERVER_URL));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
