package com.tongcheng.soothsay.bean.event;

/**
 * Created by Steven on 16/12/15.
 */
public class EventLoginBean {

    public static final int OUT_LOGIN = 0;      //登录事件
    public static final int LOGIN = 1;          //退出登录事件

    public int status;

    public EventLoginBean(int status) {
        this.status = status;
    }
}
