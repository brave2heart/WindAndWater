package com.living.bean.mingli.layout;

import com.living.bean.mingli.T;

/**
 * Created by weihao on 2018/1/9.
 */

/*
* 命理，八字排盘等条目
* */

public class FourGrid extends T {

    private String name;
    private String content;
    private int image;

    public FourGrid(String name, String content, int image) {
        this.name = name;
        this.content = content;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
