package com.tongcheng.soothsay.helper;

import android.content.Context;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * Created by Steven on 16/12/20.
 * 异常上传
 */
public class BuglyHelper {

    private static String testId = "7b2a1d686c";
    private static String releseId = "4bc6a7764f";

    /**
     * 初始化
     * 参数1:上下文对象
     * 参数2:注册时申请的APPID
     * 参数3:是否开启debug模式,true表示打开debug模式,false表示关闭调试模式
     */
    public static void init(Context context){
        Bugly.init(context.getApplicationContext(), releseId, true);
    }


    /**
     * 检查版本更新
     * 参数1:isManual 用户手动点击检查,非用户点击操作请传false
     * 参数2:isSilence 是否显示弹窗等交互,[true:没有弹窗和toast] [false:有弹窗或toast]
     */
    public static void checkUpdata(boolean isManual,boolean isSilence){
        Beta.checkUpgrade(isManual,isSilence);
    }


}
