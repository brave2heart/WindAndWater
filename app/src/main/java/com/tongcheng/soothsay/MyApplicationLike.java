package com.tongcheng.soothsay;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tongcheng.soothsay.helper.BuglyHelper;
import com.tongcheng.soothsay.helper.RongImHelper;
import com.tongcheng.soothsay.other.MyCrashHandler;


/**
 * Created by Steven on 16/10/24.
 *
 * 自定义ApplicationLike类.
 *
 * 注意：这个类是Application的代理类，以前所有在Application的实现必须要全部拷贝到这里<br/>
 *
 */
public class MyApplicationLike extends DefaultApplicationLike {

    private static Application mApplication;

    public MyApplicationLike(Application application, int tinkerFlags,
                             boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                             long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // TODO: 安装tinker
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = getApplication();

        //初始化bugly
        BuglyHelper.init(getApplication());

        //融云初始化
        RongImHelper.init(getApplication());

        //自定义处理崩溃  不显示崩溃弹窗
        //Thread.setDefaultUncaughtExceptionHandler(MyCrashHandler.getInstance());

    }

    public static Application getInstance() {
        return mApplication;
    }

    //用于融云连接判断当前进程
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }





    /*private String AppChannel() {
        String channel = "";
        ChannelInfo channelInfo = WalleChannelReader.getChannelInfo(getApplication());
        if (channelInfo != null) {
            channel = channelInfo.getChannel();
        }
        return channel;
    }*/

}
