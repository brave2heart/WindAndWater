package com.living.bean.xuetang;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/13.
 */

public class QinSuan extends T {
    private int image;
    private String name;
    private String labelGX;
    private String labelZY;
    private int jdImage;
    private String jdNum;
    private int plImage;
    private String plNum;
    private int cyImage;
    private String cyNum;

    public QinSuan(int image, String name, String labelGX, String labelZY, int jdImage, String jdNum, int plImage, String plNum, int cyImage, String cyNum) {
        this.image = image;
        this.name = name;
        this.labelGX = labelGX;
        this.labelZY = labelZY;
        this.jdImage = jdImage;
        this.jdNum = jdNum;
        this.plImage = plImage;
        this.plNum = plNum;
        this.cyImage = cyImage;
        this.cyNum = cyNum;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelGX() {
        return labelGX;
    }

    public void setLabelGX(String labelGX) {
        this.labelGX = labelGX;
    }

    public String getLabelZY() {
        return labelZY;
    }

    public void setLabelZY(String labelZY) {
        this.labelZY = labelZY;
    }

    public int getJdImage() {
        return jdImage;
    }

    public void setJdImage(int jdImage) {
        this.jdImage = jdImage;
    }

    public String getJdNum() {
        return jdNum;
    }

    public void setJdNum(String jdNum) {
        this.jdNum = jdNum;
    }

    public int getPlImage() {
        return plImage;
    }

    public void setPlImage(int plImage) {
        this.plImage = plImage;
    }

    public String getPlNum() {
        return plNum;
    }

    public void setPlNum(String plNum) {
        this.plNum = plNum;
    }

    public int getCyImage() {
        return cyImage;
    }

    public void setCyImage(int cyImage) {
        this.cyImage = cyImage;
    }

    public String getCyNum() {
        return cyNum;
    }

    public void setCyNum(String cyNum) {
        this.cyNum = cyNum;
    }
}
