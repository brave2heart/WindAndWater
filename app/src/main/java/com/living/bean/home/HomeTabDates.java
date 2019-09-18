package com.living.bean.home;

/**
 * Created by weihao on 2017/12/18.
 */

public class HomeTabDates {
    private int image;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public HomeTabDates(int image, String name) {

        this.image = image;
        this.name = name;
    }
}
