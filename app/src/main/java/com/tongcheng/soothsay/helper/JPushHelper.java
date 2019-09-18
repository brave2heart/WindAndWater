package com.tongcheng.soothsay.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by ALing on 2017/1/13 0013.
 */

public class JPushHelper {
    private static JPushHelper  mHelper;

    public static JPushHelper getInstance(){
        if(mHelper == null){
            synchronized (JPushHelper.class){
                if(mHelper == null){
                    mHelper = new JPushHelper();
                }
            }
        }

        return mHelper;
    }

    public static void init(Context context){
        JPushInterface.init(context);
    }

    /**
     * 设置要推送的别名
     * @param context
     * @param phone     使用手机号当别名
     */
    public void setJPushAlias(Activity context, String phone) {
        JPushInterface.setAliasAndTags(context, phone, null,new TagAliasCallback() {
            @Override
            public void gotResult(int code, String s, Set<String> set) {
                String logs ;
                switch (code) {
                    case 0:
                        logs = "Set tag and alias success";
                        // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                        Log.i("Tag", "gotResult: "+logs);
                        break;
                    case 6002:

                        break;
                    default:
                        logs = "Failed with errorCode = " + code;
                }
            }
        });
    }
}
