package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * Created by ALing on 2016/12/5 0005.
 */

public class BornBoyOrGirl implements Serializable{

    /**
     * sex : 1
     * huayu : 梅花
     */

    private String sex;
    private String huayu;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHuayu() {
        return huayu;
    }

    public void setHuayu(String huayu) {
        this.huayu = huayu;
    }
}
