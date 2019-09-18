package com.living.glide;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;

/**
 * Created by mootor on 2017/12/6.
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        File storageDirectory = Environment.getExternalStorageDirectory();
        String downloadDirectoryPath=storageDirectory+"/amway/amway_boku/images";
        //设置缓存的大小为100M
        int cacheSize = 100*1000*1000;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath,cacheSize));

    }
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
