package com.hyeongpil.android_pos.model;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by hyeongpil on 2018-06-06.
 */

public class ReciptModel {
    public List <ItemModel> items;
    public int cardPrice; // 카드
    public int cashPrice; // 현금
    public Timestamp time;
    public String cancle_yn; // 취소여부 플래그

}
