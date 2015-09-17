package com.greentree.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greentree.application.MyApplication;
import com.greentree.bean.Banner;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by yangyongxin on 15/9/16.
 */
public class BannerAdapter extends PagerAdapter {
    private List<Banner.ResponseDataEntity.BannerListEntity> data;
    private Context context;
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;

    public BannerAdapter(Context context, List<Banner.ResponseDataEntity.BannerListEntity> data) {
        this.data = data;
        this.context = context;
        MyApplication application = MyApplication.getApplication();
        imageLoader = application.getImageLoader();
        options = application.getOptions();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        String bannerUrl = data.get(position).getBannerUrl();
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(bannerUrl, imageView, options);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}