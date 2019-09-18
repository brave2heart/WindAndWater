package com.tongcheng.soothsay.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.R;

/**
 * Created by bozhihuatong on 2016/12/7.
 */

public class WuxingUtil {
    private static WuxingUtil mWuXing;
    private Context mContext;

    private WuxingUtil() {

        mContext = MyApplicationLike.getInstance();
    }

    public static WuxingUtil newInstace() {
        if (mWuXing == null) {
            synchronized (WuxingUtil.class) {
                if (mWuXing == null) {
                    mWuXing = new WuxingUtil();
                }
            }
        }
        return mWuXing;
    }


    private static String name = "甲乙丙丁戊己庚辛壬癸子丑寅卯辰巳午未申酉戌亥";
    private static String wuxing = "木木火火土土金金水水水土木木土火火土金金土水";
    private String shuxin = "阳阴阳阴阳阴阳阴阳阴阳阴阳阴阳阴阳阴阳阴阳阴";


    private static String shengxiao = "鼠牛虎兔龙蛇马羊猴鸡狗猪";

    private int[] shengxiaoImage = {
            R.drawable.ctmx_xtmp_1, R.drawable.ctmx_xtmp_2, R.drawable.ctmx_xtmp_3,
            R.drawable.ctmx_xtmp_4, R.drawable.ctmx_xtmp_5, R.drawable.ctmx_xtmp_6,
            R.drawable.ctmx_xtmp_7, R.drawable.ctmx_xtmp_8, R.drawable.ctmx_xtmp_9,
            R.drawable.ctmx_xtmp_10, R.drawable.ctmx_xtmp_11, R.drawable.ctmx_xtmp_12,};



    public int getShengXiaoImage(String name){
        int i = shengxiao.indexOf(name);
        return shengxiaoImage[i];
    }

    public String getWuxingStr(String name) {
        int i = WuxingUtil.name.indexOf(name);
        char c = wuxing.charAt(i);
        return String.valueOf(c);
    }


    public String getShuxing(String name) {
        int i = name.indexOf(name);
        char c = shuxin.charAt(i);
        return String.valueOf(c);
    }


    public Drawable getWuxingImag(String wuxingname) {


        String wuxingStr = getWuxingStr(wuxingname);
        Drawable drawable = null;
        switch (wuxingStr) {
            case "金":
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_gold);
                break;
            case "木":
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_wood);
                break;
            case "水":
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_water);
                break;
            case "火":
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_fire);
                break;
            case "土":
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_earth);
                break;
            default:
                drawable = mContext.getResources().getDrawable(R.drawable.eightcharacters_wuxing_gold);
                break;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }
}
