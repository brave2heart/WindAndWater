package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by Steven on 16/11/25.
 */

public class ZiweiAnalyseBean {


    /**
     * gongExplain : {"content":[{"childContent":"紫微星五行属己土 ","childTitle":"紫微坐命宫"},{"childContent":"贪狼星坐命宫","childTitle":"贪狼坐命宫"}],"title":"您的自身状况"}
     * gongWeiInfo : 【命宫在卯】主人经常会遇到令他忧郁、烦闷、不畅的事情。虽然它的生活是灰色的世界中，但他亦有菊花遇霜雪弥坚的毅力和勇气。
     * sfszList : [{"daXian":"6 - 15","gongGz":"丁卯","gongMz":"命宫","xiaoXian":"3 15 27 39 51 63 75 ","xingMzYS":["天月","天哭"],"xingMzYX":["将星"],"xingMzYZ":["岁建"],"xingMzZS":["紫微","贪狼地 权","地劫"],"xingMzZX":["小耗"],"xingMzZZ":["沐浴"]},{"daXian":"76 - 85","gongGz":"壬申","gongMz":"奴仆","xiaoXian":"8 20 32 44 56 68 80 ","xingMzYS":["天巫","大耗","劫杀","天伤"],"xingMzYX":["劫煞"],"xingMzYZ":["小耗"],"xingMzZS":["文曲地 忌","天钺"],"xingMzZX":["伏兵"],"xingMzZZ":["病"]},{"daXian":"55 - 64","gongGz":"甲戌","gongMz":"疾厄","xiaoXian":"10 22 34 46 58 70 82 ","xingMzYS":["天使"],"xingMzYX":["天煞"],"xingMzYZ":["龙德"],"xingMzZS":["天同平","台辅"],"xingMzZX":["病符"],"xingMzZZ":["墓"]},{"daXian":"92 - 101","gongGz":"庚午","gongMz":"田宅","xiaoXian":"6 18 30 42 54 66 78 ","xingMzYS":["天姚","天喜","天才"],"xingMzYX":["息神"],"xingMzYZ":["贯索"],"xingMzZS":["天梁庙 科","文昌陷","封诰","禄存"],"xingMzZX":["博士"],"xingMzZZ":["帝旺"]}]
     * xingXiangInfo : {"mJXScale":"80.00% 吉","mJiXing":"吉：文曲对照、天钺对照、文昌加会、禄存加会","mShaXing":"凶：地劫坐命宫"}
     * zhuXingInfo : 命宫主星为：紫微、贪狼
     */

    private GongExplainBean gongExplain;
    private String gongWeiInfo;
    private XingXiangInfoBean xingXiangInfo;
    private String zhuXingInfo;
    private List<SfszListBean> sfszList;

    public GongExplainBean getGongExplain() {
        return gongExplain;
    }

    public void setGongExplain(GongExplainBean gongExplain) {
        this.gongExplain = gongExplain;
    }

    public String getGongWeiInfo() {
        return gongWeiInfo;
    }

    public void setGongWeiInfo(String gongWeiInfo) {
        this.gongWeiInfo = gongWeiInfo;
    }

    public XingXiangInfoBean getXingXiangInfo() {
        return xingXiangInfo;
    }

    public void setXingXiangInfo(XingXiangInfoBean xingXiangInfo) {
        this.xingXiangInfo = xingXiangInfo;
    }

    public String getZhuXingInfo() {
        return zhuXingInfo;
    }

    public void setZhuXingInfo(String zhuXingInfo) {
        this.zhuXingInfo = zhuXingInfo;
    }

    public List<SfszListBean> getSfszList() {
        return sfszList;
    }

    public void setSfszList(List<SfszListBean> sfszList) {
        this.sfszList = sfszList;
    }

    public static class GongExplainBean {
        /**
         * content : [{"childContent":"紫微星五行属己土 ","childTitle":"紫微坐命宫"},{"childContent":"贪狼星坐命宫","childTitle":"贪狼坐命宫"}]
         * title : 您的自身状况
         */

