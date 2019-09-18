package com.living.bean.xuetang;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/12.
 */

public class ZhuanLan extends T {
    private int image;
    private String title;
    private String name;
    private String duration;
    private String label;
    private String leftPrice;
    private String rightPrice;

    public ZhuanLan(int image, String title, String name, String duration, String label, String leftPrice, String rightPrice) {
        this.image = image;
        this.title = title;
        this.name = name;
        this.duration = duration;
        this.label = label;
        this.leftPrice = leftPrice;
        this.rightPrice = rightPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
