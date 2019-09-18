package com.tongcheng.soothsay.bean.pay;

import java.io.Serializable;

/**
 * Created by Steven on 16/12/5.
 * 订单请求
 */
public class OrdersRequestBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String goodsName;   //商品名字
    private String token;
    private String transType;      //"LF":"灵符" 、 "SC":"商城" 、 "XYD":"许愿灯" 、"FSC":"放生池"、"JFDH":"积分兑换"
    private String payType;     //支付类型。WX, ALIPAY   "WX"："微信"，  "ALIPAY"："支付宝"
    private String totalPrice;  //订单价格。单位分
    private String extra;

    /**
     *自定义额外参数，根据transTypep定义。格式为json字符串。
     1，当transType等于“LF”，格式如下：
     {
     "lfName":"灵符名称",//灵符名称
     "amount":"1",//购买数量
     "buyType":"1"
     //购买类型：1：请符，2：开光，3：加持
     }
     2，当transType等于“SC”，格式如下：
     {
         "merchandiseIds":"{2,3}",//商品id数组
         "amounts":"{1,2}"//商品数量数组
     }
     3，当transType等于“XYD”，格式如下：
     {
         "xydName":"婚姻灯",//许愿灯名称
         "sex":"1",//性别(1：男；2：女)
         "bornDate":"1990-01-01"//出生日期,
         "username":"张三",//点灯人
         "content":"花好月圆",//许愿内容
         "expiryType":"1",//点灯期限(0：90天；1：180天；)
         "isOpen":"1"//是否公开（0：公开；1：不公开）
     }
     4，当transType等于“FSC”，格式如下：
     [{
     "amount":"2",//商品数量
     "merchandiseId":"1"//商品id
     }]
     5，当transType等于“JFDH”，格式如下：
     {
         "jfStoreId":"1"//积分兑换商品的id
     }

     * @return
     */

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
