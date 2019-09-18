package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/11/24.
 * 数字测算实体
 */
public class NumberTestBean {
    /**
     * jiXiong : 吉带凶
     * number : "15812691920"
     * result : （退安）智谋胆力，冒险投机，沉浮不定，退保平安。
     * resultNumbr : 40
     */

    @SerializedName("jiXiong")
    private String jiXiong;
    @SerializedName("number")
    private String number;
    @SerializedName("result")
    private String result;
    @SerializedName("resultNumbr")
    private String resultNumbr;

    public String getJiXiong() {
        return jiXiong;
    }

    public void setJiXiong(String jiXiong) {
        this.jiXiong = jiXiong;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultNumbr() {
        return resultNumbr;
    }

    public void setResultNumbr(String resultNumbr) {
        this.resultNumbr = resultNumbr;
    }
}
