package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bozhihuatong on 2016/12/2.
 * 祈福台  大仙详情里的 供品信息实体
 */
public class QfDetailBean implements Serializable{

    private static final long serialVersionUID = 6L;
    /**
     * qfDate : 1480584748000
     * qfGp : 苹果
     */

    @SerializedName("qfDate")
    private String qfDate;
    @SerializedName("qfGp")
    private String qfGp;

    public String getQfDate() {
        return qfDate;
    }

    public void setQfDate(String qfDate) {
        this.qfDate = qfDate;
    }

    public String getQfGp() {
        return qfGp;
    }

    public void setQfGp(String qfGp) {
        this.qfGp = qfGp;
    }
}
