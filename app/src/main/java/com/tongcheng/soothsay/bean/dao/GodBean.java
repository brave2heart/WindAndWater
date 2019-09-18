package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by bozhihuatong on 2016/11/28.
 */

@Entity
public class GodBean implements Serializable {
    private static final long serialVersionUID = 4L;

    @Id
    private Long id;


    /**
     * 神名类型 1是道家，2是佛家
     */
    private  int type;

    private String name;

    private int imgSrc;
    /**
     * 祈福类型
     */
    private String prayTitle;
    /**
     * 神明简介
     */
    private String intro;

    /**
     * 是否已经在祈福
     */
    private boolean isPraying;

    @Generated(hash = 1342586540)
    public GodBean(Long id, int type, String name, int imgSrc, String prayTitle,
            String intro, boolean isPraying) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.imgSrc = imgSrc;
        this.prayTitle = prayTitle;
        this.intro = intro;
        this.isPraying = isPraying;
    }

    @Generated(hash = 801426793)
    public GodBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getPrayTitle() {
        return this.prayTitle;
    }

    public void setPrayTitle(String prayTitle) {
        this.prayTitle = prayTitle;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean getIsPraying() {
        return this.isPraying;
    }

    public void setIsPraying(boolean isPraying) {
        this.isPraying = isPraying;
    }


}
