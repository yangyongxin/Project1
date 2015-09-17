package com.greentree.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greentree.R;
import com.greentree.application.MyApplication;
import com.greentree.bean.Hotel;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HotelAdapter<T> extends BaseAdapter {

    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;
    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public HotelAdapter(Context context,List<T> objects) {
        this.context = context;
        this.objects = objects;
        this.layoutInflater = LayoutInflater.from(context);
        MyApplication application = MyApplication.getApplication();
        imageLoader = application.getImageLoader();
        options = application.getOptions();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.act_hotel_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.ivImageUrl = (ImageView) convertView.findViewById(R.id.iv_imageUrl);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.ivWifi = (ImageView) convertView.findViewById(R.id.iv_wifi);
            viewHolder.ivPark = (ImageView) convertView.findViewById(R.id.iv_park);
            viewHolder.ivBreakfast = (ImageView) convertView.findViewById(R.id.iv_breakfast);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tvScore = (TextView) convertView.findViewById(R.id.tv_score);

            convertView.setTag(viewHolder);
        }
        initializeViews((T)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        Hotel.ResponseDataEntity.ItemsEntity hotel= (Hotel.ResponseDataEntity.ItemsEntity) object;
        holder.tvName.setText(hotel.getName());
        holder.tvPrice.setText(hotel.getPrice());
        holder.tvScore.setText(hotel.getScore());

        imageLoader.displayImage(hotel.getImageUrl(), holder.ivImageUrl, options);

        List<String> services = hotel.getService();
        holder.ivPark.setVisibility(View.GONE);
        holder.ivBreakfast.setVisibility(View.GONE);
        holder.ivWifi.setVisibility(View.GONE);
        for (int i = 0; i < services.size(); i++) {
            String s = services.get(i);
            if ("41".equals(s)) {
                holder.ivPark.setVisibility(View.VISIBLE);
            } else if ("51".equals(s)) {
                holder.ivBreakfast.setVisibility(View.VISIBLE);
            } else if ("91".equals(s)) {
                holder.ivWifi.setVisibility(View.VISIBLE);
            }
        }
    }

    protected class ViewHolder {
        private ImageView ivImageUrl;
    private TextView tvName;
    private ImageView ivWifi;
    private ImageView ivPark;
    private ImageView ivBreakfast;
    private TextView tvPrice;
    private TextView tvScore;
    }
}
