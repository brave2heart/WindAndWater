package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by bozhihuatong on 2016/11/28.
 */
@Entity
public class BaziUserBean implements Serializable {
    private static final long serialVersionUID = 2L;

    @Override
    public String toString() {
        return "BaziUserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", date='" + date + '\'' +
                ", icon=" + icon +
                '}';
    }

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String sex;

    private String date;        //yyyy.MM.dd.HH

    private int icon;

//    下面这些是自动生成的。

    @Generated(hash = 986589107)
    public BaziUserBean(Long id, String name, String sex, String date, int icon) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.date = date;
        this.icon = icon;
    }

    @Generated(hash = 284782555)
    public BaziUserBean() {
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
}
