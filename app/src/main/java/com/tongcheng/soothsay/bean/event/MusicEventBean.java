package com.tongcheng.soothsay.bean.event;

/**
 * Created by bozhihuatong on 2016/12/14.
 */

public class MusicEventBean {

    public static final int PLAY_KEY=0;
    public static final int STOP_KEY=1;
    public static final int BYSTOP_KEY=2;


    private int event;

    public MusicEventBean(int event) {

        this.event = event;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }
}
