package com.smartbejing.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.smartbejing.R;
import com.smartbejing.activity.MainActivity;
import com.smartbejing.bean.News;
import com.smartbejing.pager.NewsPager;

import java.util.List;

/**
 * Created by yangyongxin on 15/9/10.
 */
public class LeftMenuFragment extends BaseFragment {

    private ListView lvList;
    private List<News.NewsMenuData> newsMenuData;
    private int currentPos;
    private MenuAdapter menuAdapter;
    private int currentMenuDetailPager;

    @Override
    public View initViews() {
        View view = View.inflate(getActivity(), R.layout.fragment_left_menu, null);
        lvList = (ListView) view.findViewById(R.id.lv_list);

        return view;
    }

    @Override
    public void initData() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPos=position;
                menuAdapter.notifyDataSetChanged();
                setCurrentMenuDetailPager(position);
                toggleSlidingMenu();
            }
        });

    }

    protected void toggleSlidingMenu() {
        MainActivity mainActivity = (MainActivity) getActivity();
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        slidingMenu.toggle();
    }


    public void setCurrentMenuDetailPager(int position) {
        MainActivity mainActivity = (MainActivity) getActivity();
        ContentFragment contentFragment = mainActivity.getContentFragment();
        NewsPager newsPager = contentFragment.getNewsPager();
        newsPager.setCurrentDetailMenuPager(position);

    }

    public void setMenuData(News data) {
        newsMenuData = data.getNewsMenuData();
        menuAdapter = new MenuAdapter();
        lvList.setAdapter(menuAdapter);
    }



    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsMenuData.size();
        }

        @Override
        public Object getItem(int position) {
            return newsMenuData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.left_menu_item, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);

            News.NewsMenuData newsMenuData = (News.NewsMenuData) getItem(position);
            tvTitle.setText("▶ "+newsMenuData.getTitle());
            if (currentPos==position){
                tvTitle.setEnabled(true);
            }else {
                tvTitle.setEnabled(false);
            }
            return view;
        }
    }
}
