package com.tongcheng.soothsay.utils;

import android.content.Context;

//根据比例计算高度或宽度
public class ScaleUtil {

    /**
     * @param scaleW
     * @param scaleH
     * @param context
     * @return
     */
    public static int getScaleHeight(int scaleW,int scaleH,Context context){
        return getScaleHeight(scaleW, scaleH,-1, context);
    }

    /**
     * @param scaleW
     * @param scaleH
     * @param context
     * @return
     */
    public static int getScaleHeight(int scaleW,int scaleH,int width,Context context){
        if(context == null){
            return 0;
        }

        if(width == -1){
            width = WindowUtil.getScreenSize(WindowUtil.TYPE_WIDTH, context);
        }
        int sw = scaleW;
        int sh = scaleH;
        return (width*sh)/sw;
    }

}
