package com.hyeongpil.android_pos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    final static String TAG = MainActivity.class.getSimpleName();
    Context mContext;

    @Bind(R.id.tv_main_item)
    TextView tv_item;
    @Bind(R.id.tv_main_sell)
    TextView tv_sell;
    @Bind(R.id.tv_main_calc)
    TextView tv_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        init();
    }

    private void init() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tv_main_item:
                        intent = new Intent(mContext,ItemActivity.class);
                        return;
                }
                startActivity(intent);
            }
        };
        tv_item.setOnClickListener(clickListener);
        tv_sell.setOnClickListener(clickListener);
        tv_calc.setOnClickListener(clickListener);

    }



}