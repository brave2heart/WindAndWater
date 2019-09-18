package com.tongcheng.soothsay.data.calculate.zhuanyun.qifutai;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by bozhihuatong on 2016/12/6.
 */

public class QiFuTaiTempData implements Serializable {
    private static QiFuTaiTempData mQiFuTaiTempData;
    private HashSet<String> gods;

    public boolean isQingXianSuccessful() {
        return isSuccessful;
    }

    public String getName() {
        return mName;
    }

    private boolean isSuccessful;
    private String mName;

    private QiFuTaiTempData() {
        gods = new HashSet<>();
    }


    public static QiFuTaiTempData newInstaince() {
        if (mQiFuTaiTempData == null) {
            synchronized (QiFuTaiTempData.class) {
                if (mQiFuTaiTempData == null) {
                    mQiFuTaiTempData = new QiFuTaiTempData();
                }
            }
        }
        return mQiFuTaiTempData;
    }

    public HashSet<String> getGods() {
        return gods;
    }


    public boolean isGodPraying(String godName) {
        return gods.contains(godName);
    }

    public void addGod(String godName) {
        gods.add(godName);
    }

    public void remove(String godName) {
        gods.remove(godName);
    }


    public void setisQingXianSuccessful(boolean b, String name) {
        isSuccessful = b;
        mName = name;
    }








}
