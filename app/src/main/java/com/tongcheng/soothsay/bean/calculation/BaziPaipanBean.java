package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/11/25.
 * 八字排盘实体
 */
public class BaziPaipanBean implements Serializable {
    private static final long serialVersionUID = 3L;

    /**
     * age : 8
     * baZi : 己未,丙子,癸酉,壬子
     * daYun : ["乙亥","甲戌","癸酉","壬申","辛未","庚午","己巳","戊辰"]
     * dayunShenSha : ["驿马 羊刃 丧门 ","太极 ","将星 丧门 ","天乙 太极 月德 国印 红鸾 ","太极 华盖 吊客 ","咸池 披麻 ","天乙 太极 天德 驿马 吊客 ","太极 披麻 "]
     * liuNian : [{"ganZhi":"丁卯","shenSha":"天乙 文昌 将星","year":"1987"},{"ganZhi":"戊辰","shenSha":"太极 披麻","year":"1988"},{"ganZhi":"己巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"1989"},{"ganZhi":"庚午","shenSha":"咸池 披麻","year":"1990"},{"ganZhi":"辛未","shenSha":"太极 华盖 吊客","year":"1991"},{"ganZhi":"壬申","shenSha":"天乙 太极 月德 国印 红鸾","year":"1992"},{"ganZhi":"癸酉","shenSha":"将星 丧门","year":"1993"},{"ganZhi":"甲戌","shenSha":"太极","year":"1994"},{"ganZhi":"乙亥","shenSha":"驿马 羊刃 丧门","year":"1995"},{"ganZhi":"丙子","shenSha":"天乙 禄 空亡 咸池 元辰","year":"1996"},{"ganZhi":"丁丑","shenSha":"太极 华盖 空亡","year":"1997"},{"ganZhi":"戊寅","shenSha":"国印 金舆 天喜","year":"1998"},{"ganZhi":"己卯","shenSha":"天乙 文昌 将星","year":"1999"},{"ganZhi":"庚辰","shenSha":"太极 披麻","year":"2000"},{"ganZhi":"辛巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2001"},{"ganZhi":"壬午","shenSha":"月德 咸池 披麻","year":"2002"},{"ganZhi":"癸未","shenSha":"太极 华盖 吊客","year":"2003"},{"ganZhi":"甲申","shenSha":"天乙 太极 国印 红鸾","year":"2004"},{"ganZhi":"乙酉","shenSha":"将星 丧门","year":"2005"},{"ganZhi":"丙戌","shenSha":"太极","year":"2006"},{"ganZhi":"丁亥","shenSha":"驿马 羊刃 丧门","year":"2007"},{"ganZhi":"戊子","shenSha":"天乙 禄 空亡 咸池 元辰","year":"2008"},{"ganZhi":"己丑","shenSha":"太极 华盖 空亡","year":"2009"},{"ganZhi":"庚寅","shenSha":"国印 金舆 天喜","year":"2010"},{"ganZhi":"辛卯","shenSha":"天乙 文昌 将星","year":"2011"},{"ganZhi":"壬辰","shenSha":"太极 月德 披麻","year":"2012"},{"ganZhi":"癸巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2013"},{"ganZhi":"甲午","shenSha":"咸池 披麻","year":"2014"},{"ganZhi":"乙未","shenSha":"太极 华盖 吊客","year":"2015"},{"ganZhi":"丙申","shenSha":"天乙 太极 国印 红鸾","year":"2016"},{"ganZhi":"丁酉","shenSha":"将星 丧门","year":"2017"},{"ganZhi":"戊戌","shenSha":"太极","year":"2018"},{"ganZhi":"己亥","shenSha":"驿马 羊刃 丧门","year":"2019"},{"ganZhi":"庚子","shenSha":"天乙 禄 空亡 咸池 元辰","year":"2020"},{"ganZhi":"辛丑","shenSha":"太极 华盖 空亡","year":"2021"},{"ganZhi":"壬寅","shenSha":"月德 国印 金舆 天喜","year":"2022"},{"ganZhi":"癸卯","shenSha":"天乙 文昌 将星","year":"2023"},{"ganZhi":"甲辰","shenSha":"太极 披麻","year":"2024"},{"ganZhi":"乙巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2025"},{"ganZhi":"丙午","shenSha":"咸池 披麻","year":"2026"},{"ganZhi":"丁未","shenSha":"太极 华盖 吊客","year":"2027"},{"ganZhi":"戊申","shenSha":"天乙 太极 国印 红鸾","year":"2028"},{"ganZhi":"己酉","shenSha":"将星 丧门","year":"2029"},{"ganZhi":"庚戌","shenSha":"太极","year":"2030"},{"ganZhi":"辛亥","shenSha":"驿马 羊刃 丧门","year":"2031"},{"ganZhi":"壬子","shenSha":"天乙 月德 禄 空亡 咸池 元辰","year":"2032"},{"ganZhi":"癸丑","shenSha":"太极 华盖 空亡","year":"2033"},{"ganZhi":"甲寅","shenSha":"国印 金舆 天喜","year":"2034"},{"ganZhi":"乙卯","shenSha":"天乙 文昌 将星","year":"2035"},{"ganZhi":"丙辰","shenSha":"太极 披麻","year":"2036"},{"ganZhi":"丁巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2037"},{"ganZhi":"戊午","shenSha":"咸池 披麻","year":"2038"},{"ganZhi":"己未","shenSha":"太极 华盖 吊客","year":"2039"},{"ganZhi":"庚申","shenSha":"天乙 太极 国印 红鸾","year":"2040"},{"ganZhi":"辛酉","shenSha":"将星 丧门","year":"2041"},{"ganZhi":"壬戌","shenSha":"太极 月德","year":"2042"},{"ganZhi":"癸亥","shenSha":"驿马 羊刃 丧门","year":"2043"},{"ganZhi":"甲子","shenSha":"天乙 禄 空亡 咸池 元辰","year":"2044"},{"ganZhi":"乙丑","shenSha":"太极 华盖 空亡","year":"2045"},{"ganZhi":"丙寅","shenSha":"国印 金舆 天喜","year":"2046"},{"ganZhi":"丁卯","shenSha":"天乙 文昌 将星","year":"2047"},{"ganZhi":"戊辰","shenSha":"太极 披麻","year":"2048"},{"ganZhi":"己巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2049"},{"ganZhi":"庚午","shenSha":"咸池 披麻","year":"2050"},{"ganZhi":"辛未","shenSha":"太极 华盖 吊客","year":"2051"},{"ganZhi":"壬申","shenSha":"天乙 太极 月德 国印 红鸾","year":"2052"},{"ganZhi":"癸酉","shenSha":"将星 丧门","year":"2053"},{"ganZhi":"甲戌","shenSha":"太极","year":"2054"},{"ganZhi":"乙亥","shenSha":"驿马 羊刃 丧门","year":"2055"},{"ganZhi":"丙子","shenSha":"天乙 禄 空亡 咸池 元辰","year":"2056"},{"ganZhi":"丁丑","shenSha":"太极 华盖 空亡","year":"2057"},{"ganZhi":"戊寅","shenSha":"国印 金舆 天喜","year":"2058"},{"ganZhi":"己卯","shenSha":"天乙 文昌 将星","year":"2059"},{"ganZhi":"庚辰","shenSha":"太极 披麻","year":"2060"},{"ganZhi":"辛巳","shenSha":"天乙 太极 天德 驿马 吊客","year":"2061"},{"ganZhi":"壬午","shenSha":"月德 咸池 披麻","year":"2062"},{"ganZhi":"癸未","shenSha":"太极 华盖 吊客","year":"2063"},{"ganZhi":"甲申","shenSha":"天乙 太极 国印 红鸾","year":"2064"},{"ganZhi":"乙酉","shenSha":"将星 丧门","year":"2065"},{"ganZhi":"丙戌","shenSha":"太极","year":"2066"}]
     * naYin : ["天上火","涧下水","剑锋金","桑松木"]
     * nongLi : 一九七九年十一月十四子时
     * shengWangSiJue : ["帝旺","衰","病","死","墓","绝","胎","养"]
     * shengXiao : 羊
     * shiShen : ["食神","伤官","比肩","劫财","偏印","正印","七杀","正官"]
     * siShiShen : ["七杀","正财","日元","劫财"]
     * siZhuShenSha : ["年柱:太极 华盖 吊客 ","月柱:天乙 禄 元辰 空亡 咸池 ","日柱:金神 将星 灾煞 丧门 ","时柱:月德 天乙 禄 元辰 空亡 咸池 "]
     * wangRuo : 日元身旺
     * zangGan : ["乙 丁 己","癸 null null","辛 null null","癸 null null"]
     */

