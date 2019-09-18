package com.tongcheng.soothsay.bean.mine;

import java.io.Serializable;

/**
 * Created by ALing on 2016/12/26 0026.
 */

public class AllOrder implements Serializable {


    /**
     * createTime : 2016-12-21 10:22:41
     * money : 0.01
     * orderNo : LF20161221102240580784
     * orderStatus : 0
     * storeName : 文武财神招财符请符
     * transType : LF
     */

    private String createTime;
    private String money;
    private String orderNo;
    private String orderStatus;
    private String storeName;
    private String transType;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AllOrder)) return false;

        AllOrder order = (AllOrder) o;

        return orderNo.equals(order.orderNo);

    }

    @Override
    public int hashCode() {
        return orderNo.hashCode();
    }
}
