package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class ChengGu implements Serializable{


    /**
     * born : 1998-12-18 13:00:00
     * lunar : 一九九八年十月卅十 未时
     * ming : 此命为人多才多能，心机为巧，祖业凋零，离乡别井可成家业，兄弟少力，驳杂多端，出外有贵人扶持，一生无刑克，无大难，只是救人无功，恩中招怨，重义轻才，易聚易散，早年不能聚财，三十三岁方知劳苦，凡事顺意，三十八九，四十岁称心如意，三子送终，寿元六十九，死于三月中。
     * nan : 劳劳碌碌苦中求，东奔西走何日休，若使终身勤与俭，老来稍可免忧愁。
     * nv : 此命推来比郎强，婚姻大事碍无妨。中年走过坎坷运，末年渐比先前强。
     * result : ["3","0"]
     */

    private String born;
    private String lunar;
    private String ming;
    private String nan;
    private String nv;
    private List<String> result;

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getMing() {
        return ming;
    }

    public void setMing(String ming) {
        this.ming = ming;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
