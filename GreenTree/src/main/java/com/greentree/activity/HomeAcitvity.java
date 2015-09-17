package com.greentree.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.greentree.R;
import com.greentree.fragment.HomeFragment;
import com.greentree.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeAcitvity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.fragment_home);
        setBehindContentView(R.layout.fragment_menu);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 5);
        slidingMenu.setShadowDrawable(R.drawable.shape_menu_shadow);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_home, new HomeFragment());
        transaction.replace(R.id.fl_menu, new MenuFragment());
        transaction.commit();
    }
}
