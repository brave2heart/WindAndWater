package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/7.
 */

public class ZGJMSimpleBean {


    /**
     * reason : successed
     * result : [{"id":"873e943d1bcb40cd4b289e0809803343","title":"黄金 金子","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。"},{"id":"237169518a0ff81aec29b80a546aa7ac","title":"黄金","des":"梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。"},{"id":"315f055cfbae60064e07427321e6a722","title":"捡黄金","des":"梦见捡黄金，你要发大财，但要努力争取。发财也要付出努力，不会自动掉在你的口袋里。"}]
     * error_code : 0
     */

    @SerializedName("reason")
    private String reason;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("result")
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 873e943d1bcb40cd4b289e0809803343
         * title : 黄金 金子
         * des : 梦见黄金，预示会遭遇挫折。梦见有人送黄金给自己，可能会蒙受损失。女人梦见丢了黄金，预示添置新首饰。
         */

        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("des")
        private String des;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }
}
