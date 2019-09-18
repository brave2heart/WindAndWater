package com.tongcheng.soothsay.other;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyCrashHandler implements UncaughtExceptionHandler {

    private static MyCrashHandler myCrashHandler ;

    //1.私有化构造方法
    private MyCrashHandler(){
    }

    public static synchronized MyCrashHandler getInstance(){
        if(myCrashHandler!=null){
            return myCrashHandler;
        }else {
            myCrashHandler  = new MyCrashHandler();
            return myCrashHandler;
        }
    }

    public void uncaughtException(Thread arg0, Throwable arg1) {

    }




}