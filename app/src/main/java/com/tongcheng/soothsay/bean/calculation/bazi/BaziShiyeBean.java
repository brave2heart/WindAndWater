package com.tongcheng.soothsay.bean.calculation.bazi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/12/8.
 * 八字事业实体
 */

public class BaziShiyeBean {

    /**
     * fangwei : 八字喜水木，忌土金，为大利东北，不利中西，这种八字如再东北方发展，可有名有利，如在家乡或南方，不易发展起来。
     * zhiYe : 林业、木材、食品、家具、服装、造纸、行政办公、IT软件、广告策划、文化教育、医疗卫生、文具用品、装饰饰品等属木类行业。
     */

    @SerializedName("fangwei")
    private String fangwei;
    @SerializedName("zhiYe")
    private String zhiYe;

    public String getFangwei() {
        return fangwei;
    }

    public void setFangwei(String fangwei) {
        this.fangwei = fangwei;
    }

    public String getZhiYe() {
        return zhiYe;
    }

    public void setZhiYe(String zhiYe) {
        this.zhiYe = zhiYe;
    }
}
