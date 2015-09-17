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
import com.greentree.bean.SearchHotel;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ItemAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemAdapter(Context context, List<T> objects) {
        this.context = context;
        this.objects = objects;
        this.layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.item, null);
            ViewHolder viewHolder = new ViewHolder();

            ViewUtils.inject(viewHolder,convertView);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        SearchHotel.ResponseDataEntity.ItemsEntity item = (SearchHotel.ResponseDataEntity.ItemsEntity) object;
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.tvScore.setText(item.getScore());

        BitmapUtils bitmapUtils = MyApplication.getApplication().getBitmapUtils();
        bitmapUtils.display(holder.ivImageUrl, item.getImageUrl());

        List<String> services = item.getService();
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

    protected static class ViewHolder {
        @ViewInject(R.id.iv_imageUrl)
        private ImageView ivImageUrl;
        @ViewInject(R.id.tv_name)
        private TextView tvName;
        @ViewInject(R.id.iv_wifi)
        private ImageView ivWifi;
        @ViewInject(R.id.iv_park)
        private ImageView ivPark;
        @ViewInject(R.id.iv_breakfast)
        private ImageView ivBreakfast;
        @ViewInject(R.id.tv_price)
        private TextView tvPrice;
        @ViewInject(R.id.tv_score)
        private TextView tvScore;
    }
}
