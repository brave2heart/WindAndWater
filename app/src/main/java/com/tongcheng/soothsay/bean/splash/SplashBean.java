package com.tongcheng.soothsay.bean.splash;

/**
 * Created by 宋家任 on 2016/12/6.
 * 闪屏页实体bean
 */

public class SplashBean {

    /**
     * adPicUrl : http://120.76.219.201:8080/file/getLocalFile.do?fileType=cacheImages&fileName=11301480928669525.jpg
     * adRedirectUrl : http://www.baidu.com
     */

    private String adPicUrl;
    private String adRedirectUrl;
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdPicUrl() {
        return adPicUrl;
    }

    public void setAdPicUrl(String adPicUrl) {
        this.adPicUrl = adPicUrl;
    }

    public String getAdRedirectUrl() {
        return adRedirectUrl;
    }

    public void setAdRedirectUrl(String adRedirectUrl) {
        this.adRedirectUrl = adRedirectUrl;
    }
}
