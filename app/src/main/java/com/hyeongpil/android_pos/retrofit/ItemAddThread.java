package com.hyeongpil.android_pos.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hyeongpil.android_pos.util.BasicValue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyeongpil on 2018-06-09.
 */

public class ItemAddThread extends Thread{
    final static String TAG = ItemAddThread.class.getSimpleName();
    private Context mContext;
    private Handler handler;
    private ItemApiService itemApiService;
    private String name;
    private int price;
    private String imageUrl;
    private String ecy;

    public ItemAddThread(Handler handler, Context mContext, String name, int price, String imageUrl, String ecy){
        super();
        this.handler = handler;
        this.mContext = mContext;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.ecy = ecy;
    }

    @Override
    public void run() {
        super.run();
        Retrofit client = new Retrofit.Builder().baseUrl(BasicValue.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        ItemApiService.ItemAddInterface service = client.create(ItemApiService.ItemAddInterface.class);
        Call<ItemApiService> call = service.itemAdd_retrofit(name,price,imageUrl,ecy);
        call.enqueue(new Callback<ItemApiService>() {
            @Override
            public void onResponse(Call<ItemApiService> call, Response<ItemApiService> response) {
                if(response.isSuccessful()){
                    Log.e(TAG,"response.raw :"+response.raw());
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg","성공");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }else{
                    Log.e(TAG,"else response.raw :"+response.raw());
                }
            }

            @Override
            public void onFailure(Call<ItemApiService> call, Throwable t) {
                Log.e(TAG,"itemAdd 실패 :"+t.getMessage());
            }
        });

    }
}
