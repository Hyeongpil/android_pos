package com.hyeongpil.android_pos.model;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by hyeongpil on 2018-06-06.
 */

public class ReciptModel {
    private List <ItemModel> items;
    private int cardPrice; // 카드
    private int cashPrice; // 현금
    private Timestamp time;
    private String cancle_yn; // 취소여부 플래그


    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public int getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }

    public int getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(int cashPrice) {
        this.cashPrice = cashPrice;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCancle_yn() {
        return cancle_yn;
    }

    public void setCancle_yn(String cancle_yn) {
        this.cancle_yn = cancle_yn;
    }
}
