package com.hyeongpil.android_pos.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hyeongpil on 2018-06-09.
 */

public class ItemApiService {
    public interface ItemAddInterface {
        @FormUrlEncoded
        @POST("item/itemAdd")
        Call<ItemApiService>itemAdd_retrofit(@Field("name") String name, @Field("price") int price, @Field("imageUrl") String imageUrl);
    }
}
