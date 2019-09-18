package com.tongcheng.soothsay.bean.classroom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gubr on 2016/12/30.
 */

public class CircleListBean {
    /**
     * circleId : 7
     * content : Test
     * createTime : 1483078099000
     * isTop : 0
     * redirectUrl : http://120.76.219.201:8080/publish/circle/viewCircle.do?circleId=7
     */

    @SerializedName("circleId")
    private String circleId;
    @SerializedName("content")
    private String content;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("isTop")
    private String isTop;
    @SerializedName("redirectUrl")
    private String redirectUrl;

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
