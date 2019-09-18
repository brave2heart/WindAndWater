package com.tongcheng.soothsay.bean.classroom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/28.
 * 大师商城首页
 */

public class ClassRoomBean {

    private List<AdsBean> ads;
    private List<CirclesBean> circles;
    private List<DsBean> ds;
    private List<StoresBean> stores;

    /**
     * 轮播图广告
     * @return
     */
    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    /**
     * 共修圈子
     * @return
     */
    public List<CirclesBean> getCircles() {
        return circles;
    }

    public void setCircles(List<CirclesBean> circles) {
        this.circles = circles;
    }

    /**
     * 大师
     * @return
     */
    public List<DsBean> getDs() {
        return ds;
    }

    public void setDs(List<DsBean> ds) {
        this.ds = ds;
    }

    /**
     * 商城商品，最多四个
     * @return
     */
    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class AdsBean {
        /**
         * cover : http://127.0.0.1:8080/file/getLocalFile.do?fileType=cacheImages&amp;fileName=84621480060213812.jpg
         * title : 大师讲演广告五
         * type : 04
         * url : www
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

    public static class CirclesBean {
        /**
         * count : 2
         * facePic : http://127.0.0.1:8080/file/getLocalFile.do?fileType=cacheImages&amp;fileName=19551479979058947.jpg
         * id : 1
         * name : 生活感悟
         * redirectUrl :
         */

        private String count;
        private String facePic;
        private String id;
        private String name;

        @SerializedName("redirectUrl")
        private String redirectUrl;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFacePic() {
            return facePic;
        }

        public void setFacePic(String facePic) {
            this.facePic = facePic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }
        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }
    }

    public static class DsBean {
        /**
         * chengHao : 大师
         * id : 1
         * jianJie : 精通风水，八字
         * name : 风水大师
         * shanChang : 风水，算命
         * touXiang : http://127.0.0.1:8080/file/getLocalFile.do?fileType=cacheImages&fileName=39351480062164727.jpg
         */

        private String chengHao;
        private String id;
        private String jianJie;
        private String name;
        private String shanChang;
        private String touXiang;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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
    }

    public static class StoresBean {
        /**
         * facePic : http://127.0.0.1:8080/file/getLocalFile.do?fileType=cacheImages&amp;fileName=22161480062924081.jpg
         * merchandiseId : 5
         * name : 本命佛
         * price : 200.0
         * redirectUrl :
         * yinyu : 平安吉祥
         */

        private String facePic;
        private String merchandiseId;
        private String name;
        private String price;
        private String redirectUrl;
        private String yinyu;

        public String getFacePic() {
            return facePic;
        }

        public void setFacePic(String facePic) {
            this.facePic = facePic;
        }

        public String getMerchandiseId() {
            return merchandiseId;
        }

        public void setMerchandiseId(String merchandiseId) {
            this.merchandiseId = merchandiseId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getYinyu() {
            return yinyu;
        }

        public void setYinyu(String yinyu) {
            this.yinyu = yinyu;
        }
    }
}
