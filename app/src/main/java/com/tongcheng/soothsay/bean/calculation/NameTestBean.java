package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/16.
 * 姓名测算实体bean
 */

public class NameTestBean {

    /**
     * biHua : ["7","10","6"]
     * jieLun : ["金土金","吉","★喜歡幫助他人，可順利成功。若能節制色慾，則能健壯而福壽雙全。"]
     * name : 宋家任
     * tBiHua : 23
     * wuGe : [{"jiXiong":"半吉","jieXi":"（刚强）权威刚强，突破万难，如能容忍，必获成功。","score":"17","type":"1","wuXing":"金"},{"jiXiong":"(半吉)","jieXi":"（八卦之数）八卦之数，乾坎艮震，巽离坤兑，无穷无尽。","score":"8","type":"2","wuXing":"金"},{"jiXiong":"吉","jieXi":"（厚重）厚重载德，安富尊荣，财官双美，功成名就。","score":"16","type":"3","wuXing":"土"},{"jiXiong":"吉","jieXi":"（七政之数）七政之数，精悍严谨，天赋之力，吉星照耀。","score":"7","type":"4","wuXing":"金"},{"jiXiong":"吉","jieXi":"（壮丽）旭日东升，壮丽壮观，权威旺盛，功名荣达。女性不宜此数。","score":"23","type":"5","wuXing":"火"}]
     * wuXing : ["金","木","金"]
     */

    private String name;
    private String tBiHua;
    private List<String> biHua;
    private List<String> jieLun;
    /**
     * jiXiong : 半吉
     * jieXi : （刚强）权威刚强，突破万难，如能容忍，必获成功。
     * score : 17
     * type : 1
     * wuXing : 金
     */

    private List<WuGeBean> wuGe;
    private List<String> wuXing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTBiHua() {
        return tBiHua;
    }

    public void setTBiHua(String tBiHua) {
        this.tBiHua = tBiHua;
    }

    public List<String> getBiHua() {
        return biHua;
    }

    public void setBiHua(List<String> biHua) {
        this.biHua = biHua;
    }

    public List<String> getJieLun() {
        return jieLun;
    }

    public void setJieLun(List<String> jieLun) {
        this.jieLun = jieLun;
    }

    public List<WuGeBean> getWuGe() {
        return wuGe;
    }

    public void setWuGe(List<WuGeBean> wuGe) {
        this.wuGe = wuGe;
    }

    public List<String> getWuXing() {
        return wuXing;
    }

    public void setWuXing(List<String> wuXing) {
        this.wuXing = wuXing;
    }

    public static class WuGeBean {
        private String jiXiong;
        private String jieXi;
        private String score;
        private String type;
        private String wuXing;

        public String getJiXiong() {
            return jiXiong;
        }

        public void setJiXiong(String jiXiong) {
            this.jiXiong = jiXiong;
        }

        public String getJieXi() {
            return jieXi;
        }

        public void setJieXi(String jieXi) {
            this.jieXi = jieXi;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWuXing() {
            return wuXing;
        }

        public void setWuXing(String wuXing) {
            this.wuXing = wuXing;
        }
    }
}
