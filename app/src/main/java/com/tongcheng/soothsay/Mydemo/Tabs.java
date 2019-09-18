package com.tongcheng.soothsay.Mydemo;


/**
 * Created by weihao on 2017/11/23.
 */

public class Tabs {

    private Class fragment;
    private int icon;
    private int iconName;

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIconName() {
        return iconName;
    }

    public void setIconName(int iconName) {
        this.iconName = iconName;
    }

    public Tabs(Class fragment, int icon, int iconName) {
        this.fragment = fragment;
        this.icon = icon;
        this.iconName = iconName;
    }
}
