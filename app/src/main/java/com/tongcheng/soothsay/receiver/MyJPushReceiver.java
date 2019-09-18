package com.tongcheng.soothsay.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tongcheng.soothsay.ui.activity.mine.MyMessageActivity;

import cn.jpush.android.api.JPushInterface;

public class MyJPushReceiver extends BroadcastReceiver {
    private static String TAG = "pushreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

            Log.i(TAG,"收到了自定义消息@@消息内容是:"+ content);
            Log.i(TAG,"收到了自定义消息@@消息extra是:"+ extra);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
            Log.i(TAG,"收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            Log.i(TAG,"用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MyMessageActivity.class); // 自定义打开的界面
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.i(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

}