package com.hyeongpil.android_pos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyeongpil.android_pos.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hyeongpil on 2018-06-06.
 */

public class ItemActivity extends AppCompatActivity {
    final static String TAG = ItemActivity.class.getSimpleName();
    final static int ITEM_ADD = 1234;
    private Context mContext;
    List<ItemModel> itemModels;

    @Bind(R.id.fabtn_item_add)
    FloatingActionButton fabtn_add;
    @Bind(R.id.rv_item)
    RecyclerView rv_item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        mContext = this;

        init();
    }

    private void init() {
        itemModels = new ArrayList<>();
        rv_item.setLayoutManager(new LinearLayoutManager(this));
        rv_item.setAdapter(new ItemRecyclerAdapter());
        fabtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ItemAddActivity.class);
                startActivityForResult(intent,ITEM_ADD);
            }
        });
    }

    class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
            return new CustomViewHolder(view);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CustomViewHolder customViewHolder = ((CustomViewHolder)holder);
            ItemModel item = itemModels.get(position);
            customViewHolder.tv_name.setText(item.getName()); // 이름
            customViewHolder.tv_price.setText(String.valueOf(item.getPrice())); // 가격
            Glide.with(mContext).load(item.getImageUrl()).into(customViewHolder.iv_image); // 이미지
        }

        @Override
        public int getItemCount() {
            return itemModels.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView iv_image;
            public TextView tv_name;
            public TextView tv_price;

            public CustomViewHolder(View view) {
                super(view);
                iv_image = (ImageView) view.findViewById(R.id.iv_item_item_image);
                tv_name = (TextView) view.findViewById(R.id.tv_item_item_name);
                tv_price = (TextView) view.findViewById(R.id.tv_item_item_price);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ITEM_ADD){
            ItemModel item = (ItemModel)data.getSerializableExtra("item");
            itemModels.add((ItemModel)data.getSerializableExtra("item"));
            rv_item.getAdapter().notifyDataSetChanged();
        }
    }
}
