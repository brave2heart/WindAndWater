package com.tongcheng.soothsay.bean.calculation.bazi;

/**
 * Created by weihao on 2017/12/9.
 */

public class ClassRoomGridViewBeans {
    private int image;
    private String imageName;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public ClassRoomGridViewBeans(int image, String imageName) {

        this.image = image;
        this.imageName = imageName;
    }
}
