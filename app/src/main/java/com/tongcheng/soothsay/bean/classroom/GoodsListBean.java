package com.tongcheng.soothsay.bean.classroom;

import java.util.List;

/**
 * Created by Steven on 17/1/3.
 */

public class GoodsListBean {

    private List<StoresBean> stores;

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class StoresBean {
        /**
         * categoryName : 生肖流年
         * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=61111481683213401.png
         * merchandiseId : 5
         * name : 2017风生水属鼠千手观音琉璃车挂
         * price : 1.01
         * redirectUrl : http://120.76.219.201:8080/html/gooddetails.html?storeId=5
         * yinyu : 开运转运, 驱祸辟邪, 旺财旺事业
         */

        private String categoryName;
        private String facePic;
        private String merchandiseId;
        private String name;
        private String price;
        private String redirectUrl;
        private String yinyu;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

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
