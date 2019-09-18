package com.tongcheng.soothsay.bean.calculation.bazi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/12/8.
 * 八字性格实体
 */

public class BaziXinggeBean {

    /**
     * wuXingShengXiao : 土马
     * xingGe : 土马的主人是个行动狂，他的性格乐观，品性善良，对朋友尽心尽力，乐于助人，而且为人处事公正，所以有不少的朋友，能经常为朋友排难解忧，做事有责任感，易得上司的常识，虽然个性急燥，偶有出错，但身边的人依然对他爱护有加。他为人随和，擅长与人交往，因此很适合那些以经常在外活动，能够结交朋友的工作，在这此复杂的情况中，他们均能来去自如，他们最快乐的事莫过于学习古老的文化，或者去探索某些让他们感觉到自己适应的新社会秩序。养生之道对修养生息和身体健康非常有用，在日常生活中多注意一下这方面的知识，小心饮食，注意健康，另外再做些适当运动，自然可以健康、平安地生活。
     土马的主人必须付出辛苦的努力，才能够获得满意的成果。一生金钱运比较平衡，没有什么破财的地方，有地时候也会有炒股升值和中彩的情况出现，从而得到一笔巨额的钱财。这时宜把钱储蓄起来，以备不时之需，不要作无谓的花费。
     */

    @SerializedName("wuXingShengXiao")
    private String wuXingShengXiao;
    @SerializedName("xingGe")
    private String xingGe;

    public String getWuXingShengXiao() {
        return wuXingShengXiao;
    }

    public void setWuXingShengXiao(String wuXingShengXiao) {
        this.wuXingShengXiao = wuXingShengXiao;
    }

    public String getXingGe() {
        return xingGe;
    }

    public void setXingGe(String xingGe) {
        this.xingGe = xingGe;
    }
}
