package com.tongcheng.soothsay.bean.calculation.bazi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/12/8.
 * 八字先天命盘实体     网络返回的是一组数据  需要套list
 *                              List<BaziSzshishenBean></>
 */

public class BaziSzshishenBean {


    /**
     * jieLun : 食神为喜现于年柱又能坐下生助之地者，大都出身于中上家庭，可得双亲财力物力及精神助益与支持，而得以发展事业，更喜命局内见财星，财富丰隆之命。但如食神坐下死绝之地者，必须凭自己之努力以发展事业。
     * shiShen : 食神
     * siZhu : 年柱
     */

    @SerializedName("jieLun")
    private String jieLun;
    @SerializedName("shiShen")
    private String shiShen;
    @SerializedName("siZhu")
    private String siZhu;

    public String getJieLun() {
        return jieLun;
    }

    public void setJieLun(String jieLun) {
        this.jieLun = jieLun;
    }

    public String getShiShen() {
        return shiShen;
    }

    public void setShiShen(String shiShen) {
        this.shiShen = shiShen;
    }

    public String getSiZhu() {
        return siZhu;
    }

    public void setSiZhu(String siZhu) {
        this.siZhu = siZhu;
    }
}
