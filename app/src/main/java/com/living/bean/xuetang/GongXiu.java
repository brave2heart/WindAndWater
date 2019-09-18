package com.living.bean.xuetang;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/12.
 */

public class GongXiu extends T {
    private int headimage;
    private String title;
    private String labeltext;
    private int smallDot1;
    private String content1;
    private int smallDot2;
    private String content2;
    private int huatiImage;
    private String huatiContent;
    private int plImage;
    private String plContent;
    private int rightImage;
    private int type;

    public GongXiu(int headimage, String title, String labeltext, int smallDot1, String content1, int smallDot2, String content2, int huatiImage, String huatiContent, int plImage, String plContent, int rightImage, int type) {
        this.headimage = headimage;
        this.title = title;
        this.labeltext = labeltext;
        this.smallDot1 = smallDot1;
        this.content1 = content1;
        this.smallDot2 = smallDot2;
        this.content2 = content2;
        this.huatiImage = huatiImage;
        this.huatiContent = huatiContent;
        this.plImage = plImage;
        this.plContent = plContent;
        this.rightImage = rightImage;
        this.type = type;
    }

    public int getHeadimage() {
        return headimage;
    }

    public void setHeadimage(int headimage) {
        this.headimage = headimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabeltext() {
        return labeltext;
    }

    public void setLabeltext(String labeltext) {
        this.labeltext = labeltext;
    }

    public int getSmallDot1() {
        return smallDot1;
    }

    public void setSmallDot1(int smallDot1) {
        this.smallDot1 = smallDot1;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public int getSmallDot2() {
        return smallDot2;
    }

    public void setSmallDot2(int smallDot2) {
        this.smallDot2 = smallDot2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public int getHuatiImage() {
        return huatiImage;
    }

    public void setHuatiImage(int huatiImage) {
        this.huatiImage = huatiImage;
    }

    public String getHuatiContent() {
        return huatiContent;
    }

    public void setHuatiContent(String huatiContent) {
        this.huatiContent = huatiContent;
    }

    public int getPlImage() {
        return plImage;
    }

    public void setPlImage(int plImage) {
        this.plImage = plImage;
    }

    public String getPlContent() {
        return plContent;
    }

    public void setPlContent(String plContent) {
        this.plContent = plContent;
    }

    public int getRightImage() {
        return rightImage;
    }

    public void setRightImage(int rightImage) {
        this.rightImage = rightImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
