package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bozhihuatong on 2016/12/1.
 * 祈福台的供品实体
 */
public class Offeringsbean implements Serializable{
    private static final long serialVersionUID = 9L;


    /**
     * amount : 0
     * buyCount : 0
     * jf : 0
     * name : 核桃
     * storeId : 18
     */

    @SerializedName("amount")
    private String amount;
    @SerializedName("buyCount")
    private String buyCount;
    @SerializedName("jf")
    private String jf;
    @SerializedName("name")
    private String name;
    @SerializedName("storeId")
    private String storeId;

    public String getAmount() {
        return amount;
    }

    public int getAmountInt() {
        return Integer.valueOf(amount);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public void setBuyCountByint(int buyCount){
        Integer integer = Integer.valueOf(this.buyCount);
        int i = integer;
        int i1 = i + buyCount;
        setBuyCount(""+i1);
    }

    public String getBuyCount() {
        return buyCount;
    }

    public int getIntBuyCount(){
        return Integer.valueOf(buyCount);
    }
    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }


    public String getNameTitle(){
        return name+"("+buyCount+")";
    }

    public String getPriceTitle(){
        return jf+"积分/"+amount+"个";
    }

    public int getCurrentCount() {
        return Integer.valueOf(amount);
    }

    public int getIntJiFen(){
        return Integer.valueOf(jf);
    }

}
