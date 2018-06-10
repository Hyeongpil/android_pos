package com.hyeongpil.android_pos.util;

import com.hyeongpil.android_pos.model.ItemModel;

import java.util.List;

public class BasicValue {
    final static String TAG = BasicValue.class.getSimpleName();

    // 싱글턴 패턴
    private static BasicValue ourInstance = new BasicValue();
    public static BasicValue getInstance(){return ourInstance;}
    private BasicValue(){}

    private String baseUrl = "http://115.68.14.27:8088/";
    private List<ItemModel> itemModelList;

    public String getBaseUrl() {
        return baseUrl;
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }
}
