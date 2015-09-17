package com.greentree.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;

import com.greentree.R;
import com.greentree.activity.HotelSelectActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;


public class HomePageSelectFragment extends Fragment  {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home_page_select, null);
        ViewUtils.inject(this,view);
        return view;
    }

    @OnClick(R.id.btn_select)
    public void OnClick(View view){
        startActivity(new Intent(getActivity(), HotelSelectActivity.class));
    }


}
