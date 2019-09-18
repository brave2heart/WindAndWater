package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * Created by ALing on 2016/11/23 0023.
 */

public class HeHun implements Serializable{

    /**
     * jieXi : 主无灾无病，一生平安，儿女和睦，无奸无盗，上吉之配。
     * manG : 兑
     * result : 天医婚
     * type : 0
     * womanG : 离
     */

    private String jieXi;
    private String manG;
    private String result;
    private String type;
    private String womanG;

    public String getJieXi() {
        return jieXi;
    }

    public void setJieXi(String jieXi) {
        this.jieXi = jieXi;
    }

    public String getManG() {
        return manG;
    }

    public void setManG(String manG) {
        this.manG = manG;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWomanG() {
        return womanG;
    }

    public void setWomanG(String womanG) {
        this.womanG = womanG;
    }
}
