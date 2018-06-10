package com.hyeongpil.android_pos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyeongpil.android_pos.model.ItemModel;
import com.hyeongpil.android_pos.retrofit.GetItemListThread;
import com.hyeongpil.android_pos.util.BasicValue;

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
    List<ItemModel> itemModelList;

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

        getItemList();
        init();
    }


    private void init() {
        itemModelList = new ArrayList<>();
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

    private void getItemList() {
        Handler handler = new GetItemListReceiveHandler();
        Thread getItemListThread = new GetItemListThread(handler,mContext);
        getItemListThread.start();
    }


    @Override
    protected void onResume() {
        super.onResume();

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
            ItemModel item = itemModelList.get(position);
            customViewHolder.tv_name.setText(item.getName()); // 이름
            customViewHolder.tv_price.setText(String.valueOf(item.getPrice())); // 가격
            Glide.with(mContext).load(item.getImageUrl()).into(customViewHolder.iv_image); // 이미지
        }

        @Override
        public int getItemCount() {
            return itemModelList.size();
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
            try{
                Log.e(TAG,"onActivityResult 진입");
                getItemList();
//                itemModelList.add((ItemModel)data.getSerializableExtra("item"));
//                rv_item.getAdapter().notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(mContext, "상품 추가 실패", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"error :"+e);
            }
        }
    }
    private class GetItemListReceiveHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG,"GetItemListReceiveHandler in");
            List<ItemModel> itemModelList = (List<ItemModel>)msg.getData().getSerializable("itemModelList");
            BasicValue.getInstance().setItemModelList(itemModelList);
            ItemActivity.this.itemModelList = itemModelList;
            rv_item.getAdapter().notifyDataSetChanged();
        }
    }
}
