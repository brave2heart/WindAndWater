package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ALing on 2017/1/6 0006.
 */

public class XYDPriceBean {

    /**
     * 1 : 0.01     90天
     * 0 : 0.01     180天
     */

    @SerializedName("1")
    private String value1;
    @SerializedName("0")
    private String value0;

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue0() {
        return value0;
    }

    public void setValue0(String value0) {
        this.value0 = value0;
    }
}
