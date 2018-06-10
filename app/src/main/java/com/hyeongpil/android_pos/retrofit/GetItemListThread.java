package com.hyeongpil.android_pos.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hyeongpil.android_pos.model.ItemModel;
import com.hyeongpil.android_pos.util.BasicValue;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by hyeongpil on 2018-06-09.
 */

public class GetItemListThread extends Thread{
    final static String TAG = GetItemListThread.class.getSimpleName();
    private Context mContext;
    private Handler handler;
    List<ItemModel> itemModelList;

    public GetItemListThread(Handler handler, Context mContext){
        super();
        this.handler = handler;
        this.mContext = mContext;
    }

    @Override
    public void run() {
        super.run();
        Retrofit client = new Retrofit.Builder().baseUrl(BasicValue.getInstance().getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        GetItemListInterface service = client.create(GetItemListInterface.class);
        Call<List<ItemModel>> call = service.get_itemList_retrofit();
        call.enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(Call<List<ItemModel>> call, Response<List<ItemModel>> response) {
                if(response.isSuccessful()){
                    Log.e(TAG,"response.raw :"+response.raw());
                    itemModelList = response.body();
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemModelList",(Serializable)itemModelList);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }else{
                    Log.e(TAG,"else response.raw :"+response.raw());
                }
            }

            @Override
            public void onFailure(Call<List<ItemModel>> call, Throwable t) {
                Log.e(TAG,"getItemList 실패 :"+t.getMessage());
            }
        });

    }

    private interface GetItemListInterface {
        @GET("item/getItemList")
        Call<List<ItemModel>>get_itemList_retrofit();
    }
}
