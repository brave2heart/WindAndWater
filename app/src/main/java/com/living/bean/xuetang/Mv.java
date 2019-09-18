package com.living.bean.xuetang;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/15.
 */

public class Mv extends T {

    private int mvImage;
    private String title;
    private String name;
    private String duration;
    private String label;
    private String number;
    private String leftPrice;
    private String rightPrice;

    public Mv(int mvImage, String title, String name, String duration, String label, String number, String leftPrice, String rightPrice) {
        this.mvImage = mvImage;
        this.title = title;
        this.name = name;
        this.duration = duration;
        this.label = label;
        this.number = number;
        this.leftPrice = leftPrice;
        this.rightPrice = rightPrice;
    }

    public int getMvImage() {
        return mvImage;
    }

    public void setMvImage(int mvImage) {
        this.mvImage = mvImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLeftPrice() {
        return leftPrice;
    }

    public void setLeftPrice(String leftPrice) {
        this.leftPrice = leftPrice;
    }

    public String getRightPrice() {
        return rightPrice;
    }

    public void setRightPrice(String rightPrice) {
        this.rightPrice = rightPrice;
    }
}
