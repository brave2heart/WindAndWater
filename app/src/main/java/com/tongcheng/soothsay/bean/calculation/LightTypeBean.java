package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by ALing on 2016/12/5 0005.
 */

public class LightTypeBean {
    private int lithtType;
    private int selectLightType;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getLithtType() {
        return lithtType;
    }

    public void setLithtType(int lithtType) {
        this.lithtType = lithtType;
    }

    public int getSelectLightType() {
        return selectLightType;
    }

    public void setSelectLightType(int selectLightType) {
        this.selectLightType = selectLightType;
    }
}
