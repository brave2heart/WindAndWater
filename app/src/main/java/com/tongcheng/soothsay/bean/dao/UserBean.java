package com.tongcheng.soothsay.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 宋家任 on 2016/11/20.
 */
@Entity
public class UserBean {

    private String token;
    private String phone;

    @Id(autoincrement = true)
    private Long id;

    private String ryToken;

    @Generated(hash = 1826115446)
    public UserBean(String token, String phone, Long id, String ryToken) {
        this.token = token;
        this.phone = phone;
        this.id = id;
        this.ryToken = ryToken;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public UserBean(String token) {
        this.token = token;
    }


    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
