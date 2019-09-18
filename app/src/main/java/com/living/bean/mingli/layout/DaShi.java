package com.living.bean.mingli.layout;

import com.tongcheng.soothsay.bean.calculation.MingLiBean;

import java.util.List;

/**
 * Created by weihao on 2018/1/11.
 */

/*
* 命理，大师推荐数据bean
* */
public class DaShi {



    private List<MingLiBean.DsBean> ds;
    private String dashiZhouYi;
    private String dashiGX;
    private String dashiZhuanYe;
    private String dashiHP;
    private int dashiJDImage;
    private String dashiJDNum;
    private int dashiPLImage;
    private String dashiPLNum;
    private int dashiCYImage;
    private String dashiCYNum;

    public DaShi(List<MingLiBean.DsBean> ds, String dashiZhouYi, String dashiGX, String dashiZhuanYe, String dashiHP, int dashiJDImage, String dashiJDNum, int dashiPLImage, String dashiPLNum, int dashiCYImage, String dashiCYNum) {
        this.ds = ds;
        this.dashiZhouYi = dashiZhouYi;
        this.dashiGX = dashiGX;
        this.dashiZhuanYe = dashiZhuanYe;
        this.dashiHP = dashiHP;
        this.dashiJDImage = dashiJDImage;
        this.dashiJDNum = dashiJDNum;
        this.dashiPLImage = dashiPLImage;
        this.dashiPLNum = dashiPLNum;
        this.dashiCYImage = dashiCYImage;
        this.dashiCYNum = dashiCYNum;
    }

    public List<MingLiBean.DsBean> getDs() {
        return ds;
    }

    public void setDs(List<MingLiBean.DsBean> ds) {
        this.ds = ds;
    }

    public String getDashiZhouYi() {
        return dashiZhouYi;
    }

    public void setDashiZhouYi(String dashiZhouYi) {
        this.dashiZhouYi = dashiZhouYi;
    }

    public String getDashiGX() {
        return dashiGX;
    }

    public void setDashiGX(String dashiGX) {
        this.dashiGX = dashiGX;
    }

    public String getDashiZhuanYe() {
        return dashiZhuanYe;
    }

    public void setDashiZhuanYe(String dashiZhuanYe) {
        this.dashiZhuanYe = dashiZhuanYe;
    }

    public String getDashiHP() {
        return dashiHP;
    }

    public void setDashiHP(String dashiHP) {
        this.dashiHP = dashiHP;
    }

    public int getDashiJDImage() {
        return dashiJDImage;
    }

    public void setDashiJDImage(int dashiJDImage) {
        this.dashiJDImage = dashiJDImage;
    }

    public String getDashiJDNum() {
        return dashiJDNum;
    }

    public void setDashiJDNum(String dashiJDNum) {
        this.dashiJDNum = dashiJDNum;
    }

    public int getDashiPLImage() {
        return dashiPLImage;
    }

    public void setDashiPLImage(int dashiPLImage) {
        this.dashiPLImage = dashiPLImage;
    }

    public String getDashiPLNum() {
        return dashiPLNum;
    }

    public void setDashiPLNum(String dashiPLNum) {
        this.dashiPLNum = dashiPLNum;
    }

    public int getDashiCYImage() {
        return dashiCYImage;
    }

    public void setDashiCYImage(int dashiCYImage) {
        this.dashiCYImage = dashiCYImage;
    }

    public String getDashiCYNum() {
        return dashiCYNum;
    }

    public void setDashiCYNum(String dashiCYNum) {
        this.dashiCYNum = dashiCYNum;
    }
}
