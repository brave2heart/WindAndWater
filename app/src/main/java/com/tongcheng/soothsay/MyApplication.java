package com.tongcheng.soothsay;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.io.File;

import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * Created by Steven on 16/12/20.
 * 自定义Application.
 * <p>
 * 注意：这个类集成TinkerApplication类，这里面不做任何操作，所有Application的代码都会放到ApplicationLike继承类当中<br/>
 * <pre>
 * 参数解析：
 * 参数1：int tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
 * 参数2：String delegateClassName Application代理类 这里填写你自定义的ApplicationLike
 * 参数3：String loaderClassName  Tinker的加载器，使用默认即可
 * 参数4：boolean tinkerLoadVerifyFlag  加载dex或者lib是否验证md5，默认为false
 * </pre>
 */
public class MyApplication extends TinkerApplication {

    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.tongcheng.soothsay.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(this.getCacheDir(), "cache_path_name");
        CacheWebView
                .getCacheConfig()
                .init(this, cacheFile.getAbsolutePath(), 1024 * 1024 * 100, 1024 * 1024 * 10).enableDebug(true);//100M 磁盘缓存空间,10M 内存缓存空间
    }


}
