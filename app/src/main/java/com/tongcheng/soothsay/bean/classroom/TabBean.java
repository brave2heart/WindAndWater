package com.tongcheng.soothsay.bean.classroom;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by 宋家任 on 2016/12/23.
 * 购物车bean
 */

public class TabBean implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabBean(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }

    public TabBean(String title) {
        this.title = title;
    }
}