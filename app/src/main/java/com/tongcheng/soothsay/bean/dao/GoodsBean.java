package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by 宋家任 on 2016/12/23.
 * 商品id
 */
@Entity
public class GoodsBean implements Serializable{
    private static final long serialVersionUID = 3L;

    @Id(autoincrement = true)
    private Long id;

    private String goodsId;         //商品id
    private String amount;          //商品数量
    private String goodsName;       //商品名
    private String price;           //价格
    private String imgUrl;           //商品图片
    private String integral;        //积分

    @Generated(hash = 420674893)
    public GoodsBean(Long id, String goodsId, String amount, String goodsName,
            String price, String imgUrl, String integral) {
        this.id = id;
        this.goodsId = goodsId;
        this.amount = amount;
        this.goodsName = goodsName;
        this.price = price;
        this.imgUrl = imgUrl;
        this.integral = integral;
    }

    @Generated(hash = 1806305570)
    public GoodsBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntegral() {
        return this.integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
