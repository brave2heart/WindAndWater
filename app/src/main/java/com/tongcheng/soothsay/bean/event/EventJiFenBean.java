package com.tongcheng.soothsay.bean.event;

/**
 * Created by bozhihuatong on 2016/12/16.
 */
public class EventJiFenBean {

    public void setJiFen(String jiFen) {
        mJiFen = jiFen;
    }

    public EventJiFenBean(String jiFen) {

        mJiFen = jiFen;
    }

    private String mJiFen;

    public String getJiFen() {
        return mJiFen;
    }
}
