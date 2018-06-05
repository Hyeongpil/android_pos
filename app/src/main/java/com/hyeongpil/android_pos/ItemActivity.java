package com.hyeongpil.android_pos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Bind(R.id.fabtn_item_add)
    FloatingActionButton fabtn_add;
    @Bind(R.id.rv_item)
    RecyclerView rv_item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        rv_item.setLayoutManager(new LinearLayoutManager(this));
        rv_item.setAdapter(new ItemRecyclerAdapter());
        fabtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    class ItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<ItemModel> itemModels;

        public ItemRecyclerAdapter(){
            itemModels = new ArrayList<>();
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
            return new CustomViewHolder(view);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            CustomViewHolder customViewHolder = ((CustomViewHolder)holder);
            customViewHolder.tv_name.setText(itemModels.get(position).name);
            customViewHolder.tv_price.setText(itemModels.get(position).price);
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
}
