package com.hyeongpil.android_pos.model;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by hyeongpil on 2018-06-06.
 */

public class ItemModel implements Serializable{
    private String name;
    private int price;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
