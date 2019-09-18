package com.tongcheng.soothsay.bean.calculation;

import android.widget.TextView;

/**
 * Created by 宋家任 on 2016/11/9.
 * 大德符运实体bean(测试用)
 */

public class DadeBean {
    private String tvLeft;//左边的攻台符文名
    private String tvCenter;//中间的攻台符文名
    private String tvRight;//右边的攻台符文名
    private String tvDescLeft;//左边的灵符介绍
    private String tvDescCenter;//中间的灵符介绍
    private String tvDescRight;//右边的灵符介绍
    private String tvGxLeft;//左边的灵符功效
    private String tvGxCenter;//中间的灵符功效
    private String tvGxRight;//右边的灵符功效

    public DadeBean(String tvLeft, String tvCenter, String tvRight,
                    String tvDescLeft, String tvDescCenter, String tvDescRight,
                    String tvGxLeft, String tvGxCenter, String tvGxRight) {
        this.tvLeft = tvLeft;
        this.tvCenter = tvCenter;
        this.tvRight = tvRight;
        this.tvDescLeft = tvDescLeft;
        this.tvDescCenter = tvDescCenter;
        this.tvDescRight = tvDescRight;
        this.tvGxLeft = tvGxLeft;
        this.tvGxCenter = tvGxCenter;
        this.tvGxRight = tvGxRight;
    }

    public String getTvDescLeft() {
        return tvDescLeft;
    }

    public void setTvDescLeft(String tvDescLeft) {
        this.tvDescLeft = tvDescLeft;
    }

    public String getTvDescCenter() {
        return tvDescCenter;
    }

    public void setTvDescCenter(String tvDescCenter) {
        this.tvDescCenter = tvDescCenter;
    }

    public String getTvDescRight() {
        return tvDescRight;
    }

    public void setTvDescRight(String tvDescRight) {
        this.tvDescRight = tvDescRight;
    }

    public String getTvGxLeft() {
        return tvGxLeft;
    }

    public void setTvGxLeft(String tvGxLeft) {
        this.tvGxLeft = tvGxLeft;
    }

    public String getTvGxCenter() {
        return tvGxCenter;
    }

    public void setTvGxCenter(String tvGxCenter) {
        this.tvGxCenter = tvGxCenter;
    }

    public String getTvGxRight() {
        return tvGxRight;
    }

    public void setTvGxRight(String tvGxRight) {
        this.tvGxRight = tvGxRight;
    }


    public String getTvRight() {
        return tvRight;
    }

    public void setTvRight(String tvRight) {
        this.tvRight = tvRight;
    }

    public String getTvCenter() {
        return tvCenter;
    }

    public void setTvCenter(String tvCenter) {
        this.tvCenter = tvCenter;
    }


    public String getTvLeft() {
        return tvLeft;
    }

    public void setTvLeft(String tvLeft) {
        this.tvLeft = tvLeft;
    }
}
