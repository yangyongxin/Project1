package com.greentree.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirstRun = sp.getBoolean("isFirstRun", true);
        Intent intent;
        if (isFirstRun) {
            intent = new Intent(this, GuideActivity.class);
        } else {
            intent = new Intent(this, HomeAcitvity.class);
        }
        startActivity(intent);
        finish();
    }

}
