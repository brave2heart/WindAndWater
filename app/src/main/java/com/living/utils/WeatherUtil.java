package com.living.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.tongcheng.soothsay.R;

/**
 * Created by weihao on 2017/12/23.
 */

public class WeatherUtil {
    //获取天气情况图片
    //google推荐方法: Drawable TopDrawableOne = ContextCompat.getDrawable(context,R.drawable.icon_test);
    public static Drawable getdraWeather(Context context, String status) {
        if ("晴".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather001);
        } else if ("晴转多云".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather004);
        } else if ("多云转小雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather006);
        } else if ("多云".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather004);
        } else if ("多云转阴".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather003);
        } else if ("小雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather011);
        } else if ("中雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather012);
        } else if ("大雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather007);
        } else if ("阵雪".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather010);
        } else if ("阴转小雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather011);
        } else if ("阴转中雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather012);
        } else if ("阴".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather003);
        } else if ("小雨转中雨".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather012);
        } else if ("小雨转阴".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather003);
        } else if ("阴转多云".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather004);
        } else if ("中雨转多云".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather004);
        } else if ("多云转晴".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather001);
        } else if ("小雨转多云".equals(status)) {
            return ContextCompat.getDrawable(context, R.drawable.weather004);
        }
        return ContextCompat.getDrawable(context, R.drawable.weather_no);
    }

}
