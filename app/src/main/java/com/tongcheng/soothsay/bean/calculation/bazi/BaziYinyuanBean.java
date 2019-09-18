package com.tongcheng.soothsay.bean.calculation.bazi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/12/8.
 * 八字婚姻实体
 */

public class BaziYinyuanBean {
    /**
     * ganZhiDay : 戊午
     * result : 具非常特殊的领悟力及理解力，思想超俗也钻牛角尖，容易自相矛盾但又坚持到底，偏印就是爱幻想、不切实际，所以常会挑选让人意想不到的配偶。交流上不易畅通，配偶容易个性偏内向，有时易有不够开朗的现象。丙寅、壬申日的女性，幸福、得优秀的子女；男性妻子贤淑。丁卯、癸酉日生人，幼年时代易患大病，早离双亲，婚姻不美满。癸酉日生的女性，夫缘尤劣。庚辰、辛丑日生者，与生父母缘薄；男性，妻缘不好，女性，可得贤良子女，一生幸福，但庚辰日生者，夫缘不佳。辛未、庚戌日生人，双亲缘薄，婚姻不美满；女性，表面柔顺，内心冷酷，不孝翁姑。结婚后，操心劳形。幼年时体弱多病，而且大都属严重危险的急症。不过日支偏印者，最好钻研发明、创作而容易成功。
     * shiShen : 偏印
     */

    @SerializedName("ganZhiDay")
    private String ganZhiDay;
    @SerializedName("result")
    private String result;
    @SerializedName("shiShen")
    private String shiShen;

    public String getGanZhiDay() {
        return ganZhiDay;
    }

    public void setGanZhiDay(String ganZhiDay) {
        this.ganZhiDay = ganZhiDay;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getShiShen() {
        return shiShen;
    }

    public void setShiShen(String shiShen) {
        this.shiShen = shiShen;
    }
}