        private String title;
        private String remindInfo;

        public String getRemind() {
            return remindInfo;
        }

        public void setRemind(String remind) {
            this.remindInfo = remind;
        }

        private List<ContentBean> content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * childContent : 紫微星五行属己土
             * childTitle : 紫微坐命宫
             */

            private String childContent;
            private String childTitle;

            public String getChildContent() {
                return childContent;
            }

            public void setChildContent(String childContent) {
                this.childContent = childContent;
            }

            public String getChildTitle() {
                return childTitle;
            }

            public void setChildTitle(String childTitle) {
                this.childTitle = childTitle;
            }
        }
    }

    public static class XingXiangInfoBean {
        /**
         * mJXScale : 80.00% 吉
         * mJiXing : 吉：文曲对照、天钺对照、文昌加会、禄存加会
         * mShaXing : 凶：地劫坐命宫
         */

        private String mJXScale;
        private String mJiXing;
        private String mShaXing;

        public String getMJXScale() {
            return mJXScale;
        }

        public void setMJXScale(String mJXScale) {
            this.mJXScale = mJXScale;
        }

        public String getMJiXing() {
            return mJiXing;
        }

        public void setMJiXing(String mJiXing) {
            this.mJiXing = mJiXing;
        }

        public String getMShaXing() {
            return mShaXing;
        }

        public void setMShaXing(String mShaXing) {
            this.mShaXing = mShaXing;
        }
    }

    public static class SfszListBean {
        /**
         * daXian : 6 - 15
         * gongGz : 丁卯
         * gongMz : 命宫
         * xiaoXian : 3 15 27 39 51 63 75
         * xingMzYS : ["天月","天哭"]
         * xingMzYX : ["将星"]
         * xingMzYZ : ["岁建"]
         * xingMzZS : ["紫微","贪狼地 权","地劫"]
         * xingMzZX : ["小耗"]
         * xingMzZZ : ["沐浴"]
         */

        private String daXian;
        private String gongGz;
        private String gongMz;
        private String xiaoXian;
        private List<String> xingMzYS;
        private List<String> xingMzYX;
        private List<String> xingMzYZ;
        private List<String> xingMzZS;
        private List<String> xingMzZX;
        private List<String> xingMzZZ;

        public String getDaXian() {
            return daXian;
        }

        public void setDaXian(String daXian) {
            this.daXian = daXian;
        }

        public String getGongGz() {
            return gongGz;
        }

        public void setGongGz(String gongGz) {
            this.gongGz = gongGz;
        }

        public String getGongMz() {
            return gongMz;
        }

        public void setGongMz(String gongMz) {
            this.gongMz = gongMz;
        }

        public String getXiaoXian() {
            return xiaoXian;
        }

        public void setXiaoXian(String xiaoXian) {
            this.xiaoXian = xiaoXian;
        }

        public List<String> getXingMzYS() {
            return xingMzYS;
        }

        public void setXingMzYS(List<String> xingMzYS) {
            this.xingMzYS = xingMzYS;
        }

        public List<String> getXingMzYX() {
            return xingMzYX;
        }

        public void setXingMzYX(List<String> xingMzYX) {
            this.xingMzYX = xingMzYX;
        }

        public List<String> getXingMzYZ() {
            return xingMzYZ;
        }

        public void setXingMzYZ(List<String> xingMzYZ) {
            this.xingMzYZ = xingMzYZ;
        }

        public List<String> getXingMzZS() {
            return xingMzZS;
        }

        public void setXingMzZS(List<String> xingMzZS) {
            this.xingMzZS = xingMzZS;
        }

        public List<String> getXingMzZX() {
            return xingMzZX;
        }

        public void setXingMzZX(List<String> xingMzZX) {
            this.xingMzZX = xingMzZX;
        }

        public List<String> getXingMzZZ() {
            return xingMzZZ;
        }

        public void setXingMzZZ(List<String> xingMzZZ) {
            this.xingMzZZ = xingMzZZ;
        }
    }
}
