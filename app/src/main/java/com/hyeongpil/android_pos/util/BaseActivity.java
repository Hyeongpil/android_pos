package com.hyeongpil.android_pos.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.hyeongpil.android_pos.R;


public class BaseActivity extends AppCompatActivity {
    final static String TAG = BaseActivity.class.getSimpleName();
    protected DrawerLayout drawerLayout;
    protected ActionBar actionBar;
    protected ViewStub container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        container = (ViewStub) findViewById(R.id.container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_white_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // 뒤로가기버튼 클릭 시
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void actionBarTitleSet(String title, int color) {
        actionBar.setDisplayShowTitleEnabled(false);
        View view = getLayoutInflater().inflate(R.layout.actionbar_text_title, null);
        TextView titleView = (TextView) view.findViewById(R.id.actionbar_text_title);
        titleView.setText(title);
        titleView.setTextColor(color);

        actionBar.setCustomView(view);
    }

    protected void actionBarTitleSet(View view) {
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(view);
    }

}
