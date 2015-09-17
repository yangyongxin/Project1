package com.greentree.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.greentree.R;
import com.greentree.adapter.ItemAdapter;
import com.greentree.application.MyApplication;
import com.greentree.bean.SearchHotel;
import com.greentree.bean.SearchHotelCache;
import com.greentree.view.RefreshListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String URL = "";

    @ViewInject(R.id.lv)
    private RefreshListView refreshListView;
    @ViewInject(R.id.ll_pb)
    private LinearLayout ll_pb;
    @ViewInject(R.id.rb_price)
    private RadioButton rb_price;
    private ItemAdapter adapter;

    private List<SearchHotel.ResponseDataEntity.ItemsEntity> data = new ArrayList<SearchHotel.ResponseDataEntity.ItemsEntity>();

    private int pagesize = 10;
    private int pageindex = 1;
    private int ordertype = 0;
    private boolean isPiceDec = true;
    private HttpUtils httpUtils;
    private DbUtils dbUtils;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Gson gson = new Gson();
                    SearchHotel searchHotel = gson.fromJson((String) msg.obj, SearchHotel.class);
                    List<SearchHotel.ResponseDataEntity.ItemsEntity> items = searchHotel.getResponseData().getItems();
                    data.addAll(items);

                    ll_pb.setVisibility(View.GONE);

                    adapter.notifyDataSetChanged();
                    refreshListView.completeRefresh();
                    break;

                case 1:
                    adapter.notifyDataSetChanged();
                    refreshListView.completeRefresh();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        httpUtils = MyApplication.getApplication().getHttpUtils();
        dbUtils = MyApplication.getApplication().getDbUtils();
        initData();
        initListener();
    }

    private void initData() {
        adapter = new ItemAdapter(MainActivity.this, data);
        refreshListView.setAdapter(adapter);

        ll_pb.setVisibility(View.VISIBLE);
        loadData();
    }

    private void initListener() {
        refreshListView.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onPullRefresh() {
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onLoadingMore() {
                pageindex++;
                loadData();
            }
        });
    }

    private void againLoadData() {
        pageindex = 1;
        data.clear();

        ll_pb.setVisibility(View.VISIBLE);
        loadData();
    }

    public void loadData() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("pagesize", pagesize + "");
        params.addBodyParameter("pageindex", pageindex + "");
        params.addBodyParameter("ordertype", ordertype + "");

        //SearchHotelCache shc = findSearchHotelCache();
        SearchHotelCache shc = null;

        if (shc == null || shc.getResponse() == null) {
            httpUtils.send(
                    HttpRequest.HttpMethod.POST, URL, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String response = responseInfo.result;

                            addSearchHotelCache(response);

                            Message message = handler.obtainMessage();
                            message.what = 0;
                            message.obj = response;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Log.i(TAG, "onFailure ");
                        }
                    });
        } else {
            String response = shc.getResponse();
            Message message = handler.obtainMessage();
            message.what = 0;
            message.obj = response;
            handler.sendMessage(message);
        }

    }

    private void addSearchHotelCache(String response) {
        try {
            SearchHotelCache shc = new SearchHotelCache();
            shc.setPagesize(pagesize);
            shc.setPageindex(pageindex);
            shc.setOrdertype(ordertype);
            shc.setResponse(response);
            dbUtils.save(shc);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    private SearchHotelCache findSearchHotelCache() {
        SearchHotelCache shc = null;
        try {
            Selector selector = Selector
                    .from(SearchHotelCache.class)
                    .where("pagesize", "=", pagesize)
                    .and("pageindex", "=", pageindex)
                    .and("ordertype", "=", ordertype);
            shc = dbUtils.findFirst(selector);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return shc;
    }

    @OnRadioGroupCheckedChange(R.id.rg)
    @Override
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
            againLoadData();
        }
        if (checkedId != R.id.rb_price) {
            rb_price.setText(isPiceDec ? R.string.price_asc : R.string.price_desc);
        }
    }


}