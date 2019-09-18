package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gubr on 2017/2/16.
 */

public class NewsTypeBean {

    /**
     * id : 1
     * typeName : 生肖
     */

    @SerializedName("id")
    private int id;
    @SerializedName("typeName")
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
