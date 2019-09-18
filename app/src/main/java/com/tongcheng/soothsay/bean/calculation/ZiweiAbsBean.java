package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Steven on 16/11/24.
 */

public class ZiweiAbsBean implements Serializable {
    /**
     * daXian : 82 - 91
     * gongGz : 己巳
     * gongMz : 财帛
     * xiaoXian : 2 14 26 38 50 62 74
     * xingMzYS : ["天巫","天刑","大耗","红鸾"]
     * xingMzYX : ["亡神"]
     * xingMzYZ : ["龙德"]
     * xingMzZS : ["紫微","七杀平","文曲庙"]
     * xingMzZX : ["小耗"]
     * xingMzZZ : ["长生"]
     */

    private static final long serialVersionUID = 1L;

    private String daXian;
    private String gongGz;
    private String gongMz;
    private String xiaoXian;
    private List<String> xingMzYS;
    private List<String> xingMzYX;
    private List<String> xingMzYZ;
    private List<String> xingMzZS;
    private List<String> xingMzZX;
    private List<String> xingMzZZ;

    public String getDaXian() {
        return daXian;
    }

    public void setDaXian(String daXian) {
        this.daXian = daXian;
    }

    public String getGongGz() {
        return gongGz;
    }

    public void setGongGz(String gongGz) {
        this.gongGz = gongGz;
    }

    public String getGongMz() {
        return gongMz;
    }

    public void setGongMz(String gongMz) {
        this.gongMz = gongMz;
    }

    public String getXiaoXian() {
        return xiaoXian;
    }

    public void setXiaoXian(String xiaoXian) {
        this.xiaoXian = xiaoXian;
    }

    public List<String> getXingMzYS() {
        return xingMzYS;
    }

    public void setXingMzYS(List<String> xingMzYS) {
        this.xingMzYS = xingMzYS;
    }

    public List<String> getXingMzYX() {
        return xingMzYX;
    }

    public void setXingMzYX(List<String> xingMzYX) {
        this.xingMzYX = xingMzYX;
    }

    public List<String> getXingMzYZ() {
        return xingMzYZ;
    }

    public void setXingMzYZ(List<String> xingMzYZ) {
        this.xingMzYZ = xingMzYZ;
    }

    public List<String> getXingMzZS() {
        return xingMzZS;
    }

    public void setXingMzZS(List<String> xingMzZS) {
        this.xingMzZS = xingMzZS;
    }

    public List<String> getXingMzZX() {
        return xingMzZX;
    }

    public void setXingMzZX(List<String> xingMzZX) {
        this.xingMzZX = xingMzZX;
    }

    public List<String> getXingMzZZ() {
        return xingMzZZ;
    }

    public void setXingMzZZ(List<String> xingMzZZ) {
        this.xingMzZZ = xingMzZZ;
    }
}
