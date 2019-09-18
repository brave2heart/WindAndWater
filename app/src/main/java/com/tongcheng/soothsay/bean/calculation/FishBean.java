package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by Steven on 16/11/30.
 */
public class FishBean {

    /**
     * fsStoreId : 1
     * fsStoreName : 鲤鱼
     * fsTime : 1480605890000
     * sort : 1
     * username : 吃过饭了吗
     */

    private String fsStoreId;
    private String fsStoreName;
    private String fsTime;
    private String sort;
    private String username;
    private String pinyin;
    private String description;
    private String facePic;


    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFsStoreId() {
        return fsStoreId;
    }

    public void setFsStoreId(String fsStoreId) {
        this.fsStoreId = fsStoreId;
    }

    public String getFsStoreName() {
        return fsStoreName;
    }

    public void setFsStoreName(String fsStoreName) {
        this.fsStoreName = fsStoreName;
    }

    public String getFsTime() {
        return fsTime;
    }

    public void setFsTime(String fsTime) {
        this.fsTime = fsTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }
}
