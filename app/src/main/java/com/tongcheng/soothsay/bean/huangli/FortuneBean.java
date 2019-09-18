package com.tongcheng.soothsay.bean.huangli;

import java.io.Serializable;

/**
 * Created by Steven on 16/11/9.
 */

//个人运势
public class FortuneBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String score;       //得分
    private String userName;    //用户昵称
    private String birthday;    //生日
    private String date ;       //日期  2016年11月08日 星期二
    private String oldDate;     //农历  丙申年十月初十
    private String ganzhi;      //干支  丙申年 乙亥月 乙未年
    private String zhengchong;  //丑牛
    private String yi;          //宜  盖屋 移徙 安床 入宅 开市 开仓 祭祀 祈福 酬神 出行 求财 见贵 订婚 嫁娶
    private String ji;          //忌  冲鸡 煞西 时冲乙酉 勾陈 天地
    private String Fortune;     //今日运势
    private String luckyColor;  //幸运色
    private String imgUrl;
    private int currMon;        //当前月


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOldDate() {
        return oldDate;
    }

    public void setOldDate(String oldDate) {
        this.oldDate = oldDate;
    }

    public String getGanzhi() {
        return ganzhi;
    }

    public void setGanzhi(String ganzhi) {
        this.ganzhi = ganzhi;
    }

    public String getZhengchong() {
        return zhengchong;
    }

    public void setZhengchong(String zhengchong) {
        this.zhengchong = zhengchong;
    }

    public String getYi() {
        return yi;
    }

    public void setYi(String yi) {
        this.yi = yi;
    }

    public String getJi() {
        return ji;
    }

    public void setJi(String ji) {
        this.ji = ji;
    }

    public String getFortune() {
        return Fortune;
    }

    public void setFortune(String fortune) {
        Fortune = fortune;
    }

    public String getLuckyColor() {
        return luckyColor;
    }

    public void setLuckyColor(String luckyColor) {
        this.luckyColor = luckyColor;
    }

    public int getCurrMon() {
        return currMon;
    }

    public void setCurrMon(int currMon) {
        this.currMon = currMon;
    }
}
