package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by ALing on 2016/11/28 0028.
 */

public class MonthYunCheng {

    /**
     * date : 2016年11月
     * name : 白羊座
     * all : 本月的运势有两条脉络。一、上旬，旧伤疤被揭，带着心痛和对手较劲，到头来却发现，所谓的对手是个假想敌。小提醒，你真正应该学习的，是如何和自己的伤痛达成和解，如果做不到，下一次你还是容易投射到其他事物身上。中旬，心态开始放松，想到更多以后的事，眼前的得失似乎变得不那么重要了。二，和事业有关。火星上旬结束在事业宫的运行，对事业的重视、焦急的心态开始放松，金星随之入事业宫，事业发展的良机出现，不得不感叹，很多东西是急不来的。
     * happyMagic :
     * health : 上旬的情绪容易偏激，但越往后，随着水星的转宫，理性的力量会对其有所挽回。下旬，运动能量提升，适合开展身体锻炼，受土星影响，通常考验耐力的运动效果更佳。
     * love : 上旬的相处，基本是怎么舒服怎么来，有坦诚交流的时机，当然了，以你的直肠子，说出来话可能不太好听。中旬起，要接受现实的考验，门当户对这种东西就是这个时候要考虑的。

     * money : 上旬的财务状况比较自由轻松，中旬起变得紧张，往往是你有大布局，打算参与大项目的投资，由于冥王星近年在事业宫，参与这种项目通常导向两个极端，财富成倍数增长，或被打入死牢。所以建议你要考虑后路。

     * month : 11
     * work : 参考总运势第二点。要补充的是，中旬之后，无论心态还是机会，都比较好。不过，人际方面，由于火星入了人际宫，越是想获得大家支持，就越容易和同事发脾气啊。

     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String all;
    private String happyMagic;
    private String health;
    private String love;
    private String money;
    private int month;
    private String work;
    private String resultcode;
    private int error_code;
    private String reason;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getHappyMagic() {
        return happyMagic;
    }

    public void setHappyMagic(String happyMagic) {
        this.happyMagic = happyMagic;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
