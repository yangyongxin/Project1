package com.greentree.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.greentree.R;
import com.greentree.adapter.HotelAdapter;
import com.greentree.bean.Hotel;
import com.greentree.utils.NetWorkUtil;
import com.greentree.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_hotel_select)
public class HotelSelectActivity extends Activity {

    @ViewInject(R.id.lv_refresh_hotel)
    private PullToRefreshListView refreshListView;
    @ViewInject(R.id.rb_price)
    private RadioButton rb_price;
    private HotelAdapter adapter;

    private List<Hotel.ResponseDataEntity.ItemsEntity> hotelList = new ArrayList<Hotel.ResponseDataEntity.ItemsEntity>();

    private int pagesize = 10;
    private int pageindex = 1;
    private int ordertype = 0;
    private boolean ifRefreshing = false;
    private boolean isPiceDec = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initView();
        initData();
    }

    private void initView() {
        refreshListView.getRefreshableView().setDivider(null);
        refreshListView.getRefreshableView().setSelector(android.R.color.transparent);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!ifRefreshing) {
                    pageindex = 1;
                    hotelList.clear();
                    ifRefreshing = true;
                    loadData();
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!ifRefreshing) {
                    pageindex++;
                    ifRefreshing = true;
                    loadData();
                }
            }
        });

    }

    private void initData() {
        loadData();
        adapter = new HotelAdapter(this, hotelList);
        refreshListView.setAdapter(adapter);
    }

    public void loadData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pagesize", pagesize + "");
        params.put("pageindex", pageindex + "");
        params.put("ordertype", ordertype + "");
        NetWorkUtil.doPostRequest(Urls.SEARCH_HOTEL, params,
                new NetWorkUtil.MyRequestCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        Gson gson = new Gson();
                        Hotel hotel = gson.fromJson(str, Hotel.class);
                        List<Hotel.ResponseDataEntity.ItemsEntity> items = hotel.getResponseData().getItems();
                        hotelList.addAll(items);
                        adapter.notifyDataSetChanged();
                        ifRefreshing=false;
                        refreshListView.onRefreshComplete();
                    }

                    @Override
                    public void onFailure(String str) {
                        pageindex--;
                        ifRefreshing=false;
                        refreshListView.onRefreshComplete();
                    }
                });
    }

    @OnRadioGroupCheckedChange(R.id.rg)
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_recommend:
                ordertype = 0;
                break;
            case R.id.rb_distance:
                ordertype = 1;
                break;
            case R.id.rb_price:
                ordertype = isPiceDec ? 2 : 3;
                isPiceDec = !isPiceDec;
                break;
            case R.id.rb_score:
                ordertype = 4;
                break;
            case R.id.rb_filter:
                //TODO
                break;
        }
        if (checkedId != R.id.rb_filter) {
            pageindex = 1;
            hotelList.clear();
            loadData();
        }
        if (checkedId != R.id.rb_price) {
            rb_price.setText(isPiceDec ? R.string.price_s : R.string.price_x);
        }
    }
}
