package com.tongcheng.soothsay.bean.classroom;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 宋家任 on 2016/12/26.
 * 购物车实体bean
 */

public class ShopCartBean implements Parcelable {


    /**
     * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=8411481683281304.png
     * jf : 0
     * jfAmt : 0.0
     * merchandiseId : 4
     * name : 生肖本命挂件
     * price : 150.0
     * redirectUrl : http://127.0.0.1:8080/store/getStoreInfo.do?storeId=4
     * shopCartCount : 1
     * yinyu : 护生化煞保平安
     */

    private String facePic;
    private String jf;
    private String jfAmt;
    private String merchandiseId;
    private String name;
    private String price;
    private String redirectUrl;
    private String shopCartCount;
    private String yinyu;
    private int payCount = 0;   //购买数量
    private boolean isCheck;//是否选中
    private double totalPrice;//总价（单价X数量）

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    /**
     * @return 购买数量
     */
    public int getPayCount() {
        return payCount;
    }

    /**
     * 购买数量
     *
     * @param payCount
     */
    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    /**
     * @return 图片封面
     */
    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    /**
     * @return 可使用积分
     */
    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    /**
     * @return 积分兑换的金额
     */
    public String getJfAmt() {
        return jfAmt;
    }

    public void setJfAmt(String jfAmt) {
        this.jfAmt = jfAmt;
    }

    /**
     * @return 商品id
     */
    public String getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(String merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    /**
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 商品价格
     */
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return 商品详情跳转地址
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * @return 购物车数量
     */
    public String getShopCartCount() {
        return shopCartCount;
    }

    public void setShopCartCount(String shopCartCount) {
        this.shopCartCount = shopCartCount;
    }

    /**
     * @return 商品引语
     */
    public String getYinyu() {
        return yinyu;
    }

    public void setYinyu(String yinyu) {
        this.yinyu = yinyu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.facePic);
        dest.writeString(this.jf);
        dest.writeString(this.jfAmt);
        dest.writeString(this.merchandiseId);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.redirectUrl);
        dest.writeString(this.shopCartCount);
        dest.writeString(this.yinyu);
        dest.writeInt(this.payCount);
    }

    public ShopCartBean() {
    }

    protected ShopCartBean(Parcel in) {
        this.facePic = in.readString();
        this.jf = in.readString();
        this.jfAmt = in.readString();
        this.merchandiseId = in.readString();
        this.name = in.readString();
        this.price = in.readString();
        this.redirectUrl = in.readString();
        this.shopCartCount = in.readString();
        this.yinyu = in.readString();
        this.payCount = in.readInt();
    }

    public static final Creator<ShopCartBean> CREATOR = new Creator<ShopCartBean>() {
        @Override
        public ShopCartBean createFromParcel(Parcel source) {
            return new ShopCartBean(source);
        }

        @Override
        public ShopCartBean[] newArray(int size) {
            return new ShopCartBean[size];
        }
    };
}
