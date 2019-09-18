package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Steven on 16/11/22.
 */

@Entity
public class CityBean {

    @Id(autoincrement = true)
    private Long id;

    private String city;

    @Generated(hash = 1215789662)
    public CityBean(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    @Generated(hash = 273649691)
    public CityBean() {
    }

    public CityBean(String city) {
        this.city = city;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
