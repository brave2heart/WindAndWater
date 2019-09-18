package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by ALing on 2016/12/8 0008.
 */

public class WishingSquare {
    /**
     * blessingCount : 151
     * bornDate : 2011-11-16
     * content : 上善若水2
     * orderNo : 20161115172238139128
     * sex : 1
     * username : 工2
     * xydName : 光明灯
     */
    private String blessingCount;
    private String bornDate;
    private String content;
    private String orderNo;
    private String sex;
    private String username;
    private String xydName;

    public String getBlessingCount() {
        return blessingCount;
    }

    public void setBlessingCount(String blessingCount) {
        this.blessingCount = blessingCount;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishingSquare)) return false;

        WishingSquare that = (WishingSquare) o;

        return blessingCount.equals(that.blessingCount);

    }

    @Override
    public int hashCode() {
        return blessingCount.hashCode();
    }
}
