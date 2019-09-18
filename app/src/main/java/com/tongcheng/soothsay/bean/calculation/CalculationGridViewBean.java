package com.tongcheng.soothsay.bean.calculation;

/**
 * @description: 命理推算中GridView模块的测试实体类，到时要以实际接口为准
 * @author: lijuan
 * @date: 2016-10-24
 * @time: 16:55
 */
public class CalculationGridViewBean {
    public String name;
    public int iconRes;

    public CalculationGridViewBean(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}
