package com.tongcheng.soothsay.bean.mine;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bozhihuatong on 2016/12/15.
 */

public class JiFenBean {

    /**
     * jf : 600
     * jfstoreId : 1
     * money : 6.0
     */

    @SerializedName("jf")
    private String jf;
    @SerializedName("jfstoreId")
    private String jfstoreId;
    @SerializedName("money")
    private String money;

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getJfstoreId() {
        return jfstoreId;
    }

    public void setJfstoreId(String jfstoreId) {
        this.jfstoreId = jfstoreId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBottonStr(){
        return jf+"积分\n"+money+"元";
    }
}