    @SerializedName("age")
    private String age;
    @SerializedName("baZi")
    private String baZi;
    @SerializedName("nongLi")
    private String nongLi;
    @SerializedName("shengXiao")
    private String shengXiao;
    @SerializedName("wangRuo")
    private String wangRuo;
    @SerializedName("daYun")
    private List<String> daYun;
    @SerializedName("dayunShenSha")
    private List<String> dayunShenSha;
    @SerializedName("liuNian")
    private List<LiuNianBean> liuNian;
    @SerializedName("naYin")
    private List<String> naYin;
    @SerializedName("shengWangSiJue")
    private List<String> shengWangSiJue;
    @SerializedName("shiShen")
    private List<String> shiShen;
    @SerializedName("siShiShen")
    private List<String> siShiShen;
    @SerializedName("siZhuShenSha")
    private List<String> siZhuShenSha;
    @SerializedName("zangGan")
    private List<String> zangGan;


    @SerializedName("xiYong")
    private String xiYong;


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBaZi() {
        return baZi;
    }

    public void setBaZi(String baZi) {
        this.baZi = baZi;
    }

    public String getNongLi() {
        return nongLi;
    }

    public void setNongLi(String nongLi) {
        this.nongLi = nongLi;
    }

    public String getShengXiao() {
        return shengXiao;
    }

