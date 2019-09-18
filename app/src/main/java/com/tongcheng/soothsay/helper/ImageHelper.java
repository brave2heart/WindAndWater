package com.tongcheng.soothsay.helper;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tongcheng.soothsay.MyApplicationLike;
import com.tongcheng.soothsay.utils.ImageOptionUtil;

import java.io.File;

/**
 * Created by Steven on 16/10/10.
 * 图片加载工具类
 */

public class ImageHelper {

    private static ImageHelper helper;

    private static ImageLoaderConfiguration config;


    public static ImageHelper getInstance() {
        if (helper == null) {
            synchronized (ImageHelper.class) {
                if (helper == null) {
                    helper = new ImageHelper();
                }
            }
        }

        return helper;
    }


    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void display(String url, ImageView imageView) {
        display(url, imageView, ImageOptionUtil.getDefultListOptions());
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     */
    public void display(String url,int err_img ,ImageView imageView) {
        DisplayImageOptions options = ImageOptionUtil.normalOptions(err_img,err_img);
        display(url, imageView, options);
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     * @param options   图片显示的一些配置
     */
    public void display(String url, ImageView imageView, DisplayImageOptions options) {
        display(url, imageView, options, null);
    }

    /**
     * 加载图片
     *
     * @param url
     * @param imageView
     * @param loadingListener 加载监听
     */
    public void display(String url, ImageView imageView, ImageLoadingListener loadingListener) {
        display(url, imageView, null, loadingListener);
    }


    public void display(String url, ImageView imageView, DisplayImageOptions options, ImageLoadingListener loadingListener) {
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(url, imageView, options, loadingListener);
    }

    /**
     * 获取磁盘缓存file
     *
     * @return
     */
    public File getDiskCacheDirectory() {
        return ImageLoader.getInstance().getDiskCache().getDirectory();
    }

    /**
     * 获取磁盘缓存路径
     *
     * @return
     */
    public String getDiskCachePath() {
        return getDiskCacheDirectory().getPath();
    }

    public void initConfig(){
        config = new ImageLoaderConfiguration.Builder(MyApplicationLike.getInstance())
                .memoryCacheExtraOptions(480, 800)     // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)                      // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize((int) (Runtime.getRuntime().maxMemory() / 8))
                .diskCacheSize(30 * 1024 * 1024)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(MyApplicationLike.getInstance(), 5 * 1000, 30 * 1000))
                .build();// 开始构建
        //.writeDebugLogs() // Remove for release app

        ImageLoader.getInstance().init(config);
    }


    private ImageHelper() {
        initConfig();
    }
}
