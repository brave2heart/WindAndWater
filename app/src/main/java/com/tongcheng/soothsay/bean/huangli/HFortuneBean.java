package com.tongcheng.soothsay.bean.huangli;

import java.io.Serializable;

/**
 * Created by Steven on 16/11/9.
 */

public class HFortuneBean implements Serializable {


    /**
     * date : 20161109
     * name : 白羊座
     * datetime : 2016年11月09日
     * all : 20%
     * color : 紫色
     * health : 70%
     * love : 20%
     * money : 20%
     * number : 1
     * QFriend : 金牛座
     * summary : 今天你的状态有些低迷，白日梦连篇会导致很多事情被拖延耽搁，工作上也更容易出现各种错误。是你心情非常迷乱的一天，有些人可能会有奇特的梦境。
     * work : 20%
     * resultcode : 200
     * error_code : 0
     */

    private int date;
    private String name;
    private String datetime;
    private String all;
    private String color;
    private String health;
    private String love;
    private String money;
    private int number;
    private String QFriend;
    private String summary;
    private String work;
    private String resultcode;
    private int error_code;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    /**
     * 生成测试数据
     */
    public void exmpleData() {
        date = 20161109;
        name = "白羊座 ";
        datetime = "2016年11月09日";
        all = "97%";
        color = "紫色";
        health = "90%";
        love = "88%";
        money = "78%";
        number = 1;
        QFriend = "金牛座";
        summary = "今天你的状态有些低迷，白日梦连篇会导致很多事情被拖延耽搁，工作上也更容易出现各种错误。是你心情非常迷乱的一天，有些人可能会有奇特的梦境。";
        work = "89%";
        resultcode = "200";
        error_code = 0;
    }
}
