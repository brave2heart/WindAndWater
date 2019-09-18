package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * Created by 宋家任 on 2016/12/8.
 * 单个灵符实体bean
 */

public class OneLingFuBean implements Serializable{

    /**
     * address :
     * bazi :
     * isfree : 0
     * jcprice : 100.00
     * kgprice : 65.00
     * lfId : 1
     * name : 五鬼运财符
     * orderNo :
     * price : 25.00
     * status : 1
     * username :
     */

    private String address;
    private String bazi;
    private String isfree;
    private String jcprice;
    private String kgprice;
    private String lfId;
    private String name;
    private String orderNo;
    private String price;
    private String status;
    private String username;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBazi() {
        return bazi;
    }

    public void setBazi(String bazi) {
        this.bazi = bazi;
    }

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getJcprice() {
        return jcprice;
    }

    public void setJcprice(String jcprice) {
        this.jcprice = jcprice;
    }

    public String getKgprice() {
        return kgprice;
    }

    public void setKgprice(String kgprice) {
        this.kgprice = kgprice;
    }

    public String getLfId() {
        return lfId;
    }

    public void setLfId(String lfId) {
        this.lfId = lfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
