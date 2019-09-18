package com.tongcheng.soothsay.bean.classroom;

import com.living.bean.mingli.T;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商城首页实体
 */

public class ShopHomeBean {

    private List<AdsBean> ads;
    private List<StoreInfosBean> storeInfos;

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<StoreInfosBean> getStoreInfos() {
        return storeInfos;
    }

    public void setStoreInfos(List<StoreInfosBean> storeInfos) {
        this.storeInfos = storeInfos;
    }

    public static class AdsBean {
        /**
         * cover : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=82991480402999565.jpg
         * title : 商城首页广告一
         * type : 06
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

    public static class StoreInfosBean extends T {
        /**
         * sortId : 1
         * sortName : 生肖流年
         * stores : [{"facePic":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=80371480318040896.jpg","merchandiseId":"5","name":"本命佛","price":"200.0","redirectUrl":"","yinyu":"平安吉祥"},{"facePic":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=42721480318052092.jpg","merchandiseId":"7","name":"本命佛","price":"200.0","redirectUrl":"","yinyu":"平安吉祥"},{"facePic":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=54561480318060272.jpg","merchandiseId":"4","name":"生肖本命挂件","price":"150.0","redirectUrl":"","yinyu":"护生化煞保平安"},{"facePic":"http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=93311480318070262.jpg","merchandiseId":"6","name":"生肖本命挂件","price":"150.0","redirectUrl":"","yinyu":"护生化煞保平安"}]
         */

        private String sortId;
        private String sortName;
        private List<StoresBean> stores;

        public String getSortId() {
            return sortId;
        }

        public void setSortId(String sortId) {
            this.sortId = sortId;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public List<StoresBean> getStores() {
            return stores;
        }

        public void setStores(List<StoresBean> stores) {
            this.stores = stores;
        }

        public static class StoresBean extends T {
            /**
             * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=80371480318040896.jpg
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
}
