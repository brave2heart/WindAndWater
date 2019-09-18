package com.tongcheng.soothsay.bean.mine;

import java.util.List;

/**
 * Created by ALing on 2016/12/29 0029.
 */

public class OrderDetail {

    /**
     * address : 123123
     * area : 白银区
     * city : 白银
     * createDate : 1482906633000
     * feeType : 1
     * jf : 0
     * jfAmt : 0.0
     * name : lufei
     * orderList : [{"amount":"1","jf":"0","jfAmt":"0","price":"150.0","storeId":"0"}]
     * orderMoney : 150.0
     * orderNo : SC20161228143033265554
     * payType : 0
     * province : 甘肃省
     * status : 1
     * statusList : [{"createTime":"1482906634000","status":"1"}]
     * tel : 12312123
     */

    private String address;
    private String area;
    private String city;
    private String createDate;
    private String feeType;
    private String jf;
    private String jfAmt;
    private String name;
    private String orderMoney;
    private String orderNo;
    private String payType;
    private String province;
    private String status;
    private String tel;
    private List<OrderListBean> orderList;
    private List<StatusListBean> statusList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getJfAmt() {
        return jfAmt;
    }

    public void setJfAmt(String jfAmt) {
        this.jfAmt = jfAmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public List<StatusListBean> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusListBean> statusList) {
        this.statusList = statusList;
    }

    public static class OrderListBean {
        /**
         * amount : 1
         * jf : 0
         * jfAmt : 0
         * price : 150.0
         * storeId : 0
         */

        private String amount;
        private String jf;
        private String jfAmt;
        private String price;
        private String storeId;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getJf() {
            return jf;
        }

        public void setJf(String jf) {
            this.jf = jf;
        }

        public String getJfAmt() {
            return jfAmt;
        }

        public void setJfAmt(String jfAmt) {
            this.jfAmt = jfAmt;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
    }

    public static class StatusListBean {
        /**
         * createTime : 1482906634000
         * status : 1
         */

        private String createTime;
        private String status;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
