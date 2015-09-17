package com.smartbejing.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartbejing.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private static final int[] mImageIds={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private List<ImageView> mImageList;
    private LinearLayout llPointGuide;
    private Button btnStart;

    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        llPointGuide = (LinearLayout) findViewById(R.id.ll_point_guide);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                sp.edit().putBoolean("is_user_guide_showed",true).commit();

                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        initViews();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuideAdapter());

    }

    private void initViews(){
        mImageList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image=new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);
            mImageList.add(image);

            View point=new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.leftMargin=10;
            point.setLayoutParams(layoutParams);
            llPointGuide.addView(point);
        }


    }
    class GuideAdapter extends PagerAdapter implements OnPageChangeListener{

        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            llPointGuide.getChildAt(currentPage).setBackgroundResource(R.drawable.shape_point_gray);
            llPointGuide.getChildAt(position).setBackgroundResource(R.drawable.shape_point_red);
            if (position==mImageList.size()-1){
                btnStart.setVisibility(View.VISIBLE);
            }else {
                btnStart.setVisibility(View.INVISIBLE);
            }
            currentPage = position;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
