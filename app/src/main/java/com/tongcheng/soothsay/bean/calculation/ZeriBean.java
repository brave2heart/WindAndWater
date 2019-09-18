package com.tongcheng.soothsay.bean.calculation;

/**
 * Created by bozhihuatong on 2016/12/22.
 */
public class ZeriBean {

    private String title;
    private String content;
    private boolean isCheck;

    public ZeriBean(String title, String content, boolean isCheck) {
        this.title = title;
        this.content = content;
        this.isCheck = isCheck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }


}
