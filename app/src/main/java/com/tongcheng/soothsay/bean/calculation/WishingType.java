package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by ALing on 2016/12/7 0007.
 */

public class WishingType {
    private int id;
    private String name;
    private int sex;
    private String bornDate;
    private String content;
    private int isOpen;
    private int expiryType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(int expiryType) {
        this.expiryType = expiryType;
    }
}
