package com.tongcheng.soothsay.bean.calculation;

import java.util.List;

/**
 * Created by 宋家任 on 2017/1/5.
 * 命里推算轮播图及大师亲算实体
 */

public class MingLiBean {

    private List<AdsBean> ads;
    private List<DsBean> ds;

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<DsBean> getDs() {
        return ds;
    }

    public void setDs(List<DsBean> ds) {
        this.ds = ds;
    }

    public static class AdsBean {
        /**
         * cover : http://fssvip.b0.upaiyun.com/fss/21621483579551986.jpg
         * title : 命理推算广告一
         * type : 05
         * url : http://120.76.219.201:8080/html/advcontent.html?contentId=12
         */

        private String cover;
        private String title;
        private String type;
        private String url;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DsBean {
        /**
         * chengHao : 李大仙
         * id : 3
         * jianJie : 这是一个风水大师
         * name : 李大师
         * shanChang : 擅长算命，风水
         * touXiang : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=37621483430097944.png
         * url : http://120.76.219.201:8080/html/dashixiangqing.html?dsId=3&token=
         */

        private String chengHao;
        private String id;
        private String jianJie;
        private String name;
        private String shanChang;
        private String touXiang;
        private String url;

        public String getChengHao() {
            return chengHao;
        }

        public void setChengHao(String chengHao) {
            this.chengHao = chengHao;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJianJie() {
            return jianJie;
        }

        public void setJianJie(String jianJie) {
            this.jianJie = jianJie;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShanChang() {
            return shanChang;
        }

        public void setShanChang(String shanChang) {
            this.shanChang = shanChang;
        }

        public String getTouXiang() {
            return touXiang;
        }

        public void setTouXiang(String touXiang) {
            this.touXiang = touXiang;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
