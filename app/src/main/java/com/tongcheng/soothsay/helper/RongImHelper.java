package com.tongcheng.soothsay.helper;

import android.app.Application;
import android.content.Context;

import com.tongcheng.soothsay.MyApplicationLike;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Steven on 16/12/30.
 * 融云帮助类
 */

public class RongImHelper {

    /**
     * 初始化
     */
    public static void init(Context context){
        RongIM.init(context);
    }

    /**
     * 连接融云
     * @param app
     * @param token
     * @param callback
     */
    public static void connect(Application app, String token , RongIMClient.ConnectCallback callback){
        if (app.getApplicationInfo().packageName.equals(MyApplicationLike.getCurProcessName(app))) {
            RongIM.connect(token, callback);
        }
    }

}