    public void setShengXiao(String shengXiao) {
        this.shengXiao = shengXiao;
    }

    public String getWangRuo() {
        return wangRuo;
    }

    public void setWangRuo(String wangRuo) {
        this.wangRuo = wangRuo;
    }

    public List<String> getDaYun() {
        return daYun;
    }

    public void setDaYun(List<String> daYun) {
        this.daYun = daYun;
    }

    public List<String> getDayunShenSha() {
        return dayunShenSha;
    }

    public void setDayunShenSha(List<String> dayunShenSha) {
        this.dayunShenSha = dayunShenSha;
    }

    public List<LiuNianBean> getLiuNian() {
        return liuNian;
    }

    public void setLiuNian(List<LiuNianBean> liuNian) {
        this.liuNian = liuNian;
    }

    public List<String> getNaYin() {
        return naYin;
    }

    public void setNaYin(List<String> naYin) {
        this.naYin = naYin;
    }

    public List<String> getShengWangSiJue() {
        return shengWangSiJue;
    }

    public void setShengWangSiJue(List<String> shengWangSiJue) {
        this.shengWangSiJue = shengWangSiJue;
    }

    public List<String> getShiShen() {
        return shiShen;
    }

    public void setShiShen(List<String> shiShen) {
        this.shiShen = shiShen;
    }

    public List<String> getSiShiShen() {
        return siShiShen;
    }

    public void setSiShiShen(List<String> siShiShen) {
        this.siShiShen = siShiShen;
    }

    public List<String> getSiZhuShenSha() {
        return siZhuShenSha;
    }

    public void setSiZhuShenSha(List<String> siZhuShenSha) {
        this.siZhuShenSha = siZhuShenSha;
    }

    public List<String> getZangGan() {
        return zangGan;
    }

    public void setZangGan(List<String> zangGan) {
        this.zangGan = zangGan;
    }

    public String getXiYong() {
        return xiYong;
    }

    public void setXiYong(String xiYong) {
        this.xiYong = xiYong;
    }

    public static class LiuNianBean implements Serializable{
        private static final long serialVersionUID = 4L;

        /**
         * ganZhi : 丁卯
         * shenSha : 天乙 文昌 将星
         * year : 1987
         */

        @SerializedName("ganZhi")
        private String ganZhi;
        @SerializedName("shenSha")
        private String shenSha;
        @SerializedName("year")
        private String year;

        public String getGanZhi() {
            return ganZhi;
        }

        public void setGanZhi(String ganZhi) {
            this.ganZhi = ganZhi;
        }

        public String getShenSha() {
            return shenSha;
        }

        public void setShenSha(String shenSha) {
            this.shenSha = shenSha;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }


        public int getIntYear(){
            return Integer.valueOf(year);
        }

        public String getTenYearStr(){
            Integer startyear = Integer.valueOf(year);
            int endYear=startyear+10;
            return ganZhi+"运"+"("+startyear+"-"+endYear+"年)";
//            "乙亥运（1987-1996年）"

        }
    }

    @Override
    public String toString() {
        return "BaziPaipanBean{" +
                "age='" + age + '\'' +
                ", baZi='" + baZi + '\'' +
                ", nongLi='" + nongLi + '\'' +
                ", shengXiao='" + shengXiao + '\'' +
                ", wangRuo='" + wangRuo + '\'' +
                ", daYun=" + daYun +
                ", dayunShenSha=" + dayunShenSha +
                ", liuNian=" + liuNian +
                ", naYin=" + naYin +
                ", shengWangSiJue=" + shengWangSiJue +
                ", shiShen=" + shiShen +
                ", siShiShen=" + siShiShen +
                ", siZhuShenSha=" + siZhuShenSha +
                ", zangGan=" + zangGan +
                '}';
    }
}
