package com.tongcheng.soothsay.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.tongcheng.soothsay.R;

/**
 * Created by Steven on 16/10/10.
 */

public class ImageOptionUtil {

    /**
     * 用于显示普通的图片
     *
     * @param loadImg  加载中显示图片
     * @param errorImg 加载错误显示图片
     */
    public static DisplayImageOptions normalOptions(int loadImg, int errorImg) {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadImg) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(errorImg)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(errorImg)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//		        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//		        .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//		        .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;
    }


    /**
     * 用于显示列表的图片
     *
     * @return
     * @paramloadImg 加载中显示图片
     * @paramerrorImg 加载错误显示图片
     */
    public static DisplayImageOptions getDefultListOptions() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.place_holder_banner_1) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.place_holder_banner_1)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.froum_list_iv)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//		        .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//		        .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//              .displayer(new FadeInBitmapDisplayer(1000,true,true,false))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;
    }


    public static DisplayImageOptions getDefultOption() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.explore_top_ad) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.explore_top_ad)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.explore_top_ad)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
        return options;
    }


}
