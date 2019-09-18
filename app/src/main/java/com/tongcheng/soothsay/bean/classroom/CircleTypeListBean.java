package com.tongcheng.soothsay.bean.classroom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gubr on 2016/12/30.
 */
//共修圈子暂时  就只需要用这个就可以了.
public class CircleTypeListBean {

    /**
     * count : 23
     * facePic : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=80361480316263004.jpg
     * id : 1
     * name : 生活感悟
     * redirectUrl :
     */

    @SerializedName("count")
    private String count;
    @SerializedName("facePic")
    private String facePic;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("redirectUrl")
    private String redirectUrl;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
