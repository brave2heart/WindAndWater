package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by ALing on 2016/12/6 0006.
 */

public class WishingOrder {
        /**
         * bornDate : 1990-01-10
         * content : 花好月圆
         * expiryType : 0
         * money : 30.00
         * orderNo : 20161114172238139308
         * sex : 1
         * status : 1
         * username : 张三
         * xydName : 婚姻灯
         */
        private String bornDate;
        private String content;
        private String expiryType;
        private String money;
        private String orderNo;
        private String sex;
        private String status;
        private String username;
        private String xydName;
        private String blessingCount;

        public String getBornDate() {
            return bornDate;
        }

        public void setBornDate(String bornDate) {
            this.bornDate = bornDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExpiryType() {
            return expiryType;
        }

        public void setExpiryType(String expiryType) {
            this.expiryType = expiryType;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getXydName() {
            return xydName;
        }

        public void setXydName(String xydName) {
            this.xydName = xydName;
        }

        public String getBlessingCount() {
            return blessingCount;
        }

        public void setBlessingCount(String blessingCount) {
            this.blessingCount = blessingCount;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishingOrder)) return false;

        WishingOrder that = (WishingOrder) o;

        return status.equals(that.status);

    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
