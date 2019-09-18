package com.tongcheng.soothsay.bean.calculation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gubr on 2016/12/3.
 */

public class QfgpListBean implements Serializable{

    private static final long serialVersionUID = 5L;
    /**
     * sort : 1
     * gpId : 2
     * gpName : name
     * qfDate : 0
     */

    @SerializedName("sort")
    public int sort;
    @SerializedName("gpId")
    public int gpId;
    @SerializedName("gpName")
    public String gpName;
    @SerializedName("qfDate")
    public String qfDate;
}
