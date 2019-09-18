package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gubr on 2016/12/27.
 */

public class NewsBean {

    /**
     * cover : http://rgb366.99166.com/UpLoadFile/UploadFile/201610602214961.jpg
     * date : 1475683200000
     * id : 70
     * redirectUrl : http://120.76.219.201:8080/publish/news/viewNews.do?id=70
     * showtype : 1
     * source : 网络收集
     * title : 要想旺财，先补财库
     * typeId : 1
     */

    @SerializedName("cover")
    private String cover;
    @SerializedName("date")
    private String date;
    @SerializedName("id")
    private String id;
    @SerializedName("redirectUrl")
    private String redirectUrl;
    @SerializedName("showtype")
    private String showtype;
    @SerializedName("source")
    private String source;
    @SerializedName("title")
    private String title;
    @SerializedName("typeId")
    private String typeId;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getShowtype() {
        return showtype;
    }

    public int getIntShowtype(){
        return Integer.valueOf(showtype);
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}

