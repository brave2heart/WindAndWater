package com.tongcheng.soothsay.bean.pay;

/**
 * Created by Steven on 16/12/5.
 * 订单结果
 */
public class OrdersResultBean {

    private String sign;        //微信跟 支付宝都有
    private String timestamp;   //微信跟 支付宝都有

    private String out_trade_no; //微信跟 支付宝都有   订单编号

    /**
     * ==================
     * 支付宝的看这里
     * ==================
     */
    private String app_id;      //支付宝分配给开发者的应用ID

    private String method;      //接口名称
    private String charset;     //请求使用的编码格式，utf-8
    private String version;     //调用的接口版本，固定为：1.0
    private String notify_url;  //回调地址
    private String sign_type;   //商户生成签名字符串所使用的签名算法类型，目前支持RSA
    private String biz_content; //业务请求参数的集合参数
    /**
     * ==================
     * 微信支付的看这里
     * ==================
     */
    private String appid;       //微信开放平台审核通过的应用APPID

    private String partnerid;   //微信支付分配的商户号
    private String prepayid;    //微信返回的支付交易会话ID
    private String noncestr;    //随机字符串，不长于32位。
    //package           固定值Sign=WXPay

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(String biz_content) {
        this.biz_content = biz_content;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
}
