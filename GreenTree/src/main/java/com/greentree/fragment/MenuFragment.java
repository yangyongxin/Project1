package com.greentree.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greentree.R;
import com.greentree.activity.HomeAcitvity;
import com.greentree.adapter.MenuAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuFragment extends Fragment {

    @ViewInject(R.id.lv_menu)
    private ListView lvMenu;
    private List<Map<String, Integer>> data;
    private Integer[] imageIds = {R.drawable.green_tree, R.drawable.news, R.drawable.activity, R.drawable.my_menu, R.drawable.personal_center, R.drawable.apply, R.drawable.more};
    private Integer[] titleIds = {R.string.kongbai, R.string.menu_newscenter, R.string.menu_activity, R.string.menu_myorder, R.string.menu_userlogin, R.string.menu_apply, R.string.menu_more};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_menu_page, null);
        ViewUtils.inject(this, view);
        initData();
        return view;
    }

    private void initData() {
        data = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> map;
        for (int i = 0; i < imageIds.length; i++) {
            map = new HashMap<String, Integer>();
            map.put("imageId", imageIds[i]);
            map.put("titleId", titleIds[i]);
            data.add(map);
        }
        MenuAdapter adapter = new MenuAdapter(getActivity(), data);
        lvMenu.setAdapter(adapter);
    }

    @OnItemClick(R.id.lv_menu)
    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setCurrentContent(R.id.fl_home, new HomeFragment());
                break;
        }
    }

    private void setCurrentContent(int containerId, Fragment fragment) {
        HomeAcitvity activity = (HomeAcitvity) getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
        activity.toggle();
    }
}
