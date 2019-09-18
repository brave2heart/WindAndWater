package com.tongcheng.soothsay.bean.classroom;

import java.util.List;

/**
 * Created by 宋家任 on 2016/11/30.
 * 商城商品列表实体
 */

public class ShopClassifyInfosBean {


    private List<StoresBean> stores;

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class StoresBean {
        /**
         * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=42721480318052092.jpg
         * merchandiseId : 7
         * name : 本命佛
         * price : 200.00
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
