package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/2.
 */

public class QfDxDetailListBean implements Serializable {
    private static final long serialVersionUID = 8L;


    /**
     * gpList : []
     * qfContent : 平安
     * qfCxz : 5
     * qfDate : 1480559408000
     * qfDetail : [{"qfDate":"1480584748000","qfGp":"苹果"}]
     * qfDx : Test
     * qfTotalTimes : 0
     */

    @SerializedName("qfContent")
    private String qfContent;
    @SerializedName("qfCxz")
    private String qfCxz;
    @SerializedName("qfDate")
    private String qfDate;
    @SerializedName("qfDx")
    private String qfDx;
    @SerializedName("qfTotalTimes")
    private String qfTotalTimes;
    @SerializedName("gpList")
    private List<QfgpListBean> gpList;
    @SerializedName("qfDetail")
    private List<QfDetailBean> qfDetail;

    public String getQfContent() {
        return qfContent;
    }

    public void setQfContent(String qfContent) {
        this.qfContent = qfContent;
    }

    public String getQfCxz() {
        return qfCxz;
    }

    public void setQfCxz(String qfCxz) {
        this.qfCxz = qfCxz;
    }

    public String getQfDate() {
        return qfDate;
    }

    public void setQfDate(String qfDate) {
        this.qfDate = qfDate;
    }

    public String getQfDx() {
        return qfDx;
    }

    public void setQfDx(String qfDx) {
        this.qfDx = qfDx;
    }

    public String getQfTotalTimes() {
        return qfTotalTimes;
    }

    public void setQfTotalTimes(String qfTotalTimes) {
        this.qfTotalTimes = qfTotalTimes;
    }

    public List<?> getGpList() {
        return gpList;
    }

    public void setGpList(List<QfgpListBean> gpList) {
        this.gpList = gpList;
    }

    public List<QfDetailBean> getQfDetail() {
        return qfDetail;
    }

    public void setQfDetail(List<QfDetailBean> qfDetail) {
        this.qfDetail = qfDetail;
    }

}
