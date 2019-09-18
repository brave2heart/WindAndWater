package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Steven on 16/12/5.
 */

public class WishTreeBean {
    /**
     * createTime : 1481087384000
     * myname : good
     * sayword : TestTest
     * toname : TestRong
     * xytId : 1
     */

    @SerializedName("createTime")
    private String createTime;
    @SerializedName("myname")
    private String myname;
    @SerializedName("sayword")
    private String sayword;
    @SerializedName("toname")
    private String toname;
    @SerializedName("xytId")
    private String xytId;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getSayword() {
        return sayword;
    }

    public void setSayword(String sayword) {
        this.sayword = sayword;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getXytId() {
        return xytId;
    }

    public void setXytId(String xytId) {
        this.xytId = xytId;
    }
}
