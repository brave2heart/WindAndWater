package com.tongcheng.soothsay.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/**
 * Created by Steven on 17/1/5.
 * 广播注册工具lei
 */
public class ReceiverUtil {

    /**
     * 注册网络监听广播
     */
    public static void receiverNetwork(Activity activity, BroadcastReceiver receiver){
        if(receiver == null){
            return ;
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        activity.registerReceiver(receiver,filter);
    }


}
