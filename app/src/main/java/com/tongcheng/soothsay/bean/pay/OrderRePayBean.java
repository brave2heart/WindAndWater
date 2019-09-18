package com.tongcheng.soothsay.bean.pay;

import java.io.Serializable;

/**
 * Created by ALing on 2016/12/27 0027.
 */

public class OrderRePayBean implements Serializable{
    private String token;
    private String orderNo;
    private String payType;
    private String goodsname;
    private String goodsprice;

    public OrderRePayBean() {
    }

    public String getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(String goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
