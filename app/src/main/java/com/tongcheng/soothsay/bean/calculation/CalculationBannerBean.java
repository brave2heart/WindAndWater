package com.tongcheng.soothsay.bean.calculation;

/**
 * @description: 命理推算中广告条的测试实体类，到时要以实际接口为准
 * @author: lijuan
 * @date: 2016-10-24
 * @time: 16:55
 */
public class CalculationBannerBean {
    private int id;
    private String content;
    private String url;

    public CalculationBannerBean(int id, String content, String url) {
        this.id = id;
        this.content = content;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
