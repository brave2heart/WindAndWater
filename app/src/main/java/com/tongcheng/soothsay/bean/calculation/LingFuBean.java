package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 宋家任 on 2016/12/7.
 * 灵符bean
 */

public class LingFuBean implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;
    private String isfree;
    private String jcprice;
    private String kgprice;
    private String lfId;
    private String name;
    private String price;
    private String status;

    public String getIsfree() {
        return isfree;
    }

    public void setIsfree(String isfree) {
        this.isfree = isfree;
    }

    public String getJcprice() {
        return jcprice;
    }

    public void setJcprice(String jcprice) {
        this.jcprice = jcprice;
    }

    public String getKgprice() {
        return kgprice;
    }

    public void setKgprice(String kgprice) {
        this.kgprice = kgprice;
    }

    public String getLfId() {
        return lfId;
    }

    public void setLfId(String lfId) {
        this.lfId = lfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
