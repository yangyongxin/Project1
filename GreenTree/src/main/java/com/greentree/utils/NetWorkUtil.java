package com.greentree.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.greentree.application.MyApplication;
import com.greentree.bean.Banner;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Map;

/**
 * Created by yangyongxin on 15/9/16.
 */
public class NetWorkUtil {

    private static String TAG = "NetWorkUtil";

    public interface MyRequestCallBack {
        public void onSuccess(String str);

        public void onFailure(String str);
    }

    public static void doGetRequest(String url, Map<String, String> params, final MyRequestCallBack requestCallBack) {
        HttpUtils httpUtils = MyApplication.getApplication().getHttpUtils();
        RequestParams requestParams=null;
        if (params!=null&&!params.isEmpty()){
            requestParams = new RequestParams();
            for (String key : params.keySet()) {
                requestParams.addBodyParameter(key, params.get(key));
            }
        }
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                requestCallBack.onSuccess(responseInfo.result);
                Log.i(TAG, "onSuccess -----------" + responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i(TAG, "onFailure -----------" + s);
            }
        });
    }

    public static void doPostRequest(String url, Map<String, String> params, final MyRequestCallBack requestCallBack) {
        HttpUtils httpUtils = MyApplication.getApplication().getHttpUtils();
        RequestParams requestParams=null;
        if (params!=null&&!params.isEmpty()){
            requestParams = new RequestParams();
            for (String key : params.keySet()) {
                requestParams.addBodyParameter(key, params.get(key));
            }
        }
        httpUtils.send(HttpRequest.HttpMethod.POST, url, requestParams, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                requestCallBack.onSuccess(responseInfo.result);
                Log.i(TAG, "onSuccess -----------" + responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i(TAG, "onFailure -----------" + s);
            }
        });
    }
}
