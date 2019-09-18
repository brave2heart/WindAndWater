package com.living.utils;


import android.content.Context;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by weihao on 2018/1/10.
 * glide 图片加载
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView view) {
        //Glide 加载图片简单用法
        Glide.with(context).load((String) path).into(view);
    }

}
