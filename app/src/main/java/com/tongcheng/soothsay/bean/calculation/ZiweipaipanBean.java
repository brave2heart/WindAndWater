package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * Created by Steven on 16/11/16.
 */

public class ZiweipaipanBean implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * baseInfo : {"animals":"狗","lunarDate":"甲戌年九月十六日","mingGong":"酉","mingZhu":"文曲","name":"伍玉南","sexYY":"阳男","shenZhu":"文昌","shengGong":"亥","solarDate":"1994-10-20","wuXingJu":"金四局"}
     * gongWeiChen : {"daXian":"72 - 81","gongGz":"戊辰","gongMz":"疾厄","xiaoXian":"1 13 25 37 49 61 73 ","xingMzYS":["天虚","天使"],"xingMzYX":["月煞"],"xingMzYZ":["大耗"],"xingMzZS":["天机利","天梁庙"],"xingMzZX":["青龙"],"xingMzZZ":["养"]}
     * gongWeiChou : {"daXian":"42 - 51","gongGz":"丁丑","gongMz":"官禄","xiaoXian":"10 22 34 46 58 70 82 ","xingMzYS":["破碎","八座"],"xingMzYX":["天煞"],"xingMzYZ":["贯索"],"xingMzZS":["武曲庙 科","贪狼庙","天魁","陀罗庙"],"xingMzZX":["官符"],"xingMzZZ":["墓"]}
     * gongWeiHai : {"daXian":"22 - 31","gongGz":"乙亥","gongMz":"福德","xiaoXian":"8 20 32 44 56 68 80 ","xingMzYS":["天喜","孤辰","劫杀","恩光"],"xingMzYX":["劫煞"],"xingMzYZ":["晦气"],"xingMzZS":["天府地"],"xingMzZX":["大耗"],"xingMzZZ":["病"]}
     * gongWeiMao : {"daXian":"62 - 71","gongGz":"丁卯","gongMz":"迁移","xiaoXian":"12 24 36 48 60 72 84 ","xingMzYS":["咸池","三台"],"xingMzYX":["咸池"],"xingMzYZ":["小耗"],"xingMzZS":["天相陷","封诰","擎羊陷"],"xingMzZX":["力士"],"xingMzZZ":["胎"]}
     * gongWeiShen : {"daXian":"112 - 121","gongGz":"壬申","gongMz":"兄弟","xiaoXian":"5 17 29 41 53 65 77 ","xingMzYS":["天哭","天马"],"xingMzYX":["岁驿"],"xingMzYZ":["吊客"],"xingMzZS":[],"xingMzZX":["飞廉"],"xingMzZZ":["临官"]}
     * gongWeiSi : {"daXian":"82 - 91","gongGz":"己巳","gongMz":"财帛","xiaoXian":"2 14 26 38 50 62 74 ","xingMzYS":["天巫","天刑","大耗","红鸾"],"xingMzYX":["亡神"],"xingMzYZ":["龙德"],"xingMzZS":["紫微","七杀平","文曲庙"],"xingMzZX":["小耗"],"xingMzZZ":["长生"]}
     * gongWeiWei : {"daXian":"102 - 111","gongGz":"辛未","gongMz":"夫妻","xiaoXian":"4 16 28 40 52 64 76 ","xingMzYS":["寡宿","天德","天才","天贵"],"xingMzYX":["攀鞍"],"xingMzYZ":["天德"],"xingMzZS":["台辅","天官","天钺"],"xingMzZX":["奏书"],"xingMzZZ":["冠带"]}
     * gongWeiWu : {"daXian":"92 - 101","gongGz":"庚午","gongMz":"子女","xiaoXian":"3 15 27 39 51 63 75 ","xingMzYS":[],"xingMzYX":["将星"],"xingMzYZ":["白虎"],"xingMzZS":["破军庙 权"],"xingMzZX":["将军"],"xingMzZZ":["沐浴"]}
     * gongWeiXu : {"daXian":"12 - 21","gongGz":"甲戌","gongMz":"父母","xiaoXian":"7 19 31 43 55 67 79 ","xingMzYS":["阴煞","华盖"],"xingMzYX":["华盖"],"xingMzYZ":["岁建"],"xingMzZS":["天空"],"xingMzZX":["病符"],"xingMzZZ":["衰"]}
     * gongWeiYin : {"daXian":"52 - 61","gongGz":"丙寅","gongMz":"奴仆","xiaoXian":"11 23 35 47 59 71 83 ","xingMzYS":["天月","龙池","天伤"],"xingMzYX":["指背"],"xingMzYZ":["官符"],"xingMzZS":["太阳旺 忌","巨门庙","右弼","禄存","火星庙"],"xingMzZX":["博士"],"xingMzZZ":["绝"]}
     * gongWeiYou : {"daXian":"2 - 11","gongGz":"癸酉","gongMz":"命宫","xiaoXian":"6 18 30 42 54 66 78 ","xingMzYS":["天姚","天寿"],"xingMzYX":["息神"],"xingMzYZ":["病符"],"xingMzZS":["廉贞平 禄","文昌庙","天福"],"xingMzZX":["喜神"],"xingMzZZ":["帝旺"]}
     * gongWeiZi : {"daXian":"32 - 41","gongGz":"丙子","gongMz":"田宅","xiaoXian":"9 21 33 45 57 69 81 ","xingMzYS":["蜚廉","解神","凤阁"],"xingMzYX":["灾煞"],"xingMzYZ":["丧门"],"xingMzZS":["太阴庙","天同旺","左辅","地劫"],"xingMzZX":["伏兵"],"xingMzZZ":["死"]}
     */


    private BaseInfoBean baseInfo;
    private ZiweiAbsBean gongWeiChen;
    private ZiweiAbsBean gongWeiChou;
    private ZiweiAbsBean gongWeiHai;
    private ZiweiAbsBean gongWeiMao;
    private ZiweiAbsBean gongWeiShen;
    private ZiweiAbsBean gongWeiSi;
    private ZiweiAbsBean gongWeiWei;
    private ZiweiAbsBean gongWeiWu;
    private ZiweiAbsBean gongWeiXu;
    private ZiweiAbsBean gongWeiYin;
    private ZiweiAbsBean gongWeiYou;
    private ZiweiAbsBean gongWeiZi;

    public BaseInfoBean getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfoBean baseInfo) {
        this.baseInfo = baseInfo;
    }

    public ZiweiAbsBean getGongWeiChen() {
        return gongWeiChen;
    }

    public void setGongWeiChen(ZiweiAbsBean gongWeiChen) {
        this.gongWeiChen = gongWeiChen;
    }

    public ZiweiAbsBean getGongWeiChou() {
        return gongWeiChou;
    }

    public void setGongWeiChou(ZiweiAbsBean gongWeiChou) {
        this.gongWeiChou = gongWeiChou;
    }

    public ZiweiAbsBean getGongWeiHai() {
        return gongWeiHai;
    }

    public void setGongWeiHai(ZiweiAbsBean gongWeiHai) {
        this.gongWeiHai = gongWeiHai;
    }

    public ZiweiAbsBean getGongWeiMao() {
        return gongWeiMao;
    }

    public void setGongWeiMao(ZiweiAbsBean gongWeiMao) {
        this.gongWeiMao = gongWeiMao;
    }

    public ZiweiAbsBean getGongWeiShen() {
        return gongWeiShen;
    }

    public void setGongWeiShen(ZiweiAbsBean gongWeiShen) {
        this.gongWeiShen = gongWeiShen;
    }

    public ZiweiAbsBean getGongWeiSi() {
        return gongWeiSi;
    }

    public void setGongWeiSi(ZiweiAbsBean gongWeiSi) {
        this.gongWeiSi = gongWeiSi;
    }

    public ZiweiAbsBean getGongWeiWei() {
        return gongWeiWei;
    }

    public void setGongWeiWei(ZiweiAbsBean gongWeiWei) {
        this.gongWeiWei = gongWeiWei;
    }

    public ZiweiAbsBean getGongWeiWu() {
        return gongWeiWu;
    }

    public void setGongWeiWu(ZiweiAbsBean gongWeiWu) {
        this.gongWeiWu = gongWeiWu;
    }

    public ZiweiAbsBean getGongWeiXu() {
        return gongWeiXu;
    }

    public void setGongWeiXu(ZiweiAbsBean gongWeiXu) {
        this.gongWeiXu = gongWeiXu;
    }

    public ZiweiAbsBean getGongWeiYin() {
        return gongWeiYin;
    }

    public void setGongWeiYin(ZiweiAbsBean gongWeiYin) {
        this.gongWeiYin = gongWeiYin;
    }

    public ZiweiAbsBean getGongWeiYou() {
        return gongWeiYou;
    }

    public void setGongWeiYou(ZiweiAbsBean gongWeiYou) {
        this.gongWeiYou = gongWeiYou;
    }

    public ZiweiAbsBean getGongWeiZi() {
        return gongWeiZi;
    }

    public void setGongWeiZi(ZiweiAbsBean gongWeiZi) {
        this.gongWeiZi = gongWeiZi;
    }

    public static class BaseInfoBean implements Serializable{
        /**
         * animals : 狗
         * lunarDate : 甲戌年九月十六日
         * mingGong : 酉
         * mingZhu : 文曲
         * name : 伍玉南
         * sexYY : 阳男
         * shenZhu : 文昌
         * shengGong : 亥
         * solarDate : 1994-10-20
         * wuXingJu : 金四局
         */

        private static final long serialVersionUID = 1L;

        private String animals;
        private String lunarDate;
        private String mingGong;
        private String mingZhu;
        private String name;
        private String sexYY;
        private String shenZhu;
        private String shengGong;
        private String solarDate;
        private String wuXingJu;

        public String getAnimals() {
            return animals;
        }

        public void setAnimals(String animals) {
            this.animals = animals;
        }

        public String getLunarDate() {
            return lunarDate;
        }

        public void setLunarDate(String lunarDate) {
            this.lunarDate = lunarDate;
        }

        public String getMingGong() {
            return mingGong;
        }

        public void setMingGong(String mingGong) {
            this.mingGong = mingGong;
        }

        public String getMingZhu() {
            return mingZhu;
        }

        public void setMingZhu(String mingZhu) {
            this.mingZhu = mingZhu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSexYY() {
            return sexYY;
        }

        public void setSexYY(String sexYY) {
            this.sexYY = sexYY;
        }

        public String getShenZhu() {
            return shenZhu;
        }

        public void setShenZhu(String shenZhu) {
            this.shenZhu = shenZhu;
        }

        public String getShengGong() {
            return shengGong;
        }

        public void setShengGong(String shengGong) {
            this.shengGong = shengGong;
        }

        public String getSolarDate() {
            return solarDate;
        }

        public void setSolarDate(String solarDate) {
            this.solarDate = solarDate;
        }

        public String getWuXingJu() {
            return wuXingJu;
        }

        public void setWuXingJu(String wuXingJu) {
            this.wuXingJu = wuXingJu;
        }
    }

   /* public static class GongWeiChenBean extends ZiweiAbsBean implements Serializable{

        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 72 - 81
         * gongGz : 戊辰
         * gongMz : 疾厄
         * xiaoXian : 1 13 25 37 49 61 73
         * xingMzYS : ["天虚","天使"]
         * xingMzYX : ["月煞"]
         * xingMzYZ : ["大耗"]
         * xingMzZS : ["天机利","天梁庙"]
         * xingMzZX : ["青龙"]
         * xingMzZZ : ["养"]
         *//*


    }

    public static class GongWeiChouBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 42 - 51
         * gongGz : 丁丑
         * gongMz : 官禄
         * xiaoXian : 10 22 34 46 58 70 82
         * xingMzYS : ["破碎","八座"]
         * xingMzYX : ["天煞"]
         * xingMzYZ : ["贯索"]
         * xingMzZS : ["武曲庙 科","贪狼庙","天魁","陀罗庙"]
         * xingMzZX : ["官符"]
         * xingMzZZ : ["墓"]
         *//*


    }

    public static class GongWeiHaiBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 22 - 31
         * gongGz : 乙亥
         * gongMz : 福德
         * xiaoXian : 8 20 32 44 56 68 80
         * xingMzYS : ["天喜","孤辰","劫杀","恩光"]
         * xingMzYX : ["劫煞"]
         * xingMzYZ : ["晦气"]
         * xingMzZS : ["天府地"]
         * xingMzZX : ["大耗"]
         * xingMzZZ : ["病"]
         *//*


    }

    public static class GongWeiMaoBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 62 - 71
         * gongGz : 丁卯
         * gongMz : 迁移
         * xiaoXian : 12 24 36 48 60 72 84
         * xingMzYS : ["咸池","三台"]
         * xingMzYX : ["咸池"]
         * xingMzYZ : ["小耗"]
         * xingMzZS : ["天相陷","封诰","擎羊陷"]
         * xingMzZX : ["力士"]
         * xingMzZZ : ["胎"]
         *//*


    }

    public static class GongWeiShenBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 112 - 121
         * gongGz : 壬申
         * gongMz : 兄弟
         * xiaoXian : 5 17 29 41 53 65 77
         * xingMzYS : ["天哭","天马"]
         * xingMzYX : ["岁驿"]
         * xingMzYZ : ["吊客"]
         * xingMzZS : []
         * xingMzZX : ["飞廉"]
         * xingMzZZ : ["临官"]
         *//*


    }

    public static class GongWeiSiBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 82 - 91
         * gongGz : 己巳
         * gongMz : 财帛
         * xiaoXian : 2 14 26 38 50 62 74
         * xingMzYS : ["天巫","天刑","大耗","红鸾"]
         * xingMzYX : ["亡神"]
         * xingMzYZ : ["龙德"]
         * xingMzZS : ["紫微","七杀平","文曲庙"]
         * xingMzZX : ["小耗"]
         * xingMzZZ : ["长生"]
         *//*


    }

    public static class GongWeiWeiBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 102 - 111
         * gongGz : 辛未
         * gongMz : 夫妻
         * xiaoXian : 4 16 28 40 52 64 76
         * xingMzYS : ["寡宿","天德","天才","天贵"]
         * xingMzYX : ["攀鞍"]
         * xingMzYZ : ["天德"]
         * xingMzZS : ["台辅","天官","天钺"]
         * xingMzZX : ["奏书"]
         * xingMzZZ : ["冠带"]
         *//*


    }

    public static class GongWeiWuBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 92 - 101
         * gongGz : 庚午
         * gongMz : 子女
         * xiaoXian : 3 15 27 39 51 63 75
         * xingMzYS : []
         * xingMzYX : ["将星"]
         * xingMzYZ : ["白虎"]
         * xingMzZS : ["破军庙 权"]
         * xingMzZX : ["将军"]
         * xingMzZZ : ["沐浴"]
         *//*


    }

    public static class GongWeiXuBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 12 - 21
         * gongGz : 甲戌
         * gongMz : 父母
         * xiaoXian : 7 19 31 43 55 67 79
         * xingMzYS : ["阴煞","华盖"]
         * xingMzYX : ["华盖"]
         * xingMzYZ : ["岁建"]
         * xingMzZS : ["天空"]
         * xingMzZX : ["病符"]
         * xingMzZZ : ["衰"]
         *//*


    }

    public static class GongWeiYinBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 52 - 61
         * gongGz : 丙寅
         * gongMz : 奴仆
         * xiaoXian : 11 23 35 47 59 71 83
         * xingMzYS : ["天月","龙池","天伤"]
         * xingMzYX : ["指背"]
         * xingMzYZ : ["官符"]
         * xingMzZS : ["太阳旺 忌","巨门庙","右弼","禄存","火星庙"]
         * xingMzZX : ["博士"]
         * xingMzZZ : ["绝"]
         *//*


    }

    public static class GongWeiYouBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 2 - 11
         * gongGz : 癸酉
         * gongMz : 命宫
         * xiaoXian : 6 18 30 42 54 66 78
         * xingMzYS : ["天姚","天寿"]
         * xingMzYX : ["息神"]
         * xingMzYZ : ["病符"]
         * xingMzZS : ["廉贞平 禄","文昌庙","天福"]
         * xingMzZX : ["喜神"]
         * xingMzZZ : ["帝旺"]
         *//*


    }

    public static class GongWeiZiBean extends ZiweiAbsBean implements Serializable{
        private static final long serialVersionUID = 1L;
        *//**
         * daXian : 32 - 41
         * gongGz : 丙子
         * gongMz : 田宅
         * xiaoXian : 9 21 33 45 57 69 81
         * xingMzYS : ["蜚廉","解神","凤阁"]
         * xingMzYX : ["灾煞"]
         * xingMzYZ : ["丧门"]
         * xingMzZS : ["太阴庙","天同旺","左辅","地劫"]
         * xingMzZX : ["伏兵"]
         * xingMzZZ : ["死"]
         *//*


    }*/
}
