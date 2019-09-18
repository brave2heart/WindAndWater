package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by Steven on 16/11/16.
 */
@Entity
public class ZiweiUserBean implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String sex;

    private String date;        //yyyy.MM.dd.HH
    
    private int icon;

    private String gongWZIndex;

    @Generated(hash = 945771314)
    public ZiweiUserBean(Long id, String name, String sex, String date, int icon,
            String gongWZIndex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.icon = icon;
        this.gongWZIndex = gongWZIndex;
    }

    @Generated(hash = 291785972)
    public ZiweiUserBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getGongWZIndex() {
        return gongWZIndex;
    }

    public void setGongWZIndex(String gongWZIndex) {
        this.gongWZIndex = gongWZIndex;
    }
}
