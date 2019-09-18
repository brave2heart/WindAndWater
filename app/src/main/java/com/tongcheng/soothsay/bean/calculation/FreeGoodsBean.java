package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by Steven on 16/12/1.
 */

public class FreeGoodsBean {

    /**
     * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=44011480644588338.png
     * fsStoreId : 2
     * name : 金鱼
     * pinyin : jinyu
     * price : 1.00
     * sort : 1
     */

    private String facePic;
    private String fsStoreId;
    private String name;
    private String pinyin;
    private String price;
    private String sort;
    private int payCount = 0;   //购买数量

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public String getFsStoreId() {
        return fsStoreId;
    }

    public void setFsStoreId(String fsStoreId) {
        this.fsStoreId = fsStoreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }
}
