package com.tongcheng.soothsay.bean.event;

/**
 * Created by Gubr on 2017/1/4.
 * 网络状态
 */

public class EventNetWorkChangeBean {

    public static final int NETWORK_CONNECTED=0;//网络连接上
    public static final int NETWORK_UNCONNECTE=1;//网络断开了

    private int mEvent;

    public EventNetWorkChangeBean(int event) {
        mEvent = event;
    }

    public int getEvent() {
        return mEvent;
    }

    public void setEvent(int event) {
        mEvent = event;
    }
}
