package com.tongcheng.soothsay.bean.calculation;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.tongcheng.soothsay.R;
import com.tongcheng.soothsay.utils.ResUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozhihuatong on 2016/12/1.
 */

public class QfDxBean implements Serializable{
    private static final long serialVersionUID = 4L;

    public QfDxBean(List<QfgpListBean> gpList, String qfContent, String qfCxz, String qfDate, String qfDx, String qfTotalTimes, List<QfDetailBean> qfDetail, ResUtil resUtil) {
        this.gpList = gpList;
        this.qfContent = qfContent;
        this.qfCxz = qfCxz;
        this.qfDate = qfDate;
        this.qfDx = qfDx;
        this.qfTotalTimes = qfTotalTimes;
        this.qfDetail = qfDetail;
        mResUtil = resUtil;
    }

    public QfDxBean() {
    }

    @SerializedName("gpList")
    private List<QfgpListBean> gpList;
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
    /**
     * qfDate : 1480584748000
     * qfGp : 苹果
     */

    @SerializedName("qfDetail")
    private List<QfDetailBean> qfDetail;
    private transient ResUtil mResUtil;

    public int getImgSrc(){
//        context.getResources().getIdentifier()
        if (mResUtil==null) {
            mResUtil = ResUtil.newInstance();
        }
        return mResUtil.getGodImageRes(qfDx);
    }

    public int getImgSrc(Context context){
//        context.getResources().getIdentifier()
        if (mResUtil==null) {
            mResUtil = ResUtil.newInstance();
        }
        return mResUtil.getGodImageRes(qfDx);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<QfgpListBean> getGpList() {
        if (gpList==null)return new ArrayList<QfgpListBean>();
        return gpList;
    }

    public void setGpList(List<QfgpListBean> gpList) {
        this.gpList = gpList;
    }

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

    public int getIntQfTotalTimes(){
        if (qfTotalTimes==null||qfDx.isEmpty())
            return -1;
        return Integer.valueOf(qfTotalTimes);
    }

    public void setQfTotalTimes(String qfTotalTimes) {
        this.qfTotalTimes = qfTotalTimes;
    }

    public List<QfDetailBean> getQfDetail() {
        if (qfDetail==null) return new ArrayList<QfDetailBean>();
        return qfDetail;
    }

    public void setQfDetail(List<QfDetailBean> qfDetail) {
        this.qfDetail = qfDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QfDxBean qfDxBean = (QfDxBean) o;

        return qfDx.equals(qfDxBean.qfDx);

    }

    @Override
    public int hashCode() {
        return qfDx.hashCode();
    }
}
