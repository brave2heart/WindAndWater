package com.living.bean.mingli.layout;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/9.
 */
    /*
    * 轮播图下面8个条目
    * */

public class HeadItem  extends T {


    private int image;
    private String imageName;

    public HeadItem(int image, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }

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
}
