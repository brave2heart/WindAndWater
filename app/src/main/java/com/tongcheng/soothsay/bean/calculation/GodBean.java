package com.tongcheng.soothsay.bean.calculation;

import static android.R.attr.name;

/**
 * Created by 宋家任 on 2016/11/3.
 * 神仙实体类
 */

public class GodBean {
    private String name1;
    private String name2;
    private int iconRes;

    public GodBean(String name1, String name2, int iconRes) {
        this.name1 = name1;
        this.name2 = name2;
        this.iconRes = iconRes;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}
