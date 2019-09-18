package com.tongcheng.soothsay.bean.event;

/**
 * Created by bozhihuatong on 2016/12/6.
 * 大仙修改愿望用的  EventBus 事件类型实体
 */

public class ChangeWishBean {
    public ChangeWishBean(String wish) {
        Wish = wish;
    }

    public String Wish;
}
